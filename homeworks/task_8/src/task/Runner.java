package task;

import test.Task_8_Tests;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите свое фамилию и имя");
        String name = scanner.nextLine();
        Task_8 task = new Task_8();
        Task_8_Tests.test(task, name);
    }
}
