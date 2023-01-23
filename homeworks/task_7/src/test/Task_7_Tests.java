package test;

import task.Direction;
import task.Race;
import task.Task_7;
import service_project.test.ProtocolBuilder;
import task.Vector2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;


public class Task_7_Tests {
    private static Task_7 task_7;
    private static String[] subtask_names;

    public static void test(Task_7 task, String name) {
        task_7 = task;
        double[] scores_per_task = {
                2, 2
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
                "subtask_1_EnumProgramming",
                "subtask_2_EnumProgramming"
        };
    }

    private static Method getMethod(String name) {
        var methods = task_7.getClass().getMethods();
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
        }
        protocol.append("\n");
        return false;
    }

    private static final String[] st_1_publicMethods = {
        "values",
        "valueOf",
        "move"
    };
    private static final String[] st_1_publicFields = {
        "North",
        "West",
        "South",
        "East"
    };

    private static final String[] st_2_publicMethods = {
        "values",
        "valueOf",
        "strength",
        "health",
        "dexterity",
    };
    private static final String[] st_2_publicFields = {
        "Orc",
        "Elf",
        "Dwarf",
        "Halfling",
        "Human"
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
            String[] publicFields
    ) {
        var methods = classData.getDeclaredMethods();
        var fields = classData.getDeclaredFields();

        boolean r1 = Arrays.stream(methods).allMatch(
                method -> Arrays.stream(publicMethods).anyMatch(
                        st_1_method -> st_1_method.equals(method.getName())
                ) || Modifier.isPrivate(method.getModifiers())
        );
        boolean r2 = Arrays.stream(publicMethods).allMatch(
                st_1_method -> Arrays.stream(methods).anyMatch(
                        method -> st_1_method.equals(method.getName()) && Modifier.isPublic(method.getModifiers())
                )
        );
        boolean r3 = Arrays.stream(fields).allMatch(
            field -> Arrays.stream(publicFields).anyMatch(
                st_1_field -> st_1_field.equals(field.getName())
            ) || Modifier.isPrivate(field.getModifiers())
        );
        boolean r4 = Arrays.stream(publicFields).allMatch(
            st_1_field -> Arrays.stream(fields).anyMatch(
                field -> st_1_field.equals(field.getName()) && Modifier.isPublic(field.getModifiers())
            )
        );

        if(!r1)
            protocol.append("\tЕсть лишние публичные методы\n");
        if(!r2)
            protocol.append("\tНе все публичные методы реализованы\n");
        if(!r3)
            protocol.append("\tЕсть лишние публичные поля\n");
        if(!r4)
            protocol.append("\tНе все публичные поля реализованы\n");

        return r1 && r2 && r3 && r4;
    }

    private static boolean testTask0(StringBuilder protocol) {
        var rClass = checkClass(
            protocol,
            task_7.subtask_1_EnumProgramming(),
            st_1_publicMethods,
            st_1_publicFields
        );
        boolean rTests = testTask0Functionality(protocol);

        return rClass && rTests;
    }

    private static boolean testTask0Functionality(StringBuilder protocol) {
        boolean[] results = new boolean[] {
                testObject0(Direction.North),
                testObject0(Direction.West),
                testObject0(Direction.South),
                testObject0(Direction.East)
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

    private static boolean testObject0(Direction direction) {
        Vector2 start = new Vector2(2.5, 4.5);
        double distance = 6.3;
        Vector2 res = direction.move(start, distance);

        return switch (direction) {
            case West -> Math.abs(res.getX() + 3.8) < .0001 && Math.abs(res.getY() - 4.5) < .0001;
            case East -> Math.abs(res.getX() - 8.8) < .0001 && Math.abs(res.getY() - 4.5) < .0001;
            case North -> Math.abs(res.getX() - 2.5) < .0001 && Math.abs(res.getY() + 1.8) < .0001;
            case South -> Math.abs(res.getX() - 2.5) < .0001 && Math.abs(res.getY() - 10.8) < .0001;
        };
    }

    private static boolean testTask1(StringBuilder protocol) {
        var rClass = checkClass(
            protocol,
            task_7.subtask_2_EnumProgramming(),
            st_2_publicMethods,
            st_2_publicFields
        );
        boolean rTests = testTask1Functionality(protocol);

        return rClass && rTests;
    }

    private static boolean testTask1Functionality(StringBuilder protocol) {
        boolean[] results = new boolean[]{
            testObject1(Race.Dwarf),
            testObject1(Race.Elf),
            testObject1(Race.Orc),
            testObject1(Race.Halfling),
            testObject1(Race.Human)
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

    private static boolean testObject1(Race race) {
        return switch (race) {
            case Orc ->      race.strength() == 6 && race.health() == 12 && race.dexterity() == 2;
            case Elf ->      race.strength() == 2 && race.health() == 9  && race.dexterity() == 9;
            case Dwarf ->    race.strength() == 6 && race.health() == 10 && race.dexterity() == 4;
            case Halfling -> race.strength() == 1 && race.health() == 8  && race.dexterity() == 11;
            case Human ->    race.strength() == 6 && race.health() == 7  && race.dexterity() == 7;
        };
    }
}
