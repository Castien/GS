package main;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Land map = new Land();
        boolean playing = true;
        do{
            map.start();
            map.playGame();
            playAgain(scan, playing);
        }while(playing);
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
                    break;
                } else {
                    throw new NoSuchElementException();
                }
            } catch (NoSuchElementException ns) {
                System.out.println("Must choose 'y' or 'n'.");
            }
        } while (playing);
        return playing;
    }
}
