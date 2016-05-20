package com.hrin.admin.domain.admin;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jason on 14-10-12.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "user_name",length = 64)
    private String username;

    @Column(name = "password",length = 64)
    private String password;

    @Column(name = "remote_ip",length = 64)
    private String remoteIp;

    @Column(name="enabled_ip",length=1,nullable = false)
    private boolean enabledIp;

    @Column(name="enabled",length=1,nullable = false)
    private boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "login_time")
    private Date loginTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @ManyToMany(fetch=FetchType.EAGER,targetEntity=Role.class)
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private Set<Role> roleSet = new HashSet<Role>();

    @Transient
    private Set<Long> roleIdSet = new HashSet<Long>();

    public Set<Long> getRoleIdSet() {
        return roleIdSet;
    }

    public void setRoleIdSet(Set<Long> roleIdSet) {
        this.roleIdSet = roleIdSet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public boolean isEnabledIp() {
        return enabledIp;
    }

    public void setEnabledIp(boolean enabledIp) {
        this.enabledIp = enabledIp;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
