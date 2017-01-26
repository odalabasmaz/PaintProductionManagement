package tr.com.ppm.desktop.model.customer;

import tr.com.ppm.desktop.model.common.AuditableEntity;

/**
 * @author Orhun Dalabasmaz
 */
public class Customer extends AuditableEntity {
	public enum TYPE {INDIVIDUAL, CORPORATE}

	private String name;
	private TYPE type;
	private String address;
	private String phone;
	private String email;
	private String contact;
	private double discount;
}
