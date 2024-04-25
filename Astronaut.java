import java.io.Serializable;

public class Astronaut implements Serializable {
    private String name;
    private String emailAddress;
    private int dateOfBirth;
    private int serialNumber;
    private int phoneNumber;
    private String address;
    private double payRate; // New attribute for pay rate
    private double weight; // New attribute for weight
    private String status; // New attribute for status
    private String maritalStatus; // New attribute for marital status
    private boolean hasChildren; // New attribute for children status

    // Constructor with new attributes
    public Astronaut(String name, String emailAddress, int dateOfBirth, int serialNumber, int phoneNumber, String address, double payRate, double weight) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.serialNumber = serialNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.payRate = payRate;
        this.weight = weight;
    }

    // Getters and setters for new attributes
    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }
    
    public int getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public int getSerialNumber() {
        return this.serialNumber;
    }
    
    public int getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String getName() {
        return this.name;
    }

    // Getters and setters for status, marital status, and children
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public boolean hasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public void setSerialNumber(int serialNumber2) {
        throw new UnsupportedOperationException("Unimplemented method 'setSerialNumber'");
    }

    public void setDateOfBirth(int dob) {
        throw new UnsupportedOperationException("Unimplemented method 'setDateOfBirth'");
    }

    public void setEmailAddress(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'setEmailAddress'");
    }

    public void setName(String name2) {
        throw new UnsupportedOperationException("Unimplemented method 'setName'");
    }

    public void setAddress(String address2) {
        throw new UnsupportedOperationException("Unimplemented method 'setAddress'");
    }

    public void setPhoneNumber(int phoneNumber2) {
        throw new UnsupportedOperationException("Unimplemented method 'setPhoneNumber'");
    }
}