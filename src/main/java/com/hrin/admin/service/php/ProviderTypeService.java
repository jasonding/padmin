package com.hrin.admin.service.php;

import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.php.ProviderType;
import com.hrin.admin.domain.php.wapper.ProviderTypeWapper;

import java.util.List;

/**
 * Created by jason on 14-12-24.
 */
public interface ProviderTypeService {

    public ProviderType findById(Integer id);
    public Object getList(List<QueryBean> queryBeanList, int page, int pageSize);

    public void save(ProviderType providerType);
    public void update(ProviderType providerType);

    public List<ProviderType> getProviderTypeByPid(int pid);

    public List<ProviderTypeWapper> getProviderTypeWapperList();
}
