import jdk.jshell.Snippet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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


    public String getName() {
        return name;
    }

    private void turnToObject(ArrayList<String> from, ArrayList<Cards> to) {
        for (int i = 0; i < from.size(); i++) {
            Cards card = new Cards(from.get(i));
            to.add(card);
        }
    }
    public ArrayList<Cards> getObtainableCards() {
        return obtainableCards;
    }

    public ArrayList<String> getPlayerDeckString() {
        return playerDeckString;
    }

    public ArrayList<Cards> getPlayerDeck() {
        return playerDeck;
    }

    public ArrayList<Cards> getPlayerHand() {
        return playerHand;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public int getPlayerMaxHealth() {
        return playerMaxHealth;
    }

    public int getPlayerEnergy() {
        return playerEnergy;
    }

    public int getPlayerStartEnergy() {
        return playerStartEnergy;
    }

    public void changePlayerHealth(int damage) {
        playerHealth -= damage;
    }
    public void changePlayerEnergy(int change) {
        playerEnergy = change;
    }
    public void addCardToDeck(String cardName) {
        playerDeck.add(new Cards(cardName));
    }
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

    public void handToDiscard(int discard) {
        playerDiscard.add(playerHand.get(discard));
        playerHand.remove(discard);
    }

    public void discardToDeck() {
        for (int i = 0; i < playerDiscard.size(); i++) {
            playerDeck.add(playerDiscard.get(0));
            playerDiscard.remove(0);
        }
    }

    public String displayHand() {
        String hand = "";
            for (int i = 0; i < playerHand.size(); i++) {
                hand += i + 1 + ") " + playerHand.get(i).getName() + " (Cost: " + playerHand.get(i).getEnergyCost() + " Energy) - " + (playerHand.get(i).getDescription()) + "\n";
            }
        return hand;
    }
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

    public String attackType(int choice) {
        return playerHand.get(choice).getType();
    }

    public void changePlayerShield(int effectiveness) {
        shield += effectiveness;
    }

    public int getShield() {
        return shield;
    }

    public Cards getActiveCard(int number) {
        return playerHand.get(number);
    }

    public void obtainableToDeck(int choice) {
        playerDeck.add(obtainableCards.get(choice));
    }

    public void doBurn() {
        changePlayerHealth(statusEffects.get("Burn"));
    }
    public boolean freezeTrue() {
        return statusEffects.get("Freeze") == 1;
    }
    public void doBleed() {
        changePlayerHealth((statusEffects.get("Bleed")));
    }
    public void doPoison() {
        changePlayerHealth(statusEffects.get("Poison"));
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
    public int getFocus() {
        return statusEffects.get("Focus");
    }
    public void resetStatusEffects() {
        statusEffects.replace("Burn", statusEffects.get("Burn"), 0);
        statusEffects.replace("Freeze", statusEffects.get("Freeze"), 0);
        statusEffects.replace("Bleed", statusEffects.get("Bleed"), 0);
    }

    public void fullResetStatusEffects() {
        statusEffects.replace("Burn", statusEffects.get("Burn"), 0);
        statusEffects.replace("Freeze", statusEffects.get("Freeze"), 0);
        statusEffects.replace("Bleed", statusEffects.get("Bleed"), 0);
        statusEffects.replace("Strength", statusEffects.get("Strength"), 0);
        statusEffects.replace("Focus", statusEffects.get("Focus"), 0);
    }
    public String applyStatusPrint(String enemyName, String statusName, int statusEffectiveness) {
        return "You applied " + statusEffectiveness + " " + statusName + " to " + enemyName;
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
    public void addStatusEffect(String status, int amount) {
        statusEffects.replace(status, statusEffects.get(status), statusEffects.get(status) + amount);
    }

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

}
