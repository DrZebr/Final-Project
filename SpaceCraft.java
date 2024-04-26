import java.io.Serializable;

public class SpaceCraft implements Serializable {
    private static final long serialVersionUID = 1L; // Added serialVersionUID

    private String name;
    private String model;
    private int capacity;

    public SpaceCraft(String name, String model, int capacity) {
        this.name = name;
        this.model = model;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    // Other getters and setters if needed
}