package com.uro_alert.backend.service;

import com.uro_alert.backend.model.PatientDto;
import com.uro_alert.backend.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientDto> viewAllPatients() {
        return patientRepository.viewAllPatients();
    }

    @Override
    public PatientDto save(PatientDto patientDto) {
        return patientRepository.save(patientDto);
    }

    @Override
    public PatientDto viewPatientById(int id) {
        return patientRepository.viewPatientById(id);
    }

    @Override
    public boolean deletePatientById(int id) {
        return patientRepository.deletePatientById(id);
    }
}
