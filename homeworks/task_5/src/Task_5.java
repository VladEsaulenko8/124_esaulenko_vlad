import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Task_5 implements Task_5_base {
    @Override
    public ArrayList<Integer> subtask_1_ArrayList(ArrayList<Integer> data, int k, int n) {
        // Выбрать из данного списка элементы, кратные k, но не кратные n
        // и сформировать из них новый список.
        // Вернуть новый список в качестве результата
        ArrayList<Integer> list = new ArrayList<>();
        for(Integer i : data){
            if((i%k == 0) && (i%n != 0))
                list.add(i);
        }
        return list;
    }

    @Override
    public ArrayList<Integer> subtask_2_ArrayList(int size) {
        // сгенерировать и вернуть список размера size,
        // содержащий первые size элементов последовательности, описанной в
        // задаче subtask_2_for задания task_3
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            int n = 0;
            if(list.size() < size)
                while(n < i && list.size() < size) {
                    list.add(i);
                    n++;
                }
        }
        return list;
    }

    @Override
    public HashSet<Integer> subtask_3_HashSet(HashSet<Integer> s1, HashSet<Integer> s2) {
        // найдите пересечение множеств s1 и s2
        HashSet<Integer> set = new HashSet<>(s1);
        set.retainAll(s2);
        return set;
    }

    @Override
    public HashSet<Integer> subtask_4_HashSet(HashSet<Integer> s1, HashSet<Integer> s2) {
        // найдите объединение множеств s1 и s2
        HashSet<Integer> set = new HashSet<>();
        set.addAll(s1);
        set.addAll(s2);
        return set;
    }

    @Override
    public HashSet<Integer> subtask_5_HashSet(HashSet<Integer> s1, HashSet<Integer> s2) {
        // найдите дополнение множества s1 до множества s2
        HashSet<Integer> set = new HashSet<>();
        set.addAll(s1);
        set.addAll(s2);
        set.removeAll(s1);
        return set;
    }

    @Override
    public HashSet<Integer> subtask_6_HashSet(HashSet<Integer> s1, HashSet<Integer> s2) {
        // постройте множество, содержащее элементы, содержащиеся либо только в s1,
        // либо только в s2, но не в обоих множествах одновременно
        HashSet<Integer> set = new HashSet<>();
        for(Integer i : s1) {
            if (s2.contains(i)) {
                continue;
            } else {
                set.add(i);
            }
        }
        for(Integer i : s2) {
            if (s1.contains(i)) {
                continue;
            } else {
                set.add(i);
            }
        }
        return set;
    }

    @Override
    public HashMap<String, Double> subtask_7_HashMap(ArrayList<String> data) {
        // Дан список строк. Построить словарь, содержащий частоты слов
        // для данного списка в процентах
        HashMap<String, Double> map = new HashMap<>();
        int count = 0;
        for(String i : data){
            String[] words = i.split(" ");
            for(String j : words){
                if(map.containsKey(j)) {
                    map.put(j, map.get(j) + 1);
                } else
                    map.put(j, 1.0);
                count++;
            }
        }
        for(HashMap.Entry <String, Double> entry : map.entrySet()){
            double pr = (entry.getValue()*100)/count;
            entry.setValue(pr);
        }
        return map;
    }


    @Override
    public HashMap<String, Double> subtask_8_HashMap(ArrayList<Double> data) {
        // Дан список чисел. Сформировать словарь, содержащий среднее,
        // максимальное и минимальное значения из данного списка. Ключи
        // словаря "mean", "max", "min" соответственно
        double max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, sum = 0;
        for(Double i : data){
            sum += i;
            if(i > max) {
                max = i;
            } if(i < min) {
                min = i;
            }
        }
        HashMap<String, Double> map = new HashMap<>();
        map.put("mean", sum/data.size());
        map.put("max", max);
        map.put("min", min);
        return map;
    }
}