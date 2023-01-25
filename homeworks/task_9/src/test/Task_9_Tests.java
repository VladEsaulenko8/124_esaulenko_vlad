package test;

import task.*;
import service_project.test.ProtocolBuilder;

import java.util.function.Supplier;


public class Task_9_Tests {
    private static Task_9 task_9;
    private static String[] subtaskNames;
    private static String[] classNames;
    private static String[] testNames;
    private static Supplier[] tasks;
    private static ClassValidatorBuilder classValidatorBuilder;
    private static StringBuilder protocol;

    public static void test(Task_9 task, String name) {
        task_9 = task;
        double[] scores_per_task = {
                1, 4, 3
        };
        double score = 0;
        protocol = new StringBuilder();
        subtaskNames = getSubtasks();
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
            "subtask_1_GenericClassProgramming",
            "subtask_2_GenericClassProgramming",
            "subtask_3_GenericClassProgramming"
        };
    }
    private static String[] getClassNames() {
        return new String[] {
            "DoubleLinkedListItem",
            "DoubleLinkedList",
            "DoubleLinkedListSorter"
        };
    }
    private static String[] getTestNames() {
        return new String[] {
            "task.DoubleLinkedListItem",
            "task.DoubleLinkedList",
            "task.DoubleLinkedListSorter"
        };
    }

    private static Supplier[] getTasks() {
        return new Supplier[] {
            () -> task_9.subtask_1_GenericClassProgramming(),
            () -> task_9.subtask_2_GenericClassProgramming(),
            () -> task_9.subtask_3_GenericClassProgramming()
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

        var tester = new FunctionalityTester();
        boolean rTests = tester.testClass(testName);
        protocol.append(tester.getProtocol());
        return cTests && rTests;
    }
}
