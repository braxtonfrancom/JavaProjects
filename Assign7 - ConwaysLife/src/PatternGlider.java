public class PatternGlider extends Pattern{

    private boolean gliderData[][] = {
            {false, false, true},
            {true, false, true},
            {false, true, true},

    };
    @Override
    public int getSizeX() {
        return gliderData[0].length;
    }

    @Override
    public int getSizeY() {
        return gliderData.length;
    }

    @Override
    public boolean getCell(int x, int y) {
        return gliderData[y][x];

    }

}
