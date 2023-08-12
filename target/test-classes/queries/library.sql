-- US01 -1
select count(id) from users;

select count(distinct id) from users;

-- US01 -2
select * from users;

-- US02
Select count(*) from book_borrow
where is_returned = 0;