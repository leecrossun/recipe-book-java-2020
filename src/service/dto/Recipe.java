package service.dto;

public class Recipe {
	private String recipeId;
	private String recipeName;
	private String userId;
	private String summary;
	private String nation;
	private String difficulty;
	private String image;
	private int report;


	public Recipe(String recipeId, String recipeName, String userId, String summary, String nation, String difficulty, String image,
			int report) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.userId = userId;
		this.summary = summary;
		this.nation = nation;
		this.difficulty = difficulty;
		this.image = image;
		this.report = report;
	}


	public Recipe(String recipeName, String summary) {
		super();
		this.recipeName = recipeName;
		this.summary = summary;
	}
	
	public Recipe() {
		super();
	}


	public String getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getReport() {
		return report;
	}
	public void setReport(int report) {
		this.report = report;
	}

}
