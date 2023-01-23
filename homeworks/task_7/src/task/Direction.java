package task;

public enum Direction {
    North(0, -1),
    West(-1, 0),
    South(0, 1),
    East(1, 0);

    private int X;
    private int Y;
    Direction(int X, int Y){
        this.X = X;
        this.Y = Y;
    }
    public Vector2 move(Vector2 start, double distance){
        return new Vector2(start.getX() + (distance * X), start.getY() + (distance * Y));
    }
}
