import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

// Import the Astronaut and Spacecraft classes
public class NASAApplication {
    private static AstronautManager astronautManager;
    private static SpacecraftManager spacecraftManager;
    private static final String PASSWORD_FILE = "password.txt"; // File to store the password
    private static final String ASTRONAUTS_FILE = "astronauts.dat"; // File to store astronauts
    private JFrame frame;
    private JPanel welcomePanel, menuPanel;
    private JTextField passwordField;
 

    public static void main(String[] args) {
        astronautManager = new AstronautManager();
        spacecraftManager = new SpacecraftManager();
        SwingUtilities.invokeLater(() -> {
            NASAApplication app = new NASAApplication();
            app.createAndShowGUI();
            System.out.print("L bozo");
        });
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
        addAstronautButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to add astronaut...
                String name = JOptionPane.showInputDialog(frame, "Enter astronaut name:");
                String email = JOptionPane.showInputDialog(frame, "Enter astronaut email:");
                int dob = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter astronaut date of birth:"));
                int serialNumber = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter astronaut serial number:"));
                int phoneNumber = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter astronaut phone number:"));
                String address = JOptionPane.showInputDialog(frame, "Enter astronaut address:");
                
                Astronaut astronaut = new Astronaut(name, email, dob, serialNumber, phoneNumber, address);
                astronautManager.addAstronaut(astronaut);
                saveAstronauts(); // Save the updated list of astronauts
                JOptionPane.showMessageDialog(frame, "Astronaut added successfully.");
            }
        });
        menuPanel.add(addAstronautButton);

        JButton displayAstronautsButton = new JButton("Display Astronauts");
        displayAstronautsButton.setBounds(100, 150, 150, 25);
        displayAstronautsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAstronauts();
            }
        });
        menuPanel.add(displayAstronautsButton);

        JButton removeAstronautButton = new JButton("Remove Astronaut");
        removeAstronautButton.setBounds(100, 200, 150, 25);
        removeAstronautButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to remove astronaut...
                List<Astronaut> astronauts = astronautManager.getAstronauts();
                if (astronauts.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No astronauts to remove.");
                    return;
                }

                String[] astronautNames = new String[astronauts.size()];
                for (int i = 0; i < astronauts.size(); i++) {
                    astronautNames[i] = astronauts.get(i).getName();
                }

                String selectedAstronautName = (String) JOptionPane.showInputDialog(frame,
                        "Select Astronaut to Remove:", "Remove Astronaut",
                        JOptionPane.QUESTION_MESSAGE, null,
                        astronautNames, astronautNames[0]);

                if (selectedAstronautName != null && !selectedAstronautName.isEmpty()) {
                    astronautManager.removeAstronaut(selectedAstronautName);
                    saveAstronauts(); // Save the updated list of astronauts after removal
                    JOptionPane.showMessageDialog(frame, "Astronaut removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please select an astronaut to remove.");
                }
            }
        });
        menuPanel.add(removeAstronautButton);
 
        JButton addSpacecraftButton = new JButton("Add Spacecraft");
        addSpacecraftButton.setBounds(100, 250, 150, 25);
        addSpacecraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to add spacecraft...
                String name = JOptionPane.showInputDialog(frame, "Enter spacecraft name:");
                String model = JOptionPane.showInputDialog(frame, "Enter spacecraft model:");
                int capacity = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter spacecraft capacity:"));
                
                SpaceCraft spacecraft = new SpaceCraft(name, model, capacity);
                spacecraftManager.addSpacecraft(spacecraft);
                JOptionPane.showMessageDialog(frame, "Spacecraft added successfully.");
            }
        });
        menuPanel.add(addSpacecraftButton);

        JButton removeSpacecraftButton = new JButton("Remove Spacecraft");
        removeSpacecraftButton.setBounds(100, 300, 150, 25);
        removeSpacecraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to remove spacecraft...
                List<SpaceCraft> spacecraftList = spacecraftManager.getSpacecrafts();
                if (spacecraftList.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No spacecrafts to remove.");
                    return;
                }

                String[] spacecraftNames = new String[spacecraftList.size()];
                for (int i = 0; i < spacecraftList.size(); i++) {
                    spacecraftNames[i] = spacecraftList.get(i).getName();
                }

                String selectedSpacecraftName = (String) JOptionPane.showInputDialog(frame,
                        "Select Spacecraft to Remove:", "Remove Spacecraft",
                        JOptionPane.QUESTION_MESSAGE, null,
                        spacecraftNames, spacecraftNames[0]);

                if (selectedSpacecraftName != null) {
                    SpaceCraft spacecraftToRemove = null;
                    for (SpaceCraft spacecraft : spacecraftList) {
                        if (spacecraft.getName().equals(selectedSpacecraftName)) {
                            spacecraftToRemove = spacecraft;
                            break;
                        }
                    }

                    if (spacecraftToRemove != null) {
                        spacecraftManager.removeSpacecraft(spacecraftToRemove);
                        JOptionPane.showMessageDialog(frame, "Spacecraft removed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Selected spacecraft not found.");
                    }
                }
            }
        });
        menuPanel.add(removeSpacecraftButton);

        frame.add(menuPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void saveAstronauts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ASTRONAUTS_FILE))) {
            oos.writeObject(astronautManager.getAstronauts());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Astronaut> loadAstronauts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ASTRONAUTS_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                List<?> list = (List<?>) obj;
                List<Astronaut> astronauts = new ArrayList<>();
                for (Object o : list) {
                    if (o instanceof Astronaut) {
                        astronauts.add((Astronaut) o);
                    }
                }
                return astronauts;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void displayAstronauts() {
        List<Astronaut> astronauts = loadAstronauts();
        StringBuilder displayMessage = new StringBuilder("Astronauts:\n");

        for (Astronaut astronaut : astronauts) {
            // Generate ASCII art representation of the astronaut character
            String astronautArt = generateAstronautArt();

            // Append astronaut information along with the ASCII art representation
            displayMessage.append(astronautArt).append(" Name: ").append(astronaut.getName())
                    .append(", Email: ").append(astronaut.getEmailAddress())
                    .append(", DOB: ").append(astronaut.getDateOfBirth())
                    .append(", Serial No: ").append(astronaut.getSerialNumber())
                    .append(", Phone No: ").append(astronaut.getPhoneNumber())
                    .append(", Address: ").append(astronaut.getAddress()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, displayMessage.toString());
    }

    private String generateAstronautArt() {
        // ASCII art representation of an astronaut (you can replace this with your own)
        return "  o\n\\_\\|\\/_/\n   |";
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

    private String readStoredPasswordHash() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private boolean validatePassword(String enteredPassword, String storedPasswordHash) {
        String enteredPasswordHash = hashPassword(enteredPassword);
        return enteredPasswordHash != null && enteredPasswordHash.equals(storedPasswordHash);
    }

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

    private void savePassword(String password) {
        String hashedPassword = hashPassword(password);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORD_FILE))) {
            writer.write(hashedPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
