import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListChildren {
    public static void listChildren(File fileDir) {

        File arr[] = fileDir.listFiles();

        for (int i = 0; i < arr.length; i++) {
            File f1 = arr[i];

            if (f1.isDirectory()) {
                System.out.print("d");
            } else {
                System.out.print("-");
            }

            if (f1.canRead()) {
                System.out.print("r");
            } else {
                System.out.print("-");
            }

            if (f1.canWrite()) {
                System.out.print("w");
            } else {
                System.out.print("-");
            }

            if (f1.canExecute()) {
                System.out.print("x");
            } else {
                System.out.print("-");
            }

            SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy HH:mm:ss");
            Date date = new Date(f1.lastModified());

            System.out.printf("%10d" + " " + "%22s" + " " + f1.getName() + "\n", f1.length(), formatter.format(date));
        }
    }
}