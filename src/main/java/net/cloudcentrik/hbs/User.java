package net.cloudcentrik.hbs;

public class User {

	private Integer id;

	private String name;

	private String email;

	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		
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

	public void setMobil(String phone) {
		this.phone = phone;
		
	}

	public User() {
	}

	public User(int id, String name, String email, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User that = (User) o;
		if (!getId().equals(that.getId()))
			return false;
		if (!getName().equals(that.getName()))
			return false;
		if (!getEmail().equals(that.getEmail()))
			return false;
		if (!getPhone().equals(that.getPhone()))
			return false;
		return true;
	}

}
