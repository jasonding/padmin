package com.hrin.admin.dao.impl;


import com.hrin.admin.dao.PrivilegeDao;
import com.hrin.admin.dao.UserDao;
import com.hrin.admin.domain.admin.Privilege;
import com.hrin.admin.domain.admin.User;
import org.jason.moudule.dao.hibernate.base.DaoBaseImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-10-12.
 */
@Repository("privilegeDao")
public class PrivilegeDaoImpl extends DaoBaseImpl<Privilege> implements PrivilegeDao {

}
