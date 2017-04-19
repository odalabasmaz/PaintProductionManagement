package tr.com.ppm.desktop.model.common;

/**
 * @author Orhun Dalabasmaz
 */
public enum State {
	SOLID("KATI"), LIQUID("SIVI");

	private String value;

	State(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
