
package com.example.model;


public class Prescription {
    private int id;
    private Patient patient;
    private String medicationName;
    private String dosage;
    private String instructions;
    private String duration;
    
    public Prescription(){}

    public Prescription(int id, Patient patient, String medicationName, String dosage, String instructions, String duration) {
        this.patient = patient;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.instructions = instructions;
        this.duration = duration;
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Prescription{" + "id=" + id + ", patient=" + patient + ", medicationName=" + medicationName + ", dosage=" + dosage + ", instructions=" + instructions + ", duration=" + duration + '}';
    }
   
}
