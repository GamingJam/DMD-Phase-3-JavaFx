SELECT ssn,
       first_name,
       last_name,
       day_of_week,
       total,
       statistic_about_appointments.total / count_of_workweeks.amount_of_work_weeks as average
FROM (
         SELECT appointed_by_user_ssn,
                day_of_week,
                count(date) as total
         FROM (
                  SELECT appointed_by_user_ssn, EXTRACT(dow FROM date) as day_of_week, date
                  FROM APPOINTMENT
                  WHERE date >= (current_date - INTERVAL '1 year')
              ) as doctor_ssn_dow_and_date
         GROUP BY appointed_by_user_ssn, day_of_week
     ) as statistic_about_appointments
         JOIN
     (
         SELECT distinct appointed_by_user_ssn, count(week) as amount_of_work_weeks
         FROM (
                  SELECT appointed_by_user_ssn, EXTRACT(week FROM date) as week
                  FROM appointment
                  WHERE date >= (current_date - INTERVAL '1 year')
              ) as doctor_works_in_week
         GROUP BY appointed_by_user_ssn
     ) as count_of_workweeks
     on count_of_workweeks.appointed_by_user_ssn = statistic_about_appointments.appointed_by_user_ssn
         JOIN "user" on count_of_workweeks.appointed_by_user_ssn = ssn
WHERE role = 'doctor';
