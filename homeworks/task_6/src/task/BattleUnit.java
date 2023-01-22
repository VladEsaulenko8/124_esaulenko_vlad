package task;

public class BattleUnit {

    private int force, armor, health, x, y;
    
    public BattleUnit(int force, int armor, int health, int x, int y){
        this.force = force;
        this.armor = armor;
        this.health = health;
        this.x = x;
        this.y = y;
    }

    public int getStrength(){
        return force;
    }
    public int getArmor(){
        return armor;
    }
    public int getHealth(){
        return health;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
    // урон = сила_атакующего - броня_атакуемого
    public void attacked(BattleUnit enemy){
        if (armor > 0) {
            int damage;
            if (enemy.force >= armor) {
                damage = enemy.force - armor;
                health -= damage;
            } else {
                health = health - 0;
            }
        } else {health -= enemy.force;}
            }

    // изменение координат
    public void moveUp() {
        y--;
    }
    public void moveDown() {
        y++;
    }
    public void moveLeft() {
        x--;
    }
    public void moveRight() {
        x++;
    }
    public  boolean isAlive(){
        return health > 0;
    }
}
