package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.model.material.PaintType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class PaintSubTypeService extends BaseService<PaintSubType> {

	private static final String QUERY_STRING = "from PaintSubType where lower(name) like :name and paintType = :paintType";
	private static final String QUERY_STR = "from PaintSubType where lower(name) like :name";

	@Override
	protected Class getEntityClass() {
		return PaintSubType.class;
	}

	public List<PaintSubType> list(String paintSubType, PaintType paintType) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "%" + paintSubType.trim().toLowerCase() + "%");
		params.put("paintType", paintType);
		return executeQuery(QUERY_STRING, params);
	}
	public List<PaintSubType> list(String paintSubType) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "%" + paintSubType.trim().toLowerCase() + "%");
		return executeQuery(QUERY_STR, params);
	}
}
