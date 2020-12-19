package persistence.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import service.dto.Ingredient;
import service.dto.Recipe;
import service.dto.RecipeIngredient;
import service.dto.RecipeStep;

public class RecipeDAO {

	private static JDBCUtil jdbcUtil = null;

	public RecipeDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int insertRecipe(Recipe rcp) {

		int result = 0;
		int generatedKey = 0;
		
		// insert Recipe
		String sql = "INSERT INTO RECIPE (recipeId, recipeName, summary, nation, difficulty, userId, image, report, published) "
				+ "VALUES (recipeId_seq.nextval, ?, ?, ?, ?, ?, ?, ?,SYSDATE )";
		Object[] param = new Object[] { rcp.getRecipeName(), rcp.getSummary(), rcp.getNation(),
				rcp.getDifficulty(), rcp.getUserId(), rcp.getImage(), 0};
		jdbcUtil.setSqlAndParameters(sql, param);
		String key[] = {"recipeId"};
		
		try {
			jdbcUtil.executeUpdate(key);
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if(rs.next()) {
				generatedKey = rs.getInt(1);
				rcp.setRecipeId(String.valueOf(generatedKey));
				System.out.println("insert recipe success");
			}
			return generatedKey;
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return generatedKey;
	}

	public boolean insertRecipeStep(String rcpId, List<RecipeStep> rcpStepList) throws Exception {
		int result = 0;
		String sql = "INSERT INTO RECIPESTEP(recipeId, stepNum, content) " + "VALUES (?, ?, ?)";
		try {
			for (int i = 0; i < rcpStepList.size(); i++) {
				Object[] param = new Object[] { rcpId, rcpStepList.get(i).getStepNum(),
						rcpStepList.get(i).getContent() };
				jdbcUtil.setSqlAndParameters(sql, param);
				result += jdbcUtil.executeUpdate();
			}
			if (result == rcpStepList.size()) {
				System.out.println("insert recipeStep success");
				return true;
			}
			else 
				System.out.println("insert recipeStep failed");
		} catch (Exception e) {
			e.printStackTrace();
			jdbcUtil.rollback();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return false;
	}

	public boolean insertRecipeIngredient(String rcpId, List<RecipeIngredient> rcpIngList) throws Exception {
		int result = 0;
		String sql = "INSERT INTO RECIPE_INGREDIENT(recipeId, ingredientId, amount, unit) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			for (int i = 0; i < rcpIngList.size(); i++) {
				Object[] param = new Object[] { rcpId, rcpIngList.get(i).getIngredientId(),
						 rcpIngList.get(i).getAmount(),
						rcpIngList.get(i).getUnit() };
				jdbcUtil.setSqlAndParameters(sql, param);
				result += jdbcUtil.executeUpdate();
			}
			if (result == rcpIngList.size()) {
				System.out.println("insert recipeIngredient success");
				return true;
			}
			else 
				System.out.println("insert recipeIngredient failed");
		} catch (Exception e) {
			e.printStackTrace();
			jdbcUtil.rollback();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return false;
	}

	public int updateRecipe(Recipe rcp) {

		String sql = "UPDATE RECIPE SET ";
		int index = 0;

		Object[] tempParam = new Object[10];

		if (rcp.getRecipeName() != null) { 
			sql += "recipeName = ?, "; 
			tempParam[index++] = rcp.getRecipeName(); 
		}
		if (rcp.getSummary() != null) { 
			sql += "summary = ?, ";
			tempParam[index++] = rcp.getSummary(); 
		}
		if (rcp.getNation() != null) { 
			sql += "nation = ?, "; 
			tempParam[index++] = rcp.getNation(); 
		}
		if (rcp.getDifficulty() != null) { 
			sql += "difficulty = ?, "; 
			tempParam[index++] = rcp.getDifficulty();
		}
		if (rcp.getImage() != null) { 
			sql += "image = ?, "; 
			tempParam[index++] = rcp.getImage();
		}

		sql += "WHERE recipeId = ? "; 
		sql = sql.replace(", WHERE", " WHERE"); 

		tempParam[index++] = rcp.getRecipeId(); 

		Object[] newParam = new Object[index];
		for (int i = 0; i < newParam.length; i++)
			newParam[i] = tempParam[i];

		jdbcUtil.setSqlAndParameters(sql, newParam);

		try {
			int result = jdbcUtil.executeUpdate(); 
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); 
		}
		return 0;
	}

