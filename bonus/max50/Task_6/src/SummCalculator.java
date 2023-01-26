import java.util.HashMap;
import java.util.Set;

public class SummCalculator {
    public int calculateSumm(HashMap<String,Integer> goods,HashMap<String,Integer> costs) {
        int sum = 0;
        Set<HashMap.Entry<String,Integer>> set1 = goods.entrySet();


        System.out.printf("%nЧек: %n");
        for(HashMap.Entry<String,Integer> me:set1){
            int count = me.getValue();
            if(goods.containsKey(me.getKey())){
                int s = costs.get(me.getKey());
                System.out.println("Кол-во: " + count + ";" + " Цена за 1 шт: " + s + ";");
                int result = count * s;
                sum = sum + result;
            }
        }
        return sum;
    }
}
