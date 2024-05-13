package student.com.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
	private int id;
	
	
	private String courseId;
	@NotEmpty
	private String name;
}
