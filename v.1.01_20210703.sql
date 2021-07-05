/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : wx_edu

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 06/05/2021 19:44:23 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `advert`
-- ----------------------------
DROP TABLE IF EXISTS `advert`;
CREATE TABLE `advert` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `advert_title` varchar(255) NOT NULL COMMENT '广告标题',
  `advert_img` varchar(255) NOT NULL DEFAULT '' COMMENT '广告图片',
  `advert_url` varchar(255) DEFAULT NULL COMMENT '广告链接',
  `advert_target` varchar(255) NOT NULL DEFAULT '' COMMENT '广告跳转方式',
  `advert_location` tinyint(3) NOT NULL DEFAULT '1' COMMENT '广告位置(1首页轮播)',
  `begin_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `plat_show` tinyint(3) NOT NULL DEFAULT '0' COMMENT '位置(0电脑端，1微信端，2移动网页端)',
  `advert_type` tinyint(1) DEFAULT NULL COMMENT '广告类型(1为外部链接，2为内部内容，当值为2时，将显示adv_content的内容)',
  `advert_content` text COMMENT '当adv_type为2的时候，使用adv_content的值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='广告信息';

-- ----------------------------
--  Records of `advert`
-- ----------------------------
BEGIN;
INSERT INTO `advert` VALUES ('1', '1899-12-30 01:00:00', '1899-12-30 01:00:00', '1', '1', '旺鑫网络', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/12306794407e4108a46f7a8d1db9a625.jpg', 'http://www.wangxinvip.com/', '_blank', '1', '2014-12-29 17:00:00', '2019-07-01 17:00:00', '0', null, '<p><br></p>'), ('1247087675551354882', '2020-04-06 17:04:06', '2020-04-06 17:04:06', '1', '1', '77687uiu', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/61ee121d4b2846d8a70dd37dc492a002.gif', null, '_self', '1', '2020-04-05 00:00:00', '2020-04-22 00:00:00', '2', '2', '<p>uuiiuioiou</p>'), ('1248159581935259649', '2020-04-09 16:03:28', '2020-04-09 16:03:28', '1', '1', '2342123888', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/a390c0296a0542939d7c6d5f6b249b1e.jpg', null, '_self', '1', '2020-04-07 16:00:00', '2020-04-08 16:00:00', '2', '2', '<p>2423423</p>'), ('2', '1899-12-30 02:00:00', '1899-12-30 02:00:00', '1', '1', '旺鑫学院', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/988b2bfc7866401da47ec9cc65e61e2a.jpg', 'http://www.wangxinvip.com/', '_self', '1', '2016-01-01 17:03:39', '2021-01-27 17:04:18', '0', '1', '<p><br></p>'), ('3d0640292745406686266022edc133ba', '2020-09-15 16:51:50', '2020-09-15 16:51:50', '1', '1', '33', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/07b135ccda6748638e63eb0468301949.jpg', '33', '_blank', '1', '2020-09-26 08:00:00', '2020-10-06 08:00:00', '0', '1', '<p>3232</p>');
COMMIT;

-- ----------------------------
--  Table structure for `bottom_navigation`
-- ----------------------------
DROP TABLE IF EXISTS `bottom_navigation`;
CREATE TABLE `bottom_navigation` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `parent_id` varchar(36) NOT NULL COMMENT '父ID',
  `navigation_name` varchar(50) NOT NULL COMMENT '导航名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='站点导航';

-- ----------------------------
--  Records of `bottom_navigation`
-- ----------------------------
BEGIN;
INSERT INTO `bottom_navigation` VALUES ('13439ef50eeb4c809a9da3724e2c05f9', '2020-11-03 15:10:47', '2020-11-03 15:10:47', '1', '1', '44f6518298ba414bbb027ed49457cc19', '联系方式'), ('44f6518298ba414bbb027ed49457cc19', '2020-11-03 15:09:38', '2020-11-03 15:09:38', '1', '1', '0', '联系'), ('4dc8665e190f407cbeeda25345fd2905', '2020-08-12 21:28:13', '2020-08-12 21:28:13', '1', '1', 'b3fcc63eb1314456accf2d83c415c574', '隐私协议'), ('575f72fd5f18400cae8e16e7548b17df', '2020-08-12 21:27:49', '2020-08-12 21:27:49', '1', '1', 'b3fcc63eb1314456accf2d83c415c574', '使用协议'), ('b3fcc63eb1314456accf2d83c415c574', '2020-08-12 21:27:16', '2020-08-12 21:27:16', '1', '1', '0', '用户协议');
COMMIT;

-- ----------------------------
--  Table structure for `bottom_navigation_article`
-- ----------------------------
DROP TABLE IF EXISTS `bottom_navigation_article`;
CREATE TABLE `bottom_navigation_article` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `navigation_id` varchar(36) NOT NULL COMMENT '导航ID',
  `article_title` varchar(255) NOT NULL COMMENT '文章标题',
  `article_content` text NOT NULL COMMENT '文章描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='站点导航文章';

-- ----------------------------
--  Records of `bottom_navigation_article`
-- ----------------------------
BEGIN;
INSERT INTO `bottom_navigation_article` VALUES ('2d0fab1f70fa47f4a4c7d1964ef76ec7', '2020-08-12 21:28:48', '2020-08-12 21:28:48', '1', '1', '4dc8665e190f407cbeeda25345fd2905', '个人隐私协议', '<p>个人隐私协议88899</p>'), ('2feca834eebc4a3899554dfc11a69e2f', '2020-11-03 15:11:02', '2020-11-03 15:11:02', '1', '1', '13439ef50eeb4c809a9da3724e2c05f9', '联系电话', '23823423423<p><br></p>'), ('e88c8b2f216a414884aa1a29b707031f', '2020-08-12 21:28:04', '2020-08-12 21:28:04', '1', '1', '575f72fd5f18400cae8e16e7548b17df', '旺鑫使用协议', 'ddd<p><br></p>'), ('eb5d76bfcd2c4c55a73561ad9f9be088', '2020-11-03 15:10:12', '2020-11-03 15:10:12', '1', '1', '44f6518298ba414bbb027ed49457cc19', '联系我们', '<p><br></p>');
COMMIT;

-- ----------------------------
--  Table structure for `bottom_qr_code`
-- ----------------------------
DROP TABLE IF EXISTS `bottom_qr_code`;
CREATE TABLE `bottom_qr_code` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `qr_code_name` varchar(36) NOT NULL COMMENT '二维码名称',
  `qr_code_image_url` varchar(500) NOT NULL COMMENT '二维码图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='底部二维码';

-- ----------------------------
--  Records of `bottom_qr_code`
-- ----------------------------
BEGIN;
INSERT INTO `bottom_qr_code` VALUES ('1ee6e009-9b11-4fa9-8412-046a64683d76', '2021-01-01 20:39:00', '2021-01-01 20:40:38', '1', '4', '移动网页端', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/f4f5f02c074646138f5451a7b7ebba66.png'), ('7a931c8e-f90d-4c53-95fb-dc1891d40feb', '2021-01-01 20:51:33', '2021-01-01 20:51:33', '1', '0', '小程序', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/6f5188582fd344bbaa3166fd153f8e11.png'), ('95ebac04-a995-41aa-a877-bf5372228ae5', '2021-01-01 20:51:56', '2021-01-01 20:51:56', '1', '0', '安卓APP', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/cc9e57ec42994a2199770e3d4b2bb4ca.png');
COMMIT;

-- ----------------------------
--  Table structure for `cache_log`
-- ----------------------------
DROP TABLE IF EXISTS `cache_log`;
CREATE TABLE `cache_log` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常;0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `cache_status` int(1) NOT NULL COMMENT '缓存状态(1正在缓存，2缓存完毕,3执行失败)',
  `user_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='专区';

-- ----------------------------
--  Table structure for `chapter`
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `course_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '课程ID',
  `chapter_name` varchar(64) NOT NULL COMMENT '章节名称',
  `chapter_desc` varchar(255) DEFAULT NULL COMMENT '章节描述',
  `is_free` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否免费：1免费，0收费',
  `chapter_original` decimal(11,2) DEFAULT NULL COMMENT '原价',
  `chapter_discount` decimal(11,2) DEFAULT NULL COMMENT '优惠价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='章节信息';

-- ----------------------------
--  Table structure for `chapter_apply`
-- ----------------------------
DROP TABLE IF EXISTS `chapter_apply`;
CREATE TABLE `chapter_apply` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `apply_status` int(11) DEFAULT NULL COMMENT '审核状态(0:待审核;1:审核通过;2:审核不通过)',
  `apply_result_desc` varchar(1024) DEFAULT NULL COMMENT '审核意见',
  `course_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '课程ID',
  `chapter_name` varchar(64) NOT NULL COMMENT '章节名称',
  `chapter_desc` varchar(255) DEFAULT NULL COMMENT '章节描述',
  `is_free` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否免费：1免费，0收费',
  `chapter_original` decimal(11,2) DEFAULT NULL COMMENT '原价',
  `chapter_discount` decimal(11,2) DEFAULT NULL COMMENT '优惠价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='章节信息-审核';

-- ----------------------------
--  Table structure for `coupon`
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `coupon_code` varchar(36) NOT NULL COMMENT '优惠券代码',
  `coupon_name` varchar(36) DEFAULT NULL COMMENT '优惠券名称',
  `coupon_amount` decimal(11,2) DEFAULT NULL COMMENT '优惠券金额',
  `is_limit_course` int(1) DEFAULT NULL COMMENT '使用课程限制，0不限制，1限制课程',
  `coupon_time_begin` datetime DEFAULT NULL COMMENT '有效期开始',
  `coupon_time_end` datetime DEFAULT NULL COMMENT '有效期结束',
  `coupon_assign_time_begin` datetime DEFAULT NULL COMMENT '可领取优惠券的开始时间',
  `coupon_assign_time_end` datetime DEFAULT NULL COMMENT '可领取优惠券的结束时间',
  `coupon_desc` varchar(200) DEFAULT NULL COMMENT '优惠券描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='优惠券';

-- ----------------------------
--  Table structure for `coupon_limit_course`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_limit_course`;
CREATE TABLE `coupon_limit_course` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `coupon_id` varchar(36) NOT NULL COMMENT '优惠券ID',
  `course_id` varchar(36) NOT NULL COMMENT '课程ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='优惠券限制课程';

-- ----------------------------
--  Table structure for `coupon_user`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_user`;
CREATE TABLE `coupon_user` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `coupon_id` varchar(36) NOT NULL COMMENT '优惠券ID',
  `bind_user_no` varchar(36) NOT NULL COMMENT '绑定的用户userNo',
  `is_used` int(11) DEFAULT NULL COMMENT '是否已经使用，0未使用，1已经使用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `唯一` (`coupon_id`,`bind_user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='优惠券绑定用户';

-- ----------------------------
--  Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `teacher_user_no` varchar(36) NOT NULL DEFAULT '0' COMMENT '讲师用户编码',
  `subject_id1` varchar(36) DEFAULT '0' COMMENT '一级分类ID',
  `subject_id2` varchar(36) DEFAULT '0' COMMENT '二级分类ID',
  `subject_id3` varchar(36) DEFAULT '0' COMMENT '三级分类ID',
  `course_name` varchar(255) NOT NULL DEFAULT '' COMMENT '课程名称',
  `course_logo` varchar(255) NOT NULL DEFAULT '' COMMENT '课程封面',
  `introduce_id` varchar(36) DEFAULT '0' COMMENT '课程介绍ID',
  `is_free` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否免费：1免费，0收费',
  `course_original` decimal(11,2) DEFAULT '0.00' COMMENT '原价',
  `course_discount` decimal(11,2) DEFAULT '0.00' COMMENT '优惠价',
  `is_putaway` tinyint(3) NOT NULL DEFAULT '1' COMMENT '是否上架(1:上架，0:下架)',
  `course_sort` int(11) NOT NULL DEFAULT '0' COMMENT '课程排序(前端显示使用)',
  `buy_count` int(11) NOT NULL DEFAULT '0' COMMENT '购买人数',
  `study_count` int(11) NOT NULL DEFAULT '0' COMMENT '学习人数',
  `lesson_total` int(11) NOT NULL DEFAULT '0' COMMENT '总课时数',
  `cost_time_type` tinyint(3) DEFAULT '0' COMMENT '计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)',
  `course_type` tinyint(3) DEFAULT '0' COMMENT '课程类型，0点播，1直播，2点播+直播',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课程信息';

-- ----------------------------
--  Table structure for `course_apply`
-- ----------------------------
DROP TABLE IF EXISTS `course_apply`;
CREATE TABLE `course_apply` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `apply_status` int(11) NOT NULL DEFAULT '0' COMMENT '审核状态(0:待审核,1:审核通过,2:审核不通过)',
  `apply_result_desc` varchar(1024) DEFAULT NULL COMMENT '审核意见',
  `teacher_user_no` varchar(36) NOT NULL DEFAULT '0' COMMENT '讲师用户编码',
  `subject_id1` varchar(36) DEFAULT '0' COMMENT '一级分类ID',
  `subject_id2` varchar(36) DEFAULT '0' COMMENT '二级分类ID',
  `subject_id3` varchar(36) DEFAULT '0' COMMENT '三级分类ID',
  `course_name` varchar(255) NOT NULL DEFAULT '' COMMENT '课程名称',
  `course_logo` varchar(255) NOT NULL DEFAULT '' COMMENT '课程封面',
  `introduce_id` varchar(36) DEFAULT '0' COMMENT '课程介绍ID',
  `is_free` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否免费：1免费，0收费',
  `course_original` decimal(11,2) DEFAULT '0.00' COMMENT '原价',
  `course_discount` decimal(11,2) DEFAULT '0.00' COMMENT '优惠价',
  `is_putaway` tinyint(3) NOT NULL DEFAULT '1' COMMENT '是否上架(1:上架，0:下架)',
  `cost_time_type` tinyint(3) DEFAULT '0' COMMENT '计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)',
  `course_type` tinyint(3) DEFAULT '0' COMMENT '课程类型，0点播，1直播，2点播+直播',
  `study_count` int(11) DEFAULT '0' COMMENT '学习人数',
  `buy_count` int(11) DEFAULT '0' COMMENT '初始化购买人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课程信息-审核';

-- ----------------------------
--  Table structure for `course_introduce`
-- ----------------------------
DROP TABLE IF EXISTS `course_introduce`;
CREATE TABLE `course_introduce` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `introduce` longtext NOT NULL COMMENT '课程简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课程介绍信息';

-- ----------------------------
--  Table structure for `course_introduce_apply`
-- ----------------------------
DROP TABLE IF EXISTS `course_introduce_apply`;
CREATE TABLE `course_introduce_apply` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `introduce` longtext NOT NULL COMMENT '课程简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课程介绍信息-审核';

