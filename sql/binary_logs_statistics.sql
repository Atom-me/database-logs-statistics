create table binary_logs_statistics
(
    id            bigint       not null auto_increment primary key,
    database_host varchar(255) not null default '' comment 'database host',
    database_port int comment 'database port',
    log_name      varchar(255) not null default '' comment 'bin log file name',
    file_size     DECIMAL(20, 2) comment 'bin log file size',
    encrypted     varchar(255) comment 'encrypted',
    collect_time  DATETIME     NOT NULL comment 'collect time',
    create_time   DATETIME     NOT NULL DEFAULT now() comment 'create time',
    update_time   DATETIME     NOT NULL DEFAULT now() comment 'update time' ON UPDATE CURRENT_TIMESTAMP,
    unique index uni_host_port_log_name(database_host,database_port,log_name)
)engine=innodb DEFAULT CHARSET=utf8mb4 comment 'mysql bin log file size collection';