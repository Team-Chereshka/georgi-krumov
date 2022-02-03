import heroes.Assassin;
import heroes.Barbarian;
import heroes.Wizard;
import interfaces.Hero;
import interfaces.Item;
import interfaces.Recipe;
import items.CommonItem;
import items.RecipeItem;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tokens = scanner.nextLine().split("\\s+");

        String command = tokens[0];


        Map<String, Hero> heroMap = new LinkedHashMap<>();

        while (true) {
            switch (command) {
                case "Hero" -> addHero(tokens, heroMap);
                case "Item" -> addItem(tokens, heroMap);
                case "Recipe" -> addRecipe(tokens, heroMap);
                case "Inspect" -> inspectHero(tokens, heroMap);
                case "Quit" -> quit(heroMap);
            }
            if (command.equals("Quit")){
                break;
            }
            tokens = scanner.nextLine().split("\\s+");
            command = tokens[0];
        }
    }

    private static void quit(Map<String, Hero> heroMap) {
        Map<String, Hero> sortedMap = heroMap.entrySet().stream().sorted((e2, e1) -> {
            long sumOfFirst =
                    e1.getValue().getAgility() + e1.getValue().getStrength() + e1.getValue().getIntelligence();
            long sumOfSecond =
                    e2.getValue().getAgility() + e2.getValue().getStrength() + e2.getValue().getIntelligence();

            int result = Long.compare(sumOfFirst, sumOfSecond);

            if (result == 0) {
                long sumOfFirstDamageAndHP = e1.getValue().getDamage() + e1.getValue().getHitPoints();
                long sumOfSecondDamageAndHP = e2.getValue().getDamage() + e2.getValue().getHitPoints();

                result = Long.compare(sumOfFirstDamageAndHP, sumOfSecondDamageAndHP);
            }
            return result;
        }).collect(Collectors.toMap(
                e -> e.getKey(),
                e -> e.getValue(),
                (x, y) -> null,
                () -> new LinkedHashMap<>()
        ));

        int count = 1;
        for (Hero hero : sortedMap.values()) {
            String heroName = hero.getName();
            String heroClass = hero.getClass().getSimpleName();
            long heroHitPoints = hero.getHitPoints();
            long heroDamage = hero.getDamage();
            long heroStrength = hero.getStrength();
            long heroAgility = hero.getAgility();
            long heroIntelligence = hero.getIntelligence();
            Collection<Item> items = heroMap.get(heroName).getItems();


            System.out.printf("%d. %s: %s%n" +
                            "###HitPoints: %d%n" +
                            "###Damage: %d%n" +
                            "###Strength: %d%n" +
                            "###Agility: %d%n" +
                            "###Intelligence: %d%n", count, heroClass, heroName,
                    heroHitPoints, heroStrength, heroDamage, heroAgility, heroIntelligence);
            if (items.isEmpty()) {
                System.out.printf("###Item: None%n");
            } else {
                System.out.printf("###Item:%n");

                StringBuilder sb = new StringBuilder();

                for (Item item : items) {
                    sb.append(item.getName()).append(", ");
                }
                sb.delete(sb.length() - 2, sb.length() - 1);
                System.out.printf("%s%n", sb);
            }
            count++;
        }
    }


    private static void inspectHero(String[] tokens, Map<String, Hero> heroMap) {
        String heroName = tokens[1];
        String heroClass = heroMap.get(heroName).getClass().getSimpleName();
        long heroHitPoints = heroMap.get(heroName).getHitPoints();
        long heroDamage = heroMap.get(heroName).getDamage();
        long heroStrength = heroMap.get(heroName).getStrength();
        long heroAgility = heroMap.get(heroName).getAgility();
        long heroIntelligence = heroMap.get(heroName).getIntelligence();
        Collection<Item> items = heroMap.get(heroName).getItems();

        System.out.printf("Hero: %s, Class: %s%n" +
                        "HitPoints: %d, Damage: %d%n" +
                        "Strength: %d%n" +
                        "Agility: %d%n" +
                        "Intelligence: %d%n", heroName, heroClass,
                heroHitPoints, heroDamage, heroStrength, heroAgility, heroIntelligence);
        if (items.isEmpty()) {
            System.out.printf("###Items: None%n");
        } else {
            System.out.printf("###Items:%n");
            for (Item item : items) {
                String itemName = item.getName();
                int itemStrength = item.getStrengthBonus();
                int itemAgility = item.getAgilityBonus();
                int itemIntelligence = item.getIntelligenceBonus();
                int itemHitPoints = item.getHitPointsBonus();
                int itemDamage = item.getDamageBonus();

                System.out.printf("###Item: %s%n" +
                                "###+%d Strength%n" +
                                "###+%d Agility%n" +
                                "###+%d Intelligence%n" +
                                "###+%d HitPoints%n" +
                                "###+%d Damage%n", itemName, itemStrength,
                        itemAgility, itemIntelligence, itemHitPoints, itemDamage);
            }
        }
    }

    private static void addRecipe(String[] tokens, Map<String, Hero> heroMap) {
        String recipeName = tokens[1];
        String heroName = tokens[2];
        int strengthBonus = Integer.parseInt(tokens[3]);
        int agilityBonus = Integer.parseInt(tokens[4]);
        int intelligenceBonus = Integer.parseInt(tokens[5]);
        int hitPointsBonus = Integer.parseInt(tokens[6]);
        int damageBonus = Integer.parseInt(tokens[7]);
        List<String> requiredItems = new ArrayList<>();

        //ingredients start from token 8(inclusive)
        for (int i = 8; i < tokens.length; i++) {
            requiredItems.add(tokens[i]);
        }
        Recipe recipe = new RecipeItem(recipeName, strengthBonus, agilityBonus, intelligenceBonus,
                hitPointsBonus, damageBonus, requiredItems);
        heroMap.get(heroName).addRecipe(recipe);
        System.out.printf("Added recipe - %s to Hero - %s%n", recipeName, heroName);

    }

    private static void addItem(String[] tokens, Map<String, Hero> heroMap) {
        String itemName = tokens[1];
        String heroName = tokens[2];
        int strengthBonus = Integer.parseInt(tokens[3]);
        int agilityBonus = Integer.parseInt(tokens[4]);
        int intelligenceBonus = Integer.parseInt(tokens[5]);
        int hitPointsBonus = Integer.parseInt(tokens[6]);
        int damageBonus = Integer.parseInt(tokens[7]);

        Item item = new CommonItem(itemName, strengthBonus,
                agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
        heroMap.get(heroName).addItem(item);
        System.out.printf("Added item - %s to Hero - %s%n", itemName, heroName);
    }

    private static void addHero(String[] tokens, Map<String, Hero> heroMap) {
        String name = tokens[1];
        String type = tokens[2];

        switch (type) {
            case "Barbarian" -> {
                Hero barbarian = new Barbarian(name);
                heroMap.put(name, barbarian);
            }
            case "Assassin" -> {
                Hero assassin = new Assassin(name);
                heroMap.put(name, assassin);
            }
            case "Wizard" -> {
                Hero wizard = new Wizard(name);
                heroMap.put(name, wizard);
            }
        }
        System.out.printf("Created %s - %s%n", type, name);
    }
}