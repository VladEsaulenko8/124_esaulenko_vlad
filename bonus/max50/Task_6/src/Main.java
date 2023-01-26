import java.util.HashMap;
import java.util.Set;

// Создать класс SummCalculator.
// В классе определить метод calculateSumm, принимающий два аргуманта:
// HashMap<String, Integer> - словарь, где ключ - это название товара,  а значение
//                          - количество единиц данного товара в покупке;
// HashMap<String, Integer> - словарь, где ключ - это название товара,  а значение
//                          - стоимость одной единицы товара.
// Метод должен возвращать сумму всей покупки.
// (5 баллов)


public class Main {
    // чистит консоль
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    public static void main(String[] args) throws Exception {
        HashMap<String, Integer> goods = new HashMap<>();
        goods.put("Трусы Новогодние", 12);
        goods.put("Колпак Деда Мороза", 20);
        goods.put("Парик Снегурочки", 2);
        goods.put("Чулки Новгодние На Камин", 3);
        
        HashMap<String, Integer> costs = new HashMap<>();
        costs.put("Трусы Новогодние", 500);
        costs.put("Колпак Деда Мороза", 200);
        costs.put("Парик Снегурочки", 800);
        costs.put("Чулки Новгодние На Камин", 450);

        System.out.printf("Список товаров/Стоимость: %n %n");
        Set<HashMap.Entry<String,Integer>> set1 = costs.entrySet();
        for(HashMap.Entry<String,Integer> k:set1){
            System.out.println(k.getKey()+": "+k.getValue());
        }

        System.out.printf("%nСписок товаров/Количество: %n %n");
        Set<HashMap.Entry<String,Integer>>set2 = goods.entrySet();
        for(HashMap.Entry<String,Integer> k: set2){
            System.out.println(k.getKey()+": " + k.getValue());
        }


        SummCalculator sum = new SummCalculator();
        int result;
        result = sum.calculateSumm(goods,costs);
        System.out.printf("%nСумма к оплате: " + result);

    }
}
