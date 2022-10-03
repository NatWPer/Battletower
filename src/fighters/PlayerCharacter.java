package fighters;

import fighters.Fighter;
import items.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import items.Weapon;
public class PlayerCharacter extends Fighter {
    Weapon playerWeapon;
    private int nimbleTime=0;
    private int nimbleDefense=0;
    public ArrayList<String> options = new ArrayList<String>();
    private int stoneskinTime=0;
    private int stoneskinDefense=0;
    private int arcaneShieldTimer=0;
    private int arcaneShieldArmor=0;
    private ArrayList<Item> inventory= new ArrayList();
    public PlayerCharacter(String name) {
        super(1, 1, 1, 10, name, 1, 1);
    }

    public void manageInventory(Scanner s){
        boolean mi = true;
        int choice;
        int counter=0;
        Item chosen;
        while(mi) {
            System.out.println("INVENTORY:");
            System.out.println("What would like to do?");
            System.out.println("0) Exit Inventory");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println((i+1)+ ") Interact with " + inventory.get(i).getName());
            }
            choice=s.nextInt();
            if (choice==0){
                mi=false;
            }else{
                choice--;
                chosen = inventory.get(choice);
                System.out.println("What would you like to do with "+inventory.get(choice).getName()+"?");
                if(inventory.get(choice) instanceof Weapon){
                    counter++;
                    System.out.println(counter+") Equip");
                    options.add("e");
                }
                counter++;
                System.out.println(counter+") Delete");
                options.add("d");
                counter++;
                System.out.println(counter+") Nothing");
                options.add("n");
                choice=s.nextInt();
                switch (options.get(choice-1)){
                    case "e":
                        this.setPlayerWeapon((Weapon)chosen);
                        break;
                    case "d":
                        inventory.remove(chosen);
                        break;
                    case "n":
                }
            }
            options.clear();
            counter=0;
        }

    }

    @Override
    public String toString() {
        if (stoneskinTime==1||arcaneShieldTimer==1) {
            return "\nPlayer Name :" + this.getName() + "\nMelee:" + this.getMelee() +
                    "\nMagic :" + this.getArcane() + "\nDefense :" + this.getDefense() +
                    "-->" + super.getDefense() + "\nHealth :" + this.getHealth() + "/" + this.getMaxHealth() + "\nMax Energy :" + this.getEnergy() +
                    "\nSpeed :" + this.getSpeed();
        }
        return "\nPlayer Name :" + this.getName() + "\nMelee:" + this.getMelee() +
                "\nMagic :" + this.getArcane() + "\nDefense :" + this.getDefense()
                +"\nHealth :" + this.getHealth() + "/" + this.getMaxHealth() + "\nMax Energy :" + this.getEnergy() +
                "\nSpeed :" + this.getSpeed();
    }

    public ArrayList<String> presentMeleeG(){
        int counter = 0;
        ArrayList<String> loptions = new ArrayList<>();
        int swtch = 100-this.getMelee();
        if(swtch<0){
            swtch=0;
        }
        switch (swtch) {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
            case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
            case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29:
            case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39:
            case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 49:
            case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 59:
            case 60: case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 68: case 69:
            case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79:
            case 80: case 81: case 82: case 83: case 84:
                if(this.getCurrentEnergy()>=2) {
                    counter++;
                    loptions.add(counter+") Stoneskins");
                }
            case 85: case 86: case 87: case 88: case 89:
                if(this.getCurrentEnergy()>=1) {
                    counter++;
                    loptions.add(counter+") Heavy Strike");
                }
            case 90: case 91: case 92: case 93: case 94: case 95:
                counter++;
                loptions.add(counter+") Precision Strike");
            case 96: case 97: case 98: case 99: case 100:
                counter++;
                loptions.add(counter+") Basic Attack");
        }
        return loptions;
    }

    public void presentMelee() {
        int counter;
        int swtch = 100-this.getMelee();
        System.out.println("You have chosen melee!");
        System.out.println("1) Hit");
        options.add("a");
        if(swtch<0){
            swtch=0;
        }
        counter = 1;
        switch (swtch) {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
            case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
            case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29:
            case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39:
            case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 49:
            case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 59:
            case 60: case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 68: case 69:
            case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79:
            case 80: case 81: case 82: case 83: case 84:
                if(this.getCurrentEnergy()>=2) {
                    counter++;
                    System.out.println(counter + ") Stoneskin EC:2");
                    options.add("s");
                }else{
                    System.out.println("Not Enough Energy for Stoneskin");
                }
            case 85: case 86: case 87: case 88: case 89:
                if(this.getCurrentEnergy()>=1) {
                    counter++;
                    System.out.println(counter + ") Heavy Strike EC:1 (Deals more damage)");
                    options.add("hs");
                }else{
                    System.out.println("Not enough energy for Heavy Strike");
                }
            case 90: case 91: case 92: case 93: case 94: case 95:
                counter++;
                System.out.println(counter + ") Precision Strike (More accurate)");
                options.add("ps");
            case 96: case 97: case 98: case 99: case 100:
        }
    }
    public void executeMelee(int choice,Fighter enemy){
        int damage;
        int hitChance;
        Random rand = new Random();
        switch (options.get(choice-1)) {
            case "a":
                hitChance = rand.nextInt(20) + this.getMelee();
                if(hitChance> enemy.getDefense()){
                    System.out.println("You Hit with a "+hitChance+"!");
                    damage=rand.nextInt(this.getDamageRange())+this.getMelee();
                    enemy.takeDamage(damage);
                    System.out.println("You deal "+damage+"!");
                }else{
                    System.out.println("You Miss!");
                }
                break;
            case "ps":
                hitChance = rand.nextInt(20) + (2*this.getMelee());
                if(hitChance> enemy.getDefense()){
                    System.out.println("You Hit with a "+hitChance+"!");
                    damage=rand.nextInt(this.getDamageRange())+this.getMelee();
                    enemy.takeDamage(damage);
                    System.out.println("You deal "+damage+"!");
                }else{
                    System.out.println("You Miss!");
                }
                break;
            case "hs":
                hitChance = rand.nextInt(20) + this.getMelee();
                this.setCurrentEnergy(this.getCurrentEnergy()-1);
                if(hitChance> enemy.getDefense()){
                    System.out.println("You Hit with a "+hitChance+"!");
                    damage=rand.nextInt(this.getDamageRange())+2*this.getMelee();
                    enemy.takeDamage(damage);
                    System.out.println("You deal "+damage+"!");
                }else{
                    System.out.println("You Miss!");
                }
                break;
            case "s":
                this.setCurrentEnergy(this.getCurrentEnergy()-2);
                this.stoneskinTime=this.getMelee()/5;
                this.stoneskinDefense=this.getMelee();
        }
        options.clear();
    }

    public int getStoneskinTime() {
        return stoneskinTime;
    }

    public void setStoneskinTime(int stoneskinTime) {
        this.stoneskinTime = stoneskinTime;
    }

    public int getStoneskinDefense() {
        return stoneskinDefense;
    }

    public void setStoneskinDefense(int stoneskinDefense) {
        this.stoneskinDefense = stoneskinDefense;
    }

    public void executeArcane(int choice, Fighter enemy){
        int damage;
        int hitChance;
        Random rand = new Random();
        switch (options.get(choice-1)) {
            case "as":
                arcaneShieldTimer=2;
                arcaneShieldArmor=2*getArcane();
                System.out.println("You summon a shield of shimmering light!");
                break;
            case "h":
                heal(getArcane()+getDamageRange());
                break;
            case "fb":
                hitChance = rand.nextInt(20) + this.getArcane();
                if(hitChance> enemy.getDefense()){
                    System.out.println("You Hit with a "+hitChance+"!");
                    damage=rand.nextInt(this.getDamageRange())+this.getArcane();
                    enemy.takeDamage(damage);
                    System.out.println("You deal "+damage+"!");
                }else{
                    System.out.println("You Miss!");
                }
                break;
            case "efb":
                hitChance = rand.nextInt(20) + (this.getArcane());
                if(hitChance> enemy.getDefense()){
                    System.out.println("You Hit with a "+hitChance+"!");
                    damage=rand.nextInt(this.getDamageRange())+2*this.getArcane();
                    enemy.takeDamage(damage);
                    System.out.println("You deal "+damage+"!");
                }else{
                    System.out.println("You Miss!");
                }
                break;
            case "afb":
                hitChance = rand.nextInt(20) + 2*this.getArcane();
                if(hitChance> enemy.getDefense()){
                    System.out.println("You Hit with a "+hitChance+"!");
                    damage=rand.nextInt(this.getDamageRange())+this.getArcane();
                    enemy.takeDamage(damage);
                    System.out.println("You deal "+damage+"!");
                }else{
                    System.out.println("You Miss!");
                }
                break;

        }
        options.clear();
    }
    @Override
    public int getDefense(){
        int defense=super.getDefense();
        if (stoneskinTime>0){
            stoneskinTime--;
            defense+=stoneskinDefense;
        }
        if (arcaneShieldTimer>0) {
            arcaneShieldTimer--;
            defense+=arcaneShieldArmor;
        }
        if(nimbleTime>0){
            nimbleTime--;
            defense+=nimbleDefense;
        }
        return defense;
    }

    @Override
    public int getMelee(){
        return super.getMelee()+this.getPlayerWeapon().getMeleeBoost();
    }

    @Override
    public int getArcane(){
        return super.getArcane()+this.getPlayerWeapon().getMagicBoost();
    }

    public void presentSpecial(){
        int counter=0;
        System.out.println("You have chosen special!");
        if(this.getMelee()>20 && this.getArcane()>20){
            counter++;
            System.out.println(counter+") Smite");
            options.add("sm");
        }
        if(this.getSpeed()>10 && super.getDefense()>10 && super.getEnergy()>10){
            counter++;
            System.out.println(counter+") Nimble Defense");
            options.add("nd");
        }
    }

    public void executeSpecial(int choice, Fighter enemy){
        int damage;
        int hitChance;
        Random rand = new Random();
        switch (options.get(choice-1)) {
            case "sm":
                hitChance = rand.nextInt(20) + this.getMelee()+this.getArcane();
                if(hitChance> enemy.getDefense()){
                    System.out.println("You Hit with a "+hitChance+"!");
                    damage=rand.nextInt(this.getDamageRange())+this.getArcane()+this.getMelee();
                    enemy.takeDamage(damage);
                    System.out.println("You deal "+damage+"!");
                }else{
                    System.out.println("You Miss!");
                }
                break;
            case "nd":
                
        }
        options.clear();
    }

    public void presentArcane(){
        int counter;
        int swtch = 100-this.getArcane();
        counter=0;
        System.out.println("You have chosen magic!");
        if(swtch<0){
            swtch=0;
        }
        switch (swtch) {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
            case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
            case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29:
            case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39:
            case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 49:
            case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 59:
            case 60: case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 68: case 69:
            case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79:
            case 80: case 81: case 82: case 83: case 84:
                counter++;
                System.out.println(counter+") Arcane Shield");
                options.add("as");
            case 85: case 86: case 87: case 88: case 89:
                counter++;
                System.out.println(counter + ") Empowered Force Bolt");
                options.add("efb");
            case 90: case 91: case 92: case 93: case 94: case 95:
                counter++;
                System.out.println(counter + ") Healing Magic");
                options.add("h");
            case 96: case 97:
                counter++;
                System.out.println(counter + ") Accurate Force Bolt");
                options.add("afb");
            case 98: case 99: case 100:
                counter++;
                System.out.println(counter+") Force Bolt");
                options.add("fb");
        }
    }



    public void distribute(int statPoints) {
        Scanner improver = new Scanner(System.in);
        int select;
        int amount;
        while (statPoints>0) {
            System.out.println("You have "+statPoints+ " stat points left.");
            System.out.println("How would you like to improve " + super.getName() + "?");
            System.out.println("1) Melee");
            System.out.println("2) Magic");
            System.out.println("3) Defense");
            System.out.println("4) Health");
            System.out.println("5) Energy");
            System.out.println("6) Speed");
            select = improver.nextInt();
            System.out.println("By how much?");
            amount=improver.nextInt();
            if (select<7){
                if(amount<=statPoints) {
                    statPoints-=amount;
                    switch (select) {
                        case 1:
                            System.out.println("Melee is improved!");
                            this.setMelee(super.getMelee() + amount);
                            break;
                        case 2:
                            System.out.println("Magic is improved!");
                            this.setArcane(super.getArcane() + amount);
                            break;
                        case 3:
                            System.out.println("Defense is improved!");
                            this.setDefense(super.getDefense() + amount);
                            break;
                        case 4:
                            System.out.println("Health is improved!");
                            this.setMaxHealth(this.getMaxHealth() + amount);
                            this.setHealth(this.getHealth() + amount);
                            break;
                        case 5:
                            System.out.println("Energy is improved!");
                            this.setEnergy(this.getEnergy() + amount);
                            break;
                        case 6:
                            System.out.println("Speed is improved!");
                            this.setSpeed(this.getSpeed() + amount);
                            break;

                    }
                }else{
                    System.out.println("Not Enough Stat Points");
                }
            }else {
                System.out.println("Not an option.\n");
            }
        }
        System.out.println(this);
    }

    public void pickUp(Item item){
        inventory.add(item);
    }

    public Weapon getPlayerWeapon() {
        return playerWeapon;
    }

    public void setPlayerWeapon(Weapon playerWeapon) {

        this.playerWeapon = playerWeapon;
    }
}
