public class PatternBlinker extends Pattern {

    private boolean blinkerData[][] = {
            {false, true, false},
            {false, true, false},
            {false, true, false}
    };

    @Override
    public int getSizeX() {
        return blinkerData[0].length;
    }

    @Override
    public int getSizeY() {
        return blinkerData.length;
    }

    @Override
    public boolean getCell(int x, int y) {
        return blinkerData[y][x];
    }

}