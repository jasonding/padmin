package com.hrin.admin.domain.php;

import com.hrin.admin.constant.HrinConstant;
import com.hrin.admin.domain.enumeration.Status;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 14-12-6.
 */
//区域实体
public class Area {

    public static final int DEFAUT_PROVINCE_PID = HrinConstant.WEB_DEFAULT_INT_VALUE;//省的省id
    public static final String ID_KEY = "id";
    public static final String PID_KEY = "pid";
    public static final String NAME_KEY = "name";
    public static final String SORT_KEY = "sort";
    public static final String STATUS_KEY = "status";

    private Integer id;
    private int pid = DEFAUT_PROVINCE_PID;//省id，如果没有就是省，有就是市
    private String name;
    private int sort;
    private Status status;


    public static Area converterFromJsonObject(JSONObject jsonObject) {
        if(jsonObject == null || jsonObject.isEmpty()) return null;
        Area area = new Area();
        area.setId(jsonObject.getInt("id"));
        area.setPid(jsonObject.getInt("pid"));
        area.setName(jsonObject.getString("name"));
        area.setSort(jsonObject.getInt("sort"));
        area.setStatus(Status.getStatus(jsonObject.getInt("status")));
        return area;
    }

    public static List<Area> converterFromJsonArray(JSONArray jsonArray) {
        if(jsonArray == null || jsonArray.isEmpty()) return null;
        List<Area> list = new ArrayList<Area>();
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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
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