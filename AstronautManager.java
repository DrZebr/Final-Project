import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AstronautManager implements Serializable {
    private List<Astronaut> astronauts;

    public AstronautManager() {
        astronauts = new ArrayList<>();
    }

    public void addAstronaut(Astronaut astronaut) {
        astronauts.add(astronaut);
    }

    public void removeAstronaut(String name) {
        for (Iterator<Astronaut> iterator = astronauts.iterator(); iterator.hasNext();) {
            Astronaut astronaut = iterator.next();
            if (astronaut.getName().equals(name)) {
                iterator.remove();
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

}
