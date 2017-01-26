package tr.com.ppm.desktop.model.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Orhun Dalabasmaz
 */
public class Unit extends AuditableEntity {
	public enum TYPE {UNIT, DENSITY, TEMPERATURE, AREA, VOLUME, FORCE, ENERGY, MASS, WEIGHT}

	private TYPE type;
	private String definition;
	private String description;

	public Unit(TYPE type, String definition, String description) {
		this.type = type;
		this.definition = definition;
		this.description = description;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		String val = definition;
		if (StringUtils.isNotBlank(description)) {
			val += " (" + description + ")";
		}
		return val;

	}
}
