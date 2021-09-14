package main;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Land {

    public Human player;
    private Goblin goblin;
    boolean death;
    int defeated;
    private String[][] map;

    public Land(){

        map = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}
        };
        player = new Human();
        goblin = new Goblin();
        death = false;
        defeated = 0;
    }

    public void start() {
        map = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " "}
            };
        player = new Human();
        goblin = new Goblin();
        death = false;
        defeated = 0;
    }

    public void playGame() {
        Scanner scan = new Scanner(System.in);
        do {
            updateMap();
            printMap();
            goblinMove();
            boolean temp;
            do {
                temp = playerMove(scan.next());
            }while (!temp);
            processMove();
        }while(!death && defeated < 3);
        printMap();
        System.out.println(defeated == 3 ? "All the Goblins are dead! Huzzah!\n" : "Game Over");
    }

    private void printMap() {
        String countKills = switch (defeated) {
            default -> defeated + "            You have goblins to kill.\n";
            case 1 -> defeated + "            You have 2 more goblins to kill.\n";
            case 2 -> defeated + "            You have 1 more goblins to kill.\n";
            case 3 -> defeated + "            You have won! All goblins are defeated!\n";
        };

        System.out.printf(
                """
                        Defeated: %s
                        +------+------+------+------+------+------+------+------+
                        |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |
                        +------+------+------+------+------+------+------+------+
                        |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |
                        +------+------+------+------+------+------+------+------+
                        |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |
                        +------+------+------+------+------+------+------+------+
                        |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |
                        +------+------+------+------+------+------+------+------+
                        |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |
                        +------+------+------+------+------+------+------+------+
                        |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |
                        +------+------+------+------+------+------+------+------+
                        |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |
                        +------+------+------+------+------+------+------+------+
                        |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |
                        +------+------+------+------+------+------+------+------+
                                          North = N    East  = E
                                          South = S    West =  W
                                Health: %s / %s | Strength: %s  | Defense: %s
                                Goblin: %s / %s | Strength: %s  | Defense: %s
                        %n""",

                countKills,
                map[0][7].equals(" ") ? "  " : map[0][7],
                map[1][7].equals(" ") ? "  " : map[1][7],
                map[2][7].equals(" ") ? "  " : map[2][7],
                map[3][7].equals(" ") ? "  " : map[3][7],
                map[4][7].equals(" ") ? "  " : map[4][7],
                map[5][7].equals(" ") ? "  " : map[5][7],
                map[6][7].equals(" ") ? "  " : map[6][7],
                map[7][7].equals(" ") ? "  " : map[7][7],
                map[0][6].equals(" ") ? "  " : map[0][6],
                map[1][6].equals(" ") ? "  " : map[1][6],
                map[2][6].equals(" ") ? "  " : map[2][6],
                map[3][6].equals(" ") ? "  " : map[3][6],
                map[4][6].equals(" ") ? "  " : map[4][6],
                map[5][6].equals(" ") ? "  " : map[5][6],
                map[6][6].equals(" ") ? "  " : map[6][6],
                map[7][6].equals(" ") ? "  " : map[7][6],
                map[0][5].equals(" ") ? "  " : map[0][5],
                map[1][5].equals(" ") ? "  " : map[1][5],
                map[2][5].equals(" ") ? "  " : map[2][5],
                map[3][5].equals(" ") ? "  " : map[3][5],
                map[4][5].equals(" ") ? "  " : map[4][5],
                map[5][5].equals(" ") ? "  " : map[5][5],
                map[6][5].equals(" ") ? "  " : map[6][5],
                map[7][5].equals(" ") ? "  " : map[7][5],
                map[0][4].equals(" ") ? "  " : map[0][4],
                map[1][4].equals(" ") ? "  " : map[1][4],
                map[2][4].equals(" ") ? "  " : map[2][4],
                map[3][4].equals(" ") ? "  " : map[3][4],
                map[4][4].equals(" ") ? "  " : map[4][4],
                map[5][4].equals(" ") ? "  " : map[5][4],
                map[6][4].equals(" ") ? "  " : map[6][4],
                map[7][4].equals(" ") ? "  " : map[7][4],
                map[0][3].equals(" ") ? "  " : map[0][3],
                map[1][3].equals(" ") ? "  " : map[1][3],
                map[2][3].equals(" ") ? "  " : map[2][3],
                map[3][3].equals(" ") ? "  " : map[3][3],
                map[4][3].equals(" ") ? "  " : map[4][3],
                map[5][3].equals(" ") ? "  " : map[5][3],
                map[6][3].equals(" ") ? "  " : map[6][3],
                map[7][3].equals(" ") ? "  " : map[7][3],
                map[0][2].equals(" ") ? "  " : map[0][2],
                map[1][2].equals(" ") ? "  " : map[1][2],
                map[2][2].equals(" ") ? "  " : map[2][2],
                map[3][2].equals(" ") ? "  " : map[3][2],
                map[4][2].equals(" ") ? "  " : map[4][2],
                map[5][2].equals(" ") ? "  " : map[5][2],
                map[6][2].equals(" ") ? "  " : map[6][2],
                map[7][2].equals(" ") ? "  " : map[7][2],
                map[0][1].equals(" ") ? "  " : map[0][1],
                map[1][1].equals(" ") ? "  " : map[1][1],
                map[2][1].equals(" ") ? "  " : map[2][1],
                map[3][1].equals(" ") ? "  " : map[3][1],
                map[4][1].equals(" ") ? "  " : map[4][1],
                map[5][1].equals(" ") ? "  " : map[5][1],
                map[6][1].equals(" ") ? "  " : map[6][1],
                map[7][1].equals(" ") ? "  " : map[7][1],
                map[0][0].equals(" ") ? "  " : map[0][0],
                map[1][0].equals(" ") ? "  " : map[1][0],
                map[2][0].equals(" ") ? "  " : map[2][0],
                map[3][0].equals(" ") ? "  " : map[3][0],
                map[4][0].equals(" ") ? "  " : map[4][0],
                map[5][0].equals(" ") ? "  " : map[5][0],
                map[6][0].equals(" ") ? "  " : map[6][0],
                map[7][0].equals(" ") ? "  " : map[7][0],

                player.getHealth(),
                player.getMaxHealth(),
                player.getStrength(),
                player.getDefense(),

                goblin.getHealth(),
                goblin.getMaxHealth(),
                goblin.getStrength(),
                goblin.getDefense()
                );
    }

    private void updateMap() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                String playerIcon = "\u265c";
                String goblinIcon = "\u2620";
                if (player.getPosition()[0] == x && player.getPosition()[1] == y) map[x][y] = playerIcon;
                else if (goblin.getPosition()[0] == x && goblin.getPosition()[1] == y) map[x][y] = goblinIcon;
                else map[x][y] = " ";
            }
        }
    }

    boolean startBattle() {
        return Arrays.equals(player.getNewPosition(), goblin.getNewPosition()) ||
                (Arrays.equals(player.getNewPosition(), goblin.getPosition()) &&
                        Arrays.equals(goblin.getNewPosition(), player.getPosition()));
    }

    void processMove() {
        if(startBattle()) {
            combat();
            goblin.setNewPosition(new int[]{goblin.getPosition()[0], goblin.getPosition()[1]});
            player.setNewPosition(new int[]{player.getPosition()[0], player.getPosition()[1]});
        }
            goblin.setPosition(new int[]{goblin.getNewPosition()[0], goblin.getNewPosition()[1]});
            player.setPosition(new int[]{player.getNewPosition()[0], player.getNewPosition()[1]});
    }

    void combat() {
        goblin.loseHealth(player.getStrength());
        player.loseHealth(goblin.getStrength());
        if (player.getHealth() == 0) {
            death = true;
            player.setPosition(new int[]{0, 1});
            return;
        }
        if (goblin.getHealth() == 0) {
            defeated++;
            goblin = new Goblin();
            Random random = new Random();
            int[] temp;
            do {
                temp = new int[]{random.nextInt(8), random.nextInt(8)};
            }
            while ((Math.abs((temp[0] + temp[1]) - (player.getPosition()[0] + player.getPosition()[1])) < 8));
            goblin.setPosition(temp);
        }
    }

    private void makeMove(Combatants combatant, String direction) {
        switch (direction) {
            case "N" -> combatant.getNewPosition()[1] += 1;
            case "E" -> combatant.getNewPosition()[0] += 1;
            case "S" -> combatant.getNewPosition()[1] -= 1;
            case "W" -> combatant.getNewPosition()[0] -= 1;
            default -> System.out.println("You can't move there.");
        }
    }

    boolean playerMove(String input) {
        switch (input.toUpperCase()) {
            case "N":
                if(player.getPosition()[1] == 7) {
                    System.out.println("You can't move North. You are blocked!");
                    return false;
                }
                else {
                    makeMove(player,"N");
                    return true;
                }
            case "S":
                if(player.getPosition()[1] == 0){
                    System.out.println("You can't move South. You are blocked!");
                    return false;
                }
                else {
                    makeMove(player,"S");
                    return true;
                }
            case "E":
                if(player.getPosition()[0] == 7) {
                    System.out.println("You can't move East. You are blocked!");
                    return false;
                }
                else {
                    makeMove(player,"E");
                    return true;
                }
            case "W":
                if(player.getPosition()[0] == 0) {
                    System.out.println("You can't move West. You are blocked!");
                    return false;
                }
                else {
                    makeMove(player,"W");
                    return true;
                }
            default:
                System.out.println("Not a valid input. Try again.");
                return false;
        }
    }

    void goblinMove() {
        if(goblin.getPosition()[1] != player.getPosition()[1]) {
            if(goblin.getPosition()[1] > player.getPosition()[1]) makeMove(goblin, "S");
            else makeMove(goblin, "N");
        }
        else if(goblin.getPosition()[0] != player.getPosition()[0]) {
            if(goblin.getPosition()[0] > player.getPosition()[0]) makeMove(goblin,"W");
            else makeMove(goblin, "E");
        }
    }
}
