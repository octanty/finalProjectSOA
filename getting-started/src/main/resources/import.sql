INSERT INTO users(id, username, password) VALUES (nextval('hibernate_sequence'),'Paul', 'pas1456#');

/*INSERT INTO saving(id, amount, capitalization, currency, deposit, interest) VALUES (20, 0, true, 1, true, 0); */

INSERT INTO saving(id, amount, capitalization, deposit, interest) VALUES (20, 0, true, true, 0);
INSERT INTO account(id, lastseen, name, note, saving_id) VALUES (nextval('hibernate_sequence'),'2020-01-01','Paul', 'Example Note', 20);
