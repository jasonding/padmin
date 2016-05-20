package com.hrin.admin.service.manage.base;

import com.hrin.admin.domain.PageView;
import com.hrin.admin.domain.admin.Privilege;
import org.jason.moudule.dao.hibernate.base.DaoBase;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-11-29.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected abstract DaoBase getDao();


    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public void save(T t) {
        getDao().save(t);
    }

    @Override
    public void delete(T t) {
        getDao().delete(t);
    }

    @Override
    public void update(T t) {
        getDao().update(t);
    }

    @Override
    public T findById(Serializable id) {
        return (T) getDao().getById(id);
    }

    @Override
    public int getCount(Map<String, String> paramMap) {
        return getDao().getCount(paramMap);
    }

    @Override
    public PageView<T> getPageView(Map<String, String> paramMap, int page, int pageSize) {
        int totalCount = getCount(paramMap);
        int firstResult = PageView.calcFirstResult(page, pageSize);
        List<T> pageList = getDao().getPageList(paramMap, firstResult, pageSize);
        return  new PageView<T>(totalCount,pageList);
    }
}
