import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
/*
* The Cards class represents a card the player can play. It stores data about the cards name, type,
* effectiveness, traits, description, energy cost, and if it can target or not*/
public class Cards {
    private String name;
    private String type;

    private int effectiveness;

    private ArrayList<HashMap<String, String>> traits = new ArrayList<HashMap<String, String>>();

    private String description;

    private int energyCost;

    private String canTarget;
    


    /*
    * Constructor for the Cards class.
    * Creates a file object using the parameter the find the file and uses a scanner to scan through it
    * Uses what the scanner scans to put data into the instance variables.
    * This constructor will be used to turn a String arraylist to a Cards arrayList in the player class
    *
    * @param card represents the name of the card inside the players deck
    * */
    public Cards(String card) {
        File f = new File("cards/" + card);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException fException) {
            System.out.println("File not found.");
        }
        assert s != null;
        this.name = s.nextLine();
        this.type = s.nextLine();
        this.description = s.nextLine();
        this.canTarget = s.nextLine();
        this.effectiveness = Integer.parseInt(s.nextLine());
        this.energyCost = Integer.parseInt(s.nextLine());
        String line = s.nextLine();
        String[] options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            String[] singleMove = options[i].split(" ");
            HashMap<String, String> thing =  new HashMap<String, String>();
            thing.put(singleMove[0], singleMove[1]);
            traits.add(thing);
        }
        s.close();
    }
    /*
    * returns the energy cost to play the card
    *
    * @return returns the instance variable energyCost as an int
    * */
    public int getEnergyCost() {
        return energyCost;
    }
    /*
     * returns the name of the card
     *
     * @return returns the instance variable name as a String
     * */
    public String getName() {
        return name;
    }
    /*
     * returns the description of the card
     *
     * @return returns the instance variable description as a String
     * */
    public String getDescription() {
        return description;
    }
    /*
     * returns the type of card the card is
     *
     * @return returns the instance variable type as a String
     * */
    public String getType() {
        return type;
    }
    /*
     * returns the effectiveness of the cards damage/healing/shielding
     *
     * @return returns the instance variable effectiveness as an int
     * */
    public int getEffectiveness() {
        return effectiveness;
    }
    /*
     * returns a list of traits the card has in the form of maps
     *
     * @return returns the instance variable traits as an ArrayList<HashMap<String, String>>
     * */
    public ArrayList<HashMap<String, String>> getTraits() {
        return traits;
    }
    /*
     * gets the name of the trait at a certain index of a certain card
     *
     * @param index represents the index of the list of traits the program wants to get the name of
     *
     * @return returns a String that contains the name of the trait
     * */
    public String getTraitName(int index) {
        return Arrays.toString(traits.get(index).keySet().toArray()).substring(1, Arrays.toString(traits.get(index).keySet().toArray()).length() - 1);
    }
    /*
     * gets the effectiveness of the trait at a certain index of a certain card using getTraitName as the key to get its value
     *
     * @param index represents the index of the list of traits the program wants to get the effectiveness of
     *
     * @return returns an int that contains the effectiveness of the trait
     * */
    public int getTraitEffectiveness(int index) {
        return Integer.parseInt(traits.get(index).get(getTraitName(index)));
    }
    /*
     * returns if the card can target enemies or not
     *
     * @return returns the instance variable canTarget as a String
     * */
    public String getCanTarget() {
        return canTarget;
    }
}
