package tr.com.ppm.desktop.model.common;

/**
 * @author Orhun Dalabasmaz
 */
public enum State {
	SOLID, LIQUID;

	@Override
	public String toString() {
		if (this == SOLID) {
			return "KATI";
		} else if (this == LIQUID) {
			return "SIVI";
		}
		return super.toString();
	}
}
