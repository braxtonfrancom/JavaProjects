import java.sql.SQLOutput;

/**
 * Assignment 3 for CS 1410
 * This program determines if points are contained within circles or rectangles.
 */
public class Inside {
    /**
     * This is the primary driver code to test the "inside" capabilities of the
     * various functions.
     */
    public static void main(String[] args) {
        double[] ptX = {1, 2, 3, 4};
        double[] ptY = {1, 2, 3, 4};
        double[] circleX = {0, 5};
        double[] circleY = {0, 5};
        double[] circleRadius = {3, 3};
        double[] rectLeft = {-2.5, -2.5};
        double[] rectTop = {2.5, 5.0};
        double[] rectWidth = {6.0, 5.0};
        double[] rectHeight = {5.0, 2.5};

        /*PRINTING THE REPORT*/
        System.out.println("--- Report of Points and Circles ---");
        System.out.println("");
        for (int k = 0; k < 1; k++)
            for (int j = 0; j < circleX.length; j++) {

            for (int i = 0; i < ptX.length; i++) {
                reportPoint(ptX[i], ptY[i]);

                if (isPointInsideCircle(ptX[i], ptY[i], circleX[j], circleY[j], circleRadius[j]) == true) {
                    System.out.print(" is inside ");
                } else {
                    System.out.print(" is outside ");
                }
                reportCircle(circleX[j], circleY[j], circleRadius[j]);
            }
            }
        System.out.println("");
        System.out.println("--- Report of Points and Rectangles ---");
        System.out.println("");


        for (int k = 0; k < 1; k++)
            for (int j = 0; j < circleX.length; j++) {

                for (int i = 0; i < ptX.length; i++) {
                    reportPoint(ptX[i], ptY[i]);

                    if (isPointInsideRectangle(ptX[i], ptY[i], rectLeft[j], rectTop[j], rectWidth[j], rectHeight[j])) {
                        System.out.print(" is inside ");
                    } else {
                        System.out.print(" is outside ");
                    }
                    reportRectangle(rectLeft[j], rectTop[j], rectLeft[j] + rectWidth[j], rectTop[j] - rectHeight[j]);

                }
            }
    }

    public static void reportPoint(double x, double y) {
            System.out.printf("Point(%2s, %2s)", x, y);
        }

    public static void reportCircle(double x, double y, double r) {
        System.out.printf("Circle(%2s, %2s) Radius: %2s %n", x, y, r);
    }

    public static void reportRectangle(double left, double top, double width, double height) {
        System.out.printf("Rectangle(%2s, %2s, %2s, %2s)%n", left, top, width, height);
    }

    public static boolean isPointInsideCircle(double ptX, double ptY, double circleX, double circleY, double circleRadius) {
        double distance = Math.sqrt(Math.pow((ptX - circleX), 2) + Math.pow((ptY - circleY), 2));

        if (distance > circleRadius) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPointInsideRectangle(double ptX, double ptY, double rLeft, double rTop, double rWidth, double rHeight) {

        if (ptX >= rLeft && ptX <= (rLeft + rWidth) && ptY <= rTop && ptY >= (rTop - rHeight)) {
            return true;
        } else {
            return false; }
    }
}

