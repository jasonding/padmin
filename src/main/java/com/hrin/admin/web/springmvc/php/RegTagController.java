package com.hrin.admin.web.springmvc.php;

import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.enumeration.Status;
import com.hrin.admin.domain.php.RegTag;
import com.hrin.admin.service.php.RegTagService;
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
@Controller("regTagController")
@RequestMapping("/manage/reg/tag")
public class RegTagController extends BaseSpringMvcController {
    @Resource
    private RegTagService regTagService;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String save(Model model) {
        model.addAttribute("statusList", Status.values());
        return VIEW_REG_TAG_SAVE;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(RegTag regTag) {
        regTagService.save(regTag);
        return VIEW_REDIRECT_REG_TAG_LIST;
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable Integer id,Model model) {
        RegTag regTag = regTagService.findById(id);
        model.addAttribute(regTag);
        model.addAttribute("statusList", Status.values());
        return VIEW_REG_TAG_UPDATE;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(RegTag regTag) {
        regTagService.update(regTag);
        return VIEW_REDIRECT_REG_TAG_LIST;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list() {
        return VIEW_REG_TAG_LIST;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Object list(@RequestParam Map<String,String> param) {
        List<QueryBean> queryBeanList = getQuryBeanByMap(param);
        return regTagService.getList(queryBeanList,1,1000);
    }

}
