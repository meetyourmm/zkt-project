/*
Navicat MySQL Data Transfer

Source Server         : db.ibf.cn[root]
Source Server Version : 50505
Source Host           : db.ibf.cn:3306
Source Database       : ag_admin_v1

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-01-17 10:11:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_api_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_api_log`;
CREATE TABLE `sys_api_log` (
  `id` varchar(32) NOT NULL COMMENT '序号',
  `name` varchar(255) DEFAULT NULL COMMENT '接口名称',
  `uri` varchar(255) DEFAULT NULL COMMENT '请求api路径',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `create_host` varchar(255) DEFAULT NULL COMMENT '操作主机',
  `state` int(11) DEFAULT NULL COMMENT '返回状态码',
  `result` varchar(2000) DEFAULT NULL COMMENT '返回结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_api_log
-- ----------------------------
INSERT INTO `sys_api_log` VALUES ('004d2506bff5449896a581a205a78db9', 'getUserPage', '/api/user/page', '2018-12-25 16:55:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('00a71a2bea1741f4b6a4c1a95dc36a43', 'getAccessToken', '/auth/login', '2018-12-20 11:55:24', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('00a8d8befb784fa893010c652d4584b5', 'validateCode', '/api/group/validateCode', '2018-12-20 15:21:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('00eca143444f41779e387779784d400b', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:39', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('01152c38a79e47c0bb6ff418251af4c2', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 17:04:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('01605d2f9de44b08933679fb5376eb3b', 'getUserPage', '/api/user/page', '2018-12-25 15:59:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('020312d2ac914ba89496a58fd6ec75a5', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 15:55:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('02283200852a4dc9bb989011ae2d67eb', 'getMenuById', '/api/group/getObj', '2018-12-20 15:37:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('02880e27796c4b5497b0d62e08885e47', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:07:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('03159f603f0d41be95d8021ffccd42b0', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:25:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0341aba092f742b1b501edb2ad2647a7', 'getMenuById', '/api/group/getObj', '2018-12-20 15:21:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0353e60e671e4ef697700d5253f52dde', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:26:02', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('03f8099c7080406eb46b4c8b5aa76b5e', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:42:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('04271d8031844478bebc85dd6c50a21d', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:05:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0451c96ed0204a8987bdb92fceb5105b', 'getUserPage', '/api/user/page', '2018-12-24 16:25:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('052c03bca37846f58f122aab6fd73702', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-25 16:05:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('052e88f38ce9478c9da43156c0d5d80a', 'getUserInfo', '/api/user/info', '2018-12-20 14:50:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0548762d204e4d5e9f5656a7b81274cd', 'getMenuById', '/api/group/getObj', '2018-12-20 14:42:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('055730d545fc4ec58ea3d16c561dc0d8', 'delGroupUsers', '/api/group/delGroupUsers', '2018-12-24 17:26:29', '2', '127.0.0.1', '500', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\'\' at line 1\r\n### The error may involve com.zkt.project.admin.mapper.GroupMapper.deleteGroupUsers-Inline\r\n### The error occurred while setting parameters\r\n### SQL: delete from sys_group_user where id in ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\'\' at line 1');
INSERT INTO `sys_api_log` VALUES ('05fbd57926e5433187be7be66a620a12', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:29:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0696c3cc4bdb4123b83a93e87fdfde84', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:44:44', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('08314047246d4b788350734c68cafb02', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 15:49:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('08cb71c1fca741388e04a8f2247d3954', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-26 16:22:14', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('08e3dd0a23dc4675bfe6acb3e3fc76ee', 'getUserInfo', '/api/user/info', '2018-12-19 15:33:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('096a42b1ae95499e9e5bd7a110292edb', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:55:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('09fde662daee4647854d3c8bc1195014', 'getUserPage', '/api/user/page', '2018-12-25 16:45:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0a1dbf445c14405dbc1e7d83ed3dc60c', 'getMenuById', '/api/group/getObj', '2018-12-25 16:53:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0a52544831dd4793b4d5f654a69f2c30', 'validateCode', '/api/group/validateCode', '2018-12-20 15:21:20', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0a5e1cdead6f45b9bfa7a1bafd662d17', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 11:55:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0a95a29c15794482af0fcf8108f78665', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:55:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0b4472809e7344db94e0cfdb6ddc292c', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:55:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0b44f33fb75e4fce9a15e108dc953407', 'getUserInfo', '/api/user/info', '2018-12-25 16:53:27', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0be65f1800dd470497dc56772c702d1e', 'getMenuById', '/api/menu/getObj', '2018-12-19 15:33:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0be7c2d0669d4c539d9ccc8726e76b84', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:12:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0bef4797245b47ea94889ff6bc26bc53', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:50:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0bf039b9901b4723a9340ee6f5d54c64', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:02:48', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0bf8fb90c9aa46a587a666136201dc49', 'getUserPage', '/api/user/page', '2018-12-25 16:12:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0bffe1e3a49844ce91dc1204bf9dca90', 'getAccessToken', '/auth/login', '2018-12-20 14:29:58', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0c9f6d2458884f35a0f695b7905abd57', 'getUserPage', '/api/user/page', '2018-12-20 11:06:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0cc25835e80c406ab64561b07ab6d8d3', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 16:25:59', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0cdec35f3bb04fbebcc26389c68a0eed', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:02:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0cf4f6f98b054950be3f8437e6e32c75', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:07:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0d13a9cb7d7c42c59c913059a2c1ce46', 'getMenuById', '/api/group/getObj', '2018-12-20 14:42:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0d37e1845ca8432c963a0d94e94de9bd', 'getUserPage', '/api/user/page', '2018-12-25 16:12:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0d5cfecec6f943969ea60baedf9023a6', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:05:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0d87dd06f9224caeae3a39f66db587cb', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:53:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0defd96581254671a77e468c83007163', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0e42379112924cea90c5de207024b4a0', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:14:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0e7c35a8e0c44eaabdd3e941972e9b26', 'getUserPage', '/api/user/page', '2018-12-20 17:10:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0f4d4df886c14c63aeec71baab589400', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:09:19', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0f7843da619d468d8aeb5f8f88d41b53', 'getUserInfo', '/api/user/info', '2018-12-20 11:09:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0fb37544f1164ea6a9afd907ec8f380e', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:03:48', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0fe3831726654d858b290ab29999dc85', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:41:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('0fe48950d03a49c3b4cd296f2fb77df9', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 15:48:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('10935049fefb44539312a580cdca5c35', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:05:50', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1117e09b9b5c42c89106e552125f9cd2', 'validateCode', '/api/group/validateCode', '2018-12-20 15:22:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('11639cece0084afeabe748712231571d', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1190c9a166f846c68d67df34c328a8a6', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:07:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('11e8a094722b4673b3fac8b893b045c5', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 17:31:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1215438df44349188737b4c317c70324', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:32:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('12d2b9ef79204033bf8de192776f8fe2', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:15:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('139bbcc6766f4a18be100f1bf864f5a0', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:41:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('13e4ff9cefcf4a92b54a5b57035bb5ce', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('14196453345b4888b12a83a1afd7816f', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:09:02', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1446ec18072448c6b2c60c3613225140', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:36:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('144703744b084eed8d26ba4e791fda33', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:09:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1449c8edd75a45e097ec205da66cdd85', 'getUserPage', '/api/user/page', '2018-12-25 16:47:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('146c21a8991e4d2182b38a05ae2a340e', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:23:20', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('14a68e64d98f445f81f5c867f4d7f553', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:27:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('14fa4db446f248739ac43df017d49d92', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:24:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1588fb85e0ad44589e71338fd5c9fcfe', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:38:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('15b3936605ca4bc4ae6846bb3636bc3d', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 16:25:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('160fc7c1ef5843cf85afcc997e253819', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1632b3df45b24098b8aa745ad786b7d6', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-26 16:22:12', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('164dd78367ca4a40bb1cd53a52d0168f', 'updateMenu', '/api/group/putObj', '2018-12-25 16:13:16', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1673a679bb1a4fe896a0df3664b05b64', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 17:31:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('16c421623e924b2581b0435cb9df0a6c', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:13:07', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('17ae3170212744fe925148fdf9ed45e3', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:07:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('17c64a01eeba4baa83915fe83e3e56ca', 'getUserPage', '/api/user/page', '2018-12-20 15:54:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('18745a4c0fac4de293b6831e245fd82e', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:46:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('187b45223ae741c6b45fa3e416f13ce4', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:35:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('18f22d5a85ea45c396889e5b7c279f72', 'getMenuById', '/api/group/getObj', '2018-12-20 14:43:04', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('18ff7b699b4d4370b20e8061f6f78e1e', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:55:36', '2', '127.0.0.1', '500', '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\r\n### The error may involve com.zkt.project.admin.mapper.GroupMapper.addGroupUser-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert sys_group_user(group_id,user_id) values(?,?)\r\n### Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\n; ]; Field \'id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'id\' doesn\'t have a default value');
INSERT INTO `sys_api_log` VALUES ('191c82047cef4134a0af585c74330b7c', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:25:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('193769adc63544f487708c98b072087c', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:09:19', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('199851784b5a4c96be726f7c332d0cf4', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:34:34', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('19cf2d5b394d4d368d6d328bcc0684c4', 'getUserInfo', '/api/user/info', '2018-12-20 10:56:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1a36f3175cc44748b12adac8cb25f70b', 'delGroupUsers', '/api/group/delGroupUsers', '2018-12-25 16:54:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1ae3c3a1612e4e6ca74197a29111d649', 'getUserInfo', '/api/user/info', '2018-12-20 11:55:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1af7be19b3124fa2b7b7d712dd3c242a', 'updateMenu', '/api/group/putObj', '2018-12-20 15:24:21', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1b1b779fa45147e1a49d092311f1548a', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:38:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1b7e6eb63f74465fa7a768e1e7f9a660', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:16:45', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1b82bc6bedc24617b2e015692741e857', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:05:34', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1b834d24c7d841e2be477905e8c798a6', 'getUserPage', '/api/user/page', '2018-12-24 16:25:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1bac3949743344239f51f19e03fa7501', 'getMenuById', '/api/group/getObj', '2018-12-25 16:34:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1be309a63af34a9fa3de5ec20bc65188', 'getUserInfo', '/api/user/info', '2018-12-20 14:29:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1c0bbbdebe824271bc87012a9b964e6f', 'getUserInfo', '/api/user/info', '2018-12-19 14:57:46', '1', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1c2a98136d1940cbaab705abc0169b06', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:03:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1c476c9bf41b4451af3df5b06d94f03d', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:09:19', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1c576359f7064d7b86acf975b66bfe2e', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:35:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1c59b39b3cc94614adc9e473f41df813', 'getUserPage', '/api/user/page', '2018-12-25 16:46:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1c71ea37319e42b88acec0a1911c73a0', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:07:50', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1c8849fb09e943b2a472d27424a8f4b4', 'getMenuElementList', '/api/menu/getElementList', '2018-12-19 15:45:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1c913beb20c14d689a22ffdc3bf73e3b', 'getAccessToken', '/auth/login', '2018-12-20 10:55:26', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1cd3f498f6b944f78dd184b777efa846', 'getUserPage', '/api/user/page', '2018-12-25 16:48:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1cd7a3bbc29747039731f80c102c173b', 'getUserPage', '/api/user/page', '2018-12-25 16:44:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1d000e7bf985462e94fbad54f210a0b9', 'validateCode', '/api/group/validateCode', '2018-12-20 15:24:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1d485148af1543d788062ae4149cb5f1', 'getMenuById', '/api/menu/getObj', '2018-12-20 15:14:34', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1de7624c9c7c4effa7833457b98b3ea5', 'getUserPage', '/api/user/page', '2018-12-25 17:17:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1df6319a2a65405bacafd174868ad3c3', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:09:16', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1e165c07de8041459a10730ae54453a4', 'getMenuById', '/api/menu/getObj', '2018-12-20 15:14:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1e244a5202ec4da9bd770e37a540d14e', 'getAccessToken', '/auth/login', '2018-12-20 11:01:44', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1e2b9c6980c543ce90e52bc7dbba8680', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:26:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1e4cf07cedf24b2192eb9e2828070b2a', 'getMenuById', '/api/group/getObj', '2018-12-24 17:03:39', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1e998e232b9343dfb1ccb6584b60f974', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:27:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1ed22d97c6544c3281bf7e3b34b39f54', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:10:06', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1ee1eec9f65e47268bed07416270345e', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:14:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1ee62101f73a4ce8a9a8b94f6a1975f3', 'validateCode', '/api/group/validateCode', '2018-12-20 15:25:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1ee7dd1b5be04beaa19ef8228d8d5a5c', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:04:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1f66c8d2973d4c2982a4e9ddba1f58c0', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-26 16:22:12', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1f7d54c7a5e9479a9261cef839a86a7a', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:54:55', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1fa1a7e5827840c18891a2dcc706144f', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:22:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('1ffe73e476f64183a7dabbd7af1ccf5f', 'getMenuById', '/api/menu/getObj', '2018-12-20 10:53:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2026ee82288b4c379b4f3be537837150', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 14:38:01', '2', '127.0.0.1', '500', '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'update_user\' in \'field list\'\r\n### The error may exist in com/zkt/project/admin/mapper/GroupMapper.java (best guess)\r\n### The error may involve com.zkt.project.admin.mapper.GroupMapper.selectByExample-Inline\r\n### The error occurred while setting parameters\r\n### SQL: SELECT  id,code,name,parent_id,path,group_type,order_num,description,create_time,create_user,update_time,update_user,status  FROM sys_group  WHERE       (  group_type = ? ) order by order_num asc\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'update_user\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'update_user\' in \'field list\'');
INSERT INTO `sys_api_log` VALUES ('203418371e2543078339d914400389e6', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:15:27', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('20669fed407d4d6793f79cc6fbb4ac74', 'updateMenu', '/api/menu/putObj', '2018-12-19 15:45:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('21042bf1b6ad4fc690d4b72798f1b951', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:26:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2135305dcbe94c2cbfa7beea0580f8fe', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:30:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('21aae0d5cd054af1993848e2227ff29e', 'updateMenu', '/api/group/putObj', '2018-12-20 15:21:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('21c93125aaf543bead9d1c855c39aad4', 'getMenuById', '/api/group/getObj', '2018-12-20 14:42:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('22186516eebb4741bade452f1ecb2a07', 'getMenuById', '/api/menu/getObj', '2018-12-20 15:37:20', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('222e56d8f881460e83484883813f7759', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:42:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2280518743f84ab287f0d7862ec08354', 'getMenuById', '/api/group/getObj', '2018-12-20 15:22:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('22a355ca262c4224a591f4856d509ee1', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 15:49:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('22b953c8f9bf4c718a35f40f81cf8b66', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 15:59:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('23406d301aa941e7932b5bd9c77b0a79', 'getMenuById', '/api/group/getObj', '2018-12-20 14:42:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('23b2ea8985cd4d3ab8fe08d39bbe7d48', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 14:50:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('242ade960d894fe9a3ffb24f245b145d', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:14:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('243fa37612c94c508f9a84182c7dc885', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 16:59:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2470401e8a9f42c4a3afd766436a0515', 'getMenuById', '/api/group/getObj', '2018-12-24 17:23:20', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('24fde02efef84a9a9dd11d969ac8efc0', 'getMenuElementList', '/api/menu/getElementList', '2018-12-19 15:42:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2527189f0a2541b68a11566ba2e2aeb8', 'getUserInfo', '/api/user/info', '2018-12-20 17:15:35', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2554bb11d9d344af8244004f0619c474', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:50:45', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2581af9c3d1d4918afcf0dbf87efdd9c', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:53:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2639a4c6cece44ee956adbc9669c8c0c', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 16:59:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('26633bcd620947beb9851814d0465080', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:34:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('26a7dd017d844980a8de11944084d7c4', 'getUserPage', '/api/user/page', '2018-12-25 16:04:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('26e4ceb741f54086aa529afe7bf33e40', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:16:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('26ef6dfa73504ab2a76e28505e1db82d', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('28190bdec395445389cf8f72923c8a6a', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 10:53:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2827eec8415d48fd92b7f1a53d50de5f', 'validateCode', '/api/menu/validateCode', '2018-12-20 15:21:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('29abad28b382427bad0b8832d1a908d1', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 17:07:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2a4fac347825438f80819d60192d392e', 'getUserPage', '/api/user/page', '2018-12-25 16:46:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2aba9cbe25584cdf99e06be763f24f36', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:13:27', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2abb939962d24e29a2b84a986c3f8950', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:40:07', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2b4ba9b9c0d84aa1b76e306b6eb121e9', 'getMenuById', '/api/group/getObj', '2018-12-20 14:50:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2b7aa19b8ac249a587559297d81ff5a9', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:42:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2bbc06cc1dae479999d6bcb709d34e06', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 17:27:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2c0f694de31b4826b745758492f9deb8', 'getUserPage', '/api/user/page', '2018-12-25 16:46:39', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2d15ad59b8eb4d32a3b1364ab145d097', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:40:07', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2d5407ea3cba4a0bbf2d993bd5bb4654', 'getMenuElementList', '/api/menu/getElementList', '2018-12-19 15:35:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2d5465ab9c8644349cd3ff0b1ae4c750', 'getUserPage', '/api/user/page', '2018-12-25 16:07:19', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2d656317c60b4985a0597f93d09ae332', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:55:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2d76f14aba1942c8921fb05fb12c6526', 'getUserPage', '/api/user/page', '2018-12-24 17:25:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2de692f9c3e24905b616e8a485535e98', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:09:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2e15ccab6c9b42c68ad6d8203138f545', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:45:19', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2e1db3c0428c4978b39c84b7c5f9f097', 'getUserInfo', '/api/user/info', '2018-12-25 17:04:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2eacf5f0409141d4a76a7c77fbac4a5a', 'getAccessToken', '/auth/login', '2018-12-20 10:55:56', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2ef12bf8cce341fd97fda617a2de3b02', 'updateMenu', '/api/menu/putObj', '2018-12-20 15:21:34', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('2f5e1472767c47e2b551ca73f8592d74', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:46:09', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('2f8fc87852b64e0c9f78aaf5580eec3b', 'getMenuElementList', '/api/menu/getElementList', '2018-12-25 16:05:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3043953336474ecd80bc4364bc36e290', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 16:56:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('31f8f753c632495eb1a694bb1ad78de6', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 16:59:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('32103242069541f18f2dbff452974ac3', 'getUserById', '/api/user/getObj', '2018-12-20 13:16:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3281532ba0bd4e559417854398aafc7f', 'getMenuById', '/api/group/getObj', '2018-12-20 16:23:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('329a948786cb477e9319c542d8cb3b9c', 'getMenuElementList', '/api/menu/getElementList', '2018-12-19 15:34:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('32ca2d223ce04f98aa24b5b97bee0bd4', 'getUserPage', '/api/user/page', '2018-12-25 17:32:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('32fbd3a9485f4276a75141b99f994438', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:04:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('337322a3a17d48659a42f88cc127d1bd', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:55:08', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('33d4c876c88d46cb9aeabc64fc80509a', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:21:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('33f4fa98c88341e7a1fdb6564a6d8e07', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:12:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3420552a63804e809d1e8e3a03e4f298', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:10:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('343314033cb14d40b3e6836bef656c3a', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:43:04', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3453a79002374bcca1c4a4896cc2ff0a', 'getUserPage', '/api/user/page', '2018-12-25 15:50:45', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('35c62cff9376469aac199ccbcf86d0b1', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:29:07', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('35f48558ba8e4f00a3ad47568cf592af', 'getMenuById', '/api/menu/getObj', '2018-12-20 10:53:16', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3608eac91afa404bb4205f191abd094d', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:15:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('362ccee93e754ff4af06cff5c4f1fb4c', 'getMenuById', '/api/group/getObj', '2018-12-20 14:43:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3645d253720b400fb0a120f4ac33cff2', 'getUserInfo', '/api/user/info', '2018-12-20 17:21:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('368a42ee70e041b39193c153c7c2b892', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:31:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3776d0792c144151acd52d1f7e26445b', 'getUserPage', '/api/user/page', '2018-12-25 17:32:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('388a59baf7454bfbbc5eb36fa5fe3fff', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:03:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('399c7e649511473596328bec33340a30', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:25:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3a030e275e334252a926698cbe8ca606', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:20:14', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3af77425492844348ecac05f92c6c7a7', 'getMenuById', '/api/group/getObj', '2018-12-20 14:42:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3b100d5f70e9448ebbf87ad1da0913ef', 'getMenuById', '/api/group/getObj', '2018-12-20 14:43:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3bd018bb3b84422995b226db3328d400', 'getMenuById', '/api/group/getObj', '2018-12-25 17:07:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3c06e3364c5c473294b7d94de0dd3b1c', 'getUserInfo', '/api/user/info', '2018-12-20 16:32:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3c1f58041aa1443da63ac4ff9f441fcd', 'addMenu', '/api/menu/addObj', '2018-12-20 14:33:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3c4f3fffe5ba47eb90d1af7c6bf0a6ca', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:29:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3c542d8943c04c1b8ab07d86f4c9b096', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:09:14', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3c5da28594f44ee0a0befe7031ebd134', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:25:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3cc18aa57a3d4cfca86380467edc6e6a', 'getUserPage', '/api/user/page', '2018-12-26 16:21:06', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3d055fed6f6a409caf21e905f847521e', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:06:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3d0ff2e5ad794a28ae7f1168a835101d', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:21:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3d22ed7d55c84aa5838a6e937ad3b59d', 'getUserInfo', '/api/user/info', '2018-12-20 17:07:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3dd3602013fb48a29a8490a5d4c1de3e', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:33:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3e08dad9502b45008f8a201bfa13b4cc', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 10:53:14', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3e78b7671711417cac35fdfa58109605', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-26 16:17:55', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3f2d88a327f2457da69e8879dad329e0', 'getUserPage', '/api/user/page', '2018-12-20 15:54:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3f6cdd266afa43718a60fe2b965f7f1d', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3f6e34e8e4b6483984bc1476f9d9ebb5', 'getUserInfo', '/api/user/info', '2018-12-20 16:26:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('3fa63f2e72854558808ffd8a44ee69fc', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:09:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4087fef6c9434d88ae7e9175902ea445', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:38:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4099ead9af474e39865c8cee2f8df52f', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:43:06', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('40d571505c9e48eca9c6c5763e0b971f', 'getMenuById', '/api/group/getObj', '2018-12-20 17:13:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('40dfc95e3cf941e99c356d19fc3e1229', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:33:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('41dec611dfb44cf68ad638db671b7a0d', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:41:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('422f03b7a63f411bb08c8f3036c7124c', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4249ae7b9e8049c4901e2849cd160c01', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:30:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('431cb633ddee4f4c9000f55e75f33f85', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:32:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4333e584f4384ca084a82e268f43de2d', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:17:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('436a608291b543a88afee9845eb7148d', 'validateCode', '/api/group/validateCode', '2018-12-20 15:24:21', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('439ac62efb2d4b8ba434d5b11338ce34', 'getMenuById', '/api/group/getObj', '2018-12-25 16:35:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('43b9bca611b546f3b44a9bd434a9c064', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:31:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('44256747394c4ddeb2eb000b0f3e5dc4', 'getMenuById', '/api/group/getObj', '2018-12-20 15:37:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('444c9c44ef464d62a336cb2a3d8af052', 'getMenuById', '/api/group/getObj', '2018-12-25 16:35:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('445be1cd5ace417e868db0b5b5d15803', 'getMenuById', '/api/menu/getObj', '2018-12-20 15:13:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('451bb7c4a61c44298f26494226382fe4', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:54:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4523606bd5684543ad01942cd9aeb424', 'validateCode', '/api/group/validateCode', '2018-12-20 15:17:16', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4570787e00d6434cb0b23b930f11d2fd', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:14:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('45d3a6cd559b4ac5a956d06c650e59d1', 'getUserInfo', '/api/user/info', '2018-12-25 16:48:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('465134ee2ee444728c2d49f504eefc80', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('46657a41479c43ceaa924083b26b19b0', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:23:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('46666c7f9ce042d2807713e12ea8e9df', 'getMenuById', '/api/group/getObj', '2018-12-20 15:03:08', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('473145d556b14903918aab235d8b14cf', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:21:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('47b536578632469d945472a191f25a5b', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:16:39', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('47b9c6c647754609870a995741a0acbc', 'validateCode', '/api/group/validateCode', '2018-12-20 15:17:09', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('480c97712b8b40f49acc05281308d46f', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:15:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4837216e05f3415ea825e406bfa03293', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:42:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('48a04485823a47fabdd6ed5818246278', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:45:05', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('48f05590bc5e409aabb5367e68986378', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:20:50', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('493dcd4339dd4368aec36a3a992d409c', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 13:17:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('495d4cbba2f64631b3c0d6813aad222d', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:31:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4986945df5c74235beb585abf3e7c9d2', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 15:55:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('49dac99fd47d40f6a29efdb2effac18f', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:51:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4a06bada4ba948c5afed9bc2b9620330', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:09:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4a7b76fa52aa42c88a10c2d1cecaf870', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:17:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4ae486417b1e4adcb5f6cb833e73646c', 'getMenuById', '/api/group/getObj', '2018-12-24 17:23:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4b01b66a00f74df5a1e2c1140d41b213', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:03:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4b65521556ee4d9ab378ce836b7834b1', 'getMenuById', '/api/group/getObj', '2018-12-20 14:42:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4b711b619a6e49739a5836f15c2a0651', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:20:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4c1de98b1e7348e8bf27a506fbbcbf7d', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:11:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4c5956c2f83245adbfcfe087a6ce8db0', 'getMenuById', '/api/group/getObj', '2018-12-20 15:20:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4c830d462cfe49fe8b4b19af38d88bc1', 'getUserPage', '/api/user/page', '2018-12-25 15:49:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4ca26c65bf23430ab657d42205e39184', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4cc15038118648d589a21729df78aecf', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:46:53', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('4cc37fef7cfe4c07a306f3d9fd14831f', 'getMenuById', '/api/group/getObj', '2018-12-20 17:14:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4dc9e83f36b341f1bf49e6ad62afcce0', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4e50e9ef1c3d41299ebad845ed50600c', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 17:36:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4e519ecc56304e33ad6a7f25742b5671', 'getMenuById', '/api/menu/getObj', '2018-12-19 15:43:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('4f6fa0ab7ac24796a3f9bd98b84b8cb3', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:36:35', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('50446928a80f46edb9d5365659c2a9d9', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:36:02', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5079a1a8f6ef402bbfec2c6ed6ef38c6', 'getMenuById', '/api/group/getObj', '2018-12-25 16:27:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5122f245c5dd4f4c912943ddd9043562', 'delGroupUsers', '/api/group/delGroupUsers', '2018-12-24 17:35:26', '2', '127.0.0.1', '500', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\' and group_id = null\' at line 1\r\n### The error may involve com.zkt.project.admin.mapper.GroupMapper.deleteGroupUsers-Inline\r\n### The error occurred while setting parameters\r\n### SQL: delete from sys_group_user where user_id in ? and group_id = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\' and group_id = null\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\' and group_id = null\' at line 1');
INSERT INTO `sys_api_log` VALUES ('519a201d52014bdcb8305864231ceb9f', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:04:34', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('519d29537090432797795b503bb7655c', 'getUserPage', '/api/user/page', '2018-12-25 16:46:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5266eba2c84d400b89ffed6694619dfe', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:33:07', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('52d64c77c50e4edea1b780506ae89328', 'getUserPage', '/api/user/page', '2018-12-25 17:30:04', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('531e727607d24f1da7c67a945feff419', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:25:14', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5326520ee1b0424da9f033da4160dede', 'getUserInfo', '/api/user/info', '2018-12-25 15:49:35', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('545f0bdf63da47c387935e7c224df599', 'getUserPage', '/api/user/page', '2018-12-25 17:17:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('54f5f3e22f9f472ab92c7890b0243881', 'getMenuById', '/api/group/getObj', '2018-12-25 16:34:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('54f8823980bc410eb24d4424073d1894', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:25:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('552338269c894b9a9ee9a5472864e974', 'addMenu', '/api/menu/addObj', '2018-12-20 14:33:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('55603c5fbd1f44d79d1877919f003542', 'delGroupUsers', '/api/group/delGroupUsers', '2018-12-25 16:53:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('560eb399ff5d4b85ac7950dc653e6eaa', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:16', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5633f72603754e3683da0e33a97dabdd', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 14:29:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5680f34a022640a89eb0a39507ae5aff', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:35:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('56aa0f334e3b4261baefcf20ec0bfdd2', 'getMenuById', '/api/group/getObj', '2018-12-20 16:46:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('56d3b27725254a1ba3e1dbdfd6f6f082', 'getMenuById', '/api/group/getObj', '2018-12-20 15:22:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('57d444db83e844fc8d8d93092b999614', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:20:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5801083ba26c467a98a368f262008c18', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:56:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('588632cf6f0d466a821100430146af82', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 10:53:16', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('58a134cf61c4490fba7ed6fa5e418388', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:32:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('595ef5f9e9aa463f9b8a08c6cdab64c7', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:04:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('598728da586a4066a2fb62cd03df35d7', 'getUserPage', '/api/user/page', '2018-12-25 17:31:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('59d32bafe4b34d459e84278671e99be4', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:35:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('59ff5e13842d475b8c458ac41b33f47c', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:38:09', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5aaed34a201c4fcb958f5b284fa33500', 'getUserInfo', '/api/user/info', '2018-12-19 15:03:48', '1', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5b1346b5c2ef408ca490a02716c533ce', 'addGroup', '/api/group/addObj', '2018-12-20 15:22:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5b8412d92dad4e6a84fbe3b4c92d25d6', 'getUserPage', '/api/user/page', '2018-12-24 16:17:16', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5b9b1e3750b9439989d4282b558cb90f', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:28:59', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5bb310057f62406893a0667b8b359bf0', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 10:53:14', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5c2b017b4ff946468cd40457b6fab258', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-25 15:58:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5c4261c319294869a69a1fa36cc46fb2', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:24:21', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5c9dc530eca044d18c49d147f8d1d0a9', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:09:19', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5d03ec03a57d4961bf6b5de8d5c57280', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:04:34', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5d0dd5ed6f264202bd6e22d0d7d0a00a', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:24:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5d82c8ba71e64dee80cb831cd075baee', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:06:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5dddb94bf894456b946f71a54582efd9', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:09:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5e9e410554f44ad7aa77d7bdb3fbce69', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:38:59', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5ea27e6d0c9745a18dc1fbfac9d34f4e', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:05:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5ea91d8753cf4cb982f319d464b15ff6', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:17:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5ec6d62bec8749c98fa92948ab127f77', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:22:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5f58ab7f8b474cefae51c93f7de72281', 'getUserPage', '/api/user/page', '2018-12-25 17:22:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('5ff1aae01d2e4266b156e4b5a6a24a8b', 'getUserPage', '/api/user/page', '2018-12-25 17:30:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6021643eb44d47f8a6303f136b7293fe', 'getUserInfo', '/api/user/info', '2018-12-20 15:16:39', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6076ce46937c4dc0a52051a21531119c', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:31:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('60a35d85650c45669b05b5d551a90a02', 'getMenuById', '/api/menu/getObj', '2018-12-25 15:58:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6189ff20713048289f3ca061b9227e28', 'getAccessToken', '/auth/login', '2018-12-20 11:05:06', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('618e18e83c484652be6000ee6c200d26', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:06:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('619c3e6c09354ae5852aeb90e7032c00', 'addGroup', '/api/group/addObj', '2018-12-20 15:25:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('61c30393e49646b3bf86c40716b780e8', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:10:06', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('61fe7c72629040fe9a57b638bdecec61', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 16:25:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('62c936aa1a044d339f1faecbca676435', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:09:16', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('63639e6912c3465d9616b0dac9306233', 'getUserInfo', '/api/user/info', '2018-12-20 11:06:09', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('640a3280b38b4da595eae9693a052c4d', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:17:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('64976d4d55554deca233c3db19a0c747', 'getUserPage', '/api/user/page', '2018-12-20 10:53:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('64f630839f8f42ba8490b38be6e23628', 'validateCode', '/api/group/validateCode', '2018-12-20 15:17:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('65297908ae1445b0b9aad35b1a718b5f', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:46:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('659640c821034a59a2bcd1a61ebf3041', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:54:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('65b5f3f7fad74394b8acfe83d0fc0d78', 'getUserInfo', '/api/user/info', '2018-12-24 17:35:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('65c4f6427e4a46d7a3810b10e49b756e', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:21:34', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6612c22c1fbe4d1480f447ae1654e27c', 'getUserInfo', '/api/user/info', '2018-12-20 14:29:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('662c20d157044fc684f69cf641030ba6', 'getUserInfo', '/api/user/info', '2018-12-25 16:05:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('672168e897b844ea97579d58095cab16', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:53:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('674e255594b64b79b9ba38ee1b2c0f00', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:31:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('67c4bc2b872e481ea66b3dbf181849bc', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:48:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('67d2712e709a4e7280f28a31b7c078a1', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:46:40', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('688d83cbfc6a4d239052d2a2da14ba96', 'getUserPage', '/api/user/page', '2018-12-25 17:14:35', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('698045ace9af41b1856473b8b32fbea4', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:56:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6acbfc6bcdda46f6addd6b6d8b5b5418', 'getAccessToken', '/auth/login', '2018-12-20 10:56:18', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6ae5f1e53f6b4e68aba5c19ee330948d', 'getMenuById', '/api/group/getObj', '2018-12-20 15:14:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6b1f43d521a6441ea4f553144aadbb72', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:31:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6c270f3cd1e54d459b9cd67ef8888a59', 'getMenuById', '/api/menu/getObj', '2018-12-20 15:13:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6c47ffef0e2b4e94981aa101f12abebf', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:45:05', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('6c51ee181afb47d08264016922874819', 'getMenuById', '/api/group/getObj', '2018-12-20 14:43:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6c5cff09d58c48b39ab2b89f54c075fa', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 14:30:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6c792eedb05d419ea9f2bd9f2ec2060a', 'getUserInfo', '/api/user/info', '2018-12-24 17:25:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6ca6a9e9408946cfb6077de7681dc9be', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 17:02:48', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6d7e6acaf8b4411680843ab21e15d849', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:22:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6dc64c10ff2d44de92709e3c8013e799', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6e0e26508fa646718ebf80e8118233ae', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:21:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6e506a8d39f941ba81ad131018504d2a', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 17:29:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6e789ccbcf874832886b77c103b776f4', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:06:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6e837b10e94b4a3e91a5202b46db515f', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:32:04', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6eedbb7222484b36a105cc32d04c24d3', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:12:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6efdc00522f74af3b705cfa5e5559b75', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6eff652bd5094d458d0afc6173060528', 'getUserPage', '/api/user/page', '2018-12-25 16:41:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6f3200b001b742029c7c12b85a4ed98f', 'addMenu', '/api/menu/addObj', '2018-12-20 14:26:51', '2', '127.0.0.1', '500', '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect integer value: \'b964b3d8c35b416a96f52b929c6e0e8a\' for column \'parent_id\' at row 1\r\n### The error may involve com.zkt.project.admin.mapper.MenuMapper.insertSelective-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_menu  ( id,code,title,parent_id,type,description,status,create_time,create_user,update_time,update_user ) VALUES( ?,?,?,?,?,?,?,?,?,?,? )\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect integer value: \'b964b3d8c35b416a96f52b929c6e0e8a\' for column \'parent_id\' at row 1\n; ]; Data truncation: Incorrect integer value: \'b964b3d8c35b416a96f52b929c6e0e8a\' for column \'parent_id\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect integer value: \'b964b3d8c35b416a96f52b929c6e0e8a\' for column \'parent_id\' at row 1');
INSERT INTO `sys_api_log` VALUES ('6f5f304474064d5b9274eb4eba323dba', 'getUserPage', '/api/user/page', '2018-12-25 16:05:09', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6f698fd3b47c40c1a9b708a700ebcd6e', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:32:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6f7e9b006d554d448a701afe7e75eb8b', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:43:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6f825b4a246d46efb1158825034f30c3', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:15:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('6f96af74ada8448c9bf6b6df4b99fd1e', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:45:14', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('6ffc7b765a214ffea49967711e2f7e28', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:07:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('708018ea93fa4eaf95dd9acdf72cae56', 'getMenuElementList', '/api/menu/getElementList', '2018-12-19 15:45:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7085c75f7c5c462c95f61b6db6dbf9ea', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:03:39', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('70aeeafc561b453791b4dbf7afd64bdb', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:42:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('70bdc44967ce4a2aa62529c8ee801bdf', 'getUserInfo', '/api/user/info', '2018-12-20 17:17:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('70edc79ee9c4465cb2d016555864f8fc', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:37:20', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('71115fdb646d4b85a72fdeb29d67a57a', 'getMenuById', '/api/menu/getObj', '2018-12-19 15:45:20', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7159ec26b6dc4618b4f1fbbb931fcfe7', 'getAccessToken', '/auth/login', '2018-12-24 17:25:23', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('71dce5b00cfa4cdcb117656d60eaadec', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:17:48', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('71ef644d930a470c846f5015b66c3a89', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:11:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('71ef82f490a74f92ac629bdfcaa98e31', 'validateCode', '/api/group/validateCode', '2018-12-20 15:21:12', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('72a87bd8a7284108af9837f70e15278b', 'getMenuById', '/api/group/getObj', '2018-12-25 16:36:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('72dad1ab587c459ca57de581024d0597', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:22:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('72f999e1bb29498688de1e1cd4243614', 'getUserInfo', '/api/user/info', '2018-12-19 14:57:57', '1', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7320012b8f2843e8a7ab4796937b58ac', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:16:39', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7332c063d94c48908df317e8e2bd14ac', 'getUserInfo', '/api/user/info', '2018-12-25 16:36:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('733564b345c74801a1244d4ba6f767a8', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:05:07', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('739f41fa129e45e9af7de6b6fff48c94', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:27:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('73d39bc3de6c433c87e9c17aa873cb04', 'getMenuById', '/api/menu/getObj', '2018-12-20 15:13:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('74296069d4bc48f2a48e89d590ea24f7', 'getMenuById', '/api/group/getObj', '2018-12-24 17:23:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7468e6a7fee34359969300a2c9bb22ee', 'getUserPage', '/api/user/page', '2018-12-25 16:39:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('74e4f9efbcb44129b7190a93f9a721ac', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 11:09:02', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('74f0b73a03344f33a9b5211a916d82c7', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:39:07', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('74facb9609994a41a8bbf223fbe21263', 'getUserInfo', '/api/user/info', '2018-12-20 10:55:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('74ff146f75ca4a9cbaaadc08afc7ba92', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:34:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('752559d3468d4a79bace731b586a1adc', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:26:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('757d119e912647a9a9b5102f5b6b198d', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:55:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7625ac3119eb4543a6f266d3df9c5b41', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-25 15:59:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('76e17d46973c43cdb2f0566dc7f82d3f', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:02:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('76fe05ae8f8a4e8b9c011374e528dfb1', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:34:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('77025f4063294874b741bacc4a388ffd', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 14:15:21', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7761fc9f084a470d8b2a6ef0ba5742aa', 'getUserInfo', '/api/user/info', '2018-12-20 14:45:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7798604304984d7499f86561aca58e49', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('77f349f3004947fba45a7518113f4a04', 'getUserInfo', '/api/user/info', '2018-12-19 14:59:48', '1', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('78d9a23b17a04cbb9d96ea24f27e51e1', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 16:25:06', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('78fdd796d5114a9b9ccd8b7d4d9fd096', 'getAccessToken', '/auth/login', '2018-12-20 10:56:21', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('795ce3dac82b40da993773ee7250450b', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:31:07', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('799ec5e8640444ccbfd320f4c33b92d4', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 13:16:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('79e983e8879445c381c9a8512eb93a86', 'getMenuById', '/api/group/getObj', '2018-12-20 16:23:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7a48a22c2336428291a9df9f45c5c940', 'updateMenu', '/api/menu/putObj', '2018-12-19 15:45:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7b17a2912e2f4a1790b3fe87085c9e69', 'getUserPage', '/api/user/page', '2018-12-25 16:06:19', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7b527f4649f84d64b31d9bccf201e2cf', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:34:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7b777fb540614dc8b6e93b38218401b9', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:24:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7bf5e1a8ef09411dafbdb70a95c8c35e', 'getUserInfo', '/api/user/info', '2018-12-19 15:04:30', '1', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7c83b401391b4517a46f6d14a3a5fce4', 'getMenuById', '/api/group/getObj', '2018-12-25 16:34:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7cc1f23e2b51410c9d5a1192e8c74830', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:07:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7ce4b9f097dc4a6693b54fb079de1862', 'getMenuById', '/api/menu/getObj', '2018-12-19 15:45:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7d0f30160e3e4331adcdd4d055078a74', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 17:32:08', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7d2fb5b54dfa46b5ae9d4df24c5be773', 'getMenuById', '/api/group/getObj', '2018-12-25 16:41:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7d3004a4fe614cbea5c5e921a55fbb18', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:20:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7d896fba1dbd44f79f4ce0091e659312', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:26:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7d9e2799865a4ab292f7000815f89c11', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:36:02', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7da4f38fabd347cf92b7975a63203542', 'getMenuById', '/api/group/getObj', '2018-12-20 15:20:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7db942dcf0614484a0248f7f78a2b05e', 'getAccessToken', '/auth/login', '2018-12-20 11:01:20', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7dd1df254c3e43de8403f99122e90bcf', 'getUserInfo', '/api/user/info', '2018-12-20 17:09:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7df251c6a153424c830764add5bc9010', 'getMenuElementList', '/api/menu/getElementList', '2018-12-26 16:17:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7e05ff9ed1de4d8abb6fa7dab6aab16e', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:34:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7e4261af36084b8587c66794dd5691ec', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:20:14', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7e44aa5da0674aec9289bf7777136af0', 'updateMenu', '/api/group/putObj', '2018-12-20 15:24:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7e4a1b8959ce4afea1b816fb3a8191b1', 'getMenuById', '/api/group/getObj', '2018-12-20 14:42:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7e819c650d65490ca9bd6c2b515b892c', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7ec7f7dc1b574670bbee63f3bc94971d', 'getUserInfo', '/api/user/info', '2018-12-20 14:46:20', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7ed88947974545c1bf89c91c73a208c0', 'getUserPage', '/api/user/page', '2018-12-26 16:17:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7ee2351e96774bac9422bb028e79c03f', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:21:21', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7f29c5b31d254cbdb0d8caddacbbef75', 'updateMenu', '/api/menu/putObj', '2018-12-19 15:45:04', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('7fead5c3a806418aa1303a4a7c362ac6', 'getMenuById', '/api/group/getObj', '2018-12-20 15:22:23', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('801f43ba3ed44874a2631379d0380bf0', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-26 16:17:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('80662672a7454f7a921ca4a5e17976bd', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 14:50:55', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('80fcbd7614d94076afa2d4e8ab0c8c84', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 16:59:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8141cd88e90c44f3aebc18d14ee62e55', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 14:36:35', '2', '127.0.0.1', '500', '当前实体类不包含名为type的属性!');
INSERT INTO `sys_api_log` VALUES ('81537c9106db4fec970b1057bf4292e4', 'getUserInfo', '/api/user/info', '2018-12-20 14:25:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('81738330a77a4aae9de1f82df4967af5', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:20:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('81d571d616554da4b8af574ee2aa11e0', 'getMenuById', '/api/group/getObj', '2018-12-25 16:27:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8206107f79dc4067b6b3778f31b37b65', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:56:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('824e87661306443e8d750ccb7c7576bc', 'getMenuById', '/api/group/getObj', '2018-12-24 17:36:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('829ebbf9b94547dca2aca31b3c64b9fa', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:29:59', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('834473383899467889a193f4fccf9cac', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:22:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8356a5b966cb4f58b246ff6ee8964125', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:30:04', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('842eecf7c97e48ca9caca4adb6cfcd71', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:05:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('84644c3a2e62452f80b4f4ec924b3a93', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:28:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('84b1cecf5a2f40ffb512af1d07a9cdc0', 'getAccessToken', '/auth/login', '2018-12-19 15:33:37', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('85235c9f70e54cc88c765810db58d382', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:42:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('854ed3b7ef5b4a4caf11a89177667887', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('85bcf971b3c048d896abdd1490e2ed50', 'getUserPage', '/api/user/page', '2018-12-25 16:14:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('86cc10cc291640a19f4c1e21fa27ba26', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:23:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('86f7abf5e5b841739e555ac44efdf94e', 'getUserPage', '/api/user/page', '2018-12-25 17:26:03', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('86fd22f45d824a3b9ce28593046e7bf4', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-25 16:25:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('870ca0625409452da0c0153d27f0e4f1', 'getUserInfo', '/api/user/info', '2018-12-20 17:05:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8736f1d1836641679b92c5ff549d91d7', 'delGroupUsers', '/api/group/delGroupUsers', '2018-12-24 17:23:27', '2', '127.0.0.1', '500', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\'\' at line 1\r\n### The error may involve com.zkt.project.admin.mapper.GroupMapper.deleteGroupUsers-Inline\r\n### The error occurred while setting parameters\r\n### SQL: delete from sys_group_user where id in ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\'\' at line 1');
INSERT INTO `sys_api_log` VALUES ('875b3d9125dc45d8aae6fa1e3d53ef81', 'getUserPage', '/api/user/page', '2018-12-25 15:48:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8773ecf9e33544e892b449924333086e', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:15:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8782c570f4b540b28a09b75140a76a3a', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:55:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('87e616e6aa2e4482806e49e7a2ce1ec5', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:20:21', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('888ec35df17743e98c17734af47a88b3', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:56:34', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('88bd5a29dffc4044967b7890ae0d5241', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:15:27', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('88c8b47671b14b9a8cb28aec25053b6e', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:34:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('88e1ebca5b3e4940be0504249fe14e95', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:03:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8959bc69c99848189653d16761f4b97b', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:26:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('899874b946aa492cb8703c3a13665ae5', 'getMenuElementList', '/api/menu/getElementList', '2018-12-25 16:25:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('89da637f8be047a88a7b79d8b4bf5fde', 'getUserPage', '/api/user/page', '2018-12-24 17:02:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8a67a0e8f4244427944d71a65d9cd785', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:41:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8a8cc57a625444e3aae7e38a075c9807', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:26:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8b12f5081b0c443ab0a1e5e5c64817f9', 'getMenuElementList', '/api/menu/getElementList', '2018-12-25 15:58:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8b40b458d2ad4cd2b2d5f8a3732dcc99', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:41:35', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8bd2c5232aee4f0e818ee5af1d91a72b', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:09:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8c0c77645e13450b92bad57adfb7576a', 'getMenuById', '/api/menu/getObj', '2018-12-19 15:34:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8c670ac0351d4a918610a899e9811d30', 'getAccessToken', '/auth/login', '2018-12-20 11:01:03', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8c69bd33c9a84625938d7a3df186287e', 'getMenuById', '/api/group/getObj', '2018-12-20 14:50:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8cb3f13f5e1c45fc8225504a3e532a0c', 'updateMenu', '/api/group/putObj', '2018-12-20 15:24:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8cc7d74253f040b4a1aa0ded3abd8938', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:27:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8d741c3b22984386a17a0c9901e993c8', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:35:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8dba389e1a7d42cf9b8c1212e346e664', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:14:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8dbd4b8d5aff4b388c9f650e427e1ea7', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 17:25:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8df96c299696419ab0dde6667d8eae4d', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 17:04:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8ee6dc6b405c4e34a3f99c9c3ed4c2c1', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 16:57:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8f1b7826d8f04e30bb93c3208085f3fa', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:06:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8fa4805c013949fa95115aed01a08483', 'getUserInfo', '/api/user/info', '2018-12-19 14:56:57', '1', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('8ff501ffdfdd40fc863b4b0d1dd60eb6', 'getMenuById', '/api/menu/getObj', '2018-12-20 15:14:35', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9072c6b3bd82424e805b283a987f3d05', 'getUserInfo', '/api/user/info', '2018-12-20 14:27:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9119b941373142e1bafbbc1a9216ec0a', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:29:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('915c29f7991d435b8aab4b7697554efc', 'getMenuById', '/api/menu/getObj', '2018-12-20 15:13:23', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('918fb02fbad74ad69e0d3bab1b112bf9', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:04:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('91f46f72341c4af8bb614c9d93b2205c', 'getMenuById', '/api/group/getObj', '2018-12-25 16:54:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('92b9e11d731d4e2e9d9481b11ddc754c', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:43:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('92d6097db1fc471888e5ba04fd15ee90', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:21:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('936296eb2d984031a87100e004d550f4', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:21:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9369cd33949a4aea85923cb2d0781dc9', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:25:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9391f6b33a1c4518aaa46695e1ffe442', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 16:17:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9572f279f4f14cff8067c4ab310f14d5', 'getMenuById', '/api/group/getObj', '2018-12-25 16:38:59', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9585d3931b31410db695b8339efdf425', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 16:59:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('95b77dffdd9b4d52a5ab8d3ac8c91a6f', 'getUserPage', '/api/user/page', '2018-12-25 16:13:16', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('95e691ef7db04eb2b3115578cc04abb5', 'getUserInfo', '/api/user/info', '2018-12-25 15:48:21', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('95fa7c67975843f8b641788a57c28585', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:22:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('962c47f25fd74de59fd3ba168d24ada6', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:46:20', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('9670621c9f7d4237b5c9f883bcd64d85', 'getUserPage', '/api/user/page', '2018-12-25 16:46:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('96d9894235354779aae7a183870ee71c', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:05:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('96e406f220fa4de3870dfaa2109cffae', 'getMenuById', '/api/group/getObj', '2018-12-20 15:03:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('96f04d52c231425f976380dd8a47cefc', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:48:35', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('97beaa66304e4f7c845e7b1fe89c12f7', 'getMenuById', '/api/group/getObj', '2018-12-25 16:34:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('98687ebe69b14bd79bfab8ddccd42dcb', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:55', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('989016ef7f0f408395bcfb782a4b50c9', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:25:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('98929d48480f439d9b7735efe742bf39', 'getUserInfo', '/api/user/info', '2018-12-25 17:29:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('98e60f2b0e7241249edda8fc294597d2', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:22:23', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('991e50f566c84a5b9691e2f2c7a5bc5b', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 14:47:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9948501d08884ccb93721e68ea4db3ac', 'getMenuById', '/api/group/getObj', '2018-12-20 15:14:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('995ffa262a1e43898cfd9cbde2f363aa', 'getMenuById', '/api/menu/getObj', '2018-12-19 15:34:02', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('99cbbdff89ce45958b0f2045da5ee996', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:28:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9a0b3590a1444834a834fed21b58cd12', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:43:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9a39278c0a304b58990689625fa6557b', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:34:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9a649fc487a9420299bf2a2ecafa8f9b', 'getUserInfo', '/api/user/info', '2018-12-25 17:27:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9ab92c50880941fda2325adf85ac2320', 'getAccessToken', '/auth/login', '2018-12-20 11:06:08', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9abeeaf3a08d4c5fa9930a36a7674b40', 'getUserPage', '/api/user/page', '2018-12-25 17:31:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9b1173fb8f9847e48a4f3f4659c250c4', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:03:08', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9b75f00be7f747dfa51e819a242f299d', 'getMenuElementList', '/api/menu/getElementList', '2018-12-25 15:59:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9bca354844ef42078fe9410a8528a2a9', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9c64b59d2e694bf49e6e44b838a7655a', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 16:57:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9c8bb31e2c0049789324672f4821de74', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:35:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9d5d8265adad4cb5b5392d82870cbc66', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:27:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9d7d14f2b8064cb8892cde9c1d4b2462', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:16:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9dce8742805d47309600efe8ed6a1f75', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:16:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9e4822c84fb6436baf2042a003df8f64', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:31:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9f26cad3add44cf18669916a527c9695', 'validateCode', '/api/group/validateCode', '2018-12-20 15:25:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('9f75c96085424564aa9f6ce44a343f64', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:06:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a01238f73b404344a0f96adda2656feb', 'getUserPage', '/api/user/page', '2018-12-24 16:25:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a024ac45b1a34ab7882d5732ea0e9878', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:20:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a0713c81e27e4797826e921b9a14856a', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:29:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a09b90ed92e14965b7be1607d2a0492f', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 11:06:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a0d30efd0ff9422f8e78d259fc72bbe7', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:04:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a0e6dbe80a9a48d1976ce1f24d47fcce', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a1151f93e8b5417883427bf9e0b024ca', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a16426416cd94c1291da5dd55a43fe59', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 14:27:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a177b257b7314c1281638fa4eb4fc18d', 'getUserInfo', '/api/user/info', '2018-12-20 10:53:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a1f6f60efb1a4b8dbbd96b6279d5432f', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:25:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a2167159f86c42218667d88684d20637', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:56:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a37c8009f3994b579c54d3e6c3f559df', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:25:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a3cad125dc18418fb761a24dc1dc2860', 'validateCode', '/api/group/validateCode', '2018-12-20 15:25:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a3fda174b4d4476b9dc93a76897b6cfa', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:04:02', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a413c31acf2e42b09ada79ab9f214ca5', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:15:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a4254ad0b5624c3cb6dc15530b277334', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:16:48', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a50f83d86f684352acf0d0a06b4246a9', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 17:32:20', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a524aff6a92e4831b3720f738e54c892', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-26 16:17:55', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a53f4937d4b54a0d83b0052cbcfaa627', 'getMenuById', '/api/group/getObj', '2018-12-20 15:22:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a5542b0487e04e9a8692f3e796c3492c', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:09:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a6d755def4924c68aa7a5a70c044eeb8', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:18:08', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a6eadff027e340aaa59938af3cdcc4ea', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:37:23', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a6f7327d3d5f4209a8e549eeaa246e05', 'getUserPage', '/api/user/page', '2018-12-20 14:47:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a715ce991d6e4248981ab6ffddb5dfb7', 'getMenuById', '/api/group/getObj', '2018-12-25 16:38:27', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a90dd854f0154ce393669e744b96efc1', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:05:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a91cd9048fd94e10998ed581b2000d38', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:13:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('a9854144f6de49e7b1e0737ff7315c73', 'getUserInfo', '/api/user/info', '2018-12-20 16:47:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('aa2772f4fd3f465ab203117bd52e063e', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:14:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('aa38eaec3f6e4e5baac31e3779c768c9', 'getMenuElementList', '/api/menu/getElementList', '2018-12-25 15:58:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('aa41554590114a98b5c4ddbdae30e3f5', 'getUserPage', '/api/user/page', '2018-12-20 13:16:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ab54ea08b1e642a7aad334441df83ba0', 'getMenuById', '/api/menu/getObj', '2018-12-19 15:34:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ab68355f56d94b4fa5ab6966a1a81dac', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:09:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ab89ceb7478544f2b09958fdb676559a', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:14:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('abd7126eff194ed49d884a1f4ec30f93', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 17:25:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('abdd6f141120438c9003edcff9bd1e8c', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:26:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ac424d6d8b6b4ebda438d05f36e8a518', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:15:19', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ac4ed3b2366f49799a323a405435d227', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:06:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('acb921b96865431a9836ed66a7cccaaf', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('acc5073c4ae046c2bfea2abd1b95bbe5', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:06:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ace1007769c94cc2a829dce4874caf25', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:31:07', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('acf37fb738604ee6b1c1c7965f1e7277', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:04:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('acffda1b57be469cb4a7f08bbd0e7927', 'getMenuById', '/api/group/getObj', '2018-12-20 15:37:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ade5cbfd526a4387a1dd10c0894f4eba', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:35:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ae24bf77c99b4531ac8846c97a61de61', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:32:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('aeddee70dbee4037a500c5cd44e91ddd', 'delGroupUsers', '/api/group/delGroupUsers', '2018-12-24 17:41:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('aeeb80752b614534b78f59280cea60fc', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 14:47:55', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('af0f9e6648af4d64b8758cf442bd45f3', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:17:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('af76d84251894dcb826aed561fe3dac2', 'getMenuElementList', '/api/menu/getElementList', '2018-12-19 15:34:02', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('af91bab284a54cc99523f97241cd357b', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:17:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('afd6677ba8154f7bb6d1f89198837bca', 'getMenuById', '/api/group/getObj', '2018-12-25 17:04:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b12a187a7fa9468a91a6b6dacfce9485', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 14:50:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b22d9985ed004f579ea4769571863e06', 'getMenuById', '/api/group/getObj', '2018-12-20 15:14:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b2e801ae80f34c3e9ddadb51103683d8', 'getMenuById', '/api/group/getObj', '2018-12-20 15:25:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b309bed7416d4cf3a8c4058b52da1a74', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:16:34', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b30e07203a3747628a5908df3ad991c3', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:04:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b3ea1277b982466db1b93d763d7d95aa', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:43:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b3f084ccd625427496a260e723d1613b', 'getUserInfo', '/api/user/info', '2018-12-24 16:16:50', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b41be0ce2b2c4f7dadc94334fdbcc528', 'getMenuById', '/api/group/getObj', '2018-12-25 16:38:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b45cc01a165141249443cd802d60150c', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 14:25:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b48b5ef1b94b413d9ceb29780a6a1e68', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:25:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b52b7f4e1c3741e1937dda77c8e2bb22', 'getMenuById', '/api/group/getObj', '2018-12-20 14:43:06', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b568dfcc2f874404909fef1fa9bdf4cc', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:16', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b57560c3b9294ca2b4cb9098820c6edc', 'getMenuById', '/api/group/getObj', '2018-12-25 16:34:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b5dbdb0a5e744d32b1ffc74d5d7d26e6', 'getMenuById', '/api/group/getObj', '2018-12-20 14:43:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b5eb624d11774cedb98821d7f39dde03', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:20:50', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b5f81ccc5a0f42018e1904aa9a851922', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:20:21', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b6278b34147e43b592ea11e741c0611a', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:27:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b65bacc79fcb41b186f4985ba0620ee2', 'getUserPage', '/api/user/page', '2018-12-25 16:14:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b666fc53acec467d8cc3869e2c909cb4', 'getUserPage', '/api/user/page', '2018-12-20 17:15:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b7365f51d1564e04a2e16533986a97cd', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:11:09', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b7d2e0f3351f4c8c9a7bdceaa9aaf5c7', 'delGroupUsers', '/api/group/delGroupUsers', '2018-12-24 17:36:54', '2', '127.0.0.1', '500', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(1)\' and group_id = \'cbfbd3863f4d451eaac2ac02900c5a39\'\' at line 1\r\n### The error may involve com.zkt.project.admin.mapper.GroupMapper.deleteGroupUsers-Inline\r\n### The error occurred while setting parameters\r\n### SQL: delete from sys_group_user where user_id in ? and group_id = ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(1)\' and group_id = \'cbfbd3863f4d451eaac2ac02900c5a39\'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(1)\' and group_id = \'cbfbd3863f4d451eaac2ac02900c5a39\'\' at line 1');
INSERT INTO `sys_api_log` VALUES ('b7d88ebe66c9424d8a8a1effef6c9e1a', 'getAccessToken', '/auth/login', '2018-12-24 16:16:48', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b85ddd7daa714bcb8def8c7c47113e35', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 16:25:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('b8b2b3b1cd2845b99452bf47db3fdf3d', 'getUserInfo', '/api/user/info', '2018-12-20 16:22:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ba49a56a43e84512ae980f68e619021d', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:09:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ba6e9177c5d64354a12ae67fd0acbc6e', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:12:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ba760f03e76e49e8b60d159bd7707363', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 15:59:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ba83d1f3c7d14225b068e9c8f3958edd', 'getMenuById', '/api/group/getObj', '2018-12-20 14:43:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ba8549468665493db839184a466d592a', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:13:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ba8d8095e37d4fd694e246e5a31dc680', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:26:02', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('bb4012de587844c8afbb29e348335a21', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:46:29', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('bb549c84677c429e903ee5878c2c96a2', 'validateCode', '/api/group/validateCode', '2018-12-20 15:25:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('bbbf8c02e9ea47d4b57a3bd5833f9fe6', 'getUserInfo', '/api/user/info', '2018-12-24 17:25:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('bc3f256b351c4a7cada81ec483c132ba', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:50:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('bc42ea73b0db44038a11ef3efb1db435', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:02:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('bc960d3f05de488abd30a2a64c515dbe', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:29:07', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('bcf40f2b5dd3454b8a11194a5e1f67a7', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:43:20', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('bcf6cb0cc48f4e2797a35006b239ad08', 'validateCode', '/api/group/validateCode', '2018-12-20 15:22:27', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('bcf81edeb5054328a323d2864b99cb67', 'getUserInfo', '/api/user/info', '2018-12-20 17:16:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('bd5e690122d3439c898e32bc573851e9', 'addMenu', '/api/menu/addObj', '2018-12-20 14:33:07', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('bdc09391215249a59072d3eb24832aef', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 15:48:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('be0003ce898d4d78952f66a5a4ee6b35', 'getUserPage', '/api/user/page', '2018-12-25 16:47:55', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('be72ab70a1f2433d9b8df9fe0dd4c00c', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('be8e8ea8e6454a3691900deb7abd2fa8', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-19 15:33:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('bfe711f979ff4f8abd3370601ab24f87', 'validateCode', '/api/group/validateCode', '2018-12-20 15:17:35', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c0194fb648d34648a0e4b9c5b21f39ce', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:13:22', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c03951e5f2d94fdbac9079eb7e0df59d', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:21:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c0634f7d9515474189c0902875ef772b', 'validateCode', '/api/group/validateCode', '2018-12-20 15:17:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c0c3fe60f83e482486cb055a7f8a18d7', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:11:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c0fc157bd12e4af7984cc696201a4ca9', 'getUserPage', '/api/user/page', '2018-12-25 17:23:20', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c1263082e71e46feb09f4d45b0ae87f5', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:13:23', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c14371512db04c8197280985da55661c', 'validateCode', '/api/group/validateCode', '2018-12-20 15:17:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c1ac95cee5ce4b36ac1cbd9f32ede8f3', 'getAccessToken', '/auth/login', '2018-12-24 17:25:17', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c22510d7a29b4ae0aaa4f6a8615faf1c', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c2b6795f37ec4a9b9922972405702fef', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:32:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c33c1e52afc54786abe145966af7edf2', 'getAccessToken', '/auth/login', '2018-12-20 11:01:39', '', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c35e30e78ee24b33917de930e2798716', 'updateMenu', '/api/group/putObj', '2018-12-25 16:16:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c378dbad055949f9aeee6990ff832233', 'getMenuById', '/api/group/getObj', '2018-12-25 17:17:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c3b5b9ba2a384fb6b340a45858589d43', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:06:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c3dc0b539d404e8bba20853c69afb07a', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:36:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c3ed49f194184c71915f4d6aca884e73', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:27:59', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c440640d924444caab5f9a4efec8133f', 'getUserPage', '/api/user/page', '2018-12-25 16:10:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c45a799bcf6549a2bec1551dbbd1eeab', 'getMenuElementList', '/api/menu/getElementList', '2018-12-26 16:22:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c4652e84792247288b51af0f9203b220', 'getMenuById', '/api/group/getObj', '2018-12-20 15:22:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c47189ce04004e3bb609529acf6efcbb', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c47c20682e7142d2937e17a647b02c49', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c49f93a4242b4b3e8f8a551cf6c0f751', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:07:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c57cbd9951974e01831c8cdaab499e7b', 'getUserInfo', '/api/user/info', '2018-12-20 17:13:09', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c60b25b33d594ba3aa55fb1e1486959a', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:51:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c61ab517aee04fee9c97f8639484429e', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:06:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c623abb6f11040bfbd0e63cd4e5ea50d', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:22:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c6f851ac6c0443cdacf270d8d20200e4', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c80643ad1d954a87b9dcf30cfcf4c830', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:26:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c808533f33904763b0189c14ad5ae0f8', 'getMenuById', '/api/group/getObj', '2018-12-20 14:42:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c847b8d37345413cb797d8d170d99475', 'getMenuById', '/api/group/getObj', '2018-12-25 16:48:35', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c84d4e8d9602433baa52d969ba354ea4', 'getMenuById', '/api/group/getObj', '2018-12-20 15:03:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c8898175f4d14b968cfc0336ed6bb4a4', 'getUserPage', '/api/user/page', '2018-12-25 16:40:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c95d7bae0eb043e1bf947b42311c4bfa', 'getUserInfo', '/api/user/info', '2018-12-20 17:21:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c9b4b957cb814e88b086df2868fc482c', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 14:39:59', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('c9d7eb556fd5416db6151e6674eeea36', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:53:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cbefbcb4b6a044479e62669436c57f4a', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:55:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cbfe109288704e4b8b1858d0c8d532d1', 'addGroup', '/api/group/addObj', '2018-12-20 15:17:48', '2', '127.0.0.1', '500', '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'group_type\' doesn\'t have a default value\r\n### The error may involve com.zkt.project.admin.mapper.GroupMapper.insertSelective-Inline\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_group  ( id,code,name,parent_id,path,description,create_time,create_user,update_time,update_user,status ) VALUES( ?,?,?,?,?,?,?,?,?,?,? )\r\n### Cause: java.sql.SQLException: Field \'group_type\' doesn\'t have a default value\n; ]; Field \'group_type\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'group_type\' doesn\'t have a default value');
INSERT INTO `sys_api_log` VALUES ('cc17a85fb2e14d608cb08f347803f101', 'getUserPage', '/api/user/page', '2018-12-25 15:51:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cc297f7c79a2430db1277e567cd26042', 'getUserPage', '/api/user/page', '2018-12-20 14:47:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cc3216413c0e4e6dbc20b188c6173cfc', 'getMenuById', '/api/group/getObj', '2018-12-25 16:35:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cc37fa4e7983483e922a133bd71d062f', 'getUserInfo', '/api/user/info', '2018-12-20 15:54:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cc50cbfdfa434a65aa9a724fece8f94c', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:40:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cc52ad9017fd40259eba8379d6cb7ea5', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:06:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cc68f5ce0d584737a78e11a75a8b41fe', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:13:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cd016d08681b417283df94590ee714f6', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:23:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cd03cc558d244af0a0744e4f90855706', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:22:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cd19339b9fdd4b449eeb7a2a95719219', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cdb50194d8804410bb3c5b9b1d85245f', 'getMenuById', '/api/group/getObj', '2018-12-24 17:35:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cddf1af615d64d198e6308e8ec0ede63', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ce0b6660796f4167a598be205a98c098', 'getMenuById', '/api/group/getObj', '2018-12-20 14:42:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ce9f5cc9cb6a4b4f83ea6bf1c482219a', 'getUserPage', '/api/user/page', '2018-12-25 16:13:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ceccace451984853b5e280ceb6bf174f', 'getMenuById', '/api/group/getObj', '2018-12-25 16:34:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cef989ba2f5b4aca814483efec09401b', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:47:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cefb5a3b780648a194f5e598fac9ed8c', 'getUserPage', '/api/user/page', '2018-12-24 16:53:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cf631d3f155e48a9946310ca2f5b6c0f', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:34:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('cfafa8802c984e54a9e17c97606fa2f2', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:44:59', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d04f0c43e5464a229d897f46a08f1598', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:30:04', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d06bc4173088405f89cc5c10b9f3c110', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:47:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d27b04b4463e4e288c79fcaa5cf8cd8d', 'getUserPage', '/api/user/page', '2018-12-25 16:40:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d2dfe629a4874384858505dd4c96fd5b', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:41:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d3467f7226f04ab5977e1f5e1fe4fcd8', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:42:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d3d61044a49a49c08af65cbea746d614', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 16:59:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d41cdcb28465411591bbc169a23448f4', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:14:34', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d4de6886056749c48826b28b0aa061d5', 'getMenuById', '/api/group/getObj', '2018-12-20 15:16:45', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d4fae9720c0d4541ab04f977a5138eb5', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:34:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d56772a8f52e469ab137558069bbaf2c', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:09:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d608f312ab7245a8879d000d6ae9026a', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:54:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d695bb279cdf4ddfbc65178b01193358', 'addMenu', '/api/menu/addObj', '2018-12-20 14:34:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d6b6048f41d34f7a943fc8fe893ba80a', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 14:45:19', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d7ae69bedc1e4da6adbd6bc2124f9046', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:25:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d81e23649b884894b6914cad51b642ac', 'updateMenu', '/api/group/putObj', '2018-12-20 15:22:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d843625e10614d5cb0bee39cb46af987', 'getMenuById', '/api/group/getObj', '2018-12-25 17:14:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d8cf0699a01e4127b11e6f875f28c1d4', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:11:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d8ddeb96ba0c480b863bce190af58151', 'getMenuById', '/api/menu/getObj', '2018-12-20 11:09:14', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d8f6c2562c7345e58c5997a71236af94', 'getMenuById', '/api/group/getObj', '2018-12-25 16:40:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('d93f3e719df048cbbe3100f83dba1b75', 'getUserPage', '/api/user/page', '2018-12-25 15:48:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('dab3d83e67b0412cac02772d5591f0bc', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:05:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('db1068d967754dcd817c0fdad948c12b', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:42:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('db1de02078264013bbfb730ce8b91d01', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 13:32:20', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('db2651bdfc1c44049a8d91ed1ad64248', 'getMenuElementList', '/api/menu/getElementList', '2018-12-19 15:34:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('db7db21b29bf4026b6f4a132eaa72b0e', 'getUserInfo', '/api/user/info', '2018-12-20 14:30:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('dd18b1513bee458799e2eaaa863816e2', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:25:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('dd2ec5b92964478989baacdcba1fb570', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:11:41', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('dd64c042597b4841b1185d7bbecc9eb5', 'getUserPage', '/api/user/page', '2018-12-20 13:17:08', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('de0a70b7af184b57a9176d03aa08d463', 'getMenuElementList', '/api/menu/getElementList', '2018-12-19 15:33:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('de2a5997b41a43eda963530ab457d3e4', 'getMenuById', '/api/group/getObj', '2018-12-24 17:07:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('de8b4aa9c7f241039144ca5aca1da070', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:14:35', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('df3927bd34844364b0a26a1d2b2e6ef8', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:09:20', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('dfb3dcd0cb0b4a98a39098e3be9804b2', 'getUserPage', '/api/user/page', '2018-12-25 16:07:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('dfebf9d1de99461e81393ce28a42d484', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 17:35:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e00bbdb47f5f49a7a295750190deeec6', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:49:08', '2', '127.0.0.1', '500', '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\r\n### The error may involve com.zkt.project.admin.mapper.GroupMapper.addGroupUser-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert sys_group_user(group_id,user_id) values(?,?)\r\n### Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\n; ]; Field \'id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'id\' doesn\'t have a default value');
INSERT INTO `sys_api_log` VALUES ('e09ee164d1b34f5187410db3d1adf139', 'getMenuById', '/api/group/getObj', '2018-12-24 17:07:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e0a0efa0f98d489db1224ae53994b917', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:54:56', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e18c3d510a7d49dfa605fc9a9fc1d9a6', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:13:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e1d4042aa7844089b53d321c446e3de4', 'getMenuById', '/api/menu/getObj', '2018-12-20 15:21:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e226b53e788a4a70a8fd61a50572fde0', 'getMenuElementList', '/api/menu/getElementList', '2018-12-19 15:43:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e27f214be17e4ddebeaadeb8079d8581', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e2ad3e9bc53f464ea277a7b556740d1a', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:21:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e2cb1f0cc9074113b1c6f6aae635fc89', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:38:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e2cb836515284f8d872c175bdfb98376', 'addMenu', '/api/menu/addObj', '2018-12-19 15:35:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e2fc21a101ad4987a08942fc484d3ea5', 'getMenuById', '/api/menu/getObj', '2018-12-19 15:42:39', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e3105c62de7046549537cee964445796', 'getMenuElementList', '/api/menu/getElementList', '2018-12-19 15:45:04', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e32e5f069cd54527a7f247097d5b944a', 'getUserPage', '/api/user/page', '2018-12-25 16:16:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e3c5be31fe0d4023a08420ee96501917', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:22:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e41529ab6e444b6da49525f2f39963e8', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:44:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e4c273fc59d1401d84e64f38ac7f012a', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:32:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e573b8272aaa4f6b925e05fc63742f1b', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:14:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e58a32c4cd3346a9aa6eeea9540f137c', 'getMenuById', '/api/group/getObj', '2018-12-20 15:20:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e59610642bf442389e3355acfe021cdd', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 17:22:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e5d8c2d699fd4f97939ab09aa8908b54', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e5ea84040572442e92b9874826139408', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:24', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e636f9cad6084380996bae5e4c9b0c42', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:56:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e637bf6b207e4e44bd12459b7956170c', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:25:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e688e29ee2274b1cac8b040bca19bfe4', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:47:49', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('e6a7cfc091334133939b11bd6b04a5a7', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:42:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e6dc045b5d2143c8b4872c0b01e49de4', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:45:06', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e7339aaffd974d1d931a11adf9c60e27', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:50:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e78b901aa1f84082b7689306ff18f0cf', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:36:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e7a72e747a8c41c1ba7668d319aa52b4', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:13:38', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e7abff9817d84ea5b014dbef8baedb83', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:25:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e807f9b4018543a787c1bb27af0eed0a', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:38:27', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e878345d3d74435d879d8851fb94c871', 'getUserPage', '/api/user/page', '2018-12-24 16:59:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e8bcf73f04204c199a8cedf6993809fd', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 14:29:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e90441c7eb2d416f8a4015063d8259c2', 'getMenuById', '/api/group/getObj', '2018-12-20 15:26:01', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e904a75fe88a47558833a2deafd0fb37', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:02:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('e93254635b264e2d8afdaf9cd7d93d25', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:25:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ea7185bc8e4b4339af5b8e66d8089dcf', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 14:38:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ecc50f19909241edbd9e41ae0c7d8c3e', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:41:33', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('ed2ca8583cd94e02a9c76c0e1e3e6748', 'validateCode', '/api/group/validateCode', '2018-12-20 15:24:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ee65fa31ad774e19b74889c3596515e8', 'getUserPage', '/api/user/page', '2018-12-25 17:04:48', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('eebe63c7703a4272b97d4776cde41d7b', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 14:42:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ef07f5fb144b4ca38d827f8225fda71d', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-24 16:53:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ef1fc7bccd5b496295b44dc105065da1', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:14:10', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ef5693625ffb4ab78ec4cc7917b9500d', 'getMenuById', '/api/group/getObj', '2018-12-25 17:13:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ef7e2eac971846578ace0592a977c02d', 'getMenuById', '/api/group/getObj', '2018-12-20 15:22:17', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('efd51e81ed47465692e3e23d7464c3de', 'getMenuById', '/api/group/getObj', '2018-12-25 17:29:57', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f0050b3701084edd980f54422b97afe0', 'validateCode', '/api/group/validateCode', '2018-12-20 15:22:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f0290abd38ff4a248a68cd32c14b4792', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:02:46', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f04f7f7ca8a140309724d1e3ca2c36b5', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:09:55', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f04fb66ec7d2418282849d3fd7eb0211', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:20:54', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f0d0f8d6bb2d4790a736c74b1587f309', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 11:09:19', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f1071c40706f42b28076e7fa550bd203', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f14efefbae73439498151fafbc9113ec', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:29:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f1558713605f42b89710472d8c4e8922', 'getUserPage', '/api/user/page', '2018-12-25 16:12:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f19e523b45b8462e9beebae32cffc252', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:34:31', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f1a15f83be5a40cda979e8ef035ae76c', 'getMenuById', '/api/group/getObj', '2018-12-20 15:24:58', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f1f940b8e49a4e089d783866d9d3dd98', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:50:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f1f98aaa350940519592ae6ee9caf21d', 'getUserInfo', '/api/user/info', '2018-12-20 15:24:09', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f246b51e55774d79a1c83de4a8a0acdf', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:50:55', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f370d2aac10f48d3ab9eefe851da465b', 'getUserPage', '/api/user/page', '2018-12-25 17:07:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f41bb8627a1f4880a68755f425b5c629', 'getUserInfo', '/api/user/info', '2018-12-20 17:17:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f454eb6c99e0470499050d3847aa90a9', 'getUserPage', '/api/user/page', '2018-12-25 15:55:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f46a0e987f494222a8255bce3f3d15e1', 'getMenuById', '/api/group/getObj', '2018-12-25 16:35:42', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f4f1c73f1b0a4e2ebad9546d2b4b3e89', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-24 17:01:09', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f4fc6ad78c0641e098fda81449938ba4', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:50:52', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f589b175b6c84cdd87a0b12f9e8ee3ac', 'getGroupUserList', '/api/group/getGroupUsers', '2018-12-25 16:08:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f5e48eaf5b984c4bb866e41f34b27926', 'updateMenu', '/api/menu/putObj', '2018-12-19 15:43:36', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f5f0ab48090d4a70a8fdd56c6578416d', 'addGroup', '/api/group/addObj', '2018-12-20 15:21:21', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f671f391dc664335a4526b32f1b558ae', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:04:13', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f67ed04a00fa48a9bec11b5087ee338b', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:59', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f72a12fb87f04ea9aedfe419d2db3a06', 'addGroupUsers', '/api/group/addGroupUsers', '2018-12-25 16:42:18', '2', '127.0.0.1', '500', null);
INSERT INTO `sys_api_log` VALUES ('f7e926f8af054c3eb76b58044c985ada', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:21:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f7fe6fb6b7ff4802b877e6f14678c4ee', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:13:40', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f8450885498e4938b53f56014f654d4d', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:43:30', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f84bb88edc6145e6b72f8ab3934b979b', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:25:53', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f8da5acba4d44c668176cb036cd0332f', 'getUserPage', '/api/user/page', '2018-12-20 17:02:09', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f9366ce47073419a9420a78fc4d19660', 'getMenuById', '/api/group/getObj', '2018-12-25 16:38:09', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f9a6c8036aaf449dbe1b2f165b85a6dc', 'getUserPage', '/api/user/page', '2018-12-25 16:26:05', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('f9f3f5f99f774178aabd5a9915c2e589', 'getMenuById', '/api/menu/getObj', '2018-12-20 14:27:59', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fa01445982494b68993df67925c9c5ac', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:38:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fa08d526b51547a392de4ed11474786e', 'getUserInfo', '/api/user/info', '2018-12-20 16:46:43', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fa18d2c727a446f1a10e9d088806916a', 'getMenuById', '/api/group/getObj', '2018-12-20 15:21:51', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fa394acf4b8444b9a9b20a325b55d73f', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:42:47', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fa4288c87c0e4973807529be1f8eb092', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 17:15:35', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fb3cf12ee5c54bbf8a93f6eb40b5066c', 'getMenuById', '/api/group/getObj', '2018-12-25 16:34:37', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fb3d3e51c8d348e3b5ae71181bfa6af5', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:20:49', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fbb9292d249d43ffaf3a6e8e6e1d36ac', 'getMenuById', '/api/group/getObj', '2018-12-20 15:16:48', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fc309a266ba2423c973d3ae8322b7ae5', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 14:39:59', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fce6b8a4240e4732a854a75b9a09d373', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:12:11', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fd21629c6cd849d983335dddc2f60692', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 16:47:25', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fd39821fc6904fc9907d01aa993a344f', 'getAllMenusByTitle', '/api/menu/tree', '2018-12-20 15:21:29', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fd4a15fdb74f4ca8a3e04787e69ccb7e', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:14:28', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fd4c2c8c1dde4e88a0f7e95f586763ba', 'getMenuById', '/api/group/getObj', '2018-12-20 15:37:26', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fe0bd19638b94e0783460171b61f6be1', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-25 16:48:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fea48385a1c24af98dfd196b06cc7f17', 'getUserInfo', '/api/user/info', '2018-12-20 14:36:33', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('feafcc0d4e2642cf9a231949cb157032', 'getUserInfo', '/api/user/info', '2018-12-20 14:38:00', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fec0282f437b48be9b6f0f4b41e4869f', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:22:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fed2224b8daf4a768f63c5604375d9b5', 'getMenuById', '/api/group/getObj', '2018-12-24 17:25:32', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('fefb4af230d549fb99370ffa7b3271b6', 'delGroupUsers', '/api/group/delGroupUsers', '2018-12-24 17:24:31', '2', '127.0.0.1', '500', '\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\'\' at line 1\r\n### The error may involve com.zkt.project.admin.mapper.GroupMapper.deleteGroupUsers-Inline\r\n### The error occurred while setting parameters\r\n### SQL: delete from sys_group_user where id in ?\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\'\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near \'\'(null)\'\' at line 1');
INSERT INTO `sys_api_log` VALUES ('ff1f5ea151d343a3adf1a017d4518ab8', 'getMenuElementList', '/api/menu/getElementList', '2018-12-19 15:33:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ff64e5a48ba842c18c6feb9972cf5c48', 'getMenuById', '/api/group/getObj', '2018-12-20 16:23:18', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ff65f01f6c52458297ca122e1e8f1d45', 'getMenuElementList', '/api/menu/getElementList', '2018-12-20 15:24:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ff740069df43406ea61d12a780d95caa', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:10:44', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ff7f34750bfb4ef4af12b1f8e9044aee', 'getMenuById', '/api/group/getObj', '2018-12-20 15:22:15', '2', '127.0.0.1', '200', '操作成功！');
INSERT INTO `sys_api_log` VALUES ('ff869550d5c24ba38266ad6030f8402a', 'getGroupTreeByTitle', '/api/group/tree', '2018-12-20 15:49:06', '2', '127.0.0.1', '200', '操作成功！');

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group` (
  `id` varchar(32) NOT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `parent_id` varchar(32) NOT NULL COMMENT '上级节点',
  `path` varchar(2000) DEFAULT NULL COMMENT '树状关系',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `group_type` char(1) NOT NULL COMMENT '角色组类型1角色2部门',
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  `status` char(1) DEFAULT NULL COMMENT '状态(1 启用 ,2 禁用 , 3 删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group` VALUES ('1', 'adminRole', '管理员', '-1', '/adminRole', null, '1', '', null, null, null, null, null);
INSERT INTO `sys_group` VALUES ('3', 'testGroup', '体验组', '-1', '/testGroup', null, '1', '', null, null, null, null, null);
INSERT INTO `sys_group` VALUES ('385678e25aab404695eefa8bf7f55252', 'telGroup', '技术部', '6', '/company/telGroup', '1', '2', '技术部', '2018-12-20 15:21:21', '2', '2018-12-20 15:24:21', '2', '1');
INSERT INTO `sys_group` VALUES ('4', 'visitorRole', '游客', '3', '/testGroup/visitorRole', null, '1', '', null, null, null, null, null);
INSERT INTO `sys_group` VALUES ('6', 'company', 'AG集团', '-1', '/company', null, '2', '', null, null, null, null, null);
INSERT INTO `sys_group` VALUES ('7', 'financeDepart', '财务部', '6', '/company/financeDepart', '0', '2', '', null, null, '2018-12-20 15:24:37', '2', '1');
INSERT INTO `sys_group` VALUES ('8', 'hrDepart', '人力资源部', '6', '/company/hrDepart', '3', '2', '', null, null, '2018-12-20 15:24:53', '2', '1');
INSERT INTO `sys_group` VALUES ('9', 'blogAdmin', '博客管理员', '-1', '/blogAdmin', null, '1', '', '2017-07-16 16:59:30', '1', null, null, null);
INSERT INTO `sys_group` VALUES ('cbfbd3863f4d451eaac2ac02900c5a39', '111', '技术一部', '385678e25aab404695eefa8bf7f55252', '/company/telGroup/111', null, '2', '技术一部', '2018-12-20 15:22:10', '2', '2018-12-20 15:22:10', '2', '1');
INSERT INTO `sys_group` VALUES ('f0e3dcb02a1e403d9b739ce617bcb13c', 'ibf', '北风网', '-1', '/ibf', null, '2', null, '2018-12-20 15:25:54', '2', '2018-12-20 15:25:54', '2', '1');

-- ----------------------------
-- Table structure for sys_group_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_authority`;
CREATE TABLE `sys_group_authority` (
  `id` varchar(32) NOT NULL,
  `group_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_group_authority
-- ----------------------------
INSERT INTO `sys_group_authority` VALUES ('287', '1', '5');
INSERT INTO `sys_group_authority` VALUES ('288', '1', '9');
INSERT INTO `sys_group_authority` VALUES ('289', '1', '10');
INSERT INTO `sys_group_authority` VALUES ('290', '1', '11');
INSERT INTO `sys_group_authority` VALUES ('291', '1', '12');
INSERT INTO `sys_group_authority` VALUES ('294', '1', '5');
INSERT INTO `sys_group_authority` VALUES ('295', '1', '9');
INSERT INTO `sys_group_authority` VALUES ('296', '1', '10');
INSERT INTO `sys_group_authority` VALUES ('297', '1', '11');
INSERT INTO `sys_group_authority` VALUES ('298', '1', '12');
INSERT INTO `sys_group_authority` VALUES ('299', '1', '9');
INSERT INTO `sys_group_authority` VALUES ('300', '1', '12');
INSERT INTO `sys_group_authority` VALUES ('301', '1', '10');
INSERT INTO `sys_group_authority` VALUES ('302', '1', '11');
INSERT INTO `sys_group_authority` VALUES ('303', '1', '13');
INSERT INTO `sys_group_authority` VALUES ('304', '1', '14');
INSERT INTO `sys_group_authority` VALUES ('305', '1', '15');
INSERT INTO `sys_group_authority` VALUES ('306', '1', '10');
INSERT INTO `sys_group_authority` VALUES ('307', '1', '11');
INSERT INTO `sys_group_authority` VALUES ('308', '1', '12');
INSERT INTO `sys_group_authority` VALUES ('309', '1', '13');
INSERT INTO `sys_group_authority` VALUES ('310', '1', '14');
INSERT INTO `sys_group_authority` VALUES ('311', '1', '9');
INSERT INTO `sys_group_authority` VALUES ('312', '1', '15');
INSERT INTO `sys_group_authority` VALUES ('313', '1', '16');
INSERT INTO `sys_group_authority` VALUES ('314', '1', '17');
INSERT INTO `sys_group_authority` VALUES ('315', '1', '18');
INSERT INTO `sys_group_authority` VALUES ('317', '1', '20');
INSERT INTO `sys_group_authority` VALUES ('318', '1', '21');
INSERT INTO `sys_group_authority` VALUES ('319', '1', '22');
INSERT INTO `sys_group_authority` VALUES ('349', '4', '9');
INSERT INTO `sys_group_authority` VALUES ('371', '1', '23');
INSERT INTO `sys_group_authority` VALUES ('372', '1', '24');
INSERT INTO `sys_group_authority` VALUES ('373', '1', '27');
INSERT INTO `sys_group_authority` VALUES ('374', '1', '28');
INSERT INTO `sys_group_authority` VALUES ('375', '1', '23');
INSERT INTO `sys_group_authority` VALUES ('378', '1', '5');
INSERT INTO `sys_group_authority` VALUES ('379', '1', '9');
INSERT INTO `sys_group_authority` VALUES ('380', '1', '11');
INSERT INTO `sys_group_authority` VALUES ('381', '1', '14');
INSERT INTO `sys_group_authority` VALUES ('382', '1', '13');
INSERT INTO `sys_group_authority` VALUES ('383', '1', '15');
INSERT INTO `sys_group_authority` VALUES ('384', '1', '12');
INSERT INTO `sys_group_authority` VALUES ('385', '1', '24');
INSERT INTO `sys_group_authority` VALUES ('386', '1', '10');
INSERT INTO `sys_group_authority` VALUES ('387', '1', '27');
INSERT INTO `sys_group_authority` VALUES ('388', '1', '16');
INSERT INTO `sys_group_authority` VALUES ('389', '1', '18');
INSERT INTO `sys_group_authority` VALUES ('390', '1', '17');
INSERT INTO `sys_group_authority` VALUES ('392', '1', '20');
INSERT INTO `sys_group_authority` VALUES ('393', '1', '28');
INSERT INTO `sys_group_authority` VALUES ('394', '1', '22');
INSERT INTO `sys_group_authority` VALUES ('395', '1', '21');
INSERT INTO `sys_group_authority` VALUES ('396', '4', '23');
INSERT INTO `sys_group_authority` VALUES ('397', '4', '9');
INSERT INTO `sys_group_authority` VALUES ('398', '4', '27');
INSERT INTO `sys_group_authority` VALUES ('399', '4', '24');
INSERT INTO `sys_group_authority` VALUES ('400', '4', '28');
INSERT INTO `sys_group_authority` VALUES ('401', '1', '30');
INSERT INTO `sys_group_authority` VALUES ('402', '1', '30');
INSERT INTO `sys_group_authority` VALUES ('403', '1', '31');
INSERT INTO `sys_group_authority` VALUES ('421', '1', '31');
INSERT INTO `sys_group_authority` VALUES ('422', '1', '30');
INSERT INTO `sys_group_authority` VALUES ('423', '4', '31');
INSERT INTO `sys_group_authority` VALUES ('424', '4', '30');
INSERT INTO `sys_group_authority` VALUES ('436', '1', '32');
INSERT INTO `sys_group_authority` VALUES ('437', '1', '33');
INSERT INTO `sys_group_authority` VALUES ('438', '1', '34');
INSERT INTO `sys_group_authority` VALUES ('439', '1', '35');
INSERT INTO `sys_group_authority` VALUES ('440', '4', '32');
INSERT INTO `sys_group_authority` VALUES ('464', '1', '30');
INSERT INTO `sys_group_authority` VALUES ('465', '1', '31');
INSERT INTO `sys_group_authority` VALUES ('466', '1', '30');
INSERT INTO `sys_group_authority` VALUES ('467', '1', '31');
INSERT INTO `sys_group_authority` VALUES ('468', '1', '30');
INSERT INTO `sys_group_authority` VALUES ('469', '1', '31');
INSERT INTO `sys_group_authority` VALUES ('470', '1', '30');
INSERT INTO `sys_group_authority` VALUES ('471', '1', '31');
INSERT INTO `sys_group_authority` VALUES ('472', '1', '40');
INSERT INTO `sys_group_authority` VALUES ('492', '1', '30');
INSERT INTO `sys_group_authority` VALUES ('493', '1', '31');
INSERT INTO `sys_group_authority` VALUES ('494', '1', '40');
INSERT INTO `sys_group_authority` VALUES ('516', '4', '41');
INSERT INTO `sys_group_authority` VALUES ('517', '4', '30');
INSERT INTO `sys_group_authority` VALUES ('518', '4', '31');
INSERT INTO `sys_group_authority` VALUES ('519', '4', '40');
INSERT INTO `sys_group_authority` VALUES ('611', '4', '42');
INSERT INTO `sys_group_authority` VALUES ('612', '4', '36');
INSERT INTO `sys_group_authority` VALUES ('628', '4', '13');
INSERT INTO `sys_group_authority` VALUES ('629', '4', '5');
INSERT INTO `sys_group_authority` VALUES ('630', '4', '1');
INSERT INTO `sys_group_authority` VALUES ('631', '4', '6');
INSERT INTO `sys_group_authority` VALUES ('632', '4', '7');
INSERT INTO `sys_group_authority` VALUES ('633', '4', '8');
INSERT INTO `sys_group_authority` VALUES ('634', '4', '27');
INSERT INTO `sys_group_authority` VALUES ('635', '4', '9');
INSERT INTO `sys_group_authority` VALUES ('636', '4', '24');
INSERT INTO `sys_group_authority` VALUES ('637', '4', '22');
INSERT INTO `sys_group_authority` VALUES ('638', '4', '23');
INSERT INTO `sys_group_authority` VALUES ('639', '4', '25');
INSERT INTO `sys_group_authority` VALUES ('640', '4', '26');
INSERT INTO `sys_group_authority` VALUES ('641', '4', '28');
INSERT INTO `sys_group_authority` VALUES ('666', '1', '41');
INSERT INTO `sys_group_authority` VALUES ('689', '1', '43');
INSERT INTO `sys_group_authority` VALUES ('691', '1', '44');
INSERT INTO `sys_group_authority` VALUES ('710', '9', '42');
INSERT INTO `sys_group_authority` VALUES ('711', '9', '43');
INSERT INTO `sys_group_authority` VALUES ('712', '9', '44');
INSERT INTO `sys_group_authority` VALUES ('713', '9', '45');
INSERT INTO `sys_group_authority` VALUES ('718', '9', '42');
INSERT INTO `sys_group_authority` VALUES ('719', '9', '44');
INSERT INTO `sys_group_authority` VALUES ('720', '9', '45');
INSERT INTO `sys_group_authority` VALUES ('721', '9', '43');
INSERT INTO `sys_group_authority` VALUES ('722', '1', '41');
INSERT INTO `sys_group_authority` VALUES ('749', '10', '13');
INSERT INTO `sys_group_authority` VALUES ('750', '10', '14');
INSERT INTO `sys_group_authority` VALUES ('751', '10', '-1');
INSERT INTO `sys_group_authority` VALUES ('752', '10', '5');
INSERT INTO `sys_group_authority` VALUES ('753', '10', '6');
INSERT INTO `sys_group_authority` VALUES ('754', '10', '17');
INSERT INTO `sys_group_authority` VALUES ('755', '10', '20');
INSERT INTO `sys_group_authority` VALUES ('774', '1', '3');
INSERT INTO `sys_group_authority` VALUES ('775', '1', '4');
INSERT INTO `sys_group_authority` VALUES ('812', '1', '19');
INSERT INTO `sys_group_authority` VALUES ('924', '1', '42');
INSERT INTO `sys_group_authority` VALUES ('945', '1', '45');
INSERT INTO `sys_group_authority` VALUES ('956', '1', '46');
INSERT INTO `sys_group_authority` VALUES ('967', '1', '33');
INSERT INTO `sys_group_authority` VALUES ('968', '1', '34');
INSERT INTO `sys_group_authority` VALUES ('969', '1', '13');
INSERT INTO `sys_group_authority` VALUES ('970', '1', '-1');
INSERT INTO `sys_group_authority` VALUES ('971', '1', '27');
INSERT INTO `sys_group_authority` VALUES ('972', '1', '29');
INSERT INTO `sys_group_authority` VALUES ('973', '1', '1');
INSERT INTO `sys_group_authority` VALUES ('974', '1', '5');
INSERT INTO `sys_group_authority` VALUES ('975', '1', '6');
INSERT INTO `sys_group_authority` VALUES ('976', '1', '7');
INSERT INTO `sys_group_authority` VALUES ('977', '1', '8');
INSERT INTO `sys_group_authority` VALUES ('978', '1', '30');
INSERT INTO `sys_group_authority` VALUES ('979', '1', '31');
INSERT INTO `sys_group_authority` VALUES ('980', '1', '32');
INSERT INTO `sys_group_authority` VALUES ('981', '9', '23');
INSERT INTO `sys_group_authority` VALUES ('982', '9', '1');
INSERT INTO `sys_group_authority` VALUES ('983', '9', '13');
INSERT INTO `sys_group_authority` VALUES ('984', '9', '-1');
INSERT INTO `sys_group_authority` VALUES ('985', '9', '5');
INSERT INTO `sys_group_authority` VALUES ('986', '1', '37');

-- ----------------------------
-- Table structure for sys_group_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_user`;
CREATE TABLE `sys_group_user` (
  `id` varchar(32) NOT NULL,
  `group_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_group_user
-- ----------------------------
INSERT INTO `sys_group_user` VALUES ('10', '1', '1');
INSERT INTO `sys_group_user` VALUES ('11', '1', '2');
INSERT INTO `sys_group_user` VALUES ('160e6de1df974b22b48b5675a375856b', 'cbfbd3863f4d451eaac2ac02900c5a39', '1');
INSERT INTO `sys_group_user` VALUES ('33e61d2ae59d4449986a1690d9e4417b', 'cbfbd3863f4d451eaac2ac02900c5a39', '0f22475ad242474e987f0118f16eba8f');
INSERT INTO `sys_group_user` VALUES ('b76efab6ee1c4cc3afe935eb277071e3', 'cbfbd3863f4d451eaac2ac02900c5a39', 'cc4680cc0a7a4b7bba47b8d634c95c51');
INSERT INTO `sys_group_user` VALUES ('efeb4d6049874565a95a33742c58f75d', 'cbfbd3863f4d451eaac2ac02900c5a39', '0efa8b6cf1474b3cb76fd9cc4dd613e0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` char(32) NOT NULL COMMENT 'id',
  `code` varchar(255) DEFAULT NULL COMMENT '路径编码',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `parent_id` int(11) NOT NULL COMMENT '父级节点',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `type` char(10) DEFAULT NULL COMMENT '菜单或者按钮menu/button',
  `order_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单路由',
  `view` varchar(255) DEFAULT NULL COMMENT '视图路径',
  `uri` varchar(2000) DEFAULT NULL COMMENT '请求地址',
  `status` char(1) DEFAULT NULL COMMENT '状态(1 启用 ,2 禁用 , 3 删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '最后一次更新人',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0a1ebe522369422a8b009e170195f74a', 'menuManager:btn_element_del', '删除', '6', null, 'button', '0', '删除资源', null, null, null, '1', '2018-10-30 13:42:49', '2', '2018-10-30 13:42:49', '2');
INSERT INTO `sys_menu` VALUES ('0a8a3349739b4d4999b49e7d096da1aa', 'departManager:btn_element_del', '添加用户', '-1', null, 'button', '0', '添加部门用户', null, null, null, '1', '2018-12-20 14:34:47', '2', '2018-12-20 14:34:47', '2');
INSERT INTO `sys_menu` VALUES ('1', 'userManager', '用户管理', '5', 'user', 'menu', '0', '用户管理', '/userManager', 'admin/user/index', null, '1', null, null, '2018-12-20 15:21:34', '2');
INSERT INTO `sys_menu` VALUES ('13', 'adminSys', '系统根节点', '-1', 'setting', 'menu', '0', '', '/adminSys', null, null, '1', null, null, '2017-09-28 12:09:22', '1');
INSERT INTO `sys_menu` VALUES ('1a3ea99ec3094067a72bec6e84e24359', 'menuManager:btn_element_edit', '编辑', '6', null, 'button', '0', '编辑资源', null, null, null, '1', '2018-10-30 13:43:09', '2', '2018-10-30 13:43:09', '2');
INSERT INTO `sys_menu` VALUES ('21', 'dictManager', '数据字典', '5', 'dictionary', 'menu', '3', '数据字典', '/dictManager', 'admin/dict/index', null, '1', null, null, '2018-10-29 15:43:21', '2');
INSERT INTO `sys_menu` VALUES ('27', 'logManager', '操作日志', '5', 'viewlist', 'menu', '4', '操作日志', '/logManager', 'admin/apiLog/index', null, '1', '2017-07-01 00:00:00', '1', '2018-10-29 15:43:54', '2');
INSERT INTO `sys_menu` VALUES ('30', 'serviceManager', '服务管理', '29', 'client', 'menu', '0', '服务管理', '/adminSys/authManager/serviceManager', null, null, '3', '2017-12-26 19:56:06', '1', '2017-12-26 19:56:06', '1');
INSERT INTO `sys_menu` VALUES ('35', 'userManager:btn_add', '新增', '1', null, 'button', '0', '新增用户', '', null, '/api/user/addObj', '1', null, null, '2018-12-19 15:45:04', '2');
INSERT INTO `sys_menu` VALUES ('36', 'userManager:btn_edit', '编辑', '1', null, 'button', '0', '编辑用户', '', null, '/api/user/putObj', '1', null, null, '2018-12-19 15:45:18', '2');
INSERT INTO `sys_menu` VALUES ('37', 'menuManager_btn_add', '新增', '6', null, 'button', '0', '新增菜单', null, null, '', '1', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('5', 'adminSys', '系统管理', '13', 'tree', 'menu', '0', 'adminSys', '/adminSys', null, null, '1', null, null, '2018-10-29 14:42:13', '2');
INSERT INTO `sys_menu` VALUES ('6', 'menuManager', '菜单管理', '5', 'list', 'menu', '1', '菜单管理', '/menuManager', 'admin/menu/index', null, '1', null, null, '2018-10-29 15:26:52', '2');
INSERT INTO `sys_menu` VALUES ('6e5bfa2fcf484d299941e15aa8b756a0', 'departManager:btn_add', '添加', '-1', null, 'button', '0', '添加部门', null, null, null, '1', '2018-12-20 14:33:07', '2', '2018-12-20 14:33:07', '2');
INSERT INTO `sys_menu` VALUES ('7', 'groupManager', '角色权限管理', '5', 'peoples', 'menu', '2', '角色权限管理', '/groupManager', 'admin/group/index', null, '1', null, null, '2018-10-29 15:36:31', '2');
INSERT INTO `sys_menu` VALUES ('927f5338e5b04bc88c20711fc3d3e6f1', 'departManager:btn_del', '删除', '-1', null, 'button', '0', '删除部门', null, null, null, '1', '2018-12-20 14:33:49', '2', '2018-12-20 14:33:49', '2');
INSERT INTO `sys_menu` VALUES ('b74ec026d84c4e27b807f4ff220372ba', 'departManager:btn_edit', '修改', '-1', null, 'button', '0', '修改部门', null, null, null, '1', '2018-12-20 14:33:32', '2', '2018-12-20 14:33:32', '2');
INSERT INTO `sys_menu` VALUES ('b964b3d8c35b416a96f52b929c6e0e8a', 'departManager', '部门管理', '5', 'tree', 'menu', '1', '部门管理', '/departManager', 'admin/depart/index', null, '1', '2018-10-30 15:12:37', '2', '2018-10-30 15:12:37', '2');
INSERT INTO `sys_menu` VALUES ('c2b20408d6da49f6b3f9908992547f6f', 'userManager:btn_del', '删除', '1', null, 'button', '0', '删除资源', null, null, '/api/user/delObj', '1', '2018-10-29 18:19:17', '2', '2018-12-19 15:45:33', '2');
INSERT INTO `sys_menu` VALUES ('f314badd5c6e4b9b88135f1da86320a3', 'userManager:uri_view', '查询', '1', null, 'uri', '0', '用户列表查询', null, null, '/api/user/page', '1', '2018-12-19 15:35:24', '2', '2018-12-19 15:43:36', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` char(32) COLLATE utf8_bin NOT NULL COMMENT '用户编号',
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户登录名称（过时）',
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱地址',
  `mobile` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
  `telphone` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '电话号码',
  `status` char(1) COLLATE utf8_bin DEFAULT '1' COMMENT '状态(1 启用 ,2 禁用 , 3 删除)',
  `user_type` char(1) COLLATE utf8_bin DEFAULT '1' COMMENT '用户类型(1后台用户, 2 前端用户)',
  `real_name` text CHARACTER SET utf8mb4 COMMENT '真实姓名',
  `share_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邀请码',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `gender` char(32) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `nick_name` text CHARACTER SET utf8mb4,
  `qq` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq号码',
  `wei_chat` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '微信号码',
  `wei_bo` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '微博',
  `avatar` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '头像地址',
  `id_num` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号码',
  `country` char(32) COLLATE utf8_bin DEFAULT NULL COMMENT '现居住地（国家）',
  `province` char(32) COLLATE utf8_bin DEFAULT NULL COMMENT '现居住地（省）',
  `city` char(32) COLLATE utf8_bin DEFAULT NULL COMMENT '现居住地（市）',
  `area` char(32) COLLATE utf8_bin DEFAULT NULL COMMENT '现居住地（区、县）',
  `address` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '现居住地（详细地址）',
  `we_chat_open_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '微信端二维码',
  `qq_open_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq唯一标识码',
  `wei_bo_open_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '微博唯标识码',
  `job` char(200) COLLATE utf8_bin DEFAULT NULL COMMENT '职位信息',
  `company` char(200) COLLATE utf8_bin DEFAULT NULL COMMENT '公司名称',
  `industry` char(32) COLLATE utf8_bin DEFAULT NULL COMMENT '行业(select)',
  `fax` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '传真',
  `w_country` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '微信端国家',
  `w_province` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '微信省',
  `w_city` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '微信城市',
  `w_area` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '微信区',
  `uni_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'uniid',
  `is_com` char(1) COLLATE utf8_bin DEFAULT '1' COMMENT '是否是B端用户 1不是  2是',
  `is_admin` char(1) COLLATE utf8_bin DEFAULT '1' COMMENT '超级管理员标志1不是2是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  `update_user` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '最后一次更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0efa8b6cf1474b3cb76fd9cc4dd613e0', '32131', '$2a$12$yVhaeBsxPEOiyj.M2s/ZX.Evdgv85WygPbQvKsuSItuoMfcV7FAZS', null, null, null, '1', '1', '21321', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '2018-10-26 10:49:42', '2', '2018-10-26 10:49:42', '2');
INSERT INTO `sys_user` VALUES ('0f22475ad242474e987f0118f16eba8f', '3213213333', '$2a$12$kKY.lWdRx5FoknzSFhrsIu/8RJ41WjwNAlh9XwvxthQCtOBULExWy', 'www@qq.com', '13099880098', null, '1', '1', 'fdsafds', null, null, null, '男', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '2018-10-26 13:55:25', '2', '2018-10-26 13:55:34', '2');
INSERT INTO `sys_user` VALUES ('1', 'editor', '$2a$12$ca5TrW3xGolJF3tLyq1pMOQtKIaEEt3LuH.sWqQROsGIQSKMV.Csq', '', '13111111111', '', '1', '', '刘备', '', null, '0000-00-00', '男', '管理员', '', '', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '1', '2018-10-24 17:08:11', '2', '2018-10-26 13:31:50', '2');
INSERT INTO `sys_user` VALUES ('139b3159a393444eb17ced419135065e', 'hungz', '$2a$12$aJmauHC8UGIBRiAqboDbfuB2fQ/0/rw5QoCLX5lDNK304BpXDzv1.', null, null, null, '1', '1', '黄志忠', null, null, null, '男', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '2018-10-26 10:35:42', '2', '2018-10-26 10:35:42', '2');
INSERT INTO `sys_user` VALUES ('2', 'admin', '$2a$12$ca5TrW3xGolJF3tLyq1pMOQtKIaEEt3LuH.sWqQROsGIQSKMV.Csq', '', '', '', '1', '', '赵云', '', null, '0000-00-00', '男', '管理员', '', '', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '1', '2', '2018-10-24 17:08:15', '2', '0000-00-00 00:00:00', '2');
INSERT INTO `sys_user` VALUES ('2bf30fbe4a5845e6a91978b86e4b37d0', '321321', '321321', null, null, null, '3', '1', '3213213', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '2018-10-26 10:49:47', '2', '2018-10-26 10:49:47', '2');
INSERT INTO `sys_user` VALUES ('3303f62260374900916a38ddc65af11f', 'test', '$2a$12$ynQ4E0RF.M3/dnSghNjxCecJ.eFGxQ.VyixbVFrQVCuf7zE/DIfsS', 'test@ibeifeng.com', '13222222222', null, '1', '1', '张三', null, '22', null, '男', '测试人员', null, null, null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '2018-10-25 11:31:10', '2', '2018-10-25 03:31:10', '2');
INSERT INTO `sys_user` VALUES ('50c2e6cc98844e029cd814755dd20f35', '52152', '321321', null, null, null, '1', '1', '521521', null, null, null, '女', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '2018-10-26 10:50:06', '2', '2018-10-26 10:50:06', '2');
INSERT INTO `sys_user` VALUES ('732e1cd9ab334ac49b3843086476d150', 'yuying', '$2a$12$RTG.o3ZkyEh.Lhu.wHOWXOjUj8wx762fbFBR6dTBN1zxEfLqxvPuy', null, null, null, '1', '1', 'yueying', null, null, null, '女', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '2018-10-26 10:36:31', '2', '2018-10-26 10:36:31', '2');
INSERT INTO `sys_user` VALUES ('cc4680cc0a7a4b7bba47b8d634c95c51', 'guanyu', '$2a$12$4AMo7o.hJmQDekhde9EV5.Pe34xFbR2MyLiWuOUVoanBk1eoQCMh2', null, '10999998888', null, '1', '1', '关羽', null, null, null, '男', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '2018-10-26 10:32:34', '2', '2018-10-26 13:32:16', '2');
INSERT INTO `sys_user` VALUES ('db439fe36e62453cb7f6214ab2394c55', 'zhuge', '$2a$12$bBVRXbsRKRRg9YuUgwiGuOmulNdYRvE9q//VoPxHhxPc2P2RLQI6G', null, null, null, '1', '1', 'zhuge', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '2018-10-26 10:36:15', '2', '2018-10-26 10:36:15', '2');
INSERT INTO `sys_user` VALUES ('e36a699e553e488496e2c0188d078084', 'test2', '$2a$12$M5DWkbuQaAry1xhDkY2HYO/8JmFEmzk9HvOXqWLLfMJq.rFAsFKMm', 'test2@ibeifeng.com', '13222222122', null, '1', null, '李四', null, '222', null, '男', '测试人员2', null, null, null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '2018-10-25 13:45:03', '2', '2018-10-25 13:45:03', '2');
INSERT INTO `sys_user` VALUES ('e3f92029104742f7a573b31f5e854862', '4214', '41421421', null, null, null, '3', '1', '4213421', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '2018-10-26 10:49:54', '2', '2018-10-26 10:49:54', '2');
