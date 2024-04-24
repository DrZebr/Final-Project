import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class NASAApplication {
    private static AstronautManager astronautManager;
    private static SpacecraftManager spacecraftManager;
    private static final String PASSWORD_FILE = "password.txt"; // File to store the password
    private JFrame frame;
    private JPanel welcomePanel, menuPanel;
    private JTextField passwordField;
    private static Object adminPasswordHash;
    public static void main(String[] args) {
        astronautManager = new AstronautManager();
        spacecraftManager = new SpacecraftManager();
        SwingUtilities.invokeLater(() -> {
            NASAApplication app = new NASAApplication();
            app.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
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
                double payRate = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter astronaut pay rate:")); // New input
                double weight = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter astronaut weight:")); // New input
        
                Astronaut astronaut = new Astronaut(name, email, dob, serialNumber, phoneNumber, address, payRate, weight);
                astronautManager.addAstronaut(astronaut);
                saveAstronauts(); // Save the updated list of astronauts
                JOptionPane.showMessageDialog(frame, "Astronaut added successfully.");
            }

            private void saveAstronauts() {
                throw new UnsupportedOperationException("Unimplemented method 'saveAstronauts'");
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

            private void displayAstronauts() {
                throw new UnsupportedOperationException("Unimplemented method 'displayAstronauts'");
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
                    removePassword(selectedAstronautName); // Remove astronaut password
                    saveAstronauts(); // Save the updated list of astronauts after removal
                    JOptionPane.showMessageDialog(frame, "Astronaut removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please select an astronaut to remove.");
                }
            }

            private void saveAstronauts() {
                throw new UnsupportedOperationException("Unimplemented method 'saveAstronauts'");
            }

            private void removePassword(String selectedAstronautName) {
                throw new UnsupportedOperationException("Unimplemented method 'removePassword'");
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

        JButton launchButton = new JButton("LAUNCH");
        launchButton.setBounds(260, 135, 200, 200); // Adjust the bounds for the big button
        launchButton.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle the launch action...
                String selectedAstronaut = (String) JOptionPane.showInputDialog(frame,
                        "Select an astronaut:", "Launch",
                        JOptionPane.QUESTION_MESSAGE, null,
                        astronautManager.getAstronautNamesArray(), null);

                String selectedSpacecraft = (String) JOptionPane.showInputDialog(frame,
                        "Select a spacecraft:", "Launch",
                        JOptionPane.QUESTION_MESSAGE, null,
                        spacecraftManager.getSpacecraftNamesArray(), null);

                if (selectedAstronaut != null && selectedSpacecraft != null) {
                    JOptionPane.showMessageDialog(frame, "Launching " + selectedAstronaut + " into " + selectedSpacecraft);
                } else {
                    JOptionPane.showMessageDialog(frame, "Launch cancelled.");
                }
            }
        });
        menuPanel.add(launchButton);

        JButton editAstronautButton = new JButton("Edit Astronaut");
        editAstronautButton.setBounds(100, 350, 150, 25);
        editAstronautButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to edit astronaut...
                List<Astronaut> astronauts = astronautManager.getAstronauts();
                if (astronauts.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No astronauts to edit.");
                    return;
                }

                String[] astronautNames = new String[astronauts.size()];
                for (int i = 0; i < astronauts.size(); i++) {
                    astronautNames[i] = astronauts.get(i).getName();
                }

                String selectedAstronautName = (String) JOptionPane.showInputDialog(frame,
                        "Select Astronaut to Edit:", "Edit Astronaut",
                        JOptionPane.QUESTION_MESSAGE, null,
                        astronautNames, astronautNames[0]);

                if (selectedAstronautName != null && !selectedAstronautName.isEmpty()) {
                    // Find the selected astronaut
                    Astronaut selectedAstronaut = null;
                    for (Astronaut astronaut : astronauts) {
                        if (astronaut.getName().equals(selectedAstronautName)) {
                            selectedAstronaut = astronaut;
                            break;
                        }
                    }

                    if (selectedAstronaut != null) {
                        // Display dialog to edit astronaut info
                        String name = JOptionPane.showInputDialog(frame, "Enter new name:", selectedAstronaut.getName());
                        String email = JOptionPane.showInputDialog(frame, "Enter new email:", selectedAstronaut.getEmailAddress());
                        int dob = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter new date of birth:", selectedAstronaut.getDateOfBirth()));
                        int serialNumber = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter new serial number:", selectedAstronaut.getSerialNumber()));
                        int phoneNumber = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter new phone number:", selectedAstronaut.getPhoneNumber()));
                        String address = JOptionPane.showInputDialog(frame, "Enter new address:", selectedAstronaut.getAddress());
                        double payRate = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter new pay rate:", selectedAstronaut.getPayRate()));
                        double weight = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter new weight:", selectedAstronaut.getWeight()));

                        // Update astronaut information
                        selectedAstronaut.setName(name);
                        selectedAstronaut.setEmailAddress(email);
                        selectedAstronaut.setDateOfBirth(dob);
                        selectedAstronaut.setSerialNumber(serialNumber);
                        selectedAstronaut.setPhoneNumber(phoneNumber);
                        selectedAstronaut.setAddress(address);
                        selectedAstronaut.setPayRate(payRate);
                        selectedAstronaut.setWeight(weight);

                        saveAstronauts(); // Save the updated list of astronauts
                        JOptionPane.showMessageDialog(frame, "Astronaut information updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Selected astronaut not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please select an astronaut to edit.");
                }
            }

            private void saveAstronauts() {
                throw new UnsupportedOperationException("Unimplemented method 'saveAstronauts'");
            }
        });
        menuPanel.add(editAstronautButton);

        frame.add(menuPanel);
        frame.revalidate();
        frame.repaint();
    }

    @SuppressWarnings("unused")
    private void launchMission() {
        // Ask for astronaut and spacecraft selection
        String selectedAstronaut = (String) JOptionPane.showInputDialog(frame,
                "Select Astronaut for the Mission:", "Mission Setup",
                JOptionPane.QUESTION_MESSAGE, null,
                astronautManager.getAstronautNamesArray(), null);

        String selectedSpacecraft = (String) JOptionPane.showInputDialog(frame,
                "Select Spacecraft for the Mission:", "Mission Setup",
                JOptionPane.QUESTION_MESSAGE, null,
                spacecraftManager.getSpacecraftNamesArray(), null);

        if (selectedAstronaut != null && selectedSpacecraft != null) {
            JOptionPane.showMessageDialog(frame, "Mission launched with Astronaut: " + selectedAstronaut +
                    " and Spacecraft: " + selectedSpacecraft);
        } else {
            JOptionPane.showMessageDialog(frame, "Mission aborted. Astronaut or Spacecraft not selected.");
        }
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
                frame.remove(welcomePanel);
                createMenuPanel();
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