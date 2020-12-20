package persistence.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import service.dto.User;

public class UserDAO {
	private static String namespace = "persistence.dao.CommentMapperUser";
	InputStream inputStream;
	static SqlSessionFactory sf;
	static SqlSession sqlSession;
	public UserDAO() throws Exception {
		String resource = "mybatis-config-user.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
		}catch(IOException e) {
			throw new Exception("오류 발생");
		}
		
		sf = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public static int createUser(User user) {
		sqlSession = sf.openSession();
		try {
		int count = sqlSession.insert(
				namespace + ".createUser", user);
		if(count == 1)
			sqlSession.commit();
		return count;
		}finally{
			sqlSession.close();
		}
	}
	public static int deleteUser(String userId) {
		sqlSession = sf.openSession(false);
		Map<String, String> param = new HashMap<String, String>(2);
		param.put("adminUser", "hyosun");
		param.put("deleteUser", userId);
		int a;
		try {
			ArrayList<Object> arr = (ArrayList<Object>) sqlSession.selectList(namespace + ".findUserFavorite", userId);
			a = sqlSession.delete(namespace + ".deleteUserFavorite", userId);
			if(arr.size()!=a) {
				sqlSession.rollback();
				return 0;
			}
			
			arr = (ArrayList<Object>) sqlSession.selectList(namespace + ".findUserIngredient", userId);
			a = sqlSession.delete(namespace + ".deleteUserIngredient", userId);
			if(arr.size()!=a) {
				sqlSession.rollback();
				return 0;
			}
			arr = (ArrayList<Object>) sqlSession.selectList(namespace + ".findUserReview", userId);
			a = sqlSession.delete(namespace + ".deleteUserReview", userId);
			if(arr.size()!=a) {
				sqlSession.rollback();
				return 0;
			}
			arr = (ArrayList<Object>) sqlSession.selectList(namespace + ".findUserRecipe", userId);
			a = sqlSession.update(namespace + ".updateUserRecipe", param); 
			if(arr.size()!=a) {
				sqlSession.rollback();
				return 0;
			}
			a= sqlSession.delete(namespace + ".deleteUser", userId);
			
			if(a==0) {
				sqlSession.rollback();
				return 0;
			}
			return 1;
			
		}finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}
	public static int updateUser(User user) {
		sqlSession = sf.openSession();
		try {
			int count =  sqlSession.update(namespace + ".updateUser", user);
			if(count == 1)
				sqlSession.commit();
			return count;
		}finally {
			sqlSession.close();
		}
	}
	public boolean existingUser(String userId) {
		sqlSession = sf.openSession();
		try {
			User user = (User)sqlSession.selectOne(namespace + ".findUser", userId);
			if(user!=null)
				return true;
			else
				return false;
		}finally {
			sqlSession.close();
		}
	}
	public User findUser(String userId) {
		sqlSession = sf.openSession();
		User user = null;
		try {
			user = sqlSession.selectOne(namespace + ".findUser", userId);
			return user;
		}finally {
			sqlSession.close();
		}
	}
//	private static JDBCUtil jdbcUtil = null;
//
//	public UserDAO() {
//		jdbcUtil = new JDBCUtil();
//	}
//
//	public static int createUser(User user) {
//		String sql = "INSERT INTO USERINFO(USERID, USERNAME, PASSWORD, PHONE, EMAIL) VALUES(?, ?, ?, ?, ?)";
//		Object[] param = new Object[] { user.getUserId(), user.getName(), user.getPassword(), user.getPhone(), user.getEmail() };
//		jdbcUtil.setSqlAndParameters(sql, param);
//		try {
//			int result = jdbcUtil.executeUpdate(); // insert 문 실행
//			return result;
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.commit();
//			jdbcUtil.close(); // resource 반환
//		}
//		return 0;
//	}
//
//	public static int deleteUser(String userId) {
//		
//		try {
//			String sql= "DELETE FROM FAVORITE WHERE USERID=?";
//			jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
//			jdbcUtil.executeUpdate();
//			sql= "DELETE FROM USER_INGREDIENT WHERE USERID=?";
//			jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
//			jdbcUtil.executeUpdate();
//			sql= "DELETE FROM REVIEW WHERE USERID=?";
//			jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
//			jdbcUtil.executeUpdate();
//			sql = "UPDATE RECIPE SET USERID='hyosun' WHERE USERID=?";
//			jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
//			jdbcUtil.executeUpdate();
//			sql = "DELETE FROM USERINFO WHERE USERID=?";		
//			jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 delete문과 매개 변수 설정
//			int result = jdbcUtil.executeUpdate();	// delete 문 실행
//			
//			if (result == 0)
//				throw new Exception("사용자가 삭제되지 않았습니다");
//			return result;
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		}
//		finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();	// resource 반환
//		}		
//		return 0;
//	}
//
//	public static int updateUser(User user) {
//		String sql = "UPDATE USERINFO " + "SET USERNAME=?, PASSWORD=?, PHONE=?, EMAIL=? WHERE USERID=?";
//		Object[] param = new Object[] { user.getName(), user.getPassword(), user.getPhone(), user.getEmail(), user.getUserId()};
//		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정
//
//		try {
//			int result = jdbcUtil.executeUpdate(); // update 문 실행
//			return result;
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.commit();
//			jdbcUtil.close(); // resource 반환
//		}
//		return 0;
//	}
//
//
//	public boolean existingUser(String userId) {
//		// TODO Auto-generated method stub
//		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정
//
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
//			if (rs.next()) {
//				int count = rs.getInt(1);
//				return (count == 1 ? true : false);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
//		return false;
//	}
//
//	public User findUser(String userId) {
//		// TODO Auto-generated method stub
//		String sql = "SELECT USERID, USERNAME, PASSWORD, PHONE, EMAIL, AUTHORITY FROM USERINFO WHERE USERID=?";
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
//		User user = null;
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();
//			if(rs.next()) {
//				user = new User(rs.getString("USERID"), rs.getString("PASSWORD"), rs.getString("USERNAME"), rs.getString("EMAIL"), rs.getString("PHONE"), rs.getNString("AUTHORITY"));
//			}
//		}catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
//		return user;
//	}
    
}
