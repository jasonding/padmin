package com.hrin.admin.service.php.impl;

import com.hrin.admin.constant.ApiConstant;
import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.api.request.ApiRequest;
import com.hrin.admin.domain.api.response.ApiResponse;
import com.hrin.admin.domain.enumeration.Status;
import com.hrin.admin.domain.php.Provider;
import com.hrin.admin.domain.php.ProviderType;
import com.hrin.admin.service.api.ApiRequestService;
import com.hrin.admin.service.php.AreaService;
import com.hrin.admin.service.php.ProviderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jason on 14-12-25.
 */
@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Resource
    private ApiRequestService apiRequestService;
    @Resource
    private AreaService areaService;

    @Override
    public Object getList(List<QueryBean> queryBeanList, int page, int pageSize) {

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.PROVIDER_SEARCH);
        apiRequest.setPage(page);
        apiRequest.setPagesize(pageSize);
        for (QueryBean queryBean : queryBeanList) {
            apiRequest.setParameter(queryBean.getPropertyName(),queryBean.getPropertyValue().toString(),queryBean.getMatchType().getOp());
        }

        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() == ApiConstant.RC_SUCCESS) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("total",response.getTotal());
            JSONArray dataJsonArray = response.getData();
            jsonObject.put("rows", dataJsonArray);
            return  jsonObject;
        }else {
            throw new RuntimeException(response.getMessage());
        }

    }

    @Override
    public Provider findById(Integer id) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.PROVIDER_SEARCH);
        apiRequest.setParameter(Provider.PROVIDER_ID_KEY,String.valueOf(id));
        ApiResponse response = apiRequestService.request(apiRequest);

        if(response.getCode() == ApiConstant.RC_SUCCESS) {
            JSONObject jsonObject = response.getData().getJSONObject(0);
            Provider provider= Provider.converterFromJsonObject(jsonObject);
            return  provider;
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public void save(Provider provider) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.PROVIDER_ADD);
        addAttribute(provider,apiRequest);
        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() != ApiConstant.RC_SUCCESS) {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public void update(Provider provider) {
        ApiRequest apiRequest = new ApiRequest();

        apiRequest.setUrl(ApiConstant.PROVIDER_UPDATE);
        apiRequest.setParameter(Provider.PROVIDER_ID_KEY,String.valueOf(provider.getProviderId()));

        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() != ApiConstant.RC_SUCCESS) {
            throw new RuntimeException(response.getMessage());
        }
    }


    private void addAttribute(Provider provider, ApiRequest apiRequest) {
        apiRequest.setParameterForUpdate(Provider.DOMAIN_KEY, provider.getDomain());
        apiRequest.setParameterForUpdate(Provider.LOGO_KEY, provider.getLogo());
        apiRequest.setParameterForUpdate(Provider.CITY_ID_KEY, String.valueOf(provider.getCityId()));
        apiRequest.setParameterForUpdate(Provider.FULL_NAME_KEY, provider.getFullName());
        apiRequest.setParameterForUpdate(Provider.PROVIDER_TYPE_KEY, provider.getProviderType());
        apiRequest.setParameterForUpdate(Provider.PROVINCE_ID_KEY, String.valueOf(provider.getProvinceId()));
        apiRequest.setParameterForUpdate(Provider.SIMPLE_NAME_KEY, provider.getSimpleName());
    }

}
