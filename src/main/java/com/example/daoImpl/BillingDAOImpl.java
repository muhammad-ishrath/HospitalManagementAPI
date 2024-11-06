
package com.example.daoImpl;
import com.example.dao.BillingDAO;
import com.example.model.Doctor;
import com.example.model.Billing;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillingDAOImpl implements BillingDAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingDAOImpl.class);
    private static List<Billing> billings = new ArrayList<>();
    
    static {
        Patient patient1 = new Patient(111, "John Doe", "john.doe@email.com", "123 Main St.", "Medical History", "Healthy");
        Doctor doctor1 = new Doctor(112, "Dermatology", "Dr. Jane Smith", "555-1234", "Hospital A");
        billings.add(new Billing(8989,patient1, doctor1, "Paid", "Regular checkup", 100.0));

        Patient patient2 = new Patient(113, "Bob Johnson", "bob.johnson@email.com", "456 Oak Rd.", "Medical History", "Healthy");
        Doctor doctor2 = new Doctor(114, "Cardiology", "Dr. Michael Brown", "555-5678", "Hospital B");
        billings.add(new Billing(4453,patient2, doctor2, "Unpaid", "Emergency visit", 500.0));
    }
    
    @Override
    public List<Billing> getAllBillings(){
        LOGGER.info("Getting all billings");
        return billings;
    }
    
    @Override
    public Billing getBillingById(int id) {
        LOGGER.info("Getting billing with ID: {}", id);
        for (Billing billing : billings) {
            if (billing.getId() == id) {
                return billing;
            }
        }
        return null;
    }

    @Override
    public void addBilling(Billing billing) {
        LOGGER.info("Adding billing: {}", billing);
        int newBillingId = getNextBillingId();
        billing.setId(newBillingId);
        billings.add(billing);
    }

    @Override
    public void updateBilling(Billing updatedBilling) {
        LOGGER.info("Updating billing: {}", updatedBilling);
        for (int i = 0; i < billings.size(); i++) {
            Billing billing = billings.get(i);
            if (billing.getId()== updatedBilling.getId()) {
                billings.set(i, updatedBilling);
                System.out.println("Billing updated: " + updatedBilling);
                return;
            }
        }
    }

    @Override
    public void deleteBilling(int id) {
        LOGGER.info("Deleting billing with ID: {}", id);
        billings.removeIf(billing -> billing.getId() == id);
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getNextBillingId() {
        LOGGER.info("Getting next billing ID");
        // Initialize maxUserId with a value lower than any possible userId
        int maxBillingId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Billing billing : billings) {
            int billingId = billing.getId();
            if (billingId > maxBillingId) {
                maxBillingId = billingId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxBillingId + 1;
    }
}
