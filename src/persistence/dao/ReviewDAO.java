package persistence.dao;
import java.sql.*;
import java.util.*;
//개인 리뷰 검색
//public static ArrayList<Review> findByUserId(String userId)
//리뷰 쓰기
//public static void writeMyReview(Review review)
//레시피 별로 리뷰 검색
//public static ArrayList<Review> findByRecipeId(String recipeId)
import java.util.Date;

import service.dto.Review;

public class ReviewDAO {
	
	private static JDBCUtil jdbcUtil = null;

	public ReviewDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	// 개인 리뷰 검색
	public static List<Review> findReviewByUserID(String userId) {
		String sql = "SELECT RECIPENAME, RATING FROM REVIEW WHERE USERID = ?";
		Object[] param = new Object[] {userId};
		List<Review> list = null;
		
		jdbcUtil.setSqlAndParameters(sql,  param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			list = new ArrayList<Review>();
			
			while (rs.next()) {
				Review myReview = new Review(rs.getString("RECIPENAME"), rs.getInt("RATING"));
				list.add(myReview);
			}
			
			if (list.isEmpty())
				System.out.println("findReviewByUserID failed");
			else
				System.out.println("findReviewByUserID success");
				
			
			return list;
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return list;
	}
	
	// 리뷰 쓰기
	public static void writeMyReview(Review review) {
		String sql = "INSERT INTO REVIEW(REVIEWID, CONTENT, RATING, USERID, RECIPEID, PUBLISHED) VALUES(?, ?, ?, ?, ?, ?)";
		// 현재시간 구하기 (이걸 DAO에 포함시키는게 맞는지 고민 ... 이후에 SELECT할 떄  담을게 필요해서 published 추가했습니다.
//		Calendar cal = new GregorianCalendar();
//		Timestamp now = new Timestamp(cal.getTimeInMillis());
		
		Object[] param = new Object[] { review.getReviewId(), review.getContent(), review.getRating(), review.getUserId(), review.getRecipeId(), review.getPublished()};
		jdbcUtil.setSqlAndParameters(sql, param);
		try {
			int result = jdbcUtil.executeUpdate();
			if (result > 0)
				System.out.println("createReview success");
			else
				System.out.println("createReview failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
	}
	
	// 리뷰 삭제
	public static void deleteMyReview(Review review) {
		
		String sql = "DELETE FROM REVIEW WHERE REVIEWID = ?";
		Object[] param = new Object[] {review.getReviewId()};
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
			jdbcUtil.close();	// resource 반환
		}	
	}
	
	// 리뷰 수정
	public static void updateMyReview(Review review) {
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
			jdbcUtil.close();	// resource 반환
		}		 
	}
	
	// 레시피 별로 리뷰 검색
	public static List<Review> findReviewByRecipeId(String recipeId) {
		String sql = "SELECT * FROM REVIEW WHERE RECIPEID = ?";
		Object[] param = new Object[] {recipeId};
		List<Review> list = null;
		
		jdbcUtil.setSqlAndParameters(sql,  param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			list = new ArrayList<Review>();

			while (rs.next()) {
				Review myReview = new Review(rs.getString("REVIEWID"), rs.getString("USERID"), rs.getString("RECIPEID"), rs.getString("CONTENT"), rs.getInt("RATING"), rs.getDate("PUBLISHED"));
				list.add(myReview);
			}
			
			if (list.isEmpty())
				System.out.println("findReviewByRecipeID failed");
			else
				System.out.println("findReviewByRecipeId success");
			
			return list;
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return list;
	}

}
