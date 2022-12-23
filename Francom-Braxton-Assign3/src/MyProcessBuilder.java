import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MyProcessBuilder {

    public static long myProcessBuilder(String[] commandAndParamsList, File fileDir, long totalTime) {

        try {
            if (commandAndParamsList[commandAndParamsList.length - 1].equals("&")) {
                String[] myList = Arrays.copyOfRange(commandAndParamsList, 0, commandAndParamsList.length - 1);
                java.lang.ProcessBuilder pb = new java.lang.ProcessBuilder(myList);
                pb.directory(fileDir);
                pb.redirectInput(java.lang.ProcessBuilder.Redirect.INHERIT);
                pb.redirectOutput(java.lang.ProcessBuilder.Redirect.INHERIT);
                pb.start();
            } else {
                java.lang.ProcessBuilder pb = new java.lang.ProcessBuilder(commandAndParamsList);
                pb.directory(fileDir);
                pb.redirectInput(java.lang.ProcessBuilder.Redirect.INHERIT);
                pb.redirectOutput(java.lang.ProcessBuilder.Redirect.INHERIT);
                long startTime = System.currentTimeMillis();
                Process p = pb.start();
                p.waitFor();
                long endTime = System.currentTimeMillis();
                totalTime += (endTime - startTime);
            }
        } catch (IOException | InterruptedException | RuntimeException e) {
            System.out.println("Invalid command: " + Arrays.toString(commandAndParamsList).replace("[", "").replace("]", "").replace(",", ""));
        }
        return totalTime;
    }
}
