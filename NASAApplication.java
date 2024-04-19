import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class NASAApplication {

    private static final String PASSWORD_FILE = "password.txt"; // File to store the password
    private JFrame frame;
    private JPanel welcomePanel, menuPanel;
    private JTextField passwordField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NASAApplication().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("NASA Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 500);
        frame.setLayout(null);

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

        JButton addAstronautButton = new JButton("Add Astronaut");
        addAstronautButton.setBounds(100, 100, 150, 25);
        // Add ActionListener to handle adding astronauts
        menuPanel.add(addAstronautButton);

        JButton launchButton = new JButton("Launch");
        launchButton.setBounds(100, 300, 250, 50); // Larger size: width 250, height 50
        // Add ActionListener to handle launching
        menuPanel.add(launchButton);

        JButton removeAstronautButton = new JButton("Remove Astronaut");
        removeAstronautButton.setBounds(100, 150, 150, 25);
        // Add ActionListener to handle removing astronauts
        menuPanel.add(removeAstronautButton);

        JButton addSpacecraftButton = new JButton("Add Spacecraft");
        addSpacecraftButton.setBounds(100, 200, 150, 25);
        // Add ActionListener to handle adding spacecrafts
        menuPanel.add(addSpacecraftButton);

        JButton removeSpacecraftButton = new JButton("Remove Spacecraft");
        removeSpacecraftButton.setBounds(100, 250, 150, 25);
        // Add ActionListener to handle removing spacecrafts
        menuPanel.add(removeSpacecraftButton);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredPassword = passwordField.getText();
            String storedPasswordHash = readStoredPasswordHash();

            if (storedPasswordHash == null) {
                JOptionPane.showMessageDialog(frame, "No password set. Please create a password first.");
            } else if (validatePassword(enteredPassword, storedPasswordHash)) {
                frame.remove(welcomePanel);
                createMenuPanel();
                frame.add(menuPanel);
                frame.revalidate();
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect Password. Try again.");
            }
        }
    }

    private class CreatePasswordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newPassword = JOptionPane.showInputDialog(frame, "Enter new password:");
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                savePassword(newPassword);
                JOptionPane.showMessageDialog(frame, "Password saved successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Password not saved. Try again.");
            }
        }
    }

    // Save the password to a file (hashed for security)
    private void savePassword(String password) {
        String hashedPassword = hashPassword(password);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORD_FILE))) {
            writer.write(hashedPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read the stored password hash from the file
    private String readStoredPasswordHash() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    // Hash the password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Validate the entered password against the stored hash
    private boolean validatePassword(String enteredPassword, String storedPasswordHash) {
        String enteredPasswordHash = hashPassword(enteredPassword);
        return enteredPasswordHash != null && enteredPasswordHash.equals(storedPasswordHash);
    }
}