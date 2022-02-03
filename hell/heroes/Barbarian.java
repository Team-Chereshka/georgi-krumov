package heroes;

import entities.miscellaneous.HeroInventory;
import interfaces.Hero;
import interfaces.Item;
import interfaces.Recipe;

import java.util.Collection;

public class Barbarian implements Hero {
    private String name;
    private long strength;
    private long agility;
    private long intelligence;
    private long hitPoints;
    private long damage;
    private HeroInventory heroInventory;

    private static final int DEFAULT_STRENGTH = 90;
    private static final int DEFAULT_AGILITY = 25;
    private static final int DEFAULT_INTELLIGENCE = 10;
    private static final int DEFAULT_HIT_POINTS = 350;
    private static final int DEFAULT_DAMAGE = 150;


    public Barbarian(String name) {
        this.name = name;
        this.strength = DEFAULT_STRENGTH;
        this.agility = DEFAULT_AGILITY;
        this.intelligence = DEFAULT_INTELLIGENCE;
        this.hitPoints = DEFAULT_HIT_POINTS;
        this.damage = DEFAULT_DAMAGE;
        this.heroInventory = new HeroInventory();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength;
    }

    @Override
    public long getAgility() {
        return this.agility;
    }

    @Override
    public long getIntelligence() {
        return this.intelligence;
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints;
    }

    @Override
    public long getDamage() {
        return this.damage;
    }

    @Override
    public Collection<Item> getItems() {
        return this.heroInventory.getCommonItems().values();
    }

    @Override
    public void addItem(Item item) {
        this.heroInventory.addCommonItem(item);
        updateStats();
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.heroInventory.addRecipeItem(recipe);
        updateStats();
    }

    public void updateStats(){
        this.strength = DEFAULT_STRENGTH + heroInventory.getTotalStrengthBonus();
        this.agility = DEFAULT_AGILITY + heroInventory.getTotalAgilityBonus();
        this.intelligence = DEFAULT_INTELLIGENCE + heroInventory.getTotalIntelligenceBonus();
        this.hitPoints = DEFAULT_HIT_POINTS + heroInventory.getTotalHitPointsBonus();
        this.damage = DEFAULT_DAMAGE + heroInventory.getTotalDamageBonus();

    }

}



