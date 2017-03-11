package tr.com.ppm.desktop.dao;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author Orhun Dalabasmaz
 */
public class QueryHelper {
	private String queryString;
	private Map<String, Object> params;

	private QueryHelper(String table, Map<String, Object> params) {
		this.queryString = "from " + table + " where 1=1";
		this.params = params;
	}

	public static QueryHelper getInstance(String table, Map<String, Object> params) {
		return new QueryHelper(table, params);
	}

	public QueryHelper equals(String key, String value) {
		if (StringUtils.isNotBlank(value)) {
			queryString += " and " + key + " = :" + key;
			params.put(key, value);
		}
		return this;
	}

	public QueryHelper equalsIgnoreCase(String key, String value) {
		if (StringUtils.isNotBlank(value)) {
			queryString += " and lower(" + key + ") = :" + key;
			params.put(key, value.toLowerCase());
		}
		return this;
	}

	public QueryHelper likeIgnoreCase(String key, String value) {
		if (StringUtils.isNotBlank(value)) {
			queryString += " and " + key + " like :" + key;
			params.put(key, "%" + value + "%");
		}
		return this;
	}

	public QueryHelper like(String key, String value) {
		if (StringUtils.isNotBlank(value)) {
			queryString += " and lower(" + key + ") like :" + key;
			params.put(key, "%" + value.toLowerCase() + "%");
		}
		return this;
	}

	public String build() {
		return queryString;
	}
}
