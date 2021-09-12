import java.util.Scanner;

public class Hangman {

    private static String[] words = {"cat", "dog", "hat", "bat", "dry", "lol", "hog"}; //list of words
    private static String word = words[(int) (Math.random() * words.length)];
    //selects random word from words array
    private static String space = new String(new char[word.length()]).replace("\0", "_");
    //take random word and replaces chars with spaces, assigns blanks to new variable 'space'
    private static int count = 0; // initializing the count for the guesses
    private static String missed = ""; //initializing variable for incorrect guesses

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        while (playing) {
            System.out.println("H A N G M A N");
            System.out.println("+---+");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("   ===");
            System.out.println("Missed letters:");

            //while the variable space contains spaces ('_') it will ask the player to pick a letter,
            // stores the input in 'guess'
            while (space.contains("_")) {
                System.out.println(space);
                System.out.println("Guess a letter.");
                String guess = scanner.next();

                //passes user input to gallows and missedLetter methods
                gallows(guess);
                missedLetter(guess);
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
            count++;
            hangman();
        } else {
            space = spaceNew;
        }
        if (space.equals(word)) {
            System.out.println("Yes! The secret word is " + word + "! You have won!");
        }
    }
        boolean playing = true;

    public static String missedLetter(String guess) {
        if (!word.contains(guess)) {
            missed += guess;
        } else if(missed.contains(guess)) {
            System.out.println("You have already guessed that letter. Choose again.");
        }
        return missed;
    }

    public static void hangman() {
        if (count == 1) {
            System.out.println("+---+");
            System.out.println("O   |");
            System.out.println("    |");
            System.out.println("    |");
            System.out.println("   ===");
            System.out.println("Missed letters:" + missed);
        }
        if (count == 2) {
            System.out.println("+---+");
            System.out.println("O   |");
            System.out.println("|   |");
            System.out.println("    |");
            System.out.println("   ===");
            System.out.println("Missed letters:" + missed);
        }
        if (count == 3) {
            System.out.println("+---+");
            System.out.println("O   |");
            System.out.println("|   |");
            System.out.println("|   |");
            System.out.println("   ===");
            System.out.println("Missed letters:" + missed);
        }
    }
}
