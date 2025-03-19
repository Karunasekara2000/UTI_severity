package com.uro_alert.backend.repository;

import com.uro_alert.backend.model.PatientDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PatientRepository {

    List<PatientDto> viewAllPatients();
    PatientDto save(PatientDto patientDto);
    PatientDto viewPatientById(int id);
    boolean deletePatientById(int id);

}
