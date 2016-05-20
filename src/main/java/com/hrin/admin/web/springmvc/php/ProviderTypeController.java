package com.hrin.admin.web.springmvc.php;

import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.enumeration.Status;
import com.hrin.admin.domain.php.ProviderType;
import com.hrin.admin.service.php.ProviderTypeService;
import com.hrin.admin.web.springmvc.base.BaseSpringMvcController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-12-25.
 */
@Controller("providerTypeController")
@RequestMapping("/manage/provider/type")
public class ProviderTypeController extends BaseSpringMvcController {
    @Resource
    private ProviderTypeService providerTypeService;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String save(Model model) {
        model.addAttribute("pid", 0);
        model.addAttribute("statusList", Status.values());
        return VIEW_PROVIDER_TYPE_SAVE;
    }

    @RequestMapping(value = "/save/{pid}",method = RequestMethod.GET)
    public String save(@PathVariable Integer pid, Model model) {
        model.addAttribute("pid", pid);
        model.addAttribute("statusList", Status.values());
        return VIEW_PROVIDER_TYPE_SAVE;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(ProviderType providerType) {
        providerTypeService.save(providerType);
        if(providerType.getPid() == ProviderType.DEFAUT_PARENT_ID) {
            return VIEW_REDIRECT_PROVIDER_TYPE_LIST_PARENT;
        }else {
            return String.format(VIEW_REDIRECT_PROVIDER_TYPE_LIST_CHILD,providerType.getPid());
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(ProviderType providerType) {
        providerTypeService.update(providerType);
        if(providerType.getPid() == ProviderType.DEFAUT_PARENT_ID) {
            return VIEW_REDIRECT_PROVIDER_TYPE_LIST_PARENT;
        }else {
            return String.format(VIEW_REDIRECT_PROVIDER_TYPE_LIST_CHILD,providerType.getPid());
        }
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable Integer id,Model model) {
        ProviderType providerType = providerTypeService.findById(id);
        model.addAttribute(providerType);
        model.addAttribute("statusList", Status.values());
        return VIEW_PROVIDER_TYPE_UPDATE;
    }

    @RequestMapping(value = "/list/parent",method = RequestMethod.GET)
    public String list() {
        return VIEW_PROVIDER_TYPE_LIST_PARENT;
    }

    @RequestMapping(value = "/list/parent",method = RequestMethod.POST)
    @ResponseBody
    public Object list(@RequestParam Map<String,String> param) {
        param.put("EQ_pid","0");
        List<QueryBean> queryBeanList = getQuryBeanByMap(param);
        return providerTypeService.getList(queryBeanList, 1, 1000);
    }

    @RequestMapping(value = "/list/child/{pid}",method = RequestMethod.GET)
    public String list(@PathVariable Integer pid,Model model) {
        model.addAttribute("pid",pid);
        model.addAttribute("providerType",providerTypeService.findById(pid));
        return VIEW_PROVIDER_TYPE_LIST_CHILD;
    }

    @RequestMapping(value = "/list/child/{pid}",method = RequestMethod.POST)
    @ResponseBody
    public Object list(@PathVariable Integer pid, @RequestParam Map<String,String> param) {
        param.put("EQ_pid",String.valueOf(pid));
        List<QueryBean> queryBeanList = getQuryBeanByMap(param);
        return providerTypeService.getList(queryBeanList, 1, 1000);
    }

}
