package com.hrin.admin.service.php.impl;

import com.hrin.admin.constant.ApiConstant;
import com.hrin.admin.constant.HrinConstant;
import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.api.request.ApiRequest;
import com.hrin.admin.domain.api.response.ApiResponse;
import com.hrin.admin.domain.enumeration.Status;
import com.hrin.admin.domain.php.Area;
import com.hrin.admin.domain.php.wapper.AreaWapper;
import com.hrin.admin.service.api.ApiRequestService;
import com.hrin.admin.service.php.AreaService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jason on 14-12-25.
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService{

    @Resource
    private ApiRequestService apiRequestService;

    @Override
    public List<Area> getAreaListByPid(int pid) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.AREA_SEARCH);
        apiRequest.setParameter(Area.PID_KEY,String.valueOf(pid));

        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() == ApiConstant.RC_SUCCESS) {
            JSONArray dataJsonArray = response.getData();
            List<Area> areaList = Area.converterFromJsonArray(dataJsonArray);
            return  areaList;
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public List<AreaWapper> getAreaWapperList() {
        return null;
    }

    @Override
    public Object getAreaList(List<QueryBean> queryBeanList, int page, int pageSize) {

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.AREA_SEARCH);
        apiRequest.setPage(page);
        apiRequest.setPagesize(pageSize);

        for (QueryBean queryBean : queryBeanList) {
            apiRequest.setParameter(queryBean.getPropertyName(),queryBean.getPropertyValue().toString(),queryBean.getMatchType().getOp());
        }

        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() == ApiConstant.RC_SUCCESS) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(HrinConstant.TOTAL_KEY,response.getTotal());
            JSONArray dataJsonArray = response.getData();
            for (int i = 0; i < dataJsonArray.size(); i++) {
                JSONObject datajson = dataJsonArray.getJSONObject(i);
                datajson.put(Area.STATUS_KEY,Status.getStatus(datajson.getInt(Area.STATUS_KEY)));
            }

            jsonObject.put(HrinConstant.ROWS_KEY, dataJsonArray);
            return  jsonObject;
        }else {
            throw new RuntimeException(response.getMessage());
        }

    }

    @Override
    public Area findById(Integer id) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.AREA_SEARCH);
        apiRequest.setParameter(Area.ID_KEY,String.valueOf(id));
        ApiResponse response = apiRequestService.request(apiRequest);

        if(response.getCode() == ApiConstant.RC_SUCCESS) {
            JSONObject jsonObject = response.getData().getJSONObject(0);
            Area area= Area.converterFromJsonObject(jsonObject);
            return  area;
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public void saveProvince(Area area) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.AREA_PROVINCE_ADD);
        addAttribute(area, apiRequest);
        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() != ApiConstant.RC_SUCCESS) {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public void upateProvince(Area area) {
        ApiRequest apiRequest = new ApiRequest();

        apiRequest.setUrl(ApiConstant.AREA_PROVINCE_UPDATE);
        apiRequest.setParameter(Area.ID_KEY,String.valueOf(area.getId()));
        addAttribute(area, apiRequest);
        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() != ApiConstant.RC_SUCCESS) {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public void saveCity(Area area) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.AREA_CITY_ADD);
        addAttribute(area, apiRequest);
        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() != ApiConstant.RC_SUCCESS) {
            throw new RuntimeException(response.getMessage());
        }
    }


    @Override
    public void updateCity(Area area) {
        ApiRequest apiRequest = new ApiRequest();

        apiRequest.setUrl(ApiConstant.AREA_CITY_UPDATE);
        apiRequest.setParameter(Area.ID_KEY,String.valueOf(area.getId()));
        addAttribute(area, apiRequest);
        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() != ApiConstant.RC_SUCCESS) {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public void delete(Area area) {

    }


    private void addAttribute(Area area, ApiRequest apiRequest) {
        apiRequest.setParameterForUpdate(Area.PID_KEY, String.valueOf(area.getPid()));
        apiRequest.setParameterForUpdate(Area.NAME_KEY,area.getName());
        apiRequest.setParameterForUpdate(Area.SORT_KEY,String.valueOf(area.getSort()));
        apiRequest.setParameterForUpdate(Area.STATUS_KEY,String.valueOf(area.getStatus().getStatus()));
    }
}
