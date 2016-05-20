package com.hrin.admin.web.springmvc.php;

import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.php.Area;
import com.hrin.admin.domain.php.Provider;
import com.hrin.admin.domain.php.wapper.ProviderTypeWapper;
import com.hrin.admin.service.php.AreaService;
import com.hrin.admin.service.php.ProviderService;
import com.hrin.admin.service.php.ProviderTypeService;
import com.hrin.admin.web.springmvc.base.BaseSpringMvcController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 14-12-25.
 */
@Controller("providerController")
@RequestMapping("/manage/provider")
public class ProviderController extends BaseSpringMvcController {
    @Resource
    private ProviderTypeService providerTypeService;
    @Resource
    private ProviderService providerService;
    @Resource
    private AreaService areaService;
    @Value("${hrin.provider.logo.targetpath}")
    private String targetpath;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String save(Model model) {
        if(!model.containsAttribute("provider")) {
            model.addAttribute(new Provider());
        }

        buildFormData(model);
        return VIEW_PROVIDER_SAVE;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(HttpServletRequest request,@RequestParam(value = "logoFile") MultipartFile uploadFile,
                       @Valid @ModelAttribute Provider provider,BindingResult bindingResult,Model model) throws Exception{
        if(bindingResult.hasErrors()) {
            return save(model);
        }

        handleUploadFile(request, uploadFile, provider);

        providerService.save(provider);
        return VIEW_REDIRECT_PROVIDER_LIST;
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable Integer id,Model model) {
        if(!model.containsAttribute("provider")) {
            model.addAttribute(providerService.findById(id));
        }

        buildFormData(model);
        return VIEW_PROVIDER_UPDATE;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(HttpServletRequest request,@RequestParam(value = "logoFile") MultipartFile uploadFile,
                         @Valid @ModelAttribute Provider provider,BindingResult bindingResult,Model model) throws Exception{
        if(bindingResult.hasErrors()) {
            return update(provider.getProviderId(), model);
        }

        handleUploadFile(request, uploadFile, provider);

        providerService.update(provider);

        return VIEW_REDIRECT_PROVIDER_LIST;
    }


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list() {
        return VIEW_PROVIDER_LIST;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Object list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                       @RequestParam(value = "rows",defaultValue = "20")Integer pageSize,
                       @RequestParam Map<String,String> param) {
        List<QueryBean> quryBeanByMap = getQuryBeanByMap(param);
        return providerService.getList(quryBeanByMap,page,pageSize);
    }

    private void handleUploadFile(HttpServletRequest request, MultipartFile uploadFile, Provider provider) throws IOException {
        if(uploadFile.isEmpty()) return;

        String fileSuffix = getFileSuffix(uploadFile.getOriginalFilename());
        String targetFilePath = request.getServletContext().getRealPath("")+ targetpath;
        String name = String.valueOf(new Date().getTime());

        File dest = getFile(targetFilePath + getNewFileName(name,fileSuffix));
        uploadFile.transferTo(dest);
        resizeImage(dest, targetFilePath + getNewFileName2525(name, fileSuffix), 25, 25, fileSuffix);
        resizeImage(dest, targetFilePath + getNewFileName5050(name, fileSuffix), 50, 50, fileSuffix);
        resizeImage(dest, targetFilePath + getNewFileName100100(name, fileSuffix), 100, 100, fileSuffix);
        //resizeImage(dest, targetFilePath + getNewFileName150150(name, fileSuffix), 150, 150, fileSuffix);

        provider.setLogo(targetFilePath + getNewFileName(name,fileSuffix));
    }

    private void buildFormData(Model model) {
        List<ProviderTypeWapper> providerTypeList = providerTypeService.getProviderTypeWapperList();
        List<Area> provinceList = areaService.getAreaListByPid(Area.DEFAUT_PROVINCE_PID);
        model.addAttribute("providerTypeList",providerTypeList);
        model.addAttribute("provinceList",provinceList);
    }
}
