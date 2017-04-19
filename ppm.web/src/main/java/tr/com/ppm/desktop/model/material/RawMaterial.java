package tr.com.ppm.desktop.model.material;

import tr.com.ppm.desktop.model.common.State;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Orhun Dalabasmaz
 */
@Entity
@Table(name = "RAW_MATERIAL")
public class RawMaterial extends Material {

	@Column(name = "STATE")
	private State state;

	//for hibernate
	RawMaterial() {
		super();
	}

	public RawMaterial(String code, String name, String desc, State state, double stock) {
		super(code, name, desc, stock);
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
