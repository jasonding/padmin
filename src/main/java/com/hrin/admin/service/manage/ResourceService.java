package com.hrin.admin.service.manage;


import com.hrin.admin.domain.PageView;
import com.hrin.admin.domain.admin.Resource;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-10-12.
 */
public interface ResourceService {
    public void save(Resource resource);
    public void delete(Resource resource);
    public void update(Resource resource);
    public List<Resource> findAll();
    public List<Resource> findAllWithPrivilege();
    public Resource findById(Long id);

    public int getCount(Map<String,String> paramMap);
    public PageView<Resource> getPageView(Map<String,String> paramMap,int page,int pageSize);

}
