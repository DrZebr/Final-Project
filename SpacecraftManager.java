import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SpacecraftManager implements Serializable {
    private static final String SPACECRAFT_FILE = "spacecraft.ser"; // Set the file path
    private List<SpaceCraft> spacecrafts;

    public SpacecraftManager() {
        spacecrafts = new ArrayList<>();
        // Load spacecrafts from file on initialization
        loadSpacecrafts();
    }

    public void addSpacecraft(SpaceCraft spacecraft) {
        spacecrafts.add(spacecraft);
        saveSpacecrafts();
    }

    public void removeSpacecraft(SpaceCraft spacecraft) {
        spacecrafts.remove(spacecraft);
        saveSpacecrafts();
    }

    public List<SpaceCraft> getSpacecrafts() {
        return spacecrafts;
    }

    public String[] getSpacecraftNamesArray() {
        List<SpaceCraft> spacecrafts = getSpacecrafts();
        String[] names = new String[spacecrafts.size()];
        for (int i = 0; i < spacecrafts.size(); i++) {
            names[i] = spacecrafts.get(i).getName();
        }
        return names;
    }

    private void saveSpacecrafts() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SPACECRAFT_FILE))) {
            if (!spacecrafts.isEmpty()) {
                outputStream.writeObject(spacecrafts);
                JOptionPane.showMessageDialog(null, "Spacecrafts saved successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No spacecrafts to save.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            JOptionPane.showMessageDialog(null, "Error saving spacecrafts: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SecurityException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Security exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSpacecrafts() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(SPACECRAFT_FILE))) {
            List<?> data = (List<?>) inputStream.readObject();
            spacecrafts.clear(); // Clear the existing list before adding new objects
            for (Object obj : data) {
                if (obj instanceof SpaceCraft) {
                    spacecrafts.add((SpaceCraft) obj);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions or ignore if file does not exist yet
            // e.printStackTrace();
            System.out.println("Spacecraft file not found. Creating new file.");
        }
    }

    public SpaceCraft getSpacecraftByName(String selectedSpacecraft) {
       
        throw new UnsupportedOperationException("Unimplemented method 'getSpacecraftByName'");
    }
}