import java.nio.file.Path;
import java.nio.file.Paths;

public class MakeDirectory {
    public static String makeDirectory(String[] listOfParams, String currentDir) {
        Path proposedPath;

        if (listOfParams.length >= 2) {
            proposedPath = Paths.get(currentDir, listOfParams[1]);
            if (proposedPath.toFile().exists()) {
                System.out.printf("Sorry, '%s' already exists. Please select a new name\n", listOfParams[1]);
            } else {
                proposedPath.toFile().mkdir();
            }
        }
        else {
            System.out.println("There is no name for the new directory. Please enter you new directories name");
        }
        return currentDir;
    }
}
