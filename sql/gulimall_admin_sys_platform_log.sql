create table sys_platform_log
(
    id                 bigint unsigned auto_increment
        primary key,
    request_uri        varchar(255) null comment 'uri',
    request_url        varchar(255) null comment 'url',
    request_method     char(8)      null,
    request_params     varchar(511) null comment '请求参数',
    class_name         varchar(255) null comment '请求类',
    method_name        varchar(255) null comment '请求方法',
    method_type        tinyint      null comment '请求类型 增1删2改3查4',
    method_description varchar(255) null comment '操作描述',
    server_ip          varchar(55)  null comment '服务器地址',
    client_ip          varchar(55)  null comment '客户端地址',
    is_success         tinyint      null comment '是否成功',
    is_login           tinyint      null comment '是否为登录请求',
    spend_time         bigint       null comment '耗时',
    operation_userid   bigint       null comment '操作用户账号',
    operation_username varchar(100) null comment '操作用户名称',
    operation_time     timestamp    null comment '创建时间'
)
    comment '系统日志表' engine = MyISAM
                    charset = utf8;

create index idx_sl_userid
    on sys_platform_log (operation_userid);

INSERT INTO gulimall_admin.sys_platform_log (id, request_uri, request_url, request_method, request_params, class_name, method_name, method_type, method_description, server_ip, client_ip, is_success, is_login, spend_time, operation_userid, operation_username, operation_time) VALUES (1514625172097740802, '/gulimall-admin/admin/user/page', 'http://192.168.1.4:9090/gulimall-admin/admin/user/page', 'GET', '{"ps":10,"pn":1}', 'com.laughingather.gulimall.admin.controller.SysUserController', 'listUserWithPage()', 4, '分页获取用户列表', '127.0.0.1', '0:0:0:0:0:0:0:1%0', 1, 0, 40, 1463452828315029504, 'wangjie', '2022-04-14 23:22:36');
INSERT INTO gulimall_admin.sys_platform_log (id, request_uri, request_url, request_method, request_params, class_name, method_name, method_type, method_description, server_ip, client_ip, is_success, is_login, spend_time, operation_userid, operation_username, operation_time) VALUES (1516040569101811714, '/gulimall-admin/admin/user/page', 'http://192.168.1.4:9090/gulimall-admin/admin/user/page', 'GET', '{"ps":10,"pn":1}', 'com.laughingather.gulimall.admin.controller.SysUserController', 'listUserWithPage()', 4, '分页获取用户列表', '192.168.1.4', '0:0:0:0:0:0:0:1%0', 1, 0, 47, 1463452828315029504, 'wangjie', '2022-04-18 21:06:53');
INSERT INTO gulimall_admin.sys_platform_log (id, request_uri, request_url, request_method, request_params, class_name, method_name, method_type, method_description, server_ip, client_ip, is_success, is_login, spend_time, operation_userid, operation_username, operation_time) VALUES (1514633982673711106, '/gulimall-admin/admin/role', 'http://192.168.1.4:9090/gulimall-admin/admin/role', 'POST', '{"id":1514633900226383872,"roleName":"管理员","roleCode":"admin","description":"后台服务管理","createBy":null,"createTime":"2022-04-14 23:57:17","updateBy":null,"updateTime":null}', 'com.laughingather.gulimall.admin.controller.SysRoleController', 'saveRole()', 1, '保存角色', '127.0.0.1', '0:0:0:0:0:0:0:1%0', 1, 0, 48, 1463452828315029504, 'wangjie', '2022-04-14 23:57:37');
