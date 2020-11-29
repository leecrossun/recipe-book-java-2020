package persistence.dao;
import java.sql.*;
import java.util.*;
import java.sql.ResultSet;
import service.dto.RecipeIngredient;
import service.dto.Review;

public class ReviewDAO {
	
	private static JDBCUtil jdbcUtil = null;

	public ReviewDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	// UserId와 일치하는 Review return
	public List<Review> findReviewByUserID(String userId) {
		String sql = "SELECT R2.RECIPENAME, R1.CONTENT, R1.RATING "
				+ "FROM REVIEW R1, RECIPE R2 "
				+ "WHERE R1.USERID = ? AND R1.RECIPEID = R2.RECIPEID";
		
		Object[] param = new Object[] { userId };
		
		jdbcUtil.setSqlAndParameters(sql, param);

		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Review> list = new ArrayList<Review>();
			
			while (rs.next()) {
				Review myReview = new Review();
				myReview.setRecipeName(rs.getString("RECIPENAME"));
				myReview.setContent(rs.getString("CONTENT"));
				myReview.setRating(rs.getInt("RATING"));
				list.add(myReview);
			}
			
			if (list.isEmpty())
				System.out.println("findReviewByUserID empty");
			else
				System.out.println("findReviewByUserID success");
				
//			System.out.println(list.get(0));
			return list;
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
			System.out.println("findReviewByUserID failed");
		} finally {
			jdbcUtil.close();
			System.out.println("findReviewByUserID success");
			
		}
		return null;
	}
	
	// 리뷰 작성
	public int writeMyReview(Review review) {
		int generatedKey = 0;
		String sql = "INSERT INTO REVIEW (REVIEWID, CONTENT, RATING, USERID, RECIPEID, PUBLISHED) VALUES(reviewId_seq.nextval, ?, ?, ?, ?, SYSDATE)";
//		현재시간 구하기 (이걸 DAO에 포함시키는게 맞는지 고민 ... 이후에 SELECT할 떄  담을게 필요해서 published 추가했습니다.
//		Calendar cal = new GregorianCalendar();
//		Timestamp now = new Timestamp(cal.getTimeInMillis());
		
		Object[] param = new Object[] {review.getContent(), review.getRating(), review.getUserId(), review.getRecipeId()};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"reviewId"};
		
		
		try {
			jdbcUtil.executeUpdate(key);
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if (rs.next()) {
			
			generatedKey = rs.getInt(1);
			}
			/*
			 * if (result > 0) System.out.println("createReview success"); else
			 * System.out.println("createReview failed");
			 */
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 諛섑솚
		}
		return generatedKey;
	}
	
	// 리뷰 삭제
	public void deleteMyReview(Review review) {
		
		String sql = "DELETE FROM REVIEW WHERE REVIEWID = ?";
		Object[] param = new Object[] {review.getReviewId()};
		System.out.println("리뷰아이디로그 : " + review.getReviewId());
		jdbcUtil.setSqlAndParameters(sql, param);
		try {				
			int result = jdbcUtil.executeUpdate();
			if (result > 0)
				System.out.println("deleteReview success");
			else
				System.out.println("deleteReview failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}	
	}
	
	// 리뷰 수정
	public void updateMyReview(Review review) {
		String sql = "UPDATE REVIEW SET CONTENT = ? , RATING = ?, PUBLISHED = ? WHERE REVIEWID = ?";
		Object[] param = new Object[] {review.getContent(), review.getRating(), review.getPublished(), review.getReviewId()};
		jdbcUtil.setSqlAndParameters(sql, param);
		try {				
			int result = jdbcUtil.executeUpdate();
			if (result > 0)
				System.out.println("updateReview success");
			else
				System.out.println("updateReview failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		 
	}
	
	// 레시피 별로 리뷰 검색
	public List<Review> findReviewByRecipeId(String recipeId) {
		String sql = "SELECT REVIEWID, CONTENT, RATING, USERID, RECIPEID, PUBLISHED "
				+ "FROM REVIEW " 
				+ "WHERE RECIPEID=?";
		Object[] param = new Object[] {recipeId};
		
		List<Review> list = new ArrayList<Review>();
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			while (rs.next()) {
				Review review = new Review();
				review.setReviewId(rs.getString("REVIEWID"));
				review.setContent(rs.getString("CONTENT"));
				review.setRating(rs.getInt("RATING"));
				review.setUserId(rs.getString("USERID"));
				review.setRecipeId(rs.getString("RECIPEID"));
				review.setPublished(rs.getString("PUBLISHED"));
				list.add(review);
			}
			
			if (list.isEmpty())
				System.out.println("findReviewByRecipeID empty");
			else
				System.out.println("findReviewByRecipeId success");
			
			return list;
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
			System.out.println("findReviewByRecipeID error");
		} finally {
			System.out.println("findReviewByRecipeId success");
			jdbcUtil.close();
		}
		return list;
	}
	
	// 리뷰 아이디로 리뷰 검색
	public Review findReviewByReviewId(String reviewId) {
		String sql = "SELECT * FROM REVIEW WHERE REVIEWID = ?";
		Object[] param = new Object[] {reviewId};
		Review review = null;
		
		jdbcUtil.setSqlAndParameters(sql,  param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			review = new Review();

			while (rs.next()) {
				review = new Review(rs.getString("REVIEWID"), rs.getString("USERID"), rs.getString("RECIPEID"), rs.getString("CONTENT"), rs.getInt("RATING"), rs.getString("PUBLISHED"));
			}
			
			if (empty(review))
				System.out.println("findReviewByRecipeID failed");
			else
				System.out.println("findReviewByRecipeId success");
			
			return review;
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return review;
	}
	
	// 객체가 비어있는지 확인
	 public static Boolean empty(Object obj) {
		  if (obj instanceof String) return obj == null || "".equals(obj.toString().trim());
		  else if (obj instanceof List) return obj == null || ((List<?>) obj).isEmpty();
		  else if (obj instanceof Map) return obj == null || ((Map<?, ?>) obj).isEmpty();
		  else if (obj instanceof Object[]) return obj == null;
		  else return obj == null;
		 }

}
