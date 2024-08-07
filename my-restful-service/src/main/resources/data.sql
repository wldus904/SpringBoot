-- 인프런 예제
insert into users(id, join_date, name, password, ssn) values(90001, now(), 'User1', 'test1111', '900817-1111111');
insert into users(id, join_date, name, password, ssn) values(90002, now(), 'User2', 'test2222', '900817-2222222');
insert into users(id, join_date, name, password, ssn) values(90003, now(), 'User3', 'test3333', '900817-3333333');

insert into post(description, user_id) values ('My first post', 90001);
insert into post(description, user_id) values ('My second post', 90001);

insert into member(member_id, password, name, address, email, phone, reg_date, mod_date) values('24080101', 'aaa111!!!', '학생1', '주소1', 'student1@test.com', '01011112222', '2024-08-01T12:12:12', '2024-08-01T12:12:12');
insert into member(member_id, password, name, address, email, phone, reg_date, mod_date) values('24080102', 'aaa222@@@', '학생2', '주소2', 'student2@test.com', '01022223333', '2024-08-01T12:12:12', '2024-08-01T12:12:12');
insert into member(member_id, password, name, address, email, phone, reg_date, mod_date) values('24080103', 'aaa333###', '학생3', '주소3', 'student3@test.com', '01044445555', '2024-08-01T12:12:12', '2024-08-01T12:12:12');

