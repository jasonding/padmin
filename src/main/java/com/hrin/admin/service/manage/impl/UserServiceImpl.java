package com.hrin.admin.service.manage.impl;

import com.hrin.admin.dao.UserDao;
import com.hrin.admin.domain.admin.*;
import com.hrin.admin.service.manage.UserService;
import com.hrin.admin.service.manage.base.BaseServiceImpl;
import org.jason.moudule.dao.hibernate.base.DaoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jason on 14-10-12.
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected DaoBase getDao() {
        return userDao;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public Map<Menu, Set<Menu>> getUserMenu(User user) {

        Map<Menu, Set<Menu>> menuMap = new HashMap<Menu, Set<Menu>>();
        if (user == null) return Collections.emptyMap();
        for (Role role : user.getRoleSet()) {
            for (Privilege privilege : role.getPrivilegeSet()) {
                for (Resource resource : privilege.getResourceSet()) {
                    Menu menu = resource.getMenu();
                    if(menu == null) continue;
                    Menu parentMenu = menu.getParentMenu();

                    if (menuMap.containsKey(parentMenu)) {
                        menuMap.get(parentMenu).add(menu);
                    }else {
                        Set<Menu> set = new HashSet<Menu>();
                        Menu key = null;
                        if (parentMenu == null) {
                            if(!menuMap.containsKey(menu)) {
                                key = menu;
                            }
                        }else {
                            set.add(menu);
                            key = parentMenu;
                        }
                        menuMap.put(key,set);
                    }

                }
            }
        }

        return menuMap;
    }
}
