-- Query 3
DROP table query3;

SELECT first_name || last_name as name,
       week1.num as week1,
       week2.num as week2,
       week3.num as week3,
       week4.num as week4 into last_month_appointment
FROM "user" JOIN
     (SELECT count(date) as num, user_ssn
      FROM patient JOIN appointment a on patient.id = a.for_patient_id
      WHERE EXTRACT('week' from date) = EXTRACT('week' from '20.03.2015 00:00:01'::timestamp)
      GROUP BY patient.id) as week1 on ssn = week1.user_ssn
      JOIN
     (SELECT count(date) as num, user_ssn
      FROM patient JOIN appointment a on patient.id = a.for_patient_id
      WHERE EXTRACT('week' from date) = EXTRACT('week' from '20.03.2015 00:00:01'::timestamp) - 1
      GROUP BY patient.id) as week2 on ssn = week2.user_ssn
      JOIN
     (SELECT count(date) as num, user_ssn
      FROM patient JOIN appointment a on patient.id = a.for_patient_id
      WHERE EXTRACT('week' from date) = EXTRACT('week' from '20.03.2015 00:00:01'::timestamp) - 2
      GROUP BY patient.id) as week3 on ssn = week3.user_ssn
      JOIN
     (SELECT count(date) as num, user_ssn
      FROM patient JOIN appointment a on patient.id = a.for_patient_id
      WHERE EXTRACT('week' from date) = EXTRACT('week' from '20.03.2015 00:00:01'::timestamp) - 3
      GROUP BY patient.id) as week4 on ssn = week4.user_ssn;

create table query3 as SELECT
    (SELECT name as each_week
     FROM last_month_appointment
     WHERE week1 = 1 AND week1 = 1 AND week3 = 1 AND week4 = 1),

    (SELECT name as twice_a_week
     FROM last_month_appointment
     WHERE week1 >= 2 AND week1 >= 2 AND week3 >= 2 AND week4 >= 2);

drop table last_month_appointment;