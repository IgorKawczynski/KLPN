create sequence if not exists public.id_seq as bigint start with 1000;

create table if not exists public.user (
    id BIGINT NOT NULL DEFAULT nextval('id_seq') PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(9) NOT NULL,
    is_admin BOOLEAN NOT NULL
);

create table if not exists public.reservation (
    id BIGINT NOT NULL DEFAULT nextval('id_seq') PRIMARY KEY,
    pitch INT NOT NULL,
    date TIMESTAMP NOT NULL,
    user_id BIGINT REFERENCES public.user(id) NOT NULL
);

create table if not exists public.team (
    id BIGINT NOT NULL DEFAULT nextval('id_seq') PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    number_of_matches INT NOT NULL DEFAULT 0,
    wins INT NOT NULL DEFAULT 0,
    draws INT NOT NULL DEFAULT 0,
    loses INT NOT NULL DEFAULT 0,
    goals_for INT NOT NULL DEFAULT 0,
    goals_against INT NOT NULL DEFAULT 0,
    balance INT NOT NULL DEFAULT 0,
    points INT NOT NULL DEFAULT 0
);


create table if not exists public.student (
    id BIGINT REFERENCES public.user(id) NOT NULL PRIMARY KEY,
    index_number INT NOT NULL,
    role VARCHAR(30),
    position VARCHAR(30),
    tshirt_number INT,
    is_accepted BOOLEAN NOT NULL DEFAULT false,
    motm_amount INT,
    team_id BIGINT REFERENCES public.team(id)
);

create table if not exists public.match (
    id BIGINT NOT NULL DEFAULT nextval('id_seq') PRIMARY KEY,
    first_team_id BIGINT NOT NULL,
    second_team_id BIGINT NOT NULL,
    referee_id BIGINT NOT NULL,
    first_team_goals INT NOT NULL DEFAULT 0,
    second_team_goals INT NOT NULL DEFAULT 0
);

comment on column match.referee_id is 'This is id of a student who was a referee in this match';

create table if not exists public.match_statistic (
    id BIGINT NOT NULL DEFAULT nextval('id_seq') PRIMARY KEY,
    event VARCHAR(30) NOT NULL,
    minute INT NOT NULL,
    player_id BIGINT REFERENCES public.student(id) NOT NULL,
    match_id BIGINT REFERENCES public.match(id) NOT NULL
);

create table if not exists public.transfer (
    id BIGINT NOT NULL DEFAULT nextval('id_seq') PRIMARY KEY,
    first_student_id BIGINT NOT NULL,
    second_student_id BIGINT NOT NULL,
    first_team_id BIGINT NOT NULL,
    second_team_id BIGINT NOT NULL
);
