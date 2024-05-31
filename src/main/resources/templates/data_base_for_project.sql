create table if not exists users
(
    users_id               bigint generated always as identity
        primary key,
    users_first_name       varchar(150) not null,
    users_name             varchar(150) not null,
    users_last_name        varchar(150),
    users_date_of_birthday date         not null,
    users_email            text         not null
        constraint users_pk
            unique
        constraint users_pk_2
            unique,
    users_password         varchar      not null,
    users_phone_number     varchar(10)  not null,
    users_role             varchar(50)  not null,
    users_is_studying      boolean
);

alter table users
    owner to postgres;

create table if not exists day_of_week
(
    day_id   bigint generated always as identity
        primary key,
    day_name text not null
        unique
);

alter table day_of_week
    owner to postgres;

create table if not exists "group"
(
    group_id               bigint generated always as identity
        primary key,
    group_number           bigint not null
        unique
        constraint group_pk
            unique,
    group_of_instructor_id bigint not null
        constraint group_fk2
            references users
);

alter table "group"
    owner to postgres;

create table if not exists week
(
    week_id            bigint generated always as identity
        primary key,
    start_date_of_week date not null
        unique,
    last_date_of_week  date not null
        unique
);

alter table week
    owner to postgres;

create table if not exists users_group
(
    users_group_id bigint generated always as identity
        primary key,
    fk_user_id     bigint not null
        constraint users_group_pk
            unique
        constraint users_group_fk1
            references users,
    fk_group_id    bigint
        constraint users_group_fk2
            references "group"
);

alter table users_group
    owner to postgres;

create table if not exists time
(
    time_id bigint generated always as identity
        primary key,
    time    time not null
        unique
);

alter table time
    owner to postgres;

create table if not exists record
(
    record_id         bigint generated always as identity
        primary key,
    fk_week_id        bigint not null
        constraint week__fk
            references week,
    fk_day_of_week    bigint not null
        constraint record_fk2
            references day_of_week,
    fk_student_id     bigint not null
        constraint record_fk3
            references users,
    fk_time_of_record bigint not null
        constraint record_fk4
            references time
);

alter table record
    owner to postgres;