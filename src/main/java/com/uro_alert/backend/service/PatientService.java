package com.uro_alert.backend.service;

import com.uro_alert.backend.model.PatientDto;

import java.util.List;

public interface PatientService {

    List<PatientDto> viewAllPatients();
    PatientDto save(PatientDto patientDto);
    PatientDto viewPatientById(int id);
    boolean deletePatientById(int id);

}
