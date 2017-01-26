package tr.com.ppm.desktop.model.stock;

import tr.com.ppm.desktop.model.common.AuditableEntity;
import tr.com.ppm.desktop.model.common.Container;
import tr.com.ppm.desktop.model.common.Quantity;
import tr.com.ppm.desktop.model.material.Product;
import tr.com.ppm.desktop.model.order.ProductOrder;

/**
 * @author Orhun Dalabasmaz
 */
public class ProductStockItem extends AuditableEntity {
	private ProductOrder order;
	private Product product;
	private Quantity quantity;
	private Container container;
}
