SELECT ssn, first_name, last_name, day_of_week, total, average
FROM (
         SELECT appointed_by_user_ssn,
                day_of_week,
                count(date)      as total,
                count(date) / 52.0 as average
         FROM (
                  SELECT appointed_by_user_ssn, EXTRACT(dow FROM date) as day_of_week, date
                  FROM APPOINTMENT
                  WHERE date >= ('07.03.2017'::date - INTERVAL '1 year')
              ) as doctor_ssn_dow_and_date
         GROUP BY appointed_by_user_ssn, day_of_week
     ) as statistic_about_appointments
JOIN "user" on appointed_by_user_ssn=ssn;