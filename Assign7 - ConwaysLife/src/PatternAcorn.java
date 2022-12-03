public class PatternAcorn extends Pattern {

    private boolean acornData[][] = {
            {false, true, false, false, false, false, false},
            {false, false, false, true, false, false, false},
            {true, true, false, false, true, true, true},
    };

    @Override
    public int getSizeX() {
        return acornData[0].length;
    }

    @Override
    public int getSizeY() {
        return acornData.length;
    }

    @Override
    public boolean getCell(int x, int y) {
        return acornData[y][x];

    }
}
