public class BlankSpace {

    private String name;
    private int row;
    private int col;


    public BlankSpace(String name, int row, int col) {
        this.setName(name);
        this.row = row;
        this.col = col;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
