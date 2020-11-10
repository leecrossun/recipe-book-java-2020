package service.dto;

import java.sql.*;
import java.util.*;
import java.util.Date;

// DAO만드는 과정에서 reviewId와 recipeName이 있는게 편리할 것 같아 추가하였습니다.
public class Review {
	private String reviewId;
	private String userId;
	private String recipeId;
	private String recipeName;
	private String content;
	private int rating;
	private Date published;
	
	public Review() {
		
	}
	
	
	public Review(String reviewId, String userId, String recipeId, String recipeName, String content, int rating,
			Date published) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.content = content;
		this.rating = rating;
		this.published = published;
	}

	public Review(String reviewId, String userId, String recipeId, String content, int rating,
			Date published) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.recipeId = recipeId;
		this.content = content;
		this.rating = rating;
		this.published = published;
	}
	
	public Review(String reviewId, String userId, String recipeId, String recipeName, String content, int rating) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.content = content;
		this.rating = rating;
	}


	public Date getPublished() {
		return published;
	}

	public void setPublished(Timestamp published) {
		this.published = published;
	}

	public Review(String recipeName, int rating) {
		super();
		this.recipeName = recipeName;
		this.rating = rating;
	}


	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
