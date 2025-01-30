BEGIN;


--SQUAD:

INSERT INTO squad (name)
VALUES ('PD-DevTeam');

INSERT INTO squad (name)
VALUES ('PD-MktTeam');

--EMPLOYEE:

INSERT INTO employee (name,estimatedhours,squad_id)
VALUES ('Joao',8,1);

INSERT INTO employee (name,estimatedhours,squad_id)
VALUES ('Gabriel',8,2);

--REPORT:
--report de joao
INSERT INTO report (description,spenthours,employee_id)
VALUES ('Levantamento das user story do projeto',3,1);
--report de joao
INSERT INTO report (description,spenthours,employee_id)
VALUES ('Modelagem do databaase',5,1);
--report do gabriel
INSERT INTO report (description,spenthours,employee_id)
VALUES ('Criar artes de divulgação do produto',6,2);

INSERT INTO report (description,spenthours,employee_id)
VALUES ('Coleta dos resultados de vendas',3,2);