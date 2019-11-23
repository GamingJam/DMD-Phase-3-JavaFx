package test;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;


public class MainFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor1;

    @FXML
    public TextArea resultField;

    @FXML
    public TextArea queryField;

    @FXML
    private Button btn1;

    @FXML
    void btn1Action(ActionEvent event) {
        String sql = "SELECT * FROM \"user\"";

        DBConnector connector = new DBConnector("jdbc:sqlite:identifier.sqlite");

        resultField.appendText(connector.query(queryField.getText()));
        resultField.appendText("----End Of Query----\n");
    }

    @FXML
    void queryBtn1(){
        String sql = "SELECT * INTO appointment_for_current\n" +
                "FROM (  SELECT id as pid\n" +
                "        FROM patient\n" +
                "        WHERE user_ssn in (\n" +
                "            SELECT ssn\n" +
                "            FROM \"user\" JOIN account on account_id = account.id\n" +
                "            WHERE login = 'given_login')\n" +
                "        ) as current_patient\n" +
                "        JOIN appointment on for_patient_id = pid;\n" +
                "\n" +
                "\n" +
                "SELECT first_name, last_name\n" +
                "FROM \"user\"\n" +
                "WHERE ssn in ( SELECT appointed_by_user_ssn\n" +
                "               FROM appointment_for_current\n" +
                "               WHERE date in (SELECT max(date) FROM appointment_for_current)\n" +
                "    ) AND\n" +
                "      ((substr(first_name, 1) = 'M' AND substr(last_name, 1) <> 'M') OR\n" +
                "       (substr(first_name, 1) = 'L' AND substr(last_name, 1) <> 'L') OR\n" +
                "       (substr(first_name, 1) <> 'M' AND substr(last_name, 1) = 'M') OR\n" +
                "       (substr(first_name, 1) <> 'L' AND substr(last_name, 1) = 'L'));\n" +
                "\n" +
                "DROP table appointment_for_current;";

        DBConnector connector = new DBConnector("jdbc:sqlite:identifier.sqlite");

        resultField.appendText(connector.query(sql));
        resultField.appendText("----End Of Query----\n");
    }

    @FXML
    void queryBtn2(){
        String sql = "SELECT ssn, first_name, last_name, day_of_week, total, average\n" +
                "FROM (\n" +
                "         SELECT appointed_by_user_ssn,\n" +
                "                day_of_week,\n" +
                "                count(date)      as total,\n" +
                "                count(date) / 52 as average\n" +
                "         FROM (\n" +
                "                  SELECT appointed_by_user_ssn, EXTRACT(dow FROM date) as day_of_week, date\n" +
                "                  FROM APPOINTMENT\n" +
                "                  WHERE date >= (current_date - INTERVAL '1 year')\n" +
                "              ) as doctor_ssn_dow_and_date\n" +
                "         GROUP BY appointed_by_user_ssn, day_of_week\n" +
                "     ) as statistic_about_appointments\n" +
                "JOIN \"user\" on appointed_by_user_ssn=ssn;";

        DBConnector connector = new DBConnector("jdbc:sqlite:identifier.sqlite");

        resultField.appendText(connector.query(sql));
        resultField.appendText("----End Of Query----\n");
    }

    @FXML
    void queryBtn3(){
        String sql = "DROP table query3;\n" +
                "\n" +
                "SELECT first_name || last_name as name,\n" +
                "       week1.num as week1,\n" +
                "       week2.num as week2,\n" +
                "       week3.num as week3,\n" +
                "       week4.num as week4 into last_month_appointment\n" +
                "FROM \"user\" JOIN\n" +
                "     (SELECT count(date) as num, user_ssn\n" +
                "      FROM patient JOIN appointment a on patient.id = a.for_patient_id\n" +
                "      WHERE EXTRACT('week' from date) = EXTRACT('week' from '20.03.2015 00:00:01'::timestamp)\n" +
                "      GROUP BY patient.id) as week1 on ssn = week1.user_ssn\n" +
                "      JOIN\n" +
                "     (SELECT count(date) as num, user_ssn\n" +
                "      FROM patient JOIN appointment a on patient.id = a.for_patient_id\n" +
                "      WHERE EXTRACT('week' from date) = EXTRACT('week' from '20.03.2015 00:00:01'::timestamp) - 1\n" +
                "      GROUP BY patient.id) as week2 on ssn = week2.user_ssn\n" +
                "      JOIN\n" +
                "     (SELECT count(date) as num, user_ssn\n" +
                "      FROM patient JOIN appointment a on patient.id = a.for_patient_id\n" +
                "      WHERE EXTRACT('week' from date) = EXTRACT('week' from '20.03.2015 00:00:01'::timestamp) - 2\n" +
                "      GROUP BY patient.id) as week3 on ssn = week3.user_ssn\n" +
                "      JOIN\n" +
                "     (SELECT count(date) as num, user_ssn\n" +
                "      FROM patient JOIN appointment a on patient.id = a.for_patient_id\n" +
                "      WHERE EXTRACT('week' from date) = EXTRACT('week' from '20.03.2015 00:00:01'::timestamp) - 3\n" +
                "      GROUP BY patient.id) as week4 on ssn = week4.user_ssn;\n" +
                "\n" +
                "create table query3 as SELECT\n" +
                "    (SELECT name as each_week\n" +
                "     FROM last_month_appointment\n" +
                "     WHERE week1 = 1 AND week1 = 1 AND week3 = 1 AND week4 = 1),\n" +
                "\n" +
                "    (SELECT name as twice_a_week\n" +
                "     FROM last_month_appointment\n" +
                "     WHERE week1 >= 2 AND week1 >= 2 AND week3 >= 2 AND week4 >= 2);\n" +
                "\n" +
                "drop table last_month_appointment;";

        DBConnector connector = new DBConnector("jdbc:sqlite:identifier.sqlite");

        resultField.appendText(connector.query(sql));
        resultField.appendText("----End Of Query----\n");
    }

    @FXML
    void queryBtn4(){
        String sql = "create table possible_charge(\n" +
                "\tcharge int\n" +
                ");\n" +
                "INSERT INTO possible_charge VALUES (200);\n" +
                "INSERT INTO possible_charge VALUES (250);\n" +
                "INSERT INTO possible_charge VALUES (400);\n" +
                "INSERT INTO possible_charge VALUES (500);\n" +
                "\n" +
                "SELECT SUM(charge*amount_of_appointments) as income_in_rubles\n" +
                "FROM (\n" +
                "         SELECT age, amount_of_appointments\n" +
                "         FROM (\n" +
                "                  SELECT id as patient_id, age\n" +
                "                  FROM patient\n" +
                "                           JOIN \"user\" on patient.user_ssn = \"user\".ssn\n" +
                "              ) as age_of_patient\n" +
                "                  JOIN\n" +
                "              (\n" +
                "                  SELECT for_patient_id, count(app_id) as amount_of_appointments\n" +
                "                  FROM (\n" +
                "                           SELECT id as app_id, for_patient_id\n" +
                "                           FROM appointment\n" +
                "                           WHERE date >= (current_date - interval '1 month')\n" +
                "                       ) as appoinments_in_previous_month\n" +
                "                  GROUP BY for_patient_id\n" +
                "              ) as amount_of_appoinments_in_previous_month\n" +
                "              on patient_id = for_patient_id\n" +
                "     ) as age_and_amount_of_appointments_in_previous_month_of_patient\n" +
                "         CROSS JOIN possible_charge\n" +
                "WHERE (age < 50 AND amount_of_appointments < 3 AND charge = 200)\n" +
                "   OR (age < 50 AND amount_of_appointments >= 3 AND charge = 250)\n" +
                "   OR (age >= 50 AND amount_of_appointments < 3 AND charge = 400)\n" +
                "   OR (age >= 50 AND amount_of_appointments >= 3 AND charge = 500);\n" +
                "\n" +
                "DROP table possible_charge;";

        DBConnector connector = new DBConnector("jdbc:sqlite:identifier.sqlite");

        resultField.appendText(connector.query(sql));
        resultField.appendText("----End Of Query----\n");
    }

    @FXML
    void queryBtn5(){
        String sql = "SELECT ssn, first_name, last_name\n" +
                "FROM(\n" +
                "    SELECT ssn, first_name, last_name\n" +
                "    FROM(\n" +
                "        SELECT ssn, first_name, last_name, count(year) as amount_of_hard_working_years\n" +
                "        FROM(\n" +
                "            SELECT ssn, first_name, last_name, year\n" +
                "            FROM(\n" +
                "                SELECT ssn, first_name, last_name, year, count(for_patient_id) as patients_in_year\n" +
                "                FROM(\n" +
                "                    SELECT distinct ssn, first_name, last_name, EXTRACT(year from date) as year, for_patient_id\n" +
                "                    FROM \"user\" join appointment on appointed_by_user_ssn = ssn\n" +
                "                    WHERE role = 'doctor' AND EXTRACT(year from date) > EXTRACT(year from CURRENT_DATE) - 10\n" +
                "                ) as patients_visited_by_doctor_in_year\n" +
                "                GROUP BY ssn, first_name, last_name, year\n" +
                "            ) as amount_of_patient_visited_by_doctor_in_year\n" +
                "            WHERE patients_in_year >= 5\n" +
                "        ) as hard_working_years_of_doctor\n" +
                "        GROUP BY ssn, last_name, first_name\n" +
                "    ) as amount_of_ard_working_years_of_doctor\n" +
                "    WHERE amount_of_hard_working_years = 10\n" +
                ") as doctors_with_not_less_than_5_patients_per_year \n" +
                "JOIN \n" +
                "(\n" +
                "    SELECT ssn as ssn_of_doctor_with_100_patients\n" +
                "    FROM(\n" +
                "        SELECT ssn, count(for_patient_id) as total_patients\n" +
                "        FROM(\n" +
                "            SELECT distinct ssn, for_patient_id\n" +
                "            FROM \"user\" join appointment on appointed_by_user_ssn = ssn\n" +
                "            WHERE role = 'doctor' AND EXTRACT(year from date) > EXTRACT(year from CURRENT_DATE) - 10\n" +
                "        ) as patients_visited_by_doctor_ssn\n" +
                "        GROUP BY ssn\n" +
                "    ) as amount_of_patients_visited_by_doctor_ssn\n" +
                "    WHERE total_patients >= 100\n" +
                ") as doctors_with_not_less_than_100_patients \n" +
                "on ssn = ssn_of_doctor_with_100_patients";

        DBConnector connector = new DBConnector("jdbc:sqlite:identifier.sqlite");

        resultField.appendText(connector.query(sql));
        resultField.appendText("----End Of Query----\n");
    }

    @FXML
    void initialize() {
        assert anchor1 != null : "fx:id=\"anchor1\" was not injected: check your FXML file 'mainForm.fxml'.";
        assert btn1 != null : "fx:id=\"btn1\" was not injected: check your FXML file 'mainForm.fxml'.";
        assert queryField != null : "fx:id=\"txtArea1\" was not injected: check your FXML file 'mainForm.fxml'.";
        assert resultField != null : "fx:id=\"txtArea1\" was not injected: check your FXML file 'mainForm.fxml'.";
    }
}
