package gui;

import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import core.Ant;
import core.Grid;

public class SimulationGUI extends JFrame {
    private final Grid grid;
    private final List<Ant> ants;
    private int cellSize = 12; 
    private boolean isPaused = true;
    private int sleepTime = 50; 
    private long stepCount = 0;

    // --- CAMERA MOVEMENT VARIABLES ---
    private int offsetX = 0;
    private int offsetY = 0;
    private Point lastMousePos;

    public SimulationGUI(Grid grid, List<Ant> ants) {
        this.grid = grid;
        this.ants = ants;

        setTitle("Langton's Ant Parallel Simulation - PRAC1");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawSimulation(g);
            }
        };
        canvas.setBackground(Color.WHITE); 

        // 1. ZOOM LOGIC (Mouse Wheel)
        canvas.addMouseWheelListener(e -> {
            if (e.getWheelRotation() < 0) {
                cellSize = Math.min(cellSize + 1, 50);
            } else {
                cellSize = Math.max(cellSize - 1, 1);
            }
            repaint();
        });

        // 2. DRAG LOGIC (Right Click to Pan)
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    lastMousePos = e.getPoint();
                }
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) && lastMousePos != null) {
                    Point currentPos = e.getPoint();
                    // Update offset based on mouse movement
                    offsetX += (currentPos.x - lastMousePos.x);
                    offsetY += (currentPos.y - lastMousePos.y);
                    lastMousePos = currentPos;
                    repaint();
                }
            }
        });

        add(canvas, BorderLayout.CENTER);

        // 3. CONTROLS
        JPanel controls = new JPanel();
        JButton pauseBtn = new JButton("Start/Pause");
        JSlider speedSlider = new JSlider(1, 500, 50); 
        JLabel hint = new JLabel(" | Scroll: Zoom | Right-Click Drag: Move");

        pauseBtn.addActionListener(e -> isPaused = !isPaused);
        speedSlider.addChangeListener(e -> sleepTime = speedSlider.getValue());

        controls.add(pauseBtn);
        controls.add(new JLabel("Delay:"));
        controls.add(speedSlider);
        controls.add(hint);
        add(controls, BorderLayout.SOUTH);
    }

    private void drawSimulation(Graphics g) {
        if (ants.isEmpty()) return;

        // Base centering on 0,0 plus the user's manual offset
        int viewWidth = getWidth() / cellSize;
        int viewHeight = getHeight() / cellSize;
        
        // We start drawing from the top-left, adjusted by our drag offsets
        int startX = -offsetX / cellSize;
        int startY = -offsetY / cellSize;

        // 1. Draw Background Grid
        if (cellSize > 4) {
            g.setColor(new Color(235, 235, 235)); 
            for (int x = 0; x <= viewWidth; x++) {
                int lineX = x * cellSize + (offsetX % cellSize);
                g.drawLine(lineX, 0, lineX, getHeight());
            }
            for (int y = 0; y <= viewHeight; y++) {
                int lineY = y * cellSize + (offsetY % cellSize);
                g.drawLine(0, lineY, getWidth(), lineY);
            }
        }

        // 2. Draw Black Trail
        g.setColor(Color.BLACK);
        // Expand the scan range to fill the screen based on the offset
        for (int x = startX; x < startX + viewWidth + 1; x++) {
            for (int y = startY; y < startY + viewHeight + 1; y++) {
                if (grid.getColor(x, y) == 1) {
                    g.fillRect(x * cellSize + offsetX, y * cellSize + offsetY, cellSize, cellSize);
                }
            }
        }

        // 3. Draw Ants (Lime Green)
        g.setColor(new Color(50, 205, 50)); 
        for (Ant ant : ants) {
            int drawX = ant.x * cellSize + offsetX;
            int drawY = ant.y * cellSize + offsetY;
            g.fillRect(drawX, drawY, cellSize, cellSize);
        }

        // 4. HUD
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(10, 10, 180, 40);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 16));
        g.drawString("Steps: " + stepCount, 20, 35);
    }

    public boolean isPaused() { return isPaused; }
    public int getSleepTime() { return sleepTime; }
    public void refresh(int increment) { 
        this.stepCount += increment;
        repaint(); 
    }
}