-- V1__Create_pastebin_table.sql

CREATE TABLE IF NOT EXISTS pastebin
(
    #  基本字段
    id           CHAR(10) PRIMARY KEY,
    expires_at   TIMESTAMP,
    created_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP,
    created_by   VARCHAR(64),
    deleted      TINYINT(1) DEFAULT 0,
    #  pastebin内容
    content      TEXT NOT NULL
);