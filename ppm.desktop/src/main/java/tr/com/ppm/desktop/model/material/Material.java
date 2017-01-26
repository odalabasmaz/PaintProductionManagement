package tr.com.ppm.desktop.model.material;

import tr.com.ppm.desktop.model.common.AuditableEntity;

/**
 * @author Orhun Dalabasmaz
 */
public abstract class Material extends AuditableEntity {
	private String code;
	private String name;
	private String description;
}
