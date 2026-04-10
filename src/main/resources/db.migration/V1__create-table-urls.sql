create table urls(
     idt_url bigint not null auto_increment,
     des_url_original varchar(4800) not null,
     des_key varchar(10) not null unique,
     flg_active boolean not null,
     dat_created datetime not null default current_timestamp,
     dat_modified datetime not null default current_timestamp on update current_timestamp,

     primary key(idt_url)
);