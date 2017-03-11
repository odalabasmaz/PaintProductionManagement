package tr.com.ppm.desktop.model.material;

import tr.com.ppm.desktop.model.production.Ingredient;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Orhun Dalabasmaz
 */

@Entity
@Table(name = "PRODUCT")
public class Product extends Material {

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "PAINT_SUB_TYPE_ID")
	private PaintSubType paintSubType;   //

	@Column(name = "DENSITY")
	private double density;

	@Column(name="INTERMEDIATE_PRODUCT")
	private boolean intermediateProduct;

	@OneToMany(targetEntity = Ingredient.class)
	@JoinColumn( name = "PRODUCT_ID")
	private Set<Ingredient> ingredientSet = new HashSet<>();

	/*for hibernate*/
	Product() {
	}

	public PaintSubType getPaintSubType() {
		return paintSubType;
	}

	public void setPaintSubType(PaintSubType paintSubType) {
		this.paintSubType = paintSubType;
	}

	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public boolean isIntermediateProduct() {
		return intermediateProduct;
	}

	public void setIntermediateProduct(boolean intermediateProduct) {
		this.intermediateProduct = intermediateProduct;
	}

	public Set<Ingredient> getIngredientSet() {
		return ingredientSet;
	}

	public void setIngredientSet(Set<Ingredient> ingredientSet) {
		this.ingredientSet = ingredientSet;
	}
}
