import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
/*
* The EnemyCreator class represents an enemy that the player can fight. An Enemy has data on its
* name, health points, max health points, attacks, traits of those attacks, the current attack
* they are going to do, and status effects they have on them
* */
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
    /*
    * Constructor for the EnemyCreator class.
    * Makes a random enemy from the enemies directory and scanning the file and using it to put into the instance variables
    * also adds the statusEffects individually at the end
    * */
    public EnemyCreator() {
        File f = new File("enemies/enemy" + (int) ((Math.random() * 3) + 1));
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException fException) {
            System.out.println("File not found.");
        }
        // adds attack moves
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
// adds defense moves
        line = s.nextLine();
        options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            String[] singleMove = options[i].split(" ");
            defenseMove.put(singleMove[0], Integer.parseInt(singleMove[1]));
        }
// adds traits to defense moves
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
    /*
     * Constructor for the EnemyCreator class.
     * Makes a random enemy from the bosses directory and scanning the file and using it to put into the instance variables
     * also adds the statusEffects individually at the end
     * The parameter check is used to differentiate the EnemyCreator for normal enemies and boss enemies
     *
     * @param check represents the requirement to make the boss enemy so that the basic and boss enemy have different constructors
     * */
    public EnemyCreator(String check) {
        File f = new File("bosses/boss" + (int) ((Math.random() * 2) + 1));
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException fException) {
            System.out.println("File not found.");
        }
        // adds attack moves
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
// adds defense moves
        line = s.nextLine();
        options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            String[] singleMove = options[i].split(" ");
            defenseMove.put(singleMove[0], Integer.parseInt(singleMove[1]));
        }
