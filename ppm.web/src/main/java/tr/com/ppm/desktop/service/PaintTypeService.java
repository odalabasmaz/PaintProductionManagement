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
	private static final String QUERY_BY_NAME = "from PaintType where lower(name) like :name";
	private static final String QUERY_BY_ID = "from PaintType where id = :id";

	@Override
	protected Class<PaintType> getEntityClass() {
		return PaintType.class;
	}

	public PaintType findById(long id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return executeQuery(QUERY_BY_ID, params).get(0);
	}

	public List<PaintType> findAllByName(String name) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "%" + name.trim().toLowerCase() + "%");
		return executeQuery(QUERY_BY_NAME, params);
	}

}
