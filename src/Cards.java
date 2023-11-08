public class Cards {

    private String type;

    private int effectiveness;

    private String status;

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
        this.name = s.nextLine();
        this.healthPoints = Integer.parseInt(s.nextLine());
        String line = s.nextLine();
    }
    


}
