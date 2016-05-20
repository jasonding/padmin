package com.hrin.admin.domain.enumeration;

/**
 *
 */
public enum ProvirderStatus {
    正常(1),
    锁定(2),
    禁用(3),
    注销(4),
    注册待审核(5),
    认领待审核(6),
    资料待审核(7),
    开通未认领(8);

    ProvirderStatus(int id) {
        this.id = id;
    }

    private int id;


    public static ProvirderStatus getProvirderStatus(int status) {
        ProvirderStatus[] values = ProvirderStatus.values();
        for (ProvirderStatus value : values) {
            if(value.getId() == status) {
                return value;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
