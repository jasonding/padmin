package com.hrin.admin.domain.php;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 平台机构实体
 * Created by jason on 15-1-14.
 */
public class Provider {

    public static final String PROVIDER_ID_KEY = "provider_id";
    public static final String LOGO_KEY = "logo";
    public static final String SIMPLE_NAME_KEY = "simple_name";
    public static final String FULL_NAME_KEY = "full_name";
    public static final String DOMAIN_KEY = "domain";
    public static final String PROVIDER_TYPE_KEY = "provider_type";
    public static final String PROVINCE_ID_KEY = "province_id";
    public static final String CITY_ID_KEY = "city_id";

    private Integer providerId;
    private String logo;//图片地址
    @NotBlank(message = "不能为空")
    private String simpleName;//机构对外简称
    @NotBlank(message = "不能为空")
    private String fullName;//机构全称
    @URL(message = "url不合法,例如:http://www.domain.com")
    private String domain;//官网
    private String  providerType;//业务类型
    private Integer provinceId;//所在地省id
    private Integer cityId;//所在地城市id

    private Set<Integer> providerTypeIdSet = new HashSet<Integer>();


    public static Provider converterFromJsonObject(JSONObject jsonObject) {
        if(jsonObject == null || jsonObject.isEmpty()) return null;
        Provider provider = new Provider();
        provider.setProviderId(jsonObject.getInt(PROVIDER_ID_KEY));
        provider.setLogo(jsonObject.getString(LOGO_KEY));
        provider.setCityId(jsonObject.getInt(CITY_ID_KEY));
        provider.setDomain(jsonObject.getString(DOMAIN_KEY));
        provider.setFullName(jsonObject.getString(FULL_NAME_KEY));
        provider.setSimpleName(jsonObject.getString(SIMPLE_NAME_KEY));
        String providerType = jsonObject.getString(PROVIDER_TYPE_KEY);
        provider.setProviderType(providerType);
        Set<Integer> set = new HashSet<Integer>();
        String[] split = StringUtils.split(providerType, ",");
        for (String str : split) {
            set.add(Integer.parseInt(str));
        }
        provider.setProviderTypeIdSet(set);
        return provider;
    }

    public static List<Provider> converterFromJsonArray(JSONArray jsonArray) {
        if(jsonArray == null || jsonArray.isEmpty()) return null;
        List<Provider> list = new ArrayList<Provider>();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            list.add(converterFromJsonObject(jsonObject));

        }
        return list;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getProviderType() {
        if(this.providerType == null || this.providerType.isEmpty()) {
            if(!this.providerTypeIdSet.isEmpty()) {
                StringBuilder builder = new StringBuilder();
                for (Integer integer : providerTypeIdSet) {
                    builder.append(integer).append(",");
                }
                providerType = builder.deleteCharAt(builder.lastIndexOf(",")).toString();
            }
        }
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Set<Integer> getProviderTypeIdSet() {
        return providerTypeIdSet;
    }

    public void setProviderTypeIdSet(Set<Integer> providerTypeIdSet) {
        this.providerTypeIdSet = providerTypeIdSet;
    }
}
