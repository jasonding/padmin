package com.hrin.admin.service.php;

import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.php.Provider;

import java.util.List;

/**
 * Created by jason on 14-12-24.
 */
public interface ProviderService {

    public Provider findById(Integer id);
    public Object getList(List<QueryBean> queryBeanList, int page, int pageSize);

    public void save(Provider provider);
    public void update(Provider provider);
}
