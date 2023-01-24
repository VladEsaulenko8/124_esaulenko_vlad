package test;

import task.*;

public class FunctionalityTester {
    private String descriptorsPath;
    private StringBuilder protocol;
    private int testNum;
    private boolean allOk;

    public FunctionalityTester(String descriptorsPath) {
        this.descriptorsPath = descriptorsPath;
        protocol = new StringBuilder();
    }

    public String getProtocol() {
        return protocol.toString();
    }

    public boolean testClass(String className) {
        protocol = new StringBuilder();
        testNum = 1;
        allOk = true;
        return switch (className) {
            case "task.BattleUnit", "task.BattleUnitBase" -> true;

            //Раскомментируйте строку ниже, если сделали подзадачу 3
            case "task.Infantryman" -> testInfantryman();

            //Раскомментируйте строку ниже, если сделали подзадачу 4
            case "task.ArmorDestroyer" -> testArmorDestroyer();

            //Раскомментируйте строку ниже, если сделали подзадачу 5
            case "task.Alchemist" -> testAlchemist();

            default -> false;
        };
    }

    void writeProto(boolean testRes) {
        allOk = allOk && testRes;
        protocol.append("\tТест ").append(testNum++).append(": ").append(testRes ? "ОК\n" : "Ошибка\n");
    }


    //Раскомментируйте следующий метод, если сделали подзадачу 3

    private boolean testInfantryman() {
        Infantryman infantryman = new Infantryman("inf 1", 100, 20, 50);
        writeProto(infantryman.name().equals("inf 1")); //1
        writeProto(infantryman.armor() == 50); //2
        writeProto(infantryman.maxArmor() == 50); //3
        writeProto(infantryman.health() == 100); //4
        writeProto(infantryman.maxHealth() == 100); //5
        writeProto(infantryman.strength() == 20); //6
        writeProto(infantryman.baseStrength() == 20); //7

        infantryman.setMaxHealth(150);
        writeProto(infantryman.health() == 100); //8
        writeProto(infantryman.maxHealth() == 150); //9

        infantryman.setMaxHealth(80);
        writeProto(infantryman.health() == 80); //10
        writeProto(infantryman.maxHealth() == 80); //11

        infantryman.setMaxHealth(100);
        infantryman.heal(10);
        writeProto(infantryman.health() == 90); //12
        writeProto(infantryman.maxHealth() == 100);//13
        infantryman.heal(20);
        writeProto(infantryman.health() == 100); //14
        writeProto(infantryman.maxHealth() == 100); //15

        infantryman.takeDamage(20);
        writeProto(infantryman.health() == 80); //16
        writeProto(infantryman.maxHealth() == 100); //17
        infantryman.takeDamage(90);
        writeProto(infantryman.health() == 0); //18
        writeProto(infantryman.maxHealth() == 100); //19

        infantryman.setStrength(10);
        writeProto(infantryman.baseStrength() == 20); //20
        writeProto(infantryman.strength() == 10); //21
        infantryman.setStrength(30);
        writeProto(infantryman.baseStrength() == 20); //22
        writeProto(infantryman.strength() == 30); //23

        infantryman.setMaxArmor(40);
        writeProto(infantryman.maxArmor() == 40); //24
        writeProto(infantryman.armor() == 40); //25
        infantryman.setMaxArmor(50);
        writeProto(infantryman.maxArmor() == 50); //26
        writeProto(infantryman.armor() == 40); //27

        infantryman.damageArmor(30);
        writeProto(infantryman.maxArmor() == 50); //28
        writeProto(infantryman.armor() == 10); //29
        infantryman.damageArmor(20);
        writeProto(infantryman.maxArmor() == 50); //30
        writeProto(infantryman.armor() == 0); //31

        infantryman.restoreArmor(30);
        writeProto(infantryman.maxArmor() == 50); //32
        writeProto(infantryman.armor() == 30); //33
        infantryman.restoreArmor(30);
        writeProto(infantryman.maxArmor() == 50); //34
        writeProto(infantryman.armor() == 50); //35

        infantryman = new Infantryman("inf 1", 100, 20, 50);
        Infantryman enemy = new Infantryman("enemy", 100, 10, 50);
        infantryman.attack(enemy);
        writeProto(enemy.health() == 90); //36
        writeProto(enemy.armor() == 45); //37

        infantryman = new Infantryman("inf 1", 100, 11, 50);
        enemy = new Infantryman("enemy", 100, 10, 50);
        infantryman.attack(enemy);
        writeProto(enemy.health() == 95); //38
        writeProto(enemy.armor() == 48); //39

        infantryman = new Infantryman("inf 1", 100, 1, 50);
        enemy = new Infantryman("enemy", 100, 10, 50);
        infantryman.attack(enemy);
        writeProto(enemy.health() == 99); //40
        writeProto(enemy.armor() == 49); //41

        return allOk;
    }


    //Раскомментируйте следующий метод, если сделали подзадачу 4

