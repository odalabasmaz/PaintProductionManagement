package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.user.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class LoginService extends BaseService<User> {
	private static final String QUERY_BY_NAME = "from User where lower(username) like :username and password = :password";

	public void login(String username, String password) {

	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	public User findByName(String username , String password) {
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("password", password);
		return executeQuery(QUERY_BY_NAME, params).get(0);
	}
}
