// Создать класс Student.
// В данном классе определить поля name (строка), age (целое),  group (строка),
// mean_score (действительное).
// Определить конструктор, задающий все поля соответствующими аргументами.
// Определить методы-аксессоры на чтение и запись для всех полей.
// (2 балла)

public class Student {
    public static void main(String[] args) throws Exception {
        // имя
        Human human1 = new Human();
        human1.setName("Violett Patrusheva");
        human1.printName();
        // группа
        human1.setGroup("Information systems and technologies");
        human1.printGroup();
        // возраст
        human1.setAge(21);
        human1.printAge();
        // средний балл
        human1.setMS(5);
        human1.printMS();
    }
}

// this требуется для того, чтобы метод мог сослаться на вызвавший его объект
class Human {
    // создаем имя
    String name;

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    void printName() {
        System.out.printf("Name: %s %n", name);
    }

    // создаем группа
    String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String g) {
        this.group = g;
    }

    void printGroup() {
        System.out.printf("Group: %s %n", group);
    }

    // возраст
    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int a) {
        this.age = a;
    }

    void printAge() {
        System.out.printf("Age: %d %n", age);
    }

    // mean_score
    float mean_score;

    public float getMS() {
        return mean_score;
    }

    public void setMS(float ms) {
        this.mean_score = ms;
    }

    void printMS() {
        System.out.printf("Mean Score: %f %n", mean_score);
    }
}