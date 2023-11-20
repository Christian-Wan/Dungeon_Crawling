import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
/*
* The Dungeon class represents the map that the player walks through. It uses a 2D array and places the player on a coordinate
* on that 2D array. The array has numbers that represent the type of room that room is
* */
public class Dungeon {

    int playerRow = 4;
    int playerColumn = 4;
    int[][] dungeonMap = new int[6][6];
    /*
    * constructor for the dungeon class that takes the parameter to look for a file to scan and put inside of instance variable dungeonMap
    *
    * @param chooser represents the users choice of map
    * */
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
        s.close();
    }
    /*
     * constructor for the dungeon class that looks for a file to scan and put inside of instance variable dungeonMap
     * this constructor randomly chooses out of the three possible maps instead of using user input
     * */
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
        s.close();
    }
    /*
    * checks if the player can move north
    *
    * @returns a boolean that represents if the player can move north or not
    * */
    public boolean moveNorth() {
        boolean check = true;
        if (dungeonMap[playerRow - 1][playerColumn] == 0) {
            check = false;
        }
        return check;
    }
    /*
     * checks if the player can move east
     *
     * @returns a boolean that represents if the player can move east or not
     * */
    public boolean moveEast() {
        boolean check = true;
        if (dungeonMap[playerRow][playerColumn + 1] == 0) {
            check = false;
        }
        return check;
    }
    /*
     * checks if the player can move south
     *
     * @returns a boolean that represents if the player can move south or not
     * */
    public boolean moveSouth() {
        boolean check = true;
        if (dungeonMap[playerRow + 1][playerColumn] == 0) {
            check = false;
        }
        return check;
    }
    /*
     * checks if the player can move west
     *
     * @returns a boolean that represents if the player can move west or not
     * */
    public boolean moveWest() {
        boolean check = true;
        if (dungeonMap[playerRow][playerColumn - 1] == 0) {
            check = false;
        }
        return check;
    }
    /*
    * checks if player can move to a desired direction and if they can then move them and set their previous room as seen
    * also returning a string indicating where the player went
    *
    * @param movement represents the users choice in direction
    *
    * @return a sentence that describes what happens to the player after the player chooses a direction*/
    public String movePlayer(String movement) {
        movement = movement.toLowerCase();
        String[] allow = {"n", "e", "s", "w"};
        if (!InputValidation.stringValidate(allow, movement)) {
            return "That is not an option";
        }

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

    /*
    * checks what type of room the player is currently in and returns a string accordingly
    *
    * @returns a string that describes the room the player is currently in*/
    public String runRoom() {
        if (dungeonMap[playerRow][playerColumn] == 2) {
            return "Bad";
        }
        else if (dungeonMap[playerRow][playerColumn] == 3) {
            return "Good";
        }
        else if (dungeonMap[playerRow][playerColumn] == 4) {
            return "Boss";
        }
        else {
            return "Been";
        }
    }





}
