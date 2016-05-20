package com.hrin.admin.domain.admin;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jason on 14-10-17.
 */
@Entity
@Table(name = "privilege")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name",length = 50)
    private String name;

    @ManyToMany(mappedBy = "privilegeSet",fetch = FetchType.LAZY,targetEntity = Role.class)
    private Set<Role> roleSet = new HashSet<Role>();

    @ManyToMany(fetch = FetchType.EAGER,targetEntity = Resource.class)
    @JoinTable(name="privilege_resource",
            joinColumns=@JoinColumn(name="priviege_id"),
            inverseJoinColumns=@JoinColumn(name="resource_id")
    )
    private Set<Resource> resourceSet = new HashSet<Resource>();
    @Transient
    private Set<Long> resourceIdSet = new HashSet<Long>();

    public Set<Long> getResourceIdSet() {
        return resourceIdSet;
    }

    public void setResourceIdSet(Set<Long> resourceIdSet) {
        this.resourceIdSet = resourceIdSet;
    }
    //
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "parent_privilege_id", referencedColumnName = "id")
//    private Privilege parentPrivilege;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentPrivilege", targetEntity = Privilege.class)
//    private Set<Privilege> privilegeSet = new HashSet<Privilege>();

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

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<Resource> getResourceSet() {
        return resourceSet;
    }

    public void setResourceSet(Set<Resource> resourceSet) {
        this.resourceSet = resourceSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Privilege)) return false;

        Privilege privilege = (Privilege) o;

        if (!id.equals(privilege.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Long getPrivilegeId(String configAttribute) {
        if(configAttribute == null || configAttribute.length() == 0)  return 0L;
        String idStr = configAttribute.substring(configAttribute.lastIndexOf("_"));
        return Long.parseLong(idStr);
    }

    public String getConfigAttribute() {
        return "ROLE_" + getId();
    }
}
