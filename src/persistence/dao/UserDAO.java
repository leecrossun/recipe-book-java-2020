package persistence.dao;

import java.sql.ResultSet;

import service.dto.User;

public class UserDAO {
	private static JDBCUtil jdbcUtil = null;

	public UserDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public static int createUser(User user) {
		String sql = "INSERT INTO USERINFO(USERID, USERNAME, PASSWORD, PHONE, EMAIL) VALUES(?, ?, ?, ?, ?)";
		Object[] param = new Object[] { user.getUserId(), user.getName(), user.getPassword(), user.getPhone(), user.getEmail() };
		jdbcUtil.setSqlAndParameters(sql, param);
		try {
			int result = jdbcUtil.executeUpdate(); // insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	public static int deleteUser(String userId) {
		

		try {
			String sql= "DELETE FROM FAVORITE WHERE USERID=?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
			int result = jdbcUtil.executeUpdate();
			sql = "DELETE FROM USERINFO WHERE USERID=?";		
			jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 delete문과 매개 변수 설정
			result = jdbcUtil.executeUpdate();	// delete 문 실행
			if(result==0)
				throw new Exception("사용자가 삭제되지 않았습니다");
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	public static int updateUser(User user) {
		String sql = "UPDATE USERINFO " + "SET USERNAME=?, PASSWORD=?, PHONE=?, EMAIL=? WHERE USERID=?";
		Object[] param = new Object[] { user.getName(), user.getPassword(), user.getPhone(), user.getEmail(), user.getUserId()};
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}


	public boolean existingUser(String userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}

	public User findUser(String userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT USERID, USERNAME, PASSWORD, PHONE, EMAIL, AUTHORITY FROM USERINFO WHERE USERID=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
		User user = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				user = new User(rs.getString("USERID"), rs.getString("PASSWORD"), rs.getString("USERNAME"), rs.getString("EMAIL"), rs.getString("PHONE"), rs.getNString("AUTHORITY"));
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return user;
	}
    
}
