package com.hrin.admin.web.springmvc;

import com.hrin.admin.domain.PageView;
import com.hrin.admin.domain.admin.Privilege;
import com.hrin.admin.domain.admin.Resource;
import com.hrin.admin.service.manage.PrivilegeService;
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

import java.util.*;


/**
 * Created by jason on 14-10-17.
 */
@Controller("privilegeController")
@RequestMapping("/manage/privilege")
public class PrivilegeController {

    private PrivilegeService privilegeService;
    private ResourceService resourceService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list() {
        return "privilege/list";
    }

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String save(Model model) {
        List<Resource> resourceList = resourceService.findAll();
        model.addAttribute("resourceList",resourceList);
        return "privilege/save";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Privilege privilege) {
        Set<Long> resourceIdSet = privilege.getResourceIdSet();
        if(resourceIdSet != null && resourceIdSet.size()>0) {
            privilege.getResourceSet().clear();
            for (Long resourceId : resourceIdSet) {
                Resource resource = new Resource();
                resource.setId(resourceId);
                privilege.getResourceSet().add(resource);
            }
        }
        privilegeService.save(privilege);
        return "redirect:/manage/privilege/list";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Privilege privilege) {
        Set<Long> resourceIdSet = privilege.getResourceIdSet();
        if(resourceIdSet != null && resourceIdSet.size()>0) {
            privilege.getResourceSet().clear();
            for (Long resourceId : resourceIdSet) {
                Resource resource = new Resource();
                resource.setId(resourceId);
                privilege.getResourceSet().add(resource);
            }
        }
        privilegeService.update(privilege);
        return "redirect:/manage/privilege/list";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable Long id,Model model) {
        Privilege privilege = privilegeService.findById(id);

        Set<Resource> resourceSet = privilege.getResourceSet();
        if(resourceSet != null && resourceSet.size()> 0) {
            Set<Long> resourceIdSet = new HashSet<Long>();
            for (Resource resource : resourceSet) {
                resourceIdSet.add(resource.getId());
            }
            privilege.setResourceIdSet(resourceIdSet);
        }

        List<Resource> resourceList = resourceService.findAll();
        model.addAttribute("resourceList",resourceList);
        model.addAttribute(privilege);
        return "privilege/update";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        if(id == null) return "";
        Privilege privilege = new Privilege();
        privilege.setId(id);
        privilegeService.delete(privilege);
        return "redirect:/manage/privilege/list";
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Object list( String page,String rows,Map<String,String> param) {
        int pageNum = 1;
        int pageSize = 5;
        try {
            pageNum = Integer.parseInt(page);
        }catch (Exception e){}
        try {
            pageSize = Integer.parseInt(rows);
        }catch (Exception e){}
        PageView<Privilege> pageView = privilegeService.getPageView(param, pageNum, pageSize);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageView.getRecordCount());
        JSONArray array = new JSONArray();
        List<Privilege> recordList = pageView.getRecordList();
        for(Privilege resource :recordList) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id",resource.getId());
            jsonObject1.put("name",resource.getName());
            array.add(jsonObject1);
        }
        jsonObject.put("rows",array);

        return  jsonObject;
    }

    @Autowired
    public void setPrivilegeService(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
