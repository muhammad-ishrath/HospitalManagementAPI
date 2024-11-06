
package com.example.dao;

import com.example.model.MedicalRecord;
import java.util.List;

public interface MedicalRecordDAO {
    List<MedicalRecord> getAllMedicalRecords();
    MedicalRecord getMedicalRecordById(int id);
    void addMedicalRecord(MedicalRecord medicalRecord);
    void updateMedicalRecord(MedicalRecord updatedMedicalRecord);
    void deleteMedicalRecord(int id);
    int getNextMedicalRecordId();
}
