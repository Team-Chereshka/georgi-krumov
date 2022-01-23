public class Royal extends Vegetable {

    private static final int STAMINA = 10;
    private static final int POWER = 20;

    public Royal(String name, int row, int col, int movesToGrow) {
        super(name, row, col, movesToGrow);
        this.setStamina(STAMINA);
        this.setPower(POWER);
    }
}
