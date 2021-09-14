package main;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Land map = new Land();
        boolean gameOver = false;
        do{
            map.start();
            map.playGame();

            boolean playing;

            do{
                System.out.println("Would you like to play again? (Y or N)");
                switch (scan.next().toUpperCase()) {
                    case "Y":
                        playing = true;
                        gameOver = false;
                        break;
                    case "N":
                        playing = true;
                        gameOver = true;
                        break;
                    default:
                        System.out.println("That's not a valid input, try again.");
                        playing = false;
                }
            }while(!playing);
        }while(!gameOver);
    }
}
