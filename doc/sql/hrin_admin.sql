-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, '2015-01-09 20:25:05', '2015-01-09 20:28:50', 'AZICOnu9cyUFFvBp3xi1AA==', 'admin',1,0,NULL);
INSERT INTO `user` VALUES ('2', null, null, '2015-01-09 20:32:15', '4QrcOUm6Wau+VuBX8g+IPg==', '000001',1,0,NULL);

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员');
INSERT INTO `role` VALUES ('2', '运维-001');

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('1', '资源查看');
INSERT INTO `privilege` VALUES ('2', '资源操作');
INSERT INTO `privilege` VALUES ('4', '主页面');
INSERT INTO `privilege` VALUES ('5', '权限查看');
INSERT INTO `privilege` VALUES ('6', '权限操作');
INSERT INTO `privilege` VALUES ('7', '角色查看');
INSERT INTO `privilege` VALUES ('8', '角色操作');
INSERT INTO `privilege` VALUES ('9', '用户查看');
INSERT INTO `privilege` VALUES ('10', '用户操作');
INSERT INTO `privilege` VALUES ('11', '父菜单菜单查看');
INSERT INTO `privilege` VALUES ('12', '子菜单查看');
INSERT INTO `privilege` VALUES ('13', '菜单操作');
INSERT INTO `privilege` VALUES ('14', '区域查看');
INSERT INTO `privilege` VALUES ('15', '区域操作');
INSERT INTO `privilege` VALUES ('16', '注册标签查看');
INSERT INTO `privilege` VALUES ('17', '注册标签操作');

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');

-- ----------------------------
-- Records of privilege_resource
-- ----------------------------
INSERT INTO `privilege_resource` VALUES ('4', '1');
INSERT INTO `privilege_resource` VALUES ('1', '2');
INSERT INTO `privilege_resource` VALUES ('2', '2');
INSERT INTO `privilege_resource` VALUES ('1', '3');
INSERT INTO `privilege_resource` VALUES ('2', '3');
INSERT INTO `privilege_resource` VALUES ('2', '4');
INSERT INTO `privilege_resource` VALUES ('2', '5');
INSERT INTO `privilege_resource` VALUES ('2', '6');
INSERT INTO `privilege_resource` VALUES ('2', '7');
INSERT INTO `privilege_resource` VALUES ('5', '8');
INSERT INTO `privilege_resource` VALUES ('6', '8');
INSERT INTO `privilege_resource` VALUES ('5', '9');
INSERT INTO `privilege_resource` VALUES ('6', '9');
INSERT INTO `privilege_resource` VALUES ('6', '10');
INSERT INTO `privilege_resource` VALUES ('6', '11');
INSERT INTO `privilege_resource` VALUES ('6', '12');
INSERT INTO `privilege_resource` VALUES ('6', '13');
INSERT INTO `privilege_resource` VALUES ('7', '14');
INSERT INTO `privilege_resource` VALUES ('8', '14');
INSERT INTO `privilege_resource` VALUES ('7', '15');
INSERT INTO `privilege_resource` VALUES ('8', '15');
INSERT INTO `privilege_resource` VALUES ('8', '16');
INSERT INTO `privilege_resource` VALUES ('8', '17');
INSERT INTO `privilege_resource` VALUES ('8', '18');
INSERT INTO `privilege_resource` VALUES ('8', '19');
INSERT INTO `privilege_resource` VALUES ('9', '20');
INSERT INTO `privilege_resource` VALUES ('10', '20');
INSERT INTO `privilege_resource` VALUES ('9', '21');
INSERT INTO `privilege_resource` VALUES ('10', '21');
INSERT INTO `privilege_resource` VALUES ('10', '22');
INSERT INTO `privilege_resource` VALUES ('10', '23');
INSERT INTO `privilege_resource` VALUES ('10', '24');
INSERT INTO `privilege_resource` VALUES ('10', '25');
INSERT INTO `privilege_resource` VALUES ('11', '26');
INSERT INTO `privilege_resource` VALUES ('13', '26');
INSERT INTO `privilege_resource` VALUES ('11', '27');
INSERT INTO `privilege_resource` VALUES ('13', '27');
INSERT INTO `privilege_resource` VALUES ('12', '28');
INSERT INTO `privilege_resource` VALUES ('13', '28');
INSERT INTO `privilege_resource` VALUES ('12', '29');
INSERT INTO `privilege_resource` VALUES ('13', '29');
INSERT INTO `privilege_resource` VALUES ('13', '30');
INSERT INTO `privilege_resource` VALUES ('13', '31');
INSERT INTO `privilege_resource` VALUES ('13', '32');
INSERT INTO `privilege_resource` VALUES ('13', '33');
INSERT INTO `privilege_resource` VALUES ('13', '34');
INSERT INTO `privilege_resource` VALUES ('14', '35');
INSERT INTO `privilege_resource` VALUES ('15', '35');
INSERT INTO `privilege_resource` VALUES ('14', '36');
INSERT INTO `privilege_resource` VALUES ('15', '36');
INSERT INTO `privilege_resource` VALUES ('15', '37');
INSERT INTO `privilege_resource` VALUES ('15', '38');
INSERT INTO `privilege_resource` VALUES ('14', '41');
INSERT INTO `privilege_resource` VALUES ('14', '42');
INSERT INTO `privilege_resource` VALUES ('16', '43');
INSERT INTO `privilege_resource` VALUES ('17', '43');
INSERT INTO `privilege_resource` VALUES ('16', '44');
INSERT INTO `privilege_resource` VALUES ('17', '44');
INSERT INTO `privilege_resource` VALUES ('17', '45');
INSERT INTO `privilege_resource` VALUES ('17', '46');
INSERT INTO `privilege_resource` VALUES ('17', '47');
INSERT INTO `privilege_resource` VALUES ('17', '48');

