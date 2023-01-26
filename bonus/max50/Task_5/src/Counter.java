import java.util.HashMap;

public class Counter {
    public HashMap<Integer,Integer> count(int[] array){
        HashMap<Integer,Integer> x = new HashMap<>();
        System.out.print("Хэш-карта: ");
        for(int i = 0; i < array.length; i++){
            if(x.containsKey(array[i])){
                int k = x.get(array[i]) + 1;
                x.put(array[i], k);
            }
            else{
                x.put(array[i],1);
            }
        }
        return x; 
    }
}
