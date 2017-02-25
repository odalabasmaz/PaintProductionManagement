package tr.com.ppm.desktop.model.material;

import tr.com.ppm.desktop.model.common.AuditableEntity;

import javax.persistence.*;

/**
 * @author Orhun Dalabasmaz
 */
@Entity(name = "PAINT_SUB_TYPE")
public class PaintSubType extends AuditableEntity {
	@Column(name = "NAME")
	private String name;

	@OneToOne(targetEntity = PaintType.class, cascade = {CascadeType.ALL})
	@JoinColumn(name = "PAINT_TYPE_ID", referencedColumnName = "ID")
	private PaintType paintType;

	/* for hibernate */
	PaintSubType() {
	}

	public PaintSubType(String name, PaintType paintType) {
		this.name = name;
		this.paintType = paintType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PaintType getPaintType() {
		return paintType;
	}

	public void setPaintType(PaintType paintType) {
		this.paintType = paintType;
	}
}