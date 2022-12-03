import java.util.Scanner;
import java.lang.Math;

public class Pyramid1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("How many lines?: ");

        int rows = input.nextInt();
        String numString = "" + rows;

        int numLength = (numString.length() + 1);
        int rowCount = 1;


        for (int i = rows; i > 0; i--) {
            System.out.print("\n");
            for (int j = 1; j <= i; j++) {
                System.out.printf("%" + numLength + "s", " ");
            }
            int firstColumnNum = 1 + rowCount;
            for (int k = 1; k <= rowCount; k++) {
                System.out.printf("%" + numLength + "d", (firstColumnNum - 1));
                firstColumnNum -= 1;

            }
            int secondColumnNum = 2;
            for (int k = 1; k <= (rowCount - 1); k++) {
                secondColumnNum += 1;
                System.out.printf("%" + numLength + "d", (secondColumnNum - 1));
            }
            rowCount++;
        }
    }
}