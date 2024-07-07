INSERT INTO transporters (id, name, number)
VALUES (1, 'ООО "Авангард"', '+7-999-333-44-22');
INSERT INTO transporters (id, name, number)
VALUES (2, 'ООО "Динамо"', '+7-999-444-22-11');
INSERT INTO transporters (id, name, number)
VALUES (3, 'ООО "Торпедо"', '+7-999-111-88-00');
INSERT INTO transporters (id, name, number)
VALUES (4, 'ООО "Искра"', '+7-999-000-33-88');
INSERT INTO transporters (id, name, number)
VALUES (5, 'ООО "ЦСКА"', '+7-999-888-22-11');


INSERT INTO paths (id, start, finish, transporter_id, travel_time)
VALUES (1, 'Нижний-Новгород', 'Владимир', 1, 180);
INSERT INTO paths (id, start, finish, transporter_id, travel_time)
VALUES (2, 'Владимир', 'Юрьев-Польский', 1, 80);
INSERT INTO paths (id, start, finish, transporter_id, travel_time)
VALUES (3, 'Москва', 'Калуга', 2, 150);
INSERT INTO paths (id, start, finish, transporter_id, travel_time)
VALUES (4, 'Москва', 'Королев', 3, 70);
INSERT INTO paths (id, start, finish, transporter_id, travel_time)
VALUES (5, 'Санкт-Петербург', 'Москва', 4, 540);
INSERT INTO paths (id, start, finish, transporter_id, travel_time)
VALUES (6, 'Москва', 'Санкт-Петербург', 5, 540);



