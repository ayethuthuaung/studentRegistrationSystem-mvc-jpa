package student.com.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
	private int id;
	  private String courseId;
	
	  private String name;
	 
	  private String month;
	  private double price;
	  
	  private String period;
	  private boolean is_deleted;
}
