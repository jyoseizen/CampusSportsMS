DROP DATABASE IF EXISTS sport ;
CREATE DATABASE sport CHARACTER SET utf8;
use sport ;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for applicationform
-- ----------------------------
DROP TABLE IF EXISTS `applicationform`;
CREATE TABLE `applicationform`  (
  `id` bigint(51) NOT NULL AUTO_INCREMENT,
  `users_stnumber` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_nb` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 AVG_ROW_LENGTH = 4096 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of applicationform
-- ----------------------------
INSERT INTO `applicationform` VALUES (8, 'admin', '001');

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` bigint(51) NOT NULL AUTO_INCREMENT,
  `college_name` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_stnumber` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `college_number` varchar(23) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 AVG_ROW_LENGTH = 4096 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, '计算机学院', NULL, '001');
INSERT INTO `college` VALUES (2, '土木学院', NULL, '002');
INSERT INTO `college` VALUES (3, '物理学院', NULL, '003');

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition`  (
  `id` bigint(51) NOT NULL AUTO_INCREMENT,
  `competition_nb` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_ne` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_start` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_end` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_site` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `apply_start` datetime NULL DEFAULT NULL,
  `apply_end` datetime NULL DEFAULT NULL,
  `competition_pe` varchar(232) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_tnor` varchar(222) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 AVG_ROW_LENGTH = 4096 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of competition
-- ----------------------------
INSERT INTO `competition` VALUES (1, '001', '跳远比赛', '2023-11-15 00:00:00', '2023-11-22 00:00:00', '北门操场', '2023-11-10 00:00:00', '2023-11-14 00:00:00', '20', '15');
INSERT INTO `competition` VALUES (2, '002', '团队拔河', '2023-11-12 00:00:00', '2023-11-12 00:00:00', '西门公园', '2023-11-11 00:00:00', '2023-11-16 00:00:00', '4', '2');

-- ----------------------------
-- Table structure for draft
-- ----------------------------
DROP TABLE IF EXISTS `draft`;
CREATE TABLE `draft`  (
  `draft_id` bigint(51) NOT NULL AUTO_INCREMENT,
  `users_stnumber` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_name` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `college_name` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `draft_state` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `draft_content` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `id`(`draft_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 AVG_ROW_LENGTH = 4096 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of draft
-- ----------------------------
INSERT INTO `draft` VALUES (1, 'admin', 'admin', NULL, '1', '加油加油');

-- ----------------------------
-- Table structure for enroll
-- ----------------------------
DROP TABLE IF EXISTS `enroll`;
CREATE TABLE `enroll`  (
  `enroll_id` bigint(51) NOT NULL AUTO_INCREMENT,
  `college_name` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_integral` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_stnumber` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_sex` int(3) NULL DEFAULT NULL,
  `competition_nb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_ne` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_start` varchar(222) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_end` varchar(222) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_site` varchar(222) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_name` varchar(222) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_phone` varchar(222) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_score` varchar(222) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `id`(`enroll_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 AVG_ROW_LENGTH = 4096 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of enroll
-- ----------------------------
INSERT INTO `enroll` VALUES (21, '计算机学院', '3', 'admin', 0, '001', '跳远比赛', '2023-11-15 00:00:00', '2023-11-22 00:00:00', '北门操场', 'admin', '15053179864', '23');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint(51) NOT NULL AUTO_INCREMENT,
  `users_stnumber` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_name` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_value` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `id`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 AVG_ROW_LENGTH = 4096 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', 'admin', 'superAdmin');
INSERT INTO `role` VALUES (2, '201234567892', '学生', 'user');

-- ----------------------------
-- Table structure for site
-- ----------------------------
DROP TABLE IF EXISTS `site`;
CREATE TABLE `site`  (
  `site_id` bigint(51) NOT NULL AUTO_INCREMENT,
  `site_number` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competition_site` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `id`(`site_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 AVG_ROW_LENGTH = 4096 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of site
-- ----------------------------
INSERT INTO `site` VALUES (1, '001', '北门操场');
INSERT INTO `site` VALUES (2, '002', '西门公园');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(51) NOT NULL AUTO_INCREMENT,
  `users_stnumber` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_pwd` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_name` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_phone` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_sex` int(5) NULL DEFAULT NULL,
  `college_name` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_url` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sers_email` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `users_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 AVG_ROW_LENGTH = 4096 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '老王', '15053179864', 0, '计算机学院', '66197daf-e9e2-40bb-98c3-cf5900a3bf1b.jpg', NULL, NULL);
INSERT INTO `users` VALUES (2, '201234567892', 'e10adc3949ba59abbe56e057f20f883e', '学生', '15053179863', 0, '计算机学院', '默认头像.jpg', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
