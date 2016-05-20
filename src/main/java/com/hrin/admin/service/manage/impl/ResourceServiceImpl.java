package com.hrin.admin.service.manage.impl;

import com.hrin.admin.dao.ResourceDao;
import com.hrin.admin.domain.PageView;
import com.hrin.admin.domain.admin.Resource;
import com.hrin.admin.service.manage.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-10-12.
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    private ResourceDao resourceDao;

    @Override
    public int getCount(Map<String, String> paramMap) {
        return resourceDao.getCount(paramMap);
    }

    @Override
    public PageView<Resource> getPageView(Map<String, String> paramMap, int page, int pageSize) {
        int totalCount = getCount(paramMap);
        int firstResult = PageView.calcFirstResult(page, pageSize);
        List<Resource> pageList = resourceDao.getPageList(paramMap, firstResult, pageSize);
        return  new PageView<Resource>(totalCount,pageList);
    }

    @Override
    public Resource findById(Long id) {
        return resourceDao.getById(id);
    }

    @Override
    public List<Resource> findAllWithPrivilege() {
        List<Resource> all = resourceDao.findAll();
        for (Resource resource : all) {
            resource.getPrivilegeSet().size();
        }
        return all;
    }

    @Override
    public void delete(Resource resource) {
        resourceDao.delete(resource);
    }

    @Autowired
    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    @Override
    public void update(Resource resource) {
        resourceDao.update(resource);
    }

    @Override
    public void save(Resource resource) {
        resourceDao.save(resource);
    }

    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }

}
