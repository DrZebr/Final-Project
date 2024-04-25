import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RocketAnimation extends JPanel {

    private int rocketY = 500; // Initial position of the rocket
    private Color backgroundColor = Color.BLUE; // Initial background color

    public RocketAnimation() {
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update rocket position
                rocketY -= 5; // Adjust the speed as needed
                if (rocketY < -100) {
                    ((Timer) e.getSource()).stop(); // Stop the timer when rocket goes out of view
                }
                updateBackgroundColor(); // Update background color
                repaint(); // Trigger repaint to update the animation
            }
        });
        timer.start();
    }

    private void updateBackgroundColor() {
        // Gradually change the background color from blue to black as the rocket ascends
        float ratio = (float) (500 - rocketY) / 500; // Calculate ratio based on rocket position
        backgroundColor = new Color(
                (int) (ratio * 0 + (1 - ratio) * 0), // Red component
                (int) (ratio * 0 + (1 - ratio) * 0), // Green component
                (int) (ratio * 0 + (1 - ratio) * 0)  // Blue component
        );
    }

    public static void start() {
        JFrame frame = new JFrame("Rocket Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new RocketAnimation());
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw background gradient
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, getHeight(), Color.BLUE, 0, 0, backgroundColor));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the rocket
        drawRocket(g);
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
