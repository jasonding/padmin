package com.hrin.admin.web.springmvc;

import com.hrin.admin.domain.PageView;
import com.hrin.admin.domain.admin.Resource;
import com.hrin.admin.domain.enumeration.HttpMethodType;
import com.hrin.admin.service.manage.ResourceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * Created by jason on 14-10-17.
 */
@Controller("resourceController")
@RequestMapping("/manage/resource")
public class ResourceController {

    private ResourceService resourceService;


    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String save(Model model) {
        model.addAttribute("httpMethodList", HttpMethodType.values());
        return "resource/save";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Resource resource) {
        resourceService.save(resource);
        return "redirect:/manage/resource/list";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Resource resource) {
        resourceService.update(resource);
        return "redirect:/manage/resource/list";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable Long id,Model model) {
        if(id == null) return "";
        Resource resource = resourceService.findById(id);
        model.addAttribute(resource);
        model.addAttribute("httpMethodList", HttpMethodType.values());
        return "resource/update";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        if(id == null) return "";
        Resource resource = new Resource();
        resource.setId(id);
        resourceService.delete(resource);
        return "redirect:/manage/resource/list";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list() {
        return "resource/list";
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Object list( String page,String rows,@RequestParam  Map<String,String> param) {
        int pageNum = 1;
        int pageSize = 5;
        try {
            pageNum = Integer.parseInt(page);
        }catch (Exception e){}
        try {
            pageSize = Integer.parseInt(rows);
        }catch (Exception e){}

        PageView<Resource> pageView = resourceService.getPageView(param, pageNum, pageSize);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageView.getRecordCount());
        JSONArray array = new JSONArray();
        List<Resource> recordList = pageView.getRecordList();
        for(Resource resource :recordList) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id",resource.getId());
            jsonObject1.put("resource",resource.getDisplayName());
            jsonObject1.put("urlPartten",resource.getUrlPartten());
            jsonObject1.put("httpMethod",resource.getHttpMethod());
            array.add(jsonObject1);
        }
        jsonObject.put("rows",array);
        return  jsonObject;
    }


    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

}
