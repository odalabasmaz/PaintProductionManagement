package tr.com.ppm.desktop.model.user;

import tr.com.ppm.desktop.model.common.AuditableEntity;

import java.util.Date;
import java.util.Set;

/**
 * @author Orhun Dalabasmaz
 */
public class User extends AuditableEntity {
	private String name;
	private String surname;
	private String username;
	private String password;
	private Date lastPasswordChangedDate;
	private Set<Role> roles;
}
