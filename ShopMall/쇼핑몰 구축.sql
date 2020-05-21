
ALTER TABLE member DROP PRIMARY KEY CASCADE;
DROP TABLE member;
CREATE TABLE member(
id VARCHAR2(20) PRIMARY KEY,
pwd VARCHAR2(20),
name VARCHAR2(40),
email VARCHAR2(40),
zip_num VARCHAR2(7),
address VARCHAR2(100),
phone VARCHAR2(20),
useyn CHAR DEFAULT 'y',
regdate DATE DEFAULT SYSDATE
);

ALTER TABLE product DROP PRIMARY KEY CASCADE;
DROP TABLE product;
CREATE TABLE product(
pseq NUMBER(5) PRIMARY KEY,
name VARCHAR2(100) DEFAULT 0,
kind CHAR(1),
price1 NUMBER(7) DEFAULT 0,
price2 NUMBER(7) DEFAULT 0,
price3 NUMBER(7) DEFAULT 0,
content VARCHAR2(1000) DEFAULT NULL,
image VARCHAR2(50) DEFAULT 'default.jpg',
useyn CHAR(1) DEFAULT 'y',
bestyn CHAR(1) DEFAULT 'n',
regdate DATE DEFAULT SYSDATE
);
DROP SEQUENCE product_seq;
CREATE SEQUENCE product_seq START WITH 1;

-- 관리자 테이블
ALTER TABLE worker DROP PRIMARY KEY CASCADE;
DROP TABLE worker;
CREATE TABLE worker(
id VARCHAR2(20) PRIMARY KEY,
pwd VARCHAR2(20),
name VARCHAR2(40),
phone VARCHAR2(20)
);

-- 주소 테이블
ALTER TABLE address DROP PRIMARY KEY CASCADE;
DROP TABLE address;
CREATE TABLE address(
zip_num VARCHAR2(7),
sido VARCHAR2(30),
gugun VARCHAR2(30),
dong VARCHAR2(100),
zip_code VARCHAR2(30),
bunji VARCHAR2(30)
);

-- 장바구니 테이블
ALTER TABLE cart DROP PRIMARY KEY CASCADE;
DROP TABLE cart;
CREATE TABLE cart(
cseq NUMBER(10) PRIMARY KEY,
id VARCHAR2(20),                -- 주문자 아이디
pseq NUMBER(5),                 -- 주문 상품번호
quantity NUMBER(5) DEFAULT 1,   -- 주문 수량
result CHAR(1) DEFAULT 1,       -- 주문 처리 여부, 1: 미처리, 2: 처리
indate DATE DEFAULT sysdate,
FOREIGN KEY (id) REFERENCES member (id),
FOREIGN KEY (pseq) REFERENCES product (pseq)
);
DROP SEQUENCE cart_seq;
CREATE SEQUENCE cart_seq START WITH 1;

-- 주문 테이블
ALTER TABLE orders DROP PRIMARY KEY CASCADE;
DROP TABLE orders;
CREATE TABLE orders(
oseq NUMBER(10) PRIMARY KEY,
id VARCHAR2(20) REFERENCES member (id),
indate DATE DEFAULT sysdate
);
DROP SEQUENCE orders_seq;
CREATE SEQUENCE orders_seq START WITH 1;

-- 주문 상세 테이블
ALTER TABLE order_detail DROP PRIMARY KEY CASCADE;
DROP TABLE order_detail;
CREATE TABLE order_detail(
odseq NUMBER(10) PRIMARY KEY,
oseq NUMBER(10) REFERENCES orders (oseq),
pseq number(5) REFERENCES product (pseq),
quantity NUMBER(5),
result CHAR(1) DEFAULT 1
);
DROP SEQUENCE order_detail_seq;
CREATE SEQUENCE order_detail_seq START WITH 1;

-- 게시판 테이블
ALTER TABLE qna DROP PRIMARY KEY CASCADE;
DROP TABLE qna;
CREATE TABLE qna(
qseq NUMBER(5) PRIMARY KEY,
subject VARCHAR2(30),
content VARCHAR2(1000),
reply VARCHAR2(1000),                   -- 답변 내용
id VARCHAR2(20) REFERENCES member (id), -- 작성자
rep CHAR(1) DEFAULT '1',                -- 답변 유무 
indate DATE DEFAULT sysdate
);
DROP SEQUENCE qna_seq;
CREATE SEQUENCE qna_seq START WITH 1;

-- 기본 데이터

