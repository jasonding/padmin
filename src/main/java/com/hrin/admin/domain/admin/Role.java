package com.hrin.admin.domain.admin;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jason on 14-10-15.
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name",length = 20)
    private String name;

    @ManyToMany(mappedBy = "roleSet" , fetch = FetchType.LAZY,targetEntity = User.class)
    private Set<User> userSet = new HashSet<User>();

    @ManyToMany(fetch=FetchType.EAGER, targetEntity=Privilege.class,cascade=CascadeType.MERGE)
    @JoinTable(name="role_privilege",
            joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="priviege_id")
    )
    private Set<Privilege> privilegeSet = new HashSet<Privilege>();

    @Transient
    private Set<Long> privilegeIdSet = new HashSet<Long>();

    public Set<Long> getPrivilegeIdSet() {
        return privilegeIdSet;
    }

    public void setPrivilegeIdSet(Set<Long> privilegeIdSet) {
        this.privilegeIdSet = privilegeIdSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<Privilege> getPrivilegeSet() {
        return privilegeSet;
    }

    public void setPrivilegeSet(Set<Privilege> privilegeSet) {
        this.privilegeSet = privilegeSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (!id.equals(role.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
