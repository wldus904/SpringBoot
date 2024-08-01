insert into users(id, join_date, name, password, ssn) values(90001, now(), 'User1', 'test1111', '900817-1111111');
insert into users(id, join_date, name, password, ssn) values(90002, now(), 'User2', 'test2222', '900817-2222222');
insert into users(id, join_date, name, password, ssn) values(90003, now(), 'User3', 'test3333', '900817-3333333');

insert into post(description, user_id) values ('My first post', 90001);
insert into post(description, user_id) values ('My second post', 90001);