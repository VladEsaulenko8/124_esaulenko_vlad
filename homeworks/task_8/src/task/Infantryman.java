package task;

public class Infantryman extends BattleUnitBase {
    public Infantryman(String name, int maxHealth, int baseStrength, int maxArmor) {
        super(name, maxHealth, baseStrength, maxArmor);
    }

    @Override
    public void specialAbility(BattleUnit[] ownTeam, BattleUnit[] enemyTeam) {
    }

    @Override
    public void attack(BattleUnit attacker) { 
        double halfAttack = (this.strength / 2); 
        double quarterAttack = (this.strength / 4); 
        if(halfAttack < 1) halfAttack=1; 
        if(quarterAttack < 1) quarterAttack=1;
        if(attacker.armor()==0) { 
            attacker.takeDamage(this.strength);
        } else {
            attacker.takeDamage((int) halfAttack); 
            attacker.damageArmor((int) quarterAttack); 
        }
    }
}