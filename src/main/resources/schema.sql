create table if not exists todo
(
    id          varchar primary key,
    title       varchar not null,
    description text,
    completed   boolean   default false,
    deleted     boolean   default false,
    created_at  timestamp default now(),
    updated_at  timestamp default now()
);


