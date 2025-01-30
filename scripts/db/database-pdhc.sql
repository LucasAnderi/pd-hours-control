begin;


create table squad (
    id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(100) NOT NULL
 );

create table employee (
    id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(100) NOT NULL,
    estimatedhours INTEGER NOT NULL,
    squad_id INTEGER NOT NULL REFERENCES squad(id) ON UPDATE CASCADE


);

create table report(
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    spenthours INTEGER NOT NULL,
    employee_id INTEGER NOT NULL REFERENCES employee(id) ON UPDATE CASCADE
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
);

COMMIT;