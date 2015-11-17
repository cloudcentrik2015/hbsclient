package net.cloudcentrik.hbs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	@XmlElement
	private String userName;

	@XmlElement
	private String userPassword;

	@XmlElement
	private String userEmail;

	@XmlElement
	private String userType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public User(String userName, String userPassword, String userEmail,
			String userType) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userType = userType;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}

