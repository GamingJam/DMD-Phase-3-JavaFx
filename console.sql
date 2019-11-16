create table department (
    id SERIAL PRIMARY KEY ,
    name varchar(256) ,
    balance money not null
);

create table ambulance (
    id SERIAL PRIMARY KEY ,
    availability bool not null
);

create table account (
    id SERIAL PRIMARY KEY ,
    login varchar(8) not null UNIQUE,
    password varchar(8) not null
);

create table "user" (
    ssn SERIAL PRIMARY KEY ,
    first_name varchar(256) not null,
    last_name varchar(256) not null,
    gender char(1) not null,
    role varchar(256) not null,
    works_for_department_id int not null REFERENCES department(id),
    works_in_ambulance_id int REFERENCES ambulance(id),
    address varchar(256) ,
    phone int ,
    account_id int not null UNIQUE REFERENCES account(id)
);

create table notification (
    id SERIAL PRIMARY KEY ,
    created_by_user_ssn int not null REFERENCES "user"(ssn),
    message varchar(500) not null
);

create table is_for (
    for_user_ssn int not null REFERENCES  "user"(ssn),
    is_notification_id int not null REFERENCES notification(id)
);

create table report (
    id SERIAL PRIMARY KEY ,
    created_by_user_ssn int not null REFERENCES "user"(ssn),
    description varchar(10000) not null
);

create table patient (
    id SERIAL PRIMARY KEY ,
    history varchar(1000000) not null ,
    registration_date date not null
);

create table is_patient_of (
    is_patient_id int not null REFERENCES patient(id),
    of_user_ssn int not null REFERENCES "user"(ssn)
);

create table medical_report (
    id SERIAL PRIMARY KEY ,
    assigned_by_user_ssn int not null REFERENCES "user" (ssn),
    linked_to_patient_id int not null REFERENCES patient (id),
    content varchar(10000) not null
);

create table appointment (
    id SERIAL PRIMARY KEY ,
    appointed_by_user_ssn int not null REFERENCES "user" (ssn),
    for_patient_id int not null REFERENCES patient (id),
    data date not null
);

create table medical_certificate (
    id SERIAL PRIMARY KEY ,
    by_user_ssn int not null REFERENCES "user" (ssn),
    for_patient_id int not null REFERENCES patient (id),
    description varchar (1000) not null
);

create table invoice (
    id SERIAL PRIMARY KEY ,
    by_user_ssn int not null REFERENCES "user" (ssn),
    to_patient_id int not null REFERENCES patient (id),
    balance_due money not null ,
    reason varchar(1000) not null
);

create table request (
    id SERIAL PRIMARY KEY ,
    description varchar(1000) not null,
    completed bool not null
);

create table approved_request (
    request_id  int not null REFERENCES request(id),
    user_ssn int not null REFERENCES "user" (ssn)
);

create table required_request (
    request_id  int not null REFERENCES request(id),
    user_ssn int not null REFERENCES "user" (ssn)
);

create table chat (
    id SERIAL PRIMARY KEY ,
    name varchar(100) not null
);

create table messages (
    id SERIAL PRIMARY KEY ,
    in_chat_id int not null REFERENCES chat (id),
    from_user_ssn int not null REFERENCES "user" (ssn),
    text varchar(5000) not null
);

create table member_of_chat (
    member_user_ssn int not null REFERENCES "user" (ssn) ,
    of_chat_id int null REFERENCES chat (id)
);

create table page_of_hospital_information_portal (
    id SERIAL PRIMARY KEY ,
    maintained_by_user_ssn int not null REFERENCES "user" (ssn)
);

create table "content" (
    id SERIAL PRIMARY KEY ,
    in_page_id int not null REFERENCES page_of_hospital_information_portal(id),
    text varchar (1000000) not null
);

create table salary (
    id SERIAL PRIMARY KEY ,
    amount int not null,
    assigned_to_user_ssn int not null UNIQUE REFERENCES "user" (ssn)
);

create table medicament (
    serial_number int PRIMARY KEY ,
    mpid varchar(32) not null,
    name varchar(256) not null,
    belongs_to_department_id int not null REFERENCES department(id)
);

create table supply (
    serial_number int PRIMARY KEY ,
    name varchar(256) not null,
    belongs_to_department_id int not null REFERENCES department(id)
)