import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class DeleteDirectory {
    public static void deleteDirectory(String[] listOfParams, String currentDir) {
        Path proposedNewPath;

        if (listOfParams.length >= 2) {
            proposedNewPath = Paths.get(currentDir, listOfParams[1]);
            if (proposedNewPath.toFile().isDirectory()) {
                if (Objects.requireNonNull(proposedNewPath.toFile().listFiles()).length == 0) {
                    proposedNewPath.toFile().delete();
                }
                else {
                    System.out.println("The directory '" + listOfParams[1] + "' is not empty"); }

            } else {
                System.out.println("This directory does not exist. Please try again");
            }
        }
        else {
            System.out.println("No directory was specified to delete. Please follow the following syntax: rdir <filename>");
        }
    }
}
