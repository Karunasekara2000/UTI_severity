package com.uro_alert.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDto {
    //analysis
    private int id;
    private int Age;
    private int Color;
    private int Transparency;
    private double pH;
    private int Glucose;
    private int Protein;
    private double SpecificGravity;
    private int WBC;
    private int RBC;
    private int EpithelialCells;
    private int MucousThreads;
    private int AmorphousUrates;
    private int Bacteria;
    private int FEMALE;
    //other
    private int demoAge;
    private boolean isWhite;
    private boolean isVeteran;
    private boolean resistanceSXT14;
    private boolean resistanceNIT14;
    private boolean resistanceLVX14;
    private boolean resistanceCIP14;
    private boolean DM;
    private boolean HTN;
    private boolean CHF;
    private boolean Pulmonary;
    private boolean Renal;
    private boolean Obesity;
    private boolean Tumor;
    private boolean Liver;
    private boolean Coagulopathy;
    private boolean NeuroOther;
    private boolean nursingHome;
    private boolean ER;
    private boolean ICU;
    private boolean IP;
    private boolean OP;
    private float colonizationPressureNIT90O;
    private float colonizationPressureSXT90;
    private float colonizationPressureLVX90;
    private float colonizationPressureCIP90;

}
