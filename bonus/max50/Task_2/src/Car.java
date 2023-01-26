// Создать класс Car.
// В данном классе определить поля name (строка), brand (строка),  year (целое),
// total_distance (целое).
// Определить конструктор, задающий все поля соответствующими аргументами.
// Определить методы-аксессоры на чтение и запись для всех полей.
// Определить метод trip, принимающий в качестве аргумента расстояние и увеличива-
// ющий суммарный пробег на данное расстояние.
// (3 балла)

public class Car {
    public static void main(String[] args) throws Exception {
        Information inf1 = new Information();
        // назначали переменные
        inf1.setName("Chery");
        inf1.setBrand("Tiggo 4");
        inf1.setYear(2018);
        inf1.setTotal_distance(12704);
        // вывели на консоль
        inf1.printName();
        inf1.printBrand();
        inf1.printYear();
        inf1.printTD();
    }
}

class Information {
    // определяем поля 
    private String name, brand;
    private int year, total_distance;

    public void Car(String n, String b, int y, int td) {
        this.name = n;
        this.brand = b;
        this.year = y;
        this.total_distance = td;
    }

    // происходит назначение с помощью this
    public void setName(String n) {
        this.name = n;
    }

    public void setBrand(String b) {
        this.brand = b;
    }

    public void setYear(int y) {
        this.year = y;
    }

    public void setTotal_distance(int td) {
        this.total_distance = td;
    }

    // получение переменных
    public String getName() {
        return this.name;
    }

    public String getBrand() {
        return this.brand;
    }

    public int getYear() {
        return this.year;
    }

    public int getTotal_distance() {
        return this.total_distance;
    }

    // метод trip, принимающий в качестве аргумента расстояние и увеличивающий
    // суммарный пробег на данное расстояние.
    public void trip(int a) {
        this.total_distance += a;
    }

    // вывод на консоль через printf (с формитированием)
    void printName() {
        System.out.printf("Name: %s %n", name);
    }

    void printBrand() {
        System.out.printf("Brand: %s %n", brand);
    }

    void printYear() {
        System.out.printf("Year: %d %n", year);
    }

    void printTD() {
        System.out.printf("Total Distance: %d km %n", total_distance);
    }
}