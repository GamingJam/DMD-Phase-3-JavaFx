package test;
import test.Generation.DataGenerator;
import test.Generation.PostresGen;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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

        PostresGen gen = new PostresGen();
        System.out.print("Generation: ");
//        gen.generateToFile("insert.sql");
        System.out.println("done!");

//        System.out.println(g.getDate());
//        System.out.println(gen.genAccount());
//        System.out.println(gen.genUser());
//        System.out.println(gen.genPatient());
    }
}
