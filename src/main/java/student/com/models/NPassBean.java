package student.com.models;


import jakarta.persistence.Entity;

@Entity
public class NPassBean {
	

	private String password;

	private String email;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
