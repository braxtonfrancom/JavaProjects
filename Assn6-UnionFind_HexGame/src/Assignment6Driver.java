import java.io.File;
import java.util.Scanner;

public class Assignment6Driver {
    public static void main(String[] args) {

        //testGame();
        playGame("moves1.txt");
        System.out.println();
        playGame("moves2.txt");
    }


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    private static void playGame(String filename) {
        File file = new File(filename);
        HexGame myGame = new HexGame(11);
        try (Scanner input = new Scanner(file)) {

            boolean moveDeterminer = true;
            boolean done = false;
            while (input.hasNext()) {
                if (moveDeterminer) {
                    Integer blueNextMove = input.nextInt();
                    done = myGame.playBlue(blueNextMove, false);
                    if (done) {                                                 //Finish it up!!!!!
                        System.out.println("Blue wins with a spectacular move at position " + blueNextMove + "!");
                        printGrid(myGame);
                        return;
                    }
                    moveDeterminer = false;
                }
                else if (!moveDeterminer) {
                    Integer redNextMove = input.nextInt();
                    done = myGame.playRed(redNextMove, false);
                    if (done) {
                        System.out.println("Red wins with a breath-taking move at position " + redNextMove + "!");
                        printGrid(myGame);
                        return;
                    }
                    moveDeterminer = true;
                }
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the moves file: " + ex);
        }
    }

    private static void testGame() {
        HexGame game = new HexGame(11);

        System.out.println("--- red ---");
        game.playRed(1, true);
        game.playRed(11, true);
        game.playRed(122 - 12, true);
        game.playRed(122 - 11, true);
        game.playRed(122 - 10, true);
        game.playRed(121, true);
        game.playRed(61, true);

        System.out.println("--- blue ---");
        game.playBlue(1, true);
        game.playBlue(2, true);
        game.playBlue(11, true);
        game.playBlue(12, true);
        game.playBlue(121, true);
        game.playBlue(122 - 11, true);
        game.playBlue(62, true);

        printGrid(game);
    }

    private static void printGrid(HexGame game) {
        System.out.println();
        int counter = 0;
        String spaces = " ";
        for (int i = 0; i < game.getSize(); i++) {
            System.out.print(spaces);
            for (int j = 0; j < game.getSize(); j++) {
                if (game.gameBoard[counter] == 3) {
                    System.out.print(ANSI_RED + " R" + ANSI_RESET);
                }
                else if (game.gameBoard[counter] == 2) {
                    System.out.print(ANSI_BLUE + " B" + ANSI_RESET);
                }
                else {
                    System.out.print(" 0");
                }
                counter++;
            }
            spaces += " ";
            System.out.println();
        }
    }
}
