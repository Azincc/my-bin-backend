-- V1__Create_pastebin_table.sql

CREATE TABLE pastebin
(
    id              CHAR(10) PRIMARY KEY,
    content         TEXT NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at      TIMESTAMP,
    mark_for_delete TINYINT(1) DEFAULT 0
);