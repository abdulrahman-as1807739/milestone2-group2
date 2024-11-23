package VehicleTransfer;


import Model.Vehicle;


public class Invoice {
	
	
	// Attributes
	private String vin;
    private String ownerName;
    private double amount;
   
   
    // Parameterized constructor
    public Invoice(Vehicle vehicle) {
        this.vin = vehicle.getVin();
        this.ownerName = vehicle.getOwner().getName();
        this.amount = 0; // Assuming there is no transfer fee initially
    }
    
   

    public void createInvoice() {
        System.out.println("Invoice created:");
        System.out.println("VIN: " + vin);
        System.out.println("Owner: " + ownerName);
        System.out.println("Amount: " + amount);
    }

   

    public double getAmount() {
        return amount;
    }
}

