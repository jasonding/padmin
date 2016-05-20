package com.hrin.admin.service.security;

import com.hrin.admin.domain.admin.Privilege;
import com.hrin.admin.domain.admin.Role;
import com.hrin.admin.domain.admin.User;
import com.hrin.admin.domain.enumeration.Status;
import com.hrin.admin.service.manage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jason on 14-10-29.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userWithoutAuth = userService.findByUsername(username);
        if(userWithoutAuth == null) throw  new UsernameNotFoundException("用户名不正确");
        Set<Role> roleSet = userWithoutAuth.getRoleSet();
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<GrantedAuthority>();
        for (Role role : roleSet) {
            Set<Privilege> privilegeSet = role.getPrivilegeSet();
            for (Privilege privilege : privilegeSet) {
                grantedAuthoritySet.add(new SimpleGrantedAuthority(privilege.getConfigAttribute()));
            }

        }
        return new org.springframework.security.core.userdetails.User(username,userWithoutAuth.getPassword(), userWithoutAuth.isEnabled(),true,true,true,grantedAuthoritySet);
    }
}
