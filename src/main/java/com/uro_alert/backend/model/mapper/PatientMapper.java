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
                .Age(rs.getInt("Age"))
                .Color(rs.getInt("Color"))
                .Transparency(rs.getInt("Transparency"))
                .pH(rs.getDouble("pH"))
                .Glucose(rs.getInt("Glucose"))
                .Protein(rs.getInt("Protein"))
                .SpecificGravity(rs.getDouble("SpecificGravity"))
                .WBC(rs.getInt("WBC"))
                .RBC(rs.getInt("RBC"))
                .EpithelialCells(rs.getInt("EpithelialCells"))
                .MucousThreads(rs.getInt("MucousThreads"))
                .AmorphousUrates(rs.getInt("AmorphousUrates"))
                .Bacteria(rs.getInt("Bacteria"))
                .FEMALE(rs.getInt("FEMALE"))
                .demoAge(rs.getInt("demoAge"))
                .isWhite(rs.getBoolean("isWhite"))
                .isVeteran(rs.getBoolean("isVeteran"))
                .resistanceSXT14(rs.getBoolean("resistanceSXT14"))
                .resistanceNIT14(rs.getBoolean("resistanceNIT14"))
                .resistanceLVX14(rs.getBoolean("resistanceLVX14"))
                .resistanceCIP14(rs.getBoolean("resistanceCIP14"))
                .DM(rs.getBoolean("DM"))
                .HTN(rs.getBoolean("HTN"))
                .CHF(rs.getBoolean("CHF"))
                .Pulmonary(rs.getBoolean("Pulmonary"))
                .Renal(rs.getBoolean("Renal"))
                .Obesity(rs.getBoolean("Obesity"))
                .Tumor(rs.getBoolean("Tumor"))
                .Liver(rs.getBoolean("Liver"))
                .Coagulopathy(rs.getBoolean("Coagulopathy"))
                .NeuroOther(rs.getBoolean("NeuroOther"))
                .nursingHome(rs.getBoolean("nursingHome"))
                .ER(rs.getBoolean("ER"))
                .ICU(rs.getBoolean("ICU"))
                .IP(rs.getBoolean("IP"))
                .OP(rs.getBoolean("OP"))
                .colonizationPressureNIT90O(rs.getFloat("colonizationPressureNIT90O"))
                .colonizationPressureSXT90(rs.getFloat("colonizationPressureSXT90"))
                .colonizationPressureLVX90(rs.getFloat("colonizationPressureLVX90"))
                .colonizationPressureCIP90(rs.getFloat("colonizationPressureCIP90"))
                .build();
    }
}
