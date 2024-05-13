package student.com.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto {
	
	private int id;
	private String code, name, email, gender, phone, dob, address, role, password;
	//forsoftdelete
		 private boolean deleted;
}
