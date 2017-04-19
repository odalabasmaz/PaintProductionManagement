package tr.com.ppm.desktop.model.user;

import tr.com.ppm.desktop.model.common.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Orhun Dalabasmaz
 */
@Entity
public class Grant extends AuditableEntity {
	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
