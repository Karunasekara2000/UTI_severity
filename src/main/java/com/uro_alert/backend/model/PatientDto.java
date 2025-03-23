package com.uro_alert.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDto  implements Serializable{

    private int id;
    private int age;
    private String color;
    private String transparency;
    @JsonProperty("pH")
    private double pH;
    private String glucose;
    private String protein;
    private double specificGravity;
    @JsonProperty("wBC")
    private int wBC;
    @JsonProperty("rBC")
    private int rBC;
    private String epithelialCells;
    private String mucousThreads;
    private String amorphousUrates;
    private String bacteria;
    @JsonProperty("fEMALE")
    private int fEMALE;
    private int demoAge;
    @JsonProperty("isWhite")
    private boolean isWhite;
    @JsonProperty("isVeteran")
    private boolean isVeteran;
    private boolean resistanceSXT14;
    private boolean resistanceNIT14;
    private boolean resistanceLVX14;
    private boolean resistanceCIP14;
    @JsonProperty("dM")
    private boolean dM;
    @JsonProperty("hTN")
    private boolean hTN;
    @JsonProperty("cHF")
    private boolean cHF;
    private boolean pulmonary;
    private boolean renal;
    private boolean obesity;
    private boolean tumor;
    private boolean liver;
    private boolean coagulopathy;
    private boolean neuroOther;
    private boolean nursingHome;
    @JsonProperty("eR")
    private boolean eR;
    @JsonProperty("iCU")
    private boolean iCU;
    @JsonProperty("iP")
    private boolean iP;
    @JsonProperty("oP")
    private boolean oP;
    private float colonizationPressureNIT90O;
    private float colonizationPressureSXT90;
    private float colonizationPressureLVX90;
    private float colonizationPressureCIP90;
    private String prediction;
    private String treatment;
    private String doctorsRecommendation;

}
