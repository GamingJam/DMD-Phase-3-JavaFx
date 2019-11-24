package test;

import test.Generation.DataGenerator;
import test.Generation.PostgresGen;

import java.io.FileNotFoundException;

public class SampleGen {
    public static void main(String[] args) throws FileNotFoundException {
        DataGenerator g = new DataGenerator();
        System.out.println(g.getDate());
        System.out.println(g.getFemaleName() + " " + g.getSurname() + " " + g.getNickname());
        System.out.println(g.getMaleName() + " " + g.getSurname() + " " + g.getNickname());
        System.out.println(g.getBool());
        System.out.println(g.getMedicalFact());
        System.out.println(g.getMedicalFact());
        System.out.println(g.getMedicalFact());
        System.out.println(g.getMedicament());
        System.out.println(g.getMedicament());
        System.out.println(g.getMedicament());

        PostgresGen gen = new PostgresGen();
        System.out.print("Generation: ");
//        for(int i = 0; i < 100; ++i) gen.generate();
//        gen.generateToFile("sql/postgres_insert.sql");
        System.out.println("done!");

        System.out.println(g.getDate());
        System.out.println(g.getDate());
        System.out.println(g.getDateTime());
        System.out.println(g.getDateTime());
//        System.out.println(gen.genAccount());
//        System.out.println(gen.genUser());
//        System.out.println(gen.genPatient());
    }
}
