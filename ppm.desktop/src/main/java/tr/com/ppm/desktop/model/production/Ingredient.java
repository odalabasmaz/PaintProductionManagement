package tr.com.ppm.desktop.model.production;

import tr.com.ppm.desktop.model.common.AuditableEntity;
import tr.com.ppm.desktop.model.material.Material;
import tr.com.ppm.desktop.model.common.Quantity;

import javax.persistence.*;

/**
 * @author Orhun Dalabasmaz
 */
@Entity
@Table(name="INGREDIENT")
public class Ingredient extends AuditableEntity {

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "MATERIAL_ID")
	private Material material;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "QUANTITY_ID")
	private Quantity quantity;

	/*for hibernate*/
	public Ingredient() {
	}



	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Quantity getQuantity() {
		return quantity;
	}

	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}
}
