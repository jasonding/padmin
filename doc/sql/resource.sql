-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '主页面', '0', '主页面', '/manage/main');
INSERT INTO `resource` VALUES ('2', '资源列表', '0', '资源列表', '/manage/resource/list');
INSERT INTO `resource` VALUES ('3', '资源列表数据', '1', '资源列表数据', '/manage/resource/list');
INSERT INTO `resource` VALUES ('4', '资源添加页面', '0', '资源添加页面', '/manage/resource/save');
INSERT INTO `resource` VALUES ('5', '资源添加', '1', '资源添加', '/manage/resource/save');
INSERT INTO `resource` VALUES ('6', '资源更新页面', '0', '资源更新页面', '/manage/resource/update/(\\d)+');
INSERT INTO `resource` VALUES ('7', '资源更新', '1', '资源更新', '/manage/resource/update');
INSERT INTO `resource` VALUES ('8', '权限列表', '0', '权限列表', '/manage/privilege/list');
INSERT INTO `resource` VALUES ('9', '权限列表数据', '1', '权限列表数据', '/manage/privilege/list');
INSERT INTO `resource` VALUES ('10', '权限添加页面', '0', '权限添加页面', '/manage/privilege/save');
INSERT INTO `resource` VALUES ('11', '权限添加', '1', '权限添加', '/manage/privilege/save');
INSERT INTO `resource` VALUES ('12', '权限更新页面', '0', '权限更新页面', '/manage/privilege/update/(\\d)+');
INSERT INTO `resource` VALUES ('13', '权限更新', '1', '权限更新', '/manage/privilege/update');
INSERT INTO `resource` VALUES ('14', '角色列表', '0', '角色列表', '/manage/role/list');
INSERT INTO `resource` VALUES ('15', '角色列表数据', '1', '角色列表数据', '/manage/role/list');
INSERT INTO `resource` VALUES ('16', '角色添加页面', '0', '角色添加页面', '/manage/role/save');
INSERT INTO `resource` VALUES ('17', '角色添加', '1', '角色添加', '/manage/role/save');
INSERT INTO `resource` VALUES ('18', '角色更新页面', '0', '角色更新页面', '/manage/role/update/(\\d)+');
INSERT INTO `resource` VALUES ('19', '角色更新', '1', '角色更新', '/manage/role/upate');
INSERT INTO `resource` VALUES ('20', '用户列表', '0', '用户列表', '/manage/user/list');
INSERT INTO `resource` VALUES ('21', '用户列表数据', '1', '用户列表数据', '/manage/user/list');
INSERT INTO `resource` VALUES ('22', '用户添加页面', '0', '用户添加页面', '/manage/user/save');
INSERT INTO `resource` VALUES ('23', '用户添加', '1', '用户添加', '/manage/user/save');
INSERT INTO `resource` VALUES ('24', '用户更新页面', '0', '用户更新页面', '/manage/user/update/(\\d)+');
INSERT INTO `resource` VALUES ('25', '用户更新', '1', '用户更新', '/manage/user/update');
INSERT INTO `resource` VALUES ('26', '父菜单列表', '0', '父菜单列表', '/manage/menu/list/parent');
INSERT INTO `resource` VALUES ('27', '父菜单列表数据', '1', '父菜单列表数据', '/manage/menu/list/parent');
INSERT INTO `resource` VALUES ('28', '子菜单列表', '0', '子菜单列表', '/manage/menu/list/child/(\\d)+');
INSERT INTO `resource` VALUES ('29', '子菜单列表数据', '1', '子菜单列表数据', '/manage/menu/list/child/(\\d)+');
INSERT INTO `resource` VALUES ('30', '父菜单添加页面', '0', '父菜单添加页面', '/manage/menu/save');
INSERT INTO `resource` VALUES ('31', '子菜单添加页面', '0', '子菜单添加页面', '/manage/menu/save/(\\d)+');
INSERT INTO `resource` VALUES ('32', '菜单添加', '1', '菜单添加', '/manage/menu/save');
INSERT INTO `resource` VALUES ('33', '菜单更新页面', '0', '菜单更新页面', '/manage/menu/update/(\\d)+');
INSERT INTO `resource` VALUES ('34', '菜单更新', '1', '菜单更新', '/manage/menu/update');
INSERT INTO `resource` VALUES ('35', '区域-省列表', '0', '省列表', '/manage/area/list/province');
INSERT INTO `resource` VALUES ('36', '区域-省列表数据', '1', '省列表数据', '/manage/area/list/province');
INSERT INTO `resource` VALUES ('37', '区域添加页面', '0', '区域添加页面', '/manage/area/save(/\\d+)*');
INSERT INTO `resource` VALUES ('38', '区域添加', '1', '区域添加', '/manage/area/save');
INSERT INTO `resource` VALUES ('39', '区域更新页面', '0', '区域更新页面', '/manage/area/update/(\\d)+');
INSERT INTO `resource` VALUES ('40', '区域更新', '1', '区域更新', '/manage/area/update');
INSERT INTO `resource` VALUES ('41', '区域-城市列表', '0', '城市列表', '/manage/area/list/city/(\\d)+');
INSERT INTO `resource` VALUES ('42', '区域-城市列表数据', '1', '城市列表数据', '/manage/area/list/city/(\\d)+');
INSERT INTO `resource` VALUES ('43', '注册标签列表', '0', '注册标签列表', '/manage/reg/tag/list');
INSERT INTO `resource` VALUES ('44', '注册标签列表数据', '1', '注册标签列表数据', '/manage/reg/tag/list');
INSERT INTO `resource` VALUES ('45', '注册标签添加页面', '0', '注册标签添加页面', '/manage/reg/tag/save');
INSERT INTO `resource` VALUES ('46', '注册标签添加', '1', '注册标签添加', '/manage/reg/tag/save');
INSERT INTO `resource` VALUES ('47', '注册标签更新页面', '0', '注册标签更新页面', '/manage/reg/tag/update/(\\d)+');
INSERT INTO `resource` VALUES ('48', '注册标签更新', '1', '注册标签更新', '/manage/reg/tag/update');
INSERT INTO `resource` VALUES ('49', '父机构类型列表', '0', '父机构类型列表', '/manage/provider/type/list/parent');
INSERT INTO `resource` VALUES ('50', '父机构类型列表数据', '1', '父机构类型列表数据', '/manage/provider/type/list/parent');
INSERT INTO `resource` VALUES ('51', '机构类型添加页面', '0', '机构类型添加页面', '/manage/provider/type/save(/\\d+)*');
INSERT INTO `resource` VALUES ('52', '机构类型添加', '1', '机构类型添加', '/manage/provider/type/save');
INSERT INTO `resource` VALUES ('53', '机构类型更新页面', '0', '结构类型更新页面', '/manage/provider/type/update/(\\d)+');
INSERT INTO `resource` VALUES ('54', '机构类型更新', '1', '结构类型更新', '/manage/provider/type/update');
INSERT INTO `resource` VALUES ('55', '子机构类型列表', '0', '子机构类型列表', '/manage/provider/type/list/child/(\\d)+');
INSERT INTO `resource` VALUES ('56', '子机构类型列表数据', '1', '子机构类型列表数据', '/manage/provider/type/list/child/(\\d)+');
INSERT INTO `resource` VALUES ('57', '机构列表', '0', '机构列表', '/manage/provider/list');
INSERT INTO `resource` VALUES ('58', '机构列表数据', '1', '机构列表数据', '/manage/provider/list');
INSERT INTO `resource` VALUES ('59', '机构添加页面', '0', '机构添加页面', '/manage/provider/save');
INSERT INTO `resource` VALUES ('60', '机构添加', '1', '机构添加', '/manage/provider/save');
INSERT INTO `resource` VALUES ('61', '机构更新页面', '0', '机构更新页面', '/manage/provider/update/(\\d)+');
INSERT INTO `resource` VALUES ('62', '机构更新', '1', '机构更新', '/manage/provider/update');