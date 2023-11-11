import java.util.Scanner;

public class DungeonCrawlerRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String playerChoice;
        Dungeon play;



        System.out.print("Choose map (type 1-3 or anything else for random): ");
        playerChoice = s.nextLine();

        if (playerChoice.equals("1") || playerChoice.equals("2") || playerChoice.equals("3")) {
            play = new Dungeon(playerChoice);
        }
        else {
            play = new Dungeon();
        }


        String move;


        while (true) {
            do {
                System.out.print("Choose Direction (n - North, e - East, s - South, w - West): ");
                playerChoice = s.nextLine();
                move = play.movePlayer(playerChoice);
                System.out.println(move);
            } while (move.equals("You walk into a wall"));
            play.runRoom();
        }
    }
    static Player player;
    static EnemyCreator enemy1 = new EnemyCreator();
    static EnemyCreator enemy2 = new EnemyCreator();
    static EnemyCreator enemy3 = new EnemyCreator();

    public static void importPlayer(String choice) {
        player = new Player(choice);
    }

    public static void badRoom1() {

        Scanner s = new Scanner(System.in);
        int choice;
        System.out.println("You enter a room with a " + enemy1.getName());


        while(enemy1.getHealthPoints() != 0 || player.getPlayerHealth() != 0) {
            player.changePlayerEnergy(player.playerStartEnergy);
            player.deckToHand();
            player.changePlayerShield(-player.getShield());
            while(player.playerEnergy != 0 && !player.playerHand.isEmpty()) {
                System.out.println(enemy1.toString());
                System.out.println("-------------------");
                System.out.println(player.toString());
                System.out.println("-------------------");
                System.out.println(player.displayHand());
                System.out.println("Which attack would you like to do (Choose a number/-1 to end turn): ");
                choice = Integer.parseInt(s.nextLine()) - 1;


                if (!InputValidation.integerValidate(0, (player.getPlayerHand().size() - 1), -2, choice)) {
                    System.out.println("Number is not an option (Choose a number/-1 to end turn): ");
                    choice = Integer.parseInt(s.nextLine()) - 1;
                }
                else if (choice == -2) { //might need to get rid of the break statement
                    System.out.println("You end your turn");
                    break;
                }
                else if (!(player.getActiveCard(choice).getEnergyCost() < player.getPlayerEnergy())) {
                    System.out.println("Card costs too much energy (Choose a number/-1 to end turn: ");
                    choice = Integer.parseInt(s.nextLine()) - 1;
                }


                int damage = player.getPlayerHand().get(choice).getEffectiveness();
                if (player.attackType(choice).equals("attack")) {
                    enemy1.changeHealth(damage);
                    System.out.println(player.attackDisplay(enemy1.getName(), damage, player.attackType(choice)));
                }
                else if (player.attackType(choice).equals("defend/heal")) { //don't know if this works properly
                    if (player.getPlayerHealth() + damage > player.getPlayerMaxHealth()) {
                        damage = player.getPlayerMaxHealth() - player.getPlayerHealth();
                    }

                    player.changePlayerHealth(-damage);
                    System.out.println(player.attackDisplay(enemy1.getName(), damage, player.attackType(choice)));
                }
                else {
                    player.changePlayerShield(damage);
                    System.out.println(player.attackDisplay(enemy1.getName(), damage, player.attackType(choice)));
                }
                player.changePlayerEnergy(player.getPlayerEnergy() - player.getActiveCard(choice).getEnergyCost()); // decreases the players energy by the amount the card costs
                player.handToDiscard(choice);
            }


            for (int i = 0; i < player.playerHand.size(); i++) {
                player.handToDiscard(i);
            }


        }

    }
}
