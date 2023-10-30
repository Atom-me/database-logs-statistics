create table information_schema_files_statistics
(
    id              bigint       not null auto_increment primary key,
    database_host   varchar(255) not null default '' comment 'database host',
    database_port   int comment 'database port',
    database_type   varchar(50) comment 'database type',
    file_id         bigint       not null comment 'file id',
    file_name       varchar(255) not null default '' comment 'file name',
    tablespace_name varchar(255) not null default '' comment 'tablespace name',
    free_extents    DECIMAL(20, 2) comment 'free extents',
    total_extents   DECIMAL(20, 2) comment 'total extents',
    data_free       DECIMAL(20, 2) comment 'data free',
    collect_time    DATETIME     NOT NULL comment 'collect time',
    create_time     DATETIME     NOT NULL DEFAULT now() comment 'create time',
    update_time     DATETIME     NOT NULL DEFAULT now() comment 'update time' ON UPDATE CURRENT_TIMESTAMP,
    unique index uni_host_port_file_name(database_host,database_port,file_name)
)engine=innodb DEFAULT CHARSET=utf8mb4 comment 'mysql information schema file table statistics ';



