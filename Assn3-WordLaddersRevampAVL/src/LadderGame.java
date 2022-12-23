import java.io.File;
import java.util.*;


public abstract class LadderGame {
    ArrayList<ArrayList<String>> arrayListOfArrayLists = new ArrayList<>();
    ArrayList<String> allWords = new ArrayList<>();
    ArrayList<String> wordsOfSameLength = new ArrayList<>();

    public LadderGame(String dictionaryFile) {
        readDictionary(dictionaryFile);
    }

    public abstract void play(String start, String end); //will be implemented in each child class


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

    public int getNumberOfDifferentLetters(String word1, String word2) {
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

        int longestWord = 0;            // Track the longest word, because that tells us how big to make the array.
        try (Scanner input = new Scanner(file)) {

            while (input.hasNextLine()) {  // Start by reading all the words into memory.
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
