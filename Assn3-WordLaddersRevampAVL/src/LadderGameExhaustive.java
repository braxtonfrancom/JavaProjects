import java.util.ArrayList;

public class LadderGameExhaustive extends LadderGame {


    //The differences between the two child classes are defined within those classes respectively.

    public LadderGameExhaustive(String dictionaryFile) {
        super(dictionaryFile);     //super allows me to access the LadderGame constructor within the LadderGame class
    }



    public void play(String start, String end) {
        System.out.println("Seeking exhaustive solution from " +  start  + " -> " + end);
        start = start.toLowerCase();
        end = end.toLowerCase();
        Queue<WordInfo> myQueue = new Queue<>();
        wordsOfSameLength = (ArrayList<String>)(arrayListOfArrayLists.get(start.length() -1)).clone();
        boolean foundSolution = false;

        if (!checkLengthAndPresenceOfWords(start, end)) {
            return;
        }

        myQueue.enqueue(new WordInfo(start, 1));
        wordsOfSameLength.remove(start);
        while (!myQueue.isEmpty() && !foundSolution) {
            WordInfo wordAtFrontOfQueue = myQueue.dequeue();
            ArrayList<String> wordsToAddToQueue = oneAway(wordAtFrontOfQueue.getWord(), true);

            for (int i = 0; i < (wordsToAddToQueue.size()); i++) {
                WordInfo wordBeingAddedToQueue = new WordInfo(wordsToAddToQueue.get(i), wordAtFrontOfQueue.getMoves() + 1,   wordAtFrontOfQueue.getHistory() + " " + wordsToAddToQueue.get(i));
                myQueue.enqueue(wordBeingAddedToQueue);

                if ((wordBeingAddedToQueue.getWord()).equals(end)) {
                    System.out.println("[" + wordAtFrontOfQueue.getHistory() + " " + wordsToAddToQueue.get(i) + "]" + " total enqueues " + myQueue.getCount());
                    foundSolution = true;
                    break;
                }
            }
        }
        if (!foundSolution) {
            System.out.println(start + " -> " + end + " : No ladder was found");
        }
    }
}
