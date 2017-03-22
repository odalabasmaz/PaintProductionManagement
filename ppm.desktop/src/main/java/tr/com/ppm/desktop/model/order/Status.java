package tr.com.ppm.desktop.model.order;

/**
 * @author Orhun Dalabasmaz
 */
public enum Status {
	AWAITING_PROGRESS("Üretimi Bekliyor"),
	AWAITING_RAW_MATERIAL("Hammadde Bekleniyor"),
	IN_PROGRESS("Üretim Aşamasında"),
	CANCELED("İptal Edildi"),
	COMPLETED("Tamamlandı");

	private String value;

	Status(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
