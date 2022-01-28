public class Mushroom extends Vegetable {

    private static final int POWER = -10;
    private static final int STAMINA = -10;

    public Mushroom(String name, int row, int col, int movesToGrow) {
        super(name, row, col, movesToGrow);
        this.setStamina(STAMINA);
        this.setPower(POWER);
    }
}
