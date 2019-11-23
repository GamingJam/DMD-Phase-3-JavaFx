package test.Generation;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {

    private Scanner scf;
    private Scanner scm;
    private Scanner scs;
    private Scanner scmf;
    private Scanner scmm;
    private Scanner scnn;

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

    public static final ArrayList<String> roleNames = new ArrayList<>(Arrays.asList(
            "patient", "doctor", "nurse"
    ));

    public DataGenerator() throws FileNotFoundException {
        scf = new Scanner(new File("./src/test/txts/NamesF.txt"));
        scm = new Scanner(new File("./src/test/txts/NamesM.txt"));
        scs = new Scanner(new File("./src/test/txts/Surnames.txt"));
        scmf = new Scanner(new File("./src/test/txts/MedicalFacts.txt"));
        scmm = new Scanner(new File("./src/test/txts/Medicament.txt"));
        scnn = new Scanner(new File("./src/test/txts/Nicknames.txt"));
    }

    public int nextInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public String getNickname() {
        return scnn.nextLine();
    }

    public String getPassword() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

        StringBuilder ans = new StringBuilder();
        int n = this.nextInt(8, 25);

        for(int i = 0; i < n; ++i){
            ans.append(AlphaNumericString.charAt(this.nextInt(0, AlphaNumericString.length())));
        }
        return ans.toString();
    }

    public String getGender(boolean isMale) {
        return isMale ? "M" : "F";
    }

    public String getRole(){
        return roleNames.get(nextInt(0, roleNames.size()));
    }

    public String getPhone(){
        StringBuilder result = new StringBuilder("+7");
        for(int i = 0; i < 10; ++i){
            result.append(nextInt(0, 10));
        }
        assert result.length() <= 12;

        return result.toString();
    }

    public String getAddress(){
        return "null";
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

    public String getDateTime() {
        return (new Timestamp(ThreadLocalRandom.current().nextLong(942842538000L) + 631152000000L)).toString();
    }

    public String getDate() {
        Timestamp time = new Timestamp(ThreadLocalRandom.current().nextLong(942842538000L) + 631152000000L);
        return (new SimpleDateFormat("yyyy-MM-dd").format(new Date(time.getTime())));
    }

    public boolean getBool() {
        return ThreadLocalRandom.current().nextBoolean();
    }

}
