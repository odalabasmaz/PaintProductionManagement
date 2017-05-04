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
		return StringUtils.isBlank(name) ? service.list() : service.findAllByName(name);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/rest/products/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Product getProduct(@PathVariable(value = "id") long id) {
		return service.findById(id);
//		return service.findById(id);
	}

	@RequestMapping(
			method = RequestMethod.POST,
			path = "/rest/products")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String addProduct(@RequestBody Product product) {
		service.save(product);
		return "{\"result\": \"Product saved!\"}";
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			path = "/rest/products")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String updateProduct(@RequestBody Product product) {
		service.update(product);
		return "{\"result\": \"Product updated!\"}";
	}

	@RequestMapping(
			method = RequestMethod.DELETE,
			path = "/rest/products")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String deleteProduct(@RequestParam(value = "id") long id) {
//		service.deletePaintTypeById(id);
		return "{\"result\": \"Product deleted!\"}";
	}

}
