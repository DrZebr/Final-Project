import java.io.Serializable;

public class Astronaut implements Serializable {
    private String name;
    private String emailAddress;
    private int dateOfBirth;
    private int serialNumber;
    private int phoneNumber;
    private String address;

    public Astronaut(String name, String emailAddress, int dateOfBirth, int serialNumber, int phoneNumber, String address) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.serialNumber = serialNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public int getDateOfBirth() {
        return dateOfBirth;
    }
    
    public int getSerialNumber() {
        return serialNumber;
    }
    
    public int getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getAddress() {
        return address;
    }
}