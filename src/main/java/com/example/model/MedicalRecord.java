
package com.example.model;


public class MedicalRecord {
    private int id;
    private Patient patient;
    private String diagnoses;
    private String treatments;

    public MedicalRecord(){}
    
    public MedicalRecord(int id, Patient patient, String diagnoses, String treatments) {
        this.patient = patient;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" + "id=" + id + ", patient=" + patient + ", diagnoses=" + diagnoses + ", treatments=" + treatments + '}';
    }
    
    
}

