import jdk.jshell.Snippet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/*
* The player class represents the player's character. the player object has a name, arraylists
* that represent the deck, hand, and discard as well as having health, max health, energy, stating
* energy, shield, and status effects*/
public class Player {
    private String name;
    private ArrayList<String> playerDeckString = new ArrayList<String>();
    private ArrayList<String> obtainableCardsString = new ArrayList<String>();
    private ArrayList<Cards> playerDeck = new ArrayList<Cards>();
    private ArrayList<Cards> playerHand = new ArrayList<Cards>();
    private ArrayList<Cards> obtainableCards = new ArrayList<Cards>();
    private ArrayList<Cards> playerDiscard = new ArrayList<Cards>();
    private int playerHealth, playerMaxHealth;
    private int playerEnergy, playerStartEnergy;
    private int shield = 0;
    private HashMap<String, Integer> statusEffects = new HashMap<String, Integer>();
    /*
    * constructor for the player class that takes the parameter choice to get a file to scan through and put into
    * instance variables as well as adding status effects
    *
    * @param choice represents the users choice when selecting a class
    * */
    public Player(String choice) {
        choice = choice.toLowerCase();
        File f = new File("class/" + choice);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException fException) {
            System.out.println("File not found.");
        }
        this.name = s.nextLine();
        this.playerMaxHealth = Integer.parseInt(s.nextLine());
        this.playerHealth = Integer.parseInt(s.nextLine());
        this.playerStartEnergy = Integer.parseInt(s.nextLine());
        this.playerEnergy = Integer.parseInt(s.nextLine());
        String line = s.nextLine();
        String[] options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            playerDeckString.add(options[i]);
        }
        line = s.nextLine();
        options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            obtainableCardsString.add(options[i]);
        }
        turnToObject(playerDeckString, playerDeck);
        turnToObject(obtainableCardsString, obtainableCards);
        statusEffects.put("Burn", 0);
        statusEffects.put("Freeze", 0);
        statusEffects.put("Bleed", 0);
        statusEffects.put("Poison", 0);
        statusEffects.put("Strength", 0);
        statusEffects.put("Focus", 0);
        s.close();
    }

    /*
    * returns the name of the player's class
    *
    * @return returns the instance variable name as a String
    * */
    public String getName() {
        return name;
    }
    /*
     * returns the obtainable cards of the player
     *
     * @return returns the instance variable getPlayerHealth as an ArrayList
     * */
    public ArrayList<Cards> getObtainableCards() {
        return obtainableCards;
    }
    /*
     * returns the players hand as a list
     *
     * @return returns the instance variable playerHand as an ArrayList
     * */
    public ArrayList<Cards> getPlayerHand() {
        return playerHand;
    }
    /*
     * returns the health of the player
     *
     * @return returns the instance variable getPlayerHealth as an int
     * */
    public int getPlayerHealth() {
        return playerHealth;
    }
    /*
     * returns the max health of the player
     *
     * @return returns the instance variable getPlayerMaxHealth as an int
     * */
    public int getPlayerMaxHealth() {
        return playerMaxHealth;
    }
    /*
     * returns the energy of the player
     *
     * @return returns the instance variable playerEnergy as an int
     * */
    public int getPlayerEnergy() {
        return playerEnergy;
    }
    /*
     * returns the starting energy of the player
     *
     * @return returns the instance variable playerStartEnergy as an int
     * */
    public int getPlayerStartEnergy() {
        return playerStartEnergy;
    }
    /*
     * returns the shield of the player
     *
     * @return returns the instance variable shield as an int
     * */
    public int getShield() {
        return shield;
    }
    /*
    * changes the health of the player by the parameter
    *
    * @param damage represents the amount of damage the player is going to take
    * */
    public void changePlayerHealth(int damage) {
        playerHealth -= damage;
    }
    /*
    * changes the players energy to what the value of the parameter is
    *
    * @param change represents the energy of the player after playing a card or at the start of a new turn*/
    public void changePlayerEnergy(int change) {
        playerEnergy = change;
    }
    /*
    * turns the list of strings to list of Cards objects so that the list stores objects instead of strings
    *
    * @param from represents the original arraylist that is in strings
    * @param to represents the arraylist that will result from converting string to object and contains Cards objects
    * */
    private void turnToObject(ArrayList<String> from, ArrayList<Cards> to) {
        for (int i = 0; i < from.size(); i++) {
            Cards card = new Cards(from.get(i));
            to.add(card);
        }
    }
    /*
     * moves cards from the players deck to their hand until they have 6 cards in hand
     * */
    public void deckToHand() {
        while (playerHand.size() < 6) {
            if (playerDeck.isEmpty()) {
                discardToDeck();
            }
            int random = (int) (Math.random() * playerDeck.size());
            playerHand.add(playerDeck.get(random));
            playerDeck.remove(random);
        }
    }
    /*
     * moves cards from the hand deck to their discard
     *
     * @param discard represents the specific card that is being sent from the hand to the discard
     * */
    public void handToDiscard(int discard) {
        playerDiscard.add(playerHand.get(discard));
        playerHand.remove(discard);
    }
    /*
    * moves cards from the discard to the deck
    * */
    public void discardToDeck() {
        for (int i = 0; i < playerDiscard.size(); i++) {
            playerDeck.add(playerDiscard.get(0));
            playerDiscard.remove(0);
        }
    }
    /*
    * display the hand of the player and gives their index
    *
    * @return a list of cards and their information like their energy cost and description
    * */
    public String displayHand() {
        String hand = "";
            for (int i = 0; i < playerHand.size(); i++) {
                hand += i + 1 + ") " + playerHand.get(i).getName() + " (Cost: " + playerHand.get(i).getEnergyCost() + " Energy) - " + (playerHand.get(i).getDescription()) + "\n";
            }
        return hand;
    }

    /*
    * finds out what kind of card the card at parameter choice in the player's hand is
    *
    * @param choice represents the users choice of card in the player's hand
    *
    * @return a String that is either attack, defense/heal, defense/shield, utility
    * these represent the attack type of the card
    * */
    public String attackType(int choice) {
        return playerHand.get(choice).getType();
    }
    /*
    * changes the value of instance variable shield by the parameter effectiveness
    *
    * @param effectiveness represents how much the players shield is increasing by
    * */
    public void changePlayerShield(int effectiveness) {
        shield += effectiveness;
    }
    /*
    * gets the card that the player chooses from the players hand
    *
    * @param number represents the users choice and is used to get the index of the card in the players hand
    *
    * @return a Cards object that is the card the player wants to play*/
    public Cards getActiveCard(int number) {
        return playerHand.get(number);
    }
    /*
    * moves a certain card from the obtainable list to the players deck
    *
    * @param choice represents the index of the card in the obtainable card list that the player wants to add to their deck
    * */
    public void obtainableToDeck(int choice) {
        playerDeck.add(obtainableCards.get(choice));
    }
    /*
    * does damage to the player based on the effectiveness of the burn status effect on them
    * */
    public void doBurn() {
        changePlayerHealth(statusEffects.get("Burn"));
    }
    /*
    * determines if the player is frozen or not
    *
    * @return a boolean that represents if the player will lose their next turn or not
    * */
    public boolean freezeTrue() {
        return statusEffects.get("Freeze") > 0;
    }
    /*
     * does damage to the player based on the effectiveness of the bleed status effect on them
     * */
    public void doBleed() {
        changePlayerHealth((statusEffects.get("Bleed")));
    }
    /*
     * does damage to the player based on the effectiveness of the poison status effect on them
     * */
    public void doPoison() {
        changePlayerHealth(statusEffects.get("Poison"));
    }
    /*
    * gets the value connected to the strength key in the map statusEffects
    *
    * @return int the value connected to Strength in statusEffects
    * */
    public int getStrength() {
        return statusEffects.get("Strength");
    }
    /*
     * determines if the player has burn on them or not
     *
     * @return a boolean that represents if the player will deal less shielding next turn
     * */
    public boolean burnTrue() {
        return statusEffects.get("Burn") > 0;
    }
    /*
     * determines if the player is bleeding or not
     *
     * @return a boolean that represents if the player will deal less damage next turn
     * */
    public boolean bleedTrue() {
        return statusEffects.get("Bleed") > 0;
    }
    /*
     * gets the value connected to the Focus key in the map statusEffects
     *
     * @return int the value connected to Focus in statusEffects
     * */
    public int getFocus() {
        return statusEffects.get("Focus");
    }
    /*
     * sets status effects burn, freeze, and bleed to 0
     * */
    public void resetStatusEffects() {
        statusEffects.replace("Burn", statusEffects.get("Burn"), 0);
        statusEffects.replace("Freeze", statusEffects.get("Freeze"), 0);
        statusEffects.replace("Bleed", statusEffects.get("Bleed"), 0);
    }
    /*
     * sets status effects burn, freeze, bleed, poison, strength, and focus to 0
     * */
    public void fullResetStatusEffects() {
        statusEffects.replace("Burn", statusEffects.get("Burn"), 0);
        statusEffects.replace("Freeze", statusEffects.get("Freeze"), 0);
        statusEffects.replace("Bleed", statusEffects.get("Bleed"), 0);
        statusEffects.replace("Poison", statusEffects.get("Poison"), 0);
        statusEffects.replace("Strength", statusEffects.get("Strength"), 0);
        statusEffects.replace("Focus", statusEffects.get("Focus"), 0);
    }
    /*
     * returns a sentence when a status effect is applied to the enemy
     *
     * @param enemyName represents the name of the enemy having a status effect applied to them
     * @param statusName represents the name of the status effect that is being added to the enemy
     * @param statusEffectiveness represents the effectiveness of the status effect being applied
     *
     * @returns a String which is a sentence that states what status effect is being applied to the enemy and how effective it is
     * */
    public String applyStatusPrint(String enemyName, String statusName, int statusEffectiveness) {
        return "You applied " + statusEffectiveness + " " + statusName + " to " + enemyName;
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
     * Adds a status effect to the player by using parameters status and amount to determine which one is being added and by how much
     *
     * @param status represents the name of the status effect being added and is used to change the value of its amount
     * @param amount represents how much effectiveness of that status effect is being added to the enemy
     * */
    public void addStatusEffect(String status, int amount) {
        statusEffects.replace(status, statusEffects.get(status), statusEffects.get(status) + amount);
    }
    /*
     * returns sentences depending if a status effect is on the player
     *
     * @return possibly multiple sentences that represent the current state of the player in terms of status effects
     * */
    public String statusPrint() {
        String result = "";
        if (burnTrue()) {
            result += "You burn for " + statusEffects.get("Burn") + " damage\n";
        }
        if (bleedTrue()) {
            result += "You bleed for " + statusEffects.get("Bleed") + " damage\n";
        }
        if (statusEffects.get("Poison") != 0) {
            result += "You are poisoned for " + statusEffects.get("Poison") + " damage\n";
        }

        return result;
    }
    /*
    * displays a message that correlates to the cards attack type and shows that player what that card just did
    * */
    public String attackDisplay(String enemyName, int damage, String attackType) {
        String result = "";
        if (attackType.equals("attack")) {
            result = "You hit " + enemyName + " for " + damage + " damage!";
        }
        else if (attackType.equals("defense/heal")) {
            result = "You healed for " + damage + " HP!";
        }
        else {
            result = "You shielded for " + damage + "!";
        }
        return result;
    }
    /*
    * the toString method that shows the players class name, health, energy, shield, and status effects
    *
    * @return a string that informs the player of information of the character they are playing as and how they are doing*/
    public String toString() {
        String display = name + "\nHP: (" + playerHealth + "/" + playerMaxHealth + ")  Energy: (" + playerEnergy + "/" + playerStartEnergy + ")  Shield: " + shield + "  Status Effects:";
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
        if (statusEffects.get("Focus") != 0) {
            display += " Focus(" + statusEffects.get("Focus") + ")";
        }
        return display;
    }

}
