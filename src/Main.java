import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EnemyCreator enemy = new EnemyCreator();


        System.out.println("Health: " + enemy.getHealthPoints());
        System.out.println(enemy.getName());
        enemy.getAttack();
        System.out.println(enemy.getCurrentAttack());
        System.out.println(enemy.enemyAttackPrint(enemy.getCurrentAttack()));

    }


}

