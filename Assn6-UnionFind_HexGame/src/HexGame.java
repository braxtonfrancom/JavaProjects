import java.util.ArrayList;
import java.util.Arrays;

public class HexGame {

    public int[] gameBoard;  //Stores the color of each space
    DisjointSet myDisjointSet;

    int LEFT_EDGE = 124;
    int RIGHT_EDGE = 125;
    int TOP_EDGE = 122;
    int BOTTOM_EDGE = 123;

    public HexGame(int size) {
        myDisjointSet = new DisjointSet(size * size + 4);
        gameBoard = new int[size * size + 4];

        Arrays.fill(gameBoard, 1);

        gameBoard[TOP_EDGE - 1] = 3;
        gameBoard[BOTTOM_EDGE - 1] = 3;
        gameBoard[LEFT_EDGE - 1] = 2;
        gameBoard[RIGHT_EDGE - 1] = 2;
    }

    public boolean playBlue(int position, boolean displayNeighbors) {
        ArrayList<Integer> neighbors = getNeighborsBlue(position);

        if (gameBoard[position - 1] != 1) { return false; }

        if (displayNeighbors) {
            System.out.print("CELL " + position + ": [ ");
            for (Integer element: getNeighborsBlue(position)) {
                System.out.print(element + " ");
            }
            System.out.println("]");
        }
        gameBoard[position - 1] = 2; //Marks the space blue

        for (Integer neighbor : neighbors) {
            if (gameBoard[neighbor - 1] == 2) {
                myDisjointSet.union(myDisjointSet.find(neighbor), myDisjointSet.find(position));
            }
        }
        return myDisjointSet.find(LEFT_EDGE) == myDisjointSet.find(RIGHT_EDGE);
    }

    public boolean playRed(int position, boolean displayNeighbors) {
        ArrayList<Integer> neighbors = getNeighborsRed(position);

        if (gameBoard[position - 1] != 1) { return false; }

        if (displayNeighbors) {
            System.out.print("CELL " + position + ": [ ");
            for (Integer element: getNeighborsRed(position)) {
                System.out.print(element + " ");
            }
            System.out.println("]");
        }
        gameBoard[position - 1] = 3; //Marks the space red

        for (Integer neighbor : neighbors) {
            if (gameBoard[neighbor - 1] == 3) {
                myDisjointSet.union(myDisjointSet.find(neighbor), myDisjointSet.find(position));
            }
        }
        return myDisjointSet.find(TOP_EDGE) == myDisjointSet.find(BOTTOM_EDGE);
    }


    public int getSize() {
        return (int)Math.sqrt(gameBoard.length - 4);
    }

    private ArrayList<Integer> getNeighborsBlue(int position) {

        ArrayList<Integer> neighborArrayBlue = new ArrayList<>();

        if (position == 1) {
            neighborArrayBlue.add(position + 1);
            neighborArrayBlue.add(LEFT_EDGE);
            neighborArrayBlue.add(position + getSize());
        }
        if (position == getSize()) {  //Top-right corner
            neighborArrayBlue.add(position - 1);
            neighborArrayBlue.add(position + getSize() - 1);
            neighborArrayBlue.add(position + getSize());
            neighborArrayBlue.add(RIGHT_EDGE);
        }
        if (position == getSize() * getSize()) {  //Bottom-right corner
            neighborArrayBlue.add(position - getSize());
            neighborArrayBlue.add(position - 1);
            neighborArrayBlue.add(RIGHT_EDGE);
        }
        if (position == (getSize() * getSize()) - getSize() + 1) {  //Bottom-left corner
            neighborArrayBlue.add(position - getSize());
            neighborArrayBlue.add(position - getSize() + 1);
            neighborArrayBlue.add(position + 1);
            neighborArrayBlue.add(LEFT_EDGE);
        }
        if (position % getSize() == 0 && position != getSize() && (position != getSize() * getSize()) ) {  //End of all the END rows(except top and bottom left corners)
            neighborArrayBlue.add(position - getSize());
            neighborArrayBlue.add(position - 1);
            neighborArrayBlue.add(position + getSize() - 1);
            neighborArrayBlue.add(position + getSize());
            neighborArrayBlue.add(RIGHT_EDGE);
        }
        if (position % getSize() == 1 && position != 1 && position != getSize() * getSize() - getSize() + 1) {  //All the first column
            neighborArrayBlue.add(position - getSize());
            neighborArrayBlue.add(position - getSize() + 1);
            neighborArrayBlue.add(position + 1);
            neighborArrayBlue.add(position + getSize());
            neighborArrayBlue.add(LEFT_EDGE);
        }
        if (position < getSize() && position != 1) {  //Edge cells on first row
            neighborArrayBlue.add(position - 1);
            neighborArrayBlue.add(position + 1);
            neighborArrayBlue.add(position + getSize() - 1);
            neighborArrayBlue.add(position + getSize());
        }
        if (position > (getSize() * getSize()) - getSize() + 1 && (position != getSize() * getSize())) {  //Edge cells on last row
            neighborArrayBlue.add(position - getSize());
            neighborArrayBlue.add(position - getSize() + 1);
            neighborArrayBlue.add(position - 1);
            neighborArrayBlue.add(position + 1);
        }
        else if (position > getSize() + 1 && position < getSize() * getSize() - getSize() + 1) {  //Non-edge cell
            neighborArrayBlue.add(position - getSize());
            neighborArrayBlue.add(position - getSize() + 1);
            neighborArrayBlue.add(position - 1);
            neighborArrayBlue.add(position + 1);
            neighborArrayBlue.add(position + getSize() - 1);
            neighborArrayBlue.add(position + getSize());
        }
        
        return neighborArrayBlue;
    }


