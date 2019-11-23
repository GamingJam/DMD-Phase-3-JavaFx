package test.Generation;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class PostresGen {
    private DataGenerator gen;
    private ArrayList<Integer> departmentIDs = new ArrayList<>(30);
    private ArrayList<Integer> ambulanceIDs = new ArrayList<>(30);
    private ArrayList<Integer> accountIDs = new ArrayList<>(30);
    private ArrayList<Integer> userIDs = new ArrayList<>(30);
    private ArrayList<Integer> notificationIDs = new ArrayList<>(30);
    private ArrayList<Integer> patientIDs = new ArrayList<>(30);
    private ArrayList<Integer> requestIDs = new ArrayList<>(30);
    private ArrayList<Integer> chatIDs = new ArrayList<>(30);
    private ArrayList<Integer> web_pageIDs = new ArrayList<>(30);

    public PostresGen() throws FileNotFoundException {
        this.gen = new DataGenerator();
    }

    public Integer getRandomID(ArrayList<Integer> list){
        return list.get(this.gen.nextInt(0, list.size()));
    }

    /**
     * Generate sublist of IDs
     * @param list of IDs of corresponding table
     * @param n is amount of elements in sublist
     */
    public ArrayList<Integer> getSubsetIDs(ArrayList<Integer> list, int n){
        ArrayList<Integer> local = new ArrayList<>(list);

        Collections.shuffle(local);
        return new ArrayList<>(local.subList(0, n));
    }

    public int accountsAmount(){
        return accountIDs.size();
    }

    public String generate() {
        StringBuilder result = new StringBuilder("");

        // Tables
        result.append(this.genDepartments()).append("\n\n");
        result.append(this.genAmbulance()).append("\n\n");
        result.append(this.genAccount()).append("\n\n");
        result.append(this.genUser()).append("\n\n");
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
        result.append(this.genSalary()).append("\n\n");
        result.append(this.genMedicament()).append("\n\n");
        result.append(this.genSupply()).append("\n\n");

        // Relations
        result.append(this.genIs_for()).append("\n\n");
        result.append(this.genRequired_request()).append("\n\n");
        result.append(this.genApproved_request()).append("\n\n");
        result.append(this.genMember_of_chat()).append("\n\n");
        result.append(this.genWeb_page()).append("\n\n");
        result.append(this.genContent()).append("\n\n");

        return result.toString();
    }

    public void generateToFile(String filename){
        try {
            FileWriter write = new FileWriter(filename);
            write.write(this.generate());
            write.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String genContent() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);
        ArrayList<Integer> webpages = getSubsetIDs(web_pageIDs, web_pageIDs.size());

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO content VALUES (%d, %d, 'This first draft of hospital`s website');\n"
                            , i, webpages.get(i%webpages.size()))
            );
        }

        return result.toString();
    }

    public String genWeb_page() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(20, 31);
        ArrayList<Integer> users = getSubsetIDs(userIDs, userIDs.size());

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO web_page VALUES (%d, %d);\n"
                            , i, users.get(i%users.size()))
            );
            this.web_pageIDs.add(i);
        }

        return result.toString();
    }

    public String genMember_of_chat() {
        StringBuilder result = new StringBuilder();

        int finish = chatIDs.size();
        ArrayList<Integer> users = getSubsetIDs(userIDs, userIDs.size());
        ArrayList<Integer> chats = getSubsetIDs(chatIDs, chatIDs.size());

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO member_of_chat VALUES (%d, %d);\n"
                            , users.get(i%users.size()), chats.get(i%chats.size()))
            );
        }

        return result.toString();
    }

    public String genApproved_request() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);
        ArrayList<Integer> requests = getSubsetIDs(requestIDs, requestIDs.size());
        ArrayList<Integer> users = getSubsetIDs(userIDs, userIDs.size());

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO approved_request VALUES (%d, %d);\n"
                            , requests.get(i%requests.size()), users.get(i%users.size()))
            );
        }

        return result.toString();
    }

    public String genRequired_request() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);
        ArrayList<Integer> requests = getSubsetIDs(requestIDs, requestIDs.size());
        ArrayList<Integer> users = getSubsetIDs(userIDs, userIDs.size());

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO required_request VALUES (%d, %d);\n"
                            , requests.get(i%requests.size()), users.get(i%users.size()))
            );
        }

        return result.toString();
    }

    public String genIs_for() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);
        ArrayList<Integer> users = getSubsetIDs(userIDs, userIDs.size());

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO is_for VALUES (%d, %d);\n"
                            , users.get(i%users.size()), notificationIDs.get(i % notificationIDs.size()))
            );
        }

        return result.toString();
    }

    public String genSupply() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 800);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO supply VALUES  (%d, 'table', %d);\n"
                            , i, departmentIDs.get(i%departmentIDs.size()))
            );
        }

        return result.toString();
    }

    public String genMedicament() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 256);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO medicament VALUES (%d, '%s', '%s', %d);\n"
                            , i, gen.getPassword(), gen.getMedicament(), departmentIDs.get(i%departmentIDs.size()))
            );
        }

        return result.toString();
    }

    public String genMessage() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, accountsAmount());
        ArrayList<Integer> users = getSubsetIDs(userIDs, finish);
        ArrayList<Integer> chats = getSubsetIDs(chatIDs, chatIDs.size());

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO message VALUES (%d, %d, %d, '%s');\n"
                            , i, chats.get(i % chats.size()), users.get(i%users.size()), gen.getMedicalFact())
            );
        }

        return result.toString();
    }

    public String genChat() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO chat VALUES (%d, 'Chat name');\n"
                            , i)
            );
            this.chatIDs.add(i);
        }

        return result.toString();
    }

    public String genRequest() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 310);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO request VALUES (%d, 'I need %s', %b);\n"
                            , i, gen.getMedicament(), gen.getBool())
            );
            this.requestIDs.add(i);
        }

        return result.toString();
    }

    public String genInvoice() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(2, Math.min(accountsAmount(), 15));
        ArrayList<Integer> users = getSubsetIDs(userIDs, userIDs.size());
        ArrayList<Integer> patients = getSubsetIDs(patientIDs, patientIDs.size());

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO invoice VALUES (%d, %d, %d, '%d', 'visit to %s + %s');\n"
                            , i, users.get(i%users.size()), patients.get(i%patients.size()), gen.nextInt(1000, 50000)
                            , gen.getRole(), gen.getMedicament())
            );
        }

        return result.toString();
    }

    public String genMedical_certificate() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(3, 6);
        ArrayList<Integer> users = getSubsetIDs(userIDs, finish);
        ArrayList<Integer> patients = getSubsetIDs(patientIDs, finish);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO medical_certificate VALUES (%d, %d, %d, '%d days free from job');\n"
                    , i, users.get(i-1), patients.get(i-1), gen.nextInt(1, 10000))
            );
        }

        return result.toString();
    }

    public String genAppointment() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(3, 10);
        ArrayList<Integer> users = getSubsetIDs(userIDs, finish);
        ArrayList<Integer> patients = getSubsetIDs(patientIDs, finish);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO appointment VALUES (%d, %d, %d, DATE '%s');\n"
                    , i, users.get(i-1), patients.get(i-1),gen.getDateTime())
            );
        }

        return result.toString();
    }

    public String genMedical_report() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(2, 10);
        ArrayList<Integer> users = getSubsetIDs(userIDs, finish);
        ArrayList<Integer> patients = getSubsetIDs(patientIDs, finish);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO medical_report VALUES (%d, %d, %d, '%s');\n"
                            , i, users.get(i-1), patients.get(i-1), gen.getMedicalFact())
            );
        }

        return result.toString();
    }

    public String genPatient() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, accountsAmount());
        ArrayList<Integer> users = getSubsetIDs(userIDs, finish);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO patient VALUES (%d, %d, '%s', DATE '%s');\n"
                            , i, users.get(i-1), gen.getMedicalFact(), gen.getDate())
            );
            this.patientIDs.add(i);
        }

        return result.toString();
    }

    public String genReport() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, accountsAmount());
        ArrayList<Integer> users = getSubsetIDs(userIDs, finish);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO report VALUES (%d, %d, '5000 for advertising to find new doctors');\n"
                            , i, users.get(i-1))
            );
        }

        return result.toString();
    }

    public String genNotification() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, accountsAmount());
        ArrayList<Integer> users = getSubsetIDs(userIDs, finish);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO notification VALUES (%d, %d, 'I need more collegues');\n"
                            , i, users.get(i-1))
            );
            this.notificationIDs.add(i);
        }

        return result.toString();
    }

    public String genSalary() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, accountsAmount());
        ArrayList<Integer> users = getSubsetIDs(userIDs, finish);

        for(int i = 1; i <= finish; ++i){
            result.append(
                    String.format("INSERT INTO salary VALUES (%d, %d, %d);\n"
                            , i, gen.nextInt(20000, 9999999), users.get(i-1))
            );
        }

        return result.toString();
    }

    public String genUser() {
        StringBuilder result = new StringBuilder();

        int finish = accountsAmount();
        ArrayList<Integer> accounts = getSubsetIDs(accountIDs, finish);

        for(int i = 1; i <= finish; ++i){
            boolean isMale = this.gen.getBool();

            result.append(
                    String.format("INSERT INTO \"user\" VALUES (%d, '%s', '%s', '%s', '%s', %s, %s, '%s', '%s', %d, %d);\n"
                            , i, isMale ? gen.getMaleName() : gen.getFemaleName(), gen.getSurname()
                            , gen.getGender(isMale), gen.getRole(), "null", "null", gen.getAddress(), gen.getPhone()
                            , gen.nextInt(18, 101), accounts.get(i-1))
            );
            this.userIDs.add(i);
        }

        return result.toString();
    }

    public String genAccount() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for (int i = 1; i <= finish; ++i) {
            result.append(
                    String.format("INSERT INTO account VALUES (%d, '%s', '%s');\n"
                            , i, gen.getNickname(), gen.getPassword())
            );
            this.accountIDs.add(i);
        }

        return result.toString();
    }

    public String genAmbulance() {
        StringBuilder result = new StringBuilder();

        int finish = this.gen.nextInt(10, 31);

        for (int i = 1; i <= finish; ++i) {
            result.append(
                    String.format("INSERT INTO ambulance VALUES (%d, %b);\n"
                            , i, gen.getBool())
            );
            this.ambulanceIDs.add(i);
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
            this.departmentIDs.add(i);
        }

        return result.toString();
    }
}
