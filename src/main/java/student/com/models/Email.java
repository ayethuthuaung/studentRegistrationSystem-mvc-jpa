package student.com.models;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import jakarta.persistence.Entity;


@Entity
public class Email implements Serializable{

	
	@NotEmpty
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
