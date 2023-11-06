import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        HashMap<String, Integer> attackMove = new HashMap<String, Integer>();
        HashMap<String, Integer> defenceMove = new HashMap<String, Integer>();
        HashMap<String, HashMap<String, Integer>> moveList = new HashMap<String, HashMap<String, Integer>>();



        File f = new File("Enemies/enemy1");
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException fException) {
            System.out.println("File not found.");
        }
        String name = s.nextLine();
        int healthPoints = Integer.parseInt(s.nextLine());
        String line = s.nextLine();
        String[] options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            String[] singleMove = options[i].split(" ");
            attackMove.put(singleMove[0], Integer.parseInt(singleMove[1]));
        }
        moveList.put("Attack", attackMove);
        line = s.nextLine();
        options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            String[] singleMove = options[i].split(" ");
            defenceMove.put(singleMove[0], Integer.parseInt(singleMove[1]));
        }
        moveList.put("Defense", defenceMove);

        System.out.println("Name: " + name);
        System.out.println("Health: " + healthPoints);
        System.out.println("Moves: " + moveList);

        }
    }

