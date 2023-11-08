import java.util.ArrayList;

public class Player {
    ArrayList<String> playerDeck = new ArrayList<String>();
    ArrayList<String> playerHand = new ArrayList<String>();
    ArrayList<String> obtainableCards = new ArrayList<String>();

    ArrayList<String> playerDiscard = new ArrayList<String>();
    int playerHealth, playerMaxHealth;
    int playerEnergy, playerStartEnergy;

    public Player(String choice) {
        int playerChoice = Integer.parseInt(choice);
        if (playerChoice == 1) {
            //Scan class directory for barbarian
            //will have all instance variables filled
        }
        else if (playerChoice == 2) {
            //Scan class directory for wizard
        }
        else {
            //Scan class directory for rouge
        }


    }

    public ArrayList<String> getObtainableCards() {
        return obtainableCards;
    }

    public ArrayList<String> getPlayerDeck() {
        return playerDeck;
    }

    public ArrayList<String> getPlayerHand() {
        return playerHand;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public int getPlayerMaxHealth() {
        return playerMaxHealth;
    }

    public void changePlayerHealth(int damage) {
        playerHealth -= damage;
    }
    public void changePlayerEnergy(int change) {
        playerEnergy = change;
    }
    public void addCardToDeck(String cardName) {
        playerDeck.add(cardName);
    }
    public void deckToHand() {
        while (playerHand.size() < 7) {
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
}
