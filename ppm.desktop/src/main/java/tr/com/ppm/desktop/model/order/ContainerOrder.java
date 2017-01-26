package tr.com.ppm.desktop.model.order;

import tr.com.ppm.desktop.model.common.AuditableEntity;
import tr.com.ppm.desktop.model.common.Container;

/**
 * @author Orhun Dalabasmaz
 */
public class ContainerOrder extends AuditableEntity {
	private Container container;
	private int quantity;
}
