package com.uro_alert.backend.repository;

import com.uro_alert.backend.model.PatientDto;
import com.uro_alert.backend.model.mapper.PatientMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PatientRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<PatientDto> viewAllPatients() {

        StringBuilder query = new StringBuilder("SELECT id, Age, Color, Transparency, pH, Glucose, Protein, SpecificGravity, WBC, RBC, " +
                " EpithelialCells, MucousThreads, AmorphousUrates, Bacteria, FEMALE, demoAge, " +
                " isWhite, isVeteran, resistanceSXT14, resistanceNIT14, resistanceLVX14, resistanceCIP14, " +
                " DM, HTN, CHF, Pulmonary, Renal, Obesity, Tumor, Liver, Coagulopathy, NeuroOther, " +
                " nursingHome, ER, ICU, IP, OP, colonizationPressureNIT90O, colonizationPressureSXT90, " +
                " colonizationPressureLVX90, colonizationPressureCIP90" +
                "FROM patient_details");

        return namedParameterJdbcTemplate.query(query.toString(),new PatientMapper());
    }

    @Override
    public PatientDto save(PatientDto patientDto) {

        StringBuilder query = new StringBuilder("INSERT INTO Patient (Age, Color, Transparency, pH, Glucose, Protein, " +
                " SpecificGravity, WBC, RBC, EpithelialCells" +
                " MucousThreads, AmorphousUrates, Bacteria, FEMALE, demoAge, isWhite, isVeteran, " +
                " resistanceSXT14, resistanceNIT14, resistanceLVX14, resistanceCIP14, DM, HTN, CHF, " +
                " Pulmonary, Renal, Obesity, Tumor, Liver, Coagulopathy, NeuroOther, nursingHome, ER, " +
                " ICU, IP, OP, colonizationPressureNIT90O, colonizationPressureSXT90, " +
                " colonizationPressureLVX90, colonizationPressureCIP90" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                " ?, ?, ?, ?, ?, ?, ?, ?");

        KeyHolder key = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, patientDto.getAge());
            ps.setInt(2, patientDto.getColor());
            ps.setInt(3, patientDto.getTransparency());
            ps.setDouble(4, patientDto.getPH());
            ps.setInt(5, patientDto.getGlucose());
            ps.setInt(6, patientDto.getProtein());
            ps.setDouble(7, patientDto.getSpecificGravity());
            ps.setInt(8, patientDto.getWBC());
            ps.setInt(9, patientDto.getRBC());
            ps.setInt(10, patientDto.getEpithelialCells());
            ps.setInt(11, patientDto.getMucousThreads());
            ps.setInt(12, patientDto.getAmorphousUrates());
            ps.setInt(13, patientDto.getBacteria());
            ps.setInt(14, patientDto.getFEMALE());
            ps.setInt(15, patientDto.getDemoAge());
            ps.setBoolean(16, patientDto.isWhite());
            ps.setBoolean(17, patientDto.isVeteran());
            ps.setBoolean(18, patientDto.isResistanceSXT14());
            ps.setBoolean(19, patientDto.isResistanceNIT14());
            ps.setBoolean(20, patientDto.isResistanceLVX14());
            ps.setBoolean(21, patientDto.isResistanceCIP14());
            ps.setBoolean(22, patientDto.isDM());
            ps.setBoolean(23, patientDto.isHTN());
            ps.setBoolean(24, patientDto.isCHF());
            ps.setBoolean(25, patientDto.isPulmonary());
            ps.setBoolean(26, patientDto.isRenal());
            ps.setBoolean(27, patientDto.isObesity());
            ps.setBoolean(28, patientDto.isTumor());
            ps.setBoolean(29, patientDto.isLiver());
            ps.setBoolean(30, patientDto.isCoagulopathy());
            ps.setBoolean(31, patientDto.isNeuroOther());
            ps.setBoolean(32, patientDto.isNursingHome());
            ps.setBoolean(33, patientDto.isER());
            ps.setBoolean(34, patientDto.isICU());
            ps.setBoolean(35, patientDto.isIP());
            ps.setBoolean(36, patientDto.isOP());
            ps.setFloat(37, patientDto.getColonizationPressureNIT90O());
            ps.setFloat(38, patientDto.getColonizationPressureSXT90());
            ps.setFloat(39, patientDto.getColonizationPressureLVX90());
            ps.setFloat(40, patientDto.getColonizationPressureCIP90());

            return ps;
        });

        return patientDto;
    }

    @Override
    public PatientDto viewPatientById(int id) {

        StringBuilder query = new StringBuilder("SELECT id, Age, Color, Transparency, pH, Glucose, Protein, SpecificGravity, WBC, RBC, " +
                " EpithelialCells, MucousThreads, AmorphousUrates, Bacteria, FEMALE, demoAge, " +
                " isWhite, isVeteran, resistanceSXT14, resistanceNIT14, resistanceLVX14, resistanceCIP14, " +
                " DM, HTN, CHF, Pulmonary, Renal, Obesity, Tumor, Liver, Coagulopathy, NeuroOther, " +
                " nursingHome, ER, ICU, IP, OP, colonizationPressureNIT90O, colonizationPressureSXT90, " +
                " colonizationPressureLVX90, colonizationPressureCIP90" +
                "FROM patient_details WHERE id = : id");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(query.toString(), params, new PatientMapper());
    }

    @Override
    public boolean deletePatientById(int id) {

        StringBuilder query = new StringBuilder("DELETE FROM patient_details WHERE id =: id");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        int result = jdbcTemplate.update(query.toString(),params );

        return result > 0;


    }
}
