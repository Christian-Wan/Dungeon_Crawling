import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EnemyCreator {

    private String name;
    private int healthPoints;
    private HashMap<String, Integer> attackMove = new HashMap<String, Integer>();
    private HashMap<String, Integer> defenseMove = new HashMap<String, Integer>();
    private HashMap<String, HashMap<String, Integer>> moveList = new HashMap<String, HashMap<String, Integer>>();
    private HashMap<String, Integer> currentAttack = new HashMap<String, Integer>();

    public EnemyCreator() {
        File f = new File("enemies/enemy1");
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
            defenseMove.put(singleMove[0], Integer.parseInt(singleMove[1]));
        }
        moveList.put("Defense", defenseMove);
    }

    public void changeHealth(int damage) {

        healthPoints -= damage;
    }
    public int getHealthPoints() {

        return healthPoints;
    }

    public HashMap<String, Integer> getCurrentAttack() {
        return currentAttack;
    }
    public String getName() {
        return name;
    }

    public void getAttack() {
        String temp = "";
        int tempInt = 0;
        HashMap<String, Integer> tempMap = new HashMap<String, Integer>();
        int randomAttack = (int) (Math.random() * 3) + 1;
        if (randomAttack == 1) {
            int random = (int) (Math.random() * defenseMove.size());
            for (Map.Entry<String, Integer> entry : defenseMove.entrySet()) {
                temp = entry.getKey();
                tempInt  = entry.getValue();
                random--;
                if (random == 0) {
                    break;
                }
            }
            tempMap.put(temp, tempInt);
        }
        else {
            int random = (int) (Math.random() * attackMove.size());
            for (Map.Entry<String, Integer> entry : attackMove.entrySet()) {
                temp = entry.getKey();
                tempInt = entry.getValue();
                random--;
                if (random == 0) {
                    break;

                }
            }
            tempMap.put(temp, tempInt);
        }
        currentAttack = tempMap;
    }

    public String enemyAttackPrint(HashMap<String, Integer> move) {
        String attackName = "";
        int damage = 0;
        for (Map.Entry<String, Integer> entry : move.entrySet()) {
            attackName = entry.getKey();
            damage = entry.getValue();
        }
        String statement = "";

        return "Enemy " + name + " " + attackName + " you for " + damage + " damage!";
    }
}
