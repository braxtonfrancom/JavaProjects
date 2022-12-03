public class LifeSimulator {
    final private int sizeX;
    final private int sizeY;
    private boolean[][] worldArray;

    public LifeSimulator(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.worldArray = new boolean[getSizeX()][getSizeY()];
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public boolean getCell(int x, int y) {
        return worldArray[x][y];
    }

    public void insertPattern(Pattern pattern, int startX, int startY) {

        for (int row = 0; row < pattern.getSizeX(); row++) {
            for (int col = 0; col < pattern.getSizeY(); col++) {
                worldArray[startX + row][startY + col] = pattern.getCell(row,col);
            }
        }
    }

    private int getNeighborCount(int x, int y) {
        int liveNeighborCount = 0;

        if (y - 1 >= 0) {
            if (worldArray[x][y - 1]) {
                liveNeighborCount += 1;
            }
        }
        if (x + 1 < this.sizeX && y - 1 >= 0)
            if (worldArray[x + 1][y - 1]) {
                liveNeighborCount += 1;
            }
        if (x - 1 >= 0 && y - 1 >= 0) {
            if (worldArray[x - 1][y - 1]) {
                liveNeighborCount += 1;
            }
        }
        if (x - 1 >= 0) {
            if (worldArray[x - 1][y]) {
                liveNeighborCount += 1;
            }
        }
        if (x - 1 >= 0 && y + 1 < this.sizeY) {
            if (worldArray[x - 1][y + 1]) {
                liveNeighborCount += 1;
            }
        }
        if (x + 1 < this.sizeX) {
            if (worldArray[x + 1][y]) {
                liveNeighborCount += 1;
            }

        }
        if (y + 1 < this.sizeY) {
            if (worldArray[x][y + 1]) {
                liveNeighborCount += 1;
            }
        }
        if (y + 1 < this.sizeY && x + 1 < this.sizeX) {
            if (worldArray[x + 1][y + 1]) {
                liveNeighborCount += 1;
            }
        }
        return liveNeighborCount;
    }


    public void update() {
        //boolean[][] updatedGrid = worldArray.clone();
        boolean[][] updatedGrid = new boolean[getSizeX()][getSizeY()];
        for (int row = 0; row < worldArray.length; row++) {
            for (int col = 0; col < worldArray[row].length; col++) {
                updatedGrid[row][col] = worldArray[row][col];
            }
        }
        for (int row = 0; row < worldArray.length; row++) {
            for (int col = 0; col < worldArray[row].length; col++) {

                if (getCell(row, col)) { /*Cell we are looking at is alive*/
                    if (getNeighborCount(row, col) < 2) {
                        updatedGrid[row][col] = false;
                    }
//                    else if (getNeighborCount(row, col) == 2 || getNeighborCount(row, col) == 3) {
//                        /*The boolean is already alive so just leave it alive*/
//                        updatedGrid[row][col] = true;
//                    }
                    else if (getNeighborCount(row, col) > 3) {
                        updatedGrid[row][col] = false;
                    }
                }
                else if (!getCell(row, col)) { /*Cell we are looking at is dead*/
                    if (getNeighborCount(row, col) == 3) {
                        updatedGrid[row][col] = true;
                    }
                }

            }
        }
        for (int row = 0; row < worldArray.length; row++) {
            for (int col = 0; col < worldArray[row].length; col++) {
                worldArray[row][col] = updatedGrid[row][col];
            }
        }
    }

}
