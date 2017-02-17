package tr.com.ppm.desktop.model.common;

import javax.persistence.*;

/**
 * @author Orhun Dalabasmaz
 */

@Entity
@Table(name = "QUANTITY")
public class Quantity extends AuditableEntity {

	@Column
	private double amount;

	public Quantity() {
	}

	public Quantity(double amount) {
		this.amount = amount;
	}

	/*@Column
	private Unit unit;*/

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

/*	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}*/
}
