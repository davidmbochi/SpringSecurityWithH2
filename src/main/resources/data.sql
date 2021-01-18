-- role data
insert into Role(name) values ('ROLE_MEMBER');
insert into Role(name) values ('ROLE_TRAINER');
insert into Role(name) values ('ROLE_ADMIN');

-- subscription dummy data

insert into Subscription(cost,description,start_date,end_date,duration)
values ('1500','This package is for beginners who want to advance to intermediate level.','2021-01-14','2021-01-17',3);

insert into Subscription(cost,description,start_date,end_date,duration)
values ('3000','This package is for intermediate members who want to advance to professional level.','2021-01-18','2021-01-21',3);

insert into Subscription(cost,description,start_date,end_date,duration)
values ('5500','This package is for professional members who want to advance further.','2021-01-22','2021-01-25',3);
