package fighters;

public abstract class Fighter {
    private String png;

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    private int melee;
    private int arcane;
    private int defense;
    private int health;
    private int maxHealth;
    private int energy;
    private int currentEnergy;

    public int getHitRange() {
        return hitRange;
    }

    public void setHitRange(int hitRange) {
        this.hitRange = hitRange;
    }

    private int paralyzed;
    private int speed;
    private int damageRange=2;
    private int hitRange=20;
    private int poisonTimer=0;
    private int poisonDamage=0;

    public void takeDamage(int i){
        health -= i;
    }

    public int getDamageRange() {
        return damageRange;
    }

    public void setDamageRange(int damageRange) {
        this.damageRange = damageRange;
    }

    private String name;

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Fighter(int melee, int arcane, int defense, int health, String name, int energy, int speed) {
        this.melee = melee;
        this.arcane = arcane;
        this.defense = defense;
        this.health = health;
        this.maxHealth=health;
        this.paralyzed = 0;
        this.name = name;
        this.energy=energy;
        this.currentEnergy=energy;
        this.speed=speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getPoisonTimer() {
        return poisonTimer;
    }

    public void setPoisonTimer(int poisonTimer) {
        this.poisonTimer = poisonTimer;
    }

    public int getPoisonDamage() {
        return poisonDamage;
    }

    public void setPoisonDamage(int poisonDamage) {
        this.poisonDamage = poisonDamage;
    }
    public void heal(int x){
        for (int i = 0; i < x; i++) {
            if (this.getHealth()<this.getMaxHealth()){
                this.setHealth(this.getHealth()+1);
            }else{
                System.out.println(this.name + " is at max hp!");
                break;
            }
        }
    }
    public void poisonTrigger(){
        if (poisonTimer>0){
            poisonTimer--;
            health-=poisonDamage;
            System.out.println(this.getName()+" is poisoned.");
        }else {
            poisonDamage=0;
        }
    }

    public int deparalyzed() {
        if(paralyzed>0) {
            paralyzed--;
        }
        return paralyzed;
    }

    public void setParalyzed(int paralyzed) {
        this.paralyzed = paralyzed;
    }

    public int getMelee() {
        return melee;
    }

    public void setMelee(int melee) {
        this.melee = melee;
    }

    public int getArcane() {
        return arcane;
    }

    public void setArcane(int arcane) {
        this.arcane = arcane;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
