import java.util.List;

public class SpaceCraft {
    private String name;
    private String model;
    private int capacity;

    public SpaceCraft(String name, String model, int capacity) {
        this.name = name;
        this.model = model;
        this.capacity = capacity;
    }
    public String getModel() {
        return this.model;
    }
    
    public int getCapacity() {
        return this.capacity;
    }
    public String getName(){
        return this.name;
    }

    // Getters and setters omitted for brevity
}