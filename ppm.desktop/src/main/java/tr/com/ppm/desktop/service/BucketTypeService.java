package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.material.BucketType;
import tr.com.ppm.desktop.model.material.PaintType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Utku Adak
 */
@Service
public class BucketTypeService extends BaseService<BucketType> {
	private static final String QUERY_STRING = "from BucketType where lower(name) like :bucket_size";

	@Override
	protected Class getEntityClass() {
		return BucketType.class;
	}

	public List<BucketType> listByPaintType(String bucketType) {
		Map<String, Object> params = new HashMap<>();
		params.put("Size", "%" + bucketType.trim().toLowerCase() + "%");
		return executeQuery(QUERY_STRING, params);
	}
}
