SELECT * INTO appointment_for_current
FROM (  SELECT id as pid
        FROM patient
        WHERE user_ssn in (
            SELECT ssn
            FROM "user" JOIN account on account_id = account.id
            WHERE login = 'given_login')
        ) as current_patient
        JOIN appointment on for_patient_id = pid;


SELECT first_name, last_name
FROM "user"
WHERE ssn in ( SELECT appointed_by_user_ssn
               FROM appointment_for_current
               WHERE date in (SELECT max(date) FROM appointment_for_current)
    ) AND
      ((substr(first_name, 1) = 'M' AND substr(last_name, 1) <> 'M') OR
       (substr(first_name, 1) = 'L' AND substr(last_name, 1) <> 'L') OR
       (substr(first_name, 1) <> 'M' AND substr(last_name, 1) = 'M') OR
       (substr(first_name, 1) <> 'L' AND substr(last_name, 1) = 'L'));

DROP table appointment_for_current;
