/**
 * 
 */
package com.hrin.admin.domain.api.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseApiRequest implements IApiRequest {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	private String url;


	@Override
	public String getUrl() {
		return url;
	}

	@Override
	abstract public String toQueryString();

	public void setUrl(String url) {
		this.url = url;
	}

}
