package test;

import task.BattleUnit;
import task.Task_6;
import task.Triangle;
import task.WebAddressParser;


import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;


public class Task_6_Tests {
    private static Task_6 task_6;
    private static String[] subtask_names;

    public static void test(Task_6 task, String name) {
        task_6 = task;
        double[] scores_per_task = {
                1, 2, 2
        };
        double score = 0;
        StringBuilder protocol = new StringBuilder();
        subtask_names = getSubtasks();

        for(int i = 0; i < subtask_names.length; ++i) {
            if(testSubtask(protocol, i))
                score += scores_per_task[i];
        }
        protocol.append("Ваш балл: ");
        protocol.append((int)(score + .0001));
        System.out.println(protocol);

        ProtocolBuilder pb = new ProtocolBuilder();
        pb.BuildProtocol(name, protocol.toString());
    }

    private static String[] getSubtasks() {
        return new String[] {
                "subtask_1_ClassProgramming",
                "subtask_2_ClassProgramming",
                "subtask_3_ClassProgramming"
        };
    }

    private static Method getMethod(String name) {
        var methods = task_6.getClass().getMethods();
        for(var m: methods)
            if(m.getName().equals(name))
                return m;
        return null;
    }

    private static boolean testSubtask(StringBuilder protocol, int number) {
        protocol.append("Тестирование подзадачи ").append(number).append("...\n");
        switch (number) {
            case 0:
                var res0 = testTask0(protocol);
                if(res0)
                    protocol.append("OK\n");
                else
                    protocol.append("Ошибка\n");
                return res0;
            case 1:
                var res1 = testTask1(protocol);
                if(res1)
                    protocol.append("OK\n");
                else
                    protocol.append("Ошибка\n");
                return res1;
            case 2:
                var res2 = testTask2(protocol);
                if(res2)
                    protocol.append("OK\n");
                else
                    protocol.append("Ошибка\n");
                return res2;
        }
        protocol.append("\n");
        return false;
    }

    private static final String[] st_1_publicMethods = {
            "getA",
            "getB",
            "getC",
            "isValid",
            "square",
            "perimeter"
    };
    private static final String[][] st_1_publicConstructorArguments = {
            new String[] {
                    "double", "double", "double"
            }
    };

    private static final String[] st_2_publicMethods = {
            "getStrength",
            "getArmor",
            "getX",
            "getY",
            "getHealth",
            "attacked",
            "moveUp",
            "moveDown",
            "moveLeft",
            "moveRight",
            "isAlive",
    };
    private static final String[][] st_2_publicConstructorArguments = {
            new String[] {
                    "int", "int", "int", "int", "int"
            }
    };

    private static final String[] st_3_publicMethods = {
            "getLogin",
            "getPassword",
            "getScheme",
            "isValid",
            "getPort",
            "getHost",
            "getUrl",
            "getParameters",
            "getFragment",
    };
    private static final String[][] st_3_publicConstructorArguments = {
            new String[] {
                    "java.lang.String"
            }
    };

    private static boolean ConstructorExist(Constructor<?> constructor, String[][] publicConstructorArguments) {
        for(int i = 0; i < publicConstructorArguments.length; ++i) {
            if(Arrays.equals(
                    Arrays.stream(constructor.getParameterTypes()).map(Class::getName).sorted().toArray(),
                    Arrays.stream(publicConstructorArguments[i]).sorted().toArray()
            )) return true;
        }
        return false;
    }

    private static boolean checkClass(
            StringBuilder protocol,
            Class<?> classData,
            String[] publicMethods,
            String[][] publicConstructorArguments,
            Object obj
    ) {
        var methods = classData.getDeclaredMethods();
        var constructors = classData.getDeclaredConstructors();
        var fields = classData.getDeclaredFields();

        boolean r1 = Arrays.stream(methods).allMatch(
                method -> Arrays.stream(publicMethods).anyMatch(
                        st_1_method -> st_1_method.equals(method.getName())
                ) || !method.canAccess(obj)
        );
        boolean r2 = Arrays.stream(publicMethods).allMatch(
                st_1_method -> Arrays.stream(methods).anyMatch(
                        method -> st_1_method.equals(method.getName()) && method.canAccess(obj)
                )
        );
        boolean r3 = Arrays.stream(fields).noneMatch(field -> field.canAccess(obj));
        boolean r4 = Arrays.stream(constructors).allMatch(
                constructor -> ConstructorExist(constructor, publicConstructorArguments)
        ) && constructors.length == publicConstructorArguments.length;

        if(!r1)
            protocol.append("\tЕсть лишние публичные методы\n");
        if(!r2)
            protocol.append("\tНе все публичные методы реализованы\n");
        if(!r3)
            protocol.append("\tЕсть публичные поля\n");
        if(!r4)
            protocol.append("\tЕсть лишние конструкторы\n");

        return r1 && r2 && r3 && r4;
    }

