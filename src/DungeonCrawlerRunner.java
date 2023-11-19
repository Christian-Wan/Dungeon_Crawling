import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DungeonCrawlerRunner {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String playerChoice; //A string that will be used
        int random; //A variable that will be reused whenever math.random is needed
        Dungeon play; //Dungeon object


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


        while (player.getPlayerHealth() > 0 && boss.getHealthPoints() > 0) { //a while loop that runs the game until the player dies or beats the boss
            do { //A loop that makes the player move until they reach a new room
                System.out.println();
                System.out.print("Choose Direction (Type the letter of the direction: n - North, e - East, s - South, w - West): ");
                playerChoice = s.nextLine();
                move = play.movePlayer(playerChoice);
                System.out.println(move);
            } while (move.equals("You walk into a wall") || move.equals("That is not an option"));

            String room = play.runRoom();
            if (room.equals("Bad")) { //If the player enters a bad room they end up
                random = (int) (Math.random() * 3) + 1;
                if (random <= 5) {
                    badRoom1();
                }
                else if (random >= 9) {
                    badRoom3();
                }
                else {
                    badRoom2();
                }
            }
            else if (room.equals("Good")) {
                random = (int) (Math.random() * 2) + 1;
                if (random == 1) {
                    goodRoom1();
                }
                else {
                    goodRoom2();
                }
            }
            else if (room.equals("Boss")) {
                bossRoom();
            }
            else {
                System.out.println("You have been to this room");
            }

        }


        if (player.getPlayerHealth() > 0) { //determines if the player ended the game by dying or by defeating the boss
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

    public static void doWhatIWant() {
        System.out.println(enemy1.getDefenseMoveTraits());
        enemy1.getAttack();
        System.out.println(Arrays.toString(enemy1.getCurrentAttack().keySet().toArray()).substring(1, Arrays.toString(enemy1.getCurrentAttack().keySet().toArray()).length() - 1));
        System.out.println(enemy1.getDefenseTraitEffectiveness());
    }

    public static void importPlayer(String choice) {
        player = new Player(choice);
    }

    private static void selectCard() {
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
                  "Pick a card to add to your deck:" +
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
    private static void playerTurn(int choice, EnemyCreator enemy) {
        int damage = player.getPlayerHand().get(choice).getEffectiveness();
        if (player.attackType(choice).equals("attack")) {
            damage += player.getStrength();
            if (player.bleedTrue()) {
                damage = (int) (damage * .75);
            }
            if (enemy.markTrue()){
                damage = (int) (damage * 1.5);
            }
            if (player.getName().equals("Rouge") && Math.random() * 100 < (20 + player.getFocus() * 10)) { //20% chance for the rouge class to crit whenever they attack
                System.out.println("You critically hit the enemy!");
                damage *= 2;
            }
            for (int i = 0; i < player.getPlayerHand().get(choice).getTraits().size(); i++) {
                if (player.getPlayerHand().get(choice).getTraitName(i).equals("Self")) {
                    System.out.println("You hurt yourself for " + player.getPlayerHand().get(choice).getTraitEffectiveness(i) + " damage");
                    player.changePlayerHealth(player.getPlayerHand().get(choice).getTraitEffectiveness(i));
                }
            }

            if (enemy.getShield() != 0) {
                if (damage >= enemy.getShield()) {
                    damage -= enemy.getShield();
                    enemy.changeShield(-enemy.getShield());
                } else {
                    enemy.changeShield(-damage);
                    damage = 0;
                }
            }
            enemy.changeHealth(damage);
            System.out.println(player.attackDisplay(enemy.getName(), damage, player.attackType(choice)));
        } else if (player.attackType(choice).equals("defense/heal")) {
            if (player.getPlayerHealth() + damage > player.getPlayerMaxHealth()) {
                damage = player.getPlayerMaxHealth() - player.getPlayerHealth(); //Makes sure that player doesn't go over their max hp
            }

            player.changePlayerHealth(-damage);
            System.out.println(player.attackDisplay(enemy.getName(), damage, player.attackType(choice)));
        } else if (player.attackType(choice).equals("defense/shield")) {
            if (player.burnTrue()) {
                damage = (int) (damage * .75);
            }
            player.changePlayerShield(damage);
            System.out.println(player.attackDisplay(enemy.getName(), damage, player.attackType(choice)));
        } else {
            System.out.println("You used " + player.getPlayerHand().get(choice).getName());
        }

        if (!player.getPlayerHand().get(choice).getTraitName(0).equals("none")) { //apply status effects to self or enemy
            for (int i = 0; i < player.getPlayerHand().get(choice).getTraits().size(); i++) {
                if (player.getPlayerHand().get(choice).getTraitName(i).equals("Strength") || player.getPlayerHand().get(choice).getTraitName(i).equals("Focus")) {
                    System.out.println(player.applyStatusPrint("yourself", player.getPlayerHand().get(choice).getTraitName(i), player.getPlayerHand().get(choice).getTraitEffectiveness(i)));
                    player.addStatusEffect(player.getPlayerHand().get(choice).getTraitName(i), player.getPlayerHand().get(choice).getTraitEffectiveness(i));
                } else {
                    System.out.println(player.applyStatusPrint(enemy.getName(), player.getPlayerHand().get(choice).getTraitName(i), player.getPlayerHand().get(choice).getTraitEffectiveness(i)));
                    enemy.addStatusEffect(player.getPlayerHand().get(choice).getTraitName(i), player.getPlayerHand().get(choice).getTraitEffectiveness(i));
                }
            }
        }
        player.changePlayerEnergy(player.getPlayerEnergy() - player.getActiveCard(choice).getEnergyCost()); // decreases the players energy by the amount the card costs
        player.handToDiscard(choice);
    }
    private static void enemyTurn(EnemyCreator enemy) {
        enemy.doStatusEffects();
        System.out.print(enemy.statusPrint());//
        enemy.changeShield(-enemy.getShield()); //resets shield to 0 for enemy

        if (enemy.getHealthPoints() > 0 && !enemy.freezeTrue()) {
            enemy.getAttack();
            int effectiveness = 0;

            if (enemy.enemyAttackIsAttack()) {
                effectiveness = enemy.getEffectiveness();
                effectiveness += enemy.getStrength();
                if (player.getName().equals("Rouge") && Math.random() * 100 < (20 + player.getFocus() * 5)) { //If player is a rouge then there is a 20% chance they dodge enemy attack
                    effectiveness = 0;
                    System.out.println("You dodged " + enemy.getName() + "'s attack");
                }
                else if (enemy.bleedTrue()) { //lowers enemy damage if they are afflicted with bleed debuff
                    effectiveness = (int) (effectiveness * .75);
                }
                while (player.getShield() > 0 && effectiveness != 0) { //checks if player has a shield if they do then do damage to that instead
                    player.changePlayerShield(-1);
                    effectiveness--;
                }
                player.changePlayerHealth(effectiveness); //deals damage to the player
                System.out.println(enemy.enemyAttackPrint(effectiveness));
                if (!enemy.getAttackTraitName().equals("none") && !enemy.getAttackTraitName().equals("Strength")) {
                    System.out.println(enemy.applyStatusPrint(enemy.getAttackTraitName(), enemy.getAttackTraitEffectiveness()));
                    player.addStatusEffect(enemy.getAttackTraitName(), enemy.getAttackTraitEffectiveness());
                }
                else if (!enemy.getAttackTraitName().equals("none") && enemy.getAttackTraitName().equals("Strength")) {
                    System.out.println(enemy.getName() + " raised its strength by " + enemy.getAttackTraitEffectiveness());
                    enemy.addStatusEffect(enemy.getAttackTraitName(), enemy.getAttackTraitEffectiveness());
                }
            }
            else {
                if (enemy.getCurrentAttack().containsKey("shields")) {
                    effectiveness = enemy.getEffectiveness();
                    if (enemy.bleedTrue()) {
                        effectiveness = (int) (effectiveness * .75);
                    }
                    enemy.changeShield(effectiveness);
                }
                else {
                    effectiveness = enemy.stopOverHeal();

                    enemy.changeHealth(-effectiveness);
                }
                System.out.println(enemy.enemyAttackPrint(effectiveness));
                if (!enemy.getDefenseTraitName().equals("none") && !enemy.getDefenseTraitName().equals("Strength")) {
                    System.out.println(enemy.applyStatusPrint(enemy.getDefenseTraitName(), enemy.getDefenseTraitEffectiveness()));
                    player.addStatusEffect(enemy.getDefenseTraitName(), enemy.getDefenseTraitEffectiveness());
                }
                else if (!enemy.getDefenseTraitName().equals("none") && enemy.getDefenseTraitName().equals("Strength")) {
                    System.out.println(enemy.getName() + " raised its strength by " + enemy.getDefenseTraitEffectiveness());
                    enemy.addStatusEffect(enemy.getDefenseTraitName(), enemy.getDefenseTraitEffectiveness());
                }
            }
        }
        else if (enemy.getHealthPoints() <= 0) {
            System.out.println(enemy.getName() + " has died");
        }
        enemy.resetStatusEffects();
    }
    public static void badRoom1() {
        boolean energyCheck;
        Scanner s = new Scanner(System.in);
        int choice = 0;
        enemy1 = new EnemyCreator();
        System.out.println("You enter a room with a " + enemy1.getName());


        while (enemy1.getHealthPoints() > 0 && player.getPlayerHealth() > 0) { //entire room is in this while loop that goes between enemy and player
            player.changePlayerEnergy(player.getPlayerStartEnergy());
            player.deckToHand();
            player.changePlayerShield(-player.getShield());


            while (!player.getPlayerHand().isEmpty() && enemy1.getHealthPoints() > 0 && player.getPlayerHealth() > 0) { //loop that holds the players turn
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
                } catch (NumberFormatException e) {
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
                    } else if (choice != -2) {
                        if (player.getActiveCard(choice).getEnergyCost() <= player.getPlayerEnergy()) {
                            energyCheck = true;
                        } else {
                            System.out.print("Card costs too much energy (Choose a number from the list or -1 to end turn): ");
                            try {
                                choice = Integer.parseInt(s.nextLine()) - 1;
                            } catch (NumberFormatException e) {
                                choice = 9;
                            }
                        }
                    } else {
                        energyCheck = true;
                    }

                }
                System.out.println("-------------------");
                if (choice == -2) { //might need to get rid of the break statement
                    System.out.println("You end your turn");
                    break;
                }


                playerTurn(choice, enemy1);



            }
            for (int i = 0; i < player.getPlayerHand().size(); i++) { //Sends the players remaining hand to discard
                player.handToDiscard(i);
            }

            player.doStatusEffects();
            System.out.print(player.statusPrint());
            player.resetStatusEffects();

            enemyTurn(enemy1);



        }
        System.out.println("-------------------");
        if (player.getPlayerHealth() > 0) {
            selectCard();
        }
    }
    public static void badRoom2() {
        boolean energyCheck;
        Scanner s = new Scanner(System.in);
        int choice = 0;
        enemy1 = new EnemyCreator();
        enemy2 = new EnemyCreator();
        System.out.println("You enter a room with a " + enemy1.getName() + " and " + enemy2.getName());


        while (enemy1.getHealthPoints() > 0 && player.getPlayerHealth() > 0 && enemy2.getHealthPoints() > 0) { //entire room is in this while loop that goes between enemy and player
            player.changePlayerEnergy(player.getPlayerStartEnergy());
            player.deckToHand();
            player.changePlayerShield(-player.getShield());


            while (!player.getPlayerHand().isEmpty() && enemy1.getHealthPoints() > 0 && player.getPlayerHealth() > 0 && enemy2.getHealthPoints() > 0) { //loop that holds the players turn
                energyCheck = false;

                System.out.println("-------------------");
                if (enemy1.getHealthPoints() > 0) {
                    System.out.println(enemy1.toString());
                }
                if (enemy2.getHealthPoints() > 0) {
                    System.out.println(enemy2.toString());
                }
                System.out.println("-------------------");
                System.out.println(player.toString());
                System.out.println("-------------------");
                System.out.println(player.displayHand());
                System.out.print("Which attack would you like to do (Choose a number from the list or -1 to end turn): ");
                try {
                    choice = Integer.parseInt(s.nextLine()) - 1;
                } catch (NumberFormatException e) {
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
                    } else if (choice != -2) {
                        if (player.getActiveCard(choice).getEnergyCost() <= player.getPlayerEnergy()) {
                            energyCheck = true;
                        } else {
                            System.out.print("Card costs too much energy (Choose a number from the list or -1 to end turn): ");
                            try {
                                choice = Integer.parseInt(s.nextLine()) - 1;
                            } catch (NumberFormatException e) {
                                choice = 9;
                            }
                        }
                    } else {
                        energyCheck = true;
                    }

                }
                System.out.println("-------------------");
                if (choice == -2) { //might need to get rid of the break statement
                    System.out.println("You end your turn");
                    break;
                }

                String option1 = "1) " + enemy1.toString();
                String option2 = "2) " + enemy2.toString();
                int chooseEnemy = 0;
                if (enemy1.getHealthPoints() <= 0) {
                    option1 = "1) DEAD";
                }
                if (enemy2.getHealthPoints() <= 0) {
                    option2 = "2) DEAD";
                }
                if (player.getPlayerHand().get(choice).getCanTarget().equals("yes")) {
                    System.out.println("Pick a target:" +
                            "\n" + option1 +
                            "\n" + option2);
                    System.out.print("Who would you like to attack (Pick the number of the living enemy you want to attack): ");
                    try {
                        chooseEnemy = Integer.parseInt(s.nextLine());
                    } catch (NumberFormatException e) {
                        chooseEnemy = 9;
                    }
                    while (!InputValidation.integerValidate(1, 2, chooseEnemy) || (chooseEnemy == 1 && option1.equals("DEAD")) || (chooseEnemy == 2 && option2.equals("DEAD"))) {
                        if (!InputValidation.integerValidate(1, 2, chooseEnemy)) {
                            System.out.print("That is not an option (Pick the number of the living enemy you want to attack): ");
                            try {
                                chooseEnemy = Integer.parseInt(s.nextLine());
                            } catch (NumberFormatException e) {
                                chooseEnemy = 9;
                            }
                        } else {
                            System.out.print("That enemy is dead (Pick the number of the living enemy you want to attack): ");
                            try {
                                choice = Integer.parseInt(s.nextLine()) - 1;
                            } catch (NumberFormatException e) {
                                choice = 9;
                            }
                        }
                    }
                }
                if (chooseEnemy == 1 || player.getPlayerHand().get(choice).getCanTarget().equals("no")) {
                    playerTurn(choice, enemy1);
                }
                if (chooseEnemy == 2) {
                    playerTurn(choice, enemy2);
                }




            }
            for (int i = 0; i < player.getPlayerHand().size(); i++) { //Sends the players remaining hand to discard
                player.handToDiscard(i);
            }

            player.doStatusEffects();
            System.out.print(player.statusPrint());
            player.resetStatusEffects();

            enemyTurn(enemy1);
            enemyTurn(enemy2);

        }
        System.out.println("-------------------");
        if (player.getPlayerHealth() > 0) {
            selectCard();
        }
    }
    public static void badRoom3() {
        boolean energyCheck;
        Scanner s = new Scanner(System.in);
        int choice = 0;
        enemy1 = new EnemyCreator();
        enemy2 = new EnemyCreator();
        enemy3 = new EnemyCreator();
        System.out.println("You enter a room with a " + enemy1.getName() + " and " + enemy2.getName() + " and " + enemy3.getName());


        while (enemy1.getHealthPoints() > 0 && player.getPlayerHealth() > 0 && enemy2.getHealthPoints() > 0 && enemy3.getHealthPoints() > 0) { //entire room is in this while loop that goes between enemy and player
            player.changePlayerEnergy(player.getPlayerStartEnergy());
            player.deckToHand();
            player.changePlayerShield(-player.getShield());


            while (!player.getPlayerHand().isEmpty() && enemy1.getHealthPoints() > 0 && player.getPlayerHealth() > 0 && enemy2.getHealthPoints() > 0 && enemy3.getHealthPoints() > 0) { //loop that holds the players turn
                energyCheck = false;

                System.out.println("-------------------");
                if (enemy1.getHealthPoints() > 0) {
                    System.out.println(enemy1.toString());
                }
                if (enemy2.getHealthPoints() > 0) {
                    System.out.println(enemy2.toString());
                }
                if (enemy3.getHealthPoints() > 0) {
                    System.out.println(enemy3.toString());
                }
                System.out.println("-------------------");
                System.out.println(player.toString());
                System.out.println("-------------------");
                System.out.println(player.displayHand());
                System.out.print("Which attack would you like to do (Choose a number from the list or -1 to end turn): ");
                try {
                    choice = Integer.parseInt(s.nextLine()) - 1;
                } catch (NumberFormatException e) {
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
                    } else if (choice != -2) {
                        if (player.getActiveCard(choice).getEnergyCost() <= player.getPlayerEnergy()) {
                            energyCheck = true;
                        } else {
                            System.out.print("Card costs too much energy (Choose a number from the list or -1 to end turn): ");
                            try {
                                choice = Integer.parseInt(s.nextLine()) - 1;
                            } catch (NumberFormatException e) {
                                choice = 9;
                            }
                        }
                    } else {
                        energyCheck = true;
                    }

                }
                System.out.println("-------------------");
                if (choice == -2) { //might need to get rid of the break statement
                    System.out.println("You end your turn");
                    break;
                }

                String option1 = "1) " + enemy1.toString();
                String option2 = "2) " + enemy2.toString();
                String option3 = "3) " + enemy3.toString();
                int chooseEnemy = 0;
                if (enemy1.getHealthPoints() <= 0) {
                    option1 = "1) DEAD";
                }
                if (enemy2.getHealthPoints() <= 0) {
                    option2 = "2) DEAD";
                }
                if (enemy3.getHealthPoints() <= 0) {
                    option3 = "3) DEAD";
                }
                if (player.getPlayerHand().get(choice).getCanTarget().equals("yes")) {
                    System.out.println("Pick a target:" +
                            "\n" + option1 +
                            "\n" + option2 +
                            "\n" + option3);
                    System.out.print("Who would you like to attack (Pick the number of the living enemy you want to attack): ");
                    try {
                        chooseEnemy = Integer.parseInt(s.nextLine());
                    } catch (NumberFormatException e) {
                        chooseEnemy = 9;
                    }
                    while (!InputValidation.integerValidate(1, 3, chooseEnemy) || (chooseEnemy == 1 && option1.equals("DEAD")) || (chooseEnemy == 2 && option2.equals("DEAD")) || (chooseEnemy == 3 && option1.equals("DEAD"))) {
                        if (!InputValidation.integerValidate(1, 2, chooseEnemy)) {
                            System.out.print("That is not an option (Pick the number of the living enemy you want to attack): ");
                            try {
                                chooseEnemy = Integer.parseInt(s.nextLine());
                            } catch (NumberFormatException e) {
                                chooseEnemy = 9;
                            }
                        } else {
                            System.out.print("That enemy is dead (Pick the number of the living enemy you want to attack): ");
                            try {
                                choice = Integer.parseInt(s.nextLine()) - 1;
                            } catch (NumberFormatException e) {
                                choice = 9;
                            }
                        }
                    }
                }
                if (chooseEnemy == 1 || player.getPlayerHand().get(choice).getCanTarget().equals("no")) {
                    playerTurn(choice, enemy1);
                }
                if (chooseEnemy == 2) {
                    playerTurn(choice, enemy2);
                }
                if (chooseEnemy == 3) {
                    playerTurn(choice, enemy3);
                }




            }
            for (int i = 0; i < player.getPlayerHand().size(); i++) { //Sends the players remaining hand to discard
                player.handToDiscard(i);
            }

            player.doStatusEffects();
            System.out.print(player.statusPrint());
            player.resetStatusEffects();

            enemyTurn(enemy1);
            enemyTurn(enemy2);

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
                player.changePlayerHealth(player.getPlayerHealth() - player.getPlayerMaxHealth());
            }
        }
    }

    public static void bossRoom() {
        boolean energyCheck;
        Scanner s = new Scanner(System.in);
        int choice = 0;
        boss = new EnemyCreator("");
        System.out.println("You enter a room with a " + boss.getName());


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
                    damage += player.getStrength();
                    if (player.bleedTrue()) {
                        damage = (int) (damage / .25);
                    }
                    int originalDamage = damage;
                    if (boss.getShield() != 0) {
                        if (damage >= boss.getShield()) {
                            damage -= boss.getShield();
                            boss.changeShield(-boss.getShield());
                        }
                        else {
                            boss.changeShield(-damage);
                            damage = 0;
                        }
                    }
                    boss.changeHealth(damage);
                    System.out.println(player.attackDisplay(boss.getName(), originalDamage, player.attackType(choice)));
                }
                else if (player.attackType(choice).equals("defend/heal")) {
                    if (player.getPlayerHealth() + damage > player.getPlayerMaxHealth()) {
                        damage = player.getPlayerMaxHealth() - player.getPlayerHealth(); //Makes sure that player doesn't go over their max hp
                    }

                    player.changePlayerHealth(-damage);
                    System.out.println(player.attackDisplay(boss.getName(), damage, player.attackType(choice)));
                }
                else {
                    if (player.burnTrue()) {
                        damage = (int) (damage / .25);
                    }
                    player.changePlayerShield(damage);
                    System.out.println(player.attackDisplay(boss.getName(), damage, player.attackType(choice)));
                }

                if (!player.getPlayerHand().get(choice).getTraitName(0).equals("none")) { //apply status effects
                    for (int i = 0; i < player.getPlayerHand().get(choice).getTraits().size(); i++) {
                        System.out.println(player.applyStatusPrint(boss.getName(), player.getPlayerHand().get(choice).getTraitName(i), player.getPlayerHand().get(choice).getTraitEffectiveness(i)));
                        boss.addStatusEffect(player.getPlayerHand().get(choice).getTraitName(i), player.getPlayerHand().get(choice).getTraitEffectiveness(i));
                    }
                }
                player.changePlayerEnergy(player.getPlayerEnergy() - player.getActiveCard(choice).getEnergyCost()); // decreases the players energy by the amount the card costs
                player.handToDiscard(choice);
            }


            for (int i = 0; i < player.getPlayerHand().size(); i++) { //Sends the players remaining hand to discard
                player.handToDiscard(i);
            }

            boss.doStatusEffects();
            boss.changeShield(-boss.getShield()); //resets shield to 0 for enemy
            System.out.print(boss.statusPrint());


            if (boss.getHealthPoints() > 0 && !boss.freezeTrue()) {
                boss.getAttack();
                System.out.println(boss.enemyAttackPrint(0));

                if (boss.enemyAttackIsAttack()) {
                    int damage = boss.getEffectiveness();
                    damage += boss.getStrength();
                    if (boss.bleedTrue()) {
                        damage = (int) (damage / .25);
                    }

                    while (player.getShield() > 0 && damage != 0) {
                        player.changePlayerShield(-1);
                        damage--;
                    }
                    player.changePlayerHealth(damage);

                } else {
                    if (boss.getCurrentAttack().containsKey("shields")) {
                        int effectiveness = boss.getEffectiveness();
                        if (boss.bleedTrue()) {
                            effectiveness = (int) (effectiveness / .25);
                        }
                        boss.changeShield(effectiveness);
                    }
                    else {
                        if (boss.getHealthPoints() + boss.getEffectiveness() > boss.getMaxHealthPoints()) {
                            boss.stopOverHeal();
                        }

                        boss.changeHealth(-boss.getEffectiveness());
                    }
                }
            }
            else if (boss.getHealthPoints() <= 0) {
                System.out.println(boss.getName() + " has died");
            }
            boss.resetStatusEffects();
        }
    }
}
