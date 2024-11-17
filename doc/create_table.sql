create database if not exists smart_canvas;

use smart_canvas;

-- 用户表
-- auto-generated definition
create table user
(
    id            bigint auto_increment comment 'id'
        primary key,
    user_account  varchar(256)                           not null comment '账号',
    user_password varchar(512)                           not null comment '密码',
    user_name     varchar(256)                           null comment '用户昵称',
    user_avatar   varchar(1024)                          null comment '用户头像',
    user_profile  varchar(512)                           null comment '用户简介',
    user_role     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    has_deleted   tinyint      default 0                 not null comment '是否删除'
)
    comment '用户表';

-- 图表信息表
-- auto-generated definition
create table chart
(
    id              bigint auto_increment comment '图表id'
        primary key,
    goal            text                                null comment '分析目的',
    chart_data      text                                null comment '图表数据',
    chart_type      varchar(256)                        null comment '图表类型',
    generated_chart text                                null comment '生成的图表',
    analyzed_result text                                null comment '分析结果',
    creater_id      bigint                              null comment '图表创建者id',
    create_time     timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    has_deleted     tinyint   default 0                 not null comment '逻辑删除,1表示已删除'
)
    comment '存储可视化图表';

