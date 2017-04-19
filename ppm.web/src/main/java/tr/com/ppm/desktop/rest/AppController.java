package tr.com.ppm.desktop.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Orhun Dalabasmaz
 */
@RestController
public class AppController {

	/*@RequestMapping("/")
	public String welcome() {
		return "Welcome homepage!";
	}

	@RequestMapping("*//*")
	public String error404() {
		return "404 - Page Not Found!";
	}*/

	@RequestMapping("/rest/*")
	public String unimplementedRestService() {
		return "501 - REST service not implemented!";
	}
}
