package tr.com.ppm.desktop.model.stock;

import tr.com.ppm.desktop.model.common.AuditableEntity;
import tr.com.ppm.desktop.model.common.Quantity;
import tr.com.ppm.desktop.model.material.RawMaterial;

/**
 * @author Orhun Dalabasmaz
 */
public class RawMaterialStockItem extends AuditableEntity {
	private RawMaterial rawMaterial;
	private Quantity quantity;
}
