package fighters.enemies;

import fighters.PlayerCharacter;

public class GiantChameleon extends Enemy {
    @Override
    public void special() {

    }

    @Override
    public void makeMove(PlayerCharacter playerCharacter){
        this.attack(playerCharacter,false);
    }

    @Override
    public String toString(){
        return this.getName();
    }

    @Override
    public void upLevel(int x) {
        for (int i = 0; i < x; i++) {
            setMelee(getMelee()+3);
            setArcane(getArcane()+1);
            setDefense(getDefense()+5);
            setEnergy(getEnergy()+3);
            setDamageRange(getDamageRange()+1);
            setSpeed(getSpeed()+1);
            setHealth(getHealth()+10);
            setMaxHealth(getMaxHealth()+10);
        }
    }

    public GiantChameleon(String name) {
        super(4, 5, 20, 40, name, 3, 20, "You proceed to find a set of dark wooden doors. " +
                "As you push the doors open you find a small forest within the chamber itself. " +
                "As you enter you can't help but feel a chill run down your spine. It is like you are" +
                " being watched. As you think this, you sense some sort of projectile and duck, to see a giant tongue " +
                "slam into the tree in front of you. You turn and look as it seems the tree itself launched the attack, " +
                "but upon closer inspection it appears that it was a Giant Chameleon in disguise... and it doesn't seem like it has been fed in " +
                "a while...");
        setDamageRange(4);
    }
}
