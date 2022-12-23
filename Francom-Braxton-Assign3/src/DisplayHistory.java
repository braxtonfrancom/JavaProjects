import java.util.ArrayList;

public class DisplayHistory {
    public static String displayHistory(ArrayList<String> historyOfShell) {
        System.out.println("-- Command History --");
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < historyOfShell.size(); i++) {
            finalString.append(i + 1).append(" : ").append(historyOfShell.get(i)).append("\n");
        }
        return finalString.toString();
    }
}
