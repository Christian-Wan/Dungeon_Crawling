import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Dungeon {

    int playerRow = 4;
    int playerColumn = 4;
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
        if (dungeonMap[playerRow - 1][playerColumn] == 0) {
            check = false;
        }
        return check;
    }
    public boolean moveEast() {
        boolean check = true;
        if (dungeonMap[playerRow][playerColumn + 1] == 0) {
            check = false;
        }
        return check;
    }
    public boolean moveSouth() {
        boolean check = true;
        if (dungeonMap[playerRow + 1][playerColumn] == 0) {
            check = false;
        }
        return check;
    }
    public boolean moveWest() {
        boolean check = true;
        if (dungeonMap[playerRow][playerColumn - 1] == 0) {
            check = false;
        }
        return check;
    }

    public String movePlayer(String movement) {

        if (movement.equals("n") && moveNorth()) {
            dungeonMap[playerRow][playerColumn] = 5;
            playerRow--;
            return "You moved north";
        }
        else if (movement.equals("e") && moveEast()) {
            dungeonMap[playerRow][playerColumn] = 5;
            playerColumn++;
            return "You moved east";
        }
        else if (movement.equals("s") && moveSouth()) {
            dungeonMap[playerRow][playerColumn] = 5;
            playerRow++;
            return "You moved south";
        }
        else if (movement.equals("w") && moveWest()) {
            dungeonMap[playerRow][playerColumn] = 5;
            playerColumn--;
            return "You moved west";
        }

        return "You walk into a wall";
    }


    public void runRoom() {
        if (dungeonMap[playerRow][playerColumn] == 2) {
            System.out.println("Bad");
        }
        else if (dungeonMap[playerRow][playerColumn] == 3) {
            System.out.println("Good");
        }
        else if (dungeonMap[playerRow][playerColumn] == 4) {
            System.out.println("Boss");
        }
        else {
            System.out.println("Been");
        }
    }



}
