-- TEAM
INSERT INTO public.team(
    name, number_of_matches, wins, draws, loses, goals_for, goals_against, balance, points)
    VALUES ('Kortowskie DZIKI', 3, 1, 0, 2, 5, 4, 1, 3);

INSERT INTO public.team(
    name, number_of_matches, wins, draws, loses, goals_for, goals_against, balance, points)
    VALUES ('Delta SQUADRON', 3, 2, 0, 1, 4, 5, -1, 6);

INSERT INTO public.team(
    name, number_of_matches, wins, draws, loses, goals_for, goals_against, balance, points)
    VALUES ('TOPTeamTOP_8_00', 0, 0, 0, 0, 0, 0, 0, 0);

-- USER
INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
    VALUES ('Krystyna', 'Ronualdzka', 'cristiano7@gmail.com', 'pokiCoNieTajne', '507066425', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
    VALUES ('Leonard', 'Meszyński', 'mesimesi10@gmail.com', 'pokiCoNieTajne', '507066425', true);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
    VALUES ('Joahim', 'Kruyfiński', 'johanKruyff21@gmail.com', 'pokiCoNieTajne', '613066335', false);


-- STUDENT
INSERT INTO public.student(
    id, index_number, role,
    "position", tshirt_number, is_accepted,
    motm_amount, team_id)
    VALUES ((select u.id from public.user u where u.email = 'cristiano7@gmail.com'), 162425, 'player',
            'GK', 1, true,
            0, (select t.id from public.team t where t.name = 'Kortowskie DZIKI'));

INSERT INTO public.student(
    id, index_number, role,
    "position", tshirt_number, is_accepted,
    motm_amount, team_id)
    VALUES ((select u.id from public.user u where u.email = 'mesimesi10@gmail.com'), 164402, 'player',
            'ST', 9, true,
            10, (select t.id from public.team t where t.name = 'Delta SQUADRON'));

-- TRANSFER
INSERT INTO public.transfer(
    first_student_id,
    second_student_id,
    first_team_id,
    second_team_id)
    VALUES ((select u.id from public.user u where u.email = 'johanKruyff21@gmail.com'),
            (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'),
            null,
            null);

INSERT INTO public.transfer(
    first_student_id,
    second_student_id,
    first_team_id,
    second_team_id)
    VALUES ((select u.id from public.user u where u.email = 'cristiano7@gmail.com'),
            (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'),
            null,
            null);


INSERT INTO public.transfer(
    first_student_id,
    second_student_id,
    first_team_id,
    second_team_id)
    VALUES (null,
            (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'),
            (select t.id from public.team t where t.name = 'Kortowskie DZIKI'),
            null);

INSERT INTO public.transfer(
    first_student_id,
    second_student_id,
    first_team_id,
    second_team_id)
    VALUES (null,
            (select u.id from public.user u where u.email = 'cristiano7@gmail.com'),
            (select t.id from public.team t where t.name = 'Delta SQUADRON'),
            null);

-- RESERVATION
INSERT INTO public.reservation(
    pitch, date, user_id)
    VALUES (1, '2023-01-01 14:00', (select u.id from public.user u where u.email = 'mesimesi10@gmail.com'));

INSERT INTO public.reservation(
    pitch, date, user_id)
    VALUES (2, '2023-01-01 15:00', (select u.id from public.user u where u.email = 'johanKruyff21@gmail.com'));

INSERT INTO public.reservation(
    pitch, date, user_id)
    VALUES (3, '2023-01-01 16:00', (select u.id from public.user u where u.email = 'johanKruyff21@gmail.com'));