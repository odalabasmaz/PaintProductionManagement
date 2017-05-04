package tr.com.ppm.desktop.model.user;

import tr.com.ppm.desktop.model.common.AuditableEntity;

import javax.persistence.Column;
import java.util.Set;

/**
 * @author Orhun Dalabasmaz
 */
public class Role extends AuditableEntity {

	@Column(name = "NAME")
	private String name;

	private Set<Grant> grants;
}
