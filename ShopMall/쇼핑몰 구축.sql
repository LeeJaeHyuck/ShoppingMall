
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

-- ������ ���̺�
ALTER TABLE worker DROP PRIMARY KEY CASCADE;
DROP TABLE worker;
CREATE TABLE worker(
id VARCHAR2(20) PRIMARY KEY,
pwd VARCHAR2(20),
name VARCHAR2(40),
phone VARCHAR2(20)
);

-- �ּ� ���̺�
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

-- ��ٱ��� ���̺�
ALTER TABLE cart DROP PRIMARY KEY CASCADE;
DROP TABLE cart;
CREATE TABLE cart(
cseq NUMBER(10) PRIMARY KEY,
id VARCHAR2(20),                -- �ֹ��� ���̵�
pseq NUMBER(5),                 -- �ֹ� ��ǰ��ȣ
quantity NUMBER(5) DEFAULT 1,   -- �ֹ� ����
result CHAR(1) DEFAULT 1,       -- �ֹ� ó�� ����, 1: ��ó��, 2: ó��
indate DATE DEFAULT sysdate,
FOREIGN KEY (id) REFERENCES member (id),
FOREIGN KEY (pseq) REFERENCES product (pseq)
);
DROP SEQUENCE cart_seq;
CREATE SEQUENCE cart_seq START WITH 1;

-- �ֹ� ���̺�
ALTER TABLE orders DROP PRIMARY KEY CASCADE;
DROP TABLE orders;
CREATE TABLE orders(
oseq NUMBER(10) PRIMARY KEY,
id VARCHAR2(20) REFERENCES member (id),
indate DATE DEFAULT sysdate
);
DROP SEQUENCE orders_seq;
CREATE SEQUENCE orders_seq START WITH 1;

-- �ֹ� �� ���̺�
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

-- �Խ��� ���̺�
ALTER TABLE qna DROP PRIMARY KEY CASCADE;
DROP TABLE qna;
CREATE TABLE qna(
qseq NUMBER(5) PRIMARY KEY,
subject VARCHAR2(30),
content VARCHAR2(1000),
reply VARCHAR2(1000),                   -- �亯 ����
id VARCHAR2(20) REFERENCES member (id), -- �ۼ���
rep CHAR(1) DEFAULT '1',                -- �亯 ���� 
indate DATE DEFAULT sysdate
);
DROP SEQUENCE qna_seq;
CREATE SEQUENCE qna_seq START WITH 1;

-- �⺻ ������

insert into worker values('admin', 'admin', 'ȫ����', '010-777-7777');
insert into worker values('soonshin', '1234', '����', '010-999-9696');

insert into member(id, pwd, name, zip_num, address, phone) values
('one', '1111', '�質��', '133-110', '����ü�����������1�� 1����21ȣ', '017-777-7777');
insert into member(id, pwd, name, zip_num, address, phone) values
('two', '2222', '�̹���', '130-120', '����ü��ı����2�� ������ ����Ʈ 201�� 505ȣ', '011-123-4567');

insert into product(pseq, name, kind, price1, price2, price3, content, image) values(
product_seq.nextval, 'ũ�α״��Ϻ���', '2', '40000', '50000', '10000', '�����϶� ũ�α״��Ϻ��� �Դϴ�.', 'w2.jpg');
insert into product(pseq, name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, '�պ���', '2', 40000, 50000, 10000,'������ �պ��� �Դϴ�.', 'w-28.jpg', 'n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values( 
product_seq.nextval, '��', '1', '10000', '12000', '2000', '���������� ��', 'w-26.jpg', 'n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, '������', '4', '5000', '5500', '500', '����� �������Դϴ�.', 'w-25.jpg', 'y');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, 'ȸ����', '1', '10000', '12000', '2000', '���������� ��', 'w9.jpg', 'n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image) values(
product_seq.nextval, '��������', '2', '12000', '18000', '6000', '������ ����', 'w4.jpg');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval,  '��ũ����', '3', '5000', '5500', '500', '������� �����Դϴ�.', 'w-10.jpg', 'y');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, '������', '3', '5000', '5500', '500', '����� �������Դϴ�.', 'w11.jpg', 'y');
insert into product(pseq,  name, kind, price1, price2, price3, content, image) values(
product_seq.nextval,  '����Ŀ��', '4', '15000', '20000', '5000', 'Ȱ������ ���� ����Ŀ���Դϴ�.', 'w1.jpg');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval,  '����', '3', '5000', '5500', '500', '������� �����Դϴ�.', 'w-09.jpg','n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval,  '����Ŀ��', '5', '15000', '20000', '5000', 'Ȱ������ ���� ����Ŀ���Դϴ�.', 'w-05.jpg','n');

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
values(qna_seq.nextval, '�׽�Ʈ', '��������1', 'one');
update qna SET reply='�亯����', rep='2';

insert into qna (qseq, subject, content, id)
values(qna_seq.nextval, '�׽�Ʈ2', '��������2', 'one');
commit;

-- ��ٱ��� ���� ��
create or replace view cart_view
    as
select c.cseq, c.id, p.pseq, m.name mname, p.name pname, p.price2, c.quantity, c.indate, c.result
  from cart c, member m, product p
 where c.id = m.id 
   and c.pseq = p.pseq
   and result = 1;

-- �ֹ� ���� ���� ��
create or replace view order_view
    as
select o.oseq, d.odseq, d.pseq, d.quantity, p.name pname, m.id, m.name mname, 
       o.indate, m.zip_num, m.address, m.phone, p.price2, d.result
  from orders o, member m, order_detail d, product p
 where p.pseq = d.pseq 
   and o.oseq = d.oseq 
   and o.id = m.id;

-- ����Ʈ ��ǰ ���� ��
create or replace view best_pro_view
    as
select pseq, name, price2, image
  from (select rownum, pseq, name, price2, image
          from product
         where bestyn = 'y'
         order by regdate desc)
 where rownum <= 4;

-- �ű� ��ǰ ���� ��
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


