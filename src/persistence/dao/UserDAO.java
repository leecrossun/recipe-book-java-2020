package persistence.dao;

import service.dto.User;

public class UserDAO {
	private static JDBCUtil jdbcUtil = null;

	public UserDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public static void createUser(User user) {
		String sql = "INSERT INTO USERINFO(USERID, USERNAME, PASSWORD, PHONE, EMAIL) VALUES(?, ?, ?, ?, ?)";
		Object[] param = new Object[] { user.getUserId(), user.getName(), user.getPassword(), user.getPhone(), user.getEmail() };
		jdbcUtil.setSqlAndParameters(sql, param);
		try {
			int result = jdbcUtil.executeUpdate(); // insert 문 실행
			if (result > 0)
				System.out.println("createUser success");
			else
				System.out.println("createUser failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}

	}

	public static void deleteUser(User user) {
		String sql = "DELETE FROM USERINFO WHERE USERID=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user.getUserId()});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			if (result > 0)
				System.out.println("deleteUser success");
			else
				System.out.println("deleteUser failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
	}

	public static void updateUser(User user) {
		String sql = "UPDATE USERINFO " + "SET USERNAME=?, PASSWORD=?, PHONE=?, EMAIL=? WHERE USERID=?";
		Object[] param = new Object[] { user.getName(), user.getPassword(), user.getPhone(), user.getEmail(), user.getUserId()};
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // update 문 실행
			if (result > 0)
				System.out.println("updateUser success");
			else
				System.out.println("updateUser failed");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
	}
//	public static void main(String[] args) {
//		jdbcUtil = new JDBCUtil();
//		User user = new User("id1", "pw1","name1", "email1", "phone1", null);
//		//createUser(user);
//		user.setPhone("010101");
//		//updateUser(user);
//		deleteUser(user);
//	}
    
}
