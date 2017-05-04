package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.material.PaintSubType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class PaintSubTypeService extends BaseService<PaintSubType> {

	private static final String QUERY_BY_NAME = "from PaintSubType where lower(name) like :name";
	private static final String QUERY_BY_ID = "from PaintSubType where id = :id";

	@Override
	protected Class getEntityClass() {
		return PaintSubType.class;
	}

	public PaintSubType findById(long id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return executeQuery(QUERY_BY_ID, params).get(0);
	}

	public List<PaintSubType> findAllByName(String name) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "%" + name.trim().toLowerCase() + "%");
		return executeQuery(QUERY_BY_NAME, params);
	}
}
