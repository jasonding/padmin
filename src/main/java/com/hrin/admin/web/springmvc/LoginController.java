package com.hrin.admin.web.springmvc;

import com.hrin.admin.constant.HrinConstant;
import com.hrin.admin.domain.admin.Menu;
import com.hrin.admin.domain.admin.User;
import com.hrin.admin.service.FreeMarkerManager;
import com.hrin.admin.service.mail.MailService;
import com.hrin.admin.service.manage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by jason on 14-10-17.
 */
@Controller("loginController")
@RequestMapping("/manage")
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(String username,String password){
        return;
    }

    @RequestMapping(value = "/list/error",method = RequestMethod.GET)
    public String listError(String message,Model model) {
        model.addAttribute("exception", message);
        return "exception";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(HrinConstant.WEB_HRIN_USER_KEY);
        return "redirect:/manage/login.do";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(HttpServletRequest httpServletRequest,Model model) {

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(HrinConstant.WEB_HRIN_USER_KEY);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username =userDetails.getUsername();

        Map<Menu, List<Menu>> menuList = new HashMap<Menu, List<Menu>>();
        List<Menu> parentMenuList = new ArrayList<Menu>();
        if(user == null) {
            // first request
            user = userService.findByUsername(username);
            user.setLastLoginTime(user.getLoginTime());
            user.setLoginTime(new Date());
            userService.update(user);
        }

        Map<Menu,Set<Menu>> menuSet= userService.getUserMenu(user);
        List<Menu> childMenuList = null;
        for (Map.Entry<Menu, Set<Menu>> menuSetEntry : menuSet.entrySet()) {
            childMenuList = new ArrayList<Menu>();
            //排序
            childMenuList.addAll(menuSetEntry.getValue());
            Collections.sort(childMenuList);
            menuList.put(menuSetEntry.getKey(), childMenuList);
        }
        //排序
        parentMenuList.addAll(menuList.keySet());
        Collections.sort(parentMenuList);
        model.addAttribute(user);
        model.addAttribute("menuList", menuList);
        model.addAttribute("parentMenuList", parentMenuList);

        session.setAttribute(HrinConstant.WEB_HRIN_USER_KEY, user);
        return "main";
    }

}
