import java.util.ArrayList;

public class EnemyCreator {

    String name;
    int healthPoints;
    Object[] moves = new Object[4];

    public EnemyCreator(String name, int HP, Object[] moves) {
        this.name = name;
        healthPoints = HP;
        this.moves = moves;
    }

    public void changeHealth(int damage) {
        healthPoints -= damage;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public void attack() {
        int randomAttack = (int) (Math.random() * 4) + 1;
        if (randomAttack == 1) {

        }
        else if (randomAttack == 2) {

        }
        else if (randomAttack == 3) {

        }
        else {

        }
    }
}
