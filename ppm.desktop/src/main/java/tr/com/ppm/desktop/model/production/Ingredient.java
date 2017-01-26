package tr.com.ppm.desktop.model.production;

import tr.com.ppm.desktop.model.common.AuditableEntity;
import tr.com.ppm.desktop.model.material.Material;
import tr.com.ppm.desktop.model.common.Quantity;

/**
 * @author Orhun Dalabasmaz
 */
public class Ingredient extends AuditableEntity {
	private Material material;
	private Quantity quantity;
	private String description;
}
