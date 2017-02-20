package tr.com.ppm.desktop.model.common;

import javax.persistence.*;

/**
 * @author Orhun Dalabasmaz
 */
@Entity
@Table(name = "CONTAINER")
public class Container extends AuditableEntity {
	public enum TYPE {PLASTIC, TIN}

	@Column
	private String name;

	@Column
	@Enumerated(EnumType.STRING)
	private TYPE type;

/*	@Column
	private Quantity quantity;*/
}
