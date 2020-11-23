package controller.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import persistence.dao.UserDAO;



// an example business class
public class UserAnalysis {
	private UserDAO dao;
	
	public UserAnalysis() {}
	
	public UserAnalysis(UserDAO dao) {
		super();
		this.dao = dao;
	}


}
