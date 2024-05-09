package student.com.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
@Entity
@Table(name="student")
public class StudentBean implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String studentId;
	@NotEmpty
	private String joindate;
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String dob;
	@NotEmpty
	private String gender;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String education;
	@NotEmpty
	private String courseId;

	@Transient	
	private MultipartFile photo;
	
	@Column(name = "photo")		
    private String photoPath;
	


	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name="stud_course",joinColumns= {
	@JoinColumn(name = "studentId")},
	inverseJoinColumns = {
	@JoinColumn(name = "courseId")})
	
	private Set<CourseBean> courses = new HashSet<>();
	
	


}

