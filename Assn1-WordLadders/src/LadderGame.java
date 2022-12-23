import java.io.File;
import java.util.*;


public class LadderGame {
    ArrayList<ArrayList<String>> arrayListOfArrayLists = new ArrayList<>();
    ArrayList<String> allWords = new ArrayList<>();
    ArrayList<String> wordsOfSameLength = new ArrayList<>();

    public LadderGame(String dictionaryFile) {
        readDictionary(dictionaryFile);
        
    }

    public void play(String start, String end) {
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
                    System.out.println(start + " -> " + end + " : " + wordAtFrontOfQueue.getMoves() + " Moves " + "[" + wordAtFrontOfQueue.getHistory() + " " + wordsToAddToQueue.get(i) + "]" + " total enqueues " + myQueue.getCount());
                    foundSolution = true;
                    break;
                }
            }
        }
        if (!foundSolution) {
            System.out.println(start + " -> " + end + " : No ladder was found");
        }
    }


    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        if (!withRemoval) {
            wordsOfSameLength = (ArrayList<String>)(arrayListOfArrayLists.get(word.length() -1));
        }
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < wordsOfSameLength.size(); i++) {
            String wordToAddToQueue = wordsOfSameLength.get(i);
            if (getNumberOfDifferentLetters(word, wordToAddToQueue) == 1) {
                words.add(wordToAddToQueue);
            }
        }
        for (int j = 0; j < words.size(); j++) {
            if (withRemoval) {
                wordsOfSameLength.remove(words.get(j));
            }
        }
        return words;
    }

    private int getNumberOfDifferentLetters(String word1, String word2) {
        int numberOfDifferentLetters = 0;

        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                numberOfDifferentLetters++;
            }
        }
        return numberOfDifferentLetters;
    }


    public void listWords(int length, int howMany) {
        for (int i = 0; i < howMany; i++) {
            System.out.println(arrayListOfArrayLists.get(length - 1).get(i));
        }
    }

    private void readDictionary(String dictionaryFile) {
        File file = new File(dictionaryFile);

        // Track the longest word, because that tells us how big to make the array.
        int longestWord = 0;
        try (Scanner input = new Scanner(file)) {

            // Start by reading all the words into memory.
            while (input.hasNextLine()) {
                String word = input.nextLine().toLowerCase();
                allWords.add(word);
                longestWord = Math.max(longestWord, word.length());
            }

            for (int i = 0; i < longestWord; i++) {
                arrayListOfArrayLists.add(new ArrayList<>());

            }
            for (int word = 0; word < allWords.size(); word++) {
                int lengthOfWord = (allWords.get(word).length());
                arrayListOfArrayLists.get(lengthOfWord - 1).add(allWords.get(word));
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary: " + ex);
        }
    }
    public boolean checkLengthAndPresenceOfWords(String start, String end) {
        if (start.length() != end.length()) {
            System.out.println("The words are different length");
            return false;
        }
        else if (!wordsOfSameLength.contains(start)) {
            System.out.println(start + " is not in the dictionary");
            return false;
        }
        else if (!wordsOfSameLength.contains(end)) {
            System.out.println(end + " is not in the dictionary");
            return false;
        }
        return true;
    }
}
