import java.sql.SQLOutput;
import java.util.Scanner;

public class DungeonCrawlerRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String playerChoice;
        Dungeon play;


        System.out.println("Welcome to Unique Game Title");
        System.out.println("Here is an overview: " +
                "\n- This game is a Deck Building Dungeon crawler" +
                "\n- The game ends when either you or the boss dies" +
                "\n- When the game asks for input available inputs are explained in ()" +
                "\n- Currently only wizard works\n");

        System.out.println(
                          "Pick a class:" +
                        "\nBarbarian - Deals heavy damage but ends up hurting himself in the process" +
                        "\nWizard - Applies ailments to the enemy while defending" +
                        "\nRouge - Dodges attacks and strikes the enemy at their weak points");
        System.out.print("Select a class (Type out the name to select): ");
        playerChoice = s.nextLine();
        String[] acceptable = {"Barbarian", "barbarian", "Wizard", "wizard", "Rouge", "rouge"};
        while (!InputValidation.stringValidate(acceptable, playerChoice)) {
            System.out.print("That is not an option (Type out the name to select): ");
            playerChoice = s.nextLine();
        }
        importPlayer(playerChoice);
        System.out.print("Choose map (type 1-3 or anything else for random): ");
        playerChoice = s.nextLine();

        if (playerChoice.equals("1") || playerChoice.equals("2") || playerChoice.equals("3")) {
            play = new Dungeon(playerChoice);
        }
        else {
            play = new Dungeon();
        }


        String move;


        while (player.getPlayerHealth() > 0 && boss.getHealthPoints() > 0) {
            do {
                System.out.println();
                System.out.print("Choose Direction (Type the letter of the direction: n - North, e - East, s - South, w - West): ");
                playerChoice = s.nextLine();
                move = play.movePlayer(playerChoice);
                System.out.println(move);
            } while (move.equals("You walk into a wall") || move.equals("That is not an option"));
            String room = play.runRoom();
            if (room.equals("Bad")) {
                badRoom1();
            }
            else if (room.equals("Good")) {
                goodRoom1();
            }
            else if (room.equals("Boss")) {
                bossRoom();
            }
            else {
                System.out.println("You have been to this room");
            }

        }
        if (player.getPlayerHealth() > 0) {
            System.out.println("VICTORY!");
        }
        else {
            System.out.println("DEFEAT!");
        }
    }
    static Player player;
    static EnemyCreator enemy1 = new EnemyCreator();
    static EnemyCreator enemy2 = new EnemyCreator();
    static EnemyCreator enemy3 = new EnemyCreator();
    static EnemyCreator boss = new EnemyCreator("");

    public static void importPlayer(String choice) {
        player = new Player(choice);
    }

    public static void selectCard() {
        Scanner s = new Scanner(System.in);
        int choice = 0;
        int card1;
        int card2;
        int card3;
        do {
            card1 = (int) (Math.random() * player.getObtainableCards().size());
            card2 = (int) (Math.random() * player.getObtainableCards().size());
            card3 = (int) (Math.random() * player.getObtainableCards().size());
        } while (card1 == card2 || card1 == card3 || card2 == card3);


        System.out.println(
                  "Pick a card:" +
                "\n1) " + player.getObtainableCards().get(card1).getName() + " (Cost: " + player.getObtainableCards().get(card1).getEnergyCost() + " Energy) - " + player.getObtainableCards().get(card1).getDescription() +
                "\n2) " + player.getObtainableCards().get(card2).getName() + " (Cost: " + player.getObtainableCards().get(card2).getEnergyCost() + " Energy) - " + player.getObtainableCards().get(card2).getDescription() +
                "\n3) " + player.getObtainableCards().get(card3).getName() + " (Cost: " + player.getObtainableCards().get(card3).getEnergyCost() + " Energy) - " + player.getObtainableCards().get(card3).getDescription());

        System.out.print("Pick one (Choose a number from the list or -1 to skip): ");
        do {
            try {
                choice = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                choice = 9;
            }
            if (!InputValidation.integerValidate(1, 3, -1, choice)) {
                System.out.println("That is not an option (Choose a number from the list or -1 to skip):");
            }
        } while (!InputValidation.integerValidate(1, 3, -1, choice));
        if (choice == 1) {
            player.obtainableToDeck(card1);
        }
        else if (choice == 2) {
            player.obtainableToDeck(card2);
        }
        else if (choice == 3){
            player.obtainableToDeck(card3);
        }
    }

    public static void badRoom1() {
        boolean energyCheck;
        Scanner s = new Scanner(System.in);
        int choice = 0;
        enemy1 = new EnemyCreator();
        System.out.println("You enter a room with a " + enemy1.getName());


        while(enemy1.getHealthPoints() > 0 && player.getPlayerHealth() > 0) {
            player.changePlayerEnergy(player.getPlayerStartEnergy());
            player.deckToHand();
            player.changePlayerShield(-player.getShield());


            while(!player.getPlayerHand().isEmpty() && enemy1.getHealthPoints() > 0) {
                energyCheck = false;

                System.out.println("-------------------");
                System.out.println(enemy1.toString());
                System.out.println("-------------------");
                System.out.println(player.toString());
                System.out.println("-------------------");
                System.out.println(player.displayHand());
                System.out.print("Which attack would you like to do (Choose a number from the list or -1 to end turn): ");
                try {
                    choice = Integer.parseInt(s.nextLine()) - 1;
                }
                catch (NumberFormatException e) {
                    choice = 9;
                }

                if (InputValidation.integerValidate(0, (player.getPlayerHand().size() - 1), -2, choice)) {
                    if (choice == -2 || player.getActiveCard(choice).getEnergyCost() < player.getPlayerEnergy()) {
                        energyCheck = true;
                    }
                }

                while (!InputValidation.integerValidate(0, (player.getPlayerHand().size() - 1), -2, choice) || energyCheck == false) {
                    if (!InputValidation.integerValidate(0, (player.getPlayerHand().size() - 1), -2, choice)) {
                        System.out.print("That is not an option (Choose a number from the list or -1 to end turn): ");
                        try {
                            choice = Integer.parseInt(s.nextLine()) - 1;
                        } catch (NumberFormatException e) {
                            choice = 9;
                        }
                    }
                    else if (choice != -2){
                        if (player.getActiveCard(choice).getEnergyCost() <= player.getPlayerEnergy()) {
                            energyCheck = true;
                        }
                        else {
                            System.out.print("Card costs too much energy (Choose a number from the list or -1 to end turn): ");
                            try {
                                choice = Integer.parseInt(s.nextLine()) - 1;
                            } catch (NumberFormatException e) {
                                choice = 9;
                            }
                        }
                    }
                    else {
                        energyCheck = true;
                    }

                }


                System.out.println("-------------------");
                if (choice == -2) { //might need to get rid of the break statement
                    System.out.println("You end your turn");
                    break;
                }
                int damage = player.getPlayerHand().get(choice).getEffectiveness();
                if (player.attackType(choice).equals("attack")) {
                    enemy1.changeHealth(damage);
                    System.out.println(player.attackDisplay(enemy1.getName(), damage, player.attackType(choice)));
                }
                else if (player.attackType(choice).equals("defend/heal")) { //don't know if this works properly
                    if (player.getPlayerHealth() + damage > player.getPlayerMaxHealth()) {
                        damage = player.getPlayerMaxHealth() - player.getPlayerHealth(); //Makes sure that player doesn't go over their max hp
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


            for (int i = 0; i < player.getPlayerHand().size(); i++) { //Sends the players remaining hand to discard
                player.handToDiscard(i);
            }


            if (enemy1.getHealthPoints() > 0) {
                enemy1.getAttack();
                System.out.println(enemy1.enemyAttackPrint());

                if (enemy1.enemyAttackIsAttack()) {
                    int damage = enemy1.getEffectiveness();

                    while (player.getShield() > 0 && damage != 0) {
                        player.changePlayerShield(-1);
                        damage--;
                    }
                    player.changePlayerHealth(damage);

                } else {
                    if (enemy1.getCurrentAttack().containsKey("shields")) {
                        enemy1.changeShield(enemy1.getEffectiveness());
                    }
                    else {
                        if (enemy1.getHealthPoints() + enemy1.getEffectiveness() > enemy1.getMaxHealthPoints()) {
                            enemy1.stopOverHeal();
                        }

                        enemy1.changeHealth(-enemy1.getEffectiveness());
                    }
                }
            }

        }
        System.out.println("-------------------");
        if (player.getPlayerHealth() > 0) {
            selectCard();
        }

    }

    public static void goodRoom1() {
        System.out.println();
        int choice;
        Scanner s = new Scanner(System.in);
        System.out.println("You enter a room full of weapons\n");
        System.out.print(
                "1) Pick a card\n" +
                "2) Leave\n" +
                "What would you like to do (Type the number you would like to select): ");


        try {
            choice = Integer.parseInt(s.nextLine());
        }
        catch (NumberFormatException e) {
            choice = -3;
        }
        while (!InputValidation.integerValidate(1, 2, choice)) {
            System.out.print("That is not an option (Type the number you would like to select): ");
            try {
                choice = Integer.parseInt(s.nextLine());
            }
            catch (NumberFormatException e) {
                choice = -3;
            }
        }



        if (choice == 1) {
            for (int i = 0; i < player.getObtainableCards().size(); i++) {
                System.out.println(i + 1 + ") " + player.getObtainableCards().get(i).getName() + " (Cost: " + player.getObtainableCards().get(i).getEnergyCost() + " Energy) - " + (player.getObtainableCards().get(i).getDescription()) + "");
            }
            System.out.print("Pick a card (Type the number of the desired card/-1 to leave): ");
            try {
                choice = Integer.parseInt(s.nextLine()) - 1;
            }
            catch (NumberFormatException e) {
                choice = -3;
            }


            while (!InputValidation.integerValidate(0, (player.getObtainableCards().size() - 1), -2, choice)) {
                System.out.print("That is not an option (Type the number of the desired card/-1 to leave): ");
                try {
                    choice = Integer.parseInt(s.nextLine()) - 1;
                } catch (NumberFormatException e) {
                    choice = -3;

                }
            }
            if (choice != -2) {
                player.obtainableToDeck(choice);
            }
        }
    }

    public static void goodRoom2() {
        System.out.println();
        int choice;
        int heal = (int) (player.getPlayerMaxHealth() * .25);
        Scanner s = new Scanner(System.in);
        System.out.println("You meet a nurse who offers to heal you\n");
        System.out.print(
                "1) Heal for " + heal + " HP\n" +
                        "2) Leave\n" +
                        "What would you like to do (Type the number you would like to select): ");


        try {
            choice = Integer.parseInt(s.nextLine());
        }
        catch (NumberFormatException e) {
            choice = -3;
        }
        while (!InputValidation.integerValidate(1, 2, choice)) {
            System.out.print("That is not an option (Type the number you would like to select): ");
            try {
                choice = Integer.parseInt(s.nextLine());
            }
            catch (NumberFormatException e) {
                choice = -3;
            }
        }



        if (choice == 1) {
            player.changePlayerHealth(-heal);
            if (player.getPlayerHealth() > player.getPlayerMaxHealth()) {
                player.changePlayerHealth(player.getPlayerMaxHealth() - player.getPlayerHealth());
            }
        }
    }

    public static void bossRoom() {
        boolean energyCheck;
        Scanner s = new Scanner(System.in);
        int choice = 0;
        System.out.println("You enter the final room");


        while(boss.getHealthPoints() > 0 && player.getPlayerHealth() > 0) {
            player.changePlayerEnergy(player.getPlayerStartEnergy());
            player.deckToHand();
            player.changePlayerShield(-player.getShield());


            while(!player.getPlayerHand().isEmpty() && boss.getHealthPoints() > 0) {
                energyCheck = false;

                System.out.println("-------------------");
                System.out.println(boss.toString());
                System.out.println("-------------------");
                System.out.println(player.toString());
                System.out.println("-------------------");
                System.out.println(player.displayHand());
                System.out.print("Which attack would you like to do (Choose a number from the list or -1 to end turn): ");
                try {
                    choice = Integer.parseInt(s.nextLine()) - 1;
                }
                catch (NumberFormatException e) {
                    choice = 9;
                }

                if (InputValidation.integerValidate(0, (player.getPlayerHand().size() - 1), -2, choice)) {
                    if (choice == -2 || player.getActiveCard(choice).getEnergyCost() < player.getPlayerEnergy()) {
                        energyCheck = true;
                    }
                }

                while (!InputValidation.integerValidate(0, (player.getPlayerHand().size() - 1), -2, choice) || energyCheck == false) {
                    if (!InputValidation.integerValidate(0, (player.getPlayerHand().size() - 1), -2, choice)) {
                        System.out.print("That is not an option (Choose a number from the list or -1 to end turn): ");
                        try {
                            choice = Integer.parseInt(s.nextLine()) - 1;
                        } catch (NumberFormatException e) {
                            choice = 9;
                        }
                    }
                    else if (choice != -2){
                        if (player.getActiveCard(choice).getEnergyCost() <= player.getPlayerEnergy()) {
                            System.out.println("Card cost: " + player.getActiveCard(choice).getEnergyCost());
                            System.out.println("Player: " + player.getPlayerEnergy());
                            energyCheck = true;
                        }
                        else {
                            System.out.print("Card costs too much energy (Choose a number from the list or -1 to end turn): ");
                            try {
                                choice = Integer.parseInt(s.nextLine()) - 1;
                            } catch (NumberFormatException e) {
                                choice = 9;
                            }
                        }
                    }
                    else {
                        energyCheck = true;
                    }

                }


                System.out.println("-------------------");
                if (choice == -2) { //might need to get rid of the break statement
                    System.out.println("You end your turn");
                    break;
                }
                int damage = player.getPlayerHand().get(choice).getEffectiveness();
                if (player.attackType(choice).equals("attack")) {
                    boss.changeHealth(damage);
                    System.out.println(player.attackDisplay(boss.getName(), damage, player.attackType(choice)));
                }
                else if (player.attackType(choice).equals("defend/heal")) { //don't know if this works properly
                    if (player.getPlayerHealth() + damage > player.getPlayerMaxHealth()) {
                        damage = player.getPlayerMaxHealth() - player.getPlayerHealth(); //Makes sure that player doesn't go over their max hp
                    }

                    player.changePlayerHealth(-damage);
                    System.out.println(player.attackDisplay(boss.getName(), damage, player.attackType(choice)));
                }
                else {
                    player.changePlayerShield(damage);
                    System.out.println(player.attackDisplay(boss.getName(), damage, player.attackType(choice)));
                }
                player.changePlayerEnergy(player.getPlayerEnergy() - player.getActiveCard(choice).getEnergyCost()); // decreases the players energy by the amount the card costs
                player.handToDiscard(choice);
            }


            for (int i = 0; i < player.getPlayerHand().size(); i++) { //Sends the players remaining hand to discard
                player.handToDiscard(i);
            }
            if (boss.getHealthPoints() > 0) {
                boss.getAttack();
                System.out.println(boss.enemyAttackPrint());

                if (boss.enemyAttackIsAttack()) {
                    int damage = boss.getEffectiveness();

                    while (player.getShield() > 0 && damage != 0) {
                        player.changePlayerShield(-1);
                        damage--;
                    }
                    player.changePlayerHealth(damage);

                } else {
                    if (boss.getHealthPoints() + boss.getEffectiveness() > boss.getMaxHealthPoints()) {
                        boss.stopOverHeal();
                    }

                    boss.changeHealth(-boss.getEffectiveness());
                }
            }

        }

    }
}