    private static boolean testTask0(StringBuilder protocol) {
        var rClass = checkClass(
                protocol,
                task_6.subtask_1_ClassProgramming(),
                st_1_publicMethods,
                st_1_publicConstructorArguments,
                new Triangle(1, 1, 1)
        );
        boolean rTests = testTask0Functionality(protocol);

        return rClass && rTests;
    }

    private static boolean testTask0Functionality(StringBuilder protocol) {
        boolean[] results = new boolean[] {
                testObject0(new Triangle(-2, 4, 5), -2, 4, 5),
                testObject0(new Triangle(2, -4, 5), 2, -4, 5),
                testObject0(new Triangle(2, 4, -5), 2, 4, -5),
                testObject0(new Triangle(-2, -4, 5), -2, -4, 5),
                testObject0(new Triangle(-2, 4, -5), -2, 4, -5),
                testObject0(new Triangle(2, -4, -5), 2, -4, -5),
                testObject0(new Triangle(-2, -4, -5), -2, -4, -5),
                testObject0(new Triangle(0, 4, 5), 0, 4, 5),
                testObject0(new Triangle(2, 0, 5), 2, 0, 5),
                testObject0(new Triangle(2, 4, 0), 2, 4, 0),
                testObject0(new Triangle(0, 0, 5), 0, 0, 5),
                testObject0(new Triangle(0, 4, 0), 0, 4, 0),
                testObject0(new Triangle(2, 0, 0), 2, 0, 0),
                testObject0(new Triangle(0, 0, 0), 0, 0, 0),
                testObject0(new Triangle(2, 4, 5), 2, 4, 5),
                testObject0(new Triangle(4, 4, 2), 4, 4, 2),
                testObject0(new Triangle(1, 2, 3), 1, 2, 3)
        };
        boolean res = true;
        for(int i = 0; i < results.length; ++i) {
            if(results[i])
                protocol.append("\tТест ").append(i + 1).append(" ОК\n");
            else {
                protocol.append("\tТест ").append(i + 1).append(" Ошибка\n");
                res = false;
            }
        }
        return res;
    }

    private static boolean testObject0(Triangle triangle, int a, int b, int c) {
        double P = a + b + c;
        double p = P / 2;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return triangle.getA() == a
            && triangle.getB() == b
            && triangle.getC() == c
            && triangle.isValid() == (a > 0 && b > 0 && c > 0 && a + b > c && a + c > b && b + c > a)
            && triangle.isValid() ? Math.abs(triangle.square() - s) < .00001 : triangle.square() == -1
            && triangle.isValid() ? Math.abs(triangle.perimeter() - P) < .00001 : triangle.perimeter() == -1;
    }

    private static boolean testTask1(StringBuilder protocol) {
        var rClass = checkClass(
                protocol,
                task_6.subtask_2_ClassProgramming(),
                st_2_publicMethods,
                st_2_publicConstructorArguments,
                new BattleUnit(0, 0, 0, 0, 0)
        );
        boolean rTests = testTask1Functionality(protocol);

        return rClass && rTests;
    }

    private static boolean testTask1Functionality(StringBuilder protocol) {
        boolean[] results = new boolean[]{
            testObject1(new BattleUnit(5, 2, 10, 0, 0), 5, 2, 10, 0, 0, new BattleUnit(4, 2, 10, 0, 0), 2),
            testObject1(new BattleUnit(5, -2, 10, 0, 0), 5, -2, 10, 0, 0, new BattleUnit(4, 2, 10, 0, 0), 4),
            testObject1(new BattleUnit(5, 2, -10, 0, 0), 5, 2, -10, 0, 0, new BattleUnit(4, 2, 10, 0, 0), 2),
            testObject1(new BattleUnit(5, 2, 10, 0, 0), 5, 2, 10, 0, 0, new BattleUnit(1, 2, 10, 0, 0), 0),
            testObject1(new BattleUnit(5, 2, 10, 0, 0), 5, 2, 10, 0, 0, new BattleUnit(4, 2, 10, 0, 0), 2)
        };
        boolean res = true;
        for(int i = 0; i < results.length; ++i) {
            if(results[i])
                protocol.append("\tТест ").append(i + 1).append(" ОК\n");
            else {
                protocol.append("\tТест ").append(i + 1).append(" Ошибка\n");
                res = false;
            }
        }
        return res;
    }

