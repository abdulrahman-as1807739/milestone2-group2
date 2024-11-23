package VehicleTransfer;

public class Bill {
	
	    private String bill_Id;
	    private double amount;
	    private boolean paid;

	    public Bill(String billId, double amount, boolean paid) {
	        this.bill_Id = billId;
	        this.amount = amount;
	        this.paid = paid;
	    }

	    public boolean isPaid() {
	        return paid;
	    }

	    public void markAsPaid() {
	        this.paid = true;
	    }

	    public double getAmount() {
	        return amount;
	    }
	

}
