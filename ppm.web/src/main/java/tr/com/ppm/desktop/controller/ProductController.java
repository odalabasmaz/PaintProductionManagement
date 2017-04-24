package tr.com.ppm.desktop.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tr.com.ppm.desktop.model.customer.Customer;
import tr.com.ppm.desktop.model.material.Product;
import tr.com.ppm.desktop.service.ProductService;

import java.util.List;

/**
 * @author Orhun Dalabasmaz
 */
@RestController
public class ProductController {

	private final ProductService service;

	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			path = "/rest/products")
	@ResponseStatus(HttpStatus.OK)
	public List<Product> getProducts(@RequestParam(value = "name") String name) {
		service.findAll();

		return null;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/rest/products/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Customer getProduct(@PathVariable(value = "id") long id) {
		return null;
//		return service.findById(id);
	}
}
