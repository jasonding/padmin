package com.hrin.admin.dao.impl;


import com.hrin.admin.dao.RoleDao;
import com.hrin.admin.dao.UserDao;
import com.hrin.admin.domain.admin.Role;
import com.hrin.admin.domain.admin.User;
import org.jason.moudule.dao.hibernate.base.DaoBaseImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-10-12.
 */
@Repository("roleDao")
public class RoleDaoImpl extends DaoBaseImpl<Role> implements RoleDao {
}
