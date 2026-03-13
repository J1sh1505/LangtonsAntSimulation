package core;

public class Ant {
    public int x, y;
    public int direction; // 0: North, 1: East, 2: South, 3: West

    public Ant(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = 0; // Start facing North
    }

    public void turnClockwise() {
        direction = (direction + 1) % 4;
    }

    public void turnCounterClockwise() {
        direction = (direction + 3) % 4;
    }

    public void moveForward() {
        switch (direction) {
            case 0 -> y--; // North
            case 1 -> x++; // East
            case 2 -> y++; // South
            case 3 -> x--; // West
        }
    }
}