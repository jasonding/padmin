/**
 * 
 */
package com.hrin.admin.util;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class JSONUtil {
	
	protected static final Logger logger = LoggerFactory.getLogger(JSONUtil.class.getName());
	
	public static final String NULL_DATE = "0000-00-00 00:00:00";

    public static final String EMPTY_ARRAY = "[]";

	private static void log(JSONObject object, String key, Exception e) {
		try {
			logger.error("从JSON对象中读取属性出错, object={}, key={}", object, key);
			logger.error(e.getMessage(), e);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), e);
		}
	}

    private static void log(JSONArray array, int index, Exception e) {
        try {
            logger.error("从JSON数组中读取属性出错, object={}, index={}", array, index);
            logger.error(e.getMessage(), e);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), e);
        }
    }

    public static boolean isJSON(JSONObject object, String key) {
        Object val = object.get(key);
        return val instanceof JSON;
    }

    public static JSON getJSON(JSONObject object, String key) {
        try {
            Object val = object.get(key);
            if (val instanceof JSON) {
                return (JSON)val;
            } else {
                log(object, key, new RuntimeException(key + "对应的值不是JSON"));
                return null;
            }
        } catch (Exception e) {
            log(object, key, e);
            return null;
        }
    }
	
	public static JSONObject getObject(JSONObject object, String key) {
		try {
			return object.getJSONObject(key);
		} catch (Exception e) {
			log(object, key, e);
			return null;
		}
	}

    public static JSONObject getObjectSafe(JSONObject object, String key) {
        try {
            if (isEmptyArray(object, key)) {
                return null;
            }
            return object.getJSONObject(key);
        } catch (Exception e) {
            log(object, key, e);
            return null;
        }
    }
	
	public static JSONArray getArray(JSONObject object, String key) {
		try {
			return object.getJSONArray(key);
		} catch (Exception e) {
			log(object, key, e);
			return null;
		}
	}
	
	public static String getString(JSONObject object, String key) {
		try {
			Object valueObject = object.get(key);
			if (isNull(valueObject)) {
				return null;
			}
			return valueObject.toString();
		} catch (Exception e) {
			log(object, key, e);
			return null;
		}
	}

    public static String getString(JSONArray array, int index) {
        try {
            Object valueObject = array.get(index);
            if (isNull(valueObject)) {
                return null;
            }
            return valueObject.toString();
        } catch (Exception e) {
            log(array, index, e);
            return null;
        }
    }
	
	public static int getInt(JSONObject object, String key) {
		try {
			return object.getInt(key);
		} catch (Exception e) {
			log(object, key, e);
			return -1;
		}
	}

	public static long getLong(JSONObject object, String key) {
		try {
			//从json对象里用jar包提供的getLong取得upload_id,由于精度问题,转换出现误差,获取不到正确结果,修改成获取字符串再转成long
			return Long.parseLong(object.getString(key));
		} catch (Exception e) {
			log(object, key, e);
			return 0;
		}
	}

    public static long getLong(JSONArray array, int index) {
        try {
            //从json对象里用jar包提供的getLong取得upload_id,由于精度问题,转换出现误差,获取不到正确结果,修改成获取字符串再转成long
            return Long.parseLong(array.getString(index));
        } catch (Exception e) {
            log(array, index, e);
            return 0;
        }
    }
	
	public static double getDouble(JSONObject object, String key) {
		try {
			return object.getDouble(key);
		} catch (Exception e) {
			log(object, key, e);
			return 0d;
		}
	}
	
	public static boolean getBoolean(JSONObject object, String key) {
		try {
			return object.getBoolean(key);
		} catch (Exception e) {
			log(object, key, e);
			return false;
		}
	}
	
	public static Date getDate(JSONObject object, String key, String pattern) {
		String val = getString(object, key);
		if (val == null || "".equals(val) || val.equals(NULL_DATE)) {
			return null;
		}
		try {
			Date date = DateUtil.parseDate(val, pattern);
			return date;
		} catch (Exception e) {
			log(object, key, e);
			return null;
		}
	}
	
	public static boolean isEmpty(JSONObject object, String key) {
		try {
			return !object.containsKey(key);
		} catch (Exception e) {
			log(object, key, e);
			return true;
		}
	}

    public static boolean isEmptyArray(JSONObject object, String key) {
        try {
            if (isEmpty(object, key)) {
                return true;
            }
            String val = object.getString(key);
            return EMPTY_ARRAY.equals(val);
        } catch (Exception e) {
            log(object, key, e);
            return true;
        }
    }

	public static boolean isNull(Object object) {
		return object == null || object instanceof JSONNull || JSONUtils.isNull(object);
	}
	
	public static boolean isNull(JSONObject object, String key) {
		try {
			return object == null || !object.containsKey(key) || isNull(object.get(key));
		} catch (Exception e) {
			log(object, key, e);
			return true;
		}
	}
}
