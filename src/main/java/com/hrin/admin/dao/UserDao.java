package com.hrin.admin.dao;

import com.hrin.admin.domain.admin.User;
import org.jason.moudule.dao.hibernate.base.DaoBase;

/**
 * Created by jason on 14-10-12.
 */
public interface UserDao extends DaoBase<User> {
    public User findByUserName(String username);
}
