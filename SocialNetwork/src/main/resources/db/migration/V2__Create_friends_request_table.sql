create table friendRequest (
                               id bigserial not null,
                               approver_user_id int8,
                               requester_user_id int8,
                               primary key (id)
);
insert into friendRequest(approver_user_id, requester_user_id)
values (1 ,2);
insert into friendRequest(approver_user_id, requester_user_id)
values (2 ,3);
