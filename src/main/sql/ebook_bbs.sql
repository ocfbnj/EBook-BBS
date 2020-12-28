drop database if exists ebook_bbs;
create database ebook_bbs;

use ebook_bbs;

-- 用户表
create table user
(
    user_id     int primary key auto_increment,
    username    varchar(16) unique not null,
    password    varchar(32)        not null,
    name        varchar(8),
    sex         enum ('M', 'F')    not null,
    age         tinyint,
    phone       char(11),
    email       varchar(32) unique not null,
    create_time datetime           not null
);

delimiter ;;
create procedure register_user(in username_ varchar(18),
                               in password_ varchar(20),
                               in name_ varchar(8),
                               in sex_ enum ('M', 'F'),
                               in age_ tinyint,
                               in phone_ char(11),
                               in email_ varchar(20))
begin
    insert into user (username, password, name, sex, age, phone, email, create_time)
    values (username_, password_, name_, sex_, age_, phone_, email_, now());
end
;;
delimiter ;

-- 测试数据
call register_user('Bob', '123456',
                   null, 'M', 20, '12345678901', '1391195421@qq.com');
call register_user('Alice', '123456',
                   null, 'F', 20, '12345678901', '123456789@qq.com');

-- 图书表
create table ebook
(
    ebook_id      int primary key auto_increment,
    cover_path    varchar(128) not null,
    title         varchar(32)  not null,
    author        varchar(16)  not null,
    classify      varchar(32)  not null,
    price         decimal      not null,
    intro         text         not null,
    download_path varchar(32)
);

-- 测试数据
insert into ebook (ebook_id, cover_path, title, author, classify, price, intro, download_path)
values (1, '/img/book/1.jpg', '小风暴1.0', '肖茉莉', '小说', 36,
        '献给青春，献给你！
        一部本土创投、金融精英的成长爱恨、职场奋斗史！
        本书在大时代的背景下，以金融才子高山、创投猎手秦沃为两条主线，情节围绕外交人才许信、律师木心喜、创业者谷东等年轻人的职场、创业、爱恨情仇展开。
        在近20年里，经历了“9•11”、非典疫情、全球金融危机、大众创业浪潮等时代大事件的一代人，也从青涩的少年，成长为职场的精英，甚至成为当下中国创业浪潮支柱、搅动国内经济波澜的主力。本书中主人公们，正是这样一群弄潮儿。
        他们站在时代的最前端，各自背负自己的梦想与爱恨，离开象牙塔，逐步去领悟职场规则，练就安身立命之本领，在金融、法律、创业等职业圈中，逐步成为年轻人中的佼佼者。
        每个人都在大时代里，创造自己的小风暴。这是一部年轻人的奋斗史，更是一类人对爱、梦想与青春交出的答卷。', null);

insert into ebook (ebook_id, cover_path, title, author, classify, price, intro, download_path)
values (2, '/img/book/2.jpg', 'C++ Primer', 'Lippman', '计算机科学', 9.9,
        'Countless programmers have learned C++ from previous editions of C++ Primer. During that time, C++ has matured greatly: Its focus, and that of its programming community, has widened from looking mostly at machine efficiency to devoting more attention to programmer efficiency.',
        'ebook_pdf/2.pdf');

insert into ebook (ebook_id, cover_path, title, author, classify, price, intro, download_path)
values (3, '/img/book/3.jpg', '算法导论', 'Thomas H. Cormen', '计算机科学', 3, 'Before there were computers, there were algorithms. But now that there are computers, there are even more algorithms, and algorithms lie at the heart of computing.
This book provides a comprehensive introduction to the modern study of computer algorithms. It presents many algorithms and covers them in considerable depth, yet makes their design and analysis accessible to all levels of readers. We have tried to keep explanations elementary without sacrificing depth of coverage or mathematical rigor.',
        'ebook_pdf/3.pdf');

-- 评论
create table comment
(
    comment_id        int primary key auto_increment,
    parent_comment_id int,
    content           text,
    user_id           int      not null,
    ebook_id          int      not null,
    date              datetime not null,

    foreign key (user_id) references user (user_id),
    foreign key (ebook_id) references ebook (ebook_id)
);

create view ebook_comment as
select comment.ebook_id,
       comment.comment_id,
       comment.content,
       comment.parent_comment_id,
       comment.user_id,
       comment.date,
       user.username
from user
         join
     comment
     on user.user_id = comment.user_id
order by comment.date desc
;

delimiter ;;
create procedure add_comment(in parent_comment_id_ int,
                             in content_ text,
                             in user_id_ int,
                             in ebook_id_ int)
begin
    insert into comment (parent_comment_id, content, user_id, ebook_id, date)
    values (parent_comment_id_, content_, user_id_, ebook_id_, now());
end;;
delimiter ;

-- 测试数据
call add_comment(null, '呵呵', 1, 1);
