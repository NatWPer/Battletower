package items;

public class ArcaneWeapon extends Weapon{
    public ArcaneWeapon(String name, int level) {
        super(level*6, "A blade imbued with magic power.", name, level*3, level*3);
    }
}
