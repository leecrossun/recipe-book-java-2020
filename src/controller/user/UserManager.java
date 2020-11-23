package controller.user;

import java.sql.SQLException;

import persistence.dao.UserDAO;
import service.dto.User;


public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private UserAnalysis userAanlysis;

	private UserManager() {
		try {
			userDAO = new UserDAO();
			userAanlysis = new UserAnalysis(userDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserManager getInstance() {
		return userMan;
	}

	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "가 이미 있습니다.");
		}
		return userDAO.createUser(user);
	}

	public int remove(String userId) throws SQLException, UserNotFoundException {
		if (userDAO.findUser(userId) == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}
		return userDAO.deleteUser(userId);
	}

	public int update(User user) throws SQLException, UserNotFoundException {
		if (userDAO.findUser(user.getUserId()) == null) {
			throw new UserNotFoundException(user.getUserId() + "는 존재하지 않는 아이디입니다.");
		}
		return userDAO.updateUser(user);
	}

	public boolean login(String userId, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 틀렸습니다.");
		}
		return true;
	}

	public UserDAO getUserDAO() {
		return this.userDAO;
	}

	public User findUser(String userId) throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);

		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}
		return user;
	}

}
