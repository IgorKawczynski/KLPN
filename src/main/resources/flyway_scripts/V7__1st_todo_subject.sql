-- 1. (GRZES)

-- - Dodać 10/15 drużyn do bazy

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'Czerwone Smoki', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'Niebieskie Smoki', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'WMII Koksi', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'Mc Humans', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'Czerwone Smoki', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'Mucias Gracias Avizione', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'Sewy Squad', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'Credit Crew', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'KS Choroszcz', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'Olsztyn OLS', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'Inzynierzy Oprogramowania', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'Wybrzeze Klatki Schodowej', 0, 0, 0);

insert into "public".team(balance, draws, goals_against, goals_for, is_accepted, loses, name, number_of_matches, points, wins)
values (0, 0, 0, 0, true, 0, 'ORL Orlik', 0, 0, 0);

-- dodanie uzytkownikow, zeby zrobic z nich studentow i przypisac do druzyn

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Trzystek', 'Bolek', '439@gmail.com', 'pokiCoNieTajne', '613066123', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Adam', 'Drozdzowka', '440@gmail.com', 'pokiCoNieTajne', '613066364', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Piotrek', 'Kozakiewicz', '441@gmail.com', 'pokiCoNieTajne', '613066398', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Mateusz', 'Boniek', '442@gmail.com', 'pokiCoNieTajne', '613066112', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Sebek', 'Andres', '443@gmail.com', 'pokiCoNieTajne', '613066092', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Maciek', 'Sloneczny', '444@gmail.com', 'pokiCoNieTajne', '613066300', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Maciej', 'Zima', '445@gmail.com', 'pokiCoNieTajne', '613066312', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Pawel', 'Lato', '446@gmail.com', 'pokiCoNieTajne', '613066342', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Lukasz', 'Tomaszewski', '447@gmail.com', 'pokiCoNieTajne', '613266335', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Andrew', 'Brzeski', '448@gmail.com', 'pokiCoNieTajne', '613888335', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Roman', 'Kryski', '449@gmail.com', 'pokiCoNieTajne', '999066335', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Marcin', 'Kuklin', '450@gmail.com', 'pokiCoNieTajne', '909066335', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Aleksander', 'Krysiak', '451@gmail.com', 'pokiCoNieTajne', '951066335', false);

-- dodawanie studentow

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '439@gmail.com'), 162439, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '440@gmail.com'), 162440, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '441@gmail.com'), 162441, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '442@gmail.com'), 162442, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '443@gmail.com'), 162443, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '444@gmail.com'), 162444, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '445@gmail.com'), 162445, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '446@gmail.com'), 162446, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '447@gmail.com'), 162447, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '448@gmail.com'), 162448, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '449@gmail.com'), 162449, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '450@gmail.com'), 162450, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = '451@gmail.com'), 162451, false);

-- - Przypisać 2/3 drużynom zawodników

update student set team_id = (select t.id from team t where t.name = 'Inzynierzy Oprogramowania')
where index_number between 162425 and 162433;

update student set team_id = (select t.id from team t where t.name = 'Mc Humans')
where index_number between 162434 and 162442;

update student set team_id = (select t.id from team t where t.name = 'Olsztyn OLS')
where index_number between 162443 and 162451;

update student set is_accepted = true where index_number between 162423 and 162452;

-- - Dodać ileś rezerwacji dla 2/3 drużyn, żeby było do testowania i prezentacji

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (1, '2023-02-02 17:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com')); -- email admina

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (2, '2023-02-02 17:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (3, '2023-02-02 17:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (1, '2023-02-02 19:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (2, '2023-02-02 19:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (3, '2023-02-02 19:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (1, '2023-02-02 21:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (2, '2023-02-02 21:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (3, '2023-02-02 21:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (1, '2023-02-04 17:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (2, '2023-02-04 17:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (3, '2023-02-04 17:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (1, '2023-02-04 19:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (2, '2023-02-04 19:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(pitch, date, user_id)
    VALUES (3, '2023-02-04 19:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

-- - Dodać transfery pomiędzy 2 drużynami (więcej nie brak, aby do pokazania mu i testowania)

insert into public.transfer(first_student_id, second_student_id, first_team_id, second_team_id)
    values((select s.id from student s where s.index_number = 162426), (select s.id from student s where s.index_number = 162435),
    (select t.id from team t where t.name = 'Inzynierzy Oprogramowania'), (select t.id from team t where t.name = 'Mc Humans'));

insert into public.transfer(first_student_id, second_student_id, first_team_id, second_team_id)
    values((select s.id from student s where s.index_number = 162436), (select s.id from student s where s.index_number = 162444),
    (select t.id from team t where t.name = 'Mc Humans'), (select t.id from team t where t.name = 'Olsztyn OLS'));

insert into public.transfer(first_student_id, second_student_id, first_team_id, second_team_id)
    values((select s.id from student s where s.index_number = 162445), (select s.id from student s where s.index_number = 162429),
    (select t.id from team t where t.name = 'Olsztyn OLS'), (select t.id from team t where t.name = 'Inzynierzy Oprogramowania'));

update student set team_id = (select t.id from team t where t.name = 'Mc Humans') where index_number = 162426;
update student set team_id = (select t.id from team t where t.name = 'Inzynierzy Oprogramowania') where index_number = 162435;

update student set team_id = (select t.id from team t where t.name = 'Olsztyn OLS') where index_number = 162436;
update student set team_id = (select t.id from team t where t.name = 'Mc Humans') where index_number = 162444;

update student set team_id = (select t.id from team t where t.name = 'Olsztyn OLS') where index_number = 162429;
update student set team_id = (select t.id from team t where t.name = 'Inzynierzy Oprogramowania') where index_number = 162445;

-- - Dodać kilka statystyk meczowych też dla tej samej drużyny co transfery i rezerwacje

alter table match add column reservation_id BIGINT;

insert into public.match(first_team_id, second_team_id, referee_id, first_team_goals, second_team_goals, reservation_id)
values((select t.id from team t where t.name = 'Inzynierzy Oprogramowania'), (select t.id from team t where t.name = 'Mc Humans'),
       (select s.id from student s where s.index_number = 162436), 1, 2, (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 1));

insert into public.match(first_team_id, second_team_id, referee_id, first_team_goals, second_team_goals, reservation_id)
values((select t.id from team t where t.name = 'Inzynierzy Oprogramowania'), (select t.id from team t where t.name = 'Mc Humans'),
       (select s.id from student s where s.index_number = 162436), 2, 0, (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 2));

insert into public.match(first_team_id, second_team_id, referee_id, first_team_goals, second_team_goals, reservation_id)
values((select t.id from team t where t.name = 'Inzynierzy Oprogramowania'), (select t.id from team t where t.name = 'Mc Humans'),
       (select s.id from student s where s.index_number = 162436), 1, 0, (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 3));
