insert into tanszek(nev) values ('elsoTanszek');
insert into tanszek(nev) values ('masodikTanszek');
insert into tanszek(nev) values ('harmadikTanszek');

insert into tantargy(nev, kredit, indul, felev, tanszek_id) values ('AlgoritmusokTantargy', 3, true, 4, 1);
insert into tantargy(nev, kredit, indul, felev, tanszek_id) values ('ModalgTantargy', 3, false, 5, 1);
insert into tantargy(nev, kredit, indul, felev, tanszek_id) values ('NummodTantargy', 3, true, 6, 2);

insert into kurzus(kurzuskod, maxhallgato, idopont, created_at, updated_at, tantargy_id) values (4, 10, 'szerda', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);
insert into kurzus(kurzuskod, maxhallgato, idopont, created_at, updated_at, tantargy_id) values (6, 12, 'csut', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);
insert into kurzus(kurzuskod, maxhallgato, idopont, created_at, updated_at, tantargy_id) values (10, 8, 'pentek', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 2);

insert into hallgato(username, password, felev, created_at, updated_at, role) values ('hallgato1', '$2a$04$fZCuT6n74CdGaDLTPCWG/uVGMv9aKI.NOQxV8VP5oMV1owLy37.nK', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),  'ROLE_HALLGATO');
insert into hallgato(username, password, felev, created_at, updated_at, role) values ('hallgato2', '$2a$04$fZCuT6n74CdGaDLTPCWG/uVGMv9aKI.NOQxV8VP5oMV1owLy37.nK', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),  'ROLE_HALLGATO');
insert into hallgato(username, password, felev, created_at, updated_at, role) values ('hallgato3', '$2a$04$fZCuT6n74CdGaDLTPCWG/uVGMv9aKI.NOQxV8VP5oMV1owLy37.nK', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),  'ROLE_HALLGATO');
insert into hallgato(username, password, felev, created_at, updated_at, role) values ('tanar1', '$2a$04$fZCuT6n74CdGaDLTPCWG/uVGMv9aKI.NOQxV8VP5oMV1owLy37.nK', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ROLE_TANAR');

insert into kuha(hallgatok_id, kurzus_id) values (1, 1);
insert into kuha(hallgatok_id, kurzus_id) values (1, 2);
insert into kuha(hallgatok_id, kurzus_id) values (3, 1);
insert into kuha(hallgatok_id, kurzus_id) values (2, 2);