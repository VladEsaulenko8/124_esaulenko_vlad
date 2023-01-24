package test;

import task.*;


import java.util.function.Supplier;


public class Task_8_Tests {
    private static Task_8 task_8;
    private static String[] classNames;
    private static String[] testNames;
    private static Supplier[] tasks;
    private static ClassValidatorBuilder classValidatorBuilder;
    private static StringBuilder protocol;

    public static void test(Task_8 task, String name) {
        task_8 = task;
        double[] scores_per_task = {
                1, 1, 2, 2, 2
        };
        double score = 0;
        protocol = new StringBuilder();
        String[] subtaskNames = getSubtasks();
        classNames = getClassNames();
        testNames = getTestNames();
        tasks = getTasks();
        classValidatorBuilder = new ClassValidatorBuilder("descriptors");

        for(int i = 0; i < subtaskNames.length; ++i) {
            if(testSubtask(i))
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
            "subtask_1_DynamicPolymorphismProgramming",
            "subtask_2_DynamicPolymorphismProgramming",
            "subtask_3_DynamicPolymorphismProgramming",
            "subtask_4_DynamicPolymorphismProgramming",
            "subtask_5_DynamicPolymorphismProgramming"
        };
    }
    private static String[] getClassNames() {
        return new String[] {
            "BattleUnit",
            "BattleUnitBase",
            "Infantryman",
            "ArmorDestroyer",
            "Alchemist"
        };
    }
    private static String[] getTestNames() {
        return new String[] {
            "task.BattleUnit",
            "task.BattleUnitBase",
            "task.Infantryman",
            "task.ArmorDestroyer",
            "task.Alchemist"
        };
    }

    private static Supplier[] getTasks() {
        return new Supplier[] {
            () -> task_8.subtask_1_DynamicPolymorphismProgramming(),
            () -> task_8.subtask_2_DynamicPolymorphismProgramming(),
            () -> task_8.subtask_3_DynamicPolymorphismProgramming(),
            () -> task_8.subtask_4_DynamicPolymorphismProgramming(),
            () -> task_8.subtask_5_DynamicPolymorphismProgramming()
        };
    }

    private static boolean testSubtask(int number) {
        protocol.append("Тестирование подзадачи ").append(number + 1).append("...\n");
        var res = testTask(tasks[number], testNames[number], number);
        if (res)
            protocol.append("OK\n");
        else
            protocol.append("Ошибка\n");
        protocol.append("\n");
        return res;
    }

    private static boolean testTask(
        Supplier<Class<?>> task,
        String testName,
        int taskNum) {
        var validator = classValidatorBuilder.build(classNames[taskNum]);
        var type = task.get();
        if(type == null)
            return false;
        boolean cTests = true;
        if(!validator.validateAbstractMethods(type)) {
            protocol.append("Ошибка в списке абстрактных методов\n");
            cTests = false;
        }
        if(!validator.validateExtendedClass(type)) {
            protocol.append("Ошибочный базовый класс\n");
            cTests = false;
        }
        if(!validator.validatePublicStaticMethods(type)) {
            protocol.append("Ошибка в списке публичных статических методов\n");
            cTests = false;
        }
        if(!validator.validatePublicInstanceMethods(type)) {
            protocol.append("Ошибка в списке публичных методов экземпляра\n");
            cTests = false;
        }
        if(!validator.validateImplementedInterfaces(type)) {
            protocol.append("Ошибка в списке реализованных интерфейсов\n");
            cTests = false;
        }
        if(!validator.validatePublicConstructorArguments(type)) {
            protocol.append("Ошибка в конструкторах\n");
            cTests = false;
        }
        if(testName == null)
            return cTests;

        var tester = new FunctionalityTester("tests");
        boolean rTests = tester.testClass(testName);
        protocol.append(tester.getProtocol());
        return cTests && rTests;
    }
}
