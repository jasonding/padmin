package com.hrin.admin.service.manage;

import com.hrin.admin.domain.PageView;
import com.hrin.admin.domain.admin.Menu;
import com.hrin.admin.service.manage.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-12-6.
 */
public interface MenuService extends BaseService<Menu>{

    public PageView<Menu> getTopMenuPage(Map<String, String> paramMap, int page, int pageSize);
}
