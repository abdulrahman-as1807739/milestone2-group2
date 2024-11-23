package Model;

public class Vehicle {
	
	
	// Attributes 
	private String vin;
    private Owner owner;

    
    // Parameterized constructor 
    public Vehicle(String vin, Owner owner) {
        this.vin = vin;
        this.owner = owner;
    }
    
    // Getters and setters
    public String getVin() {
        return vin;
    }
    public Owner getOwner() {
        return owner;
    }
    public void setOwner(Owner newOwner) {
        this.owner = newOwner;
    }
 
}
	
	
	
	
	
	


