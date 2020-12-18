/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : newblog

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 18/12/2020 15:53:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_about
-- ----------------------------
DROP TABLE IF EXISTS `tb_about`;
CREATE TABLE `tb_about`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '//内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_about
-- ----------------------------
INSERT INTO `tb_about` VALUES (1, '**🍀个人简介**\n\n亚信java实习生，日子非常无聊，实习很久了，没做过任何业务\n我感觉我是不是有点FW ');

-- ----------------------------
-- Table structure for tb_api
-- ----------------------------
DROP TABLE IF EXISTS `tb_api`;
CREATE TABLE `tb_api`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `api_id` int(11) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '//api名',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//请求地址',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求方式',
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_api
-- ----------------------------
INSERT INTO `tb_api` VALUES (1, 1, 'api模块', NULL, '', NULL, 'api模块', NULL);
INSERT INTO `tb_api` VALUES (2, NULL, '分类条件查询查询api的信息,父pid查询模块所有api', '/api/listApiInfoBack', 'get', 1, '分类条件查询查询api的信息,父pid查询模块所有api', NULL);
INSERT INTO `tb_api` VALUES (3, NULL, '根据swagger2的api文档读取api信息存入数据库', '/api/saveOrUpdateApiFromSwagger', 'post', 1, '根据swagger2的api文档读取api信息存入数据库', NULL);
INSERT INTO `tb_api` VALUES (4, 2, '关于模块', NULL, NULL, NULL, 'About Controller', NULL);
INSERT INTO `tb_api` VALUES (5, NULL, '/获取关于我的信息', '/getAbout', 'get', 2, '/获取关于我的信息', NULL);
INSERT INTO `tb_api` VALUES (6, NULL, '更新关于我', '/updateAbout', 'put', 2, '更新关于我', NULL);
INSERT INTO `tb_api` VALUES (7, 3, '分类模块', NULL, NULL, NULL, 'Category Controller', NULL);
INSERT INTO `tb_api` VALUES (8, NULL, '新增或者编辑分类', '/category/addOrEditCategory', 'post', 3, '新增或者编辑分类', NULL);
INSERT INTO `tb_api` VALUES (9, NULL, '根据id删除分类', '/category/deleteCategory', 'delete', 3, '根据id删除分类', NULL);
INSERT INTO `tb_api` VALUES (10, NULL, '获取所有的标签', '/category/getCategoryList', 'get', 3, '获取所有的标签', NULL);
INSERT INTO `tb_api` VALUES (11, NULL, '查看博客展示分类列表', '/category/listCategories', 'get', 3, '查看博客展示分类列表', NULL);
INSERT INTO `tb_api` VALUES (12, NULL, '分页条件查询分类列表', '/category/listCategory', 'get', 3, '分页条件查询分类列表', NULL);
INSERT INTO `tb_api` VALUES (13, NULL, '查看分类下对应的文章', '/category/*', 'get', 3, '查看分类下对应的文章', NULL);
INSERT INTO `tb_api` VALUES (14, 4, '博客信息模块', NULL, NULL, NULL, 'User Info Controller', NULL);
INSERT INTO `tb_api` VALUES (15, NULL, '获取博主的基本信息', '/blogInfo/getBlogInfo', 'get', 4, '获取博主的基本信息', NULL);
INSERT INTO `tb_api` VALUES (16, 5, '友链模块', NULL, NULL, NULL, 'Friend Link Controller', NULL);
INSERT INTO `tb_api` VALUES (17, NULL, '添加或者修改友链', '/friendLink/addOrEditFriendLink', 'post', 5, '添加或者修改友链', NULL);
INSERT INTO `tb_api` VALUES (18, NULL, '根据关键词分页查询友链', '/friendLink/listLinks', 'get', 5, '根据关键词分页查询友链', NULL);
INSERT INTO `tb_api` VALUES (19, 6, '文章模块', NULL, NULL, NULL, 'Article Controller', NULL);
INSERT INTO `tb_api` VALUES (20, NULL, '查看首页文章', '/article/articles', 'get', 6, '查看首页文章', NULL);
INSERT INTO `tb_api` VALUES (21, NULL, '根据id删除文章', '/article/deleteArticleById', 'delete', 6, '根据id删除文章', NULL);
INSERT INTO `tb_api` VALUES (22, NULL, '根据文章id查询文章信息', '/article/getArticleById', 'get', 6, '根据文章id查询文章信息', NULL);
INSERT INTO `tb_api` VALUES (23, NULL, '根据文章标题分页查询文章列表', '/article/listArticle', 'get', 6, '根据文章标题分页查询文章列表', NULL);
INSERT INTO `tb_api` VALUES (24, NULL, '保存或者更新文章', '/article/saveOrUpdateArticle', 'post', 6, '保存或者更新文章', NULL);
INSERT INTO `tb_api` VALUES (25, NULL, '查询文章归档', '/articles/archives', 'get', 6, '查询文章归档', NULL);
INSERT INTO `tb_api` VALUES (26, NULL, '查看首页文章', '/articles/*', 'get', 6, '查看首页文章', NULL);
INSERT INTO `tb_api` VALUES (27, 7, '标签模块', NULL, NULL, NULL, 'Tag Controller', NULL);
INSERT INTO `tb_api` VALUES (28, NULL, '新增或者编辑标签', '/tag/addOrEditTag', 'post', 7, '新增或者编辑标签', NULL);
INSERT INTO `tb_api` VALUES (29, NULL, '根据id删除标签', '/tag/deleteTag', 'delete', 7, '根据id删除标签', NULL);
INSERT INTO `tb_api` VALUES (30, NULL, '后台获取所有的标签', '/tag/getTagList', 'get', 7, '后台获取所有的标签', NULL);
INSERT INTO `tb_api` VALUES (31, NULL, '后台分页条件查询标签列表', '/tag/listTags', 'get', 7, '后台分页条件查询标签列表', NULL);
INSERT INTO `tb_api` VALUES (32, NULL, '博客查看标签列表', '/tag/tags', 'get', 7, '博客查看标签列表', NULL);
INSERT INTO `tb_api` VALUES (33, NULL, '查看标签下对应的文章', '/tag/*', 'get', 7, '查看标签下对应的文章', NULL);
INSERT INTO `tb_api` VALUES (34, 8, '用户模块', NULL, NULL, NULL, 'User Controller', NULL);
INSERT INTO `tb_api` VALUES (35, NULL, '根据用户角色和昵称分页查询用户列表', '/user/getUserList', 'get', 8, '根据用户角色和昵称分页查询用户列表', NULL);
INSERT INTO `tb_api` VALUES (36, NULL, '/分页单表查询用户列表', '/user/getUserListSignal', 'get', 8, '/分页单表查询用户列表', NULL);
INSERT INTO `tb_api` VALUES (37, NULL, '注册用户', '/user/registerUser', 'post', 8, '注册用户', NULL);
INSERT INTO `tb_api` VALUES (38, NULL, '发送邮箱验证码', '/user/sendEmailCode', 'get', 8, '发送邮箱验证码', NULL);
INSERT INTO `tb_api` VALUES (39, 9, '用户留言模块', NULL, NULL, NULL, 'Message Controller', NULL);
INSERT INTO `tb_api` VALUES (40, NULL, '查看留言列表', '/message/getMessageList', 'get', 9, '查看留言列表', NULL);
INSERT INTO `tb_api` VALUES (41, NULL, '添加留言', '/message/messages', 'post', 9, '添加留言', NULL);
INSERT INTO `tb_api` VALUES (42, 10, '用户登录信息模块', NULL, NULL, NULL, 'User Login Controller', NULL);
INSERT INTO `tb_api` VALUES (43, NULL, '分页获取用户登录信息列表', '/userLogin/getUserInfoList', 'get', 10, '分页获取用户登录信息列表', NULL);
INSERT INTO `tb_api` VALUES (44, 11, '菜单展示模块', NULL, NULL, NULL, 'Menu Controller', NULL);
INSERT INTO `tb_api` VALUES (45, NULL, '获取展示菜单列表', '/menu/getMenuList', 'get', 11, '获取展示菜单列表', NULL);
INSERT INTO `tb_api` VALUES (46, 12, '角色模块', NULL, NULL, NULL, 'Role Controller', NULL);
INSERT INTO `tb_api` VALUES (47, NULL, '查询角色信息', '/role/getRoleList', 'get', 12, '查询角色信息', NULL);
INSERT INTO `tb_api` VALUES (48, 13, '评论模块', NULL, NULL, NULL, 'Comment Controller', NULL);
INSERT INTO `tb_api` VALUES (49, NULL, '查询评论', '/comment/comments', 'get', 13, '查询评论', NULL);
INSERT INTO `tb_api` VALUES (50, NULL, '添加评论或回复', '/comment/comments', 'post', 13, '添加评论或回复', NULL);
INSERT INTO `tb_api` VALUES (51, NULL, '查看回复评论', '/comment/comments/replies', 'get', 13, '查看回复评论', NULL);
INSERT INTO `tb_api` VALUES (52, NULL, '分页获取用户评论列表', '/comment/getUserCommentList', 'get', 13, '分页获取用户评论列表', NULL);
INSERT INTO `tb_api` VALUES (53, 14, '阿里云对象存储ossAPI模块', NULL, NULL, NULL, 'Ali Oss Controller', NULL);
INSERT INTO `tb_api` VALUES (54, NULL, '图片上传', '/uploadImage', 'post', 14, '图片上传', NULL);
INSERT INTO `tb_api` VALUES (56, 15, '所有权限Amdin特有', '/**', 'get,post,delete,put', NULL, '所有权限', NULL);

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article`  (
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '//文章主键id',
  `article_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//文章标题',
  `article_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '//文章内容',
  `article_cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//文章封面',
  `category_id` int(11) DEFAULT NULL COMMENT '//分类id',
  `is_top` tinyint(1) DEFAULT 0 COMMENT '//是否置顶 0：不置顶 1：置顶',
  `is_draft` tinyint(1) DEFAULT 0 COMMENT '//是否草稿 0：不是草稿 1:草稿',
  `create_time` datetime(0) DEFAULT NULL COMMENT '//创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '//更新时间',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES (11, '第一篇测试博客', '这是测试博客可以删除', 'http://xiaoshuaiblog.oss-cn-hangzhou.aliyuncs.com/2020/12/01/d01b790cb0c84328b28b65b39952d2e0.jpg?Expires=1922175813&OSSAccessKeyId=LTAI4GGxUPBP8RgRyaRJD2Lr&Signature=jdmIOg3g%2BaaSx7IC9GOT5vzwiKI%3D', 1, 0, 0, '2020-12-01 09:43:38', NULL);
