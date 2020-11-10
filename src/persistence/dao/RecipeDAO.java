package persistence.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import service.dto.*;

public class RecipeDAO {

	private static JDBCUtil jdbcUtil = null;

	public RecipeDAO() {
		jdbcUtil = new JDBCUtil();
	}

	// 레시피 추가 (recipeStep)
	public static int insertRecipe (Recipe rcp) {
		int result = 0;
		String sql = "INSERT INTO RECIPE (recipeId, recipeName, summary, nation, difficulty, image, report) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { rcp.getRecipeId(), rcp.getRecipeName(), rcp.getSummary(), rcp.getNation(),
				rcp.getDifficulty(), rcp.getImage(), rcp.getReport() };
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			result = jdbcUtil.executeUpdate();
			//			매개변수로 (List<RecipeStep> rcpStepList, List<RecipeIngredient> rcpIngList)를 받거나
			//			rcp.getRecipeId() 를 통해 쿼리 돌려서 List를 받은 후 아래의 두 줄 수행
			//			insertRecipeStep(rcpStepList);
			//			insertRecipeIngredient(rcpStepList);
			if (result == 1)
				System.out.println("insert recipe success");
			else
				System.out.println("insert recipe failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}

	public static int insertRecipeStep (List<RecipeStep> rcpStepList) {
		int result = 0;
		String sql = "INSERT INTO RECIPESTEP (recipeId, stepNum, content) "
				+ "VALUES (?, ?, ?)";
		//		매개변수 (RecipeStep rcpStep)
		//		Object[] param = new Object[] { rcpStep.getRecipeId(), rcpStep.getStepNum(), rcpStep.getContent() };
		//		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			for (int i = 0; i < rcpStepList.size(); i++) {
				Object[] param = new Object[] { rcpStepList.get(i).getRecipeId(), rcpStepList.get(i).getStepNum(), rcpStepList.get(i).getContent() };
				jdbcUtil.setSqlAndParameters(sql, param);
				result += jdbcUtil.executeUpdate();
				jdbcUtil.close();
			}
			//			result = jdbcUtil.executeUpdate();
			//			if (result == 1)
			if (result == rcpStepList.size())
				System.out.println("insert recipeStep success");
			else {
				System.out.println("insert recipeStep failed");
				jdbcUtil.rollback();
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}

	public static int insertRecipeIngredient (List<RecipeIngredient> rcpIngList) {
		int result = 0;
		String sql = "INSERT INTO RECIPESTEP (recipeId, ingredientId, ingredientName, amount, unit) "
				+ "VALUES (?, ?, ?, ?, ?)";
		//		매개변수 (RecipeIngredient rcpIng)
		//		Object[] param = new Object[] { rcpIng.getRecipeId(), rcpIng.getIngredientId(), rcpIng.getIngredientName()
		//				, rcpIng.getAmount(), rcpIng.getUnit() };
		//		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			for (int i = 0; i < rcpIngList.size(); i++) {
				Object[] param = new Object[] { rcpIngList.get(i).getRecipeId(), rcpIngList.get(i).getIngredientId()
						, rcpIngList.get(i).getIngredientName(), rcpIngList.get(i).getAmount(), rcpIngList.get(i).getUnit() };
				jdbcUtil.setSqlAndParameters(sql, param);
				result += jdbcUtil.executeUpdate();
				jdbcUtil.close();
			}
			//			result = jdbcUtil.executeUpdate();
			//			if (result == 1)
			if (result == rcpIngList.size())
				System.out.println("insert recipeIngredient success");
			else {
				System.out.println("insert recipeIngredient failed");
				jdbcUtil.rollback();
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return result;
	}

	// 레시피 수정
	public static int updateRecipe (Recipe rcp, String userId) {

		if (rcp.getUserId() != userId) 
			System.out.println("recipe update failed because of incorrect Id");

		else {

			String sql = "UPDATE RECIPE SET ";
			int index = 0;

			Object[] tempParam = new Object[10];		// update 문에 사용할 매개변수를 저장할 수 있는 임시 배열

			if (rcp.getRecipeName() != null) {		// 이름이 설정되어 있을 경우
				sql += "recipeName = ?, ";		// update 문에 이름 수정 부분 추가
				tempParam[index++] = rcp.getRecipeName();		// 매개변수에 수정할 이름 추가
			}
			if (rcp.getSummary() != null) {		// summary가 설정되어 있을 경우
				sql += "summary = ?, ";		// update 문에 summary 수정 부분 추가
				tempParam[index++] = rcp.getSummary();		// 매개변수에 수정할 summary 추가
			}
			if (rcp.getNation() != null) {		// 국가가 설정되어 있을 경우
				sql += "nation = ?, ";		// update 문에 국가 수정 부분 추가
				tempParam[index++] = rcp.getNation();		// 매개변수에 수정할 국가 추가
			}
			if (rcp.getDifficulty() != null) {		// 난이도가 설정되어 있을 경우
				sql += "difficulty = ?, ";		// update 문에 난이도 수정 부분 추가
				tempParam[index++] = rcp.getDifficulty();		// 매개변수에 수정할 난이도 추가
			}
			if (rcp.getImage() != null) {		// 이미지가 설정되어 있을 경우
				sql += "image = ?, ";		// update 문에 이미지 수정 부분 추가
				tempParam[index++] = rcp.getImage();		// 매개변수에 수정할 이미지 추가
			}

			sql += "WHERE recipeId = ? ";		// update 문에 조건 지정
			sql = sql.replace(", WHERE", " WHERE");		// update 문의 where 절 앞에 있을 수 있는 , 제거

			tempParam[index++] = rcp.getRecipeId();		// 찾을 조건에 해당하는 학번에 대한 매개변수 추가

			Object[] newParam = new Object[index];
			for (int i=0; i < newParam.length; i++)		// 매개변수의 개수만큼의 크기를 갖는 배열을 생성하고 매개변수 값 복사
				newParam[i] = tempParam[i];

			jdbcUtil.setSqlAndParameters(sql, newParam);

			try {
				int result = jdbcUtil.executeUpdate();		// update 문 실행
				return result;			// update 에 의해 반영된 레코드 수 반환
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			}
			finally {
				jdbcUtil.commit();
				jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
			}		
		}
		return 0;
	}

	// 레시피 삭제
	public static void deleteRecipe (Recipe rcp, String userId) {

		if (rcp.getUserId() != userId) 
			System.out.println("recipe update failed because of incorrect Id");

		else {
			deleteRecipeStep(rcp.getRecipeId());
			deleteRecipeIngredient(rcp.getRecipeId());

			String sql = "DELETE FROM RECIPE WHERE recipeId = ?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] {rcp.getRecipeId()});

			try {
				int result = jdbcUtil.executeUpdate();
				if (result == 1)	System.out.println("delete recipe success");
				else	System.out.println("delete recipe failed");
			} catch (Exception ex) {
				jdbcUtil.rollback();
				jdbcUtil.close();
			}
		}
	}
	
	public static void deleteRecipeStep (String recipeId) {
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

	public static void deleteRecipeIngredient (String recipeId) {
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

	// 재료명으로 레시피 DTO 반환 (검색기능)
	public static List<Recipe> getRecipeListByIngredient(List<Ingredient> ingredientList) {
		int size = ingredientList.size();

		String sql = "SELECT recipeId, recipeName, userId, summary, nation, difficulty, image, report "
				+ "FROM RECIPE r, RECIPE_INGREDIENT ri "
				+ "WHERE ingredientid = ? "; 

		for (int i = 0; i < ingredientList.size()-1; i++)
			sql += " or ingredientId = ? ";

		Object[] param = new Object[size];
		for (int i = 0; i < ingredientList.size(); i++)
			param[i++] = ingredientList.get(i++).getIngredientId();

		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<Recipe> rcpList = new ArrayList<Recipe>();
			while (rs.next()) {	
				Recipe rcp = new Recipe();
				rcp.setRecipeId(rs.getString("recipeId"));
				rcp.setRecipeName(rs.getString("recipeName"));
				rcp.setUserId(rs.getString("userId"));
				rcp.setSummary(rs.getString("summary"));
				rcp.setNation(rs.getString("nation"));
				rcp.setDifficulty(rs.getString("difficulty"));
				rcp.setImage(rs.getString("image"));
				rcp.setReport(rs.getInt("report"));
				rcpList.add(rcp);
			}
			if (rcpList.isEmpty())
				System.out.println("empty recipe");
			return rcpList;				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return null;
	}

	// recipeName으로 레시피 DTO 반환 (검색기능)
	public static List<Recipe> getRecipeListByName(String recipeName) {
		String sql = "SELECT recipeId, recipeName, userId, summary, nation, difficulty, image, report "
				+ "FROM RECIPE "
				+ "WHERE recipeName = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipeName});

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			List<Recipe> rcpList = new ArrayList<Recipe>();
			while (rs.next()) {	
				Recipe rcp = new Recipe();
				rcp.setRecipeId(rs.getString("recipeId"));
				rcp.setRecipeName(rs.getString("recipeName"));
				rcp.setUserId(rs.getString("userId"));
				rcp.setSummary(rs.getString("summary"));
				rcp.setNation(rs.getString("nation"));
				rcp.setDifficulty(rs.getString("difficulty"));
				rcp.setImage(rs.getString("image"));
				rcp.setReport(rs.getInt("report"));
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
}
