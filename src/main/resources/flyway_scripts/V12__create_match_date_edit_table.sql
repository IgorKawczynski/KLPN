create table if not exists public.match_date_edit (
    id BIGINT NOT NULL DEFAULT nextval('id_seq') PRIMARY KEY,
    pitch INT NOT NULL,
    date TIMESTAMP NOT NULL,
    user_id BIGINT REFERENCES public.user(id) NOT NULL,
    reservation_id BIGINT REFERENCES public.reservation(id) NOT NULL,
    is_accepted BOOLEAN NOT NULL
);