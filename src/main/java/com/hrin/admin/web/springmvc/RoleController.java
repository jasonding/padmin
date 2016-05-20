package com.hrin.admin.web.springmvc;

import com.hrin.admin.domain.PageView;
import com.hrin.admin.domain.admin.Privilege;
import com.hrin.admin.domain.admin.Role;
import com.hrin.admin.service.manage.PrivilegeService;
import com.hrin.admin.service.manage.RoleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by jason on 14-10-17.
 */
@Controller("roleController")
@RequestMapping("/manage/role")
public class RoleController {

    private RoleService roleService;
    private PrivilegeService privilegeService;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String save(Model model) {
        List<Privilege> privilegeList = privilegeService.findAll();
        model.addAttribute("privilegeList",privilegeList);
        return "role/save";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Role role) {
        Set<Long> privilegeIdSet = role.getPrivilegeIdSet();
        if(privilegeIdSet != null && privilegeIdSet.size()>0) {
            role.getPrivilegeSet().clear();
            for (Long privilegeId : privilegeIdSet) {
                Privilege privilege = new Privilege();
                privilege.setId(privilegeId);
                role.getPrivilegeSet().add(privilege);
            }
        }

        roleService.save(role);
        return "redirect:/manage/role/list";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Role role) {
        Set<Long> privilegeIdSet = role.getPrivilegeIdSet();
        if(privilegeIdSet != null && privilegeIdSet.size()>0) {
            role.getPrivilegeSet().clear();
            for (Long privilegeId : privilegeIdSet) {
                Privilege privilege = new Privilege();
                privilege.setId(privilegeId);
                role.getPrivilegeSet().add(privilege);
            }
        }
        roleService.update(role);
        return "redirect:/manage/role/list";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable Long id,Model model) {
        Role role = roleService.findById(id);
        Set<Privilege> privilegeSet = role.getPrivilegeSet();
        if(privilegeSet != null && privilegeSet.size()>0) {
            for (Privilege privilege : privilegeSet) {
                role.getPrivilegeIdSet().add(privilege.getId());
            }
        }
        List<Privilege> privilegeList = privilegeService.findAll();
        model.addAttribute("privilegeList",privilegeList);
        model.addAttribute(role);
        return "role/update";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {

        return "redirect:/manage/role/list";
    }


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list() {
        return "role/list";
    }


    @RequestMapping(value = "/list",method = RequestMethod.POST)
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
        PageView<Role> pageView = roleService.getPageView(param, pageNum, pageSize);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageView.getRecordCount());
        JSONArray array = new JSONArray();
        List<Role> recordList = pageView.getRecordList();
        for(Role role :recordList) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id", role.getId());
            jsonObject1.put("name", role.getName());
            array.add(jsonObject1);
        }
        jsonObject.put("rows",array);

        return  jsonObject;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPrivilegeService(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }
}
