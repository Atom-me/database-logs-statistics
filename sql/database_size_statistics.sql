create table if not exists database_size_statistics
(
    id            BIGINT auto_increment primary key,
    database_host VARCHAR(255) comment '数据库host',
    database_port VARCHAR(6) comment '数据库port',
    database_type VARCHAR(255) comment '数据库类型',
    database_name VARCHAR(255) comment '数据库名称',
    database_size DECIMAL(20, 2) comment '数据库大小，字节',
    collect_time  DATETIME NOT NULL comment '采集时间',
    create_time   DATETIME DEFAULT now()  comment '创建时间',
    update_time   DATETIME DEFAULT now() comment '更新时间' ON UPDATE CURRENT_TIMESTAMP
) engine=innodb DEFAULT CHARSET=utf8mb4 comment '数据库存储大小统计，按天统计';