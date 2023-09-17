CREATE TABLE hotels(
    id BIGSERIAL PRIMARY KEY,
    name varchar(64) NOT NULL,
    city varchar(64) NOT NULL
);

CREATE TABLE rooms(
    id bigserial primary key,
    hotel_id bigint not null,
    room_number int NOT NULL ,
    price decimal(14,4),
    foreign key (hotel_id)REFERENCES hotels(id) on DELETE CASCADE
);

create table reservations(
    id bigserial primary key,
    user_id bigint not null,
    room_id bigint not null,
    date_start date not null,
    date_end date not null
);