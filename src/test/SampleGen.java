package test;
import test.Generation.DataGenerator;
import test.Generation.PostresGen;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SampleGen {
    public static void main(String[] args) throws FileNotFoundException {
        DataGenerator g = new DataGenerator();
        System.out.println(g.getRandomDate());
        System.out.println(g.getFemaleName() + " " + g.getSurname());
        System.out.println(g.getMaleName() + " " + g.getSurname());
        System.out.println(g.getBool());
        System.out.println(g.getMedicalFact());
        System.out.println(g.getMedicalFact());
        System.out.println(g.getMedicalFact());
        System.out.println(g.getMedicament());
        System.out.println(g.getMedicament());
        System.out.println(g.getMedicament());

        PostresGen gen = new PostresGen();
        System.out.print("Deps:");
        try {
            FileWriter write = new FileWriter("insert.sql");
            write.write(gen.generate());
            write.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("done");
    }
}
