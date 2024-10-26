# 创建todo表格Flyway插件MySQL脚本
CREATE TABLE IF NOT EXISTS `todo_table`
(
    #  基本字段
    id           CHAR(10) PRIMARY KEY,
    expires_at   TIMESTAMP,
    created_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP,
    created_by   VARCHAR(64),
    deleted      TINYINT(1) DEFAULT 0,
    #  业务字段
    content      TEXT        NOT NULL COMMENT 'Todo内容',
    due_date     TIMESTAMP   NOT NULL COMMENT '过期时间',
    user_id      VARCHAR(64) NOT NULL,
    status       VARCHAR(64) NOT NULL DEFAULT 0 COMMENT '状态 ACTIVE为激活中 DISABLED为已禁用 DELETE为已删除'
);