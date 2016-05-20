package com.hrin.admin.service.manage.impl;

import com.hrin.admin.dao.RoleDao;
import com.hrin.admin.domain.admin.Role;
import com.hrin.admin.service.manage.RoleService;
import com.hrin.admin.service.manage.base.BaseServiceImpl;
import org.jason.moudule.dao.hibernate.base.DaoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 14-10-12.
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    protected DaoBase getDao() {
        return roleDao;
    }
}
