package test.Generation;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {

    private Scanner scf;
    private Scanner scm;
    private Scanner scs;
    private Scanner scmf;
    private Scanner scmm;
    public static final ArrayList<String> departmentNames = new ArrayList<String>(Arrays.asList(
            "Accident and emergency (A&E)", "Admissions", "Anesthetics", "Breast Screening"
            , "Burn Center (Burn Unit or Burns Unit)", "Cardiology", "Central Sterile Services Department (CSSD)"
            , "Chaplaincy", "Coronary Care Unit (CCU)", "Critical Care", "Diagnostic Imaging", "Discharge Lounge"
            , "Elderly services", "Finance Department", "Gastroenterology", "General Services", "General Surgery"
            , "Gynecology", "Haematology", "Health & Safety", "Intensive Care Unit (ICU)", "Human Resources"
            , "Infection Control", "Information Management", "Maternity", "Medical Records", "Microbiology", "Neonatal"
            , "Nephrology", "Neurology", "Nutrition and Dietetics", "Obstetrics/Gynecology", "Occupational Therapy"
            , "Oncology", "Ophthalmology", "Orthopaedics", "Otolaryngology (Ear, Nose, and Throat)", "Pain Management"
            , "Patient Accounts", "Patient Services", "Pharmacy", "Physiotherapy", "Purchasing & Supplies", "Radiology"
            , "Radiotherapy", "Renal", "Rheumatology", "Sexual Health", "Social Work", "Urology"
    ));


    public DataGenerator() throws FileNotFoundException {
        scf = new Scanner(new File("./src/test/txts/NamesF.txt"));
        scm = new Scanner(new File("./src/test/txts/NamesM.txt"));
        scs = new Scanner(new File("./src/test/txts/Surnames.txt"));
        scmf = new Scanner(new File("./src/test/txts/MedicalFacts.txt"));
        scmm = new Scanner(new File("./src/test/txts/Medicament.txt"));
    }

    public int nextInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public char getGender() {
        return this.getBool() ? 'M' : 'F';
    }

    public String getFemaleName() {
        return scf.nextLine();
    }

    public String getMedicalFact() {
        return scmf.nextLine();
    }

    public String getMedicament() {
        return scmm.nextLine();
    }

    public String getMaleName() {
        return scm.nextLine();
    }

    public String getSurname() {
        return scs.nextLine();
    }

    public Timestamp getRandomDate() {
        return new Timestamp(ThreadLocalRandom.current().nextLong(942842538000L) + 631152000000L);
    }

    public boolean getBool() {
        return ThreadLocalRandom.current().nextBoolean();
    }

}
