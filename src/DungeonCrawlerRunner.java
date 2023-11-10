import java.util.Scanner;

public class DungeonCrawlerRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String playerChoice;
        Dungeon play;



        System.out.print("Choose map (type 1-3 or anything else for random): ");
        playerChoice = s.nextLine();

        if (playerChoice.equals("1") || playerChoice.equals("2") || playerChoice.equals("3")) {
            play = new Dungeon(playerChoice);
        }
        else {
            play = new Dungeon();
        }


        String move;


        while (true) {
            do {
                System.out.print("Choose Direction (n - North, e - East, s - South, w - West): ");
                playerChoice = s.nextLine();
                move = play.movePlayer(playerChoice);
                System.out.println(move);
            } while (move.equals("You walk into a wall"));
            play.runRoom();
        }
    }

    static Player player;
    static EnemyCreator enemy1 = new EnemyCreator();
    static EnemyCreator enemy2 = new EnemyCreator();
    static EnemyCreator enemy3 = new EnemyCreator();

    public static void importPlayer(String choice) {
        player = new Player(choice);
    }
    public static void badRoom1() {
        Scanner s = new Scanner(System.in);
        String choice;
        System.out.println("You enter a room with a " + enemy1.getName());
        while(true) {
            while(true) {
                System.out.println(enemy1.toString());
                System.out.println("-------------------");
                System.out.println(player.toString());
                System.out.println("-------------------");
                player.deckToHand();
                System.out.println(player.displayHand());
                System.out.println("Which attack would you like to do (Choose a number): ");
                choice = s.nextLine();



            }
        }

    }
}
