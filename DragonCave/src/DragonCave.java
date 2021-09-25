import java.util.Scanner;

public class DragonCave {
    public static void main(String[] args) {
        System.out.println("You are in a land full of dragons. In front of you, you see two caves. \n" +
                        "In one cave, the dragon is friendly and will share his treasure with you. \n" +
                        "The other dragon is greedy and hungry and will eat you on sight. \n" +
                        "Which cave will you go into? (1 or 2)");

        Scanner scan = new Scanner(System.in);
        try {
            int i = scan.nextInt();

            if (i == 1) {
                System.out.println("You approach the cave... \nIt is dark and spooky... \n" +
                        "A large dragon jumps out in front of you! \nHe opens his jaws and...\n" +
                        "Gobbles you down in one bite!");
            } else {
                System.out.println("You approach the cave... \nIt is dark and spooky... \n" +
                        "A large dragon jumps out in front of you! \nRumbling a great laugh, he looks upon you and\n" +
                        "Showers you with jewels and golden treasure!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
