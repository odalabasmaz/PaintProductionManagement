package tr.com.ppm.desktop.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.common.State;
import tr.com.ppm.desktop.model.material.RawMaterial;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yoztas on 08.03.2017.
 */
@Service
public class RawMaterialService extends BaseService<RawMaterial> {
	private String query = "from RawMaterial where lower(name) like :name";

	@Override
	protected Class getEntityClass() {
		return RawMaterial.class;
	}

	public List<RawMaterial> queryWithParameter(String code, String name, State state) {
		//TODO:yoztas QueryHelper ile yap

		Map<String, Object> params = new HashMap<>();
		params.put("name", "%" + name.trim() + "%");
		StringBuilder builder = new StringBuilder(query);

		if (StringUtils.isNotBlank(code)) {
			builder.append(" and lower(code) like :code");
			params.put("code", "%" + code.trim() + "%");
		}
		if (state != null) {
			builder.append(" and state = :state");
			params.put("state", state);
		}
		return executeQuery(builder.toString(), params);
	}
}
