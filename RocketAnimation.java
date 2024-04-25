import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RocketAnimation extends JPanel {

    private int rocketY = 500; // Initial position of the rocket

    public RocketAnimation() {
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update rocket position
                rocketY -= 5; // Adjust the speed as needed
                if (rocketY < -100) {
                    ((Timer)e.getSource()).stop(); // Stop the timer when rocket goes out of view
                }
                repaint(); // Trigger repaint to update the animation
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the rocket
        g.setColor(Color.WHITE);
        g.fillRect(250, rocketY, 20, 100); // Rocket body
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{245, 265, 250}, new int[]{rocketY, rocketY, rocketY - 50}, 3); // Rocket tip
        g.setColor(Color.ORANGE);
        g.fillRect(245, rocketY + 100, 30, 10); // Rocket flame
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(520, 500); // Set preferred size for the animation panel
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rocket Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new RocketAnimation());
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
