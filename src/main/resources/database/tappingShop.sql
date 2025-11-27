### create database tappingShop;

use tappingShop;

create table items (
    id int auto_increment primary key,
    name varchar(50) not null,
    img_path varchar(100) not null,
    price int not null,
    discount_per int not null,
    created datetime default CURRENT_TIMESTAMP not null
);

INSERT INTO items(name, img_path, price, discount_per)
VALUES ('코랄블루 저소음 적축', '/img/coralblue01.jpg',172500, 10),
       ('그라파이트블루 갈축', '/img/graphiteblue01.jpg',178000, 5),
       ('그레이블루 저소음 45g', '/img/grayblue01.jpg',395000, 15),
       ('그레이블루 레드 적축', '/img/grapbluered01.jpg',107000, 5),
       ('리얼포스블랙 저소음 45g', '/img/realforceblack01.jpg',390000, 10),
       ('화이트블루스타 청축', '/img/whitebluestar01.jpg',107000, 5);


create table members(
    id int auto_increment primary key,
    name varchar(50) not null,
    login_id varchar(50) not null unique,
    login_pw varchar(100) not null,
    created datetime default CURRENT_TIMESTAMP not null
);

create table carts(
    id int auto_increment primary key,
    member_id int not null,
    item_id int not null,
    created datetime default CURRENT_TIMESTAMP not null
);

create table orders(
    id int auto_increment primary key,
    member_id int not null,
    name varchar(50) not null,
    address varchar(500) not null,
    payment varchar(10) not null,
    card_number varchar(16) not null,
    amount bigint not null,
    created datetime default  CURRENT_TIMESTAMP not null
);

create table order_items(
                            id       int auto_increment comment '아이디' primary key,
                            order_id int                                  not null comment '주문 아이디',
                            item_id  int                                  not null comment '주문 상품 아이디',
                            created  datetime default current_timestamp() not null comment '생성 일시'
);

select * from members;
