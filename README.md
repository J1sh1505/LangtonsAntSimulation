README: Langton's Ant Parallel Simulation (PRAC1)
This project implements a high-performance, parallelized version of Langton's Ant, a cellular automaton. It is designed to satisfy the requirements for the PRAC1 Assessment, demonstrating the use of Java's concurrency frameworks to handle a massive grid and multiple independent ant colonies.

## Core Features
Three Implementations: Compares Sequential, ExecutorService (Fixed Thread Pool), and ForkJoinPool (Work-Stealing).

Massive Grid Handling: Uses a Sparse Grid strategy with ConcurrentHashMap for memory efficiency.

Advanced GUI: * 3x3 Colony Grid: Spawns 45 ants across 9 distinct regions.

Interactive Controls: Start/Pause, Speed Slider, and Step Counter.

Dynamic Camera: Mouse wheel to Zoom, Right-Click and Drag to Pan.

## Prerequisites
JDK 17 or higher (required for modern switch expressions and improved ForkJoin performance).

VS Code with the Extension Pack for Java (recommended).

## Terminal Commands
To run this simulation via the terminal, navigate to the root folder (LangtonsAntSimulation) and execute the following:

1. Compile the Project
This command compiles all Java files from the different packages and places the .class files into a bin folder.

Bash
javac -d bin src/core/*.java src/sequential/*.java src/parallel/*.java src/gui/*.java src/Main.java
2. Run the Benchmarking & Simulation
This command executes the Main class. It will first run performance tests in the terminal and then launch the GUI.

Bash
java -cp bin Main
## How to Use the GUI
Start/Pause: Click the button at the bottom to begin the simulation.

Speed: Adjust the Delay (ms) slider (Lower delay = Higher speed).

Zoom: Use the Mouse Wheel over the canvas to zoom in/out.

Pan: Right-Click and Drag to move the view across the grid to see different colonies.

## Performance Comparison (For Report)
When you run the Main class, the terminal will output the execution time for 10,000 steps across three modes:

Sequential: The baseline single-threaded performance.

ExecutorService: Fixed-pool parallelism (usually the fastest for this scale).

Fork/Join: Recursive divide-and-conquer parallelism (uses Work-Stealing).