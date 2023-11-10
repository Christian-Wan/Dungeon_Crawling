import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Player player = new Player("wizard");
        System.out.println(player.getName());



        Cards card = new Cards("firebolt");
        Scanner s = new Scanner(System.in);
        String choice;

        System.out.print("Make choice: ");
        choice = s.nextLine();
        DungeonCrawlerRunner.importPlayer(choice);
        System.out.println(player.getPlayerDeck());
        System.out.println(player.getPlayerDeckString());
        player.deckToHand();
        System.out.println(player.getPlayerHand());

        DungeonCrawlerRunner thing = new DungeonCrawlerRunner();

        thing.badRoom1();

    }


}

