package com.hrin.admin.service.manage;


import com.hrin.admin.domain.admin.Privilege;
import com.hrin.admin.service.manage.base.BaseService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by jason on 14-10-12.
 */
public interface PrivilegeService extends BaseService<Privilege> {

    public List<Privilege> findByIds(Collection<Serializable> ids);

}
