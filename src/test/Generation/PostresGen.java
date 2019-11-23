package test.Generation;

import java.io.FileNotFoundException;
import java.util.concurrent.ThreadLocalRandom;

public class PostresGen {
    private DataGenerator gen;

    public PostresGen() throws FileNotFoundException {
        this.gen = new DataGenerator();
    }

    public String generate() {
        StringBuilder result = new StringBuilder("");

        // Tables
        result.append(this.genDepartments()).append("\n\n");
        result.append(this.genAmbulance()).append("\n\n");
        result.append(this.genAccount()).append("\n\n");
        /*
        result.append(this.genUser()).append("\n\n");
        result.append(this.genSalary()).append("\n\n");
        result.append(this.genNotification()).append("\n\n");
        result.append(this.genReport()).append("\n\n");
        result.append(this.genPatient()).append("\n\n");
        result.append(this.genMedical_report()).append("\n\n");
        result.append(this.genAppointment()).append("\n\n");
        result.append(this.genMedical_certificate()).append("\n\n");
        result.append(this.genInvoice()).append("\n\n");
        result.append(this.genRequest()).append("\n\n");
        result.append(this.genChat()).append("\n\n");
        result.append(this.genMessage()).append("\n\n");
        result.append(this.genMedicament()).append("\n\n");
        result.append(this.genSupply()).append("\n\n");

        // Relations
        result.append(this.genIs_for()).append("\n\n");
        result.append(this.genRequired_request()).append("\n\n");
        result.append(this.genApproved_request()).append("\n\n");
        result.append(this.genMember_of_chat()).append("\n\n");
        result.append(this.genWeb_page()).append("\n\n");
        result.append(this.genContent()).append("\n\n");
        */

        return result.toString();
    }

    /*
    private String genContent() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genWeb_page() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genMember_of_chat() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genApproved_request() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genRequired_request() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genIs_for() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genSupply() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genMedicament() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genMessage() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genChat() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genRequest() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genInvoice() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genMedical_certificate() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genAppointment() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genMedical_report() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genPatient() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genReport() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genNotification() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genSalary() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }

    private String genUser() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i-start+1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }
    */

    private String genAccount() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for (int i = 1; i <= finish; ++i) {
            result.append(
                    String.format("INSERT INTO account VALUES (%d, '%s', '%s');\n"
                            , i, gen.getMaleName(), "qwerty")
            );
        }

        return result.toString();
    }

    private String genAmbulance() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for (int i = 1; i <= finish; ++i) {
            result.append(
                    String.format("INSERT INTO ambulance VALUES (%d, %b);\n"
                            , i, gen.getBool())
            );
        }

        return result.toString();
    }

    public String genDepartments() {
        StringBuilder result = new StringBuilder();

        int start = this.gen.nextInt(0, 31);
        int len = this.gen.nextInt(10, 21);

        for (int i = start; i < start + len; ++i) {
            result.append(
                    String.format("INSERT INTO department VALUES (%d, '%s', %d);\n"
                            , i - start + 1, DataGenerator.departmentNames.get(i)
                            , gen.nextInt(1000000, 99999999))
            );
        }

        return result.toString();
    }
}
