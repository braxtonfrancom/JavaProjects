import java.util.Scanner;
import java.lang.Math;

public class Pyramid2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("How many lines?: ");

        int rows = input.nextInt();

        double largestNum = ((Math.pow(2, rows-1)));
        int largestNumber = (int)largestNum;

        String numString = "" + largestNumber;
        int numLength = (numString.length() + 1);

        int rowCount = 1;

        for (int i = rows; i > 0; i--) {
            System.out.println("\n");
            for (int j = 1; j <= i; j++) {
                System.out.printf("%" + numLength + "s", " ");
            }
            int firstColumnNum = 1;
            for (int k = 1; k <= rowCount; k++) {
                System.out.printf("%" + numLength + "d", (firstColumnNum));
                firstColumnNum *= 2;
            }
            for (int k = (rowCount - 1); k > 0; k--) {
                System.out.printf("%" + numLength + "d", (int)Math.pow(2, k - 1));
            }
            rowCount++;
        }
    }
}