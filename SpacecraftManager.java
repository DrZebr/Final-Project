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

    public String[] getSpacecraftNamesArray() {
        List<SpaceCraft> spacecrafts = getSpacecrafts();
        String[] names = new String[spacecrafts.size()];
        for (int i = 0; i < spacecrafts.size(); i++) {
            names[i] = spacecrafts.get(i).getName();
        }
        return names;
    }

    public void setSpacecrafts(List<SpaceCraft> spacecrafts2) {
        throw new UnsupportedOperationException("Unimplemented method 'setSpacecrafts'");
    }
}