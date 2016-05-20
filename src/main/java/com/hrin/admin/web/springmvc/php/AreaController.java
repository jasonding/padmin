package com.hrin.admin.web.springmvc.php;

import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.enumeration.Status;
import com.hrin.admin.domain.php.Area;
import com.hrin.admin.service.php.AreaService;
import com.hrin.admin.util.NullUtil;
import com.hrin.admin.web.springmvc.base.BaseSpringMvcController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-12-25.
 */
@Controller("areaController")
@RequestMapping("/manage/area")
public class AreaController extends BaseSpringMvcController {
    @Resource
    private AreaService areaService;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String save(Model model) {
        model.addAttribute("pid", 0);
        model.addAttribute("statusList", Status.values());
        return VIEW_AREA_SAVE;
    }

    @RequestMapping(value = "/save/{pid}",method = RequestMethod.GET)
    public String save(@PathVariable Integer pid, Model model) {
        model.addAttribute("pid", pid);
        model.addAttribute("statusList", Status.values());
        return VIEW_AREA_SAVE;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Area area) {
        if(area.getPid() == Area.DEFAUT_PROVINCE_PID) {
            areaService.saveProvince(area);
            return VIEW_REDIRECT_AREA_LIST_PROVINCE;
        }else {
            areaService.saveCity(area);
            return VIEW_REDIRECT_AREA_LIST_CITY + area.getPid();
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Area area) {

        if(area.getPid() == Area.DEFAUT_PROVINCE_PID) {
            areaService.upateProvince(area);
            return VIEW_REDIRECT_AREA_LIST_PROVINCE;
        }else {
            areaService.updateCity(area);
            return VIEW_REDIRECT_AREA_LIST_CITY + area.getPid();
        }
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable Integer id,Model model) {
        Area area = areaService.findById(id);
        model.addAttribute(area);
        model.addAttribute("statusList", Status.values());
        return VIEW_AREA_UPDATE;
    }

    @RequestMapping(value = "/list/province",method = RequestMethod.GET)
    public String list() {
        return VIEW_AREA_LIST_PROVINCE;
    }

    @RequestMapping(value = "/list/province",method = RequestMethod.POST)
    @ResponseBody
    public Object list(@RequestParam Map<String,String> param) {
        param.put("EQ_pid","0");
        List<QueryBean> queryBeanList = getQuryBeanByMap(param);
        return areaService.getAreaList(queryBeanList,1,1000);
    }

    @RequestMapping(value = "/list/city/{pid}",method = RequestMethod.GET)
    public String list(@PathVariable Integer pid,Model model) {
        model.addAttribute("pid",pid);
        model.addAttribute("province",areaService.findById(pid));
        return VIEW_AREA_LIST_CITY;
    }

    @RequestMapping(value = "/list/city/{pid}",method = RequestMethod.POST)
    @ResponseBody
    public Object list(@PathVariable Integer pid, @RequestParam Map<String,String> param) {
        param.put("EQ_pid", String.valueOf(pid));
        List<QueryBean> queryBeanList = getQuryBeanByMap(param);
        return areaService.getAreaList(queryBeanList, 1, 1000);
    }

}
