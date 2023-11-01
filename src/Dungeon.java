import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dungeon {

    int[][] dungeonMap = new int[6][6];
    public Dungeon(String chooser) {
        File f = new File("maps/map" + chooser);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException fException) {
            System.out.println("File not found.");
        }
        int row = 0;
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] rooms = line.split(" ");
            for (int i = 0; i < rooms.length; i++) {
                dungeonMap[row][i] = Integer.parseInt(rooms[i]);
            }
            row++;
        }
    }
    public Dungeon() {
        int chooser = (int) (Math.random() * 3) + 1;

        File f = new File("maps/map" + chooser);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException fException) {
            System.out.println("File not found.");
        }
        int row = 0;
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] rooms = line.split(" ");
            for (int i = 0; i < rooms.length; i++) {
                dungeonMap[row][i] = Integer.parseInt(rooms[i]);
            }
            row++;
        }
    }

    public boolean moveNorth() {
        boolean check = true;
        if (dungeonMap[][])
        return false;
    }
    public boolean moveEast() {
        return false;
    }
    public boolean moveSouth() {
        return false;
    }
    public boolean moveWest() {
        return false;
    }



}
