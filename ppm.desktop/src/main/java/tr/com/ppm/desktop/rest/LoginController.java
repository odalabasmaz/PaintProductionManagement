package tr.com.ppm.desktop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tr.com.ppm.desktop.service.LoginService;

/**
 * @author Orhun Dalabasmaz
 */
@RestController
public class LoginController {

	private final LoginService service;

	@Autowired
	public LoginController(LoginService service) {
		this.service = service;
	}

	@RequestMapping(
			method = RequestMethod.POST,
			path = "/rest/login")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
		service.login(username, password);
		return "{\"result\": \"Login!\"}";
	}
}
