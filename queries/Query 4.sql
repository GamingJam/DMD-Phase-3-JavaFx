create table possible_charge(
	charge int
);
INSERT INTO possible_charge VALUES (200);
INSERT INTO possible_charge VALUES (250);
INSERT INTO possible_charge VALUES (400);
INSERT INTO possible_charge VALUES (500);

SELECT SUM(charge*amount_of_appointments) as income_in_rubles
FROM (
         SELECT age, amount_of_appointments
         FROM (
                  SELECT id as patient_id, age
                  FROM patient
                           JOIN "user" on patient.user_ssn = "user".ssn
              ) as age_of_patient
                  JOIN
              (
                  SELECT for_patient_id, count(app_id) as amount_of_appointments
                  FROM (
                           SELECT id as app_id, for_patient_id
                           FROM appointment
                           WHERE date >= (current_date - interval '1 month')
                       ) as appoinments_in_previous_month
                  GROUP BY for_patient_id
              ) as amount_of_appoinments_in_previous_month
              on patient_id = for_patient_id
     ) as age_and_amount_of_appointments_in_previous_month_of_patient
         CROSS JOIN possible_charge
WHERE (age < 50 AND amount_of_appointments < 3 AND charge = 200)
   OR (age < 50 AND amount_of_appointments >= 3 AND charge = 250)
   OR (age >= 50 AND amount_of_appointments < 3 AND charge = 400)
   OR (age >= 50 AND amount_of_appointments >= 3 AND charge = 500);

DROP table possible_charge;
