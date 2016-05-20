package com.hrin.admin.domain.php.wapper;

import com.hrin.admin.domain.php.Area;
import com.hrin.admin.domain.php.ProviderType;

import java.util.List;

/**
 * Created by jason on 15-1-27.
 */
public class AreaWapper {
    private Area province;
    private List<Area> cityList;

    public Area getProvince() {
        return province;
    }

    public void setProvince(Area province) {
        this.province = province;
    }

    public List<Area> getCityList() {
        return cityList;
    }

    public void setCityList(List<Area> cityList) {
        this.cityList = cityList;
    }
}
