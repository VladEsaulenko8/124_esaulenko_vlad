package task;

import test.Task_9_Tests;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите свое фамилию и имя");
        String name = scanner.nextLine();
        Task_9 task = new Task_9();
        Task_9_Tests.test(task, name);
    }
}
