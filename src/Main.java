import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Cards card = new Cards("firebolt");


        System.out.println(card.getName());
        System.out.println(card.getDescription());
        System.out.println(card.getType());
        System.out.println(card.getEffectiveness());
        System.out.println(card.getTraits());

    }


}

