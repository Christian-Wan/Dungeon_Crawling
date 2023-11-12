import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Cards card = new Cards("firebolt");
        Scanner s = new Scanner(System.in);
        String choice;

        System.out.print("Make choice: ");
        choice = s.nextLine();
        DungeonCrawlerRunner.importPlayer(choice);

        DungeonCrawlerRunner thing = new DungeonCrawlerRunner();
        thing.badRoom1();
        thing.badRoom1();

    }


}