-- ----------------------------
--  Table structure for `course_recommend`
-- ----------------------------
DROP TABLE IF EXISTS `course_recommend`;
CREATE TABLE `course_recommend` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:有效;0:无效)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `subject_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '分类ID',
  `course_id` varchar(36) NOT NULL COMMENT '课程id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='课程推荐';

-- ----------------------------
--  Table structure for `course_subject`
-- ----------------------------
DROP TABLE IF EXISTS `course_subject`;
CREATE TABLE `course_subject` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `parent_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '父分类ID',
  `subject_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '分类类型(1课程，2资源)',
  `subject_name` varchar(50) NOT NULL COMMENT '分类名称',
  `floor` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '层级',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `mobile_img` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课程分类';

-- ----------------------------
--  Table structure for `course_user`
-- ----------------------------
DROP TABLE IF EXISTS `course_user`;
CREATE TABLE `course_user` (
  `id` varchar(36) NOT NULL COMMENT 'id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `course_id` varchar(36) DEFAULT NULL COMMENT '课程ID',
  `bind_user_no` varchar(36) DEFAULT NULL COMMENT '用户userNo',
  `valid_term` datetime DEFAULT NULL COMMENT '有效期',
  `total_study_progress` int(11) DEFAULT '0' COMMENT '该课程的总学习进度(0表示0%，1表示1%，2表示2%......100表示100%)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_id和bind_user_no聚合唯一` (`course_id`,`bind_user_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `course_video`
-- ----------------------------
DROP TABLE IF EXISTS `course_video`;
CREATE TABLE `course_video` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `course_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '课程ID',
  `chapter_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '章节ID',
  `lesson_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '课时ID',
  `video_name` varchar(255) DEFAULT NULL COMMENT '视频名称',
  `video_local_number` varchar(36) DEFAULT NULL COMMENT '视频编号',
  `video_upload_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '视频状态(1待上传，2上传成功，3上传失败)',
  `video_length` varchar(36) DEFAULT NULL COMMENT '时长',
  `video_oas_url` varchar(255) DEFAULT NULL COMMENT '阿里云oas',
  PRIMARY KEY (`id`),
  UNIQUE KEY `video_local_number唯一` (`video_local_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课程视频信息';

-- ----------------------------
--  Table structure for `dot`
-- ----------------------------
DROP TABLE IF EXISTS `dot`;
CREATE TABLE `dot` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `total_dot` int(11) NOT NULL COMMENT '点券总量',
  `bind_user_no` varchar(36) NOT NULL COMMENT '绑定用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='积分';

-- ----------------------------
--  Records of `dot`
-- ----------------------------
BEGIN;
INSERT INTO `dot` VALUES ('03167404-ead1-4777-bc1b-6d9aa2da9d82', '2021-06-02 12:27:25', '2021-06-03 22:02:13', '1', '0', '20', '21060212272507456918048065577890'), ('10d029de-a77b-4950-907f-25126012ed62', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580606877776236072970522'), ('1d73f377-8cf0-44cf-906b-9ae6e133691b', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580610535888178474916611'), ('1de295c4-55e7-4551-8f7a-bf8f2153c6fc', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580609169114207096425571'), ('1e014616-ec40-4f85-a514-3b5858eb6a01', '2021-06-01 14:55:50', '2021-06-01 14:55:50', '1', '0', '0', '21060114555079990365792802161409'), ('1e0375a1-5ec9-449b-94ab-dd614542cb04', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580604312125349573293567'), ('2c699383-544a-4e79-84fe-fce5c32b2ca6', '2021-03-01 15:09:40', '2021-03-01 15:09:40', '1', '0', '0', '21030115094015658368559394751065'), ('2fd3d686-67e5-43c7-bc06-9099ac877b3e', '2021-03-01 16:24:55', '2021-03-01 16:24:55', '1', '0', '0', '21030116245566239342444732520414'), ('339ca70b-e888-4afa-9918-a15ed4514764', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580609625731840188730163'), ('37622e98-41ed-423a-b4a2-1ec360644bb6', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611455307127120225395'), ('3ab2e10d-4c68-40cb-a34e-a83645d3f89c', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580608024434172478139225'), ('44d62226-0d74-4aed-a55c-a34dfccd6c9a', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611165051122887412741'), ('47421864-2f25-4a39-9043-40ec394e9b1b', '2021-03-01 15:12:03', '2021-03-01 15:12:03', '1', '0', '0', '21030115120353228610125714038829'), ('4e3d9ce4-6c59-4bfb-b8de-7b6b34b5f7b4', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580609448204433526722815'), ('4ff0715f-c45c-4036-b30b-58823fbdc0f6', '2021-06-01 14:55:35', '2021-06-01 14:55:35', '1', '0', '0', '21060114553540338915839527239786'), ('509df1a8-132e-4c53-bccc-224c0f9f90f5', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580610283631142256884431'), ('5c9487a3-163e-453a-8be2-44c457d1d8b9', '2021-01-06 20:17:23', '2021-01-06 20:17:23', '1', '0', '0', '21010620172357866103149158622331'), ('5dc243cd-58a9-4e18-900d-6f413f4bd24c', '2020-11-04 16:19:16', '2021-06-02 10:54:24', '1', '0', '440', '2020031014031940'), ('6e6a8372-d633-40ba-910f-07fe9338c6e4', '2021-03-01 16:24:05', '2021-03-01 16:24:05', '1', '0', '0', '21030116240542536664161023844667'), ('718f6ef9-c654-40be-be28-9ece1d914eca', '2021-06-01 14:52:48', '2021-06-01 14:52:48', '1', '0', '0', '21060114524870277233411930715460'), ('75503901-7215-4c97-934d-22fcc21f65cc', '2021-03-01 14:21:25', '2021-03-01 14:21:25', '1', '0', '0', '21030114212528621808833741830086'), ('78c7dd36-f0de-4f0c-aaa7-a9064ac60bcc', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580604882976832056683317'), ('7aaf95d3-8595-49d6-8181-31acb5420535', '2021-02-14 21:47:01', '2021-02-16 12:17:36', '1', '0', '40', '2020080816133046'), ('7c67f2e1-16e1-4cd5-9413-04ca07df6140', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580606554019967213761617'), ('7ea5b166-e8f5-4cb5-8b86-0ad186ecb7cc', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580607036644282502503096'), ('88ef9d9e-3bd0-4abd-a2d9-a47596ceea9b', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580608625468308793512735'), ('9d133347-2ef2-40e1-a594-68fe326a6a4e', '2021-01-21 20:31:32', '2021-01-21 20:31:32', '1', '0', '0', '21012120313250591930860425684523'), ('a0f4f467-3da7-46bb-b8ea-5b3bfeb3322e', '2021-02-14 13:03:56', '2021-02-14 13:03:56', '1', '0', '0', '21021413035657496822054815940026'), ('a4572e6f-4375-4fec-92d7-c73da214c6cf', '2021-06-02 12:02:11', '2021-06-02 12:02:11', '1', '0', '0', '21060212021130796170590587471005'), ('a5330e69-4841-4a03-b406-5f00ee4e6c60', '2021-01-21 20:50:48', '2021-01-21 20:50:48', '1', '0', '0', '21012120504841247734538848642494'), ('a60bb3c6-05b5-4cf2-badf-712eac7593a1', '2021-06-01 15:07:33', '2021-06-03 15:14:40', '1', '0', '80', '21060115073397860541541482500672'), ('abb2aeb2-a573-47cc-9412-4b10d47d8e55', '2021-02-14 13:03:56', '2021-02-14 13:03:56', '1', '0', '0', '21021413035653004492117885964802'), ('acd31fa9-e462-49bf-a443-9b90ab601837', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611966102027138175321'), ('adf97a54-eb58-4aa4-80e9-4ed5f9aaa819', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580610973461732890090270'), ('bd5473d4-e45b-415c-bf95-fda48dbb6832', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580609846396842166118724'), ('c2c9686e-9b7d-417d-b996-34a594208723', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580607459650553264807404'), ('c3ea80d8-707f-46e9-a96a-9cf1e1700b3e', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580607777057125981229238'), ('c5d774c8-9f45-4473-adcd-933e1f5d50f2', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580604027665208314805187'), ('c687ab7b-b491-4383-973a-3ee02b701d44', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580607241730872072168221'), ('c9353b34-e965-49f8-8668-abc544c2534a', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611321877372972219440'), ('c9760405-fc3f-4471-8acf-07aa5bf91ce6', '2021-01-06 20:16:18', '2021-01-21 19:56:22', '1', '0', '40', '21010620161860200173049377103699'), ('c9da925e-fe57-49ca-9c0d-1d748f4cd811', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611771345035510437098'), ('cc5db31d-6012-4c1f-ae97-c868efc5e859', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580605887047593924495148'), ('d0503112-07e1-406c-b3a5-d31e3967d1d4', '2021-01-06 20:16:18', '2021-01-06 20:16:18', '1', '0', '0', '21010620161855121855047947146308'), ('d4be4f5b-1485-432e-afe4-bae769991415', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580610714303825155406318'), ('d5aa7591-8217-4d25-a08c-6c92ee178b83', '2021-02-16 15:52:06', '2021-02-16 15:52:06', '1', '0', '0', '21021615520669973122825754353427'), ('e976aa8e-40bc-441d-9290-062a847736f1', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580612107601803799540168'), ('ed8c8f3b-6392-413b-b4c2-2bba9350f160', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580605676162554073800947'), ('edfc3796-58c9-4dcc-a399-b50f7e897b60', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611691533811448580386'), ('ee88991b-a1e7-4ae6-b14a-778e2ea2ab43', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580612340114227701182080'), ('f6e16e28-724c-47e2-884a-dbfa10e1df04', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580608977816682130507527'), ('fa8ea5cd-2fa9-4c55-b950-4bb966a774bf', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580605150377266328025265'), ('fd4107f5-eb5e-4281-b2bf-736fde614420', '2020-10-19 12:11:14', '2021-05-20 20:56:54', '1', '0', '7099', '2018112015051635'), ('fe364293-bdb4-4e95-ba87-a6f72cfbd0a8', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580606334444009642497236'), ('fe9b9005-7173-4cf3-8538-924c9e9860c2', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580604661852042770712708'), ('ffd42603-4d7f-41fe-9611-5af988a336e1', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580610046275332113487860');
COMMIT;

-- ----------------------------
--  Table structure for `dot_config`
-- ----------------------------
DROP TABLE IF EXISTS `dot_config`;
CREATE TABLE `dot_config` (
  `id` varchar(36) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `valid_value` tinyint(1) DEFAULT NULL COMMENT '状态(1有效, 0无效)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `reg_dot` int(11) DEFAULT NULL COMMENT '初次注册获得积点',
  `login_dot` int(11) DEFAULT NULL COMMENT '每次登陆获取积点',
  `login_max_dot_per_day` int(11) DEFAULT NULL COMMENT '登陆一天获得的最大积点',
  `share_dot` int(11) DEFAULT NULL COMMENT '分享每次获得得积点',
  `share_max_dot_per_day` int(11) DEFAULT NULL COMMENT '分享链接每天获得的最大积点',
  `share_reg_dot` int(11) DEFAULT NULL COMMENT '分享后每次注册获得的积点',
  `share_reg_max_dot_per_day` int(11) DEFAULT NULL COMMENT '分享链接被注册每天获得的最大积点',
  `dot_point` int(11) DEFAULT NULL COMMENT '多少积点可以换一个积分',
  `reg_link` varchar(500) DEFAULT NULL COMMENT '推广注册链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='积点设置';

-- ----------------------------
--  Records of `dot_config`
-- ----------------------------
BEGIN;
INSERT INTO `dot_config` VALUES ('a4f5fd80-59e6-44a5-a97b-44148b56a3ce', '2020-07-29 20:05:00', '2020-10-19 19:46:26', '1', '1', '1000', '20', '100', '30', '150', '1000', '3000', '10', 'http://localhost:3000/register');
COMMIT;

-- ----------------------------
--  Table structure for `dot_operate_record`
-- ----------------------------
DROP TABLE IF EXISTS `dot_operate_record`;
CREATE TABLE `dot_operate_record` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `user_no_of_dot` varchar(36) NOT NULL COMMENT '点券所属用户的用户编号',
  `operate_type` int(3) NOT NULL COMMENT '操作类型，0增加，1减少',
  `dot_change_reson` int(3) DEFAULT '0' COMMENT '点券变化原因：0其他、1注册、2登陆、3分享、4分享后被注册、5兑换积分',
  `operate_date` varchar(20) DEFAULT NULL COMMENT '操作日期字符串，格式：2010-09-10',
  `dot` int(11) DEFAULT '0' COMMENT '点券',
  `total_dot` int(11) DEFAULT '0' COMMENT '点券总量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='点券操作记录';

-- ----------------------------
--  Records of `dot_operate_record`
-- ----------------------------
BEGIN;
INSERT INTO `dot_operate_record` VALUES ('12a8363f-25a5-4fd5-b93e-669bc4bfea9c', '2021-06-02 10:54:35', '2021-06-02 10:54:35', '1', '0', '21060115073397860541541482500672', '0', '2', '2021-06-02', '20', '20'), ('5d43e2e8-58db-427d-b534-615a445b9549', '2021-06-03 15:14:40', '2021-06-03 15:14:40', '1', '0', '21060115073397860541541482500672', '0', '2', '2021-06-03', '20', '80'), ('e0e3f072-0956-43e9-a657-196b20ba16f4', '2021-06-03 15:07:58', '2021-06-03 15:07:58', '1', '0', '21060115073397860541541482500672', '0', '2', '2021-06-03', '20', '60'), ('fba16991-033a-4c64-8198-f44b975276b5', '2021-06-03 22:02:13', '2021-06-03 22:02:13', '1', '0', '21060212272507456918048065577890', '0', '2', '2021-06-03', '20', '20'), ('fc2bc5f9-056b-40ab-ad74-64dcfb99ab25', '2021-06-02 10:56:13', '2021-06-02 10:56:13', '1', '0', '21060115073397860541541482500672', '0', '2', '2021-06-02', '20', '40');
COMMIT;

-- ----------------------------
--  Table structure for `exam_management`
-- ----------------------------
DROP TABLE IF EXISTS `exam_management`;
CREATE TABLE `exam_management` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `title` varchar(200) NOT NULL COMMENT '考试主题',
  `introduce` varchar(1000) DEFAULT NULL,
  `exam_type` int(1) DEFAULT NULL COMMENT '考试类型，1定时考试，2时段考试',
  `start_time` datetime DEFAULT NULL COMMENT '考试开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '考试结束时间',
  `is_allow_switch_window` varchar(1) DEFAULT '0' COMMENT '是否允许切换窗口(是1，否0)',
  `is_show_confirm_button` varchar(1) DEFAULT '0' COMMENT '是否显示确认按钮(是1，否0)',
  `is_allow_select` varchar(1) DEFAULT '0' COMMENT '是否允许选择(0不允许，1允许)',
  `is_allow_right_click` varchar(1) DEFAULT '0' COMMENT '是否允许右键(0不允许，1允许)',
  `is_allow_repeate_hand_in_exam_paper` varchar(1) DEFAULT '0' COMMENT '是否允许重复交卷(0不允许，1允许)',
  `exam_paper_id` varchar(36) DEFAULT NULL COMMENT '试卷ID',
  `teacher_user_no` varchar(36) NOT NULL,
  `exam_object_type` varchar(1) DEFAULT NULL COMMENT '参考对象类型，1为全体学员，2为分组(user_group_ids)，3为全体学员中拥有该课程的学员，4为分组(user_group_ids)学员中拥有该课程的学员',
  `course_id` varchar(36) DEFAULT NULL COMMENT '课程ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='考试管理';

-- ----------------------------
--  Table structure for `exam_management_student_group_relate`
-- ----------------------------
DROP TABLE IF EXISTS `exam_management_student_group_relate`;
CREATE TABLE `exam_management_student_group_relate` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `exam_management_id` varchar(36) NOT NULL COMMENT '考试管理表exam_management的ID',
  `student_group_id` varchar(36) DEFAULT NULL COMMENT 'user_group学员组表的ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `em_ug_unique` (`exam_management_id`,`student_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='考试管理与用户组关系绑定';

-- ----------------------------
--  Records of `exam_management_student_group_relate`
-- ----------------------------
BEGIN;
INSERT INTO `exam_management_student_group_relate` VALUES ('0f337bb38eb1f9e40c4fadd331ee5820', '2021-04-11 22:32:00', '2021-04-11 22:32:00', '0', '0', '7abb4ce141e148aab13d842e90fb0958', '13d3e0dca8f5e7319d54aa6b286ad42f'), ('6ca403f63c0d2676ed2e5b4ba4406953', '2021-04-11 22:32:00', '2021-04-11 22:32:00', '0', '0', '7abb4ce141e148aab13d842e90fb0958', '02b743375e107e42ce0a7eddd4683e07');
COMMIT;

-- ----------------------------
--  Table structure for `exam_management_user`
-- ----------------------------
DROP TABLE IF EXISTS `exam_management_user`;
CREATE TABLE `exam_management_user` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `exam_management_id` varchar(36) DEFAULT NULL COMMENT '考试ID',
  `exam_paper_id` varchar(36) DEFAULT NULL COMMENT '试卷ID',
  `exam_user_no` varchar(36) DEFAULT NULL COMMENT '考试用户',
  `exam_paper_answer` text COMMENT '用户考试答案',
  `is_finish_user_total_score` int(11) DEFAULT NULL COMMENT '0为未完成评分(其实不会有0的数据)，1为已经完成评分，2为等待教师评分',
  `user_total_score` varchar(36) DEFAULT NULL COMMENT '用户总分',
  PRIMARY KEY (`id`),
  UNIQUE KEY `exam_management_paper_user_no` (`exam_management_id`,`exam_paper_id`,`exam_user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户考试';

-- ----------------------------
--  Table structure for `exam_paper`
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper`;
CREATE TABLE `exam_paper` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `title` varchar(200) NOT NULL COMMENT '试卷标题',
  `subtitle` varchar(200) DEFAULT '' COMMENT '试卷副标题',
  `limit_time` int(10) DEFAULT NULL COMMENT '限时(分钟)',
  `total_score` int(10) DEFAULT NULL COMMENT '总分',
  `pass_score` int(10) NOT NULL DEFAULT '0' COMMENT '及格分数',
  `level` int(1) DEFAULT NULL COMMENT '难度',
  `author` varchar(150) DEFAULT NULL COMMENT '出卷人',
  `introduce` varchar(3000) DEFAULT NULL COMMENT '试卷简介',
  `attention_points` varchar(3000) DEFAULT NULL COMMENT '注意事项',
  `area_type` int(1) DEFAULT NULL COMMENT '出题范围(1从当前课程选题，2从当前课程、章节选题，3从当前课程、章节、课时选题，4按章节比例出题)',
  `course_id` varchar(36) DEFAULT NULL COMMENT '课程ID',
  `single_choice_count` int(8) DEFAULT NULL COMMENT '单选题数量',
  `multi_choice_count` int(8) DEFAULT NULL COMMENT '多选题数量',
  `judge_count` int(8) DEFAULT NULL COMMENT '判断题数量',
  `fill_blank_count` int(8) DEFAULT NULL COMMENT '填空题数量',
  `simple_count` int(8) DEFAULT NULL COMMENT '简答题数量',
  `single_choice_score` float(8,0) DEFAULT NULL COMMENT '单选题分数',
  `multi_choice_score` float(8,0) DEFAULT NULL COMMENT '多选题分数',
  `judge_score` float(8,0) DEFAULT NULL COMMENT '判断题分数',
  `fill_blank_score` float(8,0) DEFAULT NULL COMMENT '填空题分数',
  `simple_score` float(8,0) DEFAULT NULL COMMENT '简答题分数',
  `detail_by_chapter` varchar(3000) DEFAULT NULL COMMENT '按章节比例出题内容(json存储)',
  `teacher_user_no` varchar(36) DEFAULT NULL COMMENT '教师用户编号',
  `build_status` int(1) DEFAULT '0' COMMENT '试卷生成状态(0未生成，1正在生成，2已经生成，3生成失败)',
  `build_error` varchar(1000) DEFAULT NULL COMMENT '生成试卷失败原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='试题课程关系绑定';

-- ----------------------------
--  Table structure for `exam_paper_content`
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_content`;
CREATE TABLE `exam_paper_content` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `exam_paper_id` varchar(36) NOT NULL COMMENT 'exam_paper表的ID',
  `exam_paper_content` text COMMENT '试卷内容',
  `teacher_user_no` varchar(36) DEFAULT NULL COMMENT '教师用户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='试题课程关系绑定';

-- ----------------------------
--  Records of `exam_paper_content`
-- ----------------------------
BEGIN;
INSERT INTO `exam_paper_content` VALUES ('08ba1a357f1243afac7cb223570d8fc5', '2020-12-10 19:49:37', '2020-12-10 19:49:37', '0', '0', '7abec68a868c81241da65e9718b1f07e', '[{\"analysis\":\"一个苹果加一个苹果等于两个苹果\",\"answer\":\"C\",\"createTime\":1606146515659,\"customLabel\":\"个位数计算\",\"id\":\"2533ea4587a84ac4ab1e24f3534abaae\",\"itemA\":\"等于1\",\"itemB\":\"等于2\",\"itemC\":\"等于3\",\"itemD\":\"等于4\",\"level\":\"1\",\"modifiedTime\":1606146515659,\"questionTitle\":\"1加2等于多少\",\"questionType\":\"1\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"一个苹果加一个苹果等于两个苹果\",\"answer\":\"B\",\"createTime\":1606146515650,\"customLabel\":\"个位数计算\",\"id\":\"521d95ff6a764116a48b9cc8bb0925d5\",\"itemA\":\"等于1\",\"itemB\":\"等于2\",\"itemC\":\"等于3\",\"itemD\":\"等于4\",\"level\":\"1\",\"modifiedTime\":1606146515650,\"questionTitle\":\"1加1等于多少\",\"questionType\":\"1\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"3可以1加2或者2加1\",\"answer\":\"AC\",\"createTime\":1606208652670,\"customLabel\":\"个位数计算\",\"id\":\"d5711b35bd034aafb3b4fa06b40cac6f\",\"itemA\":\"1加2\",\"itemB\":\"3加1\",\"itemC\":\"2加1\",\"itemD\":\"3加2\",\"level\":\"2\",\"modifiedTime\":1606208652670,\"questionTitle\":\"几加几等于3？\",\"questionType\":\"2\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"3可以1加2或者2加1\",\"answer\":\"B\",\"createTime\":1606208652717,\"customLabel\":\"个位数计算\",\"id\":\"651a8af56e1a48a48c58c2dc0d79f801\",\"itemA\":\"1加2\",\"itemB\":\"3加1\",\"itemC\":\"2加1\",\"itemD\":\"3加2\",\"level\":\"2\",\"modifiedTime\":1606208652717,\"questionTitle\":\"几加几等于4？\",\"questionType\":\"2\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1加2等于3\",\"answer\":\"1\",\"createTime\":1606208758028,\"id\":\"ef6a56f834f749618e53c9eb1e9f7760\",\"level\":\"2\",\"modifiedTime\":1606208758028,\"questionTitle\":\"1加2等于3\",\"questionType\":\"3\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1加3等于4\",\"answer\":\"0\",\"createTime\":1606208758039,\"id\":\"770a9bbf8a7a4f4a9f72be84d0408079\",\"level\":\"2\",\"modifiedTime\":1606208758039,\"questionTitle\":\"1加3等于3\",\"questionType\":\"3\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"6对应六\",\"createTime\":1606208857206,\"id\":\"397ab3d574f44b6ba9528802664a015c\",\"itemA\":\"六\",\"level\":\"2\",\"modifiedTime\":1606208857206,\"questionTitle\":\"6的中文写法是(_)\",\"questionType\":\"4\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1对应一，2对应二，3对应三,4对应四，5对应五\",\"createTime\":1606208857000,\"id\":\"d157b1df43f0426da69f9d92962c05fb\",\"itemA\":\"一\",\"itemB\":\"二\",\"itemC\":\"三\",\"itemD\":\"四\",\"itemE\":\"五\",\"level\":\"2\",\"modifiedTime\":1606631487658,\"questionTitle\":\"sdfsjkldfjksdfjkwejklfewkjlfkjlwefjkwejkfjwefjkwejkf随手打开方式可了解对方就哭了多少风景看了电视剧看风景看水电费是否离开家圣诞节快乐风刀霜剑看风景看路上的风景克里斯朵夫就开始搭建开放时间快点放假快乐圣诞节看风景看了四大皆空方老师讲课了对方就哭了圣诞节分开了斯捷克洛夫进口零食放进空间看论文就哭了sdkjlfsjkfjklwjkljklwfjklwejklfwejklfjklwjklfwejklfjkewfjkwjklefjwejfwjeklfjklwejkfwsdfjksfsjkfjklsjkldfsjkldfjklsd水电费圣诞节快乐放圣诞节快乐分<img src=\\\"https://atest888.oss-cn-hangzhou.aliyuncs.com/course/14f755eee7634096966516f1302b8687.jpg\\\">1的中文写法是(_),2的中文写法是(_),3的中文写法是(_),4的中文写法是(_),5的中文写法是(_)<p><br>\",\"questionType\":\"4\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"<p>水电费为\",\"createTime\":1606625249000,\"id\":\"14f5be35f97d49a09599753eda722901\",\"itemA\":\"<p>略\",\"itemC\":\"1\",\"level\":\"3\",\"modifiedTime\":1607600951228,\"questionTitle\":\"<p>该题库最好增加哪些题目？\",\"questionType\":\"5\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"<p>略\",\"createTime\":1606625210000,\"id\":\"903e370528b8490a9a69786ca73a70e8\",\"itemA\":\"当前还有很多好人好事，例如\",\"itemC\":\"1\",\"level\":\"3\",\"modifiedTime\":1607594467929,\"questionTitle\":\"<p>你认为当前社会有什么好事？请列出来\",\"questionType\":\"5\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1}]', '2018112015051635'), ('521d87c3814c4bc3a853fb4fbab2bbcc', '2021-01-19 20:32:05', '2021-01-19 20:32:05', '0', '0', '581d85fcbf0f565a29801c63fcb2fd24', '[{\"analysis\":\"一个苹果加一个苹果等于两个苹果\",\"answer\":\"C\",\"createTime\":1606146515000,\"customLabel\":\"个位数计算\",\"id\":\"2533ea4587a84ac4ab1e24f3534abaae\",\"itemA\":\"等于1\",\"itemB\":\"等于2\",\"itemC\":\"等于3\",\"itemD\":\"等于4\",\"level\":\"1\",\"modifiedTime\":1606146515000,\"questionTitle\":\"1加2等于多少\",\"questionType\":\"1\",\"score\":\"30\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"一个苹果加一个苹果等于两个苹果\",\"answer\":\"B\",\"createTime\":1606146515000,\"customLabel\":\"个位数计算\",\"id\":\"521d95ff6a764116a48b9cc8bb0925d5\",\"itemA\":\"等于1\",\"itemB\":\"等于2\",\"itemC\":\"等于3\",\"itemD\":\"等于4\",\"level\":\"1\",\"modifiedTime\":1606146515000,\"questionTitle\":\"1加1等于多少\",\"questionType\":\"1\",\"score\":\"30\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"3可以1加2或者2加1\",\"answer\":\"D\",\"createTime\":1606208652000,\"customLabel\":\"个位数计算\",\"id\":\"54d6c576e67c4af587a4a9a305e4e9c2\",\"itemA\":\"1加2\",\"itemB\":\"3加1\",\"itemC\":\"2加1\",\"itemD\":\"3加2\",\"level\":\"2\",\"modifiedTime\":1606208652000,\"questionTitle\":\"几加几等于5？\",\"questionType\":\"2\",\"score\":\"5\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"3可以1加2或者2加1\",\"answer\":\"B\",\"createTime\":1606208652000,\"customLabel\":\"个位数计算\",\"id\":\"651a8af56e1a48a48c58c2dc0d79f801\",\"itemA\":\"1加2\",\"itemB\":\"3加1\",\"itemC\":\"2加1\",\"itemD\":\"3加2\",\"level\":\"2\",\"modifiedTime\":1606208652000,\"questionTitle\":\"几加几等于4？\",\"questionType\":\"2\",\"score\":\"5\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1加3等于4\",\"answer\":\"0\",\"createTime\":1606208758000,\"id\":\"770a9bbf8a7a4f4a9f72be84d0408079\",\"itemC\":\"1\",\"level\":\"2\",\"modifiedTime\":1606208758000,\"questionTitle\":\"1加3等于3\",\"questionType\":\"3\",\"score\":\"5\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1加2等于3\",\"answer\":\"1\",\"createTime\":1606208758000,\"id\":\"ef6a56f834f749618e53c9eb1e9f7760\",\"itemC\":\"1\",\"level\":\"2\",\"modifiedTime\":1606208758000,\"questionTitle\":\"1加2等于3\",\"questionType\":\"3\",\"score\":\"5\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1对应一，2对应二，3对应三,4对应四，5对应五\",\"createTime\":1606208857000,\"id\":\"d157b1df43f0426da69f9d92962c05fb\",\"itemA\":\"一\",\"itemB\":\"二\",\"itemC\":\"三\",\"itemD\":\"四\",\"itemE\":\"五\",\"level\":\"2\",\"modifiedTime\":1606631487000,\"questionTitle\":\"sdfsjkldfjksdfjkwejklfewkjlfkjlwefjkwejkfjwefjkwejkf随手打开方式可了解对方就哭了多少风景看了电视剧看风景看水电费是否离开家圣诞节快乐风刀霜剑看风景看路上的风景克里斯朵夫就开始搭建开放时间快点放假快乐圣诞节看风景看了四大皆空方老师讲课了对方就哭了圣诞节分开了斯捷克洛夫进口零食放进空间看论文就哭了sdkjlfsjkfjklwjkljklwfjklwejklfwejklfjklwjklfwejklfjkewfjkwjklefjwejfwjeklfjklwejkfwsdfjksfsjkfjklsjkldfsjkldfjklsd水电费圣诞节快乐放圣诞节快乐分<img src=\\\"https://atest888.oss-cn-hangzhou.aliyuncs.com/course/14f755eee7634096966516f1302b8687.jpg\\\">1的中文写法是(_),2的中文写法是(_),3的中文写法是(_),4的中文写法是(_),5的中文写法是(_)<p><br>\",\"questionType\":\"4\",\"score\":\"5\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"6对应六\",\"createTime\":1606208857000,\"id\":\"397ab3d574f44b6ba9528802664a015c\",\"itemA\":\"六\",\"level\":\"2\",\"modifiedTime\":1606208857000,\"questionTitle\":\"6的中文写法是(_)\",\"questionType\":\"4\",\"score\":\"5\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"<p>水电费为\",\"createTime\":1606625249000,\"id\":\"14f5be35f97d49a09599753eda722901\",\"itemA\":\"<p>略\",\"itemC\":\"1\",\"level\":\"3\",\"modifiedTime\":1607600951000,\"questionTitle\":\"<p>该题库最好增加哪些题目？\",\"questionType\":\"5\",\"score\":\"5\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"<p>略\",\"createTime\":1606625210000,\"id\":\"903e370528b8490a9a69786ca73a70e8\",\"itemA\":\"当前还有很多好人好事，例如\",\"itemC\":\"1\",\"level\":\"3\",\"modifiedTime\":1607594467000,\"questionTitle\":\"<p>你认为当前社会有什么好事？请列出来\",\"questionType\":\"5\",\"score\":\"5\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1}]', '2018112015051635'), ('764e6631f1fe4348ae59a99e7f770315', '2021-01-19 23:16:33', '2021-01-19 23:16:33', '0', '0', '3c5a38e902cf84f8f49315084d9b5d92', '[{\"analysis\":\"一个苹果加一个苹果等于两个苹果\",\"answer\":\"C\",\"createTime\":1606146515000,\"customLabel\":\"个位数计算\",\"id\":\"2533ea4587a84ac4ab1e24f3534abaae\",\"itemA\":\"等于1\",\"itemB\":\"等于2\",\"itemC\":\"等于3\",\"itemD\":\"等于4\",\"level\":\"1\",\"modifiedTime\":1606146515000,\"questionTitle\":\"1加2等于多少\",\"questionType\":\"1\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"一个苹果加一个苹果等于两个苹果\",\"answer\":\"B\",\"createTime\":1606146515000,\"customLabel\":\"个位数计算\",\"id\":\"521d95ff6a764116a48b9cc8bb0925d5\",\"itemA\":\"等于1\",\"itemB\":\"等于2\",\"itemC\":\"等于3\",\"itemD\":\"等于4\",\"level\":\"1\",\"modifiedTime\":1606146515000,\"questionTitle\":\"1加1等于多少\",\"questionType\":\"1\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"3可以1加2或者2加1\",\"answer\":\"B\",\"createTime\":1606208652000,\"customLabel\":\"个位数计算\",\"id\":\"651a8af56e1a48a48c58c2dc0d79f801\",\"itemA\":\"1加2\",\"itemB\":\"3加1\",\"itemC\":\"2加1\",\"itemD\":\"3加2\",\"level\":\"2\",\"modifiedTime\":1606208652000,\"questionTitle\":\"几加几等于4？\",\"questionType\":\"2\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"3可以1加2或者2加1\",\"answer\":\"AC\",\"createTime\":1606208652000,\"customLabel\":\"个位数计算\",\"id\":\"d5711b35bd034aafb3b4fa06b40cac6f\",\"itemA\":\"1加2\",\"itemB\":\"3加1\",\"itemC\":\"2加1\",\"itemD\":\"3加2\",\"level\":\"2\",\"modifiedTime\":1606208652000,\"questionTitle\":\"几加几等于3？\",\"questionType\":\"2\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1加3等于4\",\"answer\":\"0\",\"createTime\":1606208758000,\"id\":\"770a9bbf8a7a4f4a9f72be84d0408079\",\"itemC\":\"1\",\"level\":\"2\",\"modifiedTime\":1606208758000,\"questionTitle\":\"1加3等于3\",\"questionType\":\"3\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1加1应该等于2\",\"answer\":\"0\",\"createTime\":1606208757000,\"id\":\"ec1d5608fd584d9ea879d4a1d9d5f0a8\",\"itemC\":\"1\",\"level\":\"2\",\"modifiedTime\":1606208757000,\"questionTitle\":\"1加1等于3\",\"questionType\":\"3\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1对应一，2对应二，3对应三,4对应四，5对应五\",\"createTime\":1606208857000,\"id\":\"d157b1df43f0426da69f9d92962c05fb\",\"itemA\":\"一\",\"itemB\":\"二\",\"itemC\":\"三\",\"itemD\":\"四\",\"itemE\":\"五\",\"level\":\"2\",\"modifiedTime\":1606631487000,\"questionTitle\":\"sdfsjkldfjksdfjkwejklfewkjlfkjlwefjkwejkfjwefjkwejkf随手打开方式可了解对方就哭了多少风景看了电视剧看风景看水电费是否离开家圣诞节快乐风刀霜剑看风景看路上的风景克里斯朵夫就开始搭建开放时间快点放假快乐圣诞节看风景看了四大皆空方老师讲课了对方就哭了圣诞节分开了斯捷克洛夫进口零食放进空间看论文就哭了sdkjlfsjkfjklwjkljklwfjklwejklfwejklfjklwjklfwejklfjkewfjkwjklefjwejfwjeklfjklwejkfwsdfjksfsjkfjklsjkldfsjkldfjklsd水电费圣诞节快乐放圣诞节快乐分<img src=\\\"https://atest888.oss-cn-hangzhou.aliyuncs.com/course/14f755eee7634096966516f1302b8687.jpg\\\">1的中文写法是(_),2的中文写法是(_),3的中文写法是(_),4的中文写法是(_),5的中文写法是(_)<p><br>\",\"questionType\":\"4\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"6对应六\",\"createTime\":1606208857000,\"id\":\"397ab3d574f44b6ba9528802664a015c\",\"itemA\":\"六\",\"level\":\"2\",\"modifiedTime\":1606208857000,\"questionTitle\":\"6的中文写法是(_)\",\"questionType\":\"4\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"<p>水电费为\",\"createTime\":1606625249000,\"id\":\"14f5be35f97d49a09599753eda722901\",\"itemA\":\"<p>略\",\"itemC\":\"1\",\"level\":\"3\",\"modifiedTime\":1607600951000,\"questionTitle\":\"<p>该题库最好增加哪些题目？\",\"questionType\":\"5\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"<p>略\",\"createTime\":1606625210000,\"id\":\"903e370528b8490a9a69786ca73a70e8\",\"itemA\":\"当前还有很多好人好事，例如\",\"itemC\":\"1\",\"level\":\"3\",\"modifiedTime\":1607594467000,\"questionTitle\":\"<p>你认为当前社会有什么好事？请列出来\",\"questionType\":\"5\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1}]', '2018112015051635'), ('76c84959760b4b608c9ff171c074cc4a', '2021-01-23 22:35:22', '2021-01-23 22:35:22', '0', '0', 'f8511c48eb907dbc2c19b8f6caa1cdd1', '[{\"analysis\":\"一个苹果加一个苹果等于两个苹果\",\"answer\":\"C\",\"createTime\":1606146515000,\"customLabel\":\"个位数计算\",\"id\":\"2533ea4587a84ac4ab1e24f3534abaae\",\"itemA\":\"等于1\",\"itemB\":\"等于2\",\"itemC\":\"等于3\",\"itemD\":\"等于4\",\"level\":\"1\",\"modifiedTime\":1606146515000,\"questionTitle\":\"1加2等于多少\",\"questionType\":\"1\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"一个苹果加一个苹果等于两个苹果\",\"answer\":\"B\",\"createTime\":1606146515000,\"customLabel\":\"个位数计算\",\"id\":\"521d95ff6a764116a48b9cc8bb0925d5\",\"itemA\":\"等于1\",\"itemB\":\"等于2\",\"itemC\":\"等于3\",\"itemD\":\"等于4\",\"level\":\"1\",\"modifiedTime\":1606146515000,\"questionTitle\":\"1加1等于多少\",\"questionType\":\"1\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"3可以1加2或者2加1\",\"answer\":\"B\",\"createTime\":1606208652000,\"customLabel\":\"个位数计算\",\"id\":\"651a8af56e1a48a48c58c2dc0d79f801\",\"itemA\":\"1加2\",\"itemB\":\"3加1\",\"itemC\":\"2加1\",\"itemD\":\"3加2\",\"level\":\"2\",\"modifiedTime\":1606208652000,\"questionTitle\":\"几加几等于4？\",\"questionType\":\"2\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"3可以1加2或者2加1\",\"answer\":\"AC\",\"createTime\":1606208652000,\"customLabel\":\"个位数计算\",\"id\":\"d5711b35bd034aafb3b4fa06b40cac6f\",\"itemA\":\"1加2\",\"itemB\":\"3加1\",\"itemC\":\"2加1\",\"itemD\":\"3加2\",\"level\":\"2\",\"modifiedTime\":1606208652000,\"questionTitle\":\"几加几等于3？\",\"questionType\":\"2\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1加1应该等于2\",\"answer\":\"0\",\"createTime\":1606208757000,\"id\":\"ec1d5608fd584d9ea879d4a1d9d5f0a8\",\"itemC\":\"1\",\"level\":\"2\",\"modifiedTime\":1606208757000,\"questionTitle\":\"1加1等于3\",\"questionType\":\"3\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1加3等于4\",\"answer\":\"0\",\"createTime\":1606208758000,\"id\":\"770a9bbf8a7a4f4a9f72be84d0408079\",\"itemC\":\"1\",\"level\":\"2\",\"modifiedTime\":1606208758000,\"questionTitle\":\"1加3等于3\",\"questionType\":\"3\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1对应一，2对应二，3对应三,4对应四，5对应五\",\"createTime\":1606208857000,\"id\":\"d157b1df43f0426da69f9d92962c05fb\",\"itemA\":\"一\",\"itemB\":\"二\",\"itemC\":\"三\",\"itemD\":\"四\",\"itemE\":\"五\",\"level\":\"2\",\"modifiedTime\":1606631487000,\"questionTitle\":\"sdfsjkldfjksdfjkwejklfewkjlfkjlwefjkwejkfjwefjkwejkf随手打开方式可了解对方就哭了多少风景看了电视剧看风景看水电费是否离开家圣诞节快乐风刀霜剑看风景看路上的风景克里斯朵夫就开始搭建开放时间快点放假快乐圣诞节看风景看了四大皆空方老师讲课了对方就哭了圣诞节分开了斯捷克洛夫进口零食放进空间看论文就哭了sdkjlfsjkfjklwjkljklwfjklwejklfwejklfjklwjklfwejklfjkewfjkwjklefjwejfwjeklfjklwejkfwsdfjksfsjkfjklsjkldfsjkldfjklsd水电费圣诞节快乐放圣诞节快乐分<img src=\\\"https://atest888.oss-cn-hangzhou.aliyuncs.com/course/14f755eee7634096966516f1302b8687.jpg\\\">1的中文写法是(_),2的中文写法是(_),3的中文写法是(_),4的中文写法是(_),5的中文写法是(_)<p><br>\",\"questionType\":\"4\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"6对应六\",\"createTime\":1606208857000,\"id\":\"397ab3d574f44b6ba9528802664a015c\",\"itemA\":\"六\",\"level\":\"2\",\"modifiedTime\":1606208857000,\"questionTitle\":\"6的中文写法是(_)\",\"questionType\":\"4\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"<p>水电费为\",\"createTime\":1606625249000,\"id\":\"14f5be35f97d49a09599753eda722901\",\"itemA\":\"<p>略\",\"itemC\":\"1\",\"level\":\"3\",\"modifiedTime\":1607600951000,\"questionTitle\":\"<p>该题库最好增加哪些题目？\",\"questionType\":\"5\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"<p>略\",\"createTime\":1606625210000,\"id\":\"903e370528b8490a9a69786ca73a70e8\",\"itemA\":\"当前还有很多好人好事，例如\",\"itemC\":\"1\",\"level\":\"3\",\"modifiedTime\":1607594467000,\"questionTitle\":\"<p>你认为当前社会有什么好事？请列出来\",\"questionType\":\"5\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1}]', '2018112015051635'), ('7a3e519d0526469da0b644421fef2fad', '2021-01-22 20:29:07', '2021-01-22 20:29:07', '0', '0', '76164ec2eb977947df774f7098f25bfe', '[{\"analysis\":\"一个苹果加一个苹果等于两个苹果\",\"answer\":\"C\",\"createTime\":1606146515000,\"customLabel\":\"个位数计算\",\"id\":\"2533ea4587a84ac4ab1e24f3534abaae\",\"itemA\":\"等于1\",\"itemB\":\"等于2\",\"itemC\":\"等于3\",\"itemD\":\"等于4\",\"level\":\"1\",\"modifiedTime\":1606146515000,\"questionTitle\":\"1加2等于多少\",\"questionType\":\"1\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"一个苹果加一个苹果等于两个苹果\",\"answer\":\"B\",\"createTime\":1606146515000,\"customLabel\":\"个位数计算\",\"id\":\"521d95ff6a764116a48b9cc8bb0925d5\",\"itemA\":\"等于1\",\"itemB\":\"等于2\",\"itemC\":\"等于3\",\"itemD\":\"等于4\",\"level\":\"1\",\"modifiedTime\":1606146515000,\"questionTitle\":\"1加1等于多少\",\"questionType\":\"1\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"3可以1加2或者2加1\",\"answer\":\"D\",\"createTime\":1606208652000,\"customLabel\":\"个位数计算\",\"id\":\"54d6c576e67c4af587a4a9a305e4e9c2\",\"itemA\":\"1加2\",\"itemB\":\"3加1\",\"itemC\":\"2加1\",\"itemD\":\"3加2\",\"level\":\"2\",\"modifiedTime\":1606208652000,\"questionTitle\":\"几加几等于5？\",\"questionType\":\"2\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"3可以1加2或者2加1\",\"answer\":\"AC\",\"createTime\":1606208652000,\"customLabel\":\"个位数计算\",\"id\":\"d5711b35bd034aafb3b4fa06b40cac6f\",\"itemA\":\"1加2\",\"itemB\":\"3加1\",\"itemC\":\"2加1\",\"itemD\":\"3加2\",\"level\":\"2\",\"modifiedTime\":1606208652000,\"questionTitle\":\"几加几等于3？\",\"questionType\":\"2\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1加1应该等于2\",\"answer\":\"0\",\"createTime\":1606208757000,\"id\":\"ec1d5608fd584d9ea879d4a1d9d5f0a8\",\"itemC\":\"1\",\"level\":\"2\",\"modifiedTime\":1606208757000,\"questionTitle\":\"1加1等于3\",\"questionType\":\"3\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1加3等于4\",\"answer\":\"0\",\"createTime\":1606208758000,\"id\":\"770a9bbf8a7a4f4a9f72be84d0408079\",\"itemC\":\"1\",\"level\":\"2\",\"modifiedTime\":1606208758000,\"questionTitle\":\"1加3等于3\",\"questionType\":\"3\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"1对应一，2对应二，3对应三,4对应四，5对应五\",\"createTime\":1606208857000,\"id\":\"d157b1df43f0426da69f9d92962c05fb\",\"itemA\":\"一\",\"itemB\":\"二\",\"itemC\":\"三\",\"itemD\":\"四\",\"itemE\":\"五\",\"level\":\"2\",\"modifiedTime\":1606631487000,\"questionTitle\":\"sdfsjkldfjksdfjkwejklfewkjlfkjlwefjkwejkfjwefjkwejkf随手打开方式可了解对方就哭了多少风景看了电视剧看风景看水电费是否离开家圣诞节快乐风刀霜剑看风景看路上的风景克里斯朵夫就开始搭建开放时间快点放假快乐圣诞节看风景看了四大皆空方老师讲课了对方就哭了圣诞节分开了斯捷克洛夫进口零食放进空间看论文就哭了sdkjlfsjkfjklwjkljklwfjklwejklfwejklfjklwjklfwejklfjkewfjkwjklefjwejfwjeklfjklwejkfwsdfjksfsjkfjklsjkldfsjkldfjklsd水电费圣诞节快乐放圣诞节快乐分<img src=\\\"https://atest888.oss-cn-hangzhou.aliyuncs.com/course/14f755eee7634096966516f1302b8687.jpg\\\">1的中文写法是(_),2的中文写法是(_),3的中文写法是(_),4的中文写法是(_),5的中文写法是(_)<p><br>\",\"questionType\":\"4\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"6对应六\",\"createTime\":1606208857000,\"id\":\"397ab3d574f44b6ba9528802664a015c\",\"itemA\":\"六\",\"level\":\"2\",\"modifiedTime\":1606208857000,\"questionTitle\":\"6的中文写法是(_)\",\"questionType\":\"4\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"<p>水电费为\",\"createTime\":1606625249000,\"id\":\"14f5be35f97d49a09599753eda722901\",\"itemA\":\"<p>略\",\"itemC\":\"1\",\"level\":\"3\",\"modifiedTime\":1607600951000,\"questionTitle\":\"<p>该题库最好增加哪些题目？\",\"questionType\":\"5\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1},{\"analysis\":\"<p>略\",\"createTime\":1606625210000,\"id\":\"903e370528b8490a9a69786ca73a70e8\",\"itemA\":\"当前还有很多好人好事，例如\",\"itemC\":\"1\",\"level\":\"3\",\"modifiedTime\":1607594467000,\"questionTitle\":\"<p>你认为当前社会有什么好事？请列出来\",\"questionType\":\"5\",\"score\":\"10\",\"sort\":0,\"subjectId1\":\"1080744766018883585\",\"subjectId2\":\"1080752583811469314\",\"userNo\":\"2018112015051635\",\"validValue\":1}]', '2018112015051635');
COMMIT;

-- ----------------------------
--  Table structure for `exam_question`
-- ----------------------------
DROP TABLE IF EXISTS `exam_question`;
CREATE TABLE `exam_question` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `question_title` text NOT NULL COMMENT '题干',
  `question_type` varchar(300) DEFAULT '' COMMENT '题目类型(1单选题，2多选题，3判断题，4填空题，5简单题)',
  `item_a` varchar(1000) DEFAULT NULL COMMENT 'A选项',
  `item_b` varchar(1000) DEFAULT '' COMMENT 'B选项',
  `item_c` varchar(1000) DEFAULT '1' COMMENT 'C选项',
  `item_d` varchar(1000) DEFAULT NULL COMMENT 'D选项',
  `item_e` varchar(1000) DEFAULT NULL COMMENT 'E选项',
  `item_f` varchar(1000) DEFAULT '' COMMENT 'F选项',
  `item_g` varchar(1000) DEFAULT NULL COMMENT 'G选项',
  `item_h` varchar(1000) DEFAULT NULL COMMENT 'H选项',
  `answer` varchar(1000) DEFAULT NULL COMMENT '答案。单选、多选、判断(1正确，0错误)、(填空答案在item_a、item_b...item_h)、(简答题答案在item_a)',
  `analysis` text COMMENT '解析',
  `user_no` varchar(36) DEFAULT NULL COMMENT 'user表对应的user_no',
  `level` varchar(1) DEFAULT NULL COMMENT '难度，1到5，1最容易，5最难',
  `subject_id1` varchar(36) DEFAULT NULL,
  `subject_id2` varchar(36) DEFAULT NULL,
  `subject_id3` varchar(36) DEFAULT NULL,
  `custom_label` varchar(200) DEFAULT NULL COMMENT '自定义标签，用于方便搜索',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='试题表';

-- ----------------------------
--  Records of `exam_question`
-- ----------------------------
BEGIN;
INSERT INTO `exam_question` VALUES ('14f5be35f97d49a09599753eda722901', '2020-11-29 12:47:29', '2020-12-10 19:49:11', '1', '0', '<p>该题库最好增加哪些题目？', '5', '<p>略', '', '1', null, null, '', null, null, null, '<p>水电费为', '2018112015051635', '3', '1080744766018883585', '1080752583811469314', null, null), ('2533ea4587a84ac4ab1e24f3534abaae', '2020-11-23 23:48:35', '2020-11-23 23:48:35', '1', '0', '1加2等于多少', '1', '等于1', '等于2', '等于3', '等于4', '', '', '', '', 'C', '一个苹果加一个苹果等于两个苹果', '2018112015051635', '1', '1080744766018883585', '1080752583811469314', null, '个位数计算'), ('397ab3d574f44b6ba9528802664a015c', '2020-11-24 17:07:37', '2020-11-24 17:07:37', '1', '0', '6的中文写法是(_)', '4', '六', '', '', '', '', '', '', '', null, '6对应六', '2018112015051635', '2', '1080744766018883585', '1080752583811469314', null, ''), ('521d95ff6a764116a48b9cc8bb0925d5', '2020-11-23 23:48:35', '2020-11-23 23:48:35', '1', '0', '1加1等于多少', '1', '等于1', '等于2', '等于3', '等于4', '', '', '', '', 'B', '一个苹果加一个苹果等于两个苹果', '2018112015051635', '1', '1080744766018883585', '1080752583811469314', null, '个位数计算'), ('54d6c576e67c4af587a4a9a305e4e9c2', '2020-11-24 17:04:12', '2020-11-24 17:04:12', '1', '0', '几加几等于5？', '2', '1加2', '3加1', '2加1', '3加2', '', '', '', '', 'D', '3可以1加2或者2加1', '2018112015051635', '2', '1080744766018883585', '1080752583811469314', null, '个位数计算'), ('651a8af56e1a48a48c58c2dc0d79f801', '2020-11-24 17:04:12', '2020-11-24 17:04:12', '1', '0', '几加几等于4？', '2', '1加2', '3加1', '2加1', '3加2', '', '', '', '', 'B', '3可以1加2或者2加1', '2018112015051635', '2', '1080744766018883585', '1080752583811469314', null, '个位数计算'), ('770a9bbf8a7a4f4a9f72be84d0408079', '2020-11-24 17:05:58', '2020-11-24 17:05:58', '1', '0', '1加3等于3', '3', null, '', '1', null, null, '', null, null, '0', '1加3等于4', '2018112015051635', '2', '1080744766018883585', '1080752583811469314', null, ''), ('903e370528b8490a9a69786ca73a70e8', '2020-11-29 12:46:50', '2020-12-10 18:01:07', '1', '0', '<p>你认为当前社会有什么好事？请列出来', '5', '当前还有很多好人好事，例如', '', '1', null, null, '', null, null, null, '<p>略', '2018112015051635', '3', '1080744766018883585', '1080752583811469314', null, null), ('d157b1df43f0426da69f9d92962c05fb', '2020-11-24 17:07:37', '2020-11-29 14:31:27', '1', '0', 'sdfsjkldfjksdfjkwejklfewkjlfkjlwefjkwejkfjwefjkwejkf随手打开方式可了解对方就哭了多少风景看了电视剧看风景看水电费是否离开家圣诞节快乐风刀霜剑看风景看路上的风景克里斯朵夫就开始搭建开放时间快点放假快乐圣诞节看风景看了四大皆空方老师讲课了对方就哭了圣诞节分开了斯捷克洛夫进口零食放进空间看论文就哭了sdkjlfsjkfjklwjkljklwfjklwejklfwejklfjklwjklfwejklfjkewfjkwjklefjwejfwjeklfjklwejkfwsdfjksfsjkfjklsjkldfsjkldfjklsd水电费圣诞节快乐放圣诞节快乐分<img src=\"https://atest888.oss-cn-hangzhou.aliyuncs.com/course/14f755eee7634096966516f1302b8687.jpg\">1的中文写法是(_),2的中文写法是(_),3的中文写法是(_),4的中文写法是(_),5的中文写法是(_)<p><br>', '4', '一', '二', '三', '四', '五', '', '', '', null, '1对应一，2对应二，3对应三,4对应四，5对应五', '2018112015051635', '2', '1080744766018883585', '1080752583811469314', null, ''), ('d5711b35bd034aafb3b4fa06b40cac6f', '2020-11-24 17:04:12', '2020-11-24 17:04:12', '1', '0', '几加几等于3？', '2', '1加2', '3加1', '2加1', '3加2', '', '', '', '', 'AC', '3可以1加2或者2加1', '2018112015051635', '2', '1080744766018883585', '1080752583811469314', null, '个位数计算'), ('ec1d5608fd584d9ea879d4a1d9d5f0a8', '2020-11-24 17:05:57', '2020-11-24 17:05:57', '1', '0', '1加1等于3', '3', null, '', '1', null, null, '', null, null, '0', '1加1应该等于2', '2018112015051635', '2', '1080744766018883585', '1080752583811469314', null, ''), ('ef6a56f834f749618e53c9eb1e9f7760', '2020-11-24 17:05:58', '2020-11-24 17:05:58', '1', '0', '1加2等于3', '3', null, '', '1', null, null, '', null, null, '1', '1加2等于3', '2018112015051635', '2', '1080744766018883585', '1080752583811469314', null, '');
COMMIT;

-- ----------------------------
--  Table structure for `exam_question_course`
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_course`;
CREATE TABLE `exam_question_course` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `question_id` varchar(36) NOT NULL COMMENT '试题表exam_question的ID',
  `course_id` varchar(36) DEFAULT '' COMMENT '课程表course的ID',
  `chapter_id` varchar(36) DEFAULT NULL COMMENT '章节表course_chapter的ID',
  `lesson_id` varchar(36) DEFAULT '' COMMENT '课时表course_chapter_period的ID',
  `bind_type` varchar(1) NOT NULL DEFAULT '0' COMMENT '绑定类型(1课程、2章节、3课时)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='试题课程关系绑定';

-- ----------------------------
--  Table structure for `file_storage`
-- ----------------------------
DROP TABLE IF EXISTS `file_storage`;
CREATE TABLE `file_storage` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `file_no` varchar(36) NOT NULL COMMENT '文件编号',
  `file_name` varchar(128) NOT NULL COMMENT '文件名称',
  `file_url` varchar(255) NOT NULL COMMENT '文件地址',
  `file_type` tinyint(3) NOT NULL COMMENT '文件类型(1:附件;2;图片;3:视频)',
  `file_size` bigint(20) NOT NULL COMMENT '文件大小',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件存储表';

-- ----------------------------
--  Table structure for `friend_link`
-- ----------------------------
DROP TABLE IF EXISTS `friend_link`;
CREATE TABLE `friend_link` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `link_name` varchar(50) NOT NULL COMMENT '名称',
  `link_url` varchar(255) NOT NULL COMMENT '链接',
  `link_target` varchar(50) NOT NULL COMMENT '跳转方式(_blank，_self)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='站点友情链接';

-- ----------------------------
--  Records of `friend_link`
-- ----------------------------
BEGIN;
INSERT INTO `friend_link` VALUES ('1060350851601158145', '2018-11-08 09:59:52', '2018-11-08 09:59:52', '1', '1', '百度', 'https://www.baidu.com', '_blank'), ('1060353540380708865', '2018-11-08 10:10:33', '2018-11-08 10:10:33', '1', '1', '旺鑫教育', 'http://www.wangxinvip.com/', '_blank'), ('fc2414e79d2045e3b2cb8f7b6fb34df7', '2020-08-13 14:22:55', '2020-08-13 14:22:55', '1', '1', '网易', 'http://www.163.com', '_blank');
COMMIT;

-- ----------------------------
--  Table structure for `lesson`
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `course_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '课程ID',
  `chapter_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '章节ID',
  `lesson_name` varchar(255) NOT NULL COMMENT '课时名称',
  `lesson_desc` varchar(255) DEFAULT NULL COMMENT '课时描述',
  `is_free` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否免费：1免费，0收费',
  `lesson_original` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '原价',
  `lesson_discount` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '优惠价',
  `buy_count` int(11) NOT NULL DEFAULT '0' COMMENT '购买人数',
  `study_count` int(11) NOT NULL DEFAULT '0' COMMENT '学习人数',
  `is_doc` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否存在文档(1存在，0否)',
  `doc_name` varchar(255) DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) DEFAULT NULL COMMENT '文档地址',
  `is_video` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否存在视频(1存在，0否)',
  `video_local_number` varchar(36) DEFAULT NULL COMMENT '视频编号',
  `video_name` varchar(255) DEFAULT NULL COMMENT '视频名称',
  `video_length` varchar(36) DEFAULT NULL COMMENT '时长',
  `video_vid` varchar(50) DEFAULT NULL COMMENT '视频VID',
  `lesson_type` tinyint(3) DEFAULT '0' COMMENT '课程类型，0点播，1直播，3回放',
  `live_broadcast_begin_time` datetime DEFAULT NULL COMMENT '直播开始时间',
  `live_broadcast_plan_length` int(11) DEFAULT '0' COMMENT '计划直播时长(分钟)',
  `live_broadcast_hls_address` varchar(500) DEFAULT NULL COMMENT '直播HLS播放地址',
  `live_broadcast_obs_stream_address` varchar(500) DEFAULT NULL COMMENT '直播推流地址',
  `live_broadcast_obs_stream_name` varchar(500) DEFAULT NULL COMMENT '直播OBS推流名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课时信息';

-- ----------------------------
--  Table structure for `lesson_apply`
-- ----------------------------
DROP TABLE IF EXISTS `lesson_apply`;
CREATE TABLE `lesson_apply` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `apply_status` int(11) NOT NULL DEFAULT '0' COMMENT '审核状态(0:待审核;1:审核通过;2:审核不通过)',
  `apply_result_desc` varchar(1024) DEFAULT NULL COMMENT '审核意见',
  `course_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '课程ID',
  `chapter_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '章节ID',
  `lesson_desc` varchar(255) DEFAULT NULL COMMENT '课时描述',
  `is_free` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否免费：1免费，0收费',
  `lesson_original` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '原价',
  `lesson_discount` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '优惠价',
  `buy_count` int(11) NOT NULL DEFAULT '0' COMMENT '购买人数',
  `study_count` int(11) NOT NULL DEFAULT '0' COMMENT '学习人数',
  `is_doc` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否存在文档(1存在，0否)',
  `doc_name` varchar(255) DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) DEFAULT NULL COMMENT '文档地址',
  `lesson_name` varchar(255) NOT NULL DEFAULT '' COMMENT '课时名称',
  `is_video` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否存在视频(1存在，0否)',
  `video_local_number` varchar(36) DEFAULT NULL COMMENT '视频编号',
  `video_name` varchar(255) DEFAULT NULL COMMENT '视频名称',
  `video_length` varchar(36) DEFAULT NULL COMMENT '时长',
  `video_vid` varchar(50) DEFAULT NULL COMMENT '视频VID',
  `lesson_type` tinyint(3) DEFAULT '0' COMMENT '课程类型，0点播，1直播，3回放',
  `live_broadcast_begin_time` datetime DEFAULT NULL COMMENT '直播开始时间',
  `live_broadcast_plan_length` int(11) DEFAULT '0' COMMENT '计划直播时长(分钟)',
  `live_broadcast_hls_address` varchar(500) DEFAULT NULL COMMENT '直播HLS播放地址',
  `live_broadcast_obs_stream_address` varchar(500) DEFAULT NULL COMMENT '直播推流地址',
  `live_broadcast_obs_stream_name` varchar(500) DEFAULT NULL COMMENT '直播OBS推流名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课时信息-审核';

-- ----------------------------
--  Table structure for `login_log`
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `login_user_no` varchar(36) NOT NULL COMMENT '登录的用户编号',
  `client_id` char(34) NOT NULL DEFAULT '' COMMENT '客户端ID',
  `login_status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '登录状态(1成功，0失败)',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录IP',
  `login_platform` tinyint(3) DEFAULT '0' COMMENT '登录平台(1PC端,2移动端,3后台管理)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='站点信息';

-- ----------------------------
--  Table structure for `mobile_middle_navigation`
-- ----------------------------
DROP TABLE IF EXISTS `mobile_middle_navigation`;
CREATE TABLE `mobile_middle_navigation` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `mmn_name` varchar(50) NOT NULL COMMENT '导航名称',
  `mmn_url` varchar(255) NOT NULL COMMENT '导航链接',
  `mmn_ico` varchar(255) DEFAULT NULL COMMENT '导航图标',
  `mmn_desc` varchar(255) DEFAULT NULL COMMENT '导航描述',
  `mmn_url_type` int(1) DEFAULT NULL COMMENT '导航链接类型，内部链接类型为1，外部链接2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='移动端中部导航';

-- ----------------------------
--  Records of `mobile_middle_navigation`
-- ----------------------------
BEGIN;
INSERT INTO `mobile_middle_navigation` VALUES ('1212', '2020-09-07 14:26:26', '2021-05-19 20:28:39', '1', '89', '领优惠券', '/my/collectCoupon/collectCouponIndex', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/85f2457ecec547cea6a0ceb0240a0619.png', '234234', '1'), ('1c93a9a4d2b3465fad273dd64a51a2ed', '2020-09-20 23:48:00', '2021-05-19 22:04:27', '1', '85', '优惠券卡', '/my/myCoupon/myCouponListIndex', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/5c72766322ed4f8a96e8c8a30024754c.png', null, '1'), ('33bfb798956f4b918e1d7c7c89bb6c31', '2020-09-08 13:33:13', '2021-05-19 22:05:22', '1', '83', '我的信息', '/my/myAccount/myAccountIndex', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/7f80729b8cc64c7283c553857d940d00.png', 'wqwq', '1'), ('4a27ae7b7e9a47e2a1d80b8751770e99', '2020-09-07 16:20:33', '2021-05-19 20:32:04', '1', '87', '外链测试', 'http://www.baidu.com', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/758ce613b9474257b204c3610e67f684.png', '233', '2'), ('4d63735d7db8422ba6e9ed6b8ddb5b7c', '2020-09-21 20:58:31', '2021-05-19 22:07:44', '1', '78', '我的订单', '/my/myOrder/myOrderListIndex', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/abb0919841b84783a9e77a0423b4fb3d.png', null, '1'), ('4d669e33fd154ddd8dc87319a267867a', '2020-09-07 18:59:25', '2021-05-19 22:06:18', '1', '82', '积分记录', '/my/myPoint/myPointIndex', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/7edee3c6a3c24efc8de8ed59a93bd638.png', null, '1'), ('5b8d8f7cabe8442d95a44a64f4754aed', '2020-09-20 23:58:28', '2021-05-19 22:06:43', '1', '80', '点券记录', '/my/myDot/myDotIndex', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2ab9e63fd50846cc882cc0ddd64bb78e.png', null, '1'), ('a54308210ecd43d8ad052adb8dd07dab', '2020-09-08 13:36:13', '2021-05-19 20:29:28', '1', '86', '学习记录', '/my/myStudyLog/myStudyLogIndex', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/24597779399e473ca38112f94b521cd3.png', '23123', '1'), ('b17538542c7642a2892716cf1af115bb', '2020-09-21 20:58:56', '2021-05-19 22:07:11', '1', '79', '我的考试', '/my/myExamManagement/myExamManagementIndex', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/d5bbd656a830486ab18b8666ef20c409.png', null, '1'), ('c8558291454a401889c009c76ace4689', '2020-09-08 13:38:11', '2021-05-18 22:43:24', '1', '90', '课程列表', '/courseAllList', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/1ae29926f35941a78e71cc3396c20c14.png', null, '1'), ('e9fcfaa751be4050b7237110b55b97ec', '2020-09-07 19:16:35', '2021-05-19 20:27:58', '1', '100', '测试练习', 'http://192.168.1.102:30002/wxmobile/#/exam/index?id=1085453180200448002&courseId=1085453180200448002', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/3f07b726eaa74a80824286383a91454b.png', '656', '2'), ('ff7df07664394b27b153818620d1a400', '2020-09-21 20:58:41', '2021-05-19 22:08:16', '1', '76', '我的课程', '/myCourseAllList', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2495b01d6f3a437a8e37b2a00eb6308c.png', null, '1');
COMMIT;

-- ----------------------------
--  Table structure for `navigation_bar`
-- ----------------------------
DROP TABLE IF EXISTS `navigation_bar`;
CREATE TABLE `navigation_bar` (
  `id` varchar(36) NOT NULL DEFAULT '0' COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `navigation_title` varchar(255) DEFAULT NULL COMMENT '导航标题',
  `navigation_url` varchar(255) DEFAULT NULL COMMENT '导航url',
  `target` varchar(255) DEFAULT NULL COMMENT '跳转方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='头部导航';

-- ----------------------------
--  Records of `navigation_bar`
-- ----------------------------
BEGIN;
INSERT INTO `navigation_bar` VALUES ('1060096588522270722', '2018-11-07 17:09:32', '2018-11-07 17:09:32', '1', '3', '课程中心', '/list', '_self'), ('1064696486139854849', '2018-11-20 09:47:00', '2020-11-17 20:17:02', '1', '4', '首页', '/index', '_self'), ('34620f60-a87a-41d2-876f-a02d08c50e7c', '2020-11-17 22:51:00', '2020-11-17 22:51:37', '1', '1', '资讯中心', '/news/newsList', '_self'), ('9f259d21-05ff-4cb6-9bae-b2f43ecdabb5', '2020-11-17 10:05:00', '2020-12-26 11:20:37', '1', '1', '百度', 'http://www.baidu.com', '_blank');
COMMIT;

-- ----------------------------
--  Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `news_title` varchar(255) NOT NULL COMMENT '新闻标题',
  `plat_show` tinyint(3) NOT NULL DEFAULT '0' COMMENT '位置(0电脑端，1微信端，2移动网页端，3全部)',
  `news_content` text COMMENT '新闻内容',
  `news_type_id` varchar(36) DEFAULT NULL COMMENT '新闻类型ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='广告信息';

-- ----------------------------
--  Table structure for `news_type`
-- ----------------------------
DROP TABLE IF EXISTS `news_type`;
CREATE TABLE `news_type` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `news_type` varchar(255) NOT NULL COMMENT '新闻类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='广告信息';

-- ----------------------------
--  Table structure for `operate_log`
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `operate_method` varchar(20) DEFAULT NULL COMMENT 'POST,GET',
  `operate_parameter` varchar(2000) DEFAULT NULL COMMENT '操作参数',
  `operate_uri` varchar(300) DEFAULT NULL COMMENT '操作URI',
  `operate_ip` varchar(50) DEFAULT NULL COMMENT '操作IP地址',
  `operate_user_no` varchar(50) DEFAULT NULL COMMENT '操作用户userNo',
  `operate_platform` tinyint(3) DEFAULT NULL COMMENT '登录平台(1PC端,2移动端,3后台管理)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='后台操作日志表';

-- ----------------------------
--  Records of `operate_log`
-- ----------------------------
BEGIN;
INSERT INTO `operate_log` VALUES ('0016291e-8876-4b8a-9d49-2e18d96897a1', '2021-06-05 19:34:41', null, '1', '0', 'POST', '{\"ids\":[\"4b4a0bf14e6b4bd0832c1fea9af39d3f\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('00199d31-d2cc-44c5-9216-be18df09884f', '2021-06-05 19:35:59', null, '1', '0', 'POST', '{\"ids\":[\"3e4da75884a943e083b1d0d9a968f2c6\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('01f50860-af32-4a7d-9be8-b36d4ed12b5d', '2021-06-05 19:35:11', null, '1', '0', 'POST', '{\"ids\":[\"2ae96b34759446b39bc8f818fecba452\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('020f5c60-1202-431e-8dd5-c9993a437e4a', '2021-06-05 19:37:49', null, '1', '0', 'POST', '{\"ids\":[\"ff0bc731dca142fd8d1241fe010f34e1\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('04e898f9-20c5-4d42-8409-f881e347502d', '2021-06-05 19:37:36', null, '1', '0', 'POST', '{\"ids\":[\"1f28901360394cee952c6184a6c1b77b\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('05cf48e8-72fc-4926-b812-1c2344d02c8a', '2021-06-05 19:36:35', null, '1', '0', 'POST', '{\"ids\":[\"20a803de9dcb49008ae0bd4d4d4eba97\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('0648d972-45e0-47de-86a2-bc3098dde92e', '2021-06-05 19:35:18', null, '1', '0', 'POST', '{\"ids\":[\"65caefe7959743e29aca73570a17c874\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('06553764-88b7-4b48-a129-eb437bef277c', '2021-06-05 19:35:03', null, '1', '0', 'POST', '{\"ids\":[\"1955fdd3d4e841b8a085b851b2eda682\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('06c97a9f-92fe-4bba-9489-63eea5a68503', '2021-06-05 19:36:18', null, '1', '0', 'POST', '{\"ids\":[\"5a595d27884648a9b7cf18924341a74d\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('08b44eea-e88a-4b8e-ae60-aeceefa3af0f', '2021-06-05 19:35:02', null, '1', '0', 'POST', '{\"ids\":[\"3feb694f6ad848ac94098db470bb9e03\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('092f0ccd-b7b0-4ea9-b6bf-f156202dc7e5', '2021-06-05 19:38:07', null, '1', '0', 'POST', '{\"ids\":[\"bf0925998c8940148206ff58f928c99d\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('0a1e1c9d-6427-4698-a838-5e60e18f61bb', '2021-06-05 19:35:34', null, '1', '0', 'POST', '{\"ids\":[\"d82205ce5fc2459daebfd66063be15cd\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('0a370109-6a47-4e5a-995d-fed08ed81b8f', '2021-06-05 19:36:44', null, '1', '0', 'POST', '{\"ids\":[\"d122ff0a4a1d4cedbf3cbd5d88b646a0\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('0be039c3-0829-49a7-99cf-a7383d53b453', '2021-06-05 19:35:22', null, '1', '0', 'POST', '{\"ids\":[\"3628ed666c234e2c9b511ae2774ca187\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('0dcc4462-5fb2-4176-b653-375a384137cd', '2021-06-05 19:38:00', null, '1', '0', 'POST', '{\"ids\":[\"d27c3ab636c74d518c0bd134a79b7b66\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('0f85d6a2-f194-4a16-9928-ed47fea8138c', '2021-06-05 19:37:41', null, '1', '0', 'POST', '{\"ids\":[\"57143200533b4b3aa045baf1f7d6e69f\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('0ffd5627-12f2-4239-9019-44fab3b77048', '2021-06-05 19:36:01', null, '1', '0', 'POST', '{\"ids\":[\"3f8e32c0653e496a8a424e251c5146d6\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('13858eda-2181-4f37-b4fe-b7a2aef5dc1d', '2021-06-05 19:38:10', null, '1', '0', 'POST', '{\"ids\":[\"d0d8b6f27b47446cb92129fefe8ba2fd\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('147b2e0e-d687-49a4-a3a0-c59196c09995', '2021-06-05 19:38:04', null, '1', '0', 'POST', '{\"ids\":[\"2e5876933f56431d9cee671a707b3c52\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('159c349e-fd81-42bd-b68b-85184bdb8330', '2021-06-05 19:37:37', null, '1', '0', 'POST', '{\"ids\":[\"ca9999c7748f4a45b279dd2b0791125a\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('16b6689e-224c-4881-9329-8823c877dda3', '2021-06-05 19:37:11', null, '1', '0', 'POST', '{\"ids\":[\"65b5f4f01bc441b18815eb2a8d705d20\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('17a8697b-c70c-4c09-9c80-e4ccfc204885', '2021-06-05 19:37:50', null, '1', '0', 'POST', '{\"ids\":[\"14232ea498044f51a55590b8bb682546\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('18454beb-3211-4d1c-8a3d-1083f75439bf', '2021-06-05 19:34:46', null, '1', '0', 'POST', '{\"ids\":[\"14494491dbb4482383782656842617f3\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('1e99d5ce-68ac-43bc-84b2-b872183bc03c', '2021-06-05 19:36:36', null, '1', '0', 'POST', '{\"ids\":[\"1e9704fb662e4710a94cdcdcf8f86e03\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('1ed28b4f-2b33-4aad-a82b-b6016007d8e6', '2021-06-05 19:36:54', null, '1', '0', 'POST', '{\"ids\":[\"32c1740d560949c09b74f92d510cb985\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('1fbd255b-035b-49d7-9165-7d9f9c2cca66', '2021-06-05 19:34:36', null, '1', '0', 'POST', '{\"ids\":[\"163b4cb9af074d48adad192bb0f9964d\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('236eda1a-baf5-4134-8dcf-dc664fac36cf', '2021-06-05 19:34:29', null, '1', '0', 'POST', '{\"ids\":[\"f70da5652bc84470b85bea02657d422b\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('29042b80-5652-435b-b982-81bbf88e60d7', '2021-06-05 19:35:39', null, '1', '0', 'POST', '{\"ids\":[\"6fbc9594174d473d8c9e7d8338c0b5f1\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('293e34e3-f1d8-49c6-a194-35ef675772a5', '2021-06-05 19:35:24', null, '1', '0', 'POST', '{\"ids\":[\"062cdd2df4a14b038dfe41a4031b2c3f\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('2f125a28-f14e-4377-af2f-dc8cd063bc21', '2021-06-05 19:37:23', null, '1', '0', 'POST', '{\"ids\":[\"cc8793b7746043cfa87c93bbf6730754\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('30d42a13-f7af-40d0-a984-3d805d2f3d26', '2021-06-05 19:37:18', null, '1', '0', 'POST', '{\"ids\":[\"db9fabf1ff3d46258256ed33c5e23223\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('314567cf-2d1a-4190-b495-98726af2a3b7', '2021-06-05 19:36:27', null, '1', '0', 'POST', '{\"ids\":[\"92097a1971004fdf8a4c7ae995aef773\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('3376209f-3c13-45bb-ab6d-74e18e1a8a85', '2021-06-05 19:38:20', null, '1', '0', 'POST', '{\"ids\":[\"f1e2d721c852420d8d273a791526aa00\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('33b1830d-f4da-4ccd-9342-971a484f2f94', '2021-06-05 19:37:20', null, '1', '0', 'POST', '{\"ids\":[\"ca65481ea6b343cb926396b6ae4234c3\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('340289fa-928e-4fee-aca0-6b09d1ee7c28', '2021-06-05 19:34:49', null, '1', '0', 'POST', '{\"ids\":[\"8500e2d1394c4ed4ad9ae599e2aba1f4\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('36847c51-f963-4fa8-8c20-f547c7ed5b5d', '2021-06-05 19:35:05', null, '1', '0', 'POST', '{\"ids\":[\"f90481949d9e4abcb105a1da0a5f0fff\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('3813cf39-b6fb-4d66-a4fc-eeadd19fa8fb', '2021-06-05 19:35:57', null, '1', '0', 'POST', '{\"ids\":[\"22366587b7b7470d8e921b7fa99a2243\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('3928fc72-62cd-4260-9ea1-036264643faf', '2021-06-05 19:35:16', null, '1', '0', 'POST', '{\"ids\":[\"00f586e41cd14032a77447a226809e9d\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('3b8ff08d-f9ac-4dc6-b534-a140beb0b247', '2021-06-05 19:35:31', null, '1', '0', 'POST', '{\"ids\":[\"c8b97e5437c243d38fcfa0083586ceea\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('3c386e38-86f2-423c-9122-2a059ed5ee86', '2021-06-05 19:36:28', null, '1', '0', 'POST', '{\"ids\":[\"618de090521c43488b2cc8457653323b\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('3e826a35-b71a-4b67-b01b-6c9b3f7c2a8d', '2021-06-05 19:37:15', null, '1', '0', 'POST', '{\"ids\":[\"760aca54a88a4f0a9ae04be8cb896e3a\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('3eba0c8a-299c-4918-96ae-8dde4e907acc', '2021-06-05 19:36:26', null, '1', '0', 'POST', '{\"ids\":[\"669bcb8ed04844deac557b575807a301\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('400cad99-d003-4dc9-ab07-9b35ca47bf3c', '2021-06-05 19:36:08', null, '1', '0', 'POST', '{\"ids\":[\"75f4639f8ed143458230770ce16c8293\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('42e772b6-98f3-469b-abeb-1536cb8baf9a', '2021-06-05 19:35:08', null, '1', '0', 'POST', '{\"ids\":[\"b97c7861582a4d8f9c56f2d3de887bd6\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('45dd5add-6bcc-4f23-8b87-f879d5a68a52', '2021-06-05 19:37:04', null, '1', '0', 'POST', '{\"ids\":[\"9d9987a3ea1542d992a62851fa5bb67c\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('4623d599-5d74-4e57-8d97-91dfdb1d07c7', '2021-06-05 19:37:47', null, '1', '0', 'POST', '{\"ids\":[\"63e469621ee1483192d9c4fdf82bfd50\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('48493a4b-82d8-47d2-9b2a-771593de96ba', '2021-06-05 19:34:52', null, '1', '0', 'POST', '{\"ids\":[\"855a9e97a3404670aa9acf95aaa4ec45\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('4afe3ab4-127f-48e2-bbe3-25c160c37df6', '2021-06-05 19:36:38', null, '1', '0', 'POST', '{\"ids\":[\"2222b327b20f47838fd4c100654a2ff1\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('4c7ca389-fc59-4be1-b90d-04870390fbab', '2021-06-05 19:36:59', null, '1', '0', 'POST', '{\"ids\":[\"5bddb48b466742a3871b7064a8bdaccc\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('4e15ad96-ade1-42d5-bcfe-ea3a74fe8cf4', '2021-06-05 19:34:35', null, '1', '0', 'POST', '{\"ids\":[\"0ad476e038a04c3899aa1abfd793a8c3\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('5337eb0f-bbab-44e0-871b-7aa6e18813aa', '2021-06-05 19:36:06', null, '1', '0', 'POST', '{\"ids\":[\"ec862f4b089748b3aad8dd35a9680a97\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('5477286a-fd53-4d2b-8897-03769b34df78', '2021-06-05 19:37:54', null, '1', '0', 'POST', '{\"ids\":[\"5d41516ddc3046888fd603cfd30699e9\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('564192f3-7832-495d-9d60-0993df01b281', '2021-06-05 19:34:47', null, '1', '0', 'POST', '{\"ids\":[\"20283c7023664b4db0bb9a293f7ab5d1\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('570bed6d-83c5-4cec-b9e4-b57c9aac606e', '2021-06-05 19:35:35', null, '1', '0', 'POST', '{\"ids\":[\"ed7421ca4fd74a97956e369d52e502f1\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('5ac3029f-2140-4c56-9629-f004775e0aab', '2021-06-05 19:36:43', null, '1', '0', 'POST', '{\"ids\":[\"348725d1e21041b3a8d1298012ce8fc6\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('5e020be5-7bfc-4fb2-906e-5abc1ea7c833', '2021-06-05 19:38:19', null, '1', '0', 'POST', '{\"ids\":[\"420b9d27f9b24283a06dc96384611945\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('6051696a-b60a-4d1c-941a-fb67bcafc74e', '2021-06-05 19:35:41', null, '1', '0', 'POST', '{\"ids\":[\"a0cf433408944e0faf0287a039f7783b\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('60ae2b98-b002-4a11-b633-7cfc180e5031', '2021-06-05 19:38:03', null, '1', '0', 'POST', '{\"ids\":[\"66da6b0e565e4c84a62cc471c9d92923\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('60fc7b19-c6d7-48de-908e-4ad9f1ceaae0', '2021-06-05 19:37:09', null, '1', '0', 'POST', '{\"ids\":[\"6d27134f36a74dd49379ff9dddb862af\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('65dc7633-fd3e-4bec-bf89-8444f72d5f32', '2021-06-05 19:38:17', null, '1', '0', 'POST', '{\"ids\":[\"2b325f6e9e154d4f8e60abd2ade67b07\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('682cc138-fe3c-4ae4-8778-fb7d27c705c3', '2021-06-05 19:34:38', null, '1', '0', 'POST', '{\"ids\":[\"99371efe22704bf08b42d6e9b88b6bc7\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('68bb675f-2173-4af7-9afd-c467687be342', '2021-06-05 19:37:52', null, '1', '0', 'POST', '{\"ids\":[\"c1a31461ef9b48438ccb3c2922a32710\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('6c204dfa-3b82-49c1-b5bf-86e525707d38', '2021-06-05 19:37:29', null, '1', '0', 'POST', '{\"ids\":[\"60de13e17e6d4c5fb9882a3224d07701\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('74e7e61d-971d-4e56-a9b9-e6fe9a2be61c', '2021-06-05 19:36:15', null, '1', '0', 'POST', '{\"ids\":[\"240fce3a6d7b4bffb557ebdc6eeace2d\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('7547eba8-ba82-47b1-97d8-5cbc29a27fa4', '2021-06-05 19:35:32', null, '1', '0', 'POST', '{\"ids\":[\"070652169c0f401bbe890b0164824514\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('78aa9fbf-f739-4758-8825-ae9c767cdccd', '2021-06-05 19:37:31', null, '1', '0', 'POST', '{\"ids\":[\"cec9f544029b41918fa7802052b32d06\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('799e8801-7a32-4f14-bcf6-12a2f59b0d8b', '2021-06-05 19:36:20', null, '1', '0', 'POST', '{\"ids\":[\"6711a986be614071a64f2815418078e5\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('7a728529-d554-468f-815f-95456f239090', '2021-06-05 19:37:32', null, '1', '0', 'POST', '{\"ids\":[\"0e26afa3e0594983af8e0cb8cc5f89e9\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('7a9129e0-ef97-40f7-b860-cfcfdeacb365', '2021-06-05 19:37:58', null, '1', '0', 'POST', '{\"ids\":[\"3af970b274a940c7be2a1eb264f720f1\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('7aa19ce4-48a4-4ee3-be0a-dc2c8502ff77', '2021-06-05 19:37:12', null, '1', '0', 'POST', '{\"ids\":[\"080cfdcc52434058b9a90388311c3e77\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('7d18441d-6bdf-4eb2-a639-541cd60c7a3f', '2021-06-05 19:37:26', null, '1', '0', 'POST', '{\"ids\":[\"9573335c49bd4dddb734c687ba4f6e25\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('8119bbed-1659-49df-b71a-994b532b9fb0', '2021-06-05 19:34:44', null, '1', '0', 'POST', '{\"ids\":[\"bfb17af6ac7543ec9fab852ec190de03\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('83ec927a-3942-4069-89f8-2cf0f7446f6e', '2021-06-05 19:35:06', null, '1', '0', 'POST', '{\"ids\":[\"49566064ddf6432da5dcb3f7488ceef1\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('85d1fc74-4273-4997-9eaa-a48451af39c3', '2021-06-05 19:38:01', null, '1', '0', 'POST', '{\"ids\":[\"8f01b2dd4fe240e5be3e77b246104a5a\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('8a248187-b68d-4ad8-9cc5-cc8ed73acc73', '2021-06-05 19:35:13', null, '1', '0', 'POST', '{\"ids\":[\"7411ae77a3614beabc9c8abed8facabb\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('8b098de5-8028-47de-8464-f6ad4ab3c2ee', '2021-06-05 19:38:13', null, '1', '0', 'POST', '{\"ids\":[\"6df9d23f803f45d5823d8ebc9683598a\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('8c1cb7dd-0fb4-4f2b-8b18-3f8a5be80e80', '2021-06-05 19:36:30', null, '1', '0', 'POST', '{\"ids\":[\"741b5fdf64fc4f0dae9d55a096341da0\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('8caec67f-f8f8-4920-8462-eef5105b07d4', '2021-06-05 19:36:21', null, '1', '0', 'POST', '{\"ids\":[\"9e9b3f967c4e4a8880bffac280452000\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('8d2134b7-43b2-4f7a-965f-4c236849110f', '2021-06-05 19:36:33', null, '1', '0', 'POST', '{\"ids\":[\"15d1755fc58f4781a3d69acb6bed2d55\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('9247d39e-56b9-446b-8d25-1b2f61d63ce7', '2021-06-05 19:37:45', null, '1', '0', 'POST', '{\"ids\":[\"f32b5afca55940ecbfbc01391ffaac19\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('92d8195a-1c65-4825-9193-9c1d81d2661c', '2021-06-05 19:37:06', null, '1', '0', 'POST', '{\"ids\":[\"ac927fc13c554756a817fd7b3de221db\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('97e27075-b685-40b9-b5e7-73d9267037cb', '2021-06-05 19:35:46', null, '1', '0', 'POST', '{\"ids\":[\"193943a0807a443f92ec91e8bcdfd41c\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('99170400-139d-4aeb-960b-91415dc5ba23', '2021-06-05 19:35:14', null, '1', '0', 'POST', '{\"ids\":[\"318cadee15c84d268574ee9ee43c7edc\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('9a51e746-1af0-4cbf-a764-1221b147eb6c', '2021-06-05 19:36:50', null, '1', '0', 'POST', '{\"ids\":[\"7882b71da8374ed68187f1e36854b259\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('9cf6117d-641c-4cc0-ba8c-f8555d33afb0', '2021-06-05 19:35:26', null, '1', '0', 'POST', '{\"ids\":[\"5272ff5428be479ea2a860c1efd3e564\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('9d34c54a-d99f-403f-8657-d0ade5b69925', '2021-06-05 19:37:39', null, '1', '0', 'POST', '{\"ids\":[\"a835b9524b124807966e34df93c136f4\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('9e0e9e09-f708-499a-ba05-0ba4328768cf', '2021-06-05 19:37:22', null, '1', '0', 'POST', '{\"ids\":[\"dbfe0e720b124c59ba710a8b11cdc87a\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('9f4a61b6-e78d-4036-9bee-a52560747482', '2021-06-05 19:35:38', null, '1', '0', 'POST', '{\"ids\":[\"c1f68c655d324c5bb46382860cac200e\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('a0022071-4925-4e3e-b3fb-e66bd0ce4dbb', '2021-06-05 19:36:52', null, '1', '0', 'POST', '{\"ids\":[\"c2a9d53c6bbe474da1ba8f03702bdfa5\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('a181b6b6-b6b4-44d1-8dc2-4147b51a0eeb', '2021-06-05 19:34:56', null, '1', '0', 'POST', '{\"ids\":[\"fd5fcd0ce27b4480865054c2cb5dddfb\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('a2d40fb1-eb2e-4e64-acbf-7d02befa0bba', '2021-06-05 19:36:02', null, '1', '0', 'POST', '{\"ids\":[\"c3308a77bb034dd6bcd0bf296104561e\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('a3b90943-441b-42f4-a0a3-613ce38e2f65', '2021-06-05 19:35:37', null, '1', '0', 'POST', '{\"ids\":[\"b57ce57ec02642a3a36d6161eaacc591\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('a3b937a2-a8a1-4c6d-8c33-cec2771d44d7', '2021-06-05 19:37:07', null, '1', '0', 'POST', '{\"ids\":[\"8e278c155c6e485ba0b67ac71b6fdd5b\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('a50a2988-d3da-4647-9a9b-233126ee57f9', '2021-06-05 19:35:56', null, '1', '0', 'POST', '{\"ids\":[\"7fc507a1d7e1439c87aa109ad2fb4c80\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('a61cff45-1b34-4e91-a4a7-9f9e5cd96f20', '2021-06-05 19:34:43', null, '1', '0', 'POST', '{\"ids\":[\"624ef83b372c49b0a7111b84585d37d7\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('a851e482-13e5-4d16-b5b6-415afbe784c1', '2021-06-05 19:37:14', null, '1', '0', 'POST', '{\"ids\":[\"24e8f4e728e2402b8f5b692151ef0bc7\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('a8796535-5938-4eff-9782-76839f9e8c79', '2021-06-05 19:36:47', null, '1', '0', 'POST', '{\"ids\":[\"36adb375b9304b8f8b7b242a02430ce8\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('aab1fc75-ac01-47e9-a8cd-deaae909553c', '2021-06-05 19:35:00', null, '1', '0', 'POST', '{\"ids\":[\"a8574894ff0744f7b1119f801a3be31a\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('aaccc0b6-6b9a-4953-8bd4-31b758570405', '2021-06-05 19:34:33', null, '1', '0', 'POST', '{\"ids\":[\"3f64091d7d4c449ba7088d3bda155c55\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('aca58eb5-c675-48c7-bffe-8c65c80276b5', '2021-06-05 19:35:43', null, '1', '0', 'POST', '{\"ids\":[\"fa2e92eab8ca48e788a2e075adf4f730\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('b0bb1827-1a33-42b9-a84d-f4c06f31e1fc', '2021-06-05 19:38:14', null, '1', '0', 'POST', '{\"ids\":[\"abe3be2b775442c98ad0dc9ab4a3eb51\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('b37e8c8f-019a-4041-9b10-766666ed3886', '2021-06-05 19:37:02', null, '1', '0', 'POST', '{\"ids\":[\"b546f5ac6a4b409580e421cce6327aa9\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('b4d0c13c-96c1-458c-a8c1-18ac0b786f13', '2021-06-05 19:36:22', null, '1', '0', 'POST', '{\"ids\":[\"1d6587e1019b4c97b5581095648c1c78\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('b509b735-e619-4104-bdfa-fd86e03899e4', '2021-06-05 19:38:06', null, '1', '0', 'POST', '{\"ids\":[\"a7d73534db51496781e0db421fbade15\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('ba9a65ba-2d12-45b0-aa27-96efb4e36b11', '2021-06-05 19:37:34', null, '1', '0', 'POST', '{\"ids\":[\"91dc2038ab9a4653994051daa74ea530\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('bb245017-db7c-4522-8152-af0c980a07e4', '2021-06-05 19:36:11', null, '1', '0', 'POST', '{\"ids\":[\"9d632cb03e2c44df8e6a8c9f7fe2af00\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('bc561a37-9cac-452f-817a-d0a0a61e4164', '2021-06-05 19:37:56', null, '1', '0', 'POST', '{\"ids\":[\"1d70f14916e44c4db1fc2b6c9003b5d2\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('bf11fbb6-7c5f-4e4c-904a-2a05c94ed74e', '2021-06-05 19:37:57', null, '1', '0', 'POST', '{\"ids\":[\"056c1bb27fb84f84af8e4f6c104aac9d\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('c290c9c7-b807-426e-b837-31fb207948f7', '2021-06-05 19:37:01', null, '1', '0', 'POST', '{\"ids\":[\"1cb9da6ad20c441580670b6f0dd828c9\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('c39bcd17-0b1b-4752-9936-5bebb769520f', '2021-06-05 19:36:16', null, '1', '0', 'POST', '{\"ids\":[\"2c6b9f7407994a65bc9a9d1ac3d2fc91\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('c477b51c-e5ca-4288-9c16-b3a241fa3da4', '2021-06-05 19:36:03', null, '1', '0', 'POST', '{\"ids\":[\"752da2e148ef4e11a8e4d98132585d9c\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('c5212eee-e5d3-4cc0-9a21-0839c5c6b78d', '2021-06-05 19:36:49', null, '1', '0', 'POST', '{\"ids\":[\"a7e4b04f43074b4b8d829a2f7227dd49\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('c57fee66-c3f4-4132-9b28-9e93d9ea7d85', '2021-06-05 19:36:57', null, '1', '0', 'POST', '{\"ids\":[\"1fad8e37fe20428ab8289ba404d904ca\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('c621e9bb-76a2-47af-9cfa-7e4474087e8b', '2021-06-05 19:37:28', null, '1', '0', 'POST', '{\"ids\":[\"2167b040815f4494a509abc8e085d878\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('c6e3f17f-0893-4ebf-a85e-3bb2fb5c7e8d', '2021-06-05 19:36:05', null, '1', '0', 'POST', '{\"ids\":[\"e4312377a6d740f7b17b5d764375c816\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('cbc3c770-2064-400e-b7c5-d5a56400a0d1', '2021-06-05 19:35:54', null, '1', '0', 'POST', '{\"ids\":[\"af71480e2f9d400a889c491a44883229\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('cf91ef49-529a-43a4-a68d-49896d4ec68a', '2021-06-05 19:37:17', null, '1', '0', 'POST', '{\"ids\":[\"73a6381a8bc341a291390601053fc2f3\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('d0518ef0-33e9-4f4d-b888-6727cd635061', '2021-06-05 19:34:39', null, '1', '0', 'POST', '{\"ids\":[\"ca2b1229d35c47b2b831612315079470\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('d0936fec-e1a7-4d38-a89a-a852557a87fc', '2021-06-05 19:36:32', null, '1', '0', 'POST', '{\"ids\":[\"7b771910924845f68dec230946f0c282\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('d1aa318a-e145-4e0e-ad34-d05fa9322697', '2021-06-05 19:36:24', null, '1', '0', 'POST', '{\"ids\":[\"a973fe6d13724265941235546e6b0882\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('d7213435-f25a-4640-987d-1f1934183c67', '2021-06-05 19:34:31', null, '1', '0', 'POST', '{\"ids\":[\"b32e47b435fa4afa96387ea67e39f2b5\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('dc974a61-6997-4509-8d6a-2c54c825358a', '2021-06-05 19:35:20', null, '1', '0', 'POST', '{\"ids\":[\"06115ab98f104d2c8d3b87679ad4d088\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('dcda0773-11bd-455e-b194-0c3ae0b1e90f', '2021-06-05 19:36:39', null, '1', '0', 'POST', '{\"ids\":[\"1b33c8f7ecd042c9adb681061dcc158c\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('dfe8bf09-5604-4626-864b-5d7e2f4b4d0c', '2021-06-05 19:35:27', null, '1', '0', 'POST', '{\"ids\":[\"348ee90bc3b94ba0849f4861323299ad\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('e22780f5-b9af-4037-ba3e-45e05864f560', '2021-06-05 19:37:42', null, '1', '0', 'POST', '{\"ids\":[\"3334f1cbd5764c10a7be8842cab5365d\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('e79bc802-de9d-4ca7-8246-aec184e6d9e5', '2021-06-05 19:35:19', null, '1', '0', 'POST', '{\"ids\":[\"bde152f8db7e4ec8a26f4e0f523b9b06\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('e7bf1157-a060-47f2-8fab-b40d2c90e5f9', '2021-06-05 19:38:11', null, '1', '0', 'POST', '{\"ids\":[\"10fae7777cf545588c05286c5bef348c\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('e8835a8a-3196-433a-9555-4d304675fca3', '2021-06-05 19:36:13', null, '1', '0', 'POST', '{\"ids\":[\"feacb64adec6494580669d410ecfeb75\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('e9f68e4d-5020-4726-a9ab-35cee4cdea9d', '2021-06-05 19:35:45', null, '1', '0', 'POST', '{\"ids\":[\"cb53621068a24822a4b91c493d33eee1\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('f0908c0b-ae82-412b-ac98-d6853176e3fa', '2021-06-05 19:36:56', null, '1', '0', 'POST', '{\"ids\":[\"2797db2bbe564713be77679e68fb5a2a\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('f17571b0-d16e-4f48-b81f-89a05acf62df', '2021-06-05 19:34:54', null, '1', '0', 'POST', '{\"ids\":[\"9059e474ea8b43c1b1cfd62feeb9d331\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('f28aa22a-2abf-4c09-814b-dfa3239c5055', '2021-06-05 19:35:48', null, '1', '0', 'POST', '{\"ids\":[\"182e754abfd24804b0ec648f3b53a577\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('f557c7fb-c914-4aaa-95e0-ba5117f95a58', '2021-06-05 19:34:50', null, '1', '0', 'POST', '{\"ids\":[\"0cbb1c641a694ffc86fb9ffd2fa6d914\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('f872bca7-48ce-4fdc-910b-e0138991b601', '2021-06-05 19:35:52', null, '1', '0', 'POST', '{\"ids\":[\"38fe92c6317a4fe79a79c56eb20987e9\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('f9fcc0ee-631a-4c66-9364-9f027fb80754', '2021-06-05 19:38:16', null, '1', '0', 'POST', '{\"ids\":[\"a49b2a4c6218463293c9a39c6c24a09c\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('fa3d513e-d4e0-4bbd-b89e-fa19fd69ac2a', '2021-06-05 19:35:09', null, '1', '0', 'POST', '{\"ids\":[\"f8b76749995748a9844dd05c6b26829f\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/coupon/delete', '127.0.0.1', '21060212272507456918048065577890', '3'), ('ff8995ec-8bef-41cd-8cf4-abee82b2511f', '2021-06-05 19:35:49', null, '1', '0', 'POST', '{\"ids\":[\"8b930a7f1bef46019557b88e8d7f8c36\"],\"platform\":\"pcboss\",\"userNo\":\"21060212272507456918048065577890\"}', '/pcboss/market/studycard/delete', '127.0.0.1', '21060212272507456918048065577890', '3');
COMMIT;

-- ----------------------------
--  Table structure for `order_main`
-- ----------------------------
DROP TABLE IF EXISTS `order_main`;
CREATE TABLE `order_main` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `order_user_no` varchar(36) NOT NULL COMMENT '用户编号',
  `order_no` varchar(36) DEFAULT NULL COMMENT '订单号',
  `course_id` varchar(36) NOT NULL COMMENT '课程ID',
  `payable_amount` decimal(11,2) DEFAULT NULL COMMENT '应付金额',
  `discount_amount` decimal(11,2) DEFAULT NULL COMMENT '折扣金额',
  `payable_actual_amount` decimal(11,2) DEFAULT NULL COMMENT '折扣后应付金额(包括学习卡、优惠券的金额)',
  `pay_amount` decimal(11,2) DEFAULT NULL COMMENT '实付金额(不包括学习卡、优惠券的金额)',
  `pay_status` int(11) DEFAULT '0' COMMENT '支付状态，0未支付，1已支付，2已作废,3已取消',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `last_date_for_payment` datetime DEFAULT NULL COMMENT '订单支付最后期限',
  `qr_code` varchar(500) DEFAULT NULL COMMENT 'PC支付的二维码qrCode',
  `system_remark` varchar(200) DEFAULT NULL COMMENT '系统备注',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `alipay_html_form` text COMMENT '支付宝移动端返回的跳转html表单内容',
  PRIMARY KEY (`id`),
  UNIQUE KEY `订单号唯一` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单';

-- ----------------------------
--  Table structure for `pay_amount_record`
-- ----------------------------
DROP TABLE IF EXISTS `pay_amount_record`;
CREATE TABLE `pay_amount_record` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `order_id` varchar(36) NOT NULL COMMENT '订单ID',
  `pay_type` int(11) NOT NULL COMMENT '支付方式，1支付宝，2微信支付，3学习卡，4优惠券',
  `pay_amount` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '支付金额',
  `pay_type_relate_id` varchar(36) DEFAULT NULL COMMENT '付款类型相关的ID，如学习卡就是学习卡的ID、优惠券就是优惠券的ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='支付记录';

-- ----------------------------
--  Table structure for `point`
-- ----------------------------
DROP TABLE IF EXISTS `point`;
CREATE TABLE `point` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `total_point` int(11) NOT NULL COMMENT '积分',
  `bind_user_no` varchar(36) NOT NULL COMMENT '绑定用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='积分';

-- ----------------------------
--  Records of `point`
-- ----------------------------
BEGIN;
INSERT INTO `point` VALUES ('06abe2a2-7cae-4e01-ae46-4e6158e3cd29', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580610283631142256884431'), ('085e0f93-3346-48ce-9f09-8e5ebb9c612d', '2021-02-14 13:03:56', '2021-02-14 13:03:56', '1', '0', '0', '21021413035657496822054815940026'), ('1a67d73c-f6c8-491c-8bfd-145df2e4d0b8', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611321877372972219440'), ('1b33a619-d0a8-42f2-8af7-79388288ea86', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580610046275332113487860'), ('2002f96f-2847-4ecf-a2c6-016a9c600dda', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580604882976832056683317'), ('21a77087-f887-48c5-ae7d-ad0b6fb80658', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580609625731840188730163'), ('26a532a8-3a3b-47eb-a869-83c3295c5e05', '2021-06-02 12:27:25', '2021-06-02 12:27:25', '1', '0', '0', '21060212272507456918048065577890'), ('27e6d3b4-1303-4954-9cad-80cc797e37d1', '2021-06-02 12:02:11', '2021-06-02 12:02:11', '1', '0', '0', '21060212021130796170590587471005'), ('2bce6c42-8dc2-426b-8770-2da8316b3f70', '2021-03-01 16:24:05', '2021-03-01 16:24:05', '1', '0', '0', '21030116240542536664161023844667'), ('2dfa461f-14bd-4215-ac0c-912384d88b5c', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580612340114227701182080'), ('2ed474ee-c53e-4e2b-91bc-9cbe28e101e0', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580607777057125981229238'), ('406d6a37-f479-40f9-a6cd-394f27f2c207', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611691533811448580386'), ('4227740e-2217-40cd-9586-fbb3118b8432', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580609846396842166118724'), ('49ae23b3-2e02-469c-9556-c5d440bbed99', '2021-03-01 15:09:40', '2021-03-01 15:09:40', '1', '0', '0', '21030115094015658368559394751065'), ('4d3f4d42-9f3d-4fce-940e-021e3bb5923a', '2021-06-01 14:55:35', '2021-06-01 14:55:35', '1', '0', '0', '21060114553540338915839527239786'), ('4e724ed2-8fb7-4e8b-abba-f551a3e44ff4', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611966102027138175321'), ('58278409-fa12-4fa5-95c4-08876d95bd57', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580609169114207096425571'), ('60bbc40f-7a85-41f8-aede-abd46677fa06', '2021-03-01 14:21:25', '2021-03-01 14:21:25', '1', '0', '0', '21030114212528621808833741830086'), ('6250c1c7-8904-4048-bc57-438377230d96', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611165051122887412741'), ('66a04372-f1ef-4f4f-8615-c8f01743f751', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580606554019967213761617'), ('68c83a6a-5972-458a-9605-d1f0f3468c22', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580608977816682130507527'), ('6a0bfbd1-d48b-4230-b899-7e89b48bbd4e', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580605676162554073800947'), ('7bb42d01-14da-430c-a058-0fad4648ded4', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580609448204433526722815'), ('80df0ce3-f1fd-40ab-b797-d583f74eb6e7', '2021-06-01 15:07:33', '2021-06-01 15:07:33', '1', '0', '0', '21060115073397860541541482500672'), ('84ca10a5-0d88-46d1-9546-b634f5f12ebd', '2021-01-06 20:16:18', '2021-01-06 20:16:18', '1', '0', '0', '21010620161855121855047947146308'), ('856236da-a142-486d-a969-9561e0c37d9c', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580605887047593924495148'), ('8638d204-00a7-4ef2-98ae-8181cc8d2e56', '2021-02-16 15:52:06', '2021-02-16 15:52:06', '1', '0', '0', '21021615520669973122825754353427'), ('877440b6-2379-4f4b-a624-1db39bf17db2', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611455307127120225395'), ('8aa359fa-3a2a-4149-9092-4b7ab3106c17', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580604661852042770712708'), ('934c2564-cf33-4822-98e0-cd27fb8bde7f', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580612107601803799540168'), ('98abc2e6-4735-49a7-aab9-2cd8e301f199', '2020-10-15 20:58:11', '2021-03-30 13:37:35', '1', '0', '68', '2018112015051635'), ('a0018a4f-7048-4c48-9ca5-3fdc67ee4977', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580605150377266328025265'), ('a0d4f40d-64c2-4743-9144-c3d85e8f57ac', '2020-11-04 16:19:50', '2020-11-04 16:19:50', '1', '0', '0', '2020031014031940'), ('a28caafe-67b9-4e40-864b-f0a73054ee0d', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580611771345035510437098'), ('a3f68c69-0736-4654-b357-88831bf69f53', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580607459650553264807404'), ('a6a25b46-d034-4281-904a-7f146857db8a', '2021-06-01 14:52:48', '2021-06-01 14:52:48', '1', '0', '0', '21060114524870277233411930715460'), ('af148352-1a5e-40fb-ba55-fe12ed43e3df', '2021-03-01 16:24:55', '2021-03-01 16:24:55', '1', '0', '0', '21030116245566239342444732520414'), ('b54b8ca0-09ba-4486-8eed-1297462b9da7', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580607241730872072168221'), ('b8503cfa-04b9-437f-8d02-7f13f4d1c507', '2021-01-06 20:16:18', '2021-01-06 20:16:18', '1', '0', '0', '21010620161860200173049377103699'), ('bdd908b9-bf2f-48d3-8091-dfd6ba4c73f0', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580610714303825155406318'), ('bee2d3c5-452d-4023-bef0-12d751c8ff05', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580608625468308793512735'), ('bfb64188-d2f9-4240-9462-17583074730d', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580608024434172478139225'), ('c17db955-f206-4fb4-b9e7-c0a315606aa7', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580604027665208314805187'), ('c8b2e215-bbbb-41b7-be92-99a812638c5c', '2021-01-06 20:17:23', '2021-01-06 20:17:23', '1', '0', '0', '21010620172357866103149158622331'), ('c8d43066-5d32-4223-8f80-4332e625c5e3', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580607036644282502503096'), ('cc7d1b7b-04fa-4ad5-9d22-5c2662b1c17e', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580610973461732890090270'), ('cdb4b0dd-6165-4d64-ac71-c0b79c6aecfe', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580604312125349573293567'), ('d1ae74e1-9293-42a3-9140-1ee86ebd6823', '2021-06-01 14:55:50', '2021-06-01 14:55:50', '1', '0', '0', '21060114555079990365792802161409'), ('d48e3390-3fa1-48a3-8825-aeb5d37edadb', '2021-01-21 20:50:48', '2021-01-21 20:50:48', '1', '0', '0', '21012120504841247734538848642494'), ('e888edf7-f23e-44b8-a162-89e823b1d756', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580610535888178474916611'), ('e88ee789-4ef9-4867-8cf5-3ecba2ebf5a2', '2021-01-21 20:31:32', '2021-01-21 20:31:32', '1', '0', '0', '21012120313250591930860425684523'), ('ed139ab1-a46f-4186-9843-102f325a9593', '2021-02-14 13:03:56', '2021-02-14 13:03:56', '1', '0', '0', '21021413035653004492117885964802'), ('f1a7c3e1-6d11-437a-a9cb-4d4587f79f94', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580606334444009642497236'), ('f38d3d67-d27e-4572-9b22-07b8a03d92c4', '2021-03-01 15:12:03', '2021-03-01 15:12:03', '1', '0', '0', '21030115120353228610125714038829'), ('f5c746f7-300d-41c2-8069-c050d711e635', '2021-02-16 15:58:06', '2021-02-16 15:58:06', '1', '0', '0', '21021615580606877776236072970522');
COMMIT;

-- ----------------------------
--  Table structure for `point_operate_record`
-- ----------------------------
DROP TABLE IF EXISTS `point_operate_record`;
CREATE TABLE `point_operate_record` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `user_no_of_point` varchar(36) NOT NULL COMMENT '积分所属用户的用户编号',
  `operate_type` int(3) NOT NULL COMMENT '操作类型，0增加，1减少',
  `point` int(11) DEFAULT NULL COMMENT '积分',
  `total_point` int(11) DEFAULT '0' COMMENT '总积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='积分操作记录';

-- ----------------------------
--  Table structure for `send_sms_log`
-- ----------------------------
DROP TABLE IF EXISTS `send_sms_log`;
CREATE TABLE `send_sms_log` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `template` varchar(36) NOT NULL COMMENT '短信模板',
  `mobile` char(11) NOT NULL COMMENT '手机号码',
  `sms_code` varchar(12) NOT NULL COMMENT '验证码',
  `is_success` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否发送成功(1发送成功，0:发送失败)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户发送短信日志';

-- ----------------------------
--  Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `user_no` varchar(36) NOT NULL COMMENT '用户编号',
  `user_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '用户类型(1用户，2讲师)',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '用户手机',
  `sex` tinyint(3) unsigned DEFAULT '3' COMMENT '性别(1男，2女，3保密)',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `identity_card_no` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_mobile` (`mobile`),
  UNIQUE KEY `uk_user_no` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户教育信息';

-- ----------------------------
--  Records of `student`
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES ('1a6526859d8140368435bc0503b65927', '2021-06-01 15:07:33', '2021-06-01 15:07:33', '1', '21060115073397860541541482500672', '1', '13800138001', '3', null, '3333', null, null, '13800138001', '323323232'), ('328a55360c0844edbdf5e147972abbdd', '2021-06-02 12:02:11', '2021-06-02 12:02:11', '1', '21060212021130796170590587471005', '1', '18800000000', '3', null, null, null, null, '18800000000', null), ('a7af5fca4ea4420d87f9cdcbf5541106', '2021-06-02 12:27:25', '2021-06-02 12:27:25', '1', '21060212272507456918048065577890', '1', '13800138000', '3', null, null, null, null, '13800138000', null);
COMMIT;

-- ----------------------------
--  Table structure for `student_group`
-- ----------------------------
DROP TABLE IF EXISTS `student_group`;
CREATE TABLE `student_group` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `group_name` varchar(20) NOT NULL COMMENT '用户组名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_name_unique` (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户分组';

-- ----------------------------
--  Table structure for `student_student_group_relate`
-- ----------------------------
DROP TABLE IF EXISTS `student_student_group_relate`;
CREATE TABLE `student_student_group_relate` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `user_no_for_group` varchar(36) NOT NULL COMMENT '用户编号',
  `student_group_id` varchar(36) NOT NULL DEFAULT '1' COMMENT '用户组表(user_group)ID',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_no_user_group_id_unique` (`user_no_for_group`,`student_group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学员与分组关系';

-- ----------------------------
--  Table structure for `study_card`
-- ----------------------------
DROP TABLE IF EXISTS `study_card`;
CREATE TABLE `study_card` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `card_title` varchar(100) DEFAULT NULL COMMENT '学习卡主题',
  `card_no` varchar(36) NOT NULL COMMENT '卡号',
  `card_psw` varchar(36) DEFAULT NULL COMMENT '卡密',
  `is_limit_course` int(1) DEFAULT '0' COMMENT '使用课程限制，0不限制，1限制课程',
  `card_status` int(11) DEFAULT '0' COMMENT '卡消费状态，0未做任何操作，1已经绑定用户，2已经消费使用',
  `study_card_time_begin` datetime DEFAULT NULL COMMENT '有效期开始',
  `study_card_time_end` datetime DEFAULT NULL COMMENT '有效期结束',
  `bind_user_no` varchar(36) DEFAULT NULL COMMENT '绑定用户编号',
  `total_face_value_amount` decimal(11,2) DEFAULT '0.00' COMMENT '面值金额',
  `used_face_value_amount` decimal(11,2) DEFAULT '0.00' COMMENT '已经使用面额',
  `frozen_face_value_amount` decimal(11,2) DEFAULT '0.00' COMMENT '被冻结的面额(例如下了订单尚未支付)',
  `bind_user_time` datetime DEFAULT NULL COMMENT '用户绑定的时间',
  `days_from_bind_user_time` int(11) DEFAULT '0' COMMENT '从绑定用户的时间(bind_user_time)开始算(days_from_bind_user_time)',
  `value_point` int(11) DEFAULT '0' COMMENT '需抵扣积分额',
  `card_desc` varchar(200) DEFAULT NULL COMMENT '学习卡描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学习卡';

-- ----------------------------
--  Table structure for `study_card_limit_course`
-- ----------------------------
DROP TABLE IF EXISTS `study_card_limit_course`;
CREATE TABLE `study_card_limit_course` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `study_card_id` varchar(36) NOT NULL COMMENT '学习卡ID',
  `course_id` varchar(36) NOT NULL COMMENT '课程ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='对于有使用范围的学习卡的学习课程';

-- ----------------------------
--  Table structure for `study_card_operate_record`
-- ----------------------------
DROP TABLE IF EXISTS `study_card_operate_record`;
CREATE TABLE `study_card_operate_record` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `user_no` varchar(36) NOT NULL COMMENT '用户编号',
  `operate_type` int(3) NOT NULL COMMENT '操作类型，0增加，1减少',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学习卡操作记录';

-- ----------------------------
--  Table structure for `study_proof`
-- ----------------------------
DROP TABLE IF EXISTS `study_proof`;
CREATE TABLE `study_proof` (
  `id` varchar(36) NOT NULL COMMENT 'id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `proof_user_no` varchar(36) NOT NULL COMMENT '该证明的用户编号',
  `real_name` varchar(50) NOT NULL COMMENT '用户真实姓名',
  `sex` tinyint(3) DEFAULT NULL COMMENT '性别(1男，2女，3保密)',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `mobile` varchar(36) DEFAULT '' COMMENT '手机号码',
  `identity_card_no` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `proof_date` datetime DEFAULT NULL COMMENT '证明日期',
  `main_seal_content` varchar(20) NOT NULL COMMENT '主印章名称(大圆的那个印章名称)',
  `second_seal_content` varchar(20) NOT NULL COMMENT '印章内横着的那行字内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学习证明';

-- ----------------------------
--  Records of `study_proof`
-- ----------------------------
BEGIN;
INSERT INTO `study_proof` VALUES ('03ebfba9-d1ba-4cf2-aaf7-588ddf04c0a8', '2021-01-27 11:00:45', '2021-01-27 11:00:45', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-01-27 11:00:46', '浪树学习系统', '学习证明章'), ('123d7323-fc3c-4f18-9853-48f0f988d8ea', '2021-01-26 23:05:43', '2021-01-26 23:05:43', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-01-26 23:05:44', '浪树学习系统', '学习证明章'), ('1e08c3ee-efcc-49a4-b3b5-7aba8ac1338c', '2021-01-27 11:00:20', '2021-01-27 11:00:20', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-01-27 11:00:21', '浪树学习系统', '学习证明章'), ('1f52396c-cf8f-49f6-9d40-07f49a47c5d6', '2021-01-26 22:44:33', '2021-01-26 22:44:33', '1', '0', '2018112015051635', '李雪书分飞虎66', '3', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '0', '2021-01-26 22:44:33', '浪树学习系统', '学习证明章'), ('26f168e2-762b-4e91-8a68-d406a3e0a862', '2021-03-25 19:48:04', '2021-03-25 19:48:04', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/ba27e454198a4b409f8e4a9adaef4b25.jpg', '13800138001', '4409861918009100000', '20', '2021-03-25 19:48:05', '浪树学习系统', '学习证明章'), ('33320925-f380-4d5e-a19c-c7177edffea7', '2021-01-26 23:04:25', '2021-01-26 23:04:25', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-01-26 23:04:25', '浪树学习系统', '学习证明章'), ('50ea2a80-0942-4458-baeb-6e089de7f186', '2021-01-26 23:05:48', '2021-01-26 23:05:48', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-01-26 23:05:48', '浪树学习系统', '学习证明章'), ('57f45387-1788-41dd-91e2-8c6d57aebeb7', '2021-02-14 21:45:22', '2021-02-14 21:45:22', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-02-14 21:45:22', '浪树学习系统', '学习证明章'), ('6b6fd66e-16d0-4855-8df3-02a7b2badbb1', '2021-01-26 22:45:24', '2021-01-26 22:45:24', '1', '0', '2018112015051635', '李雪书分飞虎66', '3', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '0', '2021-01-26 22:45:25', '浪树学习系统', '学习证明章'), ('78f3c1a5-10ac-49cb-9a5f-ca0d8978700d', '2021-02-14 21:45:12', '2021-02-14 21:45:12', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-02-14 21:45:13', '浪树学习系统', '学习证明章'), ('89c1eeb0-fbac-4599-87ce-0bdf45d50358', '2021-01-26 22:57:38', '2021-01-26 22:57:38', '1', '0', '2018112015051635', '李雪书分飞虎66', '3', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '0', '2021-01-26 22:57:38', '浪树学习系统', '学习证明章'), ('9fc974b1-02d7-4244-87ba-1636afe7a8a4', '2021-01-26 22:56:12', '2021-01-26 22:56:12', '1', '0', '2018112015051635', '李雪书分飞虎66', '3', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '0', '2021-01-26 22:56:13', '浪树学习系统', '学习证明章'), ('a7e4eb75-d7c2-4236-843c-80cc4b837f85', '2021-01-26 23:05:43', '2021-01-26 23:05:43', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-01-26 23:05:44', '浪树学习系统', '学习证明章'), ('aca0b582-963f-417b-9249-e79552b9738f', '2021-01-26 22:58:04', '2021-01-26 22:58:04', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-01-26 22:58:04', '浪树学习系统', '学习证明章'), ('ad15918e-c214-4971-8d2d-2c4f84d65685', '2021-01-27 10:58:15', '2021-01-27 10:58:15', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-01-27 10:58:16', '浪树学习系统', '学习证明章'), ('b58b395b-b556-4058-815d-34dc82ff47ff', '2021-02-14 21:45:26', '2021-02-14 21:45:26', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-02-14 21:45:27', '浪树学习系统', '学习证明章'), ('bec381b6-c244-4ab9-bf9a-b0bd4719cc37', '2021-01-26 22:57:27', '2021-01-26 22:57:27', '1', '0', '2018112015051635', '李雪书分飞虎66', '3', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '0', '2021-01-26 22:57:28', '浪树学习系统', '学习证明章'), ('e84cc369-6b6c-43e0-b739-1bf29d0f6af0', '2021-01-26 23:02:34', '2021-01-26 23:02:34', '1', '0', '2018112015051635', '李雪书分飞虎66', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '20', '2021-01-26 23:02:35', '浪树学习系统', '学习证明章'), ('f92015e4-c88d-45dc-991a-236df0285367', '2021-01-26 21:42:52', '2021-01-26 21:42:52', '1', '0', '2018112015051635', '李雪书分飞虎66', '3', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/2a605c027ccf4dac9eb71308c5efbc6b.jpg', '13800138001', '4409861918009100000', '0', '2021-01-26 21:42:53', '浪树学习系统', '学习证明章');
COMMIT;

-- ----------------------------
--  Table structure for `study_proof_course`
-- ----------------------------
DROP TABLE IF EXISTS `study_proof_course`;
CREATE TABLE `study_proof_course` (
  `id` varchar(36) NOT NULL COMMENT 'id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `study_proof_id` varchar(36) NOT NULL COMMENT '学习证明ID',
  `course_id` varchar(36) NOT NULL COMMENT '课程ID',
  `course_name` varchar(255) NOT NULL COMMENT '课程名称',
  `total_study_progress` int(11) NOT NULL DEFAULT '0' COMMENT '该课程的总学习进度(0表示0%，1表示1%，2表示2%......100表示100%)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学习证明对应的课程';

-- ----------------------------
--  Records of `study_proof_course`
-- ----------------------------
BEGIN;
INSERT INTO `study_proof_course` VALUES ('01a8be61-88e9-47a6-ad3c-b17e0c256267', '2021-02-14 21:45:22', '2021-02-14 21:45:22', '1', '0', '57f45387-1788-41dd-91e2-8c6d57aebeb7', '1080756535567257601', '后台管理系统', '12'), ('021db781-d6ea-415f-8754-ff9f26785aac', '2021-01-27 11:00:45', '2021-01-27 11:00:45', '1', '0', '03ebfba9-d1ba-4cf2-aaf7-588ddf04c0a8', '1080758482462511106', '运维平台安装使用', '0'), ('06885974-13aa-4867-ae1c-e472568a62fe', '2021-01-26 23:02:34', '2021-01-26 23:02:34', '1', '0', 'e84cc369-6b6c-43e0-b739-1bf29d0f6af0', '1080758482462511106', '运维平台安装使用', '0'), ('14670564-66b9-434b-9b2d-e87a9611ecf2', '2021-01-27 11:00:45', '2021-01-27 11:00:45', '1', '0', '03ebfba9-d1ba-4cf2-aaf7-588ddf04c0a8', '1085453180200448002', '测试下单课程sflsdfjklsdfwjkekjwefjkw长度豆腐块水电费水电费', '12'), ('1a99ca30-99c0-4ac2-b2fc-7be40cf7eae9', '2021-01-26 23:02:34', '2021-01-26 23:02:34', '1', '0', 'e84cc369-6b6c-43e0-b739-1bf29d0f6af0', '1085453180200448002', '测试下单课程sflsdfjklsdfwjkekjwefjkw长度豆腐块水电费水电费', '12'), ('1ab69516-e32c-46ca-a3f3-bc9c8f2444db', '2021-01-26 23:05:48', '2021-01-26 23:05:48', '1', '0', '50ea2a80-0942-4458-baeb-6e089de7f186', '1085453180200448002', '测试下单课程sflsdfjklsdfwjkekjwefjkw长度豆腐块水电费水电费', '12'), ('25e92554-e563-4617-822f-71dccf520597', '2021-01-26 23:02:34', '2021-01-26 23:02:34', '1', '0', 'e84cc369-6b6c-43e0-b739-1bf29d0f6af0', '1080756535567257601', '后台管理系统', '12'), ('2b92138c-f471-4ed1-82fd-461289e457ab', '2021-01-26 21:42:52', '2021-01-26 21:42:52', '1', '0', 'f92015e4-c88d-45dc-991a-236df0285367', '1080756535567257601', '后台管理系统', '12'), ('2caf26b1-8744-4384-9ff2-0014acda51ef', '2021-01-26 22:56:12', '2021-01-26 22:56:12', '1', '0', '9fc974b1-02d7-4244-87ba-1636afe7a8a4', '1080758482462511106', '运维平台安装使用', '0'), ('2dfaf867-34b7-4ce8-8b97-ff30ef230b67', '2021-01-26 22:57:38', '2021-01-26 22:57:38', '1', '0', '89c1eeb0-fbac-4599-87ce-0bdf45d50358', '1232249370041163778', '1', '0'), ('2f3d0ad9-d5fc-4c62-be4f-96f54d58b4e9', '2021-01-26 22:57:38', '2021-01-26 22:57:38', '1', '0', '89c1eeb0-fbac-4599-87ce-0bdf45d50358', '1080756535567257601', '后台管理系统', '12'), ('301db817-e107-4bbf-9d2c-25ace4232f16', '2021-01-27 10:58:15', '2021-01-27 10:58:15', '1', '0', 'ad15918e-c214-4971-8d2d-2c4f84d65685', '1232249370041163778', '1', '0'), ('3044c612-9325-43ef-9fb8-1c53c91d68a1', '2021-01-26 22:45:24', '2021-01-26 22:45:24', '1', '0', '6b6fd66e-16d0-4855-8df3-02a7b2badbb1', '1080756535567257601', '后台管理系统', '12'), ('31dfb586-cd5a-49e5-9577-d8812b33e715', '2021-01-26 22:57:27', '2021-01-26 22:57:27', '1', '0', 'bec381b6-c244-4ab9-bf9a-b0bd4719cc37', '1080758482462511106', '运维平台安装使用', '0'), ('3bf0f7d3-7172-44d3-9a77-1b4dd4f94d6f', '2021-01-26 22:56:12', '2021-01-26 22:56:12', '1', '0', '9fc974b1-02d7-4244-87ba-1636afe7a8a4', '1080756535567257601', '后台管理系统', '12'), ('3cf03586-9827-47ed-af9c-474ea4b22e72', '2021-02-14 21:45:22', '2021-02-14 21:45:22', '1', '0', '57f45387-1788-41dd-91e2-8c6d57aebeb7', '1232249370041163778', '1', '0'), ('41631f33-7ee7-4a85-acf8-321ba9863a71', '2021-01-26 23:05:43', '2021-01-26 23:05:43', '1', '0', 'a7e4eb75-d7c2-4236-843c-80cc4b837f85', '1085453180200448002', '测试下单课程sflsdfjklsdfwjkekjwefjkw长度豆腐块水电费水电费', '12'), ('528661cc-810a-4e4d-815b-ec4988caea4b', '2021-01-27 11:00:45', '2021-01-27 11:00:45', '1', '0', '03ebfba9-d1ba-4cf2-aaf7-588ddf04c0a8', '1232249370041163778', '1', '0'), ('61633767-44b1-4114-be7c-b08f228845dd', '2021-01-27 10:58:15', '2021-01-27 10:58:15', '1', '0', 'ad15918e-c214-4971-8d2d-2c4f84d65685', '1080758482462511106', '运维平台安装使用', '0'), ('76b2b888-0539-4735-aab0-97115aff1d86', '2021-01-26 23:05:43', '2021-01-26 23:05:43', '1', '0', '123d7323-fc3c-4f18-9853-48f0f988d8ea', '1080758482462511106', '运维平台安装使用', '0'), ('8489b9b7-85c7-45d7-87d1-0b8b49addd58', '2021-01-26 22:44:33', '2021-01-26 22:44:33', '1', '0', '1f52396c-cf8f-49f6-9d40-07f49a47c5d6', '1080756535567257601', '后台管理系统', '12'), ('88664882-4bdf-428c-9de7-deaf0cfe3932', '2021-01-26 23:05:43', '2021-01-26 23:05:43', '1', '0', '123d7323-fc3c-4f18-9853-48f0f988d8ea', '1085453180200448002', '测试下单课程sflsdfjklsdfwjkekjwefjkw长度豆腐块水电费水电费', '12'), ('8be04a5f-5519-42bf-95f4-fae985ceab53', '2021-01-26 22:57:27', '2021-01-26 22:57:27', '1', '0', 'bec381b6-c244-4ab9-bf9a-b0bd4719cc37', '1232249370041163778', '1', '0'), ('8fe61580-195c-44c0-8d77-6bc1427808bc', '2021-02-14 21:45:26', '2021-02-14 21:45:26', '1', '0', 'b58b395b-b556-4058-815d-34dc82ff47ff', '1232249370041163778', '1', '0'), ('9788484f-4061-466d-a723-9fb0637b9e7c', '2021-01-26 22:56:12', '2021-01-26 22:56:12', '1', '0', '9fc974b1-02d7-4244-87ba-1636afe7a8a4', '1085453180200448002', '测试下单课程sflsdfjklsdfwjkekjwefjkw长度豆腐块水电费水电费', '12'), ('9a5267b0-748b-4128-ba19-1fcd581a8478', '2021-01-26 22:56:12', '2021-01-26 22:56:12', '1', '0', '9fc974b1-02d7-4244-87ba-1636afe7a8a4', '1232249370041163778', '1', '0'), ('9a79e82d-b53b-4672-9dbb-a52c06e024e6', '2021-02-14 21:45:12', '2021-02-14 21:45:12', '1', '0', '78f3c1a5-10ac-49cb-9a5f-ca0d8978700d', '1080756535567257601', '后台管理系统', '12'), ('a562fb67-2eec-4ef6-9ec1-654fbd29b376', '2021-01-27 11:00:20', '2021-01-27 11:00:20', '1', '0', '1e08c3ee-efcc-49a4-b3b5-7aba8ac1338c', '1085453180200448002', '测试下单课程sflsdfjklsdfwjkekjwefjkw长度豆腐块水电费水电费', '12'), ('a728976a-c94c-4eea-920c-9eb041931975', '2021-01-27 10:58:15', '2021-01-27 10:58:15', '1', '0', 'ad15918e-c214-4971-8d2d-2c4f84d65685', '1085453180200448002', '测试下单课程sflsdfjklsdfwjkekjwefjkw长度豆腐块水电费水电费', '12'), ('a9508fab-6b21-49c0-9f67-a5ab654c89a4', '2021-03-25 19:48:04', '2021-03-25 19:48:04', '1', '0', '26f168e2-762b-4e91-8a68-d406a3e0a862', 'b3794873ad97467cb6e8cab54c8f09a2', 'kkkttt', '0'), ('ae286cc0-bf42-40a2-85bc-cc47944fa2e7', '2021-01-26 22:58:04', '2021-01-26 22:58:04', '1', '0', 'aca0b582-963f-417b-9249-e79552b9738f', '1080756535567257601', '后台管理系统', '12'), ('bd116ddb-2adb-4297-babf-6dee3ed42ff6', '2021-01-27 11:00:20', '2021-01-27 11:00:20', '1', '0', '1e08c3ee-efcc-49a4-b3b5-7aba8ac1338c', '1232249370041163778', '1', '0'), ('be213a1a-45c8-4f35-8e4f-1e8ccf734448', '2021-01-26 23:04:25', '2021-01-26 23:04:25', '1', '0', '33320925-f380-4d5e-a19c-c7177edffea7', '1085453180200448002', '测试下单课程sflsdfjklsdfwjkekjwefjkw长度豆腐块水电费水电费', '12'), ('c1ccf482-567c-45a1-a7b1-3c25a97e3ab5', '2021-01-26 22:57:38', '2021-01-26 22:57:38', '1', '0', '89c1eeb0-fbac-4599-87ce-0bdf45d50358', '1080758482462511106', '运维平台安装使用', '0'), ('c864b32a-472c-4f42-87e0-c9921fed0f9d', '2021-02-14 21:45:26', '2021-02-14 21:45:26', '1', '0', 'b58b395b-b556-4058-815d-34dc82ff47ff', '1080758482462511106', '运维平台安装使用', '0'), ('cbba69f1-199d-4f21-a106-25a9a73b8796', '2021-01-26 23:04:25', '2021-01-26 23:04:25', '1', '0', '33320925-f380-4d5e-a19c-c7177edffea7', '1080758482462511106', '运维平台安装使用', '0'), ('cc5e6cdf-f842-408a-bcb1-be6999288bdd', '2021-01-26 22:45:24', '2021-01-26 22:45:24', '1', '0', '6b6fd66e-16d0-4855-8df3-02a7b2badbb1', '1080758482462511106', '运维平台安装使用', '0'), ('ce193f7d-2263-4cd3-8ca5-ac29f81ab7b4', '2021-01-26 23:02:34', '2021-01-26 23:02:34', '1', '0', 'e84cc369-6b6c-43e0-b739-1bf29d0f6af0', '1232249370041163778', '1', '0'), ('ce43c58c-20db-4fcb-af11-5f5d1804ea3b', '2021-01-26 23:05:43', '2021-01-26 23:05:43', '1', '0', 'a7e4eb75-d7c2-4236-843c-80cc4b837f85', '1080758482462511106', '运维平台安装使用', '0'), ('ce81f2ac-7842-441f-8524-37ea289f1bfa', '2021-01-26 22:44:33', '2021-01-26 22:44:33', '1', '0', '1f52396c-cf8f-49f6-9d40-07f49a47c5d6', '1080758482462511106', '运维平台安装使用', '0'), ('d3cc69be-14f2-428d-869b-fa6c41d89529', '2021-01-26 21:42:52', '2021-01-26 21:42:52', '1', '0', 'f92015e4-c88d-45dc-991a-236df0285367', '1080758482462511106', '运维平台安装使用', '0'), ('d63e0f1f-ca8c-43b6-8360-be0fa53e6277', '2021-01-26 22:57:27', '2021-01-26 22:57:27', '1', '0', 'bec381b6-c244-4ab9-bf9a-b0bd4719cc37', '1080756535567257601', '后台管理系统', '12'), ('dceb5983-e9f1-4804-b053-909513f21b3c', '2021-01-26 22:57:38', '2021-01-26 22:57:38', '1', '0', '89c1eeb0-fbac-4599-87ce-0bdf45d50358', '1085453180200448002', '测试下单课程sflsdfjklsdfwjkekjwefjkw长度豆腐块水电费水电费', '12'), ('e05521e6-8718-4a66-8acc-a52167f48de4', '2021-01-27 11:00:20', '2021-01-27 11:00:20', '1', '0', '1e08c3ee-efcc-49a4-b3b5-7aba8ac1338c', '1080758482462511106', '运维平台安装使用', '0'), ('e41a2583-0524-408a-8702-96c084d29c64', '2021-01-26 23:05:48', '2021-01-26 23:05:48', '1', '0', '50ea2a80-0942-4458-baeb-6e089de7f186', '1080758482462511106', '运维平台安装使用', '0'), ('e7a28cf7-16c1-4cdc-a817-df4929b7e473', '2021-01-26 22:58:04', '2021-01-26 22:58:04', '1', '0', 'aca0b582-963f-417b-9249-e79552b9738f', '1080758482462511106', '运维平台安装使用', '0'), ('e7a44530-7ead-44a6-acd8-ff62e7362d39', '2021-01-26 22:58:04', '2021-01-26 22:58:04', '1', '0', 'aca0b582-963f-417b-9249-e79552b9738f', '1232249370041163778', '1', '0'), ('f60f7bcb-51eb-4fc1-89f1-c84ea944a794', '2021-02-14 21:45:26', '2021-02-14 21:45:26', '1', '0', 'b58b395b-b556-4058-815d-34dc82ff47ff', '1080756535567257601', '后台管理系统', '12');
COMMIT;

-- ----------------------------
--  Table structure for `system_config`
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `id` varchar(36) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `valid_value` tinyint(1) DEFAULT NULL COMMENT '状态(1有效, 0无效)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `aliyun_access_key_id` varchar(255) DEFAULT NULL COMMENT 'access_key_id',
  `aliyun_access_key_secret` varchar(255) DEFAULT NULL COMMENT 'access_key_secret',
  `aliyun_oss_bucket_url` varchar(255) DEFAULT NULL COMMENT 'bucket域名',
  `aliyun_oss_bucket` varchar(255) DEFAULT NULL COMMENT 'bucket',
  `aliyun_oss_endpoint` varchar(255) DEFAULT NULL COMMENT '地域节点',
  `aliyun_oas_vault` varchar(255) DEFAULT NULL COMMENT 'oas_vault',
  `aliyun_oas_url` varchar(255) DEFAULT NULL COMMENT '归档存储OAS',
  `sms_code` varchar(255) DEFAULT NULL COMMENT '模版CODE',
  `sign_name` varchar(255) DEFAULT NULL COMMENT '短信签名',
  `lesson_video_path` varchar(255) DEFAULT NULL COMMENT '本地视频存放路径',
  `doc_storage_path` varchar(255) DEFAULT NULL COMMENT '本地上传文档路径',
  `pic_storage_path` varchar(255) DEFAULT NULL COMMENT '本地上传图片路径',
  `video_type` tinyint(1) DEFAULT '1' COMMENT '视频存储平台（1阿里云，3本地[本地未用]）',
  `file_type` tinyint(4) DEFAULT '1' COMMENT '文件存储类型（1阿里云，3本地[本地未用]）',
  `is_open_zfb_pc` tinyint(3) DEFAULT '0' COMMENT '是否开启PC端支付宝支付，0关闭，1开启',
  `zfb_pc_app_id` varchar(500) DEFAULT NULL COMMENT '网站(签约:当面付)支付APPID',
  `zfb_pc_subject` varchar(100) NOT NULL COMMENT '消费者扫码支付的时候显示的订单信息',
  `zfb_pc_private_key` text COMMENT '网站(签约:当面付)支付私钥',
  `zfb_pc_alipay_public_key` text COMMENT '网站(签约:当面付)支付"支付宝公钥"(非应用公钥)',
  `zfb_pc_open_api_domain` varchar(500) DEFAULT NULL COMMENT '网站(签约:当面付)支付网关',
  `is_open_zfb_mobile` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否开启移动端支付宝支付，0关闭，1开启',
  `zfb_return_url` varchar(500) DEFAULT NULL,
  `is_open_weixinpay_pc` tinyint(3) DEFAULT '0' COMMENT '是否开启PC端微信支付，0关闭，1开启',
  `is_open_weixinpay_mobile` tinyint(3) DEFAULT '0' COMMENT '是否开启移动端微信支付，0关闭，1开启',
  `is_open_weixinpay_xiaochengxu` tinyint(4) DEFAULT '0' COMMENT '是否开启小程序端微信支付，0关闭，1开启',
  `login_log_retention_days` int(11) DEFAULT NULL COMMENT '登录日志保留天数',
  `operate_log_retention_days` int(11) DEFAULT NULL COMMENT '操作日志保留天数',
  `is_open_do_exercises_data_sync` tinyint(3) DEFAULT '0' COMMENT '是否开启刷题多端数据同步，开启为1，不开启为0',
  PRIMARY KEY (`id`,`is_open_zfb_mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

-- ----------------------------
--  Records of `system_config`
-- ----------------------------
BEGIN;
INSERT INTO `system_config` VALUES ('a4f5fd80-59e6-44a5-a97b-44148b56a3ce', '2020-07-29 20:05:57', '2021-06-05 19:31:36', '1', '1', '', '', '', '', '', '', '', '', '', '/Users/shenge/work/workspaces/workspace-wangxin/upload/wangxin/file/video/', '/Users/shenge/work/workspaces/workspace-wangxin/upload/wangxin/doc/', '/Users/shenge/work/workspaces/workspace-wangxin/upload/wangxin/pic/', '1', '1', '1', '', '', '', '', '', '1', '', '0', '0', '0', '180', '30', '1');
COMMIT;

-- ----------------------------
--  Table structure for `system_menu`
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `parent_id` varchar(36) NOT NULL COMMENT '父ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(255) DEFAULT '' COMMENT '路由路径',
  `api_url` varchar(255) DEFAULT '' COMMENT '接口URL',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `menu_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '菜单类型(1：目录   2：菜单   3：按钮)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='菜单信息';

-- ----------------------------
--  Records of `system_menu`
-- ----------------------------
BEGIN;
INSERT INTO `system_menu` VALUES ('01600c998f4040c5926cadeeee525acb', '2020-08-22 17:34:14', '2020-08-22 17:34:14', '1', '1', '6eadb897cf7b42bca206cf119820b3bb', '学习卡', '/market/studycard/pc', '/pcboss/market/studycard/list', null, null, '2'), ('03c21615ccea423b851a552124929efa', '2020-08-28 22:15:53', '2020-08-28 22:15:53', '1', '1', '3978e74ba30b4cdaa556b7d7a582e61b', '优惠券分页列表接口', '', '/pcboss/market/coupon/list', null, null, '3'), ('05173427af9e4e83b1d8357fe23e606e', '2020-11-18 16:10:21', '2020-11-18 16:10:21', '1', '3', '1153498794638020609', '登录日志', '/system/log/loginLog', '/pcboss/system/loginLog/list', null, '登录日志', '2'), ('0a538193f43c438d9405ddc624f8457e', '2020-08-28 22:14:35', '2020-08-28 22:14:35', '1', '1', '01600c998f4040c5926cadeeee525acb', '学习卡查看接口', '', '/pcboss/market/studycard/view', null, null, '3'), ('0eed407104cc47efbf31182b497f22a8', '2020-09-01 18:25:48', '2020-09-01 18:25:48', '1', '1', '308e04eb3f394322834652db61474164', '更新积点配置', '', '/pcboss/market/dot/config/update', null, null, '3'), ('1153478139284656129', '2019-07-23 09:33:11', '2019-07-23 09:33:11', '1', '9', '0', '订单管理', 'order', '', null, '订单管理', '1'), ('1153478416205189122', '2019-07-23 09:34:17', '2019-07-23 09:34:17', '1', '7', '0', '首页管理', 'homepage', '', null, '首页管理', '1'), ('1153478559176429569', '2019-07-23 09:34:51', '2019-07-23 09:34:51', '1', '6', '0', '课程管理', 'course', '', null, '课程管理', '1'), ('1153478694937661442', '2019-07-23 09:35:23', '2019-07-23 09:35:23', '1', '5', '0', '教师管理', 'lecturer', '', null, '讲师管理', '1'), ('1153478801917579265', '2019-07-23 09:35:49', '2019-07-23 09:35:49', '1', '4', '0', '学生管理', 'user', '', null, '学员管理', '1'), ('1153478911053369345', '2019-07-23 09:36:15', '2019-07-23 09:36:15', '1', '1', '0', '系统管理', 'sys', '', null, '系统管理111', '1'), ('1153493592497295362', '2019-07-23 10:34:35', '2019-07-23 10:34:35', '1', '3', '1153478416205189122', '首页轮播', '/advert', '', null, '首页轮播', '1'), ('1153493835884367873', '2019-07-23 10:35:33', '2019-07-23 10:35:33', '1', '1', '1153493592497295362', '首页广告设置', '/homepage/advert/pc', '/pcboss/course/advert/list', null, '首页广告设置', '2'), ('1153494219218587650', '2019-07-23 10:37:05', '2019-07-23 10:37:05', '1', '2', '1153478416205189122', '首页专区管理', '/topic', '', null, '专区管理', '1'), ('1153494438295474177', '2019-07-23 10:37:57', '2019-07-23 10:37:57', '1', '1', '1153494219218587650', '专区设置', '/homepage/topic/pc', '/pcboss/course/topic/list', null, 'PC端设置', '2'), ('1153494698514288641', '2019-07-23 10:38:59', '2019-07-23 10:38:59', '1', '1', '1153478416205189122', '导航管理', '/website', '', null, '站点管理', '1'), ('1153494935626682369', '2019-07-23 10:39:56', '2019-07-23 10:39:56', '1', '3', '1153494698514288641', '头部导航', '/homepage/website/navigationBar', '/pcboss/system/fresh/navigation/bar/list', null, '头部导航列表接口', '2'), ('1153495031810461697', '2019-07-23 10:40:18', '2019-07-23 10:40:18', '1', '2', '1153494698514288641', '底部导航', '/homepage/website/bottomNavigation', '/pcboss/system/website/navigation/list', null, '底部导航', '2'), ('1153495155055890433', '2019-07-23 10:40:48', '2019-07-23 10:40:48', '1', '1', '1153494698514288641', '友情链接', '/homepage/website/friendLink', '/pcboss/system/website/link/list', null, '友情链接', '2'), ('1153495582782623746', '2019-07-23 10:42:30', '2019-07-23 10:42:30', '1', '2', '1153478559176429569', '课程管理', '/course', '', null, '课程管理', '1'), ('1153495768179249153', '2019-07-23 10:43:14', '2019-07-23 10:43:14', '1', '1', '1153478559176429569', '分类管理', '/subject', '', null, '分类管理', '1'), ('1153495948102307842', '2019-07-23 10:43:57', '2019-07-23 10:43:57', '1', '2', '1153495582782623746', '课程列表', '/course/course/course', '/pcboss/course/fresh/course/list', null, '课程列表', '2'), ('1153496094022144002', '2019-07-23 10:44:32', '2019-07-23 10:44:32', '1', '1', '1153495582782623746', '课程审核列表', '/course/course/apply', '/pcboss/course/fresh/course/apply/list', null, '课程发布列表', '2'), ('1153496241066053634', '2019-07-23 10:45:07', '2019-07-23 10:45:07', '1', '1', '1153495768179249153', '分类列表', '/course/subject/subject', '/pcboss/course/course/subject/list', null, '分类列表', '2'), ('1153496406875279362', '2019-07-23 10:45:46', '2019-07-23 10:45:46', '1', '2', '1153478694937661442', '教师管理', '/teacher', '', null, '讲师管理', '1'), ('1153496795896975361', '2019-07-23 10:47:19', '2019-07-23 10:47:19', '1', '2', '1153496406875279362', '教师列表', '/teacher/teacher/teacher', '/pcboss/user/teacher/list', null, '教师列表', '2'), ('1153497106191585282', '2019-07-23 10:48:33', '2019-07-23 10:48:33', '1', '1', '1153496406875279362', '教师审核列表', '/teacher/teacher/apply', '/pcboss/user/teacher/audit/list', null, '讲师审核列表', '2'), ('1153498121905213442', '2019-07-23 10:52:35', '2019-07-23 10:52:35', '1', '5', '1153478911053369345', '权限管理', '/pms', '', null, '权限管理', '1'), ('1153498480237187073', '2019-07-23 10:54:01', '2019-07-23 10:54:01', '1', '3', '1153478911053369345', '站点管理', '/website', '', null, '站点管理', '1'), ('1153498794638020609', '2019-07-23 10:55:16', '2019-07-23 10:55:16', '1', '1', '1153478911053369345', '日志查看', '/log', '', null, '日志查看', '1'), ('1153498940276838401', '2019-07-23 10:55:50', '2019-07-23 10:55:50', '1', '3', '1153498121905213442', '用户管理', '/system/pms/user', '/pcboss/system/system/user/list', null, '用户管理', '2'), ('1153499292782923778', '2019-07-23 10:57:14', '2019-07-23 10:57:14', '1', '2', '1153498121905213442', '角色管理', '/system/pms/role', '/pcboss/system/system/role/list', null, '角色管理', '2'), ('1153499423880089601', '2019-07-23 10:57:46', '2019-07-23 10:57:46', '1', '1', '1153498121905213442', '菜单管理', '/system/pms/menu', '/pcboss/system/menu/list', null, '菜单管理', '2'), ('1153500580878848001', '2019-07-23 11:02:21', '2019-07-23 11:02:21', '1', '5', '1153498480237187073', '站点设置', '/system/website/website', '/pcboss/system/fresh/website/view', null, '站点设置', '2'), ('1156030262547324930', '2019-07-30 10:34:26', '2019-07-30 10:34:26', '1', '1', '1153493835884367873', '添加', '', '/pcboss/course/advert/add', null, '跳添加页面弹窗', '3'), ('1156030361037971458', '2019-07-30 10:34:49', '2019-07-30 10:34:49', '1', '1', '1153493835884367873', '保存', '', '/pcboss/course/advert/save', null, '保存接口', '3'), ('1156030992003899394', '2019-07-30 10:37:20', '2019-07-30 10:37:20', '1', '1', '1153493835884367873', '更新', '', '/pcboss/course/advert/update', null, '更新接口', '3'), ('1156032108988997634', '2019-07-30 10:41:46', '2019-07-30 10:41:46', '1', '1', '1153494438295474177', '添加', '', '/pcboss/course/topic/add', null, '添加页面', '3'), ('1156032296088510465', '2019-07-30 10:42:31', '2019-07-30 10:42:31', '1', '1', '1153494438295474177', '保存', '', '/pcboss/course/topic/save', null, '保存接口', '3'), ('1156032474489036801', '2019-07-30 10:43:13', '2019-07-30 10:43:13', '1', '1', '1153494438295474177', '删除', '', '/pcboss/course/topic/delete', null, '删除', '3'), ('1156033449756991490', '2019-07-30 10:47:06', '2019-07-30 10:47:06', '1', '1', '1153494438295474177', '修改', '', '/pcboss/course/topic/view', null, '修改页面', '3'), ('1156033853009960961', '2019-07-30 10:48:42', '2019-07-30 10:48:42', '1', '1', '1153494438295474177', '更新', '', '/pcboss/course/topic/update', null, '更新接口', '3'), ('1156035023040421889', '2019-07-30 10:53:21', '2019-07-30 10:53:21', '1', '1', '1153494438295474177', '专区课程', null, '/pcboss/course/topic/course/list', null, '专区课程新窗口打开', '3'), ('1156035443800416257', '2019-07-30 10:55:01', '2019-07-30 10:55:01', '1', '1', '1156035023040421889', '添加', '', '/pcboss/course/course/list', null, '添加课程列出接口', '3'), ('1156035689204948994', '2019-07-30 10:56:00', '2019-07-30 10:56:00', '1', '1', '1156035023040421889', '保存', '', '/pcboss/course/topic/course/save', null, '保存添加', '3'), ('1156036045850812417', '2019-07-30 10:57:25', '2019-07-30 10:57:25', '1', '1', '1156035023040421889', '删除', '', '/pcboss/course/topic/course/delete', null, '专区课程删除接口', '3'), ('1156036539059019777', '2019-07-30 10:59:22', '2019-07-30 10:59:22', '1', '1', '1156035023040421889', '修改', '', '/pcboss/course/topic/course/edit', null, '修改弹窗页面', '3'), ('1156036653299277825', '2019-07-30 10:59:49', '2019-07-30 10:59:49', '1', '1', '1156035023040421889', '更新', '', '/pcboss/course/topic/course/update', null, '专区课程接口', '3'), ('1156036831607529473', '2019-07-30 11:00:32', '2019-07-30 11:00:32', '1', '1', '1153493835884367873', '修改', '', '/pcboss/course/advert/edit', null, '修改页面弹窗', '3'), ('1156037020263129089', '2019-07-30 11:01:17', '2019-07-30 11:01:17', '1', '1', '1153493835884367873', '删除', '', '/pcboss/course/advert/delete', null, '删除接口', '3'), ('1156039623340466178', '2019-07-30 11:11:38', '2019-07-30 11:11:38', '1', '1', '1153494935626682369', '添加', '', '/pcboss/system/fresh/navigation/bar/add', null, '添加弹窗', '3'), ('1156039959035781122', '2019-07-30 11:12:58', '2019-07-30 11:12:58', '1', '1', '1153494935626682369', '保存', '', '/pcboss/system/fresh/navigation/bar/save', null, '保存接口', '3'), ('1156040289144283137', '2019-07-30 11:14:16', '2019-07-30 11:14:16', '1', '1', '1153494935626682369', '删除', '', '/pcboss/system/fresh/navigation/bar/delete', null, '删除接口', '3'), ('1156040630476742658', '2019-07-30 11:15:38', '2019-07-30 11:15:38', '1', '1', '1153494935626682369', '修改', '', '/pcboss/system/fresh/navigation/bar/edit', null, '修改弹窗', '3'), ('1156040868612546562', '2019-07-30 11:16:34', '2019-07-30 11:16:34', '1', '1', '1153494935626682369', '更新', '', '/pcboss/system/fresh/navigation/bar/update', null, '更新接口', '3'), ('1156041685298061313', '2019-07-30 11:19:49', '2019-07-30 11:19:49', '1', '7', '1153495031810461697', '添加', '', '/pcboss/system/website/navigation/add', null, '添加弹窗', '3'), ('1156042299306418177', '2019-07-30 11:22:16', '2019-07-30 11:22:16', '1', '6', '1153495031810461697', '保存', '', '/pcboss/system/website/navigation/save', null, '保存接口', '3'), ('1156042595428474882', '2019-07-30 11:23:26', '2019-07-30 11:23:26', '1', '5', '1153495031810461697', '删除', '', '/pcboss/system/website/navigation/delete', null, '删除', '3'), ('1156043168856940545', '2019-07-30 11:25:43', '2019-07-30 11:25:43', '1', '4', '1153495031810461697', '修改', '', '/pcboss/system/website/navigation/edit', null, '修改弹窗', '3'), ('1156043234770427905', '2019-07-30 11:25:59', '2019-07-30 11:25:59', '1', '3', '1153495031810461697', '更新', '', '/pcboss/system/website/navigation/update', null, '更新接口', '3'), ('1156045293838147585', '2019-07-30 11:34:10', '2019-07-30 11:34:10', '1', '2', '1153495031810461697', '文章管理', null, '/pcboss/system/website/navigation/article/view', null, '文章管理新窗口', '3'), ('1156099141575385090', '2019-07-30 15:08:08', '2019-07-30 15:08:08', '1', '1', '1153495155055890433', '添加', '', '/pcboss/system/website/link/add', null, '添加弹窗', '3'), ('1156099301554528257', '2019-07-30 15:08:46', '2019-07-30 15:08:46', '1', '1', '1153495155055890433', '保存', '', '/pcboss/system/website/link/save', null, '保存接口', '3'), ('1156099420307857410', '2019-07-30 15:09:14', '2019-07-30 15:09:14', '1', '1', '1153495155055890433', '删除', '', '/pcboss/system/website/link/delete', null, '删除接口', '3'), ('1156099620929806338', '2019-07-30 15:10:02', '2019-07-30 15:10:02', '1', '1', '1153495155055890433', '修改', '', '/pcboss/system/website/link/edit', null, '修改弹窗', '3'), ('1156099736667430914', '2019-07-30 15:10:30', '2019-07-30 15:10:30', '1', '1', '1153495155055890433', '更新', '', '/pcboss/system/website/link/update', null, '更新接口', '3'), ('1156102063474352129', '2019-07-30 15:19:44', '2019-07-30 15:19:44', '1', '1', '1153495948102307842', '修改', '', '/pcboss/course/fresh/course/view', null, '修改弹窗', '3'), ('1156102212472807425', '2019-07-30 15:20:20', '2019-07-30 15:20:20', '1', '1', '1153495948102307842', '更新', '', '/pcboss/course/fresh/course/update', null, '更新接口', '3'), ('1156106290552643585', '2019-07-30 15:36:32', '2019-07-30 15:36:32', '1', '1', '1153496094022144002', '查看', '', '/pcboss/course/fresh/course/apply/view', null, '查看', '3'), ('1156106778274701314', '2019-07-30 15:38:29', '2019-07-30 15:38:29', '1', '1', '1153496094022144002', '审核课程', '', '/pcboss/course/fresh/course/apply/audit', null, '审核课程', '3'), ('1156106947468730369', '2019-07-30 15:39:09', '2019-07-30 15:39:09', '1', '1', '1153496094022144002', '更新', '', '/pcboss/course/fresh/course/apply/update', null, '更新', '3'), ('1156389609588662274', '2019-07-31 10:22:21', '2019-07-31 10:22:21', '1', '1', '1153496241066053634', '添加', '', '/pcboss/course/course/subject/add', null, '添加弹窗', '3'), ('1156389834885701634', '2019-07-31 10:23:15', '2019-07-31 10:23:15', '1', '1', '1153496241066053634', '保存', '', '/pcboss/course/course/subject/save', null, '保存接口', '3'), ('1156390300390531073', '2019-07-31 10:25:06', '2019-07-31 10:25:06', '1', '1', '1153496241066053634', '修改', '', '/pcboss/course/course/subject/view', null, '修改弹窗', '3'), ('1156390387736911873', '2019-07-31 10:25:26', '2019-07-31 10:25:26', '1', '1', '1153496241066053634', '更新', '', '/pcboss/course/course/subject/update', null, '更新', '3'), ('1156390708198514689', '2019-07-31 10:26:43', '2019-07-31 10:26:43', '1', '1', '1153496241066053634', '删除', '', '/pcboss/course/course/subject/delete', null, '删除', '3'), ('1156393404230017026', '2019-07-31 10:37:26', '2019-07-31 10:37:26', '1', '1', '1153496795896975361', '修改', '', '/pcboss/user/teacher/view', null, '修改、查看、设置分成弹窗', '3'), ('1156393596735987714', '2019-07-31 10:38:11', '2019-07-31 10:38:11', '1', '1', '1153496795896975361', '更新', '', '/pcboss/user/teacher/update', null, '更新', '3'), ('1156394505545195522', '2019-07-31 10:41:48', '2019-07-31 10:41:48', '1', '1', '1153497106191585282', '添加', '', '/pcboss/user/teacher/audit/add', null, '添加弹窗', '3'), ('1156395045071101953', '2019-07-31 10:43:57', '2019-07-31 10:43:57', '1', '1', '1153497106191585282', '保存', '', '/pcboss/user/teacher/audit/save', null, '保存接口', '3'), ('1156395484629966849', '2019-07-31 10:45:42', '2019-07-31 10:45:42', '1', '1', '1153497106191585282', '修改', '', '/pcboss/user/teacher/audit/view', null, '修改、查看弹窗', '3'), ('1156395794031190017', '2019-07-31 10:46:55', '2019-07-31 10:46:55', '1', '1', '1153497106191585282', '校验教师是否存在', '', '/pcboss/user/teacher/audit/check', null, '校验教师是否存在', '3'), ('1156395965360119810', '2019-07-31 10:47:36', '2019-07-31 10:47:36', '1', '1', '1153497106191585282', '更新', '', '/pcboss/user/teacher/audit/update', null, '更新弹窗', '3'), ('1156396502923091970', '2019-07-31 10:49:44', '2019-07-31 10:49:44', '1', '1', '1153497106191585282', '审核页面', '', '/pcboss/user/teacher/audit/isAudit', null, '审核弹窗', '3'), ('1156396635052056577', '2019-07-31 10:50:16', '2019-07-31 10:50:16', '1', '1', '1153497106191585282', '审核更新', '', '/pcboss/user/teacher/audit/audit', null, '审核更新', '3'), ('1156452734279098369', '2019-07-31 14:33:11', '2019-07-31 14:33:11', '1', '1', '1153497221941792770', '批量标记为已打款', '', '/pcboss/user/lecturer/profit/batch', null, '批量标记为已打款', '3'), ('1156453190803922945', '2019-07-31 14:35:00', '2019-07-31 14:35:00', '1', '1', '1153497221941792770', '打款跳页面', '', '/pcboss/user/lecturer/profit/edit', null, '打款跳页面', '3'), ('1156453747622944769', '2019-07-31 14:37:13', '2019-07-31 14:37:13', '1', '1', '1153497221941792770', '更新讲师打款状态', '', '/pcboss/user/lecturer/profit/update', null, '更新讲师打款状态', '3'), ('1156467834150985729', '2019-07-31 15:33:11', '2019-07-31 15:33:11', '1', '1', '1153498940276838401', '添加', '', '/pcboss/system/system/user/add', null, '添加弹窗', '3'), ('1156467976803459073', '2019-07-31 15:33:45', '2019-07-31 15:33:45', '1', '1', '1153498940276838401', '保存', '', '/pcboss/system/system/user/save', null, '保存接口', '3'), ('1156468115706224642', '2019-07-31 15:34:18', '2019-07-31 15:34:18', '1', '1', '1153498940276838401', '删除', '', '/pcboss/system/system/user/delete', null, '删除接口', '3'), ('1156471160762540033', '2019-07-31 15:46:24', '2019-07-31 15:46:24', '1', '1', '1153498940276838401', '修改', '', '/pcboss/system/system/user/view', null, '修改弹窗', '3'), ('1156471428245889026', '2019-07-31 15:47:28', '2019-07-31 15:47:28', '1', '1', '1153498940276838401', '更新', '', '/pcboss/system/system/user/update', null, '更新接口', '3'), ('1156471546990829570', '2019-07-31 15:47:56', '2019-07-31 15:47:56', '1', '1', '1153498940276838401', '更新密码弹窗', '', '/pcboss/system/system/user/password', null, '更新密码弹窗', '3'), ('1156471749651210241', '2019-07-31 15:48:45', '2019-07-31 15:48:45', '1', '1', '1153498940276838401', '更新密码', '', '/pcboss/system/system/user/update/password', null, '更新密码接口', '3'), ('1156472210034794497', '2019-07-31 15:50:34', '2019-07-31 15:50:34', '1', '1', '1153498940276838401', '设置角色', '', '/pcboss/system/system/role/list', null, '设置角色弹窗', '3'), ('1156472470257803266', '2019-07-31 15:51:36', '2019-07-31 15:51:36', '1', '1', '1153498940276838401', '保存用户角色', '', '/pcboss/system/system/role/user/save', null, '保存用户角色接口', '3'), ('1156472761619324929', '2019-07-31 15:52:46', '2019-07-31 15:52:46', '1', '1', '1153498940276838401', '用户关联的角色', '', '/pcboss/system/system/role/user/list', null, '获取用户关联的角色', '3'), ('1156473195461353473', '2019-07-31 15:54:29', '2019-07-31 15:54:29', '1', '1', '1153498940276838401', '列出用户', '', '/pcboss/user/user/list', null, '列出用户', '3'), ('1156473846425722881', '2019-07-31 15:57:04', '2019-07-31 15:57:04', '1', '1', '1153499292782923778', '添加', '', '/pcboss/system/system/role/add', null, '添加弹窗', '3'), ('1156473962389839874', '2019-07-31 15:57:32', '2019-07-31 15:57:32', '1', '1', '1153499292782923778', '保存', '', '/pcboss/system/system/role/save', null, '保存接口', '3'), ('1156474159387910146', '2019-07-31 15:58:19', '2019-07-31 15:58:19', '1', '1', '1153499292782923778', '删除', '', '/pcboss/system/system/role/delete', null, '删除接口', '3'), ('1156475549820657665', '2019-07-31 16:03:51', '2019-07-31 16:03:51', '1', '1', '1153499292782923778', '修改', '', '/pcboss/system/system/role/view', null, '修改修改弹窗', '3'), ('1156475705584525314', '2019-07-31 16:04:28', '2019-07-31 16:04:28', '1', '1', '1153499292782923778', '更新', '', '/pcboss/system/system/role/update', null, '更新接口', '3'), ('1156475975043391490', '2019-07-31 16:05:32', '2019-07-31 16:05:32', '1', '1', '1153499292782923778', '设置权限', '', '/pcboss/system/menu/list', null, '设置权限弹窗', '3'), ('1156476159274000386', '2019-07-31 16:06:16', '2019-07-31 16:06:16', '1', '1', '1153499292782923778', '菜单角色关联', '', '/pcboss/system/menu/role/list', null, '菜单角色关联', '3'), ('1156476414988132353', '2019-07-31 16:07:17', '2019-07-31 16:07:17', '1', '1', '1153499292782923778', '保存角色菜单关联', '', '/pcboss/system/menu/role/save', null, '角色菜单关联接口', '3'), ('1156477233678524418', '2019-07-31 16:10:32', '2019-07-31 16:10:32', '1', '1', '1153499423880089601', '添加', '', '/pcboss/system/menu/add', null, '添加弹窗', '3'), ('1156477357225943042', '2019-07-31 16:11:01', '2019-07-31 16:11:01', '1', '1', '1153499423880089601', '保存', '', '/pcboss/system/menu/save', null, '保存接口', '3'), ('1156477431565787138', '2019-07-31 16:11:19', '2019-07-31 16:11:19', '1', '1', '1153499423880089601', '删除', '', '/pcboss/system/menu/delete', null, '删除接口', '3'), ('1156478453679923201', '2019-07-31 16:15:23', '2019-07-31 16:15:23', '1', '1', '1153499423880089601', '修改', '', '/pcboss/system/menu/view', null, '修改弹窗', '3'), ('1156478738817097730', '2019-07-31 16:16:31', '2019-07-31 16:16:31', '1', '1', '1153499423880089601', '更新', '', '/pcboss/system/menu/update', null, '更新', '3'), ('1156763603487694849', '2019-08-01 11:08:28', '2019-08-01 11:08:28', '1', '1', '1153501173945044993', '添加', '', '/pcboss/user/platform/add', null, '添加弹窗', '3'), ('1156763684647477250', '2019-08-01 11:08:47', '2019-08-01 11:08:47', '1', '1', '1153501173945044993', '保存', '', '/pcboss/user/platform/save', null, '保存接口', '3'), ('1156763793531609089', '2019-08-01 11:09:13', '2019-08-01 11:09:13', '1', '1', '1153501173945044993', '修改', '', '/pcboss/user/platform/view', null, '修改、查看弹窗', '3'), ('1156763879334486018', '2019-08-01 11:09:34', '2019-08-01 11:09:34', '1', '1', '1153501173945044993', '更新', '', '/pcboss/user/platform/update', null, '更新', '3'), ('1156770628078673921', '2019-08-01 11:36:23', '2019-08-01 11:36:23', '1', '1', '1153498480237187073', '系统更新', '', '/pcboss/system/system/update', null, '系统更新接口', '3'), ('1156770804654678017', '2019-08-01 11:37:05', '2019-08-01 11:37:05', '1', '4', '1153498480237187073', '站点富文本上传图片', '', '/pcboss/course/api/upload/pic', null, '站点富文本上传图片接口', '3'), ('1156770958275256321', '2019-08-01 11:37:41', '2019-08-01 11:37:41', '1', '3', '1153498480237187073', '站点更新', '', '/pcboss/system/fresh/website/update', null, '站点更新接口', '3'), ('1156771497591447553', '2019-08-01 11:39:50', '2019-08-01 11:39:50', '1', '1', '1153495031810461697', '导航文章更新', '', '/pcboss/system/website/navigation/article/update', null, '导航文章更新', '3'), ('1156846769674223617', '2019-08-01 16:38:56', '2019-08-01 16:38:56', '1', '10', '0', '首页', 'dashboard', '', null, '首页', '1'), ('1158323404529299458', '2019-08-05 18:26:33', '2019-08-05 18:26:33', '1', '1', '1153501173945044993', '删除', '', '/pcboss/user/platform/delete', null, '删除接口', '3'), ('1160850528846749698', '2019-08-12 17:48:27', '2019-08-12 17:48:27', '1', '1', '1153478801917579265', '学生列表', '/user/student', '/pcboss/user/user/ext/list', null, '学员列表', '2'), ('1160851003339972610', '2019-08-12 17:50:20', '2019-08-12 17:50:20', '1', '1', '1160850528846749698', '修改', '', '/pcboss/user/user/ext/view', null, '修改、查看弹窗', '3'), ('1160851295330639874', '2019-08-12 17:51:29', '2019-08-12 17:51:29', '1', '1', '1160850528846749698', '更新', '', '/pcboss/user/user/ext/update', null, '更新接口', '3'), ('1237594255145402370', '2020-03-11 12:20:38', '2020-03-11 12:20:38', '1', '1', '1153478801917579265', '学生组管理', '/user/studentGroup', '/pcboss/user/user/group/list', null, null, '2'), ('1237615405342752770', '2020-03-11 13:44:41', '2020-03-11 13:44:41', '1', '1', '1237594255145402370', '增加', '', '/pcboss/user/user/group/save', null, null, '3'), ('1237629590336241666', '2020-03-11 14:41:03', '2020-03-11 14:41:03', '1', '1', '1237594255145402370', '修改', '', '/pcboss/user/user/group/update', null, null, '3'), ('1237637804939329537', '2020-03-11 15:13:41', '2020-03-11 15:13:41', '1', '1', '1237594255145402370', '删除', '', '/pcboss/user/user/group/delete', null, null, '3'), ('1237637913173344257', '2020-03-11 15:14:07', '2020-03-11 15:14:07', '1', '1', '1237594255145402370', '查看', '', '/pcboss/user/user/group/view', null, null, '3'), ('1237732560876863489', '2020-03-11 21:30:13', '2020-03-11 21:30:13', '1', '1', '1237594255145402370', '查看绑定/解绑学生', '', '/pcboss/user/user/group/relate/list', null, null, '3'), ('1237753095350415361', '2020-03-11 22:51:48', '2020-03-11 22:51:48', '1', '1', '1237732560876863489', '绑定学员', '', '/pcboss/user/user/group/relate/save', null, null, '3'), ('1247079477561888769', '2020-04-06 16:31:31', '2020-04-06 16:31:31', '1', '8', '0', '新闻管理', 'news', '', null, null, '1'), ('1247136949664256002', '2020-04-06 20:19:54', '2020-04-06 20:19:54', '1', '1', '1247079477561888769', '新闻发布', '/news', '', null, null, '1'), ('1247137713874833409', '2020-04-06 20:22:56', '2020-04-06 20:22:56', '1', '1', '1247136949664256002', '新闻管理', '/news/news/pc', '/pcboss/system/news/list', null, null, '2'), ('1247137943294873601', '2020-04-06 20:23:51', '2020-04-06 20:23:51', '1', '1', '1247137713874833409', '增加', '', '/pcboss/system/news/save', null, null, '3'), ('1247138091966173185', '2020-04-06 20:24:26', '2020-04-06 20:24:26', '1', '1', '1247137713874833409', '删除', '', '/pcboss/system/news/delete', null, null, '3'), ('1247138182709940225', '2020-04-06 20:24:48', '2020-04-06 20:24:48', '1', '1', '1247137713874833409', '修改', '', '/pcboss/system/news/update', null, null, '3'), ('1247138266277253121', '2020-04-06 20:25:08', '2020-04-06 20:25:08', '1', '1', '1247137713874833409', '查看', '', '/pcboss/system/news/view', null, null, '3'), ('1284696020512518146', '2020-07-19 11:46:14', '2020-07-19 11:46:14', '1', '3', '1153498480237187073', '缓存管理', '/system/website/cache', '/pcboss/system/cachelog/list', null, null, '2'), ('1284705761741512706', '2020-07-19 12:24:56', '2020-07-19 12:24:56', '1', '1', '1284696020512518146', '缓存更新', '', '/pcboss/system/cachelog/update', null, null, '3'), ('1288423092346957825', '2020-07-29 18:36:17', '2020-07-29 18:36:17', '1', '4', '1153498480237187073', '系统配置', '/system/website/systemconfig', '/pcboss/system/system/config/view', null, null, '2'), ('1288423276518846466', '2020-07-29 18:37:01', '2020-07-29 18:37:01', '1', '1', '1288423092346957825', '系统配置更新', '', '/pcboss/system/system/config/update', null, null, '3'), ('15ad3209d5324a8dbbb796b287fa9365', '2020-08-28 22:33:29', '2020-08-28 22:33:29', '1', '1', '01600c998f4040c5926cadeeee525acb', '学习卡更新接口', '', '/pcboss/market/studycard/update', null, null, '3'), ('174b2a5f6c29410f8e9096e0110036aa', '2021-05-22 21:41:40', '2021-05-22 21:41:40', '1', '1', '1160850528846749698', '删除', '', '/pcboss/user/user/ext/delete', null, null, '3'), ('1e6faa4730724cdc9932eaba6e3a2968', '2020-08-31 20:57:39', '2020-08-31 20:57:39', '1', '1', '1153496795896975361', '绑定教师和学生组', '', '/pcboss/teacher/student/student/group/save', null, null, '3'), ('26b9cfbd7bd341fb880a57a18c52fd7e', '2021-01-01 20:27:41', '2021-01-01 20:27:41', '1', '1', '1153494698514288641', '底部二维码', '/homepage/website/bottomQrCode', '/pcboss/system/bottom/qr/code/list', null, null, '2'), ('2c5ec2d4a41a4e2c85915734645967f9', '2020-08-28 22:15:18', '2020-08-28 22:15:18', '1', '1', '3978e74ba30b4cdaa556b7d7a582e61b', '课程范围', '', '/pcboss/course/fresh/course/list', null, null, '3'), ('2ce2496f8e0542b589d7482dfdbffdc6', '2020-11-15 22:47:13', '2020-11-15 22:47:13', '1', '1', '60aeb58dd9794e65a5b8a66814f55851', '删除', '', '/pcboss/system/newsType/delete', null, null, '3'), ('2e3387d0509242428164b858d0722720', '2021-01-01 20:30:01', '2021-01-01 20:30:01', '1', '1', '26b9cfbd7bd341fb880a57a18c52fd7e', '底部二维码查看', '', '/pcboss/system/bottom/qr/code/view', null, null, '3'), ('308e04eb3f394322834652db61474164', '2020-09-01 16:47:47', '2020-09-01 16:47:47', '1', '3', '6eadb897cf7b42bca206cf119820b3bb', '积点配置', '/market/dotconfig', '/pcboss/market/dot/config/view', null, null, '2'), ('3978e74ba30b4cdaa556b7d7a582e61b', '2020-08-28 22:09:28', '2020-08-28 22:09:28', '1', '1', '6eadb897cf7b42bca206cf119820b3bb', '优惠券', '/market/coupon/pc', '/pcboss/market/coupon/list', null, null, '2'), ('3b75601ffb6c427485401b1652cca7e8', '2021-01-01 20:29:10', '2021-01-01 20:29:10', '1', '1', '26b9cfbd7bd341fb880a57a18c52fd7e', '底部二维码更新', '', '/pcboss/system/bottom/qr/code/update', null, null, '3'), ('4107d7e63f564795abb27b17fb7684b6', '2020-08-28 22:14:05', '2020-08-28 22:14:05', '1', '1', '01600c998f4040c5926cadeeee525acb', '学习卡删除接口', '', '/pcboss/market/studycard/delete', null, null, '3'), ('41ea23b7759e463891f9c58639930f73', '2021-01-01 20:28:52', '2021-01-01 20:28:52', '1', '1', '26b9cfbd7bd341fb880a57a18c52fd7e', '底部二维码增加', '', '/pcboss/system/bottom/qr/code/save', null, null, '3'), ('4525eecd7fe0473b86c8367c331ed5e4', '2020-11-15 22:47:29', '2020-11-15 22:47:29', '1', '1', '60aeb58dd9794e65a5b8a66814f55851', '增加', '', '/pcboss/system/newsType/save', null, null, '3'), ('51131714f5da4ae680ee436ee3fddeca', '2020-11-10 22:59:01', '2020-11-10 22:59:01', '1', '1', '1153496094022144002', '删除', '', '/pcboss/course/fresh/course/apply/delete', null, null, '3'), ('598f3be3ad3643f5a63a6b3a3186439d', '2020-09-01 22:05:35', '2020-09-01 22:05:35', '1', '1', '1153478139284656129', '订单详情', '/order/orderMain', '/ddfdfddds', null, null, '2'), ('5de0a06811654590bf39a7878ca895bd', '2020-11-15 22:46:33', '2020-11-15 22:46:33', '1', '1', '60aeb58dd9794e65a5b8a66814f55851', '查看', '', '/pcboss/system/newsType/view', null, null, '3'), ('60aeb58dd9794e65a5b8a66814f55851', '2020-11-15 22:46:12', '2020-11-15 22:46:12', '1', '1', '1247136949664256002', '新闻类型管理', '/news/newsType/pc', '/pcboss/system/newsType/list', null, null, '2'), ('60fc99cfc53a44f9add56c52825bde78', '2020-09-01 22:20:27', '2020-09-01 22:20:27', '1', '1', '598f3be3ad3643f5a63a6b3a3186439d', '订单详情列表', '', '/pcboss/order/ordermain/list', null, null, '3'), ('69d26b9cf6b24d70a9cededce2e90c8d', '2020-08-28 22:16:24', '2020-08-28 22:16:24', '1', '1', '3978e74ba30b4cdaa556b7d7a582e61b', '优惠券添加接口', '', '/pcboss/market/coupon/save', null, null, '3'), ('6eadb897cf7b42bca206cf119820b3bb', '2020-08-22 17:32:43', '2020-08-22 17:32:43', '1', '1', 'a587f1e6a2284d2d84c6351439bb708d', '卡券管理', 'card', '', null, null, '1'), ('6ee43ac531fc4cf190a9661cff99dfd0', '2020-09-07 14:42:37', '2020-09-07 14:42:37', '1', '1', 'b73bb19eae1c411cb6d12011d4e0975a', '中部导航查看', '', '/pcboss/system/mobileMiddleNavigation/view', null, null, '3'), ('72539edd72bf4a629442c80f2c9dbd32', '2020-08-28 22:16:45', '2020-08-28 22:16:45', '1', '1', '3978e74ba30b4cdaa556b7d7a582e61b', '优惠券删除接口', '', '/pcboss/market/coupon/delete', null, null, '3'), ('72953cacc97645d59911cb4f9cdffaea', '2021-01-01 20:29:38', '2021-01-01 20:29:38', '1', '1', '26b9cfbd7bd341fb880a57a18c52fd7e', '底部二维码删除', '', '/pcboss/system/bottom/qr/code/delete', null, null, '3'), ('7297ce9180854cf5ba3a360e12f9a481', '2020-08-22 17:40:38', '2020-08-22 17:40:38', '1', '1', '01600c998f4040c5926cadeeee525acb', '课程范围', '', '/pcboss/course/fresh/course/list', null, null, '3'), ('744bb8ce2d5a4aaeab265d77c9114ce2', '2020-09-02 21:36:21', '2020-09-02 21:36:21', '1', '1', '598f3be3ad3643f5a63a6b3a3186439d', '订单详情导出', '', '/pcboss/order/ordermain/export', null, null, '3'), ('754b7b632b2e48a29edfc8d97dd1e23d', '2021-05-23 12:21:18', '2021-05-23 12:21:18', '1', '1', '1153496795896975361', '删除', '', '/pcboss/user/teacher/delete', null, null, '3'), ('77ee8038d78e4c3e888a2256e92896cc', '2020-08-28 22:34:10', '2020-08-28 22:34:10', '1', '1', '3978e74ba30b4cdaa556b7d7a582e61b', '优惠券更新接口', '', '/pcboss/market/coupon/update', null, null, '3'), ('85ee4e8c4c144cfebf87685a73db9585', '2021-02-14 21:17:42', '2021-02-14 21:17:42', '1', '3', '0', '学习记录', 'studyLog', '', null, null, '1'), ('8962505ede1d47ed91c9343062f888e2', '2020-09-04 20:28:35', '2020-09-04 20:28:35', '1', '1', '01600c998f4040c5926cadeeee525acb', '学习卡导出', '', '/pcboss/market/studycard/export', null, null, '3'), ('906b11b7bfcb44e787f5e34471b55913', '2021-01-17 15:51:59', '2021-01-17 15:51:59', '1', '1', '1153498480237187073', '系统授权信息', '/system/website/authcenter', '/pcboss/authCenter/view', null, null, '2'), ('91c2a14723ef44c1b30b0bb289d06446', '2021-05-23 11:18:30', '2021-05-23 11:18:30', '1', '1', '1153497106191585282', '删除', '', '/pcboss/user/teacher/audit/delete', null, null, '3'), ('957ed11de6c44e629a3f0a4a28f70be5', '2020-09-06 11:25:08', '2020-09-06 11:25:08', '1', '1', '1160850528846749698', '增加学生', '', '/pcboss/user/user/ext/save', null, null, '3'), ('9b4d5721f0ce42819b95a8e8b10e88d0', '2020-08-31 20:36:08', '2020-08-31 20:36:08', '1', '1', '1153496795896975361', '获取教师拥有的学生组列表', '', '/pcboss/teacher/student/student/group/list', null, null, '3'), ('9f81b0ee328d4f97a81472a826611109', '2020-09-02 14:53:02', '2020-09-02 14:53:02', '1', '1', '598f3be3ad3643f5a63a6b3a3186439d', '订单详情删除', '', '/pcboss/order/ordermain/delete', null, null, '3'), ('a587f1e6a2284d2d84c6351439bb708d', '2020-08-22 17:32:04', '2020-08-22 17:32:04', '1', '2', '0', '营销管理', 'market', '', null, '营销管理', '1'), ('a6c72bc628ac4278b19747778ebf283d', '2020-09-03 16:10:51', '2020-09-03 16:10:51', '1', '1', '1160850528846749698', '导入学生', '', '/pcboss/user/user/ext/import', null, null, '3'), ('a7b2e10b65e34817b6a38d68870d7772', '2020-09-07 14:41:30', '2020-09-07 14:41:30', '1', '1', 'b73bb19eae1c411cb6d12011d4e0975a', '中部导航保存', '', '/pcboss/system/mobileMiddleNavigation/save', null, null, '3'), ('ad1fb58aef0b4ae6ac1c48825f9f4152', '2020-09-06 12:08:40', '2020-09-06 12:08:40', '1', '1', '1160850528846749698', '学生密码重置', '', '/pcboss/user/user/ext/resetPassword', null, null, '3'), ('b73bb19eae1c411cb6d12011d4e0975a', '2020-09-07 13:35:54', '2020-09-07 13:35:54', '1', '1', '1153494698514288641', '移动中部导航', '/homepage/website/mobileMiddleNavigation', '/pcboss/system/mobileMiddleNavigation/list', null, null, '2'), ('b9dbc02280d641ef86ac5478dcc4a75a', '2020-09-04 11:34:31', '2020-09-04 11:34:31', '1', '1', '1160850528846749698', '学生导入模版', '', '/pcboss/user/user/ext/exportTemplate', null, null, '3'), ('ba6c2385b1d3455596db84bbd877c3d5', '2020-11-18 23:44:51', '2020-11-18 23:44:51', '1', '1', '1153498794638020609', '操作日志', '/system/log/operateLog', '/pcboss/system/operateLog/list', null, null, '2'), ('c70aa8df6c0344929dd0583349a0c378', '2020-11-15 22:46:52', '2020-11-15 22:46:52', '1', '1', '60aeb58dd9794e65a5b8a66814f55851', '修改', '', '/pcboss/system/newsType/update', null, null, '3'), ('ced52013c14849359fc309e4955c8010', '2020-09-07 14:43:15', '2020-09-07 14:43:15', '1', '1', 'b73bb19eae1c411cb6d12011d4e0975a', '中部导航删除', '', '/pcboss/system/mobileMiddleNavigation/delete', null, null, '3'), ('cf940b01973148cca382cb64b5077d94', '2020-08-28 22:13:42', '2020-08-28 22:13:42', '1', '1', '01600c998f4040c5926cadeeee525acb', '学习卡添加接口', '', '/pcboss/market/studycard/save', null, null, '3'), ('e4a6aa36bb2141cd9ffbbd1804b400ed', '2020-08-28 22:13:12', '2020-08-28 22:13:12', '1', '1', '01600c998f4040c5926cadeeee525acb', '学习卡分页列表接口', '', '/pcboss/market/studycard/list', null, null, '3'), ('e78893087eb44e8b8b664e45ee083e49', '2020-09-24 22:24:31', '2020-09-24 22:24:31', '1', '1', '1153495948102307842', '课程信息是否有效更新接口', '', '/pcboss/course/fresh/course/updateCourseValidValue', null, null, '3'), ('e80ef6dddbd04d0aad1e074043736cbd', '2021-01-31 23:08:59', '2021-01-31 23:08:59', '1', '1', '1153498480237187073', '系统激活信息', '/system/website/authactivate', '/pcboss/authActivate/view', null, null, '2'), ('e852e318c5174841a36775a7b05bb938', '2021-02-04 23:38:52', '2021-02-04 23:38:52', '1', '1', '1153498480237187073', '返回系统信息(激活)', '', '/pcboss/authActivate/viewSystemInfo', null, null, '3'), ('ee8b45cfa2764aca850991cad2b7d48d', '2021-02-14 21:18:19', '2021-02-14 21:18:19', '1', '3', '85ee4e8c4c144cfebf87685a73db9585', '学习记录', '/user/studyLog', '/pcboss/user/userStudyLog/listByStudentUserNo', null, null, '2'), ('f1edc0c602df42789e9927823a87304a', '2020-09-07 14:42:00', '2020-09-07 14:42:00', '1', '1', 'b73bb19eae1c411cb6d12011d4e0975a', '中部导航修改', '', '/pcboss/system/mobileMiddleNavigation/update', null, null, '3'), ('f78a2f3e1c0143349c8e0aa8438d496d', '2020-11-13 17:57:04', '2020-11-13 17:57:04', '1', '1', '1156846769674223617', '订单统计', '', '/pcboss/order/ordermain/queryOrderCountBefore30Days', null, null, '3'), ('f78cd265747d4686aacaa977e0363801', '2020-08-28 22:17:08', '2020-08-28 22:17:08', '1', '1', '3978e74ba30b4cdaa556b7d7a582e61b', '优惠券查看接口', '', '/pcboss/market/coupon/view', null, null, '3'), ('fd8a2c3b82994496adf4f915d0041bbd', '2020-09-24 22:23:42', '2020-09-24 22:23:42', '1', '1', '1153495948102307842', '课程信息上下架更新接口', '', '/pcboss/course/fresh/course/updateCoursePutaway', null, null, '3');
COMMIT;

-- ----------------------------
--  Table structure for `system_menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `system_menu_role`;
CREATE TABLE `system_menu_role` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `menu_id` varchar(36) NOT NULL COMMENT '菜单ID',
  `role_id` varchar(36) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='菜单角色关联表';

-- ----------------------------
--  Records of `system_menu_role`
-- ----------------------------
BEGIN;
INSERT INTO `system_menu_role` VALUES ('00d1f4d84dbe4c029a8e8ae14bf01e41', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156039959035781122', '3'), ('01e8896b42c54735995cbfaa851924ce', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156395965360119810', '1'), ('045cbf4fa5cc461f8c9bd3bc9be29bf3', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '01600c998f4040c5926cadeeee525acb', '1'), ('05b75326bd7749ca9eb0497979861f7f', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156040289144283137', '1'), ('075e48cbaf7d4def856c674e5e0507d1', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156390300390531073', '1'), ('08724dfe7ee04eee95eacbcf7254ced3', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156389834885701634', '1'), ('08726641605345e3aabfa407ec52f2de', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156030361037971458', '3'), ('08826006d8cf4ade9ba2c1273eb89e9a', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156035023040421889', '3'), ('091c27034ead4a9e84bbffac3720451c', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156033449756991490', '1'), ('095882d04e654af6b549bb3f9381d8d0', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'fd8a2c3b82994496adf4f915d0041bbd', '1'), ('0aada48ac846437a9ff8991eae72ee16', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '72539edd72bf4a629442c80f2c9dbd32', '1'), ('0ada40ac375b450a8f94bcd0fef47072', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153494935626682369', '3'), ('0af09172e26e4fd79042f19a03c3e919', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156040630476742658', '3'), ('0c9a92e9ba884f6b99d95549fa3d1aed', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156477233678524418', '1'), ('0cd3e7ede7d44cc3a3c198ba267411cf', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156099420307857410', '1'), ('0cfe2c48b0ca44e5a9b88baed6befd1d', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156030262547324930', '3'), ('0db116f6d8494d2eaf3b93d8c669f586', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156468115706224642', '1'), ('0e3c9d1d1de449fa95fada5eda1d96e3', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156099620929806338', '1'), ('0e6e3de1149c41748956b2418a78d5d4', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156099420307857410', '3'), ('0ea0605d00334f82b819788481d35d97', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156390387736911873', '1'), ('12ed1f29adec44aaa8b0e5b7b52491da', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '91c2a14723ef44c1b30b0bb289d06446', '1'), ('13076014f3a84a2b8252cbce8432be9a', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156035689204948994', '3'), ('13c42fd8e7d5458abb7386b778587f0a', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1247137943294873601', '3'), ('1421042554a745729aa66931eb03edb6', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1237615405342752770', '1'), ('187b1b7b269748f1b0f76f0dd3ce8015', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156475975043391490', '1'), ('198b4272ea3c43b0b9d79ab2a5a4eb28', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156045293838147585', '1'), ('1cb26049063b4d10a6cdc5bdaf54c159', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156102212472807425', '1'), ('20e1b7c930624a33a240c2123aa7edbf', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '308e04eb3f394322834652db61474164', '1'), ('21c91221a45b4c3bbc8d0b70f2b74316', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156035689204948994', '1'), ('2202e5cff44c4cbd93663584e7c1003b', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156039623340466178', '1'), ('2326cdb0987346168a145dbe29a9c52e', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156033853009960961', '3'), ('23490fe5c77946259afee5a8cdbabd12', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '7297ce9180854cf5ba3a360e12f9a481', '1'), ('2350268ae95b407687934cec080cdf70', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '744bb8ce2d5a4aaeab265d77c9114ce2', '1'), ('24240b90682b46fcb3736558968ebf94', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156099301554528257', '3'), ('2494971bd6cc4472b44d48352bae1700', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'a6c72bc628ac4278b19747778ebf283d', '1'), ('25478071f6ef473bb94ded5f8404230b', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '85ee4e8c4c144cfebf87685a73db9585', '1'), ('255c82546b7d4c1fb2833acf96cd9b39', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153478416205189122', '3'), ('25ba34c16d01449da9308b2c9e231db0', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1247136949664256002', '3'), ('28abbdbf0b22435da40527d5a6c09c01', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1237637804939329537', '1'), ('29473d47473d4352a62e6f69e8516779', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153495031810461697', '3'), ('2baa61dfc4564e16a1db6bb7ca9941fa', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153478416205189122', '1'), ('2c5883b7d01b40e29a1f19c77120aa01', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156478738817097730', '1'), ('2d222f5da75949bd81a0a5519c787aa6', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153494219218587650', '3'), ('2d2912233703470f89c5512f61756be9', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153498794638020609', '1'), ('2d3a26095bbe422d94a066a5189d3dd3', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156036831607529473', '1'), ('2db753d690f7427a97a538f203809487', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153494438295474177', '1'), ('2dd0611c474046a49e918ffa44ef09cf', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1247138091966173185', '1'), ('2e109cb9a7fe4726a44cb0f0c2beff49', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156471160762540033', '1'), ('2e1f76e8989c4e90b02da90878ece45f', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156771497591447553', '1'), ('2fc94f1dd6914a8badf6866892896e00', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '41ea23b7759e463891f9c58639930f73', '1'), ('31bc0b016f3047539552c8dc3907c376', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153478694937661442', '1'), ('3280d03c24d1404e96efa4a8c26dd854', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'f78a2f3e1c0143349c8e0aa8438d496d', '1'), ('3298bbcb8fac4218b14443b67ce50ceb', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153499423880089601', '1'), ('34319e21cd5446f9930fadd3addff713', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '9f81b0ee328d4f97a81472a826611109', '1'), ('34423e6507d74fb2be29cd33555ef916', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153478694937661442', '3'), ('3617ebeae9ea4c7f8f4633c3d017b7f5', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156035023040421889', '1'), ('3672126a993b4739ac2bec96e1ea4feb', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156040630476742658', '1'), ('3674ecba0b594db2899839b09da28f9a', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156472470257803266', '1'), ('3684541492494c3d89eedfa40987be7a', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156036539059019777', '3'), ('3957af3899cb417faed83b2c102a341b', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153494698514288641', '3'), ('39a970a71d4f4960a4457e8872c207c3', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1158310111785873409', '3'), ('3dadbeb9ff4e4943873b1dc8b957cdc7', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '2ce2496f8e0542b589d7482dfdbffdc6', '1'), ('3e336dafc85f40cb85aee8c4a22165b4', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156032296088510465', '3'), ('3e4602f44593415b97b561ca70896977', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '8962505ede1d47ed91c9343062f888e2', '1'), ('3e7886a6291b4166ac6bf4b3a2e41690', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153495155055890433', '1'), ('3ea5bda96b8d4984bd08f77c0f229d66', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1154683387156230146', '3'), ('3f0268721242407985be422007c3964c', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156039623340466178', '3'), ('4011e8e92d944c289ac88f628215cf77', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1247079477561888769', '3'), ('41cbc365a33f4d70922177f6c615a9a4', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156395045071101953', '1'), ('428cda861c84448d98a74e67684eb604', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153478139284656129', '1'), ('48ba2cf2d45d45b699fa154d1a5aca6c', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '72953cacc97645d59911cb4f9cdffaea', '1'), ('4936789de7d04d82ba7b7662dab2d33d', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153495768179249153', '1'), ('4a32e00c4eb9483caac0b76d4ad6c4a6', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1288423276518846466', '1'), ('4b6baf7068f64558953194485cc789ca', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153496406875279362', '1'), ('4c0decdcafe544738f8f190347844b38', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153496795896975361', '1'), ('4ef9773107ae41bc94049f7d56fe7eb9', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153493010483089409', '3'), ('4fa91132b70348eb9da801ed5a6426ee', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156030992003899394', '3'), ('50dd5e5594de4f008ee5fb7ade6cd517', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1288423092346957825', '1'), ('50e6ba49bd0b4b228c3d96934f2fe6fb', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '4525eecd7fe0473b86c8367c331ed5e4', '1'), ('50f108b394fd43268da33736695d52b9', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1160851295330639874', '1'), ('5210b15f86fe44bf94813b5ecabfa702', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '69d26b9cf6b24d70a9cededce2e90c8d', '1'), ('523850b54fff4c1bb23c15729d7eb721', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156393404230017026', '3'), ('53391d521c1645cdb95c318e99c12a2a', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156037020263129089', '3'), ('54f50fe3c91241da91757418d3a016eb', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153499292782923778', '1'), ('571602ee7ada4f20b4fb9d462b4b9df8', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1247138266277253121', '1'), ('5b986a6b46184d8daf407e0f5d6fc81c', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1237732560876863489', '1'), ('5c34879d08f143e8a885343375b3c53f', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156035443800416257', '1'), ('5d07cfc8b5e142fbb90438a632bfe4ae', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1237629590336241666', '1'), ('5d6d1e1b24ff4541a8b9581dd50983fc', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156478453679923201', '1'), ('5dff97ea5eb241c6bd50459a6b65dfb0', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1160850528846749698', '3'), ('5e7a13e201dc4a9b82405bcb6a6e6a58', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1247137713874833409', '3'), ('5ec403904eef49ccbf393cad87897342', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156106290552643585', '1'), ('6031e48b1d5a47d387b8ae9725757209', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1247079477561888769', '1'), ('60da8afcd8dc442eaadf00a580218e0b', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156393404230017026', '1'), ('64596ffdae0a4f95a4d1b288b8546d9d', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153501541194108929', '3'), ('645b0e571de249649f906f5ec4b1b13d', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1247138182709940225', '1'), ('652c983e99e2490b8108e4c7e50c15fe', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153498794638020609', '3'), ('65d2e9c9697c48d4b6a984a234b9fcaa', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156099620929806338', '3'), ('67dab91de8f74b648f17f443f1d5cbc0', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156394505545195522', '3'), ('69e3e97431144b1db99989b9f96892cd', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156043168856940545', '3'), ('6b1de1e1d04543c181d79bbd8f8de5a5', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'e852e318c5174841a36775a7b05bb938', '1'), ('6bc831a6ff6248cdae3ac172f7ee27bc', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'b9dbc02280d641ef86ac5478dcc4a75a', '1'), ('6c146781c15e4c8ca85cb63b67c39bcb', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156390708198514689', '1'), ('6c3e70ad274d40c2a28628af56aedb08', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156032296088510465', '1'), ('6c646bec0179491ab5455ad77b921aa7', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'e78893087eb44e8b8b664e45ee083e49', '1'), ('6c6969ccaf76461f81b5fc3ecc21680f', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156477357225943042', '1'), ('6d98f8329a214034989a91ddc8bacba4', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1247136949664256002', '1'), ('6db5ec18534c492ab8bf4b50d36b374a', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153498121905213442', '1'), ('6e54aa7687be40889b88f62354323b32', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '754b7b632b2e48a29edfc8d97dd1e23d', '1'), ('6e5a97b3cf2c4b73b6dade6b66360348', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1160850528846749698', '1'), ('6e799d15e5c94c1a95bdd6ab94165deb', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156472761619324929', '1'), ('6e7ed4938fcb44a18fa8b6e8777b2884', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153494438295474177', '3'), ('6ed56551d8f240528277228c32e4e677', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153478139284656129', '3'), ('6ee74f4298cb48a29e946a2a3a60ea8c', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '5de0a06811654590bf39a7878ca895bd', '1'), ('71972d470bad46b5bd374749f930816e', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156396502923091970', '1'), ('7281bf4e85fa41ce9c47bda360288b06', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1e6faa4730724cdc9932eaba6e3a2968', '1'), ('73efc46260594f81bb5000a3bc97ecbc', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '3978e74ba30b4cdaa556b7d7a582e61b', '1'), ('7536d069693b4fb18b906683b3484a20', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156043168856940545', '1'), ('77b17584474c4c65a4e1c8254af88755', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '598f3be3ad3643f5a63a6b3a3186439d', '1'), ('77c0a6368418457f9ce339a63b73e9a6', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156040289144283137', '3'), ('7832203b6863449e8909e33304646346', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156042299306418177', '3'), ('788a79aa8cb543e3a143b665bbf56026', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156394505545195522', '1'), ('7affda49fbad4718ba69fd4c6c6d2a34', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'b73bb19eae1c411cb6d12011d4e0975a', '1'), ('7c0f0f9dc17a43feb771e2353cdedd33', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'e4a6aa36bb2141cd9ffbbd1804b400ed', '1'), ('7c14918be3ae486abaf68c6e94181b40', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156395794031190017', '1'), ('7e0e14f03ef942a186cb2be3bdd905da', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156473846425722881', '1'), ('7e6634c13d484b95834b3c55d215c789', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156475705584525314', '1'), ('7f3575a72b04464ba4b9ca02a7900a6a', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '6eadb897cf7b42bca206cf119820b3bb', '1'), ('832dbc0bd3fc4517a44c981c534c1c97', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156036831607529473', '3'), ('8504e65294f041d292e01d780b7a370d', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '906b11b7bfcb44e787f5e34471b55913', '1'), ('857d6175815641928141c586d7975f8d', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1247138266277253121', '3'), ('8695040eaf074e5595cfaea6a183a800', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153497106191585282', '1'), ('86a0ae604d51466ebc5dfa0aee40a483', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'f1edc0c602df42789e9927823a87304a', '1'), ('88c8dd7bed9e48efb530a2a3a9917634', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '3b75601ffb6c427485401b1652cca7e8', '1'), ('89466a932392478383d1752763b05d57', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1237753095350415361', '1'), ('8c89ed699ac346368b53887f00108116', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156033853009960961', '1'), ('8ca38f092b994b2eb77fed6f30383979', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156036653299277825', '3'), ('8d49232c45534626a39d0bb8cd5b13a1', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'f78cd265747d4686aacaa977e0363801', '1'), ('8d93338c8f9f4c96a43ec2de0dc61175', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156395794031190017', '3'), ('8edbe488ae9c43ebb7c4709b0efe6184', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153494219218587650', '1'), ('8f1381cbacc446178a770525c9cf4a75', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153495582782623746', '1'), ('8fbde27e66e64d95a36cee49fb93975a', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1160851003339972610', '1'), ('90d523b7e060428d9ce6c40805ca5540', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156042595428474882', '3'), ('9144402c41d641ff996182a84d16fd2b', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1247137943294873601', '1'), ('919ebcd860a54ce785f815ba8b742bde', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '05173427af9e4e83b1d8357fe23e606e', '1'), ('91b802ed319f4be19a99e760746bd640', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1154683761573359618', '3'), ('9316dd07a571431ca30cb4f22aa38b65', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156396502923091970', '3'), ('9750d4077dbc4082b77b850902295c75', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '0a538193f43c438d9405ddc624f8457e', '1'), ('97a22edd701b418a96111d2d69f67dff', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156389609588662274', '1'), ('9a33fd77e42442f9baedc9f200afb3b3', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'ba6c2385b1d3455596db84bbd877c3d5', '1'), ('9cdd48109ecf46bd9b913408e4c798d2', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156102063474352129', '1'), ('9d2eed0cb1ec4f26bd24799745b82889', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156036045850812417', '1'), ('9d660c583d2f439aa5ae3946e893c8a4', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156043234770427905', '3'), ('9ec2e7b5dacb459eb1beb0912fba2b67', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153497106191585282', '3'), ('9f023962c4ca4e7fab573e07495fb297', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153478911053369345', '1'), ('9fd1e053211c4e69ae07e30d2eaa1637', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156036045850812417', '3'), ('a0362bb3a61d46a7a16d7a941c9b5e30', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153493592497295362', '3'), ('a20a3421a74344829818b6c2b1767693', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153494698514288641', '1'), ('a20da66031c3474cbb30cd2db43d777f', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156395484629966849', '1'), ('a25885d2cf3143eb8e2518195ccfccd3', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156396635052056577', '1'), ('a29ee068a9bb4da59a7a8db45ba9949d', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156041685298061313', '3'), ('a485d071807548a2931e89ca64a68754', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156032108988997634', '3'), ('a4a3627ef9b4470f9261f1ac5aea9b6d', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1158305741493071874', '3'), ('a4d01f15204c4bd89e8a4b216ad017c7', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156467976803459073', '1'), ('a57f4bee81ed4c619ac6fa41d0c74ebf', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156042595428474882', '1'), ('a6eb09a3b5494da2bd2db16d3be72253', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153493592497295362', '1'), ('a81f719ade1042438ebac1ad6b536e81', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156045293838147585', '3'), ('a9199b52801d44d9af0f4769fd08ddd8', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153498480237187073', '1'), ('a9ea0406b30b488086efd28ecd1fa423', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1247137713874833409', '1'), ('aa5e4d352d21443b8761b57d30255487', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156099736667430914', '1'), ('aa73a812791c4aff980579a1d7e6670b', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156471428245889026', '1'), ('aabcd025de224da88f2c6d1ebc23a59a', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156030361037971458', '1'), ('ab0c7f025c8d4786a3e2e5f7166e3951', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156041685298061313', '1'), ('ab38495b4d5a427e8c5ad924c3d853b4', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '9b4d5721f0ce42819b95a8e8b10e88d0', '1'), ('abc968fd7d4a427489b2d784dd4694b5', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156040868612546562', '1'), ('abd66cbf83df4c3dbe968cd10f2828a0', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156846769674223617', '1'), ('ac3ae39cef684aa5b1789496628e4fa6', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156771497591447553', '3'), ('ad20712b33ee4d6ab8bd6835bd120d7f', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'ee8b45cfa2764aca850991cad2b7d48d', '1'), ('ae255fc3d23d40ccb67dcf76626d4d07', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '2c5ec2d4a41a4e2c85915734645967f9', '1'), ('afd46dbd11a2492f996a257a0cba4fe6', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '4107d7e63f564795abb27b17fb7684b6', '1'), ('b019a91866714dc283fbdd3bacb0792a', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153496406875279362', '3'), ('b1517a4597ce49459b9d1b75e09ba72d', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156395484629966849', '3'), ('b16bc626d5514244b1cbb5a6f8918b53', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153498940276838401', '1'), ('b51259fce9664b758af0534458771212', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153496241066053634', '1'), ('b5887e09e82c4dbc834177a62cd4f909', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156036653299277825', '1'), ('b588e7f474384f0b8a90a31021b41670', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153478559176429569', '1'), ('b6ba47ff2425409d8d66df6f82c802cb', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'c70aa8df6c0344929dd0583349a0c378', '1'), ('b6e38d76d39847868e8e6b60a65c3186', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1160851552827351041', '3'), ('b7e0b3c711e64487bd798b0c0d6d4bea', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156099301554528257', '1'), ('b9d284002ad646d38fef6ad4689c5a31', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1247138182709940225', '3'), ('bb8a4fd99e8747e680e8cd28211cc961', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156106778274701314', '1'), ('bc8cf7f349054893b43e6e5f9b9061f3', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156037020263129089', '1'), ('bcbcd0189e4a47c9878d37a4b4ff0123', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156032474489036801', '1'), ('bdd250bad34547938612d2c100e1fcd6', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'e80ef6dddbd04d0aad1e074043736cbd', '1'), ('beb37f4691c94930a534af64e4b07642', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1247138091966173185', '3'), ('bed2178d9ad741b3b29cb399e606ffc8', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156766341252521985', '3'), ('bfed9a4438f5451280acff3f9564dca9', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '77ee8038d78e4c3e888a2256e92896cc', '1'), ('c26348f69e93488c86cce8328cc6e182', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156030992003899394', '1'), ('c2bf93acdff84151877bbeecde6fa673', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156042299306418177', '1'), ('c2d0d42883114e73a0518f6a2901c305', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153496795896975361', '3'), ('c3bb8f38a7764105938e933b26aa8966', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156030262547324930', '1'), ('c49d007029d845958f24dfd00976e1b4', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156473962389839874', '1'), ('c5a57dcac552475ba90e6dbebab85785', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156467834150985729', '1'), ('c81a01c8e91c4e09b002d997a281a406', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'a587f1e6a2284d2d84c6351439bb708d', '1'), ('c935594807204d6fb6dca5bcb79c1b43', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156770804654678017', '1'), ('c937c4e4c0394e3f9843c726ac426ed6', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '51131714f5da4ae680ee436ee3fddeca', '1'), ('c95ad744879a46a18e003954971c48b0', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156476159274000386', '1'), ('c986a16fd3344a83bc1894a3e5a42bbb', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153500580878848001', '3'), ('ca85eb5ba0ad41b2b7ea60fe7ccb17bf', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156472210034794497', '1'), ('cabc8fbf78d34dc580cd25abe8d33c89', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153495948102307842', '1'), ('cae08bf8ae664570b55539cead5289db', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156036539059019777', '1'), ('cb08e4a7dc1744f88c33d493d06330dd', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '2e3387d0509242428164b858d0722720', '1'), ('cb1d0a8fc20349a0829caf3f2ffbe4b8', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156471749651210241', '1'), ('cb5a64f667bc4876a68e394cd53e1bf4', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '60fc99cfc53a44f9add56c52825bde78', '1'), ('cc9ac19abbb448bf8db436f2fcd42767', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'a7b2e10b65e34817b6a38d68870d7772', '1'), ('ce253cd2bca44b81b163ad4e8217c7e4', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '60aeb58dd9794e65a5b8a66814f55851', '1'), ('cefbbee1d917410182d7ba65325bc8f5', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156040868612546562', '3'), ('d00da633269d4660999e1deb8994081c', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156474159387910146', '1'), ('d065ec4187314257b78fb3e2a827732d', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153494935626682369', '1'), ('d18cf9ca6bb148d9a7863168352d8d57', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'ad1fb58aef0b4ae6ac1c48825f9f4152', '1'), ('d236f2fa54f74706b1b65581483e0a8c', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156099141575385090', '3'), ('d4c43a7063464aa08aff9cdc6438eaa8', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156393596735987714', '1'), ('d509298cd800492e8e32f6e060222e29', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1284696020512518146', '1'), ('d66ee4694f01490b9c7569f7e1de4fc4', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1284705761741512706', '1'), ('d759482db8ae42efaf4ded5425a28829', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156043234770427905', '1'), ('db21fde1d8f34233b22453c5c5e1f689', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156471546990829570', '1'), ('dbca646e2803487684a4aebf36f1c8e2', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '957ed11de6c44e629a3f0a4a28f70be5', '1'), ('dca36c9b9e684639a7acea0da79a6283', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'ced52013c14849359fc309e4955c8010', '1'), ('dd4744d6a53b41889654bf95325cb776', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156473195461353473', '1'), ('de381af5054a4162b05c2bb9b4a0c24e', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1160851003339972610', '3'), ('de6ef4d219f649df99f4fcdc5dfa744b', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '0eed407104cc47efbf31182b497f22a8', '1'), ('dee0700b4be74c34a16f44eb787b5dd4', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156032108988997634', '1'), ('df198a92fe794f149840075203297dea', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153496094022144002', '1'), ('e06a8ede0fcb4f97b3fe0f61026b2384', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156770628078673921', '1'), ('e175f1bce7d04357924776534587f54c', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153498480237187073', '3'), ('e1bee8c98cf14771b04a212db8cc41fe', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156475549820657665', '1'), ('e2580b4395f2499daa9ecdf351ccd59a', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156846769674223617', '3'), ('e33d7a3af30f4c1daded87841d3d56fd', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156770958275256321', '1'), ('e93ef36d071d45fb863a88c11fb98e11', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153493835884367873', '1'), ('e9ade5377e504cd78f209b7b62ef3b7e', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153478911053369345', '3'), ('ed8966cd1ecc490a8025f39f9c80b2f1', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1237637913173344257', '1'), ('ee4c7677085449689455d4ee15a4e2d9', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156477431565787138', '1'), ('ee5c1749392a416aa9c17fe605fec459', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156106947468730369', '1'), ('ee6cb25fa690455e9fbe09632e69a6f7', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156035443800416257', '3'), ('ee800cd4c2a74f53b832506e116ab494', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156033449756991490', '3'), ('ef00840b744e423185e2b227fe23f471', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153495031810461697', '1'), ('f02e278447014cfe9eb15da28c6c10dc', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '6ee43ac531fc4cf190a9661cff99dfd0', '1'), ('f180285bad2040bdb44e2ff3e0eb665d', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156039959035781122', '1'), ('f2e2e5b343c3440bbf1d4e4cd3ce108e', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '174b2a5f6c29410f8e9096e0110036aa', '1'), ('f3b095c2e04c40be8eb6b1c4a569c9c6', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1237594255145402370', '1'), ('f542937ac25045b6ad7ae81cce5c67eb', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153495155055890433', '3'), ('f6e224b0ff6d4770b7f47c4977242c4d', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153478801917579265', '1'), ('f720b96f732d4054a553559baa6aaf6e', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153493835884367873', '3'), ('f7c2bc3cc3794e1097ded46df6b23c7b', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', 'cf940b01973148cca382cb64b5077d94', '1'), ('f7e6e96c40e34f1eb5996c1eb3bc2dad', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '03c21615ccea423b851a552124929efa', '1'), ('f8367a1e43a64f5a840dfc7a0645aa03', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153478801917579265', '3'), ('f895731f6ad14d4bb18355f37a7460f3', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156099736667430914', '3'), ('f8cadcdbb9eb4f8f980a615a8f3f1d03', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156099141575385090', '1'), ('faa5f61de4cd46758f5b5ed4ae16bcc6', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '26b9cfbd7bd341fb880a57a18c52fd7e', '1'), ('fc2081ae81db4c38846039ecb2aaad6e', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1156476414988132353', '1'), ('fcb42b8da2ad4e17a6b944299deccdc9', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '1153500580878848001', '1'), ('fcbe6b4bb5a24788bcd54e214e32df78', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1153501424605040641', '3'), ('fd970f7c8f0441569ffc15d407215b74', '2020-08-20 21:29:37', '2020-08-20 21:29:37', '1', '1', '1156032474489036801', '3'), ('fed10efe5b274094a7a8e76dec88bce4', '2021-05-23 12:21:46', '2021-05-23 12:21:46', '1', '1', '15ad3209d5324a8dbbb796b287fa9365', '1');
COMMIT;

-- ----------------------------
--  Table structure for `system_role`
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `role_name` varchar(50) NOT NULL COMMENT '名称',
  `remark` varchar(255) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色信息';

-- ----------------------------
--  Records of `system_role`
-- ----------------------------
BEGIN;
INSERT INTO `system_role` VALUES ('1', '2018-02-06 15:47:52', '2018-03-30 15:12:07', '1', '2', '超级管理员', '管理后台全部权限'), ('3', '2018-12-28 18:23:38', '2018-12-28 18:23:38', '1', '1', '测试人员', '该用户仅有查看功能');
COMMIT;

-- ----------------------------
--  Table structure for `system_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `system_role_user`;
CREATE TABLE `system_role_user` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `role_id` varchar(36) NOT NULL COMMENT '角色ID',
  `user_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色用户关联表';

-- ----------------------------
--  Records of `system_role_user`
-- ----------------------------
BEGIN;
INSERT INTO `system_role_user` VALUES ('34a2025c113e4f9898a70034d5662dc3', '2021-06-02 12:27:54', '2021-06-02 12:27:54', '1', '1', '3', '2939c1c7227f42f89db43dfe12262c94'), ('40c31f779de24bcc8688c309f962ec1a', '2021-06-02 11:57:39', '2021-06-02 11:57:39', '1', '1', '3', '41a38366559a47e4a85b0d5f8bcbd3b5'), ('4aa9a8e531fe44eaa39ee434b21bfa0b', '2021-06-02 11:57:39', '2021-06-02 11:57:39', '1', '1', '1', '41a38366559a47e4a85b0d5f8bcbd3b5'), ('ffe232508e6d4ccdac3f97236cd78c81', '2021-06-02 12:27:54', '2021-06-02 12:27:54', '1', '1', '1', '2939c1c7227f42f89db43dfe12262c94');
COMMIT;

-- ----------------------------
--  Table structure for `system_user`
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `user_no` varchar(36) NOT NULL COMMENT '用户编号',
  `mobile` char(11) NOT NULL DEFAULT '' COMMENT '手机',
  `real_name` varchar(50) NOT NULL COMMENT '真实姓名',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_phone` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='后台用户信息';

-- ----------------------------
--  Records of `system_user`
-- ----------------------------
BEGIN;
INSERT INTO `system_user` VALUES ('2939c1c7227f42f89db43dfe12262c94', '2021-06-02 12:27:45', '2021-06-02 12:27:45', '1', '1', '21060212272507456918048065577890', '13800138000', '13800138000', '13800138000'), ('41a38366559a47e4a85b0d5f8bcbd3b5', '2021-06-01 15:07:51', '2021-06-01 15:07:51', '1', '1', '21060115073397860541541482500672', '13800138001', '13800138001', '13800138001');
COMMIT;

-- ----------------------------
--  Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `teacher_user_no` varchar(36) NOT NULL COMMENT '讲师用户编号',
  `teacher_name` varchar(50) NOT NULL DEFAULT '' COMMENT '讲师名称',
  `teacher_mobile` char(11) NOT NULL DEFAULT '' COMMENT '讲师手机',
  `teacher_email` varchar(100) DEFAULT NULL COMMENT '讲师邮箱',
  `position` varchar(50) DEFAULT NULL COMMENT '职位',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `introduce` varchar(2048) DEFAULT NULL COMMENT '简介',
  `teacher_proportion` decimal(5,4) NOT NULL DEFAULT '0.0000' COMMENT '讲师分成比例',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='讲师信息';

-- ----------------------------
--  Table structure for `teacher_apply`
-- ----------------------------
DROP TABLE IF EXISTS `teacher_apply`;
CREATE TABLE `teacher_apply` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `apply_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '审核状态(0:待审核,1:审核通过,2:审核不通过)',
  `apply_result_desc` varchar(1024) DEFAULT NULL COMMENT '审核意见',
  `teacher_user_no` varchar(36) NOT NULL COMMENT '讲师用户编号',
  `teacher_name` varchar(50) NOT NULL DEFAULT '' COMMENT '讲师名称',
  `teacher_mobile` char(11) NOT NULL DEFAULT '' COMMENT '讲师手机',
  `teacher_email` varchar(100) NOT NULL DEFAULT '' COMMENT '讲师邮箱',
  `position` varchar(50) DEFAULT NULL COMMENT '职位',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `introduce` varchar(2048) DEFAULT NULL COMMENT '简介',
  `teacher_proportion` decimal(5,4) NOT NULL DEFAULT '0.0000' COMMENT '讲师分成比例',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='讲师信息-审核';

-- ----------------------------
--  Table structure for `teacher_student_group`
-- ----------------------------
DROP TABLE IF EXISTS `teacher_student_group`;
CREATE TABLE `teacher_student_group` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `teacher_id` varchar(36) NOT NULL COMMENT '讲师用户编号',
  `student_group_id` varchar(36) NOT NULL DEFAULT '' COMMENT '讲师名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `teacher_id_student_group_id` (`teacher_id`,`student_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='老师拥有的学生组';

-- ----------------------------
--  Table structure for `test`
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` varchar(36) NOT NULL,
  `test_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `test`
-- ----------------------------
BEGIN;
INSERT INTO `test` VALUES ('22', null), ('2288', '2020-09-03 20:38:48');
COMMIT;

-- ----------------------------
--  Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常;0:禁用)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `topic_name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `topic_desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `topic_location` tinyint(3) NOT NULL DEFAULT '0' COMMENT '位置(0电脑端，1微信端, 2移动端)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='专区';

-- ----------------------------
--  Table structure for `topic_course`
-- ----------------------------
DROP TABLE IF EXISTS `topic_course`;
CREATE TABLE `topic_course` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常;0:禁用)',
  `sort` int(11) NOT NULL COMMENT '排序',
  `topic_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '专区编号',
  `topic_location` tinyint(3) NOT NULL DEFAULT '0' COMMENT '位置(0电脑端，1微信端)',
  `course_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '课程ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='专区课程关联表';

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `user_no` varchar(36) NOT NULL DEFAULT '0' COMMENT '用户编号',
  `mobile` varchar(11) NOT NULL COMMENT '手机号码',
  `psw_salt` varchar(36) NOT NULL COMMENT '密码盐',
  `user_psw` varchar(255) NOT NULL COMMENT '登录密码',
  `user_source` varchar(255) DEFAULT NULL COMMENT '用户来源(client_id)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_mobile` (`mobile`),
  UNIQUE KEY `uk_user_no` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户基本信息';

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('9d74a8fe001049a2987b01495732fa5d', '2021-06-02 12:27:25', '2021-06-02 12:27:25', '1', '21060212272507456918048065577890', '13800138000', 'b821634651ac4ad7961d54e0f255f242', '4047636da924455a587af1602ac2df2abf25e81e', null), ('abb199145d70457aa8774f7aed877f48', '2021-06-02 12:02:11', '2021-06-02 12:02:11', '1', '21060212021130796170590587471005', '18800000000', '3e5a411542bc48fcaa48eda2e5dfc034', '840c68232a33b647d5c8e88c0f9d988ca9089b71', null), ('cc0136180e2d4cff8a3ee74cf84cb55c', '2021-06-01 15:07:33', '2021-06-01 15:07:33', '1', '21060115073397860541541482500672', '13800138001', '9c74c58dd7364848872f15030f15a19d', '74142c090445c98c88914ca48af0dc577539d21f', null);
COMMIT;

-- ----------------------------
--  Table structure for `user_study_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_study_log`;
CREATE TABLE `user_study_log` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `valid_value` tinyint(3) NOT NULL DEFAULT '1',
  `sort` int(11) NOT NULL DEFAULT '0',
  `course_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '课程编号',
  `chapter_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '章节编号',
  `lesson_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '课时编号',
  `study_user_no` varchar(36) NOT NULL DEFAULT '0' COMMENT '学习的用户编号',
  `study_progress` int(11) DEFAULT '0' COMMENT '学习进度(0表示0%，1表示1%，2表示2%......100表示100%)',
  `study_progess_details` varchar(500) DEFAULT NULL COMMENT '学习进度详细，以英文逗号分开，如值为1,2,3,6,60,65,80   表示学过了1%,2%,3%,6%,60%,65%,80%',
  PRIMARY KEY (`id`),
  UNIQUE KEY `lesson_id_study_user_no` (`lesson_id`,`study_user_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程用户学习日志';

-- ----------------------------
--  Table structure for `video_event`
-- ----------------------------
DROP TABLE IF EXISTS `video_event`;
CREATE TABLE `video_event` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `title` varchar(200) NOT NULL COMMENT '事件主题',
  `trigger_time` int(7) DEFAULT NULL COMMENT '触发时间',
  `type` varchar(1) DEFAULT NULL COMMENT '类型(1提醒,2知识展示,3课程提问,4实时反馈)',
  `itemA` varchar(300) DEFAULT '' COMMENT 'A选项',
  `itemB` varchar(300) DEFAULT NULL COMMENT 'B选项',
  `itemC` varchar(300) DEFAULT NULL COMMENT 'C选项',
  `itemD` varchar(300) DEFAULT NULL COMMENT 'D选项',
  `analysis` varchar(2000) DEFAULT NULL COMMENT '解析。',
  `question_title` varchar(1000) DEFAULT NULL COMMENT '当type类型为3的时候，是题干，其它类型均为内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='试题表';

-- ----------------------------
--  Table structure for `website`
-- ----------------------------
DROP TABLE IF EXISTS `website`;
CREATE TABLE `website` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid_value` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序',
  `favicon_ico_url` varchar(255) DEFAULT NULL COMMENT 'org_logo_ico',
  `logo_url` varchar(255) DEFAULT NULL COMMENT 'org_logo_img',
  `website_title` varchar(255) DEFAULT NULL COMMENT '站点标题',
  `website_keyword` varchar(255) DEFAULT NULL COMMENT '站点关键词',
  `website_desc` varchar(255) DEFAULT NULL COMMENT '站点描述',
  `copyright` varchar(255) DEFAULT NULL COMMENT '站点版权',
  `icp` varchar(255) DEFAULT NULL COMMENT '备案号',
  `gonganbeian_id` varchar(255) DEFAULT NULL COMMENT '公安备案号(用于指向公安备案网站链接的ID)',
  `gonganbeian` varchar(255) DEFAULT NULL COMMENT '公安备案号',
  `weixin_qr_url` varchar(255) DEFAULT NULL COMMENT '站点微信',
  `weixin_xiaochengxu_qr_url` varchar(255) DEFAULT NULL COMMENT '小程序二维码',
  `weibo` varchar(255) DEFAULT NULL COMMENT '站点微博',
  `is_open_external_code` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否开启统计',
  `external_code` varchar(255) DEFAULT NULL COMMENT '统计代码',
  `is_open_qq_service` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否显示客服信息',
  `service_qq1` varchar(50) DEFAULT NULL COMMENT '客服信息1',
  `service_qq2` varchar(50) DEFAULT NULL COMMENT '客服信息2',
  `user_agreement` text COMMENT '用户协议',
  `org_no` varchar(255) DEFAULT NULL COMMENT '机构编号',
  `cookie_domain` varchar(255) DEFAULT NULL COMMENT 'cookie作用域',
  `cookie_token_name` varchar(255) DEFAULT NULL COMMENT 'cookie的token名称',
  `pcweb_main_url` varchar(255) DEFAULT NULL COMMENT 'PC网站地址(带http://或者https://开头)，可以带工程名',
  `mobile_main_url` varchar(255) DEFAULT NULL COMMENT '移动网站地址(带http://或者https://开头)，可以带工程名',
  `main_url` varchar(255) DEFAULT NULL COMMENT '网站主域名(不需要带http://等开头，直接如test.com即可)',
  `main_seal_content` varchar(20) NOT NULL COMMENT '学习证明主印章名称(大圆的那个印章名称)',
  `second_seal_content` varchar(20) NOT NULL COMMENT '学习证明印章内横着的那行字内容',
  `mobile_contact_us` varchar(500) DEFAULT NULL COMMENT '移动端的"客服信息"',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='站点信息';

-- ----------------------------
--  Records of `website`
-- ----------------------------
BEGIN;
INSERT INTO `website` VALUES ('934374967448227841', '2018-11-09 17:36:00', '2021-06-05 19:28:01', '1', '1', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/804d5b290234488293fb3ddbb8b08617.png', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/81d6b67b3dbd45f3a41541db76d1ae18.jpg', '旺鑫树学习平台', '旺鑫教育-28', '旺鑫教育-3', 'Copyright 2018-2023 广州旺鑫计算机科技有限公司2 版权所有', '粤ICP备19146352号', '-', '-', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/1575464338.png', 'https://atest888.oss-cn-hangzhou.aliyuncs.com/course/1575464338.png', '', '1', '', '0', '1000000', '10000', '<p><br></p>', 'wx', 'localhost', 'WXTOKEN', 'http://localhost:3000', null, null, '旺鑫树学习系统', '学习证明章', '<div style=\"color:red\">联系电话：xxxxx</div>');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
