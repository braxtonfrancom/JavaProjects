import java.util.Scanner;
import java.lang.*;

/**
 @author Braxton Francom
 */

public class Isbn {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the first 9 digits of an ISBN: ");

        int number = input.nextInt();

        int d1 = number / 100_000_000;

        //Finding the second number
        int n1 = number - (d1 * 100_000_000);
        int d2 = n1 / 10_000_000;

        //Finding the third number
        int n2 = n1 - (d2 * 10_000_000);
        int d3 = n2 / 1_000_000;

        //Finding the fourth number
        int n3 = n2 - (d3 * 1_000_000);
        int d4 = n3 / 100_000;

        //Finding the fifth number
        int n4 = n3 - (d4 * 100_000);
        int d5 = n4 / 10_000;

        //Finding the sixth number
        int n5 = n4 - (d5 * 10_000);
        int d6 = n5 / 1_000;

        //Finding the seventh number
        int n6 = n5 - (d6 * 1_000);
        int d7 = n6 / 100;

        //Finding the eighth number
        int n7 = n6 - (d7 * 100);
        int d8 = n7 / 10;

        //Finding the ninth number
        int n8 = n7 - (d8 * 10);
        int d9 = n8 / 1;

        //Checksum calculations
        int checkSum = ((d1 + d2 * 2 + d3 * 3 + d4 * 4 + d5 * 5 + d6 * 6 + d7 * 7 + d8 * 8 + d9 * 9) % 11);

        //Final printout
        if (checkSum == 10) {
            System.out.println("The ISBN-10 number is: " + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + "X");
        }

        else {
            System.out.print("The ISBN-10 number is: " + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + checkSum);
        }

    }
}