package com.hrin.admin.domain.api.request;

import com.hrin.admin.constant.ApiConstant;
import net.sf.json.JSONObject;

public class ApiRequestSet {
	private String key;
	private String value;

	public ApiRequestSet(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		json.put(ApiConstant.API_REQUEST_KEY_NAME, this.key);
		json.put(ApiConstant.API_REQUEST_VALUE_NAME, this.value);
		return json;
	}
}
