package com.hrin.admin.domain.enumeration;

/**
 * Created by jason on 14-11-30.
 */
public enum ConsumerStatus {
    正常(1),
    锁定(2),
    禁用(3),
    注销(4),
    系统账户(5);

    ConsumerStatus(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
