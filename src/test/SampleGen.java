package test;
import java.io.FileNotFoundException;

public class SampleGen {
    public static void main(String[] args) throws FileNotFoundException {
        Generator g = new Generator();
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


    }
}
