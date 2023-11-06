import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class EnemyCreator {

    String name;
    int healthPoints;
    HashMap<String, Integer> attackMove = new HashMap<String, Integer>();
    HashMap<String, Integer> defenceMove = new HashMap<String, Integer>();
    HashMap<String, HashMap<String, Integer>> moveList = new HashMap<String, HashMap<String, Integer>>();

    public EnemyCreator() {
        File f = new File("Enemies/enemy" + Math.random());
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException fException) {
            System.out.println("File not found.");
        }
        this.name = s.nextLine();
        this.healthPoints = Integer.parseInt(s.nextLine());
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
    }

    public void changeHealth(int damage) {
        healthPoints -= damage;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public void attack() {
        int randomAttack = (int) (Math.random() * 4) + 1;
        if (randomAttack == 1) {

        }
        else if (randomAttack == 2) {

        }
        else if (randomAttack == 3) {

        }
        else {

        }
    }
}
