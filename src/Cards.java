import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Cards {
    private String name;
    private String type;

    private int effectiveness;

    private ArrayList<HashMap<String, String>> traits = new ArrayList<HashMap<String, String>>();

    private String description;

    private int energyCost;

    private String canTarget;
    



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

    public int getEnergyCost() {
        return energyCost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getEffectiveness() {
        return effectiveness;
    }

    public ArrayList<HashMap<String, String>> getTraits() {
        return traits;
    }
    public String getTraitName(int index) {
        return Arrays.toString(traits.get(index).keySet().toArray()).substring(1, Arrays.toString(traits.get(index).keySet().toArray()).length() - 1);
    }
    public int getTraitEffectiveness(int index) {
        return Integer.parseInt(traits.get(index).get(getTraitName(index)));
    }

    public String getCanTarget() {
        return canTarget;
    }
}