INSERT INTO `tb_article` VALUES (12, '测试', '测试\n```java\n\n测试java代码块\n\n```\n', '', 1, 0, 0, '2020-12-09 03:00:27', NULL);

-- ----------------------------
-- Table structure for tb_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_tag`;
CREATE TABLE `tb_article_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '//article 和 tag的关联 id',
  `tag_id` int(11) DEFAULT NULL COMMENT '//标签id',
  `article_id` int(11) DEFAULT NULL COMMENT '//文章id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_article_tag
-- ----------------------------
INSERT INTO `tb_article_tag` VALUES (12, 5, 11);
INSERT INTO `tb_article_tag` VALUES (13, 5, 12);

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '//分类id',
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//分类名称',
  `create_time` datetime(0) DEFAULT NULL COMMENT '//创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '//更新时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (1, '测试分类', '2020-11-25 09:29:08', '2020-12-01 09:34:00');

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '//评论主键id',
  `user_id` int(11) DEFAULT NULL COMMENT '//用户id',
  `article_id` int(11) DEFAULT NULL COMMENT '//文章id',
  `comment_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '//评论内容',
  `create_time` datetime(0) DEFAULT NULL COMMENT '//评论时间',
  `reply_id` int(11) DEFAULT NULL COMMENT '//回复用户id',
  `parent_id` int(11) DEFAULT NULL COMMENT '//父评论id',
  `is_delete` tinyint(1) DEFAULT 0 COMMENT '//是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `tb_friend_link`;
CREATE TABLE `tb_friend_link`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '友链主键id',
  `link_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '链接名',
  `link_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '链接头像',
  `link_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '链接地址',
  `link_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '链接简介',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_friend_link
-- ----------------------------
INSERT INTO `tb_friend_link` VALUES (1, '风丶宇的个人博客', 'https://www.static.talkxj.com/avatar/blogger.jpg', 'https://www.talkxj.com/', '成事在人 谋事在天', '2020-12-01 01:46:05');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '//后台菜单id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//菜单列表名字',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//菜单的url',
  `parent_id` int(11) DEFAULT NULL COMMENT '//菜单的父ID',
  `menu_sort` int(255) DEFAULT NULL COMMENT '//菜单排序',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//描述',
  `menu_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//按钮图标',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (1, '首页', '/home', 0, 1, '首页', 'el-icon-s-home');
