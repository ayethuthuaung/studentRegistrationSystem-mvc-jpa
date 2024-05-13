package student.com.dto;

import java.io.InputStream;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import student.com.models.CourseBean;

@Getter
@Setter
public class StudentDto {
	private int id;
	private String studentId, joindate, name, email , password, dob, gender, phone ,education, courseId, photoPath;
	private MultipartFile photoImageInput;
	private byte[] photo;
	private Set<CourseBean> courses = new HashSet<>();
	
}
