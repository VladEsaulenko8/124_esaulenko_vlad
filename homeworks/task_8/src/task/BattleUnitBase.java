package task;


public abstract class BattleUnitBase implements BattleUnit {

    public int health=-1;
    protected int strength=-1000;
    protected int armor=-1;
    private int maxHealth;
    private final String name;
    int maxArmor;
    int baseStrength;

    public BattleUnitBase(String name, int maxHealth, int baseStrength, int maxArmor ) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.baseStrength = baseStrength;
        this.maxArmor = maxArmor;
        this.armor=armor();
        this.strength=strength();
        this.health=health();
    }

    public abstract void specialAbility(BattleUnit[] ownTeam, BattleUnit[] enemyTeam);
    public abstract void attack(BattleUnit other);
    
    @Override
    public String name (){
        return this.name;
    }

    @Override
    public int armor() {
        if (armor == -1) {
            armor = maxArmor;
        }
        return armor;
    }

    @Override
    public int health() {
        int retHealth=this.health;
        if (retHealth == -1) {
            retHealth = maxHealth;
        }
        if(retHealth <0 ){
            retHealth = 0;
        }
        if(retHealth > maxHealth ){
            retHealth = maxHealth;
        }
        this.health=retHealth;
        return retHealth;
    }

    @Override
    public  int maxHealth(){
        return  maxHealth;
    }

    @Override
    public  void setMaxHealth(int value) {
        if (this.health > value) this.health = value;
        this.maxHealth = value;
    }

    @Override
    public void heal(int value) {
        this.health += value;
        if (this.health > maxHealth) this.health = maxHealth;
    }

    @Override
    public void takeDamage(int value) {
        health = health - value;
    }

    @Override
    public int strength() {
        if (strength ==-1000 ) {
            strength = baseStrength;
        }
        return this.strength;
    }

    @Override
    public void setStrength(int value) {
        this.strength = value;
    }

    @Override 
    public int baseStrength(){
        return baseStrength;
    }

    @Override
    public void restoreArmor(int value) {
        this.armor += value;
        if(this.armor > maxArmor) this.armor=maxArmor;
    }

    @Override
    public void damageArmor(int value) {
        this.armor -= value;
        if(this.armor < 0) this.armor=0;
    }

    @Override
    public int maxArmor(){
        return maxArmor;
    }

    @Override
    public void setMaxArmor(int value) {
        this.maxArmor = value;
        if(this.armor>value) this.armor=value;
    }
}
