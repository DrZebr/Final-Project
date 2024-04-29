import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpacecraftAnimation extends JPanel {

    // Define rocket parameters and animation flags
    private int rocketY = 400;
    private double fuel = 1000.0;
    private double usedFuel = 0.0;
    private double altitude = 0.0;
    private double speed = 0.0;
    private boolean parachutesDeployed = false;
    private boolean landed = false;
    private boolean explosionOccurred = false;

    public SpacecraftAnimation() {
        // Initialize the timer for animation updates
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!landed) {
                    if (fuel > 0) {
                        // Rocket is powered by fuel
                        consumeFuel();
                        updateRocketPosition();
                    } else {
                        // Rocket is in freefall
                        enterFreefall();
                    }
                }
                repaint(); // Repaint the panel to update the animation
            }
        });
        timer.start(); // Start the timer
    }

//     public void start()
//     {   Timer timer = new Timer(50, new ActionListener() {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             if (!landed) {
//                 if (fuel > 0) {
//                     // Rocket is powered by fuel
//                     consumeFuel();
//                     updateRocketPosition();
//                 } else {
//                     // Rocket is in freefall
//                     enterFreefall();
//                 }
//             }
//             repaint(); // Repaint the panel to update the animation
//         }
//     });
//     timer.start(); // Start the timer
// }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background and rocket
        drawBackground(g);
        drawRocket(g);

        // Draw HUD (Fuel, Speed, Altitude)
        drawHUD(g);

        // Check for explosion animation
        if (explosionOccurred) {
            drawExplosion(g);
        }
    }

    private void drawBackground(Graphics g) {
        // Draw starry background
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
        // Draw rocket based on its current position
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
        // Draw explosion animation at the rocket's location
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.fillOval(240, rocketY - 50, 40, 40);
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(245, rocketY - 45, 30, 30);
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(250, rocketY - 40, 20, 20);
    }

    private void updateRocketPosition() {
        if (fuel > 0) {
            // Calculate fuel consumption rate based on the rocket's configuration
            double fuelConsumptionRate = 0.05; // Adjust this value according to your rocket's specifications
            
            // Simulate fuel consumption
            double consumedFuel = fuelConsumptionRate * (speed / 30); // Calculate consumed fuel based on the current speed
            fuel -= consumedFuel; // Deduct consumed fuel from the remaining fuel
            
            // Calculate speed based on the remaining fuel
            speed = fuel * 30; // Assuming each pound of fuel burned increases speed by 30 m/s
            
            // Calculate altitude based on the accumulated speed
            altitude += speed / 50; // Adjust the divisor based on your desired altitude increase rate
        }
    }
    
    private void consumeFuel() {
        // Simulate fuel consumption
        fuel -= 0.05; // Slower fuel consumption rate
    }

    private void enterFreefall() {
        // Simulate freefall animation
        if (altitude > 0) {
            altitude -= 0.1; // Reduce altitude slowly
            if (altitude < 10000 && !parachutesDeployed) {
                deployParachutes(); // Deploy parachutes when altitude is below 10000 meters
            }
        } else {
            landed = true; // Mark as landed when altitude reaches 0
        }
    }

    private void deployParachutes() {
        // Simulate parachute deployment animation
        parachutesDeployed = true;
        speed = Math.max(speed, 7); // Ensure speed is not faster than 7 m/s with parachutes deployed
    }

    private void triggerExplosion() {
        // Trigger explosion animation when speed exceeds 3000 m/s
        if (speed > 3000 && !explosionOccurred) {
            explosionOccurred = true;
            // You may want to add a sound effect or other visual cues for the explosion
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(520, 500); // Set preferred size for the animation panel
    }

    public static void start() {
        JFrame frame = new JFrame("Rocket Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SpacecraftAnimation());
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        start();
    }
}