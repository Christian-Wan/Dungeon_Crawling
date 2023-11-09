import java.util.ArrayList;

public class Player {

    ArrayList<String> playerDeckString = new ArrayList<String>();
    ArrayList<String> obtainableCardsString = new ArrayList<String>();
    ArrayList<Cards> playerDeck = new ArrayList<Cards>();
    ArrayList<Cards> playerHand = new ArrayList<Cards>();
    ArrayList<Cards> obtainableCards = new ArrayList<Cards>();
    ArrayList<Cards> playerDiscard = new ArrayList<Cards>();
    int playerHealth, playerMaxHealth;
    int playerEnergy, playerStartEnergy;

    public Player(String choice) {
        int playerChoice = Integer.parseInt(choice);
        if (playerChoice == 1) {
            //Scan class directory for barbarian
            //will have all instance variables filled
            //turn strings to objects that contain are the cards
        }
        else if (playerChoice == 2) {
            //Scan class directory for wizard
        }
        else {
            //Scan class directory for rouge
        }


    }

    public ArrayList<Cards> getObtainableCards() {
        return obtainableCards;
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
                hand += i + 1 + ") " + (playerHand.get(i)).getName() + " - " + (playerHand.get(i).getDescription()) + "\n";
            }
        return hand;
    }
    public String toString() {
        return "You HP: (" + playerHealth + "/" + playerMaxHealth + ")";
    }
}