	public int updateRecipeStep (List<RecipeStep> rcpStepList) {

		String sql = "UPDATE RECIPESTEP SET " + "SET STEPNUM=?, CONTENT=?" /*+  "WHERE RECIPEID=?"*/;
		int result = 0;
		try {
			for (RecipeStep rcpStep : rcpStepList) {
				Object[] param = new Object[] {rcpStep.getStepNum(), rcpStep.getContent()/*, rcpStep.getRecipeId() */};
				jdbcUtil.setSqlAndParameters(sql, param);
				result += jdbcUtil.executeUpdate();	
			}
			return result;		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;
	}

	public int updateRecipeIngredient (List<RecipeIngredient> rcpIngList) {

		String sql = "UPDATE RECIPEINGREDIENT SET " + "SET INGREDIENTID=?, INGREDIENTNAME=?, AMOUNT=?, UNIT=?" /* + "WHERE RECIPEID=? "*/;
		int result = 0;
		try {
			for (RecipeIngredient rcpIng : rcpIngList) {
				Object[] param = new Object[] {rcpIng.getIngredientId(), rcpIng.getIngredientName(), rcpIng.getAmount(), rcpIng.getUnit()};
				jdbcUtil.setSqlAndParameters(sql, param);
				result += jdbcUtil.executeUpdate();	
			}
			return result;		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;
	}

	public void deleteRecipe (String rcpId) {
		String sql = "DELETE FROM RECIPE WHERE recipeId = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {rcpId});

		try {
			int result = jdbcUtil.executeUpdate();
			if (result == 1)	System.out.println("delete recipe success");
			else	System.out.println("delete recipe failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			jdbcUtil.close();
		}finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
	}

	public void deleteRecipeStep (String recipeId) {
		String sql = "DELETE FROM RECIPESTEP WHERE recipeID = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});

		try {
			int result = jdbcUtil.executeUpdate();
			if (result > 0)		System.out.println("delete recipeSteps success");
			else	System.out.println("delete recipeSteps failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			jdbcUtil.close();
		}
	}

	public void deleteRecipeIngredient (String recipeId) {
		String sql = "DELETE FROM RECIPE_INGREDIENT WHERE recipeId = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});

		try {
			int result = jdbcUtil.executeUpdate();
			if (result > 0)		System.out.println("delete recipe_ingredient success");
			else	System.out.println("delete recipe_ingredient failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			jdbcUtil.close(); 
		}
	}

	public List<Recipe> getMyRecipeList(String userId){
		List<Recipe> myRecipeList = null;
		String sql = "SELECT recipeId, recipeName, userId, summary, nation, difficulty, image, report " + "FROM RECIPE "
				+ "WHERE userId = ? ";
		Object[] param = {userId};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			myRecipeList = new ArrayList<Recipe>();

			while(rs.next()) {
				Recipe rcp = new Recipe();
				rcp.setRecipeId(rs.getString("recipeId"));
				rcp.setRecipeName(rs.getString("recipeName"));
				rcp.setUserId(rs.getString("userId"));
				rcp.setSummary(rs.getString("summary"));
				rcp.setNation(rs.getString("nation"));
				rcp.setDifficulty(rs.getString("difficulty"));
				rcp.setImage(rs.getString("image"));
				rcp.setReport(rs.getInt("report"));
				myRecipeList.add(rcp);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return myRecipeList;
	}
	
	public List<Recipe> getRecipeListByName(String recipeName) {
		String sql = "SELECT recipeId, recipeName, summary, image " + "FROM RECIPE "
				+ "WHERE recipeName LIKE '%' || ? || '%'";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {  "%"+recipeName+"%" });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Recipe> rcpList = new ArrayList<Recipe>();
			while (rs.next()) {
				Recipe rcp = new Recipe();
				rcp.setRecipeId(rs.getString("recipeId"));
				rcp.setRecipeName(rs.getString("recipeName"));
				rcp.setSummary(rs.getString("summary"));
				rcp.setImage(rs.getString("image"));
				rcpList.add(rcp);
			}
			if (rcpList.isEmpty())
				System.out.println("empty refrigerator");
			return rcpList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<Recipe >getRecipeListByIng(String[] ingIds) {
		String sql = "SELECT recipeId, recipeName, summary, image, COUNT(recipeId) AS cnt "
				+ "FROM ( SELECT r.recipeId, i.ingredientId, r.recipeName, r.summary, r.image " 
				+ "FROM recipe r, recipe_ingredient ri, ingredient i "
				+ "WHERE r.recipeId = ri.recipeId AND ri.ingredientId = i.ingredientId "
				+ "AND i.ingredientId IN (? ";
		
		Object[] params = new Object[ingIds.length];
		for (int i = 0; i < ingIds.length; i++) {
			params[i] = ingIds[i];
		}
		
		for (int i = 0; i < ingIds.length-1; i++) {
			sql += ", ? ";
		}
		
		sql += ")) GROUP BY recipeId, recipeName, summary, image ORDER BY cnt DESC ";
		
		jdbcUtil.setSqlAndParameters(sql, params);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Recipe> rcpList = new ArrayList<Recipe>();
			
			while (rs.next()) {
				Recipe rcp = new Recipe();
				rcp.setRecipeId(rs.getString("recipeId"));
				rcp.setRecipeName(rs.getString("recipeName"));
				rcp.setSummary(rs.getString("summary"));
				rcp.setImage(rs.getString("image"));
				rcpList.add(rcp);
			}
			return rcpList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public Recipe findRecipeById(String recipeId) {
		String sql = "SELECT recipeId, recipeName, userId, summary, nation, difficulty, image, report " + "FROM RECIPE "
				+ "WHERE recipeId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});

		Recipe rcp = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				rcp = new Recipe();
				rcp.setRecipeId(rs.getString("recipeId"));
				rcp.setRecipeName(rs.getString("recipeName"));
				rcp.setUserId(rs.getString("userId"));
				rcp.setSummary(rs.getString("summary"));
				rcp.setNation(rs.getString("nation"));
				rcp.setDifficulty(rs.getString("difficulty"));
				rcp.setImage(rs.getString("image"));
				rcp.setReport(rs.getInt("report"));
			}
			rcp.setIngList(findRcpIngById(recipeId));
			rcp.setStepList(findRcpStepById(recipeId));
			return rcp;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<RecipeIngredient> findRcpIngById (String recipeId) {
		String sql = "SELECT R1.recipeId, R2.ingredientId, ingredientName, amount, unit " + "FROM RECIPE R1, RECIPE_INGREDIENT R2, INGREDIENT I " 
					+ "WHERE R2.ingredientId=I.ingredientId and R1.recipeId=R2.recipeId and R1.recipeId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<RecipeIngredient> rcpIngList = new ArrayList<RecipeIngredient>();
			while (rs.next()) {
				RecipeIngredient rcpIng = new RecipeIngredient();
				rcpIng.setIngredientName(rs.getString("ingredientName"));
				rcpIng.setAmount(rs.getDouble("amount"));
				rcpIng.setUnit(rs.getString("unit"));
				rcpIngList.add(rcpIng);
			}
			return rcpIngList;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("failed");
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<RecipeStep> findRcpStepById (String recipeId) {
		String sql = "SELECT stepNum, content " + "FROM RECIPESTEP " + "WHERE recipeId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeId});
		RecipeStep rcpStep = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<RecipeStep> rcpStepList = new ArrayList<RecipeStep>();
			while (rs.next()) {
				rcpStep = new RecipeStep();
				rcpStep.setStepNum(rs.getInt("stepNum"));
				rcpStep.setContent(rs.getString("content"));
				rcpStepList.add(rcpStep);
			}
			return rcpStepList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
