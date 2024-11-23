package AccidentManagment;
import Model.Vehicle;


	
public class InsuranceCompany {
	
    private String companyName;
    private String policyNumber;

    public InsuranceCompany() {
        this.companyName = "Ex ... ";
        this.policyNumber = "Ex ... ";;
    }

    public void processClaim(AccidentReport report) {
  
        System.out.println("Sending accident report to " + companyName);
        System.out.println("Policy Number: " + policyNumber);
        System.out.println("Accident Details: " + report.getDetails());
   
        sendAcknowledgement();
    }

    private void sendAcknowledgement() {
        System.out.println("Acknowledgement received from " + companyName);
      }
	}


