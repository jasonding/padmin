package com.hrin.admin.domain.admin;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jason on 14-11-5.
 */
@Entity
@Table(name = "menu")
public class Menu implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",length = 64)
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "orders",length = 4)
    private int order;

    @OneToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "parent_menu_id", referencedColumnName = "id")
    private Menu parentMenu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentMenu", targetEntity = Menu.class)
    private Set<Menu> menuSet = new HashSet<Menu>();

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Set<Menu> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        if (!id.equals(menu.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Menu other = (Menu) o;
        return new Integer(this.getOrder()).compareTo(other.getOrder());
    }
}
