package com.hrin.admin.service.php.impl;

import com.hrin.admin.constant.ApiConstant;
import com.hrin.admin.domain.QueryBean;
import com.hrin.admin.domain.api.request.ApiRequest;
import com.hrin.admin.domain.api.response.ApiResponse;
import com.hrin.admin.domain.enumeration.Status;
import com.hrin.admin.domain.php.RegTag;
import com.hrin.admin.service.api.ApiRequestService;
import com.hrin.admin.service.php.RegTagService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jason on 14-12-25.
 */
@Service("regTagService")
public class RegTagServiceImpl implements RegTagService{

    @Resource
    private ApiRequestService apiRequestService;

    @Override
    public Object getList(List<QueryBean> queryBeanList, int page, int pageSize) {

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.REG_TAG_SEARCH);
        apiRequest.setPage(page);
        apiRequest.setPagesize(pageSize);
        apiRequest.addOrder("id", ApiConstant.API_REQUEST_ORDER_DESC);

        for (QueryBean queryBean : queryBeanList) {
            apiRequest.setParameter(queryBean.getPropertyName(),queryBean.getPropertyValue().toString(),queryBean.getMatchType().getOp());
        }

        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() == ApiConstant.RC_SUCCESS) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("total",response.getTotal());
            JSONArray dataJsonArray = response.getData();
            for (int i = 0; i < dataJsonArray.size(); i++) {
                JSONObject datajson = dataJsonArray.getJSONObject(i);
                datajson.put("status",Status.getStatus(datajson.getInt("status")));
            }

            jsonObject.put("rows", dataJsonArray);
            return  jsonObject;
        }else {
            throw new RuntimeException("查找区域列表失败");
        }

    }

    @Override
    public RegTag findById(Integer id) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.REG_TAG_SEARCH);
        apiRequest.setParameter("id",id+"");
        ApiResponse response = apiRequestService.request(apiRequest);

        if(response.getCode() == ApiConstant.RC_SUCCESS) {
            JSONObject jsonObject = response.getData().getJSONObject(0);
            RegTag regTag= new RegTag();
            regTag.setId(jsonObject.getInt("id"));
            regTag.setName(jsonObject.getString("name"));
            regTag.setSort(jsonObject.getInt("sort"));
            regTag.setStatus(Status.getStatus(jsonObject.getInt("status")));
            return  regTag;
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public void save(RegTag regTag) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(ApiConstant.REG_TAG_ADD);
        addAttribute(regTag, apiRequest);
        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() != ApiConstant.RC_SUCCESS) {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public void update(RegTag regTag) {
        ApiRequest apiRequest = new ApiRequest();

        apiRequest.setUrl(ApiConstant.REG_TAG_UPDATE);
        apiRequest.setParameter("id",regTag.getId()+"");

        addAttribute(regTag, apiRequest);
        ApiResponse response = apiRequestService.request(apiRequest);
        if(response.getCode() != ApiConstant.RC_SUCCESS) {
            throw new RuntimeException(response.getMessage());
        }
    }

    private void addAttribute(RegTag regTag, ApiRequest apiRequest) {
        apiRequest.setParameterForUpdate("name", regTag.getName());
        apiRequest.setParameterForUpdate("sort",regTag.getSort()+"");
        apiRequest.setParameterForUpdate("status",regTag.getStatus().getStatus()+"");
    }
}
