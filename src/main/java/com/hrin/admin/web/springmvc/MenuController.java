package com.hrin.admin.web.springmvc;

import com.hrin.admin.domain.PageView;
import com.hrin.admin.domain.admin.Menu;
import com.hrin.admin.domain.admin.Resource;
import com.hrin.admin.service.manage.MenuService;
import com.hrin.admin.service.manage.ResourceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by jason on 14-10-17.
 */
@Controller("menuController")
@RequestMapping("/manage/menu")
public class MenuController {

    private MenuService menuService;
    private ResourceService resourceService;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String save(Model model) {
        List<Resource> resourceList = resourceService.findAll();
        model.addAttribute("resourceList",resourceList);
        return "menu/save";
    }

    @RequestMapping(value = "/save/{parentId}",method = RequestMethod.GET)
    public String save(@PathVariable Long parentId, Model model) {
        List<Resource> resourceList = resourceService.findAll();
        model.addAttribute("resourceList",resourceList);
        model.addAttribute("parentId",parentId);
        return "menu/save";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Menu menu) {
        menuService.save(menu);
        if(menu.getParentMenu() != null) {
            return "redirect:/manage/menu/list/child/" + menu.getParentMenu().getId();
        }else {
            return "redirect:/manage/menu/list/parent";
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Menu menu) {
        menuService.update(menu);
        if(menu.getParentMenu() != null) {
            return "redirect:/manage/menu/list/child/" + menu.getParentMenu().getId();
        }else {
            return "redirect:/manage/menu/list/parent";
        }
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable Long id,Model model) {
        if(id == null) return "";
        Menu menu = menuService.findById(id);

        List<Resource> resourceList = resourceService.findAll();
        model.addAttribute("resourceList",resourceList);
        model.addAttribute(menu);
        return "menu/update";
    }

    @RequestMapping(value = "/list/parent",method = RequestMethod.GET)
    public String list() {
        return "menu/list";
    }

    @RequestMapping(value = "/list/parent",method = RequestMethod.POST)
    @ResponseBody
    public Object list( String page,String rows,Map<String,String> param) {
        int pageNum = 1;
        int pageSize = 20;
        try {
            pageNum = Integer.parseInt(page);
        }catch (Exception e){}
        try {
            pageSize = Integer.parseInt(rows);
        }catch (Exception e){}

        PageView<Menu> pageView = menuService.getTopMenuPage(param,pageNum,pageSize);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageView.getRecordCount());
        JSONArray array = new JSONArray();
        List<Menu> recordList = pageView.getRecordList();
        for(Menu menu :recordList) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id", menu.getId());
            jsonObject1.put("name", menu.getName());
            jsonObject1.put("order", menu.getOrder());
            array.add(jsonObject1);
        }
        jsonObject.put("rows",array);
        return  jsonObject;
    }

    @RequestMapping(value = "/list/child/{parentId}",method = RequestMethod.GET)
    public String list(@PathVariable Long parentId,Model model) {
        model.addAttribute("parentMenuId",parentId);
        return "menu/listChild";
    }

    @RequestMapping(value = "/list/child/{parentId}",method = RequestMethod.POST)
    @ResponseBody
    public Object list( @PathVariable Long parentId, String page,String rows,Map<String,String> param) {
        int pageNum = 1;
        int pageSize = 20;
        try {
            pageNum = Integer.parseInt(page);
        }catch (Exception e){}
        try {
            pageSize = Integer.parseInt(rows);
        }catch (Exception e){}

        if(param == null) {
            param = new HashMap<String, String>();
        }
        param.put("parentMenuId",parentId.toString());
        PageView<Menu> pageView = menuService.getPageView(param,pageNum,pageSize);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageView.getRecordCount());
        JSONArray array = new JSONArray();
        List<Menu> recordList = pageView.getRecordList();
        for(Menu menu :recordList) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id", menu.getId());
            jsonObject1.put("name", menu.getName());
            array.add(jsonObject1);
        }
        jsonObject.put("rows",array);
        return  jsonObject;
    }

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
