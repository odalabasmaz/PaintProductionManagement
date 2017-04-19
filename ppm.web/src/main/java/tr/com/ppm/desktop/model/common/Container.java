package tr.com.ppm.desktop.model.common;

/**
 * @author Orhun Dalabasmaz
 */

public enum Container {
	KG1(1D, Type.KG, "1 KG"), KG5(5D, Type.KG, "5 KG"), LT5(5D, Type.LT, "5 LT");

	private enum Type {
		KG, LT
	}

	private Double amount;
	private Type type;
	private String definition;

	Container(Double amount, Type type, String definition) {
		this.amount = amount;
		this.type = type;
		this.definition = definition;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
