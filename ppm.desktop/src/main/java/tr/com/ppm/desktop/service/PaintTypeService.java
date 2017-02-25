package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.material.PaintType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class PaintTypeService extends BaseService<PaintType> {
	private static final String QUERY_STRING = "from PaintType where lower(name) like :name";

	@Override
	protected Class getEntityClass() {
		return PaintType.class;
	}

	public List<PaintType> listByPaintType(String paintType) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "%" + paintType.trim().toLowerCase() + "%");
		return executeQuery(QUERY_STRING, params);
	}
}
