package student.com.models;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Code must not be empty")
	 private String code;
	
	 @NotEmpty(message = "Name must not be empty")
	 private String name;
	    
	 @NotEmpty(message = "Email must not be empty")
	 @Pattern(regexp = "\\b[A-Za-z0-9._%+-]+@gmail\\.com\\b", message = "Email must be a Gmail address")
	 private String email;
	    
	 @NotEmpty(message = "Gender must not be empty")
	 private String gender;
	    
	 @NotEmpty(message = "Phone must not be empty")
	 @Size(min = 8, max = 12, message = "Phone number must be between 8 and 12 characters")
	 private String phone;
	 
	 @NotEmpty(message = "Date of Birth must not be empty")
	 private String dob;
	 
	 @NotEmpty(message = "Address must not be empty")
	 private String address;
	 
	 private String role;
	 
	 private String password;
	
	 //forsoftdelete
	 private boolean deleted;

}
