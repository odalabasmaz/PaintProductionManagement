package tr.com.ppm.desktop.model.order;

import tr.com.ppm.desktop.model.common.AuditableEntity;
import tr.com.ppm.desktop.model.material.Product;

import java.util.List;

/**
 * @author Orhun Dalabasmaz
 */
public class ProductOrder extends AuditableEntity {
	private Product product;
	private List<ContainerOrder> containerOrders;
}
