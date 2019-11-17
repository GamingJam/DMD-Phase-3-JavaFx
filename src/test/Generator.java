package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class Generator {

    private Scanner scf;
    private Scanner scm;
    private Scanner scs;
    private Scanner scmf;
    private Scanner scmm;


    Generator() throws FileNotFoundException {
        scf = new Scanner(new File("./src/test/NamesF.txt"));
        scm = new Scanner(new File("./src/test/NamesM.txt"));
        scs = new Scanner(new File("./src/test/Surnames.txt"));
        scmf = new Scanner(new File("./src/test/MedicalFacts.txt"));
        scmm = new Scanner(new File("./src/test/Medicament.txt"));
    }

    String getFemaleName() {
        return scf.nextLine();
    }

    String getMedicalFact() {
        return scmf.nextLine();
    }

    String getMedicament() {
        return scmm.nextLine();
    }

    String getMaleName() {
        return scm.nextLine();
    }

    String getSurname() {
        return scs.nextLine();
    }

    Timestamp getRandomDate() {
        return new Timestamp(ThreadLocalRandom.current().nextLong(942842538000L) + 631152000000L);
    }

    boolean getBool(){
        return ThreadLocalRandom.current().nextBoolean();
    }



}
