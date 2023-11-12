import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class EnemyCreator {

    private String name;
    private int healthPoints, maxHealthPoints;
    private HashMap<String, Integer> attackMove = new HashMap<String, Integer>();
    private HashMap<String, Integer> defenseMove = new HashMap<String, Integer>();
    private HashMap<String, HashMap<String, Integer>> moveList = new HashMap<String, HashMap<String, Integer>>();
    private HashMap<String, Integer> currentAttack = new HashMap<String, Integer>();
    //figure out a way to re-randomize the enemy type
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
        this.maxHealthPoints = Integer.parseInt(s.nextLine());
        this.healthPoints = maxHealthPoints;
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
        s.close();
    }

    public EnemyCreator(String check) {
        File f = new File("bosses/boss1");
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException fException) {
            System.out.println("File not found.");
        }
        this.name = s.nextLine();
        this.maxHealthPoints = Integer.parseInt(s.nextLine());
        this.healthPoints = maxHealthPoints;
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
        s.close();
    }
    public void changeHealth(int damage) {

        healthPoints -= damage;
    }
    public int getHealthPoints() {

        return healthPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public HashMap<String, Integer> getCurrentAttack() {
        return currentAttack;
    }
    public String getName() {
        return name;
    }

    public void getAttack() {
        String temp = "";
        HashMap<String, Integer> tempMap = new HashMap<String, Integer>();
        int randomAttack = (int) (Math.random() * 3) + 1;

        if (randomAttack == 1) {
            int random = (int) (Math.random() * defenseMove.size());
            Object[] defenseArray = defenseMove.keySet().toArray();
            temp = defenseArray[random].toString();
            tempMap.put(temp, defenseMove.get(temp));
        }

        else {
            int random = (int) (Math.random() * attackMove.size());
            Object[] attackArray = attackMove.keySet().toArray();
            temp = attackArray[random].toString();
            tempMap.put(temp, attackMove.get(temp));
        }
        currentAttack = tempMap;
    }

    public String enemyAttackPrint() {
        String attackName = "";
        String attackStatement = "";
        int damage = 0;
        Object[] moveArray = currentAttack.keySet().toArray();
        attackName = moveArray[0].toString();
        damage = currentAttack.get(attackName);

        String statement = "";
        if (enemyAttackIsAttack()) {
            attackStatement = "Enemy " + name + " " + attackName + " you for " + damage + " damage!";
        }
        else {
            attackStatement = "Enemy " + name + " " + attackName + " itself for " + damage + " damage!";
        }
        return attackStatement;
    }

    public void stopOverHeal() {
        String attackName = "";
        int healing = maxHealthPoints - healthPoints;
        Object[] moveArray = currentAttack.keySet().toArray();
        attackName = moveArray[0].toString();
        currentAttack.replace(attackName, currentAttack.get(attackName), healing);
    }

    public boolean enemyAttackIsAttack() {
        boolean isAttack = false;

        Object[] moveArray = currentAttack.keySet().toArray();
        String attackName = moveArray[0].toString();
        if (attackMove.keySet().contains(attackName)) {
            isAttack = true;
        }
        return isAttack;
    }

    public int getEffectiveness() {
        String attackName = "";
        Object[] moveArray = currentAttack.keySet().toArray();
        attackName = moveArray[0].toString();
        return currentAttack.get(attackName);
    }

    public String toString() {
        return name + " HP: (" + healthPoints + "/" + maxHealthPoints + ")";
    }

}
