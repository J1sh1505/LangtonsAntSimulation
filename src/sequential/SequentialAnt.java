package sequential;

import core.Ant;
import core.Grid;

//Sequential Version 
//Provides a baseline for performance comparison.

public class SequentialAnt {
    private final Grid grid;
    private final Ant[] ants;
    private final int steps;

    public SequentialAnt(Grid grid, int numberOfAnts, int steps, int gridWidth, int gridHeight) {
        this.grid = grid;
        this.steps = steps;
        this.ants = new Ant[numberOfAnts];
        
        // Initialize ants at the center as per requirement 
        for (int i = 0; i < numberOfAnts; i++) {
            ants[i] = new Ant(gridWidth / 2, gridHeight / 2);
        }
    }

    public void run() {
        for (int s = 0; s < steps; s++) {
            for (Ant ant : ants) {
                step(ant);
            }
        }
    }

    private void step(Ant ant) {
        // 1. Get current color (0 = White, 1 = Black)
        int currentColor = grid.getColor(ant.x, ant.y);

        // 2. Apply rules based on color 
        if (currentColor == 0) { // White cell
            grid.flipColor(ant.x, ant.y); // Flip to black
            ant.turnClockwise();          // Turn 90 deg clockwise
        } else { // Black cell
            grid.flipColor(ant.x, ant.y); // Flip to white
            ant.turnCounterClockwise();   // Turn 90 deg counter-clockwise
        }

        // 3. Move forward one unit
        ant.moveForward();
    }
}