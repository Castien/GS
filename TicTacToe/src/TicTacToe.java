import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {


    public static void main(String[] args) {

        boolean playing = true;

        do {
            Random random = new Random();
            Scanner scan = new Scanner(System.in);

            char letterCP = 0;
            int first = 0;
            first = random.nextInt(2);

            System.out.println("Welcome to Tic-Tac-Toe!");

            char[][] board = {{' ', ' ', ' '},
                    {' ', ' ', ' '},
                    {' ', ' ', ' '}
            };

            getBoard(board);

            char letterHP = letterCheck(scan);

            if (letterHP == 'X') {
                System.out.println("You are X's.");
                letterCP = 'O';
            } else if (letterHP == 'O') {
                System.out.println("You are the O's.");
                letterCP = 'X';
            }


            if (first < 1) {
                System.out.println("The computer will go first.");
                while (true) {
                    System.out.println("--------------");
                    System.out.println("Computer's Move");
                    computer(board, letterCP);
                    if (gameOver(board)) {
                        break;
                    }
                    getBoard(board);
                    System.out.println("--------------");
                    System.out.println("Your Move");
                    player(board, scan, letterHP);
                    if (gameOver(board)) {
                        break;
                    }
                    getBoard(board);
                }
            } else {
                System.out.println("You will go first.");
                while (true) {
                    System.out.println("--------------");
                    System.out.println("Your Move");
                    player(board, scan, letterHP);
                    if (gameOver(board)) {
                        break;
                    }
                    getBoard(board);
                    System.out.println("--------------");
                    System.out.println("Computer's Move");
                    computer(board, letterCP);
                    if (gameOver(board)) {
                        break;
                    }
                    getBoard(board);
                }
            }

            scan.nextLine();
            System.out.println("Would you like to play again? (Enter 'y' or 'n':)");
            playAgain(scan, playing);
        }
        while (playing);
    }

    private static char letterCheck(Scanner scan) {
        char letterHP = 0;
        boolean invalidLetter = true;
        do {
            try {
                System.out.println("Do you want to be X or O?");
                letterHP = scan.next().charAt(0);
                letterHP -= 32;

                if (!(letterHP == 'X' || letterHP == 'O')) {
                    throw new InputMismatchException();
                }
                else {
                    invalidLetter = false;
                }
            } catch (InputMismatchException l) {
                System.out.println("Please enter 'X' or 'O':");
            }
        }while (invalidLetter);
       return letterHP;
    }


    private static void player(char[][] board, Scanner scan, char letter) {
        int userInput = 10;
        while (true) {
            System.out.println("What is your next move? (1-9)");
            try {
                userInput = scan.nextInt();
                if (isValid(board, userInput)) {
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException p) {
                System.out.println(userInput + " is not a valid move.");
            }
        }
        placeLetter(board, userInput, letter);
    }

    private static void computer(char[][] board, char letter) {
        Random rand = new Random();
        int compMove;
        while (true) {
            compMove = rand.nextInt(9) + 1;
            if (isValid(board, compMove)) {
                break;
            }
        }
        placeLetter(board, compMove, letter);
    }

            private static boolean isValid (char[][] board, int position) {
            switch(position) {
                case 1:
                    return (board[0][0] == ' ');
                case 2:
                    return (board[0][1] == ' ');
                case 3:
                    return (board[0][2] == ' ');
                case 4:
                    return (board[1][0] == ' ');
                case 5:
                    return (board[1][1] == ' ');
                case 6:
                    return (board[1][2] == ' ');
                case 7:
                    return (board[2][0] == ' ');
                case 8:
                    return (board[2][1] == ' ');
                case 9:
                    return (board[2][2] == ' ');
                default:
                    return false;
            }
        }

    private static boolean playerWon(char[][] board, char letter) {
        if((board[0][0] == letter && board[0][1] == letter && board[0][2] == letter) ||
                (board[1][0] == letter && board[1][1] == letter && board[1][2] == letter) ||
                (board[2][0] == letter && board[2][1] == letter && board[2][2] == letter) ||
                (board[0][0] == letter && board[1][0] == letter && board[2][0] == letter) ||
                (board[0][1] == letter && board[1][1] == letter && board[2][1] == letter) ||
                (board[0][2] == letter && board[1][2] == letter && board[2][2] == letter) ||
                (board[0][0] == letter && board[1][1] == letter && board[2][2] == letter) ||
                (board[0][2] == letter && board[1][1] == letter && board[2][0] == letter)) {
            return true;
        }
        return false;
    }

            private static void placeLetter(char[][] board, int position, char letter) {
            switch(position) {
                case 1:
                    board[0][0] = letter;
                    break;
                case 2:
                    board[0][1] = letter;
                    break;
                case 3:
                    board[0][2] = letter;
                    break;
                case 4:
                    board[1][0] = letter;
                    break;
                case 5:
                    board[1][1] = letter;
                    break;
                case 6:
                    board[1][2] = letter;
                    break;
                case 7:
                    board[2][0] = letter;
                    break;
                case 8:
                    board[2][1] = letter;
                    break;
                case 9:
                    board[2][2] = letter;
                    break;
                default:
                    System.out.println("Invalid!");
            }
        }

    private static boolean gameOver(char[][] board) {
        if (playerWon(board, 'X')) {
            getBoard(board);
            System.out.println("X wins!");
            return true;
        }

        if (playerWon(board, 'O')) {
            getBoard(board);
            System.out.println("O wins!");
            return true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }

        getBoard(board);
        System.out.println("The game ended in a tie!");
        return true;
    }

    public static void getBoard(char[][] board) {
            System.out.println(board[0][0] + "|" +  board[0][1] + "|" +  board[0][2] );
            System.out.println("-+-+-");
            System.out.println(board[1][0] + "|" +  board[1][1] + "|" +  board[1][2] );
            System.out.println("-+-+-");
            System.out.println(board[2][0] + "|" +  board[2][1] + "|" +  board[2][2] );
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
