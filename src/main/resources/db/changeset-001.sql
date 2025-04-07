create table users (
    pk uuid not null default pg_catalog.uuid_generate_v4(),
    first_name varchar(32) not null,
    last_name varchar(32) not null,
    email varchar(64) unique not null,
    password varchar(256) not null
);

alter table users add constraint pk__users primary key (pk);

create unique index ix__users__email on users (email) where email is not null;
