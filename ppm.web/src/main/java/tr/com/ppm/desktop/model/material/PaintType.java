package tr.com.ppm.desktop.model.material;

import tr.com.ppm.desktop.model.common.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Orhun Dalabasmaz
 */
@Entity
@Table(name = "PAINT_TYPE")
public class PaintType extends AuditableEntity {
	@Column(name = "NAME")
	private String name;

	/* for hibernate */
	PaintType() {
	}

	public PaintType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}
}