    private static boolean testObject1(
        BattleUnit battleUnit, int strength, int armor, int health, int x, int y, BattleUnit enemy, int validDamage
    ) {
        boolean r1 = battleUnit.getArmor() == armor
            && battleUnit.isAlive() == (health > 0)
            && battleUnit.getStrength() == strength
            && battleUnit.getX() == x
            && battleUnit.getY() == y
            && battleUnit.getHealth() == health;

        battleUnit.attacked(enemy);
        boolean r2 = battleUnit.getArmor() == armor
            && battleUnit.isAlive() == (health - validDamage > 0)
            && battleUnit.getStrength() == strength
            && battleUnit.getX() == x
            && battleUnit.getY() == y
            && battleUnit.getHealth() == health - validDamage;

        battleUnit.attacked(enemy);
        battleUnit.attacked(enemy);
        boolean r3 = battleUnit.getArmor() == armor
            && battleUnit.isAlive() == (health - 3 * validDamage > 0)
            && battleUnit.getStrength() == strength
            && battleUnit.getX() == x
            && battleUnit.getY() == y
            && battleUnit.getHealth() == health - 3 * validDamage;
        battleUnit.moveUp();
        battleUnit.moveLeft();
        boolean r4 = battleUnit.getArmor() == armor
            && battleUnit.isAlive() == (health - 3 * validDamage > 0)
            && battleUnit.getStrength() == strength
            && battleUnit.getX() == x - 1
            && battleUnit.getY() == y - 1
            && battleUnit.getHealth() == health - 3 * validDamage;
        battleUnit.moveDown();
        battleUnit.moveDown();
        battleUnit.moveRight();
        battleUnit.moveRight();

        boolean r5 = battleUnit.getArmor() == armor
            && battleUnit.isAlive() == (health - 3 * validDamage > 0)
            && battleUnit.getStrength() == strength
            && battleUnit.getX() == x + 1
            && battleUnit.getY() == y + 1
            && battleUnit.getHealth() == health - 3 * validDamage;

        return r1 && r2 && r3 && r4 && r5;
    }

    private static boolean testTask2(StringBuilder protocol) {
        var rClass = checkClass(
                protocol,
                task_6.subtask_3_ClassProgramming(),
                st_3_publicMethods,
                st_3_publicConstructorArguments,
                new WebAddressParser("")
        );
        boolean rTests = testTask2Functionality(protocol);

        return rClass && rTests;
    }

    private static boolean testTask2Functionality(StringBuilder protocol) {
        var args_1 = new HashMap<String, String>();
        args_1.put("access", "private");
        args_1.put("link", "global");
        var args_2 = new HashMap<String, String>();
        args_2.put("account", "guest");

        boolean[] results = new boolean[]{
            testObject2(new WebAddressParser("dfgvslksdfmvsdkg"), "", "", "", false, "", "", "", new HashMap<>(), ""),
            testObject2(new WebAddressParser("https://ru.wikipedia.org/wiki/URL#Структура_URL"), "", "", "https", true, "ru.wikipedia.org", "", "wiki/URL", new HashMap<>(), "Структура_URL"),
            testObject2(new WebAddressParser("ftp://user:pass@test.ru:8000/data/noname?access=private&link=global"), "user", "pass", "ftp", true, "test.ru", "8000", "data/noname", args_1, ""),
            testObject2(new WebAddressParser("http://my_site.local.com:8888/public?account=guest#Manual"), "", "", "http", true, "my_site.local.com", "8888", "public", args_2, "Manual")
        };
        boolean res = true;
        for(int i = 0; i < results.length; ++i) {
            if(results[i])
                protocol.append("\tТест ").append(i + 1).append(" ОК\n");
            else {
                protocol.append("\tТест ").append(i + 1).append(" Ошибка\n");
                res = false;
            }
        }
        return res;
    }

    private static boolean testObject2(
        WebAddressParser webAddressParser,
        String login,
        String password,
        String scheme,
        boolean valid,
        String host,
        String port,
        String url,
        HashMap<String, String> params,
        String fragment
    ) {
        return webAddressParser.isValid() == valid
            && webAddressParser.getFragment().equals(fragment)
            && webAddressParser.getHost().equals(host)
            && webAddressParser.getPassword().equals(password)
            && webAddressParser.getLogin().equals(login)
            && webAddressParser.getScheme().equals(scheme)
            && webAddressParser.getUrl().equals(url)
            && webAddressParser.getPort().equals(port)
            && mapIsEquals(params, webAddressParser.getParameters());
    }

    private static boolean mapIsEquals(HashMap<String, String> map1, HashMap<String, String> map2) {
        for (var key: map1.keySet()) {
            if(!map2.containsKey(key) || !map1.get(key).equals(map2.get(key)))
                return false;
        }
        for (var key: map2.keySet()) {
            if(!map1.containsKey(key) || !map2.get(key).equals(map1.get(key)))
                return false;
        }
        return true;
    }
}
