package fighters.enemies;

import fighters.PlayerCharacter;

public class FleshGolem extends Enemy {
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
            setMelee(getMelee()+5);
            setArcane(getArcane()+2);
            setDefense(getDefense()+3);
            setEnergy(getEnergy()+1);
            setDamageRange(getDamageRange()+1);
            setSpeed(getSpeed()+1);
            setHealth(getHealth()+10);
            setMaxHealth(getMaxHealth()+10);
        }
    }

    public FleshGolem(String name) {
        super(5, 3, 3, 55, name, 3, 2, "You climb the stair to " +
                "the next chamber. As you step of the top stair, they drop behind you. You turn and look down where the stairs should be, but there is only darkness... " +
                "Withing the room you here *THUMP*thump* *THUMP*thump* *THUMP*thump* as a giant golem made of flesh begin to approach you, dragging a flail... made of " +
                "corpses.");
        setDamageRange(5);
    }
}
