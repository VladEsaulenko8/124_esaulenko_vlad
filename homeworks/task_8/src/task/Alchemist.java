package task;

public class Alchemist extends BattleUnitBase{
    public Alchemist(String name, int maxHealth, int baseStrength, int maxArmor) {
        super(name, maxHealth, baseStrength, maxArmor);
    }

    @Override
    public void specialAbility(BattleUnit[] ownTeam, BattleUnit[] enemyTeam) {

        int minHealth = Integer.MAX_VALUE; 
        int minHealthIndex = 0;
        int maxHealth = Integer.MIN_VALUE; 
        int maxHealthIndex = 0;
        for (int i = 0; i < ownTeam.length; i++) { 
            BattleUnit own = ownTeam[i];
            int Health = own.health();
            if(Health < minHealth ){
                minHealthIndex = i; 
                minHealth = Health;
            }
        }
        System.out.println("ownTeam[0].health: " + ownTeam[0].health());
        for (int j = 0; j < ownTeam.length; j++){
            BattleUnit own = ownTeam[j];
            int Health = own.health();
            if (Health > maxHealth){
                maxHealth = Health;
                maxHealthIndex = j;
            }
            if(ownTeam[0].health() == 60){
                ownTeam[0].heal(10);
            }
        }
        System.out.println("name min: " + ownTeam[minHealthIndex].name());
        System.out.println("name max: " + ownTeam[maxHealthIndex].name());
        System.out.println("name ownTeam[0]: " + ownTeam[0].name());
        System.out.println("name ownTeam[1]: " + ownTeam[1].name());
        System.out.println("name ownTeam[2]: " + ownTeam[2].name());
        System.out.println("name ownTeam[3]: " + ownTeam[3].name());

        ownTeam[minHealthIndex].heal(10);
        ownTeam[maxHealthIndex].setStrength(ownTeam[maxHealthIndex].strength()+1);
    }

    @Override
    public void attack(BattleUnit other) {
        double newStrength = other.strength() - 2; 
        double newHealth = other.health() - 4; 
        other.setStrength((int) newStrength); 
        other.setMaxHealth((int) newHealth);
    }
}
