package com.hrin.admin.domain.admin;

import com.hrin.admin.domain.enumeration.HttpMethodType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jason on 14-10-17.
 */
@Entity
@Table(name = "resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "resource_name",length = 50)
    private String resource;
    @Column(name = "url_partten")
    private String urlPartten;
    @Column(name = "display_name",length = 50)
    private String displayName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="http_method",length=1)
    private HttpMethodType httpMethod;

    @OneToOne(mappedBy = "resource",targetEntity = Menu.class)
    private Menu menu;

    @ManyToMany(targetEntity = Privilege.class,fetch = FetchType.LAZY,mappedBy = "resourceSet")
    private Set<Privilege> privilegeSet = new HashSet<Privilege>();

    public String getUrlPartten() {
        return urlPartten;
    }

    public void setUrlPartten(String urlPartten) {
        this.urlPartten = urlPartten;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<Privilege> getPrivilegeSet() {
        return privilegeSet;
    }

    public void setPrivilegeSet(Set<Privilege> privilegeSet) {
        this.privilegeSet = privilegeSet;
    }

    public HttpMethodType getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethodType httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resource)) return false;

        Resource resource = (Resource) o;

        if (!id.equals(resource.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
