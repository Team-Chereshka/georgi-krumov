public class Asparagus extends Vegetable {

    private static final int POWER = 5;
    private static final int STAMINA = -5;


    public Asparagus(String name, int row, int col, int movesToGrow) {
        super(name, row, col, movesToGrow);
        super.setPower(POWER);
        super.setStamina(STAMINA);
    }
}
