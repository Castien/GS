import java.util.*;

public class Hangman {

    static String start = (" H A N G M A N\n");
    static String beam = (" +---+\n");
    static String blank = "     |\n";
    static String head = " O   |\n";
    static String chest = " |   |\n";
    static String butt = " |   |\n";
    static String leg1 = "/    |\n";
    static String leg2 = "/ \\  |\n";
    static String arm1 = "/|   |\n";
    static String arm2 = "/|\\  |\n";
    static String plat = ("    ===\n");

    static String wrong0 = beam + blank + blank + blank + blank + plat;
    static String wrong1 = beam + head + blank + blank + blank + plat;
    static String wrong2 = beam + head + chest + blank + blank + plat;
    static String wrong3 = beam + head + chest + butt + blank + plat;
    static String wrong4 = beam + head + arm1 + butt + blank + plat;
    static String wrong5 = beam + head + arm2 + butt + blank + plat;
    static String wrong6 = beam + head + arm2 + butt + leg1 + plat;
    static String wrong7 = beam + head + arm2 + butt + leg2 + plat;

    static String[] words = new String[]{"cat", "dog", "hat", "bat", "dry", "lol", "hog", "bear", "bugs", "gator", "loser", "wizard", "giraffe"};

    static Scanner scan = new Scanner(System.in);
    static Random random = new Random();

    List<String> miss = new ArrayList<String>();

    static String[] answer = new String[]{};
    static String[] guess = new String[]{};

    static Set<String> missedLetters = new HashSet<>();

    static int wrongCount = 0;
    static boolean correct = false;
    static boolean playing = true;


    public static void main(String[] args) {
        // write your code here
        playHangman();
    }

    public static void playHangman() {

        do {
            startHangman();
            while (!correct && wrongCount < 7) {
                System.out.println("\n" + start);
                printGallows();
                System.out.println("Guess a letter.");

                try {
                    String letter = scan.next();
                    if (!isAlpha(letter)) {
                        throw new InputMismatchException();
                    } else {
                        if (!checkLetter(letter)) {
                            throw new InputMismatchException();
                        }
                        checkAnswer();
                        }
                }catch (InputMismatchException v) {
                    System.out.println("Must be a letter from a to z.");
                }
            }
            printGallows();
            if (!correct) System.out.println("Sorry, you're all out of tries!");
            else
                System.out.println(String.format("Yes! The secret word is \"%s\"! You have won!", collectionToString(Arrays.asList(guess))));

            scan.nextLine();
            System.out.println("Would you like to play again? (Enter 'y' or 'n':)");
            playAgain(scan, playing);
        } while (playing);
    }

    public static void startHangman() {
        answer = words[random.nextInt(10)].split("");
        guess = new String[answer.length];

        for (int i = 0; i < guess.length; i++) {
            guess[i] = "_";
        }

        wrongCount = 0;
        missedLetters = new HashSet<String>(0);
        correct = false;
    }

    public static void printGallows() {
        switch (wrongCount) {
            case 0:
                System.out.println(wrong0);
                break;
            case 1:
                System.out.println(wrong1);
                break;
            case 2:
                System.out.println(wrong2);
                break;
            case 3:
                System.out.println(wrong3);
                break;
            case 4:
                System.out.println(wrong4);
                break;
            case 5:
                System.out.println(wrong5);
                break;
            case 6:
                System.out.println(wrong6);
                break;
            case 7:
                System.out.println(wrong7);
                break;
            default:
                System.out.println(wrongCount);
                break;
        }
        System.out.println("Missed letters: " + collectionToString(missedLetters));
        System.out.println(collectionToString(Arrays.asList(guess)));
    }

    public static String collectionToString(Collection<String> collection) {
        String result = "";
        for (String s : collection) {
            result += s;
        }
        return result;
    }

    public static boolean isAlpha(String letter) {
        char[] chars = letter.toCharArray();
        boolean check = false;
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return check;
            }
            else {
                check = true;
            }
        }
        return check;
    }

    public static boolean checkLetter(String letter) {
        boolean isPresent = false;

        for (int i = 0; i < answer.length; i++) {
            if (letter.equals(answer[i])) {
                guess[i] = letter;
                isPresent = true;
            }
        }
        if (!isPresent) {
            missedLetters.add(letter);
            wrongCount++;
        }
        return isPresent;
    }

    public static void checkAnswer() {
        correct = true;
        for (int i = 0; i < answer.length; i++) {
            if (!guess[i].equals(answer[i])) correct = false;
        }
    }

    private static boolean playAgain(Scanner scan, boolean playing) {
        do {
            try {
                String restart = scan.nextLine();
                if (restart.equalsIgnoreCase("y")) {
                    break;
                } else if (restart.equalsIgnoreCase("n")) {
                    System.out.println("Thanks for playing!");
                    playing = false;
                } else {
                    throw new NoSuchElementException();
                }
            } catch (NoSuchElementException ns) {
                System.out.println("Must choose 'y' or 'n'.");
            }
        } while (true);
        return playing;
    }
}

