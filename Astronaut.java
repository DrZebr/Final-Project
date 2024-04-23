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
    // Getters and setters omitted for brevity

    public void setAddress(String address2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAddress'");
    }

    public void setPhoneNumber(int phoneNumber2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPhoneNumber'");
    }

    public void setSerialNumber(int serialNumber2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSerialNumber'");
    }

    public void setDateOfBirth(int dob) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDateOfBirth'");
    }

    public void setEmailAddress(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEmailAddress'");
    }

    public void setName(String name2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setName'");
    }
}