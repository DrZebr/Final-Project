import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AstronautManager implements Serializable {
    private List<Astronaut> astronauts;

    public AstronautManager() {
        this.astronauts = new ArrayList<>();
    }

    public void addAstronaut(Astronaut astronaut) {
        System.out.println("Astronaut name: "+ astronaut.getName());
        astronauts.add(astronaut);
    }
        
    public List<Astronaut> getAstronauts() {
        return astronauts;
    }
}
