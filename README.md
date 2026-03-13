# 🐜 Langton's Ant Parallel Simulation

A high-performance, parallelized implementation of the **Langton's Ant** cellular automaton. This project leverages Java’s advanced concurrency frameworks to efficiently simulate multiple independent ant colonies across a massive coordinate system.

---

### 🚀 Core Features

* **Parallel Execution Engines** Compare performance across three distinct modes:
  * **Sequential:** Single-threaded baseline.
  * **ExecutorService:** Fixed thread pool parallelism.
  * **ForkJoinPool:** Recursive "Divide and Conquer" with work-stealing.

* **Scalable Sparse Grid** Utilizes a `ConcurrentHashMap` strategy to manage a **10,000 × 10,000** coordinate system with minimal memory overhead and high thread safety.

* **Distributed Colonies** Features a pre-configured **3x3 grid layout** spawning 45 ants in distinct regions to observe complex emergent behaviors and "highway" collisions.

* **Interactive Visualization** * **Real-time HUD:** Monitor step counts and simulation metrics live.
  * **Dynamic Camera:** Seamlessly **Zoom** (Mouse Wheel) and **Pan** (Right-Click + Drag).
  * **Granular Speed:** Adjustable delay settings to study specific patterns.

---

### 📋 Prerequisites

* **Java Development Kit (JDK) 17** or higher.
* **Git** for version control.

---

### 🛠️ Getting Started

#### 1. Compile the Project
Build the project by compiling all packages into the `bin` directory:
```bash
javac -d bin src/core/*.java src/sequential/*.java src/parallel/*.java src/gui/*.java src/Main.java

---

#### 2. Run the Simulation
Execute the application to trigger benchmarking results followed by the interactive GUI:
```bash
java -cp bin Main

---

### 🎮 Usage Controls

Action,Control
Start / Pause,Toggle button in the control panel
Zooming,Mouse Wheel (Scroll up/down)
Panning,Right-Click + Drag over the grid
Adjust Speed,Slider (Lower delay = Higher speed)

Note: The simulation begins with a benchmarking phase in the terminal to compare the efficiency of different parallel frameworks before launching the graphical window.

---

### Project Structure 

LangtonsAntSimulation/
├── bin/                    # Compiled .class files
├── src/
│   ├── core/               # Shared logic and data structures
│   │   ├── Ant.java        # Ant movement and state rules
│   │   ├── Grid.java       # Sparse grid using ConcurrentHashMap
│   │   └── SimulationLogic.java (Empty)
│   ├── gui/                # User interface components
│   │   └── SimulationGUI.java # Swing-based window, zoom/pan logic
│   ├── parallel/           # Parallel implementation frameworks
│   │   ├── ExecutorAnt.java # Fixed thread pool implementation
│   │   ├── ForkJoinAnt.java # Main ForkJoin entry point
│   │   └── RegionTask.java  # Recursive task for Work-Stealing
│   ├── sequential/         # Baseline performance logic
│   │   └── SequentialAnt.java # Single-threaded implementation
│   └── Main.java           # Entry point: Benchmarking and GUI launch
├── README.md               # Project documentation
└── .gitignore              # Files for Git to ignore (like bin/)