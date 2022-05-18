drop database if exists inventory;

create database inventory;

use inventory;

create table po (
    ord_id int auto_increment not null,
    name varchar(64) not null,
    email varchar(64),
    primary key(ord_id)
);
/* 
create table sku (
    sku_id char(6) not null,
    prod_name varchar(32) not null,
    unit_price float(7, 2) not null,

    primary key(sku_id)
);
 */
create table line_item (
    item_id int auto_increment not null,
    -- sku_id char(6),
    prod_name varchar(64) not null,
    quantity int,
    unit_price float not null,
    ord_id int,
    
    primary key(item_id),
    constraint fk_ord_id
        foreign key(ord_id)
        references po(ord_id)/* ,
    constraint fk_sku_id
        foreign key(sku_id)
        references sku(sku_id) */
);

create view po_total as
    select po.ord_id, po.name, sum(li.quantity * li.unit_price) as total 
        from po
        join line_item as li
        on po.ord_id = li.ord_id
        group by ord_id;