-- Query 3

SELECT first_name || ' ' || last_name as twice_a_week
FROM "user" JOIN
     (SELECT count(date) as num, user_ssn
      FROM patient JOIN appointment a on patient.id = a.for_patient_id
      WHERE EXTRACT('week' from date) = EXTRACT('week' from '07.04.2017 00:00:01'::timestamp)
      GROUP BY patient.id) as week1 on ssn = week1.user_ssn
      JOIN
     (SELECT count(date) as num, user_ssn
      FROM patient JOIN appointment a on patient.id = a.for_patient_id
      WHERE EXTRACT('week' from date) = EXTRACT('week' from '07.04.2017 00:00:01'::timestamp) - 1
      GROUP BY patient.id) as week2 on ssn = week2.user_ssn
      JOIN
     (SELECT count(date) as num, user_ssn
      FROM patient JOIN appointment a on patient.id = a.for_patient_id
      WHERE EXTRACT('week' from date) = EXTRACT('week' from '07.04.2017 00:00:01'::timestamp) - 2
      GROUP BY patient.id) as week3 on ssn = week3.user_ssn
      JOIN
     (SELECT count(date) as num, user_ssn
      FROM patient JOIN appointment a on patient.id = a.for_patient_id
      WHERE EXTRACT('week' from date) = EXTRACT('week' from '07.04.2017 00:00:01'::timestamp) - 3
      GROUP BY patient.id) as week4 on ssn = week4.user_ssn
WHERE week1.num >= 2 AND week1.num >= 2 AND week3.num >= 2 AND week4.num >= 2;

