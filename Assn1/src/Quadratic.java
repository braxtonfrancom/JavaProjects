import java.util.Scanner;
import java.lang.*;

/**
 @author Braxton Francom
 */

public class Quadratic {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a, b, c: ");
        double coefficientA = input.nextDouble();
        double coefficientB = input.nextDouble();
        double coefficientC = input.nextDouble();


        double root1 = ((-coefficientB) + (Math.sqrt(Math.pow(coefficientB, 2.0) - (4.0 * coefficientA * coefficientC)))) / (2.0 * coefficientA);

        double root2 = ((-coefficientB) - (Math.sqrt(Math.pow(coefficientB, 2.0) - (4.0 * coefficientA * coefficientC)))) / (2.0 * coefficientA);

        //Determine the number of roots
        double discriminate = (coefficientB * coefficientB) - (4.0 * coefficientA * coefficientC);

        if (discriminate > 0) {
            System.out.println("There are two roots for the quadratic equation with these coefficients.");
            System.out.println("r1 = " + root1);
            System.out.println("r2 = " + root2);
        }
        else if (discriminate == 0) {
            System.out.println("There is one root for the quadratic equation with these coefficients.");
            System.out.println("r1 = " + root1);
        }
        else {
            System.out.println("There are no roots for the quadratic equation with these coefficients.");
        }
    }
}