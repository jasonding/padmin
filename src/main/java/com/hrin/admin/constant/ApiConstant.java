/**
 * 
 */
package com.hrin.admin.constant;

public interface ApiConstant {

    public static final String AREA_PROVINCE_ADD =  "/component/add_province.php";
    public static final String AREA_CITY_ADD =  "/component/add_city.php";
    public static final String AREA_SEARCH =         "/component/search_area.php";
    public static final String AREA_PROVINCE_UPDATE = "/component/update_province.php";
    public static final String AREA_CITY_UPDATE = "/component/update_city.php";

    public static final String REG_TAG_ADD = "/component/add_reg_tag.php";
    public static final String REG_TAG_SEARCH = "/component/search_reg_tag.php";
    public static final String REG_TAG_UPDATE = "/component/update_reg_tag.php";

    public static final String PROVIDER_TYPE_ADD = "/provider/add_type.php";
    public static final String PROVIDER_TYPE_SEARCH = "/provider/search_type.php";
    public static final String PROVIDER_TYPE_UPDATE = "/provider/update_type.php";

    public static final String PROVIDER_ADD = "/provider/add_provider.php";
    public static final String PROVIDER_SEARCH = "/provider/search_provider.php";
    public static final String PROVIDER_UPDATE = "";

	public static final int API_TRUE = 1;
	public static final int API_FALSE = 0;
	
	public static final String API_WILDCARD_MATCH_ONE = "?";
	public static final String API_WILDCARD_MATCH_ANY = "*";
	
	public static final String API_OP_EQUAL = "=";
	public static final String API_OP_IN = "IN";
	public static final String API_OP_LIKE = "LIKE";
	public static final String API_OP_BETWEEN = "BETWEEN";
	public static final String API_OP_LESS = "<";
	public static final String API_OP_GREATER = ">";
	public static final String API_OP_NULL = "NULL";
	public static final String API_OP_NOT_NULL = "NOT_NULL";
	
	public static final String API_OP_BETWEEN_START = "_start";
	public static final String API_OP_BETWEEN_END = "_end";

	/* 发送给API时的参数名称 */
	public static final String API_REQUEST_PARAMETER_NAME = "json";
	
	public static final String API_REQUEST_TIME_OUT_PROPERTY_NAME = "lehecai.api.timeout";
	
	public static final int API_REQUEST_TIME_OUT_DEFAULT = 10000;
	public static final int API_REQUEST_TIME_OUT_LONG = 60000;	// 默认的长超时时间
	
	public static final String API_REQUEST_MSET_NAME = "mset";
	public static final String API_REQUEST_MGET_NAME = "mget";
	public static final String API_REQUEST_SET_NAME = "set";
	public static final String API_REQUEST_WHERE_NAME = "where";
	public static final String API_REQUEST_ORDER_NAME = "order";
	public static final String API_REQUEST_RANGE_NAME = "range";
	public static final String API_REQUEST_SELECT_NAME = "select";
	public static final String API_REQUEST_PAGE_NAME = "page";
	public static final String API_REQUEST_PAGESIZE_NAME = "pagesize";
	
	public static final String API_REQUEST_KEY_NAME = "key";
	public static final String API_REQUEST_VALUE_NAME = "val";
	public static final String API_REQUEST_OPERATOR_NAME = "op";
	
	public static final String API_REQUEST_ORDER_FIELD_NAME = "field";
	public static final String API_REQUEST_ORDER_ORDER_NAME = "order";
	
	public static final String API_REQUEST_RANGE_BEGIN_NAME = "begin";
	public static final String API_REQUEST_RANGE_END_NAME = "end";
	
	public static final String API_REQUEST_ORDER_ASC = "ASC";
	public static final String API_REQUEST_ORDER_DESC = "DESC";
	
	public static final int API_REQUEST_PAGESIZE_DEFAULT = 20;
	
	public static final String API_RESPONSE_TOTAL_NAME = "total";
	public static final String API_RESPONSE_CODE_NAME = "code";
	public static final String API_RESPONSE_MESSAGE_NAME = "message";
	public static final String API_RESPONSE_DEBUGTIME_NAME = "debugtime";
	public static final String API_RESPONSE_DATA_NAME = "data";

    public static final String API_RESPONSE_BATCH_SUCCESS = "success";
    public static final String API_RESPONSE_BATCH_FAIL = "fail";
    public static final String API_RESPONSE_BATCH_INSERT_ID = "insert_id";
	
	public static final int RC_SUCCESS = 0;
	public static final int RC_FAILURE = -1;
	
	
	public static final int API_MAX_REQUEST_FAILED_DEFAULT_COUNT = 60;//api最大请求失败默认次数,超过则报警
	
	public static final String NULL = "null";
	
	public static final String NULL_DATE = "0000-00-00 00:00:00";
	
	public static final String HTTP_ERROR_CODE_502 = "502";
	public static final String HTTP_ERROR_CODE_504 = "504";
}
