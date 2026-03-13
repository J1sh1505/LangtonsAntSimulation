package parallel;

import core.Ant;
import core.Grid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//Uses a fixed thread pool to process grid regions in parallel.
 
public class ExecutorAnt {
    private final Grid grid;
    private final List<Ant> ants;
    private final int steps;
    private final int numThreads;
    private final ExecutorService executor;

    public ExecutorAnt(Grid grid, List<Ant> ants, int steps) {
        this.grid = grid;
        this.ants = ants;
        this.steps = steps;
        // Use available processors for the thread pool size
        this.numThreads = Runtime.getRuntime().availableProcessors();
        this.executor = Executors.newFixedThreadPool(numThreads);
    }

    public void run() throws InterruptedException {
        for (int s = 0; s < steps; s++) {
            // In a real region-based split, we group ants by their current location
            // and submit tasks for each region.
            submitRegionTasks();
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    private void submitRegionTasks() {
        // Dividing the workload: Each thread processes a sub-list of ants
        // This simulates 'Work Distribution'
        int batchSize = (int) Math.ceil((double) ants.size() / numThreads);

        for (int i = 0; i < numThreads; i++) {
            int start = i * batchSize;
            int end = Math.min(start + batchSize, ants.size());

            if (start < end) {
                executor.submit(() -> {
                    for (int j = start; j < end; j++) {
                        step(ants.get(j));
                    }
                });
            }
        }
        // Note: For a strict 'tick-based' simulation, you would use a cyclic barrier 
        // here to ensure all ants move before the next step starts.
    }

    private void step(Ant ant) {
        // Thread-safety is handled by the ConcurrentHashMap in Grid.java
        int currentColor = grid.getColor(ant.x, ant.y);

        if (currentColor == 0) {
            grid.flipColor(ant.x, ant.y);
            ant.turnClockwise();
        } else {
            grid.flipColor(ant.x, ant.y);
            ant.turnCounterClockwise();
        }
        ant.moveForward();
    }
}