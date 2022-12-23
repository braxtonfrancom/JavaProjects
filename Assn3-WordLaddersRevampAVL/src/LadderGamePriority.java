import java.util.ArrayList;
import java.util.Locale;

public class LadderGamePriority extends LadderGame {

    public LadderGamePriority(String dictionaryFile) {
        super(dictionaryFile);
    }

    public void play(String start, String end) {
        System.out.println("Seeking A* solution from " + start + " -> " + end);
        AVLTree<WordInfoPriority> myPriorityQueue = new AVLTree<>();
        AVLTree<WordInfoPrev> wordsPreviouslyUsedAVLTree = new AVLTree<>();
        wordsOfSameLength = (ArrayList<String>)(arrayListOfArrayLists.get(start.length() - 1));
        boolean foundSolution = false;
        if (!checkLengthAndPresenceOfWords(start, end)) {
            return;
        }
        WordInfoPriority startWord = new WordInfoPriority(start, 0, getNumberOfDifferentLetters(start, end));
        myPriorityQueue.insert(startWord);

        while (!myPriorityQueue.isEmpty() && !foundSolution) {
            WordInfoPriority smallestExpectedWorkInAVL = myPriorityQueue.deleteMin();
            WordInfoPrev wordInfoPrevObject = new WordInfoPrev(smallestExpectedWorkInAVL.getWord(), smallestExpectedWorkInAVL.getMoves());
            wordsPreviouslyUsedAVLTree.insert(wordInfoPrevObject);

            ArrayList<String> wordsToAddToAVL = oneAway(smallestExpectedWorkInAVL.getWord(), false);

            for (int i = 0; i < wordsToAddToAVL.size(); i++) {
                WordInfoPriority newLadder = new WordInfoPriority(wordsToAddToAVL.get(i), smallestExpectedWorkInAVL.getMoves() + 1, getNumberOfDifferentLetters(wordsToAddToAVL.get(i), end), smallestExpectedWorkInAVL.getHistory() + " " + wordsToAddToAVL.get(i));
                WordInfoPrev newPrevLadder = new WordInfoPrev(wordsToAddToAVL.get(i), smallestExpectedWorkInAVL.getMoves() + 1);

                WordInfoPrev alreadyFoundLadder = wordsPreviouslyUsedAVLTree.getByKey(newPrevLadder); //Access the ladder already found for that specific word

                if (newLadder.getWord().equals(end)) {
                    foundSolution = true;
                    System.out.println("[" + smallestExpectedWorkInAVL.getHistory() + " " + newLadder.getWord() + "] total enqueues " + myPriorityQueue.getCount());
                    break;
                }
                if (!wordsPreviouslyUsedAVLTree.contains(newPrevLadder)) {
                    myPriorityQueue.insert(newLadder);
                    wordsPreviouslyUsedAVLTree.insert(newPrevLadder);
                }
                else if (wordsPreviouslyUsedAVLTree.contains(newPrevLadder) && newLadder.getMoves() < alreadyFoundLadder.getLengthOfLadder()) {
                     alreadyFoundLadder.setLengthOfLadder(newLadder.getEstimatedWork());       //Update the estimated work with new estimated work
                     myPriorityQueue.insert(newLadder);
                }
            }
        }
        if (!foundSolution) {
            System.out.println( "There was no ladder found for " + start + " --> " + end);
        }
    }
}