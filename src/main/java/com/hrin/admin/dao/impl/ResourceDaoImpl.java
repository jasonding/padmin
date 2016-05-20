package com.hrin.admin.dao.impl;


import com.hrin.admin.dao.ResourceDao;
import com.hrin.admin.dao.UserDao;
import com.hrin.admin.domain.admin.Resource;
import com.hrin.admin.domain.admin.User;
import org.jason.moudule.dao.hibernate.base.DaoBaseImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-10-12.
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends DaoBaseImpl<Resource> implements ResourceDao {
    @Override
    protected List<Object> buildQueryCondition(Map<String, String> params, StringBuilder hql) {
        if(params == null || params.isEmpty()){
            return super.buildQueryCondition(params,hql);
        }
        List<Object> list = new ArrayList<Object>();

        if(params.get("resourceName") != null && params.get("resourceName").trim().length()>0) {
            hql.append(" AND resource = ?");
            list.add(params.get("resourceName"));
        }
        return list;
    }
}
