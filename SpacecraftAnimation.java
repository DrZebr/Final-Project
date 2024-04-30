import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpacecraftAnimation extends JPanel {

    private int rocketY = 400; // Initial position of the rocket
    private double fuel; // Current fuel amount
    private double altitude = 0.0; // Current altitude
    private double speed = 0.0; // Current speed
    private boolean landed = false; // Flag indicating if the rocket has landed
    private boolean explosionOccurred = false; // Flag indicating if an explosion occurred
    private boolean evaMode = false; // Flag indicating if astronauts are on EVA
    private boolean parachuteDeployed = false; // Flag indicating if parachutes are deployed
    private double initialFuel; // Initial fuel amount for the spacecraft

    public SpacecraftAnimation(double initialFuel) {
        this.initialFuel = initialFuel;
        this.fuel = initialFuel;

        Timer timer = new Timer(1000, new ActionListener() { // Timer for animation updates (1 second interval)
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!landed && fuel > 0) {
                    // Rocket is powered by fuel
                    consumeFuel();
                    updateRocketPosition();
                } else {
                    // Rocket is in freefall or out of fuel
                    enterFreefall();
                }
                repaint(); // Repaint the panel to update the animation
            }
        });
        timer.start(); // Start the timer
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background, rocket, HUD, explosion, and EVA astronauts
        drawBackground(g);
        drawRocket(g);
        drawHUD(g);
        if (explosionOccurred) {
            drawExplosion(g);
        }
        if (evaMode) {
            drawEVA(g);
        }
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        for (int i = 0; i < 1000; i++) {
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * getHeight());
            g.fillRect(x, y, 1, 1);
        }
    }

    private void drawRocket(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Rocket body
        GradientPaint bodyGradient = new GradientPaint(250, rocketY, Color.DARK_GRAY, 270, rocketY + 100, Color.GRAY);
        g2d.setPaint(bodyGradient);
        g2d.fillRect(250, rocketY, 20, 100);

        // Rocket windows
        g2d.setColor(Color.WHITE);
        g2d.fillRect(253, rocketY + 10, 4, 15);
        g2d.fillRect(253, rocketY + 30, 4, 15);
        g2d.fillRect(253, rocketY + 50, 4, 15);
        g2d.fillRect(253, rocketY + 70, 4, 15);

        // Rocket wings
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(240, rocketY + 30, 30, 10);
        g2d.fillRect(240, rocketY + 60, 30, 10);

        // Rocket tip
        int[] xPoints = {245, 275, 259};
        int[] yPoints = {rocketY, rocketY, rocketY - 50};
        GradientPaint tipGradient = new GradientPaint(250, rocketY, Color.RED, 250, rocketY - 50, Color.ORANGE);
        g2d.setPaint(tipGradient);
        g2d.fillPolygon(xPoints, yPoints, 3);

        // Rocket flame
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(245, rocketY + 100, 30, 10);
    }

    private void drawHUD(Graphics g) {
        // Draw HUD elements (Fuel, Speed, Altitude)
        g.setColor(Color.WHITE);
        g.drawString("Fuel: " + String.format("%.1f", fuel), 10, 20);
        g.drawString("Speed: " + String.format("%.1f", speed) + " m/s", 10, 40);
        g.drawString("Altitude: " + String.format("%.1f", altitude) + " m", 10, 60);
    }

    private void drawExplosion(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.fillOval(240, rocketY - 50, 40, 40);
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(245, rocketY - 45, 30, 30);
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(250, rocketY - 40, 20, 20);
    }

    private void drawEVA(Graphics g) {
        // Draw astronauts on EVA
        g.setColor(Color.WHITE);
        g.drawString("Astronauts on EVA", 400, 20);
        // Draw astronauts outside the spacecraft
        // Implement drawing astronauts here
    }

    private void updateRocketPosition() {
        // Calculate speed based on the remaining fuel
        speed = fuel * 30; // Assuming each pound of fuel burned increases speed by 30 m/s

        // Calculate altitude based on the accumulated speed
        altitude += speed / 50; // Adjust the divisor based on your desired altitude increase rate
    }

    private void consumeFuel() {
        // Simulate fuel consumption
        fuel -= (initialFuel - fuel) * 0.05 / initialFuel; // Consume fuel based on the remaining fuel
    }

    private void enterFreefall() {
        if (altitude > 0) {
            altitude -= 9.81; // Reduce altitude by 9.81 meters per second
        } else {
            // Rocket has landed
            landed = true;
            if (!parachuteDeployed && altitude <= 0) {
                // Deploy parachutes if not deployed already and altitude is within landing range
                deployParachutes();
            }
        }
    }

    private void deployParachutes() {
        // Deploy parachutes
        parachuteDeployed = true;
        // Add code to simulate parachute deployment
    }

    public Dimension getPreferredSize() {
        return new Dimension(520, 500);
    }

    public static void start(double initialFuel) {
        JFrame frame = new JFrame("Rocket Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpacecraftAnimation spacecraftAnimation = new SpacecraftAnimation(initialFuel); // Pass the initial fuel amount
        frame.getContentPane().add(spacecraftAnimation);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        double initialFuel = 1000.0;
        start(initialFuel);
    }
}
