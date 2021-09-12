import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Random randomnum = new Random();

        boolean playing = true;

        System.out.println("Hello! What is your name?");
        String name = scan.nextLine();

        while(playing) {
            int answer = randomnum.nextInt(20);
            int guess = 0;
            int tries = 0;
            System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20.");

            while (tries < 6) {
                System.out.println("Take a guess.");
                guess = scan.nextInt();
                tries++;

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

            if(guess > 0) {
                if (guess == answer) {
                    System.out.println("Good job, " + name + "! You guessed my number in " + tries + " guesses!");
                } else {
                    System.out.println("Sorry. The number was " + answer + ".");
                }
            }

            System.out.println("Would you like to play again? (Enter 'y' or 'n':)");
            scan.nextLine();
            String restart = scan.nextLine();

            if(!restart.equalsIgnoreCase("y")) {
                if(restart.equalsIgnoreCase("n")) {
                    System.out.println("Thanks for playing!");
                    playing = false;
                }
            }
        }
    }
}
