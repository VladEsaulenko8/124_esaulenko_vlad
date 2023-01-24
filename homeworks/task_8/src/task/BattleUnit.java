package task;
public interface BattleUnit {
    String name ();
    int health();
    int maxHealth();
    void setMaxHealth(int value);
    void heal(int value);
    void takeDamage(int value);
    int strength();
    void setStrength(int value);
    int baseStrength();
    int armor();
    void restoreArmor(int value);
    void damageArmor(int value);
    int maxArmor();
    void setMaxArmor(int value);
    void specialAbility(BattleUnit[] ownTeam, BattleUnit[] enemyTeam);
    void attack(BattleUnit other);
}
