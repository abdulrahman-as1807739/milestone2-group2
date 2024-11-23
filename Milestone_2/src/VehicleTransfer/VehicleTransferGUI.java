package VehicleTransfer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Model.Owner;
import Model.Vehicle;


public class VehicleTransferGUI extends JFrame {
    private List<Vehicle> vehicles;
    private List<Owner> owners;

    public VehicleTransferGUI() {
        setTitle("Transfer Registred Vehicle");

        // Initialize data for vehicles and owners, in this case it is being managed locally 
        // with no Database
        initializeData();

        // Define main UI Components
        JLabel vinLabel = new JLabel("Enter VIN:");
        JTextField vinField = new JTextField(20);
        
        JLabel ownerQidLabel = new JLabel("Current Owner QID:");
        JTextField ownerQidField = new JTextField(20);
        
        JLabel ownerNameLabel = new JLabel("Current Owner Name:");
        JTextField ownerNameField = new JTextField(20);
        
        JLabel newOwnerQidLabel = new JLabel("New Owner QID:");
        JTextField newOwnerQidField = new JTextField(20);

        JButton transferButton = new JButton("Transfer Vehicle");
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vin = vinField.getText().trim();
                String ownerQid = ownerQidField.getText().trim();
                String ownerName = ownerNameField.getText().trim();
                String newOwnerQid = newOwnerQidField.getText().trim();

                Vehicle vehicle = findVehicleByVin(vin);
                Owner currentOwner = findOwnerByQid(ownerQid);
                Owner newOwner = findOwnerByQid(newOwnerQid);

                // Validation 
                if (vehicle == null) {
                    JOptionPane.showMessageDialog(VehicleTransferGUI.this, "Incorrect Information.");
                } 
                  else if (currentOwner == null || // Verify the correctness of information
                		!vehicle.getOwner().getQid().equals(currentOwner.getQid()) ||
                        !vehicle.getOwner().getName().equalsIgnoreCase(ownerName)) {
                    JOptionPane.showMessageDialog(VehicleTransferGUI.this, "Incorrect Information.");
                } 
                  else if (newOwner == null) {
                    JOptionPane.showMessageDialog(VehicleTransferGUI.this, "Incorrect Information.");
                    
                } else if (hasUnpaidBills(currentOwner)) {
                	 JOptionPane.showMessageDialog(VehicleTransferGUI.this, "Pay the bills first.");
                	 
                } else {
                    // Make the transfer
                    vehicle.setOwner(newOwner); // set the new owner
                    prepareRegistrationSticker(vehicle); // prepare registration sticker
                    Invoice invoice = new Invoice(vehicle);
                    invoice.createInvoice(); // create invoice
                    
                    JOptionPane.showMessageDialog(VehicleTransferGUI.this, "Transfer completed");
                }
            }
        });

        // Layout, add all components to panel
        JPanel panel = new JPanel();
        panel.add(vinLabel);
        panel.add(vinField);
        panel.add(ownerQidLabel);
        panel.add(ownerQidField);
        panel.add(ownerNameLabel);
        panel.add(ownerNameField);
        panel.add(newOwnerQidLabel);
        panel.add(newOwnerQidField);
        panel.add(transferButton);

        add(panel);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    
    
    private void initializeData() {
        // using some data for testing
        owners = new ArrayList<>();
        owners.add(new Owner("1234", "Ali"));
        owners.add(new Owner("4321", "faisal"));
        
        vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("V1234", owners.get(0))); 
        vehicles.add(new Vehicle("V4321", owners.get(1))); 
    }

    
   
    public Vehicle findVehicleByVin(String vin) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin().equalsIgnoreCase(vin)) {
                return vehicle;
            }
        }
        return null;
     }
    private Owner findOwnerByQid(String qid) {
        for (Owner owner : owners) {
            if (owner.getQid().equalsIgnoreCase(qid)) {
                return owner;
            }
        }
        return null;
      }
    private void prepareRegistrationSticker(Vehicle vehicle) {
    	System.out.println("Registration Sticker For VIN: " + vehicle.getVin());
    }
    private boolean hasUnpaidBills(Owner owner) {
    	for(Bill bill: owner.getBills()) {
    		if(!bill.isPaid()) {
    			return true;
    	  }
    	}
    		return false;
    }

    
    
    public static void main(String[] args) {
        new VehicleTransferGUI();
    }
}