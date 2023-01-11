
update match set second_team_id = (select t.id from team t where t.name = 'Olsztyn OLS')
where id = (select m.id from match m where m.first_team_goals = 1 and m.second_team_goals = 2);

update match set first_team_id = (select t.id from team t where t.name = 'Olsztyn OLS')
where id = (select m.id from match m where m.first_team_goals = 1 and m.second_team_goals = 0);

insert into match_statistic(event, minute, player_id, match_id)
values('GOAL', 6, (select s.id from student s where s.index_number = 162431),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 1)));

insert into match_statistic(event, minute, player_id, match_id)
values('YELLOW_CARD', 8, (select s.id from student s where s.index_number = 162431),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 1)));

insert into match_statistic(event, minute, player_id, match_id)
values('GOAL', 22, (select s.id from student s where s.index_number = 162446),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 1)));

insert into match_statistic(event, minute, player_id, match_id)
values('GOAL', 29, (select s.id from student s where s.index_number = 162447),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 1)));

insert into match_statistic(event, minute, player_id, match_id)
values('ASSIST', 22, (select s.id from student s where s.index_number = 162447),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 1)));

insert into match_statistic(event, minute, player_id, match_id)
values('ASSIST', 29, (select s.id from student s where s.index_number = 162446),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 1)));

-- second match

insert into match_statistic(event, minute, player_id, match_id)
values('GOAL', 46, (select s.id from student s where s.index_number = 162433),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 2)));

insert into match_statistic(event, minute, player_id, match_id)
values('GOAL', 55, (select s.id from student s where s.index_number = 162430),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 2)));

insert into match_statistic(event, minute, player_id, match_id)
values('ASSIST', 46, (select s.id from student s where s.index_number = 162433),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 2)));

insert into match_statistic(event, minute, player_id, match_id)
values('RED_CARD', 53, (select s.id from student s where s.index_number = 162447),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 2)));

-- third match

insert into match_statistic(event, minute, player_id, match_id)
values('YELLOW_CARD', 5, (select s.id from student s where s.index_number = 162448),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 3)));

insert into match_statistic(event, minute, player_id, match_id)
values('YELLOW_CARD', 7, (select s.id from student s where s.index_number = 162441),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 3)));

insert into match_statistic(event, minute, player_id, match_id)
values('GOAL', 55, (select s.id from student s where s.index_number = 162448),
    (select m.id from match m where m.reservation_id = (select r.id from reservation r where r.date = '2023-02-02 17:00' and r.pitch = 3)));



