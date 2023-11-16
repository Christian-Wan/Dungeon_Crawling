import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        while (playerHand.size() < 7) {
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
        return name + "\nHP: " + playerHealth + "/" + playerMaxHealth + "  Energy: (" + playerEnergy + "/" + playerStartEnergy + ")  Shield: " + shield;
    }

    public String attackDisplay(String enemyName, int damage, String attackType) {
        String result = "";
        if (attackType.equals("attack")) {
            result = "You hit " + enemyName + " for " + damage + " damage!";
        }
        else if (attackType.equals("defend/heal")) {
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


}
