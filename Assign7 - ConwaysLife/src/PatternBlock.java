public class PatternBlock extends Pattern{

    private boolean blockData[][] = {
            {true, true},
            {true, true},
    };

    @Override
    public int getSizeX() {
        return blockData[0].length;
    }

    @Override
    public int getSizeY() {
        return blockData.length;
    }

    @Override
    public boolean getCell(int x, int y) {
        return blockData[y][x];

    }

}


