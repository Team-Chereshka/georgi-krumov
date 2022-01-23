public class Broccoli extends Vegetable {

    private static final int POWER = 10;

    public Broccoli(String name, int row, int col, int movesToGrow) {
        super(name, row, col, movesToGrow);
        this.setPower(POWER);
    }
}
