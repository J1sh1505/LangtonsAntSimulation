import core.Ant;
import core.Grid;
import gui.SimulationGUI;
import parallel.ExecutorAnt;
import parallel.ForkJoinAnt;
import sequential.SequentialAnt;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int gridWidth = 10000;  
        int gridHeight = 10000; 
        int numAnts = 50;       
        int steps = 10000;

        System.out.println("=== Langton's Ant Performance Benchmarking ===");
        
        // 1. Sequential
        Grid seqGrid = new Grid();
        SequentialAnt seqSim = new SequentialAnt(seqGrid, numAnts, steps, gridWidth, gridHeight);
        long startSeq = System.currentTimeMillis();
        seqSim.run();
        System.out.println("1. Sequential: " + (System.currentTimeMillis() - startSeq) + "ms");

        // 2. Executor
        Grid execGrid = new Grid();
        List<Ant> execAnts = createDistributedAnts(numAnts);
        ExecutorAnt execSim = new ExecutorAnt(execGrid, execAnts, steps);
        long startExec = System.currentTimeMillis();
        execSim.run();
        System.out.println("2. ExecutorService: " + (System.currentTimeMillis() - startExec) + "ms");

        // 3. Fork/Join
        Grid fjGrid = new Grid();
        List<Ant> fjAnts = createDistributedAnts(numAnts);
        ForkJoinAnt fjSim = new ForkJoinAnt(fjGrid, fjAnts, steps);
        long startFj = System.currentTimeMillis();
        fjSim.run();
        System.out.println("3. Fork/Join: " + (System.currentTimeMillis() - startFj) + "ms");

        // 4. Visualization
        System.out.println("\nLaunching Polished Visualization...");
        Grid guiGrid = new Grid();
        List<Ant> guiAnts = createDistributedAnts(numAnts); 
        SimulationGUI gui = new SimulationGUI(guiGrid, guiAnts);
        gui.setVisible(true); 

        ForkJoinAnt guiRunner = new ForkJoinAnt(guiGrid, guiAnts, 1);
        while (true) {
            if (!gui.isPaused()) { 
                guiRunner.run(); 
                gui.refresh(1); 
            }
            Thread.sleep(gui.getSleepTime()); 
        }
    }

    // New logic: Splits 50 ants into 10 groups of 5, spaced out horizontally
   private static List<Ant> createDistributedAnts(int n) {
    List<Ant> ants = new ArrayList<>();
    int rows = 3;
    int cols = 3;
    int antsPerColony = 5;
    int spacing = 200; // How far apart the colonies are
    
    // Starting coordinates for the top-left colony
    int startX = 400; 
    int startY = 400;

    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            int colonyX = startX + (c * spacing);
            int colonyY = startY + (r * spacing);
            
            for (int i = 0; i < antsPerColony; i++) {
                ants.add(new Ant(colonyX, colonyY));
            }
        }
    }
    
    
    return ants;

    }
}