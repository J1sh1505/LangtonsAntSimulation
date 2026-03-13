package parallel;

import core.Ant;
import core.Grid;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

//Parallel Version using ForkJoinPool.
 
public class ForkJoinAnt {
    private final Grid grid;
    private final List<Ant> ants;
    private final int steps;
    private final ForkJoinPool pool;

    public ForkJoinAnt(Grid grid, List<Ant> ants, int steps) {
        this.grid = grid;
        this.ants = ants;
        this.steps = steps;
        // ForkJoinPool uses a work-stealing algorithm for better CPU utilization
        this.pool = new ForkJoinPool();
    }

    public void run() {
        for (int s = 0; s < steps; s++) {
            // Invoke the root task [cite: 362]
            pool.invoke(new RegionTask(ants, grid));
        }
    }
}