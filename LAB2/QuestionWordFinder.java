package LAB2;

import java.util.*;

/** The QuestionWordFinder class contains functions required in {@link LAB2}*/
public class QuestionWordFinder {
    /** Text, which may contain interrogative sentences */
    private final StringBuffer text;


    /** Constructor creates a new word finder object for
     * interrogative sentences out of text
     *
     * @param text the input text
     */
    public QuestionWordFinder(StringBuffer text) {
        this.text = text;
    }

    /**
     * Extracts interrogative sentences out of given text.
     * <p>It functions by iterating through text, adding characters to a sentence</p>
     * <p>If the end of sentence contains question mark, it gets added to a resulting list</p>
     * @return the list of interrogative sentences
     */
    private ArrayList<StringBuffer> extractInterrogativeSentences() {
        ArrayList<StringBuffer> sentences = new ArrayList<>();
        StringBuffer sentence = new StringBuffer();

        // The function can't use .split() or .substring(), because it needs/returns String object
        // So the function iterates through text and creates them character by character
        boolean isEndFound = false;
        boolean isQuestionMarkFound = false;

        for (int i = 0; i < text.length(); i++) {
            Character ch = text.charAt(i);

            if (!(ch == '.' || ch == '!' || ch == '?')) {
                if (isQuestionMarkFound) {
                    isQuestionMarkFound = false;
                    sentences.add(sentence);
                }
                if (isEndFound) {
                    isEndFound = false;
                    sentence = new StringBuffer();
                }
            } else {
                isEndFound = true;
                if (ch == '?') {
                    isQuestionMarkFound = true;
                }
            }

            sentence.append(ch);
        }

        if (isQuestionMarkFound) {
            sentences.add(sentence);
        }

        return sentences;
    }

    /**
     * Extracts unique words from interrogative sentences.
     *
     * @return a set of unique words
     */
    private Set<String> extractWords() {
        ArrayList<StringBuffer> sentences = extractInterrogativeSentences();
        Set<String> words = new HashSet<>();
        StringBuffer word = new StringBuffer();

        // Because we already have no other things to do with
        // words, the program can turn StringBuffer to String
        // without violating the terms of the task
        for (StringBuffer sentence: sentences) {
            for (int i = 0; i < sentence.length(); i++) {
                Character ch = sentence.charAt(i);

                if (Character.isLetter(ch)) {
                    word.append(ch);
                } else {
                    if (!word.isEmpty()) {
                        words.add(word.toString().toLowerCase());
                    }
                    word = new StringBuffer();
                }
            }
        }
        words.add(word.toString().toLowerCase());
        return words;
    }

    /**
     * Prints the unique words of the given length from interrogative sentences.
     *
     * @param wordLength the length of words needed to be printed
     */
    public void printWords(int wordLength) {
        Set<String> words = extractWords();

        // First remove the words of length other than needed
        words.removeIf(word -> word.length() != wordLength);

        // Then, output them if there are words left
        if (words.isEmpty()) {
            System.out.println("No words of length " + wordLength + " found in interrogative sentences.");
            return;
        }
        System.out.println("Unique words of length " + wordLength + ": ");
        for (String word: words) {
            System.out.println(word);
        }
    }
}