    private boolean testArmorDestroyer() {
        ArmorDestroyer ad = new ArmorDestroyer("ad 1", 100, 20, 50);
        Infantryman enemy = new Infantryman("enemy", 100, 10, 50);
        ad.attack(enemy);
        writeProto(enemy.health() == 95); //1
        writeProto(enemy.armor() == 30); //2

        ad = new ArmorDestroyer("ad 1", 100, 11, 50);
        enemy = new Infantryman("enemy", 100, 10, 50);
        ad.attack(enemy);
        writeProto(enemy.health() == 98); //3
        writeProto(enemy.armor() == 39); //4

        ad = new ArmorDestroyer("ad 1", 100, 1, 50);
        enemy = new Infantryman("enemy", 100, 10, 50);
        ad.attack(enemy);
        writeProto(enemy.health() == 99); //5
        writeProto(enemy.armor() == 49); //6

        BattleUnit[] enemies = new BattleUnit[] {
            new Infantryman("enemy 1", 100, 10, 50), //0
            new Infantryman("enemy 2", 100, 10, 45), //1
            new Infantryman("enemy 3", 100, 10, 55)  //2
        };

        ad = new ArmorDestroyer("ad 1", 100, 20, 50);
        BattleUnit[] ownTeam = new BattleUnit[] {
            ad
        };
        ad.specialAbility(ownTeam, enemies);
        writeProto(enemies[2].armor() == 15); //7
        writeProto(enemies[2].health() == 100); //8
        writeProto(enemies[0].armor() == 50); //9
        writeProto(enemies[0].health() == 100); //10
        writeProto(enemies[1].armor() == 45); //11
        writeProto(enemies[1].health() == 100); //12

        enemies = new BattleUnit[] {
            new Infantryman("enemy 1", 100, 10, 50), //0
            new Infantryman("enemy 2", 100, 10, 45),
            new Infantryman("enemy 3", 0, 10, 55)
        };

        ad = new ArmorDestroyer("ad 1", 100, 20, 50);
        ownTeam = new BattleUnit[] {
            ad
        };
        ad.specialAbility(ownTeam, enemies);
        writeProto(enemies[2].armor() == 55); //13
        writeProto(enemies[2].health() == 0); //14
        writeProto(enemies[0].armor() == 10); //15
        writeProto(enemies[0].health() == 100); //16
        writeProto(enemies[1].armor() == 45); //17
        writeProto(enemies[1].health() == 100); //18

        enemies = new BattleUnit[] {
            new Infantryman("enemy 1", 100, 10, 0),
            new Infantryman("enemy 2", 100, 10, 0)
        };

        ad = new ArmorDestroyer("ad 1", 100, 20, 50);
        ownTeam = new BattleUnit[] {
            ad
        };
        ad.specialAbility(ownTeam, enemies);
        writeProto(enemies[0].armor() == 0); //19
        writeProto(enemies[1].armor() == 0); //20
        writeProto((enemies[0].health() == 95 || enemies[1].health() == 95) &&
                  !(enemies[0].health() == 95 && enemies[1].health() == 95) //21
        );


        return allOk;
    }




    //Раскомментируйте следующий метод, если сделали подзадачу 5
    private boolean testAlchemist() {
        Alchemist alchemist = new Alchemist("al 1", 100, 10, 20);
        Infantryman enemy = new Infantryman("inf 1", 100, 10, 20);

        alchemist.attack(enemy);
        writeProto(enemy.strength() == 8); //1
        writeProto(enemy.maxHealth() == 96); //2
        writeProto(enemy.armor() == 20); //3

        enemy = new Infantryman("inf 1", 100, 1, 20);
        alchemist.attack(enemy);
        writeProto(enemy.strength() == -1); //4
        writeProto(enemy.maxHealth() == 96); //5
        writeProto(enemy.armor() == 20); //6

        enemy.attack(alchemist);
        writeProto(alchemist.health() == 99); //7

        alchemist = new Alchemist("al 1", 80, 10, 20);
        BattleUnit[] enemies = new BattleUnit[] {
            enemy
        };
        BattleUnit[] own = new BattleUnit[] {
            alchemist,
            new Infantryman("i1", 80, 10, 20),
            new Infantryman("i2", 100, 10, 20),
            new Infantryman("i3", 70, 10, 20)
        };
        own[3].takeDamage(20);
        alchemist.specialAbility(own, enemies);
        writeProto(own[0].health() == 80); //8
        writeProto(own[1].health() == 80); //9
        writeProto(own[2].health() == 100); //10
        writeProto(own[3].health() == 60); //11 (80-20+10)

        writeProto(own[0].strength() == 10); //12
        writeProto(own[1].strength() == 10); //13
        writeProto(own[2].strength() == 11); //14
        writeProto(own[3].strength() == 10); //15

        own = new BattleUnit[] {
            alchemist,
            new Infantryman("i1", 90, 10, 20),
            new Infantryman("i2", 0, 10, 20),
            new Infantryman("i3", 0, 10, 20)
        };
        own[0].takeDamage(20); //алхимик получает 20 урона???
        alchemist.specialAbility(own, enemies);
        writeProto(own[0].health() == 70); //16 (80 - 20 + 10 = 70)
        writeProto(own[1].health() == 90); //17
        writeProto(own[2].health() == 0); //18 //мертв
        writeProto(own[3].health() == 0); //19 //мертв

        writeProto(own[0].strength() == 10); //20
        writeProto(own[1].strength() == 11); //21
        writeProto(own[2].strength() == 10); //22
        writeProto(own[3].strength() == 10); //23

        return allOk;
    }
}
