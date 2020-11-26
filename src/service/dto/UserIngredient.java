package service.dto;
import java.util.Date;

public class UserIngredient {
	private String userId;
	private String ingredientName;
	private String ingredientId;
	private int amount;
	private String unit;
	private String expireDate;
	
	public UserIngredient(String userId, String ingredientId, int amount, String unit,
			String expireDate) {
		super();
		this.userId = userId;
		this.ingredientId = ingredientId;
		this.amount = amount;
		this.unit = unit;
		this.expireDate = expireDate;
	}
	
	public UserIngredient(String ingredientName, int amount, String unit,
			String expireDate) {
		super();
		this.ingredientName = ingredientName;
		this.amount = amount;
		this.unit = unit;
		this.expireDate = expireDate;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(String ingredientId) {
		this.ingredientId = ingredientId;
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
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	
}
