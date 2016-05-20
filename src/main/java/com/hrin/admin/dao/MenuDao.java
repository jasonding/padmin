package com.hrin.admin.dao;

import com.hrin.admin.domain.admin.Menu;
import org.jason.moudule.dao.hibernate.base.DaoBase;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-10-12.
 */
public interface MenuDao extends DaoBase<Menu> {
    public List<Menu> findTopMenu(Map<String, String> params, Integer firstRow, Integer maxRow);
    public Integer findTopMenuCount(Map<String,String> params);
}
