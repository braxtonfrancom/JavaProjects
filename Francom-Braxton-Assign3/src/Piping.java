import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Piping {

    public static long piping(String[] commandAndParamsList, ArrayList<String> myArray, File fileDir, long totalTime) {
        int middleIndex = 0;
        for (int i = 0; i < myArray.size(); i++) {
            if (myArray.get(i).contains("|")) {
                middleIndex = i;
            }
        }
        String[] firstOp = Arrays.copyOfRange(commandAndParamsList, 0, middleIndex);
        String[] secondOp = Arrays.copyOfRange(commandAndParamsList, middleIndex + 1, commandAndParamsList.length);

        ProcessBuilder pb1 = new ProcessBuilder(firstOp);
        ProcessBuilder pb2 = new ProcessBuilder(secondOp);

        pb1.directory(fileDir);
        pb2.directory(fileDir);

        pb1.redirectInput(ProcessBuilder.Redirect.INHERIT);
        pb2.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        try {
            long startTime = System.currentTimeMillis();
            Process p1 = pb1.start();
            Process p2 = pb2.start();

            java.io.InputStream in = p1.getInputStream();
            java.io.OutputStream out = p2.getOutputStream();

            int data;
            while ((data = in.read()) != -1) {
                out.write(data);
            }
            out.flush();
            out.close();

            p1.waitFor();
            p2.waitFor();

            long endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }
        catch (Exception ex) {
        }
        return totalTime;
    }
}