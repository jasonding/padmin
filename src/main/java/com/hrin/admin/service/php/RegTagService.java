package com.hrin.admin.service.php;

import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.php.RegTag;

import java.util.List;

/**
 * Created by jason on 14-12-24.
 */
public interface RegTagService {

    public RegTag findById(Integer id);
    public Object getList(List<QueryBean> queryBeanList, int page, int pageSize);

    public void save(RegTag regTag);
    public void update(RegTag regTag);
}
