package service.dto;
public class Ingredient {
	private String ingredientId;
	private String ingredientName;
	
	public Ingredient(String ingredientId, String ingredientName) {
		super();
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
	}
	public String getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(String ingredientId) {
		this.ingredientId = ingredientId;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	
	
}
