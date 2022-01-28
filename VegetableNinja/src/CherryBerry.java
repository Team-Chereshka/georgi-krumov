public class CherryBerry extends Vegetable {

    private static final int STAMINA = 10;

    public CherryBerry(String name, int row, int col, int movesToGrow) {
        super(name, row, col, movesToGrow);
        this.setStamina(STAMINA);
    }
}
