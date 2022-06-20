use masterdev_hoand68_index

CREATE table users(
id mediumint unsigned auto_increment primary key,
username varchar(12) unique not null,
fullname varchar(30) not null,
province varchar(3) not null,
age tinyint unsigned not null
)

create index indexProvince on users(province)
create index indexAge on users(age)
create index indexUsername on users(username)




alter TABLE users drop index indexAge


select * from users WHERE username="ghtk"

select * 
from users 
WHERE province = "SG"
order by age DESC 
limit 10
