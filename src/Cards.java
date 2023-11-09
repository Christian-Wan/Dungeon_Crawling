import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cards {
    private String name;
    private String type;

    private int effectiveness;

    private ArrayList<String> traits = new ArrayList<String>();

    private String description;

    



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
        this.effectiveness = Integer.parseInt(s.nextLine());
        String line = s.nextLine();
        String[] options = line.split(", ");
        for (int i = 0; i < options.length; i++) {
            traits.add(options[i]);
        }
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

    public ArrayList<String> getTraits() {
        return traits;
    }


}
