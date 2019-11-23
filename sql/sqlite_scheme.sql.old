CREATE TABLE department (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(256) ,
    balance money NOT NULL
);

CREATE TABLE ambulance (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    availability bool NOT NULL
);

CREATE TABLE account (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    login VARCHAR(8) NOT NULL UNIQUE,
    password VARCHAR(8) NOT NULL
);

CREATE TABLE "user" (
    ssn INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name VARCHAR(256) NOT NULL,
    last_name VARCHAR(256) NOT NULL,
    gender char(1) NOT NULL,
    role VARCHAR(256) NOT NULL,
    works_for_department_id INTEGER NOT NULL REFERENCES department(id),
    works_in_ambulance_id INTEGER REFERENCES ambulance(id),
    address VARCHAR(256) ,
    phone INTEGER ,
    age INTEGER NOT NULL,
    account_id INTEGER NOT NULL UNIQUE REFERENCES account(id)
);

CREATE TABLE notification (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_by_user_ssn INTEGER NOT NULL REFERENCES "user"(ssn),
    message VARCHAR(500) NOT NULL
);

CREATE TABLE is_for (
    for_user_ssn INTEGER NOT NULL REFERENCES  "user"(ssn),
    is_notification_id INTEGER NOT NULL REFERENCES notification(id)
);

CREATE TABLE report (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_by_user_ssn INTEGER NOT NULL REFERENCES "user"(ssn),
    description VARCHAR(10000) NOT NULL
);

CREATE TABLE patient (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_ssn INTEGER NOT NULL UNIQUE REFERENCES "user"(ssn) ,
    history VARCHAR(1000000) NOT NULL ,
    registration_date date NOT NULL
);

CREATE TABLE medical_report (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    assigned_by_user_ssn INTEGER NOT NULL REFERENCES "user" (ssn),
    linked_to_patient_id INTEGER NOT NULL REFERENCES patient (id),
    content VARCHAR(10000) NOT NULL
);

CREATE TABLE appointment (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    appointed_by_user_ssn INTEGER NOT NULL REFERENCES "user" (ssn),
    for_patient_id INTEGER NOT NULL REFERENCES patient (id),
    data timestamp NOT NULL ,
    day VARCHAR(10) NOT NULL
);

CREATE TABLE medical_certificate (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    by_user_ssn INTEGER NOT NULL REFERENCES "user" (ssn),
    for_patient_id INTEGER NOT NULL REFERENCES patient (id),
    description VARCHAR (1000) NOT NULL
);

CREATE TABLE invoice (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    by_user_ssn INTEGER NOT NULL REFERENCES "user" (ssn),
    to_patient_id INTEGER NOT NULL REFERENCES patient (id),
    balance_due money NOT NULL ,
    reason VARCHAR(1000) NOT NULL
);

CREATE TABLE request (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    description VARCHAR(1000) NOT NULL,
    completed bool NOT NULL
);

CREATE TABLE approved_request (
    request_id  INTEGER NOT NULL REFERENCES request(id),
    user_ssn INTEGER NOT NULL REFERENCES "user" (ssn)
);

CREATE TABLE required_request (
    request_id  INTEGER NOT NULL REFERENCES request(id),
    user_ssn INTEGER NOT NULL REFERENCES "user" (ssn)
);

CREATE TABLE chat (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE message (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    in_chat_id INTEGER NOT NULL REFERENCES chat (id),
    from_user_ssn INTEGER NOT NULL REFERENCES "user" (ssn),
    text VARCHAR(5000) NOT NULL
);

CREATE TABLE member_of_chat (
    member_user_ssn INTEGER NOT NULL REFERENCES "user" (ssn) ,
    of_chat_id INTEGER null REFERENCES chat (id)
);

CREATE TABLE page_of_hospital_information_portal (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    maintained_by_user_ssn INTEGER NOT NULL REFERENCES "user" (ssn)
);

CREATE TABLE "content" (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    in_page_id INTEGER NOT NULL REFERENCES page_of_hospital_information_portal(id),
    text VARCHAR (1000000) NOT NULL
);

CREATE TABLE salary (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    amount INTEGER NOT NULL,
    assigned_to_user_ssn INTEGER NOT NULL UNIQUE REFERENCES "user" (ssn)
);

CREATE TABLE medicament (
    serial_number INTEGER PRIMARY KEY AUTOINCREMENT,
    mpid VARCHAR(32) NOT NULL,
    name VARCHAR(256) NOT NULL,
    belongs_to_department_id INTEGER NOT NULL REFERENCES department(id)
);

CREATE TABLE supply (
    serial_number INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(256) NOT NULL,
    belongs_to_department_id INTEGER NOT NULL REFERENCES department(id)
);
