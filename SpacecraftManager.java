import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpacecraftManager implements Serializable {
    private List<Spacecraft> spacecrafts;

    public SpacecraftManager() {
        spacecrafts = new ArrayList<>();
    }

    public void addSpacecraft(Spacecraft spacecraft) {
        spacecrafts.add(spacecraft);
    }

    public void removeSpacecraft(Spacecraft spacecraft) {
        spacecrafts.remove(spacecraft);
    }

    public List<Spacecraft> getSpacecrafts() {
        return spacecrafts;
    }
}