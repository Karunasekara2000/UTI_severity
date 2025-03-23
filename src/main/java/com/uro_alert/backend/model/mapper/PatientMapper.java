package com.uro_alert.backend.model.mapper;

import com.uro_alert.backend.model.PatientDto;
import com.uro_alert.backend.model.User;
import com.uro_alert.backend.model.enumeration.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PatientMapper implements RowMapper<PatientDto> {

    @Override
    public PatientDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PatientDto.builder()
                .id(rs.getInt("id"))
                .age(rs.getInt("Age"))
                .color(rs.getString("Color"))
                .transparency(rs.getString("Transparency"))
                .pH(rs.getDouble("pH"))
                .glucose(rs.getString("Glucose"))
                .protein(rs.getString("Protein"))
                .specificGravity(rs.getDouble("SpecificGravity"))
                .wBC(rs.getInt("WBC"))
                .rBC(rs.getInt("RBC"))
                .epithelialCells(rs.getString("EpithelialCells"))
                .mucousThreads(rs.getString("MucousThreads"))
                .amorphousUrates(rs.getString("AmorphousUrates"))
                .bacteria(rs.getString("Bacteria"))
                .fEMALE(rs.getInt("FEMALE"))
                .demoAge(rs.getInt("demoAge"))
                .isWhite(rs.getBoolean("isWhite"))
                .isVeteran(rs.getBoolean("isVeteran"))
                .resistanceSXT14(rs.getBoolean("resistanceSXT14"))
                .resistanceNIT14(rs.getBoolean("resistanceNIT14"))
                .resistanceLVX14(rs.getBoolean("resistanceLVX14"))
                .resistanceCIP14(rs.getBoolean("resistanceCIP14"))
                .dM(rs.getBoolean("DM"))
                .hTN(rs.getBoolean("HTN"))
                .cHF(rs.getBoolean("CHF"))
                .pulmonary(rs.getBoolean("Pulmonary"))
                .renal(rs.getBoolean("Renal"))
                .obesity(rs.getBoolean("Obesity"))
                .tumor(rs.getBoolean("Tumor"))
                .liver(rs.getBoolean("Liver"))
                .coagulopathy(rs.getBoolean("Coagulopathy"))
                .neuroOther(rs.getBoolean("NeuroOther"))
                .nursingHome(rs.getBoolean("nursingHome"))
                .eR(rs.getBoolean("ER"))
                .iCU(rs.getBoolean("ICU"))
                .iP(rs.getBoolean("IP"))
                .oP(rs.getBoolean("OP"))
                .colonizationPressureNIT90O(rs.getFloat("colonizationPressureNIT90O"))
                .colonizationPressureSXT90(rs.getFloat("colonizationPressureSXT90"))
                .colonizationPressureLVX90(rs.getFloat("colonizationPressureLVX90"))
                .colonizationPressureCIP90(rs.getFloat("colonizationPressureCIP90"))
                .prediction(rs.getString("prediction"))
                .treatment(rs.getString("treatment"))
                .doctorsRecommendation(rs.getString("doctor_recommendation"))
                .build();
    }
}
