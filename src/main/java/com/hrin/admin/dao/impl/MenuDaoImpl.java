package com.hrin.admin.dao.impl;


import com.hrin.admin.dao.MenuDao;
import com.hrin.admin.dao.UserDao;
import com.hrin.admin.domain.admin.Menu;
import com.hrin.admin.domain.admin.User;
import org.hibernate.Query;
import org.jason.moudule.dao.hibernate.base.DaoBaseImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-10-12.
 */
@Repository("menuDao")
public class MenuDaoImpl extends DaoBaseImpl<Menu> implements MenuDao {

    @Override
    protected List<Object> buildQueryCondition(Map<String, String> params, StringBuilder hql) {
        List<Object> list = new ArrayList<Object>();
        if(params == null || params.isEmpty()) return list;
        if(params.containsKey("parentMenuId")) {
            hql.append(" AND parentMenu = ? ");
            Menu parentMenu = new Menu();
            parentMenu.setId(Long.parseLong(params.get("parentMenuId")));
            list.add(parentMenu);
        }
        return list;
    }

    @Override
    public List<Menu> findTopMenu(Map<String, String> params, Integer firstRow, Integer maxRow) {
        StringBuilder hql = new StringBuilder(" FROM Menu WHERE parentMenu IS NULL ");
        List<Object> list = buildQueryCondition(params,hql);
        Query query = this.getSession().createQuery(hql.toString());
        for(int i=0; i<list.size(); i++) {
            query.setParameter(i, list.get(i));
        }
        return query.list();
    }

    @Override
    public Integer findTopMenuCount(Map<String,String> params) {
        StringBuilder hql = new StringBuilder(" SELECT COUNT(*) FROM Menu WHERE parentMenu IS NULL ");
        List<Object> list = buildQueryCondition(params,hql);
        Query query = this.getSession().createQuery(hql.toString());
        for(int i=0; i<list.size(); i++) {
            query.setParameter(i, list.get(i));
        }
        return ((Number) query.uniqueResult()).intValue();
    }
}
