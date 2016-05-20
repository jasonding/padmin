package com.hrin.admin.domain.enumeration;

/**
 * Created by jason on 14-12-6.
 */
public enum  Status {
    打开(1),关闭(2);
    private int status;

    Status(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static Status getStatus(int status) {
        Status[] values = Status.values();
        for (Status value : values) {
            if(value.getStatus() == status) {
                return value;
            }
        }
        return null;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
