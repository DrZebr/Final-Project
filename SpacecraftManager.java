import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpacecraftManager implements Serializable {
    private List<SpaceCraft> spacecrafts;

    public SpacecraftManager() {
        spacecrafts = new ArrayList<>();
    }

    public void addSpacecraft(SpaceCraft spacecraft) {
        spacecrafts.add(spacecraft);
    }

    public void removeSpacecraft(SpaceCraft spacecraft) {
        spacecrafts.remove(spacecraft);
    }

    public List<SpaceCraft> getSpacecrafts() {
        return spacecrafts;
    }
}