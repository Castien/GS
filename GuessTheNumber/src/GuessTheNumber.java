import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random randomnum = new Random();

        boolean playing = true;

        System.out.println("Hello! What is your name?");
        String name = scan.nextLine();

        while (playing) {
            int answer = randomnum.nextInt(20);
            int guess = 0;
            int tries = 0;

            System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20.");

            while (tries < 6) {
                System.out.println("Take a guess.");
                try {
                    guess = scan.nextInt();
                    tries++;

                    if(guess < 1 || guess > 20) {
                        throw new InputMismatchException();
                    }
                    else {
                        if (guess < answer) {
                            System.out.println("Your guess is too low.");
                        }
                        if (guess > answer) {
                            System.out.println("Your guess is too high");
                        }
                        if (guess == answer) {
                            break;
                        }
                    }
                }
                catch (InputMismatchException mm) {
                    System.out.println("Not a valid number.");
                }
            }
            if(guess > 0) {
                if (guess == answer) {
                    System.out.println("Good job, " + name + "! You guessed my number in " + tries + " guesses!");
                } else {
                    System.out.println("Sorry. The number was " + answer + ".");
                }
            }

            scan.nextLine();
            System.out.println("Would you like to play again? (Enter 'y' or 'n':)");
            playAgain(scan, playing);
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