    private ArrayList<Integer> getNeighborsRed(int position) {

        ArrayList<Integer> neighborArrayRed = new ArrayList<>();

        if (position == 1) {
            neighborArrayRed.add(position + 1);
            neighborArrayRed.add(TOP_EDGE);
            neighborArrayRed.add(position + getSize());
        }
        if (position == getSize()) {  //Top-right corner
            neighborArrayRed.add(position - 1);
            neighborArrayRed.add(position + getSize() - 1);
            neighborArrayRed.add(position + getSize());
            neighborArrayRed.add(TOP_EDGE);
        }
        if (position == getSize() * getSize()) { //Bottom-right corner
            neighborArrayRed.add(position - getSize());
            neighborArrayRed.add(position - 1);
            neighborArrayRed.add(BOTTOM_EDGE);
        }
        if (position == (getSize() * getSize()) - getSize() + 1) {  //Bottom-left corner
            neighborArrayRed.add(position - getSize());
            neighborArrayRed.add(position - getSize() + 1);
            neighborArrayRed.add(position + 1);
            neighborArrayRed.add(BOTTOM_EDGE);
        }
        if (position % getSize() == 0 && position != getSize() && (position != getSize() * getSize()) ) {  //End of all the END rows(except top and bottom left corners
            neighborArrayRed.add(position - getSize());
            neighborArrayRed.add(position - 1);
            neighborArrayRed.add(position + getSize() - 1);
            neighborArrayRed.add(position + getSize());
        }
        if (position % getSize() == 1 && position != 1 && position != getSize() * getSize() - getSize() + 1) {  //All the first column
            neighborArrayRed.add(position - getSize());
            neighborArrayRed.add(position - getSize() + 1);
            neighborArrayRed.add(position + 1);
            neighborArrayRed.add(position + getSize());
        }
        if (position < getSize() && position != 1) {  //Edge cells on first row
            neighborArrayRed.add(position - 1);
            neighborArrayRed.add(position + 1);
            neighborArrayRed.add(position + getSize() - 1);
            neighborArrayRed.add(position + getSize());
            neighborArrayRed.add(TOP_EDGE);
        }
        if (position > (getSize() * getSize()) - getSize() + 1 && (position != getSize() * getSize())) {  //Edge cells on last row
            neighborArrayRed.add(position - getSize());
            neighborArrayRed.add(position - getSize() + 1);
            neighborArrayRed.add(position - 1);
            neighborArrayRed.add(position + 1);
            neighborArrayRed.add(BOTTOM_EDGE);
        }
        else if (position > getSize() + 1 && position < getSize() * getSize() - getSize()) {  //Non-edge cell
            neighborArrayRed.add(position - getSize());
            neighborArrayRed.add(position - getSize() + 1);
            neighborArrayRed.add(position - 1);
            neighborArrayRed.add(position + 1);
            neighborArrayRed.add(position + getSize() - 1);
            neighborArrayRed.add(position + getSize());
        }

        return neighborArrayRed;
    }
}