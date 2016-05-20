package com.hrin.admin.web.springmvc;

import com.hrin.admin.domain.PageView;
import com.hrin.admin.domain.admin.Role;
import com.hrin.admin.domain.admin.User;
import com.hrin.admin.service.manage.RoleService;
import com.hrin.admin.service.manage.UserService;
import com.hrin.admin.util.MD5Util;
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
@Controller("userController")
@RequestMapping("/manage/user")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String save(Model model) {
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList",roleList);
        return "user/save";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(User user) {
        Set<Long> roleIdSet = user.getRoleIdSet();
        if(roleIdSet != null && roleIdSet.size()>0) {
            user.getRoleSet().clear();
            for (Long roleId : roleIdSet) {
                Role role = new Role();
                role.setId(roleId);
                user.getRoleSet().add(role);
            }
        }
        user.setPassword(MD5Util.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/manage/user/list";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(User user,String confrimPassword) {
        Set<Long> roleIdSet = user.getRoleIdSet();

        if(user.getPassword() == null || user.getPassword().isEmpty() ) {
            User dbUser = userService.findById(user.getId());
            user.setPassword(dbUser.getPassword());
        }else {
            if(!user.getPassword().equals(confrimPassword)) {
                //抛异常，密码输入不一致
            }
            user.setPassword(MD5Util.encode(user.getPassword()));
        }


        if(roleIdSet != null && roleIdSet.size()>0) {
            user.getRoleSet().clear();
            for (Long roleId : roleIdSet) {
                Role role = new Role();
                role.setId(roleId);
                user.getRoleSet().add(role);
            }
        }
        userService.update(user);
        return "redirect:/manage/user/list";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable Long id,Model model) {
        User user = userService.findById(id);
        Set<Role> roleSet = user.getRoleSet();
        if(roleSet != null && roleSet.size()>0) {
            for (Role privilege : roleSet) {
                user.getRoleIdSet().add(privilege.getId());
            }
        }
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList",roleList);
        model.addAttribute(user);
        return "user/update";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {

        return "redirect:/manage/user/list";
    }


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list() {
        return "user/list";
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
        PageView<User> pageView = userService.getPageView(param, pageNum, pageSize);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageView.getRecordCount());
        JSONArray array = new JSONArray();
        List<User> recordList = pageView.getRecordList();
        for(User user :recordList) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id", user.getId());
            jsonObject1.put("name", user.getUsername());
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
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
