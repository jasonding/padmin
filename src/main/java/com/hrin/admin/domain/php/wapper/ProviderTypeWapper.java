package com.hrin.admin.domain.php.wapper;

import com.hrin.admin.domain.php.ProviderType;

import java.util.List;

/**
 * Created by jason on 15-1-27.
 */
public class ProviderTypeWapper {
    private ProviderType parentProviderType;
    private List<ProviderType> childProviderTypeList;

    public ProviderType getParentProviderType() {
        return parentProviderType;
    }

    public void setParentProviderType(ProviderType parentProviderType) {
        this.parentProviderType = parentProviderType;
    }

    public List<ProviderType> getChildProviderTypeList() {
        return childProviderTypeList;
    }

    public void setChildProviderTypeList(List<ProviderType> childProviderTypeList) {
        this.childProviderTypeList = childProviderTypeList;
    }
}
