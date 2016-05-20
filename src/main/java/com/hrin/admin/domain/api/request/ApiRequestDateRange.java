package com.hrin.admin.domain.api.request;

import com.hrin.admin.util.DateUtil;

import java.util.Date;

public class ApiRequestDateRange extends ApiRequestRange {

	public ApiRequestDateRange(Date begin, Date end) {
		super(begin == null ? null : DateUtil.formatDate(begin), end == null ? null : DateUtil.formatDate(end));
	}
}
