package com.hrin.admin.web.springmvc.base;

import com.hrin.admin.web.WebControlbHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jason on 15-1-9.
 */
public abstract class BaseSpringMvcController extends WebControlbHelper {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    public static final String VIEW_REDIRECT = "redirect:";

    /*区域view*/
    public static final String VIEW_REDIRECT_AREA_LIST_PROVINCE = VIEW_REDIRECT + "/manage/area/list/province";
    public static final String VIEW_REDIRECT_AREA_LIST_CITY = VIEW_REDIRECT + "/manage/area/list/city/";
    public static final String VIEW_AREA_SAVE = "area/save";
    public static final String VIEW_AREA_UPDATE = "area/update";
    public static final String VIEW_AREA_LIST_PROVINCE = "area/listProvince";
    public static final String VIEW_AREA_LIST_CITY = "area/listCity";
    /*区域view*/

    /*provider Type view*/
    public static final String VIEW_REDIRECT_PROVIDER_TYPE_LIST_PARENT = VIEW_REDIRECT + "/manage/provider/type/list/parent";
    public static final String VIEW_REDIRECT_PROVIDER_TYPE_LIST_CHILD = VIEW_REDIRECT + "/manage/provider/type/list/child/%s";
    public static final String VIEW_PROVIDER_TYPE_SAVE = "provider/type/save";
    public static final String VIEW_PROVIDER_TYPE_UPDATE = "provider/type/update";
    public static final String VIEW_PROVIDER_TYPE_LIST_PARENT = "provider/type/listParent";
    public static final String VIEW_PROVIDER_TYPE_LIST_CHILD = "provider/type/listChild";
    /*provider Type view*/

    /*provider Type view*/
    public static final String VIEW_REDIRECT_PROVIDER_LIST = VIEW_REDIRECT + "/manage/provider/list";
    public static final String VIEW_REDIRECT_PROVIDER_SAVE = VIEW_REDIRECT + "/manage/provider/save";
    public static final String VIEW_PROVIDER_SAVE = "provider/save";
    public static final String VIEW_PROVIDER_UPDATE = "provider/update";
    public static final String VIEW_PROVIDER_LIST = "provider/list";
    /*provider Type view*/

    /* reg_tag view*/
    public static final String VIEW_REDIRECT_REG_TAG_LIST = VIEW_REDIRECT + "/manage/reg/tag/list";
    public static final String VIEW_REG_TAG_SAVE = "regtag/save";
    public static final String VIEW_REG_TAG_UPDATE = "regtag/update";
    public static final String VIEW_REG_TAG_LIST = "regtag/list";
    /*reg_tag view*/

    @ExceptionHandler()
    protected String handleException(Exception e,HttpServletRequest httpServletRequest) {
        httpServletRequest.setAttribute("exception", e.getMessage());
        logger.info(e.getMessage(),e);
        return "exception";
    }

}
