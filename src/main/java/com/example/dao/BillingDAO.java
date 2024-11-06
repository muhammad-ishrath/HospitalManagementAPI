
package com.example.dao;

import com.example.model.Billing;
import java.util.List;

public interface BillingDAO {
    List<Billing> getAllBillings();
    Billing getBillingById(int id);
    void addBilling(Billing billing);
    void updateBilling(Billing updatedBilling);
    void deleteBilling(int id);
    int getNextBillingId(); 
}
