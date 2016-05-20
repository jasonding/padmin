package com.hrin.admin.service.manage.base;

import com.hrin.admin.domain.PageView;
import com.hrin.admin.domain.admin.Privilege;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-11-29.
 */
public interface BaseService<T> {

    public List<T> findAll();
    public void save(T t);
    public void delete(T t);
    public void update(T t);
    public T findById(Serializable id);

    public int getCount(Map<String,String> paramMap);
    public PageView<T> getPageView(Map<String,String> paramMap,int page,int pageSize);

}
