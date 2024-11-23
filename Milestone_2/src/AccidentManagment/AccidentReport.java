package AccidentManagment;
import Model.Vehicle;


public class AccidentReport {
	
	    private Vehicle offendingVehicle;
	    private Vehicle victimVehicle;
	    private String details;
	    
	    

	    public AccidentReport(Vehicle offendingVehicle, Vehicle victimVehicle, String details) {
	        this.offendingVehicle = offendingVehicle;
	        this.victimVehicle = victimVehicle;
	        this.details = details;
	    }
	    
	    public String getDetails() {
	        return details;
	    }

	    public void sendToInsuranceCompany() {
	        InsuranceCompany insurance = new InsuranceCompany();
	        insurance.processClaim(this);
	        System.out.println("Accident report sent to insurance company.");
	    }
}
	
	
	


