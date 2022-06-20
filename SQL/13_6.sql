use masterdev_hoand68;

CREATE TABLE student(
idStudent varchar(45) primary key ,
nameStudent varchar(45) not null
);

create table subject(
idSubject varchar(45) primary key,
nameSubject varchar(45) not null
);

create table teacher(
idTeacher varchar(45) primary key,
nameTeacher varchar(45) not null,
idSubject varchar(45)
);

create table class(
idClass varchar(45) primary key ,
nameClass varchar(45) not null,
idTeacher varchar(45),
idSubject varchar(45)
);

create table class_student(
idClass varchar(45) not null,
idStudent varchar(45) not null,
idSubject varchar(45) not null,
mark float(2) not null,
primary key(idClass,idStudent)
);

DELETE from class_student  

SELECT * from student;
select * from teacher;
select * from subject;
select * from class;
select * from class_student;

#Diem trung binh toan truong
SELECT AVG(mark)
from class_student

#Ti le truot,trung binh, kha, gioi, xuat sac
select (SELECT COUNT(mark) from class_student WHERE mark<4)*100/(SELECT COUNT(mark) from class_student ) as TiLeTruot,
(SELECT COUNT(mark) from class_student WHERE mark>4 and mark<6)*100/(SELECT COUNT(mark) from class_student ) as TiLeTrungBinh,
(SELECT COUNT(mark) from class_student WHERE mark>6 and mark<8)*100/(SELECT COUNT(mark) from class_student ) as TiLeKha,
(SELECT COUNT(mark) from class_student WHERE mark>8 and mark<9)*100/(SELECT COUNT(mark) from class_student ) as TiLeGioi,
(SELECT COUNT(mark) from class_student WHERE mark>9)*100/(SELECT COUNT(mark) from class_student ) as TiLeXuatSac

#Mon co diem trung binh cao nhat
select nameSubject, avg_mark 
from
(select idSubject , avg(mark) as avg_mark
		from class_student
		group by idSubject) p 
INNER JOIN subject s 
on p.idSubject = s.idSubject
WHERE avg_mark = (SELECT MAX(avg_mark)
					from (select idSubject , avg(mark) as avg_mark
		from class_student
		group by idSubject) as max_avg_mark
)


#Lop co diem trung binh cao nhat
select nameClass, avg_mark 
from
(select idClass , avg(mark) as avg_mark
		from class_student
		group by idClass) p 
INNER JOIN class s
on p.idClass = s.idClass
WHERE avg_mark = (SELECT MAX(avg_mark)
					from (select idClass , avg(mark) as avg_mark
		from class_student
		group by idClass) as max_avg_mark
)


#Ban co diem trung binh cao nhat
select nameStudent, avg_mark 
from
(select idStudent , avg(mark) as avg_mark
		from class_student
		group by idStudent) p 
INNER JOIN student s
on p.idStudent = s.idStudent
WHERE avg_mark = (SELECT MAX(avg_mark)
					from (select idStudent , avg(mark) as avg_mark
		from class_student
		group by idStudent ) as max_avg_mark
)

#Mon co ti le truot cao nhat
SELECT nameSubject ,ratio_fail
from 
(SELECT nameSubject, ratio_fail
from
(SELECT p.idSubject , count_fail_mark*100/COUNT(mark) as ratio_fail
from
(SELECT idSubject , COUNT(fail_mark) as count_fail_mark 
from 
(SELECT idSubject,mark as fail_mark
from class_student
WHERE mark < 4) as fail_mark_lst
group by fail_mark_lst.idSubject) p
INNER JOIN class_student
on p.idSubject = class_student.idSubject
GROUP BY idSubject) m
INNER JOIN subject s 
on m.idSubject = s.idSubject) as a
ORDER by ratio_fail DESC
limit 1


#Danh sach cac ban khong truot
SELECT s.idStudent , s.nameStudent
from student s 
WHERE s.idStudent not in
(SELECT DISTINCT idStudent
from class_student cs 
WHERE mark<4) 


#Top 10 mon kho nhat
SELECT nameSubject ,ratio_fail
from 
(SELECT nameSubject, ratio_fail
from
(SELECT p.idSubject , count_fail_mark*100/COUNT(mark) as ratio_fail
from
(SELECT idSubject , COUNT(fail_mark) as count_fail_mark 
from 
(SELECT idSubject,mark as fail_mark
from class_student
WHERE mark < 4) as fail_mark_lst
group by fail_mark_lst.idSubject) p
INNER JOIN class_student
on p.idSubject = class_student.idSubject
GROUP BY idSubject) m
INNER JOIN subject s 
on m.idSubject = s.idSubject) as a
order by ratio_fail DESC
LIMIT 10


