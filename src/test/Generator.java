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
    private Scanner scnn;

    Generator() throws FileNotFoundException {
        scf = new Scanner(new File("./src/test/txts/NamesF.txt"));
        scm = new Scanner(new File("./src/test/txts/NamesM.txt"));
        scs = new Scanner(new File("./src/test/txts/Surnames.txt"));
        scmf = new Scanner(new File("./src/test/txts/MedicalFacts.txt"));
        scmm = new Scanner(new File("./src/test/txts/Medicament.txt"));
        scnn = new Scanner(new File("./src/test/txts/Nicknames.txt"));
    }

    String getFemaleName() {
        return scf.nextLine();
    }

    String getNickname() {
        return scnn.nextLine();
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

    boolean getBool() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    


}
