package com.hrin.admin.domain.php;

import com.hrin.admin.constant.HrinConstant;
import com.hrin.admin.domain.enumeration.Status;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构类型
 * Created by jason on 15-1-14.
 */
public class ProviderType {

    public static final int DEFAUT_PARENT_ID = HrinConstant.WEB_DEFAULT_INT_VALUE;//缺省的pid
    public static final String ID_KEY = "id";
    public static final String PID_KEY = "pid";
    public static final String NAME_KEY = "name";
    public static final String SORT_KEY = "sort";
    public static final String STATUS_KEY = "status";

    private Integer id;
    private Integer pid;
    private String name;
    private int sort;
    private Status status;


    public static ProviderType converterFromJsonObject(JSONObject jsonObject) {
        if(jsonObject == null || jsonObject.isEmpty()) return null;
        ProviderType providerType = new ProviderType();
        providerType.setId(jsonObject.getInt("id"));
        providerType.setPid(jsonObject.getInt("pid"));
        providerType.setName(jsonObject.getString("name"));
        providerType.setSort(jsonObject.getInt("sort"));
        providerType.setStatus(Status.getStatus(jsonObject.getInt("status")));
        return providerType;
    }

    public static List<ProviderType> converterFromJsonArray(JSONArray jsonArray) {
        if(jsonArray == null || jsonArray.isEmpty()) return null;
        List<ProviderType> list = new ArrayList<ProviderType>();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            list.add(converterFromJsonObject(jsonObject));

        }
        return list;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
