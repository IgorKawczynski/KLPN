-- USER
INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
    VALUES ('Pierwszy', 'Pierwszakowski', 'jeden@gmail.com', 'pokiCoNieTajne', '507066425', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
    VALUES ('Drugi', 'Meszyński', 'dwa@gmail.com', 'pokiCoNieTajne', '507066425', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
    VALUES ('Trzeci', 'Kruyfiński', 'trzy@gmail.com', 'pokiCoNieTajne', '613066335', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Czwarty', 'Pierwszakowski', 'cztery@gmail.com', 'pokiCoNieTajne', '507066425', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Piąty', 'Meszyński', 'piec@gmail.com', 'pokiCoNieTajne', '507066425', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Szósty', 'Kruyfiński', 'szesc@gmail.com', 'pokiCoNieTajne', '613066335', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Siódmy', 'Pierwszakowski', 'siedem@gmail.com', 'pokiCoNieTajne', '507066425', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Ósmy', 'Meszyński', 'osiem@gmail.com', 'pokiCoNieTajne', '507066425', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Dziewiąty', 'Kruyfiński', 'dziewiec@gmail.com', 'pokiCoNieTajne', '613066335', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Dziesiąty', 'Pierwszakowski', 'dziesiec@gmail.com', 'pokiCoNieTajne', '507066425', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Jedenasty', 'Meszyński', 'jedenascie@gmail.com', 'pokiCoNieTajne', '507066425', false);

INSERT INTO public."user"(
    first_name, last_name, email, password, phone_number, is_admin)
VALUES ('Dwunasty', 'Kruyfiński', 'dwanascie@gmail.com', 'pokiCoNieTajne', '613066335', false);

-- STUDENT
INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'johanKruyff21@gmail.com'), 162426, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'jeden@gmail.com'), 162427, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'dwa@gmail.com'), 162428, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'trzy@gmail.com'), 162429, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'cztery@gmail.com'), 162430, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'piec@gmail.com'), 162431, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'szesc@gmail.com'), 162432, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'siedem@gmail.com'), 162433, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'osiem@gmail.com'), 162434, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'dziewiec@gmail.com'), 162435, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'dziesiec@gmail.com'), 162436, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'jedenascie@gmail.com'), 162437, false);

INSERT INTO public.student(id, index_number, is_accepted)
VALUES ((select u.id from public.user u where u.email = 'dwanascie@gmail.com'), 162438, false);