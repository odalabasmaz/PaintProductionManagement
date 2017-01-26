package tr.com.ppm.desktop.model.user;

import tr.com.ppm.desktop.model.common.AuditableEntity;

import java.util.Set;

/**
 * @author Orhun Dalabasmaz
 */
public class Role extends AuditableEntity {
	private String name;
	private Set<Grant> grants;
}
