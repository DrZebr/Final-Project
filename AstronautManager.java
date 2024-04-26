import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

public class AstronautManager implements Serializable {
    private static final String ASTRONAUTS_FILE = "astronauts.ser"; // Set the file path
    private List<Astronaut> astronauts;

    public AstronautManager() {
        astronauts = new ArrayList<>();
        // Load astronauts from file on initialization
        loadAstronauts();
    }

    public void addAstronaut(Astronaut astronaut) {
        astronauts.add(astronaut);
        saveAstronauts();
    }

    public void removeAstronaut(String name) {
        for (Iterator<Astronaut> iterator = astronauts.iterator(); iterator.hasNext();) {
            Astronaut astronaut = iterator.next();
            if (astronaut.getName().equals(name)) {
                iterator.remove();
                saveAstronauts();
                return;
            }
        }
        System.out.println("Astronaut with name " + name + " not found.");
    }

    public List<Astronaut> getAstronauts() {
        return astronauts;
    }

    public String[] getAstronautNamesArray() {
        List<Astronaut> astronauts = getAstronauts();
        String[] names = new String[astronauts.size()];
        for (int i = 0; i < astronauts.size(); i++) {
            names[i] = astronauts.get(i).getName();
        }
        return names;
    }

    private void saveAstronauts() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ASTRONAUTS_FILE))) {
            outputStream.writeObject(astronauts);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving astronauts: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadAstronauts() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ASTRONAUTS_FILE))) {
            astronauts = (List<Astronaut>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions or ignore if file does not exist yet
            // e.printStackTrace();
            System.out.println("Astronaut file not found. Creating new file.");
        }
    }


    public void setAstronauts(List<Astronaut> astronauts2) {
        throw new UnsupportedOperationException("Unimplemented method 'setAstronauts'");
    }
}