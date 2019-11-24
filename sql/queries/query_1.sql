WITH appointment_for_current AS
         (
             SELECT *
             FROM (SELECT id as pid
                   FROM patient
                   WHERE user_ssn in (
                       SELECT ssn
                       FROM "user"
                                JOIN account on account_id = account.id
                       WHERE login = 'i_am_alfiya')
                  ) as current_patient
                      JOIN appointment on for_patient_id = pid
         )
SELECT first_name, last_name
FROM "user"
WHERE ssn in (SELECT appointed_by_user_ssn
              FROM appointment_for_current
              WHERE date in (SELECT max(date) FROM appointment_for_current)
)
  AND ((left(first_name, 1) = 'M' AND left(last_name, 1) <> 'M') OR
       (left(first_name, 1) = 'L' AND left(last_name, 1) <> 'L') OR
       (left(first_name, 1) <> 'M' AND left(last_name, 1) = 'M') OR
       (left(first_name, 1) <> 'L' AND left(last_name, 1) = 'L'));
