package task;

public class Triangle {
    private double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }


    public boolean isValid() {
        boolean truth = true;
        boolean statement = false;
        if (((a > 0) && (b > 0) && (c > 0) && ((a + b) > c) && ((a + c) > b) && ((b + c) > a)) == truth) {
            return true;
        } else {
            return statement;
        }
    }

    public double square() {
        if (isValid()) {
            double x = ((a + b + c) / 2);
            return Math.sqrt(x * (x - a) * (x - b) * (x - c));
        } else
            return -1;
    }

    public double perimeter() {
        if (isValid()) {
            return a + b + c;
        } else {
            return -1;
        }
    }
}