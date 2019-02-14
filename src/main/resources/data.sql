-- Companies
insert into company values(98,'23639953000108', '(85) 97772277', 'IDEALIT');
insert into company values(99,'57342905734928', '(11) 74981027', 'ORACLE');
-- Incidents
insert into incident (id, comment, danger_level, date, status, date_resolution, company_id) values (98, 'Risk of explosion.', 'WARNING', '2019-02-13 17:55:57.646', 'OPEN', null, 98);
insert into incident (id, comment, danger_level, date, status, date_resolution, company_id) values (99, 'Risk of explosion controlled.', 'OK', '2019-02-13 17:55:57.646', 'RESOLVED', null, 99);
