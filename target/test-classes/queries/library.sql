-- US01 -1
select count(id) from users;

select count(distinct id) from users;

-- US01 -2
select * from users;

-- US02
Select count(*) from book_borrow
where is_returned = 0;

-- US03
select count(*) from book_categories;
select name from book_categories;

-- US04
select * from books
where name = 'Clean Code Odil';

-- US05

Select book_id from book_borrow
group by book_id
order by count(*)desc
limit 1;

select book_category_id from books where id = (Select book_id from book_borrow
                                group by book_id
                                order by count(*)desc
                                limit 1);

select name from book_categories
            where id = (select book_category_id from books
            where id = (Select book_id from book_borrow
            group by book_id
            order by count(*)desc
            limit 1));

select name, author, isbn from books
where name = 'Head First Java';

