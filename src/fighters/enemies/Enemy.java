package fighters.enemies;

import fighters.Fighter;
import fighters.PlayerCharacter;

import java.util.Random;

public class Enemy extends Fighter {

    public Random rand = new Random();
    String roomDescription;
    public void special(PlayerCharacter p){}
    public void special(){}
    public void upLevel(int x){}
    public void makeMove(PlayerCharacter p){}

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public void attack(PlayerCharacter p, boolean arcane){
        int hitChance;
        int damage;
        if(arcane){
            hitChance = rand.nextInt(20) + (2*this.getArcane());
            if(hitChance> p.getDefense()){
                System.out.println(this.getName()+" hit you with a "+hitChance+"!");
                damage=rand.nextInt(this.getDamageRange())+this.getArcane();
                p.takeDamage(damage);
                System.out.println("You take "+damage+" damage!");
            }else{
                System.out.println(this.getName()+" missed!");
            }
        }else{
            hitChance = rand.nextInt(20) + (2*this.getMelee());
            if(hitChance> p.getDefense()){
                System.out.println(this.getName()+" hit you with a "+hitChance+"!");
                damage=rand.nextInt(this.getDamageRange())+this.getMelee();
                p.takeDamage(damage);
                System.out.println("You take "+damage+" damage!");
            }else{
                System.out.println(this.getName()+" missed!");
            }
        }
    }
    public Enemy(int melee, int arcane, int defense, int health, String name, int energy, int speed, String roomDescription) {
        super(melee, arcane, defense, health, name, energy, speed);
        this.roomDescription =roomDescription;
    }
}
