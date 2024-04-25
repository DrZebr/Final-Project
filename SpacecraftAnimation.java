import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpacecraftAnimation extends JPanel {

    private int rocketY = -100; // Initial position of the rocket at the top of the panel
    private int moonX = 50; // Position of the moon
    private int moonY = 250; // Position of the moon
    private int moonWidth = 420; // Width of the moon
    private int moonHeight = 420; // Height of the moon
    private int landingThreshold = 50; // Distance threshold for landing

    public SpacecraftAnimation() {
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update rocket position
                rocketY += 5; // Move the rocket downwards
                if (rocketY >= moonY - landingThreshold) { // Adjusted stop condition for landing
                    ((Timer) e.getSource()).stop(); // Stop the timer when rocket lands on the moon
                }
                repaint(); // Trigger repaint to update the animation
            }
        });
        timer.start();
    }

    public static void start() {
        JFrame frame = new JFrame("Rocket Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SpacecraftAnimation());
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color to black
        setBackground(Color.BLACK);

        // Draw the moon
        drawMoon(g);

        // Draw the rocket
        drawRocket(g);
    }

    private void drawMoon(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Moon gradient
        GradientPaint moonGradient = new GradientPaint(
                moonX + moonWidth / 2, moonY + moonHeight / 2, new Color(255, 255, 255, 220),
                moonX + moonWidth / 2, moonY + moonHeight, new Color(150, 150, 150, 50)
        );
        g2d.setPaint(moonGradient);
        g2d.fillOval(moonX, moonY, moonWidth, moonHeight);
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(520, 500); // Set preferred size for the animation panel
    }

    public static void main(String[] args) {
        start();
    }
}
