-- Aquestas dades son de prova per veure el funcionament del frontend Vue 3

INSERT INTO countries (id, name, code) VALUES (1, 'Ukraine', 'UA');
INSERT INTO countries (id, name, code) VALUES (2, 'Russia', 'RU');
INSERT INTO countries (id, name, code) VALUES (3, 'United States Of America', 'US');
INSERT INTO countries (id, name, code) VALUES (4, 'Germany', 'DE');
INSERT INTO countries (id, name, code) VALUES (5, 'United Kingdom', 'GB');
INSERT INTO countries (id, name, code) VALUES (6, 'France', 'FR');
INSERT INTO countries (id, name, code) VALUES (7, 'Italy', 'IT');
INSERT INTO countries (id, name, code) VALUES (8, 'Poland', 'PL');
INSERT INTO countries (id, name, code) VALUES (9, 'Japan', 'JP');
INSERT INTO countries (id, name, code) VALUES (10, 'China', 'CN');


INSERT INTO conflicts (id, name, start_date, status, description)
VALUES (1, 'Ukraine Conflict', '2022-02-24', 'ACTIVE', 'Ongoing war in Ukraine.');
INSERT INTO conflicts (id, name, start_date, status, description)
VALUES (2, 'Test Conflict 1', '2023-03-25', 'FROZEN', 'Temporary ceasefire.');
INSERT INTO conflicts (id, name, start_date, status, description)
VALUES (3, 'Test Conflict 2', '2024-04-26', 'ENDED', 'The war has ended.');


INSERT INTO conflict_countries (conflict_id, country_id) VALUES (1, 1);
INSERT INTO conflict_countries (conflict_id, country_id) VALUES (1, 2);

INSERT INTO conflict_countries (conflict_id, country_id) VALUES (2, 3);
INSERT INTO conflict_countries (conflict_id, country_id) VALUES (2, 4);

INSERT INTO conflict_countries (conflict_id, country_id) VALUES (3, 5);
INSERT INTO conflict_countries (conflict_id, country_id) VALUES (3, 6);


INSERT INTO factions (id, name, conflict_id) VALUES (1, 'NATO Support Coalition', 1);
INSERT INTO factions (id, name, conflict_id) VALUES (2, 'Eastern Alliance', 1);

INSERT INTO factions (id, name, conflict_id) VALUES (3, 'Peacekeeping Forces', 2);

INSERT INTO factions (id, name, conflict_id) VALUES (4, 'Reconstruction Alliance', 3);


INSERT INTO faction_country (faction_id, country_id) VALUES (1, 3);
INSERT INTO faction_country (faction_id, country_id) VALUES (1, 4);
INSERT INTO faction_country (faction_id, country_id) VALUES (1, 5);

INSERT INTO faction_country (faction_id, country_id) VALUES (2, 2);
INSERT INTO faction_country (faction_id, country_id) VALUES (2, 1);

INSERT INTO faction_country (faction_id, country_id) VALUES (3, 6);
INSERT INTO faction_country (faction_id, country_id) VALUES (3, 8);

INSERT INTO faction_country (faction_id, country_id) VALUES (4, 7);
INSERT INTO faction_country (faction_id, country_id) VALUES (4, 9);


INSERT INTO events (id, event_date, location, description, conflict_id)
VALUES (1, '2022-02-24', 'Kyiv', 'Initial escalation of conflict.', 1);

INSERT INTO events (id, event_date, location, description, conflict_id)
VALUES (2, '2022-03-10', 'Kharkiv', 'Major military operation reported.', 1);

INSERT INTO events (id, event_date, location, description, conflict_id)
VALUES (3, '2023-04-01', 'Border Zone', 'Ceasefire negotiations started.', 2);

INSERT INTO events (id, event_date, location, description, conflict_id)
VALUES (4, '2024-05-15', 'Geneva', 'Peace agreement signed.', 3);