INSERT INTO `tb_menu` VALUES (2, '用户管理', '', 0, 2, '用户管理', 'el-icon-user');
INSERT INTO `tb_menu` VALUES (3, '用户列表', '/userList', 2, 201, '用户列表', 'el-icon-user-solid');
INSERT INTO `tb_menu` VALUES (4, '登录信息', '/userLoginInfo', 2, 202, '用户登录信息', 'el-icon-location');
INSERT INTO `tb_menu` VALUES (7, '权限管理', NULL, 0, 4, '权限管理', 'el-icon-notebook-1');
INSERT INTO `tb_menu` VALUES (8, '角色分配', '/assignRole', 7, 701, '角色分配', 'el-icon-s-flag');
INSERT INTO `tb_menu` VALUES (9, '菜单分配', '/assignMenu', 7, 702, '菜单分配', 'el-icon-s-grid');
INSERT INTO `tb_menu` VALUES (10, 'API分配', '/assignAPI', 7, 703, 'API分配', 'el-icon-files');
INSERT INTO `tb_menu` VALUES (11, '文章管理', NULL, 0, 5, '文章管理', 'el-icon-tickets');
INSERT INTO `tb_menu` VALUES (12, '博客列表', '/blogList', 11, 1101, '博客列表', 'el-icon-document');
INSERT INTO `tb_menu` VALUES (13, '新增博客', '/blogAdd', 11, 1102, '新增博客', 'el-icon-folder-add');
INSERT INTO `tb_menu` VALUES (14, '标签管理', '/tag', 11, 1103, '标签管理', 'el-icon-collection-tag');
INSERT INTO `tb_menu` VALUES (15, '分类管理', '/category', 11, 1104, '分类管理', 'el-icon-menu');
INSERT INTO `tb_menu` VALUES (16, '消息留言', NULL, 0, 6, '消息留言', 'el-icon-message');
INSERT INTO `tb_menu` VALUES (17, '评论列表', '/comment', 16, 1601, '评论列表', 'el-icon-chat-dot-round');
INSERT INTO `tb_menu` VALUES (18, '留言列表', '/message', 16, 1602, '留言列表', 'el-icon-message');
INSERT INTO `tb_menu` VALUES (19, '友链其他', '', 0, 7, '友链其他', 'el-icon-guide');
INSERT INTO `tb_menu` VALUES (20, '友链列表', '/friendLink', 19, 1901, '友链列表', 'el-icon-link');
INSERT INTO `tb_menu` VALUES (21, '关于我', '/about', 19, 1902, '关于我', 'el-icon-view');
INSERT INTO `tb_menu` VALUES (22, '接口文档', '/swagger', 19, 1903, '接口文档', 'el-icon-printer');
INSERT INTO `tb_menu` VALUES (23, '个人中心', '/settings', 0, 8, '个人中心', 'el-icon-s-tools');

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//ip',
  `ip_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//ip地理位置',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//头像',
  `message_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '//留言内容',
  `time` tinyint(1) DEFAULT NULL COMMENT '//弹幕速度',
  `create_time` datetime(0) DEFAULT NULL COMMENT '//发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_message