// adds traits to defense moves
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
    /*
    * Changes the health by subtracting the enemies current health by the parameter damage
    *
    * @param damage represents the damage the enemy is going to take
    * */
    public void changeHealth(int damage) {

        healthPoints -= damage;
    }
    /*
    * returns the health points of the enemy
    *
    * @return returns the instance variable healthPoints as an int*/
    public int getHealthPoints() {

        return healthPoints;
    }
    /*
    * returns a map containing one key and value that represent the attack the enemy is going to use
    *
    * @returns the instance variable currentAttack as a HashMap */
    public HashMap<String, Integer> getCurrentAttack() {
        return currentAttack;
    }
    /*
    * returns the name of the enemy
    *
    * @returns the instance variable name as a String*/
    public String getName() {
        return name;
    }
    /*
    * randomly selects an attack from the enemies arsenal with it being a 1/3 for it to be defensive and 2/3 for it to be offensive
    * and sets instance variable currentAttack to that attack
    * */
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
    /*
    * returns what the enemies attack did in sentence form
    *
    * @returns a String that is a sentence that represents what the enemies attack did*/
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
    /*
    * gets the effectiveness of the current attack and if adding that to the enemies current health exceeds its max
    * health then it returns the value of max health - current health. If it doesn't go over it returns the original value
    *
    * @returns an int that represents the amount of healing the enemy will do
    * */
    public int stopOverHeal() {
        int healing = getEffectiveness();
        if (healthPoints + getEffectiveness() > maxHealthPoints) {
            healing = maxHealthPoints - healthPoints;
        }
        return healing;
    }
    /*
    * checks if the current attack is or is not an offensive move
    * if it is offensive than return true
    *
    * @return is a boolean that represents if the current attack is an attack or not
    * */
    public boolean enemyAttackIsAttack() {
        boolean isAttack = false;

        Object[] moveArray = currentAttack.keySet().toArray();
        String attackName = moveArray[0].toString();
        if (attackMove.keySet().contains(attackName)) {
            isAttack = true;
        }
        return isAttack;
    }
    /*
    * gets the effectiveness of the current attack
    *
    * @returns an int that represents how much damage/healing/shielding a move will do*/
    public int getEffectiveness() {
        String attackName = "";
        Object[] moveArray = currentAttack.keySet().toArray();
        attackName = moveArray[0].toString();
        return currentAttack.get(attackName);
    }

    /*
    * does damage to the enemy if a status effect is on them
    * */
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


    /*
    * changes the value of instance variable shields by adding on the parameter to the original value of shield
    *
    * @param value represents the value that shield will increase by
    * */
    public void changeShield(int value) {
        shield += value;
    }
    /*
    * uses the changeHealth() method to change the enemies health by the effectiveness of burn
    * */
    public void doBurn() {
        changeHealth(statusEffects.get("Burn"));
    }
    /*
     * checks if the status effect freeze has a value greater than 0, if it does than return true
     *
     * @returns a boolean that represents if freeze is on the enemy or not
     * */
    public boolean freezeTrue() {
        return statusEffects.get("Freeze") > 0;
    }
    /*
     * uses the changeHealth() method to change the enemies health by the effectiveness of bleed
     * */
    public void doBleed() {
        changeHealth((statusEffects.get("Bleed")));
    }
    /*
     * uses the changeHealth() method to change the enemies health by the effectiveness of poison
     * */
    public void doPoison() {
        changeHealth(statusEffects.get("Poison"));
    }
    /*
    * gets the value connected to key strength in map statusEffects
    *
    * @return is an int that represents the value of the status effect Strength
    * */
    public int getStrength() {
        return statusEffects.get("Strength");
    }
    /*
     * checks if the status effect burn has a value greater than 0, if it does than return true
     *
     * @returns a boolean that represents if burn is on the enemy or not
     * */
    public boolean burnTrue() {
        return statusEffects.get("Burn") > 0;
    }
    /*
     * checks if the status effect bleed has a value greater than 0, if it does than return true
     *
     * @returns a boolean that represents if bleed is on the enemy or not
     * */
    public boolean bleedTrue() {
        return statusEffects.get("Bleed") > 0;
    }
    /*
     * returns the amount of shield the enemy has
     *
     * @returns the instance variable shield as an int
     * */
    public int getShield() {
        return shield;
    }

    /*
    * returns sentences depending if a status effect is on the enemy
    *
    * @return possibly multiple sentences that represent the current state of the enemy in terms of status effects
    * */
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
    /*
    * returns a sentence when a status effect is applied to the player
    *
    * @param statusName represents the name of the status effect that is being added to the player
    * @param statusEffectiveness represents the effectiveness of the status effect being applied
    *
    * @returns a String which is a sentence that states what status effect is being applied to the enemy and how effective it is
    * */
    public String applyStatusPrint(String statusName, int statusEffectiveness) {
        return name + " applied " + statusEffectiveness + " " + statusName + " to you";
    }
    /*
    * sets status effects burn, freeze, and bleed to 0 and decreases status effect mark by 1 if it is currently on the enemy
    * */
    public void resetStatusEffects() {
        statusEffects.replace("Burn", statusEffects.get("Burn"), 0);
        statusEffects.replace("Freeze", statusEffects.get("Freeze"), 0);
        statusEffects.replace("Bleed", statusEffects.get("Bleed"), 0);
        if (markTrue()) {
            statusEffects.replace("Mark", statusEffects.get("Mark"), statusEffects.get("Mark") - 1);
        }
    }
    /*
    * Adds a status effect to the enemy by using parameters status and amount to determine which one is being added and by how much
    *
    * @param status represents the name of the status effect being added and is used to change the value of its amount
    * @param amount represents how much effectiveness of that status effect is being added to the enemy
    * */
    public void addStatusEffect(String status, int amount) {
        statusEffects.replace(status, statusEffects.get(status), statusEffects.get(status) + amount);
    }
    /*
    * returns a string of the trait of the attack
    *
    * @return a string that is the name of the trait an attack has
    * */
    public String getAttackTraitName() {
        return attackMoveTraits.get(Arrays.toString(currentAttack.keySet().toArray()).substring(1, Arrays.toString(currentAttack.keySet().toArray()).length() - 1))[0];
    }
    /*
     * returns an int of the effectiveness of the trait of the attack
     *
     * @return an int that is the effectiveness of the trait that the attack has
     * */
    public int getAttackTraitEffectiveness() {
        return Integer.parseInt(attackMoveTraits.get(Arrays.toString(currentAttack.keySet().toArray()).substring(1, Arrays.toString(currentAttack.keySet().toArray()).length() - 1))[1]);
    }
    /*
     * returns a string of the trait of the defensive move
     *
     * @return a string that is the name of the trait a defensive move has
     * */
    public String getDefenseTraitName() {
        return defenseMoveTraits.get(Arrays.toString(currentAttack.keySet().toArray()).substring(1, Arrays.toString(currentAttack.keySet().toArray()).length() - 1))[0];
    }
    /*
     * returns an int of the effectiveness of the trait of the defensive move
     *
     * @return an int that is the effectiveness of the trait that the defenseive move has
     * */
    public int getDefenseTraitEffectiveness() {
        return Integer.parseInt(defenseMoveTraits.get(Arrays.toString(currentAttack.keySet().toArray()).substring(1, Arrays.toString(currentAttack.keySet().toArray()).length() - 1))[1]);
    }
    /*
    * checks if the enemy has hp greater than 0
    *
    * @returns a boolean that represents if the enemy is currently alive or not
    * */
    public boolean isAlive() {
        return healthPoints > 0;
    }
    /*
    * checks if the status effect mark is on the enemy
    *
    * @returns a boolean that represents if the enemy has the status effect mark on them
    * */
    public boolean markTrue() {
        return statusEffects.get("Mark") > 0;
    }
    /*
    * toString method that returns the enemies name, health, shield, and status effects
    *
    * @returns a sentence that contains information the player needs on the enemy like its health shield, and status effects on it
    *  */
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
}
