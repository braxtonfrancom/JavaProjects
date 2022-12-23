import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangeDirectories {
    public static String changeDirectories(String[] listOfParams, String currentDir, File fileDir) throws Exception {
        Path proposedPath;

        try {
            if (listOfParams.length >= 2) {
                if (listOfParams[1].equals("..")) {
                    proposedPath = Paths.get(fileDir.getParent());
                    currentDir = proposedPath.toString();
                } else { //It's a file name
                    proposedPath = Paths.get(currentDir, listOfParams[1]);

                    if (proposedPath.toFile().isDirectory()) {
                        currentDir = proposedPath.toString();
                    } else {
                        System.out.printf("The directory '%s' does not exist!\n", listOfParams[1]);
                    }
                }
            } else { //It is just 'cd' without anything else
                proposedPath = Paths.get(System.getProperty("user.home"));
                currentDir = proposedPath.toString();
            }
        }
        catch (Exception ignored) {
        }
            return currentDir;
    }
}
