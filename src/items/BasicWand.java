package items;

public class BasicWand extends Weapon{
    public BasicWand(String name, int level) {
        super(level*5, "A basic magic focus.", name, level*0, level*5);
    }
}