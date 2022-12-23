import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Assign3 {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        boolean running = true;
        long totalTime = 0;
        ArrayList<String> historyOfShell = new ArrayList<>();

        String currentDir = System.getProperty("user.dir");
        File fileDir = new File(currentDir);

        while (running) {
            System.out.printf("[%s]: ", currentDir);
            String commandAndParams = input.nextLine();
            String[] commandAndParamsList = Split.splitCommand(commandAndParams);

            if (commandAndParams.contains("^")) {
                try {
                    if (!historyOfShell.isEmpty()) {
                        historyOfShell.add(Integer.parseInt(commandAndParamsList[1]) + 1, historyOfShell.get(Integer.parseInt(commandAndParamsList[1]) - 1));
                    }
                } catch (Exception e) {}
            }
            else {
                historyOfShell.add(commandAndParams);
            }

            ArrayList<String> myArray = new ArrayList<>();
            for (int i = 0; i < commandAndParamsList.length; i++) {
                myArray.add(commandAndParamsList[i]);
            }

            if (commandAndParamsList[0].equals("^")) {
                try {
                    commandAndParams = historyOfShell.get(Integer.parseInt(commandAndParamsList[1]) - 1);
                    commandAndParamsList = Split.splitCommand(commandAndParams);
                } catch (Exception e) {}
            }
            if (commandAndParamsList[0].equals("cd")) {
                currentDir = ChangeDirectories.changeDirectories(commandAndParamsList, currentDir, fileDir);
                fileDir = new File(currentDir);
            }
            else if (commandAndParamsList[0].equals("ptime")) {
                System.out.printf("Total time in child processes: " + "%.4f" + " seconds\n", (totalTime / 1000.0f) % 60);
            }
            else if (commandAndParamsList[0].equals("list")) {
                ListChildren.listChildren(fileDir);
            }
            else if (commandAndParamsList[0].equals("mdir")) {
                MakeDirectory.makeDirectory(commandAndParamsList, currentDir);
            }
            else if (commandAndParamsList[0].equals("rdir")) {
                DeleteDirectory.deleteDirectory(commandAndParamsList, currentDir);
            }
            else if (commandAndParamsList[0].equals("history")) {
                System.out.println(DisplayHistory.displayHistory(historyOfShell));
            }
            else if (myArray.contains("|")) {
                totalTime = Piping.piping(commandAndParamsList, myArray, fileDir, totalTime);
            }
            else if (commandAndParamsList[0].equals("exit")) {
                break;
            }
            else {
                totalTime = MyProcessBuilder.myProcessBuilder(commandAndParamsList, fileDir, totalTime);
            }
        }
    }
}