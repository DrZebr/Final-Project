import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RocketMission {
    private JFrame frame;
    private JPanel missionPanel;
    private JLabel altitudeLabel;
    private RocketPanel rocketPanel;
    private int altitude;
    private int speed;

    public RocketMission() {
        altitude = 0;
        speed = 0;
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Rocket Mission");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        missionPanel = new JPanel();
        missionPanel.setLayout(null);

        altitudeLabel = new JLabel("Altitude: " + altitude + " meters");
        altitudeLabel.setBounds(20, 20, 200, 25);
        missionPanel.add(altitudeLabel);

        rocketPanel = new RocketPanel();
        rocketPanel.setBounds(100, 100, 100, 200);
        missionPanel.add(rocketPanel);

        JButton launchButton = new JButton("Launch");
        launchButton.setBounds(20, 50, 100, 25);
        launchButton.addActionListener(new LaunchButtonListener());
        missionPanel.add(launchButton);

        frame.add(missionPanel);
        frame.setVisible(true);
    }

    private void updateAltitude() {
        altitude += speed;
        altitudeLabel.setText("Altitude: " + altitude + " meters");
        rocketPanel.setY(altitude);
        missionPanel.repaint();
        if (altitude >= 70000) {
            // Rocket enters atmosphere
            // Implement logic for atmosphere entry
        }
        if (altitude <= 10000) {
            // Parachutes deploy
            // Implement logic for parachute deployment
        }
    }

    private void updateSpeed(int fuelBurned) {
        // Implement logic to calculate speed based on fuel burned
    }

    private void launchRocket(int countdownSeconds, int fuelAmount) {
        new Thread(() -> {
            for (int i = countdownSeconds; i >= 0; i--) {
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Countdown: " + i);
            }
            // Launch rocket
            for (int t = 1; t <= fuelAmount; t++) {
                updateSpeed(t); // Update speed based on fuel burned
                updateAltitude(); // Update altitude based on speed
                try {
                    Thread.sleep(100); // Sleep for 100 milliseconds
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            // Rocket runs out of fuel, enters freefall
            // Implement logic for freefall
        }).start();
    }

    private class LaunchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Start rocket launch sequence
            launchRocket(5, 1000); // Example: 5 seconds countdown, 1000 fuel units
        }
    }

    private class RocketPanel extends JPanel {
        private int y;

        public RocketPanel() {
            y = 0;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw rocket
            g.setColor(Color.RED);
            g.fillRect(0, getHeight() - 20 - y, 20, 20);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RocketMission());
    }
}
