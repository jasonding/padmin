package com.hrin.admin.service.manage.impl;

import com.hrin.admin.dao.MenuDao;
import com.hrin.admin.dao.UserDao;
import com.hrin.admin.domain.PageView;
import com.hrin.admin.domain.admin.Menu;
import com.hrin.admin.domain.admin.User;
import com.hrin.admin.service.manage.MenuService;
import com.hrin.admin.service.manage.UserService;
import com.hrin.admin.service.manage.base.BaseServiceImpl;
import org.jason.moudule.dao.hibernate.base.DaoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-10-12.
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {
    public MenuDao menuDao;


    @Override
    public PageView<Menu> getTopMenuPage(Map<String, String> paramMap, int page, int pageSize) {
        int totalCount = menuDao.findTopMenuCount(paramMap);
        int firstResult = PageView.calcFirstResult(page, pageSize);
        List<Menu> pageList = menuDao.findTopMenu(paramMap, firstResult, pageSize);
        return  new PageView<Menu>(totalCount,pageList);
    }

    @Override
    protected DaoBase getDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
}
