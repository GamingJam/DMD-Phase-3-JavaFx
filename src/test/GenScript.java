package test;

import test.Generation.DataGenerator;
import test.Generation.PostgresGen;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenScript {
    public static void main(String[] args) throws FileNotFoundException {
        PostgresGen gen = new PostgresGen();

        Scanner scan = new Scanner(System.in);
        System.out.print("Please, prompt sql file name to generate: ");
        String filename = scan.nextLine();
        if(filename.equals("")){
            filename = "sql/postgres_insert.sql";
        }

        System.out.print("Generation: ");
        gen.generateToFile(filename);
        System.out.println("done!");

    }
}