-- ----------------------------
-- Records of role_privilege
-- ----------------------------
INSERT INTO `role_privilege` VALUES ('1', '1');
INSERT INTO `role_privilege` VALUES ('1', '2');
INSERT INTO `role_privilege` VALUES ('1', '4');
INSERT INTO `role_privilege` VALUES ('2', '4');
INSERT INTO `role_privilege` VALUES ('1', '5');
INSERT INTO `role_privilege` VALUES ('1', '6');
INSERT INTO `role_privilege` VALUES ('1', '7');
INSERT INTO `role_privilege` VALUES ('1', '8');
INSERT INTO `role_privilege` VALUES ('1', '9');
INSERT INTO `role_privilege` VALUES ('1', '10');
INSERT INTO `role_privilege` VALUES ('1', '11');
INSERT INTO `role_privilege` VALUES ('1', '12');
INSERT INTO `role_privilege` VALUES ('1', '13');
INSERT INTO `role_privilege` VALUES ('1', '14');
INSERT INTO `role_privilege` VALUES ('2', '14');
INSERT INTO `role_privilege` VALUES ('1', '15');
INSERT INTO `role_privilege` VALUES ('2', '15');
INSERT INTO `role_privilege` VALUES ('1', '16');
INSERT INTO `role_privilege` VALUES ('2', '16');
INSERT INTO `role_privilege` VALUES ('1', '17');
INSERT INTO `role_privilege` VALUES ('2', '17');

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统管理', '0', '', null, null);
INSERT INTO `menu` VALUES ('2', '资源列表', '0', '/manage/resource/list', '1', '2');
INSERT INTO `menu` VALUES ('3', '权限列表', '0', '/manage/privilege/list', '1', '8');
INSERT INTO `menu` VALUES ('4', '角色列表', '0', '/manage/role/list', '1', '14');
INSERT INTO `menu` VALUES ('5', '用户列表', '0', '/manage/user/list', '1', '20');
INSERT INTO `menu` VALUES ('6', '父菜单列表', '0', '/manage/menu/list/parent', '1', '26');
INSERT INTO `menu` VALUES ('8', '网站配置', '0', '', null, null);
INSERT INTO `menu` VALUES ('9', '用户管理', '0', '', null, null);
INSERT INTO `menu` VALUES ('10', '内容管理', '0', '', null, null);
INSERT INTO `menu` VALUES ('11', '数据统计', '0', '', null, null);
INSERT INTO `menu` VALUES ('12', '省份列表', '0', '/manage/area/list/province', '8', '35');
INSERT INTO `menu` VALUES ('13', '注册标签列表', '0', '/manage/reg/tag/list', '8', '43');


