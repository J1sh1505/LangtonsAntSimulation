package core;

import java.util.concurrent.ConcurrentHashMap;
import java.awt.Point;


public class Grid {
    // Stores only Black cells. Key = (x, y), Value = Color (0 for White, 1 for Black)
    private final ConcurrentHashMap<Point, Integer> cells;

    public Grid() {
        this.cells = new ConcurrentHashMap<>();
    }

    // Returns 0 for White (default) or 1 for Black
    public int getColor(int x, int y) {
        return cells.getOrDefault(new Point(x, y), 0);
    }

    // Flips the color: 0 -> 1 or 1 -> 0
    public void flipColor(int x, int y) {
        Point p = new Point(x, y);
        cells.compute(p, (key, val) -> (val == null || val == 0) ? 1 : 0);
    }
}