package test;

import test.Generation.DataGenerator;
import test.Generation.PostgresGen;

import java.io.FileNotFoundException;

public class SampleGen {
    public static void main(String[] args) throws FileNotFoundException {
        PostgresGen gen = new PostgresGen();

        System.out.print("Generation: ");
        gen.generateToFile("sql/postgres_insert.sql");
        System.out.println("done!");

    }
}
