package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.dao.QueryHelper;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.model.material.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class ProductService extends BaseService<Product> {
	private static final String QUERY_BY_NAME = "from Product where lower(name) like :name";
	private static final String QUERY_BY_ID = "from Product where id = :id";

	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}

	public Product findById(long id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return executeQuery(QUERY_BY_ID, params).get(0);
	}

	public List<Product> findAllByName(String name) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "%" + name.trim().toLowerCase() + "%");
		return executeQuery(QUERY_BY_NAME, params);
	}
}
