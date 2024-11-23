package AccidentManagment;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Model.Owner;
import Model.Vehicle;




public class AccidentManagementGUI extends JFrame {
    private List<Vehicle> vehicles;
    private List<Owner> owners;

    public AccidentManagementGUI() {
        setTitle("Manage accident");

        // Initialize data for vehicles and owners, in this case it is being managed locally 
        // with no Database
        initializeData();

        // Define main UI Components
        JLabel vinOffendingLabel = new JLabel("Offending Vehicle VIN:");
        JTextField vinOffendingField = new JTextField(20);
        
        JLabel vinVictimLabel = new JLabel("Victim Vehicle VIN:");
        JTextField vinVictimField = new JTextField(20);
        
        JLabel detailsLabel = new JLabel("Accident Details:");
        JTextArea detailsArea = new JTextArea(5, 20);

        JButton reportButton = new JButton("Report Accident");
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vinOffending = vinOffendingField.getText().trim();
                String vinVictim = vinVictimField.getText().trim();
                String accidentDetails = detailsArea.getText().trim();

                Vehicle offendingVehicle = findVehicleByVin(vinOffending);
                Vehicle victimVehicle = findVehicleByVin(vinVictim);

                // Validation 
                if (offendingVehicle == null) {
                    JOptionPane.showMessageDialog(AccidentManagementGUI.this, "Not found.");
                } else if (victimVehicle == null) {
                    JOptionPane.showMessageDialog(AccidentManagementGUI.this, "Not found.");
                } else if (offendingVehicle == victimVehicle) {
                    JOptionPane.showMessageDialog(AccidentManagementGUI.this, "Cannot be the same.");
                } else {
                    // Create and send accident report
                    AccidentReport report = new AccidentReport(offendingVehicle, victimVehicle, accidentDetails);
                    report.sendToInsuranceCompany(); // Send the accident report to the insurance company
                    InsuranceCompany insuranceCompany = new InsuranceCompany();
                    insuranceCompany.processClaim(report); // Process the report and send acknowledgement 
                    
                    JOptionPane.showMessageDialog(AccidentManagementGUI.this, "Accident reported successfully.");
                }
            }
        });

        // Add components to panel
        JPanel panel = new JPanel();
        panel.add(vinOffendingLabel);
        panel.add(vinOffendingField);
        panel.add(vinVictimLabel);
        panel.add(vinVictimField);
        panel.add(detailsLabel);
        panel.add(detailsArea);
        panel.add(reportButton);

        add(panel);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeData() {
        // Initialize some data for testing
        owners = new ArrayList<>();
        owners.add(new Owner("1234", "Ali"));
        owners.add(new Owner("4321", "Faisal"));

        vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("V1234", owners.get(0))); 
        vehicles.add(new Vehicle("V4321", owners.get(1))); 
    }

    private Vehicle findVehicleByVin(String vin) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin().equalsIgnoreCase(vin)) {
                return vehicle;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new AccidentManagementGUI();
    }
}