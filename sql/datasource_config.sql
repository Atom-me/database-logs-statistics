CREATE TABLE if not exists  datasource_config
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(255) NOT NULL,
    database_type VARCHAR(255) NOT NULL,
    host          VARCHAR(255) NOT NULL,
    port          INT          NOT NULL,
    username      VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    create_time   DATETIME    NOT NULL  DEFAULT now(),
    update_time   DATETIME     NOT NULL DEFAULT now() ON UPDATE CURRENT_TIMESTAMP,
    unique index uni_host_port(host,port)
)engine=innodb DEFAULT CHARSET=utf8mb4 comment '数据源配置表，用于统计，收集数据库磁盘占用';


-- insert into datasource_config(name, database_type, host, port, username, password,create_time,update_time)
-- values ("atom test mysql", "mysql", "47.92.253.131",3306, "root", "SY9geC3tZdc", now(), now());
--
--
-- insert into datasource_config(name, database_type, host, port, username, password,create_time,update_time)
-- values ("atom test pg", "postgresql", "10.1.39.95",5432, "postgres", "123456", now(), now());




DELIMITER //
DROP TRIGGER IF EXISTS mysql_discover_init_datasource_config_trigger//
CREATE TRIGGER mysql_discover_init_datasource_config_trigger AFTER INSERT ON database_instance FOR EACH ROW
BEGIN
    REPLACE INTO datasource_config(name,database_type,host,port,username,password,create_time,update_time) VALUES("mysql instance","mysql",new.hostname,new.port,"orch_topology","voo9ePh:",now(),now());
END
//
DELIMITER ;