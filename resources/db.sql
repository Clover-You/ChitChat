-- 创建数据库
create database chit_chat default charset=utf8mb4 collate=utf8mb4_unicode_ci;

use chit_chat;

-- 创建用户表
create table sys_user(
  id bigint(20) unsigned not null primary key auto_increment comment '用户 ID',
  name varchar(20) comment '用户昵称',
  avatar varchar(255) collate utf8mb4_unicode_ci default null comment '用户头像',
  create_time datetime not null default current_timestamp() comment '注册时间'
) auto_increment=20000 comment '用户表';