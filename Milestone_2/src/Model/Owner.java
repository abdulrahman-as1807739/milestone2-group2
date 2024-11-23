package Model;

import java.util.ArrayList;
import java.util.List;

import VehicleTransfer.Bill;

public class Owner {
	
	
	// Attributes 
	private String qid;
	private String name;
    private List<Bill> bills;
    
	
	// Parameterized constructor 
    public Owner(String qid, String name) {
        this.qid = qid;
        this.name = name;
        this.bills = new ArrayList<>();
    }
    
    // Getters
    public String getQid() {
        return qid;
    }
    public String getName() {
        return name;
    }
    public void addBill(Bill bill) {
        this.bills.add(bill);
    }
    public List<Bill> getBills() {
        return bills;
    }
	    
	
    
}



