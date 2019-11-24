WITH possible_charge AS (
    SELECT *
    FROM (
             SELECT 200
             UNION ALL
             SELECT 250
             UNION ALL
             SELECT 400
             UNION ALL
             SELECT 500
         ) as possible_charge(charge)
)
SELECT SUM(charge * amount_of_appointments) as income_in_rubles
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
                           WHERE date >= ('2017-04-07'::date - interval '1 month')
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
