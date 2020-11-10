package service.dto;
public class RecipeStep {
	private String recipeId;
	private int stepNum;
	private String content;
	
	
	public RecipeStep(String recipeId, int stepNum, String content) {
		this.recipeId = recipeId;
		this.stepNum = stepNum;
		this.content = content;
	}
	public String getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}
	public int getStepNum() {
		return stepNum;
	}
	public void setStepNum(int stepNum) {
		this.stepNum = stepNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
