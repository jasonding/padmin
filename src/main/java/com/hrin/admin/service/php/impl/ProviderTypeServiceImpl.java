package com.hrin.admin.service.php.impl;

import com.hrin.admin.constant.ApiConstant;
import com.hrin.admin.constant.HrinConstant;
import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.api.request.ApiRequest;
import com.hrin.admin.domain.api.response.ApiResponse;
import com.hrin.admin.domain.enumeration.Status;
import com.hrin.admin.domain.php.ProviderType;
import com.hrin.admin.domain.php.wapper.ProviderTypeWapper;
import com.hrin.admin.service.api.ApiRequestService;
import com.hrin.admin.service.php.ProviderTypeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 14-12-25.
 */
@Service("providerTypeService")
public class ProviderTypeServiceImpl implements ProviderTypeService{

    @Resource
    private ApiRequestService apiRequestService;

    @Override
    public List<ProviderTypeWapper> getProviderTypeWapperList() {
        List<ProviderType> parentProviderTypeList = getProviderTypeByPid(ProviderType.DEFAUT_PARENT_ID);
        if(parentProviderTypeList == null) return null;
        List<ProviderTypeWapper> list = new ArrayList<ProviderTypeWapper>();
        for (ProviderType providerType : parentProviderTypeList) {
            ProviderTypeWapper providerTypeWapper = new ProviderTypeWapper();
            providerTypeWapper.setParentProviderType(providerType);
            providerTypeWapper.setChildProviderTypeList(getProviderTypeByPid(providerType.getId()));
            list.add(providerTypeWapper);
        }
        return list;
    }

    @Override
    public List<ProviderType> getProviderTypeByPid(int pid) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.PROVIDER_TYPE_SEARCH);
        apiRequest.setParameter(ProviderType.PID_KEY,String.valueOf(pid));
        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() == ApiConstant.RC_SUCCESS) {
            JSONArray dataJsonArray = response.getData();
            List<ProviderType> list = ProviderType.converterFromJsonArray(dataJsonArray);
            return  list;
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public Object getList(List<QueryBean> queryBeanList, int page, int pageSize) {

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.PROVIDER_TYPE_SEARCH);
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
                datajson.put(ProviderType.STATUS_KEY,Status.getStatus(datajson.getInt(ProviderType.STATUS_KEY)));
            }

            jsonObject.put(HrinConstant.ROWS_KEY, dataJsonArray);
            return  jsonObject;
        }else {
            throw new RuntimeException(response.getMessage());
        }

    }

    @Override
    public ProviderType findById(Integer id) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.PROVIDER_TYPE_SEARCH);
        apiRequest.setParameter(ProviderType.ID_KEY,String.valueOf(id));
        ApiResponse response = apiRequestService.request(apiRequest);

        if(response.getCode() == ApiConstant.RC_SUCCESS) {
            JSONObject jsonObject = response.getData().getJSONObject(0);
            ProviderType providerType= ProviderType.converterFromJsonObject(jsonObject);
            return  providerType;
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public void save(ProviderType providerType) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.PROVIDER_TYPE_ADD);
        addAttribute(providerType, apiRequest);
        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() != ApiConstant.RC_SUCCESS) {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public void update(ProviderType providerType) {
        ApiRequest apiRequest = new ApiRequest();

        apiRequest.setUrl(ApiConstant.PROVIDER_TYPE_UPDATE);
        apiRequest.setParameter(ProviderType.ID_KEY,String.valueOf(providerType.getId()));

        addAttribute(providerType, apiRequest);
        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() != ApiConstant.RC_SUCCESS) {
            throw new RuntimeException(response.getMessage());
        }
    }

    private void addAttribute(ProviderType providerType, ApiRequest apiRequest) {
        apiRequest.setParameterForUpdate(ProviderType.NAME_KEY, providerType.getName());
        apiRequest.setParameterForUpdate(ProviderType.SORT_KEY, String.valueOf(providerType.getSort()));
        apiRequest.setParameterForUpdate(ProviderType.PID_KEY, String.valueOf(providerType.getPid()));
        apiRequest.setParameterForUpdate(ProviderType.STATUS_KEY, String.valueOf(providerType.getStatus().getStatus()));
    }
}
