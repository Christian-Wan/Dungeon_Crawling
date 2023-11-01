import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] dungeonMap = new int[6][6];
        File f = new File("maps/map" + 3);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException fException) {
            System.out.println("File not found.");
        }
        int row = 0;
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] rooms = line.split(" ");
            for (int i = 0; i < rooms.length; i++) {
                dungeonMap[row][i] = Integer.parseInt(rooms[i]);
            }
            row++;
        }

        for (int r = 0; r < dungeonMap.length; r++) {
            for (int c = 0; c < dungeonMap[r].length; c++) {
                System.out.print(dungeonMap[r][c] + " " );
            }
            System.out.println();
        }
    }
}
