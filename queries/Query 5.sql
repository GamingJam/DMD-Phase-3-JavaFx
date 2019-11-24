--delete distinct from inner selects if they are not unique
SELECT ssn, first_name, last_name
FROM(
    SELECT ssn, first_name, last_name
    FROM(
        SELECT ssn, first_name, last_name, count(year) as amount_of_hard_working_years
        FROM(
            SELECT ssn, first_name, last_name, year
            FROM(
                SELECT ssn, first_name, last_name, year, count(for_patient_id) as patients_in_year
                FROM(
                    SELECT distinct ssn, first_name, last_name, EXTRACT(year from date) as year, for_patient_id
                    FROM "user" join appointment on appointed_by_user_ssn = ssn
                    WHERE role = 'doctor' AND date >= CURRENT_DATE - INTERVAL '10 year'
                ) as patients_visited_by_doctor_in_year
                GROUP BY ssn, first_name, last_name, year
            ) as amount_of_patient_visited_by_doctor_in_year
            WHERE patients_in_year >= 5
        ) as hard_working_years_of_doctor
        GROUP BY ssn, last_name, first_name
    ) as amount_of_ard_working_years_of_doctor
    WHERE amount_of_hard_working_years = 10
) as doctors_with_not_less_than_5_patients_per_year 
JOIN 
(
    SELECT ssn as ssn_of_doctor_with_100_patients
    FROM(
        SELECT ssn, count(for_patient_id) as total_patients
        FROM(
            SELECT distinct ssn, for_patient_id
            FROM "user" join appointment on appointed_by_user_ssn = ssn
            WHERE role = 'doctor' AND date >= CURRENT_DATE - INTERVAL '10 year'
        ) as patients_visited_by_doctor_ssn
        GROUP BY ssn
    ) as amount_of_patients_visited_by_doctor_ssn
    WHERE total_patients >= 100
) as doctors_with_not_less_than_100_patients 
on ssn = ssn_of_doctor_with_100_patients
