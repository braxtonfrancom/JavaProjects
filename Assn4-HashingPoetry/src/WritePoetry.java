import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WritePoetry {

    public String writePoem(String file, String startWord, int length, boolean printHashable) {   //Method uses completed hash table to write a new poem!

        HashTable<String, WordFreqInfo> completeHashTable = readFileAndBuildHashTable(file);
        if (printHashable) {
            System.out.println(completeHashTable.toString(completeHashTable.size()));
        }
        String word = startWord;
        StringBuilder finalPoem = new StringBuilder();

        finalPoem.append(startWord);

        Random randomNumberGenerator = new Random();
        for (int i = 0; i < length - 1; i++) {

            WordFreqInfo wordToWrite = completeHashTable.find(word);
            int numberOfOccurrences = wordToWrite.getOccurCount();
            int randomNumber = randomNumberGenerator.nextInt(numberOfOccurrences);
            String nextWord = wordToWrite.getFollowWord(randomNumber);

            String prevWord = word;
            word = nextWord;

            if (!Character.isAlphabetic(nextWord.charAt(nextWord.length() - 1))) { //If next word is punctuation
                finalPoem.append(nextWord).append("\n");
            } else {
                if (Character.isAlphabetic(prevWord.charAt(prevWord.length() - 1))) {
                    finalPoem.append(" ").append(nextWord);
                } else {
                    finalPoem.append(nextWord);
                }
            }
        }
        if (Character.isAlphabetic(finalPoem.charAt(finalPoem.length() - 1))) {
            finalPoem.append("."); }
        return finalPoem.toString();
    }



    private HashTable<String, WordFreqInfo> readFileAndBuildHashTable(String file) {

        File poemFile = new File(file);
        HashTable<String, WordFreqInfo> allWordsHash = new HashTable<>();
        int count = 0;
        String placeholder = "";
        try (Scanner input = new Scanner(poemFile)) {

            while (input.hasNextLine()) {
                ArrayList<String> words = splitLine(input.nextLine());
                for(int i = 0; i < words.size(); i++) {

                    if (words.get(i).trim().length() > 0) {
                        if (i == 0 && !placeholder.equals("")) {
                            allWordsHash.find(placeholder).updateFollows(words.get(i));
                        }
                        if (!allWordsHash.contains(words.get(i))) {
                            WordFreqInfo newWordObject = new WordFreqInfo(words.get(i), count);
                            allWordsHash.insert(words.get(i), newWordObject);
                        }
                        if (i < words.size() - 1) {
                            allWordsHash.find(words.get(i)).updateFollows(words.get(i + 1));
                        }
                        else {
                            placeholder = words.get(i);
                        }
                    }
                }
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary: " + ex);
        }
        return allWordsHash;
    }



    private ArrayList<String> splitLine(String line) {
        ArrayList<String> finalWordsArray = new ArrayList<String>();

        String[] words = line.split("((?=[.,!?]))|(\s)", 0);
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals("")) {
                words[i] = words[i].toLowerCase();
                finalWordsArray.add(words[i]);
                //System.out.println("a[" + i + "] = " + words[i]);
            }
        }
        return finalWordsArray;
    }

}
