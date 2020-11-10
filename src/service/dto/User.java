package service.dto;
public class User {
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String authority;
	
	public User(String userId, String password, String name, String email, String phone, String authority) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.authority = authority;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
