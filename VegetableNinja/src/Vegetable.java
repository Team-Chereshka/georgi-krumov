public abstract class Vegetable {

    private String name;
    private int row;
    private int col;
    private int movesToGrow;
    private int remainingMovesToGrow;
    private int stamina;
    private int power;

    /*not all vegetables have power and stamina increasing/decreasing abilities ->
                                                                      resolve by creating child objects of vegetable */

    public Vegetable(String name, int row, int col, int movesToGrow) {
        this.setName(name);
        this.movesToGrow = movesToGrow;
        this.remainingMovesToGrow = 0;
        this.row = row;
        this.col = col;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    public void harvest() {
        this.remainingMovesToGrow = this.movesToGrow;
    }

    public void grow() {
        if (this.remainingMovesToGrow > 0) {
            this.remainingMovesToGrow--;
        }
    }

    public boolean isHarvested() {
        return this.remainingMovesToGrow == 0;
    }
}
