package com.uro_alert.backend.controller;

import com.uro_alert.backend.model.PatientDto;
import com.uro_alert.backend.service.PatientService;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.text.StringSubstitutor;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<PatientDto>> viewAllPatients() {

        return ResponseEntity.ok(patientService.viewAllPatients());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<PatientDto> viewPatientById(@PathVariable int id) {
        return ResponseEntity.ok(patientService.viewPatientById(id));
    }

    @PostMapping()
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(patientService.save(patientDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePatient(@PathVariable int id) {
        return ResponseEntity.ok(patientService.deletePatientById(id));
    }

    @GetMapping("/patient-report/{id}")
    public ResponseEntity<byte[]> generateReport(@PathVariable int id) {
        try {
            ClassPathResource resource = new ClassPathResource("templates/patient-report.docx");
            InputStream inputStream = resource.getInputStream();
            XWPFDocument document = new XWPFDocument(inputStream);

            PatientDto patient = patientService.viewPatientById(id);

            // Create your data here (example attributes clearly matching the template)
            Map<String, String> placeholders = new HashMap<>();
            placeholders.put("date", LocalDate.now().toString());
            placeholders.put("id", String.valueOf(id));
            placeholders.put("age", String.valueOf(patient.getAge()));
            placeholders.put("color", patient.getColor());
            placeholders.put("transparency", patient.getTransparency());
            placeholders.put("glucose", patient.getGlucose());
            placeholders.put("protein", patient.getProtein());
            placeholders.put("pH", String.valueOf(patient.getPH()));
            placeholders.put("specificGravity", String.valueOf(patient.getSpecificGravity()));
            placeholders.put("wBC", String.valueOf(patient.getWBC()));
            placeholders.put("rBC", String.valueOf(patient.getRBC()));
            placeholders.put("epithelialCells", patient.getEpithelialCells());
            placeholders.put("mucousThreads", patient.getMucousThreads());
            placeholders.put("amorphousUrates", patient.getAmorphousUrates());
            placeholders.put("bacteria", patient.getBacteria());
            placeholders.put("fEMALE", String.valueOf(patient.getFEMALE()));

// Resistance and Colonization
            placeholders.put("resistanceNIT14", String.valueOf(patient.isResistanceNIT14()));
            placeholders.put("resistanceSXT14", String.valueOf(patient.isResistanceSXT14()));
            placeholders.put("resistanceLVX14", String.valueOf(patient.isResistanceLVX14()));
            placeholders.put("resistanceCIP14", String.valueOf(patient.isResistanceCIP14()));
            placeholders.put("colonizationPressureNIT90O", String.valueOf(patient.getColonizationPressureNIT90O()));
            placeholders.put("colonizationPressureSXT90", String.valueOf(patient.getColonizationPressureSXT90()));
            placeholders.put("colonizationPressureLVX90", String.valueOf(patient.getColonizationPressureLVX90()));
            placeholders.put("colonizationPressureCIP90", String.valueOf(patient.getColonizationPressureCIP90()));

// Clinical Patient Details
            placeholders.put("dM", String.valueOf(patient.isDM()));
            placeholders.put("hTN", String.valueOf(patient.isHTN()));
            placeholders.put("cHF", String.valueOf(patient.isCHF()));
            placeholders.put("pulmonary", String.valueOf(patient.isPulmonary()));
            placeholders.put("renal", String.valueOf(patient.isRenal()));
            placeholders.put("obesity", String.valueOf(patient.isObesity()));
            placeholders.put("tumor", String.valueOf(patient.isTumor()));
            placeholders.put("liver", String.valueOf(patient.isLiver()));
            placeholders.put("coagulopathy", String.valueOf(patient.isCoagulopathy()));
            placeholders.put("neuroOther", String.valueOf(patient.isNeuroOther()));
            placeholders.put("nursingHome", String.valueOf(patient.isNursingHome()));
            placeholders.put("eR", String.valueOf(patient.isER()));
            placeholders.put("iCU", String.valueOf(patient.isICU()));
            placeholders.put("iP", String.valueOf(patient.isIP()));
            placeholders.put("oP", String.valueOf(patient.isOP()));

// Prediction and Treatment
            placeholders.put("prediction", patient.getPrediction());
            placeholders.put("treatment", patient.getTreatment());

            replacePlaceholders(document, placeholders);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.write(outputStream);
            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename("PatientReport_" + id + ".docx")
                    .build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(outputStream.toByteArray());

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private void replacePlaceholders(XWPFDocument document, Map<String, String> placeholders) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            replaceTextInParagraph(paragraph, placeholders);
        }
        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        replaceTextInParagraph(paragraph, placeholders);
                    }
                }
            }
        }
    }

    private void replaceTextInParagraph(XWPFParagraph paragraph, Map<String, String> placeholders) {
        String text = paragraph.getText();
        if (text.contains("${")) {
            StringSubstitutor sub = new StringSubstitutor(placeholders, "${", "}");
            String replacedText = sub.replace(text);

            // Clear existing runs
            clearParagraphRuns(paragraph);

            // Create new run with replaced text
            XWPFRun run = paragraph.createRun();
            run.setText(replacedText, 0);
        }
    }

    private void clearParagraphRuns(XWPFParagraph paragraph) {
        int size = paragraph.getRuns().size();
        for (int i = size - 1; i >= 0; i--) {
            paragraph.removeRun(i);
        }
    }

}
