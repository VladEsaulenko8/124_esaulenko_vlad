package task;

import test.Task_7_Tests;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите свое фамилию и имя");
        String name = scanner.nextLine();
        Task_7 task = new Task_7();
        Task_7_Tests.test(task, name);
    }
}
