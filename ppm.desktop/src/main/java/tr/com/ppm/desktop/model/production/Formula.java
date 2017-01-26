package tr.com.ppm.desktop.model.production;

import tr.com.ppm.desktop.model.common.AuditableEntity;
import tr.com.ppm.desktop.model.material.Product;

import java.util.Set;

/**
 * @author Orhun Dalabasmaz
 */
public class Formula extends AuditableEntity {
	private String name;
	private Product product;
	private Set<Ingredient> ingredients;
	private String description;
}
