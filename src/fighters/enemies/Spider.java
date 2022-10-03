package fighters.enemies;

import fighters.PlayerCharacter;

public class Spider extends Enemy {
    int hitChance;
    @Override
    public void special(PlayerCharacter p) {
        hitChance = rand.nextInt(20) + (2*this.getArcane());
        if(hitChance> p.getDefense()){
            p.setPoisonDamage(getArcane()/4);
            p.setPoisonTimer(getArcane()/2);
        }else{
            System.out.println(this.getName()+" missed!");
        }
    }

    @Override
    public void makeMove(PlayerCharacter p){
        if (p.getPoisonTimer()<=0){
            this.special(p);
        }else{
            this.attack(p,false);
        }
    }

    @Override
    public String toString(){
        return this.getName();
    }

    @Override
    public void upLevel(int x) {
        for (int i = 0; i < x; i++) {
            setMelee(getMelee()+2);
            setArcane(getArcane()+4);
            setDefense(getDefense()+3);
            setEnergy(getEnergy()+2);
            setDamageRange(getDamageRange()+2);
            setSpeed(getSpeed()+4);
            setHealth(getHealth()+8);
            setMaxHealth(getMaxHealth()+8);
        }
    }

    public Spider(String name) {
        super(2, 6, 5, 40, name, 3, 5," As you arrive you push open the doors and walk through. When you reach the center of the chamber" +
                " the door shuts behind you and locks. Surprisingly you see no monsters on this floor. You begin to head " +
                "towards the door to the stairs, when you step in a strange puddle. As you pull your foot out, some of the " +
                "liquid is pulled up with it. You bring your gaze up to look for the source of the liquid" +
                " and as you do you see leaping down from the rafters a giant salivating spider with 8 glowing red eyes staring at you... hungrily. " +
                "You enter battle!");
        setDamageRange(4);
    }
}
