package parallel;

import core.Ant;
import core.Grid;
import java.util.List;
import java.util.concurrent.RecursiveAction;

// Divide and Conquer Parallelism
// Uses RecursiveAction for tasks that do not return a result.
 
public class RegionTask extends RecursiveAction {
    private final List<Ant> ants;
    private final Grid grid;
    private final int THRESHOLD = 10; // Splitting threshold based on ant count 

    public RegionTask(List<Ant> ants, Grid grid) {
        this.ants = ants;
        this.grid = grid;
    }

    @Override
    protected void compute() {
        // If workload is small enough, process sequentially 
        if (ants.size() <= THRESHOLD) {
            for (Ant ant : ants) {
                step(ant);
            }
        } else {
            // Divide: Split the ant list into two subtasks 
            int mid = ants.size() / 2;
            RegionTask leftTask = new RegionTask(ants.subList(0, mid), grid);
            RegionTask rightTask = new RegionTask(ants.subList(mid, ants.size()), grid);

            // Fork: Run the left task asynchronously 
            leftTask.fork();
            // Solve: Compute the right task in the current thread 
            rightTask.compute();
            // Join: Wait for the left task to complete 
            leftTask.join();
        }
    }

    private void step(Ant ant) {
        int currentColor = grid.getColor(ant.x, ant.y);
        if (currentColor == 0) { // White [cite: 448]
            grid.flipColor(ant.x, ant.y);
            ant.turnClockwise();
        } else { // Black [cite: 454]
            grid.flipColor(ant.x, ant.y);
            ant.turnCounterClockwise();
        }
        ant.moveForward();
    }
}