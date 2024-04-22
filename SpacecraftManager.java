import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SpacecraftManager {
    private List<Spacecraft> spacecrafts;
    private static final String SPACECRAFTS_FILE = "spacecrafts.dat";

    public SpacecraftManager() {
        this.spacecrafts = loadSpacecrafts();
    }

    public void addSpacecraft(Spacecraft spacecraft) {
        spacecrafts.add(spacecraft);
        saveSpacecrafts();
    }

    public void removeSpacecraft(Spacecraft spacecraft) {
        spacecrafts.remove(spacecraft);
        saveSpacecrafts();
    }

    public List<Spacecraft> getSpacecrafts() {
        return spacecrafts;
    }

    private void saveSpacecrafts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SPACECRAFTS_FILE))) {
            oos.writeObject(spacecrafts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Spacecraft> loadSpacecrafts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SPACECRAFTS_FILE))) {
            return (List<Spacecraft>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}