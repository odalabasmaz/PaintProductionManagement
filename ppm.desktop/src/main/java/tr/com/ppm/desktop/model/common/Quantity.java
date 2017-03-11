package tr.com.ppm.desktop.model.common;

import javax.persistence.*;

/**
 * @author Orhun Dalabasmaz
 */

@Entity
@Table(name = "QUANTITY")
public class Quantity extends AuditableEntity {

	@Column(name = "AMOUNT")
	private double amount;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "UNIT_ID")
	private Unit unit;

	/* for hibernate */
	Quantity() {
	}

	public Quantity(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}
