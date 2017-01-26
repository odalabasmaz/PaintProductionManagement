package tr.com.ppm.desktop.model.common;

/**
 * @author Orhun Dalabasmaz
 */
public class Container extends AuditableEntity {
	public enum TYPE {PLASTIC, TIN}

	private String name;
	private TYPE type;
	private Quantity quantity;
}