-- ----------------------------
INSERT INTO `tb_message` VALUES (1, '127.0.0.1', '', '天不生我傅瓜皮', 'https://xiaoshuaiblog.oss-cn-hangzhou.aliyuncs.com/2020/avatar/avatar.jpg', '留言测试', 4, '2020-12-03 03:37:50');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, 'admin', '管理员');
INSERT INTO `tb_role` VALUES (2, 'user', '普通用户');
INSERT INTO `tb_role` VALUES (3, 'test', '后台测试');

-- ----------------------------
-- Table structure for tb_role_api
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_api`;
CREATE TABLE `tb_role_api`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `api_id` int(11) DEFAULT NULL COMMENT 'api的主键id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 149 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_api
-- ----------------------------
INSERT INTO `tb_role_api` VALUES (61, 1, 56);
INSERT INTO `tb_role_api` VALUES (91, 3, 2);
INSERT INTO `tb_role_api` VALUES (92, 3, 5);
INSERT INTO `tb_role_api` VALUES (93, 3, 10);
INSERT INTO `tb_role_api` VALUES (94, 3, 11);
INSERT INTO `tb_role_api` VALUES (95, 3, 12);
INSERT INTO `tb_role_api` VALUES (96, 3, 13);
INSERT INTO `tb_role_api` VALUES (97, 3, 18);
INSERT INTO `tb_role_api` VALUES (98, 3, 20);
INSERT INTO `tb_role_api` VALUES (99, 3, 22);
INSERT INTO `tb_role_api` VALUES (100, 3, 23);
INSERT INTO `tb_role_api` VALUES (101, 3, 25);
INSERT INTO `tb_role_api` VALUES (102, 3, 26);
INSERT INTO `tb_role_api` VALUES (103, 3, 30);
INSERT INTO `tb_role_api` VALUES (104, 3, 31);
INSERT INTO `tb_role_api` VALUES (105, 3, 32);
INSERT INTO `tb_role_api` VALUES (106, 3, 33);
INSERT INTO `tb_role_api` VALUES (107, 3, 35);
INSERT INTO `tb_role_api` VALUES (108, 3, 36);
INSERT INTO `tb_role_api` VALUES (109, 3, 38);
INSERT INTO `tb_role_api` VALUES (110, 3, 40);
INSERT INTO `tb_role_api` VALUES (111, 3, 41);
INSERT INTO `tb_role_api` VALUES (112, 3, 43);
INSERT INTO `tb_role_api` VALUES (113, 3, 45);
INSERT INTO `tb_role_api` VALUES (114, 3, 47);
INSERT INTO `tb_role_api` VALUES (115, 3, 49);
INSERT INTO `tb_role_api` VALUES (116, 3, 50);
INSERT INTO `tb_role_api` VALUES (117, 3, 51);
INSERT INTO `tb_role_api` VALUES (118, 3, 52);
INSERT INTO `tb_role_api` VALUES (119, 3, 54);
INSERT INTO `tb_role_api` VALUES (120, 2, 2);
INSERT INTO `tb_role_api` VALUES (121, 2, 5);
INSERT INTO `tb_role_api` VALUES (122, 2, 10);
INSERT INTO `tb_role_api` VALUES (123, 2, 11);
INSERT INTO `tb_role_api` VALUES (124, 2, 12);
INSERT INTO `tb_role_api` VALUES (125, 2, 13);
INSERT INTO `tb_role_api` VALUES (126, 2, 15);
INSERT INTO `tb_role_api` VALUES (127, 2, 18);
INSERT INTO `tb_role_api` VALUES (128, 2, 20);
INSERT INTO `tb_role_api` VALUES (129, 2, 22);
INSERT INTO `tb_role_api` VALUES (130, 2, 23);
INSERT INTO `tb_role_api` VALUES (131, 2, 25);
INSERT INTO `tb_role_api` VALUES (132, 2, 26);
INSERT INTO `tb_role_api` VALUES (133, 2, 30);
INSERT INTO `tb_role_api` VALUES (134, 2, 31);
INSERT INTO `tb_role_api` VALUES (135, 2, 32);
INSERT INTO `tb_role_api` VALUES (136, 2, 33);
INSERT INTO `tb_role_api` VALUES (137, 2, 35);
INSERT INTO `tb_role_api` VALUES (138, 2, 36);
INSERT INTO `tb_role_api` VALUES (139, 2, 38);
INSERT INTO `tb_role_api` VALUES (140, 2, 40);
INSERT INTO `tb_role_api` VALUES (141, 2, 41);
INSERT INTO `tb_role_api` VALUES (142, 2, 43);
INSERT INTO `tb_role_api` VALUES (143, 2, 45);
INSERT INTO `tb_role_api` VALUES (144, 2, 47);
INSERT INTO `tb_role_api` VALUES (145, 2, 49);
INSERT INTO `tb_role_api` VALUES (146, 2, 50);
INSERT INTO `tb_role_api` VALUES (147, 2, 51);
INSERT INTO `tb_role_api` VALUES (148, 2, 52);
INSERT INTO `tb_role_api` VALUES (149, 2, 54);

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '//角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '//菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_role_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_user`;
CREATE TABLE `tb_role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_user
-- ----------------------------
INSERT INTO `tb_role_user` VALUES (1, 1, 1);
INSERT INTO `tb_role_user` VALUES (2, 2, 2);

-- ----------------------------
-- Table structure for tb_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag`  (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '//标签id',
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//标签名称',
  `create_time` datetime(0) DEFAULT NULL COMMENT '//创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '//更新时间',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_tag
-- ----------------------------
INSERT INTO `tb_tag` VALUES (5, '测试标签', '2020-12-01 09:42:04', NULL);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简介',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT 0 COMMENT '0:可用  1：逻辑删除',
  `is_silence` tinyint(1) DEFAULT 0 COMMENT '0：不禁言  1：禁言',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '$2a$10$jalwt9mC7GWOkM5HjZroeOuD/1xhXcqvXgSYYX7ruCXBSeIX/ypW6', '天不生我傅瓜皮', 'https://xiaoshuaiblog.oss-cn-hangzhou.aliyuncs.com/2020/avatar/avatar.jpg', '带薪自学的java实习生', '密码6个0', '2020-10-23 13:52:14', NULL, 0, 0);
INSERT INTO `tb_user` VALUES (2, 'user', '$2a$10$jalwt9mC7GWOkM5HjZroeOuD/1xhXcqvXgSYYX7ruCXBSeIX/ypW6', '普通用户', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3188491167,862997468&fm=26&gp=0.jpg', '普通用户的简介', '密码6个0', '2020-10-31 13:52:20', NULL, 0, 0);
INSERT INTO `tb_user` VALUES (3, 'testUser', '$2a$10$fdIs2r3M5uzHzuByZaeqLuZ2anogRZzCYQh.bS/TmnG9IjRzmKz3O', '默认昵称', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3188491167,862997468&fm=26&gp=0.jpg', NULL, '密码6个0', '2020-12-01 09:10:04', NULL, 0, 0);

-- ----------------------------
-- Table structure for tb_user_login
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_login`;
CREATE TABLE `tb_user_login`  (
  `user_login_id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `ip_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录的ip地址',
  `ip_sources` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ip地址来源',
  `login_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录类型',
  `login_time` datetime(0) DEFAULT NULL COMMENT '登录时间',
  `last_login_time` datetime(0) DEFAULT NULL COMMENT '上次登录时间',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录头像',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户角色',
  PRIMARY KEY (`user_login_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
