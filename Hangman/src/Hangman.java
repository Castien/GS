import java.util.Scanner;

public class Hangman {

    static String start = (" H A N G M A N\n");
    static String beam = (" +---+\n");
    static String blank = "     |\n";
    static String head = " +---+\n" + " O   |\n";
    static String neck = " |   |\n";
    static String foot = " |   |\n";
    static String plat = ("    ===\n");

    static String wrong0 = beam + blank + blank + blank + plat;
    static String wrong1 = head + blank + blank + plat;
    static String wrong2 = head + neck + blank + plat;
    static String wrong3 = head + neck + foot + plat;

    static String[] wrongs = {wrong0, wrong1, wrong2, wrong3};
    static int wrongIndex = 0;

    private static String[] words = {"cat", "dog", "hat", "bat", "dry", "lol", "hog"};
    private static String word = words[(int) (Math.random() * words.length)];
    private static String space = new String(new char[word.length()]).replace("\0", "_");

    private static String missed = "";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        System.out.println(start + wrongs[wrongIndex]);

        while (playing) {

            //while the variable space contains spaces ('_') it will ask the player to pick a letter,
            // stores the input in 'guess'
            while (space.contains("_")) {
                System.out.println("Missed letters:\n" + missed);
                System.out.println(space);
                System.out.println("Guess a letter.");
                String guess = scanner.next().toLowerCase();
                String usedLetters = "";
                usedLetters += guess;

                //passes user input to gallows and missedLetter methods
                gallows(guess);
                missedLetter(guess, usedLetters);
            }
            scanner.close();
        }
    } //end of main method

    //gallows takes the user input from main method
    public static void gallows(String guess) {
        //initializing an empty String
        String spaceNew = "";
        //increments the index count until i is equal to the number of letters in the random word
        for (int i = 0; i < word.length(); i++) {
            //if the random word's character at the index is the same as the letter guessed,
            //it adds it to the temporary variable spaceNew at the matching index
            //if space's data at index i is not a '_' it will add
            if (word.charAt(i) == guess.charAt(0)) {
                spaceNew += guess.charAt(0);
            } else if (space.charAt(i) != '_') {
                spaceNew += word.charAt(i);
            } else {
                spaceNew += "_";
            }
        }

        if (space.equals(spaceNew)) {
            hangman();
        } else {
            space = spaceNew;
        }
        if (space.equals(word)) {
            System.out.println("Yes! The secret word is " + word + "! You have won!");
        }
    }

    public static String missedLetter(String guess, String usedLetters) {
        if (usedLetters.contains(guess)) {
            System.out.println("You have already guessed that letter. Choose again.");
        }
        if (!word.contains(guess) && !missed.contains(guess)) {
            missed += guess;
            wrongIndex++;
        }
        if (wrongIndex == 4) {
            System.out.println("Sorry! The secret word was " + word + "! You have lost...");
        }
        return missed;
    }

    public static void hangman() {
        int count = 0;
        if (wrongIndex <= 3) {
            System.out.println(wrongs[wrongIndex]);
        }
    }
}

