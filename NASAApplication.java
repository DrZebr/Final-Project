import javax.swing.*;
import java.awt.Dimension; 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class NASAApplication {
    private static AstronautManager astronautManager;
    private static SpacecraftManager spacecraftManager;
    private static final String PASSWORD_FILE = "password.txt"; // File to store the password
    protected static final String ASTRONAUTS_FILE = null;
    private JFrame frame;
    private JPanel welcomePanel, menuPanel;
    private JTextField passwordField;
    private static Object adminPasswordHash;

    public static void main(String[] args) {
        astronautManager = new AstronautManager();
        spacecraftManager = new SpacecraftManager();
    
        double initialFuel = 1000.0; // Initial fuel amount for the spacecraft

        // Start the SpacecraftAnimation with the initial fuel amount
        SwingUtilities.invokeLater(() -> {
            NASAApplication app = new NASAApplication();
            app.createAndShowGUI(initialFuel);
        });
    }

    private void createAndShowGUI(double initialFuel) {
        frame = new JFrame("NASA Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 500);
        frame.setLayout(null);

        loadPassword();

        createWelcomePanel();
        frame.add(welcomePanel);
        frame.setVisible(true);
    }

    private void createWelcomePanel() {
        welcomePanel = new JPanel();
        welcomePanel.setLayout(null);
        welcomePanel.setBounds(0, 0, 520, 500);

        JLabel welcomeLabel = new JLabel("Welcome to NASA");
        welcomeLabel.setBounds(200, 100, 120, 25);
        welcomePanel.add(welcomeLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 200, 80, 25);
        welcomePanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 200, 150, 25);
        welcomePanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(180, 250, 80, 25);
        loginButton.addActionListener(new LoginButtonListener());
        welcomePanel.add(loginButton);

        JButton createPasswordButton = new JButton("Create/Change Password");
        createPasswordButton.setBounds(270, 250, 180, 25);
        createPasswordButton.addActionListener(new CreatePasswordButtonListener());
        welcomePanel.add(createPasswordButton);
    }

    private void createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBounds(0, 0, 520, 500);

        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setBounds(230, 20, 60, 25);
        menuPanel.add(menuLabel);

        // Buttons for menuPanel...
    }

    private void loadPassword() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            String hashedPassword = reader.readLine();
            adminPasswordHash = hashedPassword;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void savePassword(String password) {
        String hashedPassword = hashPassword(password);
        try (PrintWriter writer = new PrintWriter(new FileWriter(PASSWORD_FILE))) {
            writer.println(hashedPassword);
            adminPasswordHash = hashedPassword; // Update the password hash in memory
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String hashPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Add password bytes to digest
            md.update(password.getBytes());
            // Get the hashed bytes
            byte[] hashedBytes = md.digest();
            // Convert hashed bytes to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputPassword = passwordField.getText();
            if (authenticate(inputPassword)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                createMenuPanel();
                frame.remove(welcomePanel);
                createMenuPanel(); // Add this line after "Login successful!"
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect password. Please try again.");
            }
        }
    }

    private class CreatePasswordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newPassword = JOptionPane.showInputDialog(frame, "Enter new password:");
            savePassword(newPassword);
            JOptionPane.showMessageDialog(frame, "Password created/changed successfully.");
        }
    }

    private boolean authenticate(String password) {
        if (adminPasswordHash == null) {
            return false; // Password not set yet
        }
        String enteredHashedPassword = hashPassword(password);
        System.out.println("Entered Hashed Password: " + enteredHashedPassword);
        System.out.println("Stored Hashed Password: " + adminPasswordHash);
        return adminPasswordHash.equals(enteredHashedPassword);
    }
}
