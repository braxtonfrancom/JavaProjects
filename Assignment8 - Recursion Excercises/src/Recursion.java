import java.util.Locale;

public class Recursion {
    public static void main(String[] args) {

        int[] sumMe = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
        System.out.printf("Array Sum: %d\n", arraySum(sumMe, 0));

        int[] minMe = {0, 1, 1, 2, 3, 5, 8, -42, 13, 21, 34, 55, 89};
        System.out.printf("Array Min: %d\n", arrayMin(minMe, 0));

        String[] amISymmetric = {
                "You can cage a swallow can't you but you can't swallow a cage can you",
                "I still say cS 1410 is my favorite class"
        };
        for (String test : amISymmetric) {
            String[] words = test.toLowerCase().split(" ");
            System.out.println();
            System.out.println(test);
            System.out.printf("Is word symmetric: %b\n", isWordSymmetric(words, 0, words.length - 1));
        }

        double masses[][] = {
                {51.18},
                {55.90, 131.25},
                {69.05, 133.66, 132.82},
                {53.43, 139.61, 134.06, 121.63}
        };
        System.out.println();
        System.out.println("--- Weight Pyramid ---");
        for (int row = 0; row < masses.length; row++) {
            for (int column = 0; column < masses[row].length; column++) {
                double weight = computePyramidWeights(masses, row, column);
                System.out.printf("%.2f ", weight);
            }
            System.out.println();
        }

        char image[][] = {
                {'*', '*', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' '},
                {' ', '*', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' '},
                {' ', '*', ' ', ' ', '*', '*', '*', ' ', ' ', ' '},
                {' ', '*', '*', ' ', '*', ' ', '*', ' ', '*', ' '},
                {' ', '*', '*', ' ', '*', '*', '*', '*', '*', '*'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' '},
                {' ', ' ', ' ', '*', '*', '*', ' ', ' ', '*', ' '},
                {' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', '*', ' '}
        };
        int howMany = howManyOrganisms(image);
        System.out.println();
        System.out.println("--- Labeled Organism Image ---");
        for (char[] line : image) {
            for (char item : line) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.printf("There are %d organisms in the image.\n", howMany);
    }


    public static boolean isWordSymmetric(String[] words, int start, int end) {
        if (words.length <= 0) {
            return true;
        } else if (start == words.length - 1) {
            return true;
        } else {
            if (words.length % 2 == 0) {
                if (words[start].toLowerCase().equals(words[end].toLowerCase())) {
                    return isWordSymmetric(words, (start + 1), (end - 1));
                } else {
                    return false;
                }
            } else if (words.length % 2 != 0) {
                if (words[start].toLowerCase().equals(words[end].toLowerCase())) {
                    return isWordSymmetric(words, (start + 1), (end - 1));
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    public static long arraySum(int[] data, int position) {
        if (data.length <= 0) {
            return 0;
        } else if (position == data.length) {
            return 0;
        }

        return arraySum(data, position + 1) + data[position];
    }


    public static int arrayMin(int[] data, int position) {

        if (position == data.length - 1) {
            return data[position];
        }
        return Math.min(data[position + 1], arrayMin(data, position + 1));
    }


    public static double computePyramidWeights(double[][] masses, int row, int column) {
        if (masses.length == 0) {
            return 0;
        }
        if (row < 0 || row >= masses.length) {
            return 0;
        }
        if (masses[row].length == 0) {
            return 0;
        }
        if (column < 0 || column > masses[row].length) {
            return 0;
        }

        if (row == 0) {
            return masses[row][column];
        }
        if (column == 0) {
            return masses[row][column] + (computePyramidWeights(masses, row - 1, column)) / 2;
        }
        if (column == row) {
            return masses[row][column] + (computePyramidWeights(masses, row - 1, column - 1)) / 2;
        } else {
            return masses[row][column] + (computePyramidWeights(masses, row - 1, column - 1) / 2) + (computePyramidWeights(masses, row - 1, column) / 2);
        }
    }


    public static int howManyOrganisms(char[][] image) {
        int count = 0;
        for (int row = 0; row < image.length; row++) {
            for (int column = 0; column < image[row].length; column++) {
                if (image[row][column] == '*') {
                    count++;
                    labelOrganism(image, row, column, count);
                }
            }
        }
        return count;
    }

    public static void labelOrganism(char[][] image, int row, int column, int count) {
        char label = (char)(count + 96);

        if (row < 0) {
            return;
        }
        if (row >= image.length) {
            return;
        }
        if (column < 0) {
            return;
        }
        if (column >= image[row].length) {
            return;
        }

        if (image[row][column] == '*') {
            image[row][column] = label;
            labelOrganism(image, row + 1, column, count);
            labelOrganism(image, row - 1, column, count);
            labelOrganism(image, row, column + 1, count);
            labelOrganism(image, row, column - 1, count);
        }
    }
}