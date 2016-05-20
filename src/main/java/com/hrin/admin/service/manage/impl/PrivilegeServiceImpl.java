package com.hrin.admin.service.manage.impl;

import com.hrin.admin.dao.PrivilegeDao;
import com.hrin.admin.domain.admin.Privilege;
import com.hrin.admin.service.manage.PrivilegeService;
import com.hrin.admin.service.manage.base.BaseServiceImpl;
import org.jason.moudule.dao.hibernate.base.DaoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by jason on 14-10-12.
 */
@Service("privilegeService")
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege> implements PrivilegeService {
    private PrivilegeDao privilegeDao;

    @Override
    public List<Privilege> findByIds(Collection<Serializable> ids) {
        return privilegeDao.findByIds(ids);
    }

    @Autowired
    public void setPrivilegeDao(PrivilegeDao privilegeDao) {
        this.privilegeDao = privilegeDao;
    }

    @Override
    protected DaoBase getDao() {
        return privilegeDao;
    }
}
