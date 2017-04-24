package tr.com.ppm.desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.dao.QueryHelper;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.model.material.Product;
import tr.com.ppm.desktop.repository.ProductRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class ProductService {
	String intermediateProductQuery = "from Product where intermediateProduct = :intermediateProduct";

	private final ProductRepository repository;

	@Autowired
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public List<Product> list(String name, String code, String colorName, String colorCode,
	                          PaintType paintType, PaintSubType paintSubType) {
		Map<String, Object> params = new HashMap<>();
		String queryString = QueryHelper.getInstance("Product", params)
				.equals("name", name)
				.equals("code", code)
				.likeIgnoreCase("colorName", colorName)
				.equals("colorCode", colorCode)
				.build();
		return null;
//		return executeQuery(queryString, params);
	}

	public Collection<Product> listIntermediateProducts() {
		Map<String, Object> params = new HashMap<>();
		params.put("intermediateProduct", Boolean.TRUE);
//		return executeQuery(intermediateProductQuery, params);
		return null;
	}

	public List<Product> findByName(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}

	public List<Product> findAll() {
		return repository.findAll();
	}
}
