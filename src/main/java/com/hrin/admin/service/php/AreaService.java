package com.hrin.admin.service.php;

import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.php.Area;
import com.hrin.admin.domain.php.wapper.AreaWapper;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-12-24.
 */
public interface AreaService {

    public Area findById(Integer id);
    public Object getAreaList(List<QueryBean> queryBeanList,int page, int pageSize);

    public void saveProvince(Area area);
    public void upateProvince(Area area);

    public void saveCity(Area area);
    public void updateCity(Area area);

    public void delete(Area area);

    public List<Area> getAreaListByPid(int pid);

    public List<AreaWapper> getAreaWapperList();

}