insert into worker values('admin', 'admin', '홍관리', '010-777-7777');
insert into worker values('soonshin', '1234', '명강사', '010-999-9696');

insert into member(id, pwd, name, zip_num, address, phone) values
('one', '1111', '김나리', '133-110', '서울시성동구성수동1가 1번지21호', '017-777-7777');
insert into member(id, pwd, name, zip_num, address, phone) values
('two', '2222', '이백합', '130-120', '서울시송파구잠실2동 리센츠 아파트 201동 505호', '011-123-4567');

insert into product(pseq, name, kind, price1, price2, price3, content, image) values(
product_seq.nextval, '크로그다일부츠', '2', '40000', '50000', '10000', '오지니랄 크로그다일부츠 입니다.', 'w2.jpg');
insert into product(pseq, name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, '롱부츠', '2', 40000, 50000, 10000,'따뜻한 롱부츠 입니다.', 'w-28.jpg', 'n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values( 
product_seq.nextval, '힐', '1', '10000', '12000', '2000', '여성용전용 힐', 'w-26.jpg', 'n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, '슬리퍼', '4', '5000', '5500', '500', '편안한 슬리퍼입니다.', 'w-25.jpg', 'y');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, '회색힐', '1', '10000', '12000', '2000', '여성용전용 힐', 'w9.jpg', 'n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image) values(
product_seq.nextval, '여성부츠', '2', '12000', '18000', '6000', '여성용 부츠', 'w4.jpg');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval,  '핑크샌달', '3', '5000', '5500', '500', '사계절용 샌달입니다.', 'w-10.jpg', 'y');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, '슬리퍼', '3', '5000', '5500', '500', '편안한 슬리퍼입니다.', 'w11.jpg', 'y');
insert into product(pseq,  name, kind, price1, price2, price3, content, image) values(
product_seq.nextval,  '스니커즈', '4', '15000', '20000', '5000', '활동성이 좋은 스니커즈입니다.', 'w1.jpg');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval,  '샌달', '3', '5000', '5500', '500', '사계절용 샌달입니다.', 'w-09.jpg','n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval,  '스니커즈', '5', '15000', '20000', '5000', '활동성이 좋은 스니커즈입니다.', 'w-05.jpg','n');

insert into cart(cseq,id, pseq, quantity) values(cart_seq.nextval, 'one', 1, 1);

insert into orders(oseq, id) values(orders_seq.nextval, 'one');
insert into orders(oseq, id) values(orders_seq.nextval, 'one');
insert into orders(oseq, id) values(orders_seq.nextval, 'two');

insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 1, 1, 1);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 1, 2, 5);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 2,  4, 3);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 3, 1);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 2, 1);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 6, 2);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 1, 2);

insert into qna (qseq, subject, content, id)
values(qna_seq.nextval, '테스트', '질문내용1', 'one');
update qna SET reply='답변내용', rep='2';

insert into qna (qseq, subject, content, id)
values(qna_seq.nextval, '테스트2', '질문내용2', 'one');
commit;

-- 장바구니 보기 뷰
create or replace view cart_view
    as
select c.cseq, c.id, p.pseq, m.name mname, p.name pname, p.price2, c.quantity, c.indate, c.result
  from cart c, member m, product p
 where c.id = m.id 
   and c.pseq = p.pseq
   and result = 1;

-- 주문 내역 보기 뷰
create or replace view order_view
    as
select o.oseq, d.odseq, d.pseq, d.quantity, p.name pname, m.id, m.name mname, 
       o.indate, m.zip_num, m.address, m.phone, p.price2, d.result
  from orders o, member m, order_detail d, product p
 where p.pseq = d.pseq 
   and o.oseq = d.oseq 
   and o.id = m.id;

-- 베스트 상품 보기 뷰
create or replace view best_pro_view
    as
select pseq, name, price2, image
  from (select rownum, pseq, name, price2, image
          from product
         where bestyn = 'y'
         order by regdate desc)
 where rownum <= 4;

-- 신규 상품 보기 뷰
create or replace view new_pro_view
    as
select pseq, name, price2, image
  from (select rownum, pseq, name, price2, image
          from product
         where useyn = 'y'
         order by regdate desc)
 where rownum <= 4;


@zip

delete FROM member;

drop table address;
select * from orders;
select * from order_detail;
select * from product where pseq=41;
delete from order_detail where odseq > 10; 
delete from orders where id = 'test';
delete from cart where id ='test';
delete from member where id = 'test';
commit;


