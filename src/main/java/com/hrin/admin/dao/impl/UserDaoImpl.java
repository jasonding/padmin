package com.hrin.admin.dao.impl;


import com.hrin.admin.dao.UserDao;
import com.hrin.admin.domain.admin.User;
import org.jason.moudule.dao.hibernate.base.DaoBaseImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-10-12.
 */
@Repository("userDao")
public class UserDaoImpl extends DaoBaseImpl<User> implements UserDao {
    @Override
    public User findByUserName(String username) {
        return (User) getSession().createQuery("FROM User WHERE username=:username")//
                         .setParameter("username",username)//
                         .setMaxResults(1).uniqueResult();
    }
}
