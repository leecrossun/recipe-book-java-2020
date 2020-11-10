package service.dto;
public class RecipeIngredient {
	private String recipeId;
	private String ingredientId;
	private String ingredientName;
	private int amount;
	private String unit;
	
	public RecipeIngredient(String recipeId, String ingredientId, String ingredientName, int amount, String unit) {
		this.recipeId = recipeId;
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
		this.amount = amount;
		this.unit = unit;
	}
	public String getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	
}
