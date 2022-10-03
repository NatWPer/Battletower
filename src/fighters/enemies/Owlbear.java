package fighters.enemies;

import fighters.PlayerCharacter;

public class Owlbear extends Enemy {
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
            setDefense(getDefense()+4);
            setEnergy(getEnergy()+2);
            setDamageRange(getDamageRange()+2);
            setSpeed(getSpeed()+2);
            setHealth(getHealth()+10);
            setMaxHealth(getMaxHealth()+10);
        }
    }

    public Owlbear(String name) {
        super(6, 1, 4, 55, name, 3, 2, "You enter the next chamber and " +
                "as you pass through the doors you find yourself in a large laboratory. You look around the room and " +
                " notice in the back cloaks in darkness, what appear to be a cage. You take a step closer, but accidentally" +
                " step on a glass vial! *ROOOOOAAAAARRRR*. You look back up at the cage and lumbering out " +
                "is a large bearlike animal, but instead of a bear's head, it has a large beak sitting upon an owl-like " +
                "visage. You steel yourself to fight.");
        setDamageRange(5);
    }
}
