-- ----------------------------
-- Table structure for ip_information_t
-- ----------------------------
CREATE TABLE IF NOT EXISTS `ip_information_t`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `start_ip` BIGINT DEFAULT NULL,
    `end_ip`   BIGINT DEFAULT NULL,
    `location` varchar(255) DEFAULT NULL,
    `isp`      varchar(255) DEFAULT NULL,
    INDEX `idx_ip_range`(`start_ip`, `end_ip`) USING BTREE,
    INDEX `idx_start`(`start_ip`) USING BTREE,
    INDEX `idx_end`(`end_ip`) USING BTREE,
    PRIMARY KEY (`id`)
);