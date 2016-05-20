package com.hrin.admin.service.manage;


import com.hrin.admin.domain.admin.Menu;
import com.hrin.admin.domain.admin.User;
import com.hrin.admin.service.manage.base.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jason on 14-10-12.
 */
public interface UserService extends BaseService<User> {

    public User findByUsername(String username);

    public Map<Menu, Set<Menu>> getUserMenu(User user);
}
