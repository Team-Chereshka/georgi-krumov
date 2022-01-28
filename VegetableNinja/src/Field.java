import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Field {

    private char[][] matrix;

    private List<Vegetable> vegetableList;

    private Ninja ninja1;
    private Ninja ninja2;

    private Ninja currentPlayer;


    public Field(Ninja ninja1, Ninja ninja2) {
        this.ninja1 = ninja1;
        this.ninja2 = ninja2;
        this.currentPlayer = ninja1;
        this.vegetableList = new ArrayList<>();
    }

    private boolean isInsideBounds(int ninjaRow, int ninjaCol) {
        if (ninjaRow < 0 || ninjaRow >= this.matrix.length || ninjaCol < 0 || ninjaCol >= this.matrix[0].length) {
            return false;
        }

        return true;
    }

    private void switchPlayer() {
        if (this.currentPlayer == this.ninja1) {
            this.currentPlayer = this.ninja2;
        } else {
            this.currentPlayer = this.ninja1;
        }
    }

    private void fight() {
        if (this.ninja1.getPower() > this.ninja2.getPower()) {
            System.out.printf("Winner: %s%n" +
                    "Power: %d%n" +
                    "Stamina: %d", this.ninja1.getName(), this.ninja1.getPower(), this.ninja1.getStamina());
        } else if (this.ninja1.getPower() < this.ninja2.getPower()) {
            System.out.printf("Winner: %s%n" +
                    "Power: %d%n" +
                    "Stamina: %d", this.ninja2.getName(), this.ninja2.getPower(), this.ninja2.getStamina());
        } else {
            System.out.printf("Winner: %s%n" +
                    "Power: %d%n" +
                    "Stamina: %d", this.currentPlayer.getName(), this.currentPlayer.getPower(), this.currentPlayer.getStamina());
        }
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public List<Vegetable> getVegetableList() {
        return this.vegetableList;
    }

    public void growVegetables() {
        for (Vegetable vegetable : this.vegetableList) {
            //if ninja is on the vegetable it SHOULDN'T GROW
            if (this.ninja1.getRow() != vegetable.getRow() && this.ninja1.getColumn() != vegetable.getColumn() &&
                    this.ninja2.getRow() != vegetable.getRow() && this.ninja2.getColumn() != vegetable.getColumn()) {
                vegetable.grow();
            }
        }
    }

    public void createMatrix(int n, int m, Scanner scanner) {
        //note to self: use ONLY 1 scanner in the whole app
        char[][] matrix = new char[n][m];

        for (int row = 0; row < matrix.length; row++) {
            String input = scanner.nextLine().toUpperCase();
            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = input.charAt(col);

                if (matrix[row][col] == this.ninja1.getName().charAt(0)) {
                    this.ninja1.setRow(row);
                    this.ninja1.setColumn(col);

                } else if (matrix[row][col] == this.ninja2.getName().charAt(0)) {
                    this.ninja2.setRow(row);
                    this.ninja2.setColumn(col);
                }

                switch (matrix[row][col]) {
                    case 'A':
                        this.vegetableList.add(new Asparagus("Asparagus", row, col, 2));
                        break;

                    case 'B':
                        this.vegetableList.add(new Broccoli("Broccoli", row, col, 3));
                        break;

                    case 'C':
                        this.vegetableList.add(new CherryBerry("CherryBerry", row, col, 5));
                        break;

                    case 'M':
                        this.vegetableList.add(new Mushroom("Mushroom", row, col, 5));
                        break;

                    case 'R':
                        this.vegetableList.add(new Royal("Royal", row, col, 10));
                        break;

                    case '-':
                        new BlankSpace("BlankSpace", row, col);
                        break;

                    case '*':
                        this.vegetableList.add(new MeloLemonMelon("MeloLemonMelon", row, col, 9999));
                        break;
                }
            }
        }
        this.matrix = matrix;

    }

    public boolean moveCurrentPlayer(char direction) {
        switch (direction) {
            case 'U':
                if (isInsideBounds(this.currentPlayer.getRow() - 1, this.currentPlayer.getColumn())) {
                    this.currentPlayer.setRow(this.currentPlayer.getRow() - 1);
                }
                this.currentPlayer.setStamina(this.currentPlayer.getStamina() - 1);
                break;
            case 'D':
                if (isInsideBounds(this.currentPlayer.getRow() + 1, this.currentPlayer.getColumn())) {
                    this.currentPlayer.setRow(this.currentPlayer.getRow() + 1);
                }
                this.currentPlayer.setStamina(this.currentPlayer.getStamina() - 1);
                break;
            case 'L':
                if (isInsideBounds(this.currentPlayer.getRow(), this.currentPlayer.getColumn() - 1)) {
                    this.currentPlayer.setColumn(this.currentPlayer.getColumn() - 1);
                }
                this.currentPlayer.setStamina(this.currentPlayer.getStamina() - 1);
                break;
            case 'R':
                if (isInsideBounds(this.currentPlayer.getRow(), this.currentPlayer.getColumn() + 1)) {
                    this.currentPlayer.setColumn(this.currentPlayer.getColumn() + 1);
                }
                this.currentPlayer.setStamina(this.currentPlayer.getStamina() - 1);
                break;
        }

        this.growVegetables();

        if (this.ninja1.getRow() == this.ninja2.getRow() && this.ninja1.getColumn() == this.ninja2.getColumn() /*condition*/) {
            this.fight();
            return true;
        }


        for (Vegetable vegetable : this.vegetableList) {
            if (vegetable.getRow() == this.currentPlayer.getRow() && vegetable.getColumn() == this.currentPlayer.getColumn()) {
                this.currentPlayer.getVegetableList().add(vegetable);
                vegetable.harvest();
            }
        }

        if (this.currentPlayer.getStamina() == 0) {
            //SPECIAL CASE - BONUS MODE, EAT 5  MUSHROOMS - NOT WORKING!!!
            for (int i = 0; i < this.currentPlayer.getVegetableList().size(); i++) {
                if (this.currentPlayer.getVegetableList().get(i).getName().equals("MeloLemonMelon")) {
                    this.currentPlayer.eatVegetables();
                    this.switchPlayer();
                    this.currentPlayer.setPower(this.currentPlayer.getPower() - 50);
                    this.currentPlayer.setStamina(this.currentPlayer.getPower() - 50);
                } else {
                    this.currentPlayer.eatVegetables();
                }
            }
            this.switchPlayer();
        }

        return false;
    }
}
