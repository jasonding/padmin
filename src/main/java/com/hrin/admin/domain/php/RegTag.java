package com.hrin.admin.domain.php;

import com.hrin.admin.domain.enumeration.Status;

/**
 * Created by jason on 14-12-6.
 */
public class RegTag {
    private int id;
    private String name;
    private int sort;
    private Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
