package com.hrin.admin.domain.api.request;


public interface IApiRequest {
	
	/**
	 * 获取API调用的URL
	 * @return
	 */
	public String getUrl();
	
	/**
	 * 转换成请求字符串
	 * @return
	 */
	public String toQueryString();

}
