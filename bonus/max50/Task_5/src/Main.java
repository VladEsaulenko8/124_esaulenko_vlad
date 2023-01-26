import java.util.HashMap;
import java.util.Random;

// Создать класс Counter.
// В классе определить метод count, принимающий в качестве  аргумента массив целых
// чисел и возвращающий HashMap<Integer, Integer>, который содержыт счетчики вхож-
// дений каждого числа в массив.
// Пример:
// [1, 2, 3, 3, 1, 5] -> {1: 2, 2: 1, 3: 2, 5: 1}
// (5 баллов)

public class Main {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        int[] arr = new int[5];
        HashMap<Integer,Integer> hm = new HashMap<>();
        Counter b = new Counter();

        System.out.print("Массив: ");
        for(int i = 0; i < arr.length; i++){
            arr[i] =  random.nextInt(1, 10);
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        hm = b.count(arr);
        System.out.println(hm);
    }
}


