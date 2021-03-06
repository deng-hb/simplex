## Simplex

### 项目简介
这个项目主要是为了推广[eorm-spring](https://github.com/deng-hb/eorm-spring)这个基于Spring JDBC的ORM框架。


```
mvn clean install -Dmaven.test.skip=true
```

- more

### 建表模板
```
CREATE TABLE `tb_表名` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `operator` int(11) NOT NULL DEFAULT '0' COMMENT '操作人',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='表名描述';

```
+ 小写下划线命名格式
+ 每个字段都必须有描述
+ 字符编码utf8mb4

### docker redis mysql 服务
```
docker pull redis:latest
docker run -itd --name redis-server -p 6379:6379 redis

docker pull mysql:5.6
docker run -itd --name mysql-56 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123 mysql:5.6

```

### note
service 方法超过3个参数考虑使用对象来传递
