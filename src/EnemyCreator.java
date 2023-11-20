import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class EnemyCreator {

    private String name;
    private int healthPoints, maxHealthPoints;
    private HashMap<String, Integer> attackMove = new HashMap<String, Integer>();
    private HashMap<String, String[]> attackMoveTraits = new HashMap<String, String[]>();
    private HashMap<String, Integer> defenseMove = new HashMap<String, Integer>();
    private HashMap<String, String[]> defenseMoveTraits = new HashMap<String, String[]>();
    private HashMap<String, Integer> currentAttack = new HashMap<String, Integer>();
    private HashMap<String, Integer> statusEffects = new HashMap<String, Integer>();
    private int shield = 0;

    public EnemyCreator() {
        File f = new File("enemies/enemy" + (int) ((Math.random() * 3) + 1));
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

//adds traits to attacks
        line = s.nextLine();
        options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            String[] singleMove = options[i].split(" ");
            String[] traits = {singleMove[1], singleMove[2]};
            attackMoveTraits.put(singleMove[0], traits);
        }

        line = s.nextLine();
        options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            String[] singleMove = options[i].split(" ");
            defenseMove.put(singleMove[0], Integer.parseInt(singleMove[1]));
        }

        line = s.nextLine();
        options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            String[] singleMove = options[i].split(" ");
            String[] traits = {singleMove[1], singleMove[2]};
            defenseMoveTraits.put(singleMove[0], traits);
        }

        statusEffects.put("Burn", 0);
        statusEffects.put("Freeze", 0);
        statusEffects.put("Bleed", 0);
        statusEffects.put("Poison", 0);
        statusEffects.put("Strength", 0);
        statusEffects.put("Self", 0);
        statusEffects.put("Mark", 0);
        s.close();
    }

    public EnemyCreator(String check) {
        File f = new File("bosses/boss" + (int) ((Math.random() * 2) + 1));
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

//adds traits to attacks
        line = s.nextLine();
        options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            String[] singleMove = options[i].split(" ");
            String[] traits = {singleMove[1], singleMove[2]};
            attackMoveTraits.put(singleMove[0], traits);
        }

        line = s.nextLine();
        options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            String[] singleMove = options[i].split(" ");
            defenseMove.put(singleMove[0], Integer.parseInt(singleMove[1]));
        }

        line = s.nextLine();
        options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            String[] singleMove = options[i].split(" ");
            String[] traits = {singleMove[1], singleMove[2]};
            defenseMoveTraits.put(singleMove[0], traits);
        }

        statusEffects.put("Burn", 0);
        statusEffects.put("Freeze", 0);
        statusEffects.put("Bleed", 0);
        statusEffects.put("Poison", 0);
        statusEffects.put("Strength", 0);
        statusEffects.put("Self", 0);
        statusEffects.put("Mark", 0);
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

    public String enemyAttackPrint(int effectiveness) {
        String attackName = "";
        String attackStatement = "";
        int damage = effectiveness;
        Object[] moveArray = currentAttack.keySet().toArray();
        attackName = moveArray[0].toString();

        if (enemyAttackIsAttack()) {
            attackStatement = "Enemy " + name + " " + attackName + " you for " + damage + " damage!";
        }
        else {
            attackStatement = "Enemy " + name + " " + attackName + " itself for " + damage + " damage!";
        }
        return attackStatement;
    }

    public int stopOverHeal() {
        int healing = getEffectiveness();
        if (healthPoints + getEffectiveness() > maxHealthPoints) {
            healing = maxHealthPoints - healthPoints;
        }
        return healing;
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


    public void doStatusEffects() {
        if (burnTrue()) {
            doBurn();
        }
        if (bleedTrue()) {
            doBleed();
        }
        if (statusEffects.get("Poison") != 0) {
            doPoison();
        }
    }

    public String toString() {
        String display = name + " HP: (" + healthPoints + "/" + maxHealthPoints + ")  Shield: " + shield + "  Status Effects:";
        if (burnTrue()) {
            display += " Burn(" + statusEffects.get("Burn") + ")";
        }
        if (freezeTrue()) {
            display += " Frozen(1)";
        }
        if (bleedTrue()) {
            display += " Bleed(" + statusEffects.get("Bleed") + ")";
        }
        if (statusEffects.get("Poison") != 0) {
            display += " Poison(" + statusEffects.get("Poison") + ")";
        }
        if (statusEffects.get("Strength") != 0) {
            display += " Strength(" + statusEffects.get("Strength") + ")";
        }
        if (statusEffects.get("Mark") != 0) {
            display += " Mark(" + statusEffects.get("Mark") + ")";
        }
        return display;
    }

    public void changeShield(int value) {
        shield += value;
    }

    public void doBurn() {
        changeHealth(statusEffects.get("Burn"));
    }
    public boolean freezeTrue() {
        return statusEffects.get("Freeze") == 1;
    }
    public void doBleed() {
        changeHealth((statusEffects.get("Bleed")));
    }
    public void doPoison() {
        changeHealth(statusEffects.get("Poison"));
    }
    public int getStrength() {
        return statusEffects.get("Strength");
    }
    public boolean burnTrue() {
        return statusEffects.get("Burn") > 0;
    }
    public boolean bleedTrue() {
        return statusEffects.get("Bleed") > 0;
    }

    public int getShield() {
        return shield;
    }

    public HashMap<String, String[]> getAttackMoveTraits() {
        return attackMoveTraits;
    }

    public HashMap<String, String[]> getDefenseMoveTraits() {
        return defenseMoveTraits;
    }

    public String statusPrint() {
        String result = "";
        if (burnTrue()) {
            result += name + " burns for " + statusEffects.get("Burn") + " damage\n";
        }
        if (bleedTrue()) {
            result += name + " bleeds for " + statusEffects.get("Bleed") + " damage\n";
        }
        if (statusEffects.get("Poison") != 0) {
            result += name + " is poisoned for " + statusEffects.get("Poison") + " damage\n";
        }
        if (freezeTrue()) {
            result += name + " was frozen\n";
        }

        return result;
    }

    public String applyStatusPrint(String statusName, int statusEffectiveness) {
        return name + " applied " + statusEffectiveness + " " + statusName + " to you";
    }

    public void resetStatusEffects() {
        statusEffects.replace("Burn", statusEffects.get("Burn"), 0);
        statusEffects.replace("Freeze", statusEffects.get("Freeze"), 0);
        statusEffects.replace("Bleed", statusEffects.get("Bleed"), 0);
        if (markTrue()) {
            statusEffects.replace("Mark", statusEffects.get("Mark"), statusEffects.get("Mark") - 1);
        }
    }
    public void addStatusEffect(String status, int amount) {
        statusEffects.replace(status, statusEffects.get(status), statusEffects.get(status) + amount);
    }
    public String getAttackTraitName() {
        return attackMoveTraits.get(Arrays.toString(currentAttack.keySet().toArray()).substring(1, Arrays.toString(currentAttack.keySet().toArray()).length() - 1))[0];
    }
    public int getAttackTraitEffectiveness() {
        return Integer.parseInt(attackMoveTraits.get(Arrays.toString(currentAttack.keySet().toArray()).substring(1, Arrays.toString(currentAttack.keySet().toArray()).length() - 1))[1]);
    }
    public String getDefenseTraitName() {
        return defenseMoveTraits.get(Arrays.toString(currentAttack.keySet().toArray()).substring(1, Arrays.toString(currentAttack.keySet().toArray()).length() - 1))[0];
    }
    public int getDefenseTraitEffectiveness() {
        return Integer.parseInt(defenseMoveTraits.get(Arrays.toString(currentAttack.keySet().toArray()).substring(1, Arrays.toString(currentAttack.keySet().toArray()).length() - 1))[1]);
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }
    public boolean markTrue() {
        return statusEffects.get("Mark") > 0;
    }
}
