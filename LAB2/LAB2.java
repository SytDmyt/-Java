package LAB2;

import java.util.Scanner;

/**
 * LAB2 class performs string task, which is described in Lab work #2
 *
 * <p>Firstly, it takes text with several sentences and an integer value
 * for the length of required words using string args or via console input</p>
 * <p>Secondly, it finds the interrogative sentences out of the text based on the question marks</p>
 * <p>Thirdly, it splits the sentence into the list of unique words</p>
 * <p>And finally, it output the result</p>
 */
public class LAB2 {
    public static void main(String[] args) {
        StringBuffer text;
        int wordLength;

        // Attempt to get text and desire word length via command-line arguments
        // If failed, another attempt to get them through console input
        if (args.length >= 3) {
            Object[] parsed = parseArgs(args);
            if (parsed == null) return;
            text = (StringBuffer) parsed[0];
            wordLength = (int) parsed[1];
        } else {
            Object[] fromConsole = readFromConsole();
            if (fromConsole == null) return;
            text = (StringBuffer) fromConsole[0];
            wordLength = (int) fromConsole[1];
        }

        if (wordLength <= 0) {
            System.out.println("Word length must be greater than 0.");
            return;
        }

        QuestionWordFinder finder = new QuestionWordFinder(text);
        finder.printWords(wordLength);
    }

    /**
     * Attempts to parse a String and a positive integer from command-line arguments.
     *
     * @param args array of command-line arguments
     * @return an array [text, wordLength] if parsing succeeds, or null if invalid
     */
    private static Object[] parseArgs(String[] args) {
        try {
            String text = args[0];
            int length = Integer.parseInt(args[1]);
            return new Object[]{new StringBuffer(text), length};
        } catch (NumberFormatException e) {
            System.out.println("Arguments must be: text int");
            return null;
        }
    }

    /**
     * Gets text and wordLength via console.
     *
     * @return an array [text, wordLength] if parsing succeeds, or null if invalid
     */
    private static Object[] readFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        System.out.print("Enter word length: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Word length must be a positive integer.");
            return null;
        }
        int length = scanner.nextInt();

        return new Object[]{new StringBuffer(text), length};
    }
}
