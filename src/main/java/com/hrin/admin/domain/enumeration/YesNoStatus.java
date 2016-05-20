package com.hrin.admin.domain.enumeration;

/**
 * Created by jason on 14-12-6.
 */
public enum YesNoStatus {
    是(1),否(0);
    private int status;

    YesNoStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static YesNoStatus getStatus(int status) {
        YesNoStatus[] values = YesNoStatus.values();
        for (YesNoStatus value : values) {
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
