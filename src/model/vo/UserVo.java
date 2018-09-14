package model.vo;

//实现用户实体的数据封装
public class UserVo {
	private String userName;
	private String password;
	private String chName;
	private String role;

	public UserVo() {
		super();
	}

	public UserVo(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public UserVo(String userName, String password, String chrName, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.chName = chrName;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chrName) {
		this.chName = chrName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserVo [userName=" + userName + ", password=" + password
				+ ", chName=" + chName + ", role=" + role + "]";
	}

}
