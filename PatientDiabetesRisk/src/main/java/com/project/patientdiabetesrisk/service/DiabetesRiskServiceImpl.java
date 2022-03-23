package com.project.patientdiabetesrisk.service;

import com.project.patientdiabetesrisk.constant.RiskLevels;
import com.project.patientdiabetesrisk.dto.PatientNoteRequest;
import com.project.patientdiabetesrisk.dto.PatientRequest;
import com.project.patientdiabetesrisk.proxy.PatientHistoricalMicroService;
import com.project.patientdiabetesrisk.proxy.PatientMicroService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DiabetesRiskServiceImpl implements DiabetesRiskService {

    private final PatientHistoricalMicroService patientHistoricalMicroService;
    private final PatientMicroService patientMicroService;

    /**
     * The medical keyword trigger
     */
    private final String[] keywords = {"Hemoglobin A1C", "Microalbumin",
            "Height", "Weight",
            "Smoker", "Abnormal", "Cholesterol",
            "Dizziness", "Relapse", "Reaction", "Antibodies"};

    public DiabetesRiskServiceImpl(PatientHistoricalMicroService patientHistoricalMicroService, PatientMicroService patientMicroService) {
        this.patientHistoricalMicroService = patientHistoricalMicroService;
        this.patientMicroService = patientMicroService;
    }


    public String finalTextResult(Integer patientId) {

        PatientRequest patientRequest = patientMicroService.getPatientById(patientId);
        String riskLevel = evaluateRiskLevel(patientRequest);

        return "Patient : "
                +patientRequest.getLastName()+" "
                +patientRequest.getFirstName()
                +" "+" age "
                + calculateAge(patientRequest)
                +" "+" diabetes risk level is : "+riskLevel;

    }

    /**
     * Methode to evaluate the risk level
     * @param patient the patient
     * @return the risk level
     */
    private String evaluateRiskLevel(PatientRequest patient) {

        List<PatientNoteRequest> notes  = patientHistoricalMicroService.getPatientNoteList(patient.getId());

        int age = calculateAge(patient);
        int trigger = getTriggerForOnePatient(notes);
        String sex = patient.getSex();
        RiskLevels riskLevel = getRiskLevel(age,trigger,sex);

        System.out.println("Patient is "+ age + "years old, with "+ trigger +"triggers keywords, so his risk level is "+ riskLevel );

        return riskLevel.getRiskLevel();
    }

    /**
     * Method to get the risk level
     * @param age the age
     * @param trigger the number of triggers
     * @param sex the sex
     * @return A risk level
     */
    private RiskLevels getRiskLevel(int age, int trigger, String sex) {

        if(isEarlyOnSet(age, trigger, sex)) {

            return RiskLevels.EARLY_ONSET;
        }

        if(isInDanger(age,trigger,sex)) {

            return RiskLevels.IN_DANGER;
        }

        if(isBorderline(age, trigger)) {

            return RiskLevels.BORDERLINE;
        }

        return RiskLevels.NONE;
    }

    /**
     * Method to calculate Patient age
     * @param patient the patient
     * @return the age
     */
    private Integer calculateAge(PatientRequest patient) {

        LocalDate birthDate = patient.getBirthDate();

        Integer year = birthDate.getYear();

        Integer now = LocalDate.now().getYear();

        return now - year;

    }

    /**
     * Method to get the number of trigger for a patient
     * @param notes the list of patient's note
     * @return the number of trigger
     */
    private int getTriggerForOnePatient(List<PatientNoteRequest> notes) {

        AtomicInteger countTrigger = new AtomicInteger();

        notes.forEach(patientNoteRequest -> {

            String note = patientNoteRequest.getNote();

            for (String keyword : keywords) {

                if (note.contains(keyword)) {

                    countTrigger.incrementAndGet();
                }
            }
        });

        return countTrigger.get();
    }

    /**
     * Method to evaluate if risk level is borderline
     * @param age the age
     * @param trigger the number of trigger
     * @return  patient's risk lvl is borderline
     */
    private boolean isBorderline(int age, int trigger) {

        int NUM_BORDERLINE_TRIGGERS = 2;
        return (age > 30) && (trigger >= NUM_BORDERLINE_TRIGGERS);

    }

    /**
     * Method to evaluate if risk level is borderline
     * @param age the age
     * @param trigger the number of trigger
     * @param sex the patient sex
     * @return patient's risk lvl is In Danger
     */
    private boolean isInDanger(int age, int trigger, String sex) {


        if (age < 30) {

            if (sex.equals("M")) {

                int NUM_IN_DANGER_TRIGGERS_MALE_UNDER_30 = 3;
                if (trigger >= NUM_IN_DANGER_TRIGGERS_MALE_UNDER_30)
                    return true;

            }
            {
                int NUM_IN_DANGER_TRIGGERS_FEMALE_UNDER_30 = 4;
                if (trigger >= NUM_IN_DANGER_TRIGGERS_FEMALE_UNDER_30) {
                    return true;
                }
            }
        }

        int NUM_IN_DANGER_TRIGGERS_OVER_30 = 6;
        return trigger >= NUM_IN_DANGER_TRIGGERS_OVER_30;

    }

    /**
     * Method to evaluate if risk is EarlyOnSet
     * @param age the age
     * @param trigger the trigger
     * @param sex the sex
     * @return patient's risk lvl is EarlyOnSet
     */
    private boolean isEarlyOnSet(int age, int trigger, String sex) {

        if (age < 30) {

                if (sex.equals("M")) {

                    int NUM_EARLY_ONSET_TRIGGERS_MALE = 5;
                    if (trigger >= NUM_EARLY_ONSET_TRIGGERS_MALE)

                    return true;
            }
                else {

                    int NUM_EARLY_ONSET_TRIGGERS_FEMALE_UNDER_30 = 7;
                    return trigger >= NUM_EARLY_ONSET_TRIGGERS_FEMALE_UNDER_30;

            }

        }

        int NUM_EARLY_ONSET_TRIGGERS_OVER_30 = 8;
        return trigger >= NUM_EARLY_ONSET_TRIGGERS_OVER_30;

    }
}

