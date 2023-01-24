package task;

import java.util.Arrays;

public class ArmorDestroyer extends BattleUnitBase{
    public ArmorDestroyer(String name, int maxHealth, int baseStrength, int maxArmor) {
        super(name, maxHealth, baseStrength, maxArmor);
    }

    @Override
    public void specialAbility(BattleUnit[] ownTeam, BattleUnit[] enemyTeam) {
        int maxArmorEnemy = 0; 
        int maxArmor = 0; 
        for (int i = 0; i<enemyTeam.length; i++) {
            BattleUnit enemy = enemyTeam[i]; 
            int enemyArmor = enemy.armor();
            int enemyHealth = enemy.health();
            if(enemyHealth > 0 && enemyArmor > maxArmor) { 
                maxArmorEnemy = i; 
                maxArmor = enemyArmor;
            }
        }
        double doubleAttack = this.strength*2; 
        double quarterAttack = this.strength / 4; 
        if (enemyTeam[maxArmorEnemy].armor() > 0) { 
            enemyTeam[maxArmorEnemy].damageArmor((int) doubleAttack);
        } else { 
            enemyTeam[maxArmorEnemy].takeDamage((int) quarterAttack);
        }
    }

    @Override
    public void attack(BattleUnit other) {
        double fullAttack = this.strength;
        double halfAttack = this.strength / 2; 
        double quarterAttack = this.strength / 4; 
        if(fullAttack < 1) fullAttack=1;
        if(halfAttack < 1) halfAttack=1; 
        if(quarterAttack < 1) quarterAttack=1; 
        if (other.armor() > 0){
            other.takeDamage((int) quarterAttack);
            other.damageArmor((int) fullAttack);
        } else {
            other.takeDamage((int) halfAttack);
        }
    }
}
