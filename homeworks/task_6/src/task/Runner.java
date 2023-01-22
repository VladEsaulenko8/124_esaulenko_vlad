package task;

import test.Task_6_Tests;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите свое фамилию и имя");
        String name = scanner.nextLine();
        Task_6 task = new Task_6();
        Task_6_Tests.test(task, name);
    }
}
