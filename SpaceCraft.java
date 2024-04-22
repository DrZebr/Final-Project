import java.io.Serializable;

public class Spacecraft implements Serializable {
    private String name;
    private String model;
    private int capacity;

    // Constructor
    public Spacecraft(String name, String model, int capacity) {
        this.name = name;
        this.model = model;
        this.capacity = capacity;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // toString method
    @Override
    public String toString() {
        return "Spacecraft{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}