import java.util.ArrayList;
import java.util.List;

public class Ninja {

    private String name;
    private int power;
    private int stamina;
    private int row;
    private int column;

    private List<Vegetable> vegetableList;

    public Ninja(String name) {
        this.name = name;
        this.power = 1;
        this.stamina = 1;
        this.vegetableList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        if (stamina >= 0) {
            this.stamina = stamina;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public List<Vegetable> getVegetableList() {
        return vegetableList;
    }

    public void eatVegetables() {
        for (Vegetable vegetable : this.vegetableList) {
            this.setPower(this.getPower() + vegetable.getPower());
            this.setStamina(this.getStamina() + vegetable.getStamina());
        }

        this.vegetableList.clear();
    }
}
