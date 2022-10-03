package items;

public class Weapon implements Item{
    private int value;

    public int getMeleeBoost() {
        return meleeBoost;
    }

    public void setMeleeBoost(int meleeBoost) {
        this.meleeBoost = meleeBoost;
    }

    public int getMagicBoost() {
        return magicBoost;
    }

    public void setMagicBoost(int magicBoost) {
        this.magicBoost = magicBoost;
    }

    private String description;
    private String name;
    private int meleeBoost;
    private int magicBoost;

    public Weapon(int value, String description, String name, int meleeBoost, int magicBoost) {
        this.value = value;
        this.description = description;
        this.name = name;
        this.meleeBoost = meleeBoost;
        this.magicBoost = magicBoost;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getName(){
        return name;
    }
}
