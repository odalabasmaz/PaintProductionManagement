package tr.com.ppm.desktop.model.material;

import tr.com.ppm.desktop.model.common.AuditableEntity;
import tr.com.ppm.desktop.model.common.State;

import javax.persistence.*;

/**
 * @author Orhun Dalabasmaz
 */
@Entity
@Table(name = "MATERIAL")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Material extends AuditableEntity {

	@Column(name = "CODE")
	private String code;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	public Material(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	//for Hibernate
	public Material() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
