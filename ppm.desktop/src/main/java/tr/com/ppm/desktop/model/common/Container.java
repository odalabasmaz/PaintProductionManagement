package tr.com.ppm.desktop.model.common;

import javax.persistence.*;

/**
 * @author Orhun Dalabasmaz
 */
@Entity
@Table(name = "CONTAINER")
public class Container extends AuditableEntity {
	public enum TYPE {PLASTIC, TIN}
	public enum SIZE_TYPE {KG, LT}

	@Column
	private String name;

	@Column
	@Enumerated(EnumType.STRING)
	private TYPE type;

	@Column
	@Enumerated(EnumType.STRING)
	private SIZE_TYPE size_type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public SIZE_TYPE getSize_type() {
		return size_type;
	}

	public void setSize_type(SIZE_TYPE size_type) {
		this.size_type = size_type;
	}

	/*	@Column
	private Quantity quantity;*/
}
