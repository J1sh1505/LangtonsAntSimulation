# 🐜 Langton's Ant Parallel Simulation

A high-performance, parallelized implementation of the **Langton's Ant** cellular automaton. This project leverages Java’s advanced concurrency frameworks to efficiently simulate multiple independent ant colonies across a massive coordinate system.

---

### 🚀 Core Features

* **Parallel Execution Engines**
    * **Sequential:** Single-threaded baseline.
    * **ExecutorService:** Fixed thread pool parallelism.
    * **ForkJoinPool:** Recursive "Divide and Conquer" with work-stealing.
* **Scalable Sparse Grid**
    * Utilizes a `ConcurrentHashMap` strategy to manage a **10,000 × 10,000** coordinate system with minimal memory overhead.
* **Distributed Colonies**
    * Features a pre-configured **3x3 grid layout** spawning 45 ants in distinct regions.
* **Interactive Visualization**
    * **Dynamic Camera:** Seamlessly **Zoom** (Mouse Wheel) and **Pan** (Right-Click + Drag).
    * **Granular Speed:** Adjustable delay settings.

---

### 📋 Prerequisites

* **Java Development Kit (JDK) 17** or higher.
* **Git** for version control.

---

### 🛠️ Getting Started

#### 1. Compile the Project
```bash
javac -d bin src/core/*.java src/sequential/*.java src/parallel/*.java src/gui/*.java src/Main.java

2. Run the Simulation
Bash
java -cp bin Main

🎮 Usage Controls

Action,Control
Start / Pause,Toggle button in the control panel
Zooming,Mouse Wheel (Scroll up/down)
Panning,Right-Click + Drag over the grid
Adjust Speed,Slider (Lower delay = Higher speed)

📂 Project Structure

LangtonsAntSimulation/
├── bin/                    # Compiled .class files
├── src/
│   ├── core/               # Shared logic and data structures
│   │   ├── Ant.java
│   │   ├── Grid.java
│   │   └── SimulationLogic.java (Empty)
│   ├── gui/                # User interface components
│   │   └── SimulationGUI.java
│   ├── parallel/           # Parallel frameworks
│   │   ├── ExecutorAnt.java
│   │   ├── ForkJoinAnt.java
│   │   └── RegionTask.java
│   ├── sequential/         # Baseline logic
│   │   └── SequentialAnt.java
│   └── Main.java           # Entry point
├── README.md
└── .gitignore

