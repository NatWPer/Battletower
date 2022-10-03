package textEdition;

import java.util.Scanner;
import java.lang.Thread;
import java.util.Random;
import java.util.ArrayList;

import fighters.enemies.*;
import fighters.PlayerCharacter;
import items.*;
public class Main extends Thread {
    public static boolean inBattle=false;
    public static void main(String[] args) {
        // initializes variables
        //demonCounter determine game's hardcore mode
        int demonCounter=1;
        //helps determine order(will be updated to work with multiple fighters.enemies
        int second;
        //scanner being used for player input
        Scanner combatChoice = new Scanner(System.in);
        //random generator
        Random rand = new Random();
        //determines difficulty and skill point allocation
        System.out.println("How difficult would you like this to be on a scale of 1 to 10?");
        int difficulty = combatChoice.nextInt()-1;
        //a hardcore mode
        System.out.println("Do you want demon mode on? 1) No 2) Yes but I shouldn't");
        int demon=combatChoice.nextInt();
        //Sets the player's name
        System.out.println("What is your warrior's name?");
        combatChoice.nextLine();
        String name = combatChoice.nextLine();
        //creates the player object
        PlayerCharacter player = new PlayerCharacter(name);
        //Choosing a wand or a blade
        System.out.println("Do you want 1) a wand OR 2) a blade");
        int weaponChoice = combatChoice.nextInt();
        Weapon startingWeapon;
        if(weaponChoice==1){
            startingWeapon = new BasicWand("Old Bud",1);
        }else{
            startingWeapon=new BasicBlade("Rusty Friend",1);
        }
        player.setPlayerWeapon(startingWeapon);
        player.pickUp(startingWeapon);
        // has the player create their stat point distribution (modifies the number of skill points based on difficulty
        player.distribute(40-2*difficulty);
        //shows the stats of the player
        System.out.println(player);
        //Stores all the different story chapters
        String[] storyArray = new String[]{"You hike up the cold desolate " +
                "mountain towards your fate. There is only one chance to win your freedom" +
                ". That is to bring back the Crown of Skulls from the Tower" +
                " of Mount Telbrok. As you approach the tower its dark obsidian walls " +
                "loom above you, partially mocking, partially intimidating you.",
                "As you finish off your first opponent you let out a sigh of relief. It is good to know you can handle yourself." +
                        " You begin to walk to the next room.",
                "Your last battle most certainly left you tired. The stench of that creature's body throwing you off, " +
                        "you decide to climb even higher into the next chamber.",
                "You lie exhausted on the floor. Battered and beaten, you clamber back onto your feet and and begin to " +
                        "limp to the next floor. "
                };
        //creates a list of fighters.enemies
        ArrayList<Enemy> enemyArray = new ArrayList<>();
        enemyArray.add(new Spider("Johnny"));
        enemyArray.add(new GiantChameleon("David"));
        enemyArray.add(new FleshGolem("Charles"));
        enemyArray.add(new Owlbear("Jerry"));
        //array of treasure
        ArrayList<Item> treasure = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            treasure.add(new BasicBlade("Warrior's Blade",2));
            treasure.add(new BasicWand("Mages Orb",2));
        }
        for (int i = 0; i < 3; i++) {
            treasure.add(new BasicBlade("Fine Blade",3));
            treasure.add(new BasicWand("Wizard's Staff",3));
        }
        for (int i = 0; i < 2; i++) {
            treasure.add(new BasicBlade("Ancient Blade", 4));
            treasure.add(new BasicWand("Arcane Crystal", 4));
        }
        //initializes the monster selection variable
        int monsterSelect;
        //story game loop
        for (int i = 0; i < storyArray.length; i++) {
            //picks the index of the enemy from the enemy arraylist
            monsterSelect = rand.nextInt(enemyArray.size());
            //prints each character of the story one by one
            for (int j = 0; j < storyArray[i].length(); j++) {
                try {
                    sleep(4);
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.print(storyArray[i].charAt(j));
            }
            //prints the characters of the monster and room description one by one
            for (int j = 0; j < enemyArray.get(monsterSelect).getRoomDescription().length(); j++) {
                try {
                    sleep(4);
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.print(enemyArray.get(monsterSelect).getRoomDescription().charAt(j));
            }
            System.out.println("\nEnter 1 to continue");
            combatChoice.nextInt();
            //combat loop
            inBattle = true;
            while (inBattle) {
                //increments energy
                if (player.getEnergy()>player.getCurrentEnergy()){
                    player.setCurrentEnergy(player.getCurrentEnergy()+1);
                }
                //checks to see if the player is poisoned
                player.poisonTrigger();
                //checks to see if the enemy is poisoned
                enemyArray.get(monsterSelect).poisonTrigger();
                //prints player options
                System.out.println(player);
                System.out.println("\nWhat will you do?");
                System.out.println("You have " + player.getCurrentEnergy()+" energy!");
                System.out.println("1) Melee Move");
                System.out.println("2) Magic Move");
                System.out.println("3) Use Item");
                System.out.println("4) Use Special");
                //selects the kind of move
                int choice = combatChoice.nextInt();
                switch (choice) {
                    case 1:
                        //checks to see who goes first then acts accordingly
                        if(enemyArray.get(monsterSelect).getSpeed()>player.getSpeed()) {
                            //displays the melee combat options to the player
                            player.presentMelee();
                            //takes the players selection before either combatant makes a move
                            second=combatChoice.nextInt();
                            //enemy makes move
                            enemyArray.get(monsterSelect).makeMove(player);
                            //player makes move if alive
                            if (player.getHealth()>0) {
                                player.executeMelee(second, enemyArray.get(monsterSelect));
                            }
                        }else{
                            //displays the melee combat options to the player
                            player.presentMelee();
                            //player makes move
                            player.executeMelee(combatChoice.nextInt(),enemyArray.get(monsterSelect));
                            //enemy makes move if alive
                            if(enemyArray.get(monsterSelect).getHealth()>0) {
                                enemyArray.get(monsterSelect).makeMove(player);
                            }
                        }
                        break;
                    case 2:
                        if(enemyArray.get(monsterSelect).getSpeed()>player.getSpeed()) {
                            //displays the arcane combat options to the player
                            player.presentArcane();
                            //takes the players selection before either combatant makes a move
                            second=combatChoice.nextInt();
                            //enemy makes move
                            enemyArray.get(monsterSelect).makeMove(player);
                            //player makes move if alive
                            if (player.getHealth()>0) {
                                player.executeArcane(second, enemyArray.get(monsterSelect));
                            }
                        }else{
                            //displays the arcane combat options to the player
                            player.presentArcane();
                            //player makes move
                            player.executeArcane(combatChoice.nextInt(),enemyArray.get(monsterSelect));
                            //enemy makes move if alive
                            if(enemyArray.get(monsterSelect).getHealth()>0) {
                                enemyArray.get(monsterSelect).makeMove(player);
                            }
                        }
                        break;
                    case 3:
                        System.out.println("You have chosen item!(IN DEVELOPMENT)");
                        break;
                    case 4:
                        System.out.println("You have chosen special!(IN DEVELOPMENT)");
                        //checks to see who goes first then acts accordingly
                        if(enemyArray.get(monsterSelect).getSpeed()>player.getSpeed()) {
                            //displays the melee combat options to the player
                            player.presentSpecial();
                            //takes the players selection before either combatant makes a move
                            second=combatChoice.nextInt();
                            //enemy makes move
                            enemyArray.get(monsterSelect).makeMove(player);
                            //player makes move if alive
                            if (player.getHealth()>0) {
                                player.executeSpecial(second, enemyArray.get(monsterSelect));
                            }
                        }else{
                            //displays the melee combat options to the player
                            player.presentSpecial();
                            //player makes move
                            player.executeSpecial(combatChoice.nextInt(),enemyArray.get(monsterSelect));
                            //enemy makes move if alive
                            if(enemyArray.get(monsterSelect).getHealth()>0) {
                                enemyArray.get(monsterSelect).makeMove(player);
                            }
                        }
                        break;
                    default:
                        System.out.println("Your failure to react has resulted in your death. GAME OVER!");
                        System.exit(0);
                }
                inBattle=player.getHealth()>0 && enemyArray.get(monsterSelect).getHealth()>0;
            }
            try {
                sleep(10);
            } catch (Exception e) {
                System.out.println(e);
            }
            if(player.getHealth()<=0) {
                System.out.println("You were defeated by " + enemyArray.get(monsterSelect));
                System.exit(0);
            }else{
                System.out.println("You defeated "+enemyArray.get(monsterSelect));
            }
            System.out.println(player);
            //levels up player
            player.distribute(20-difficulty);
            //levels up unselected fighters.enemies
            if(demon>1){
                for (Enemy e :
                        enemyArray) {
                    e.upLevel(demonCounter);
                    demonCounter++;
                }
            }else {
                for (Enemy e :
                        enemyArray) {
                    e.upLevel(1);
                }
            }
            Item item = treasure.get(rand.nextInt(treasure.size()));
            System.out.println("Congratulations! You found a " + item.getName()+"! Would you like to " +
                    "1) pick it up or " +
                    "2) leave it behind?");
            if (combatChoice.nextInt()==1){
                System.out.println("You pick up the "+item.getName()+"!");
                player.pickUp(item);
            }else{
                System.out.println("You leave the "+item.getName()+"behind...");
            }
            System.out.println("Would you like to manage your inventory? 1) Skip 2) Manage");
            if (combatChoice.nextInt()==2) {
                player.manageInventory(combatChoice);
            }else{
                System.out.println("You skipped");
            }
            enemyArray.remove(monsterSelect);
        }
    }
}