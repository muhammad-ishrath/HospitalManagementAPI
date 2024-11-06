
package com.example.model;


public class Billing {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String invoiceDetails;
    private String paymentDetails;
    private double outstandingBalance;
    
    public Billing(){}

    public Billing(int id, Patient patient, Doctor doctor, String invoiceDetails, String paymentDetails, double outstandingBalance) {
        this.patient = patient;
        this.doctor = doctor;
        this.invoiceDetails = invoiceDetails;
        this.paymentDetails = paymentDetails;
        this.outstandingBalance = outstandingBalance;
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(String invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Billing{" + "id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", invoiceDetails=" + invoiceDetails + ", paymentDetails=" + paymentDetails + ", outstandingBalance=" + outstandingBalance + '}';
    }
    
    
}

