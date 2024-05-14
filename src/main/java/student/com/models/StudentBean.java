package student.com.models;

import java.io.Serializable;
import java.sql.Blob;
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
	@Column(name = "id")
	private int id;
	@NotEmpty
	@Column(name = "studentid")
	private String studentId;
	@NotEmpty
	@Column(name = "joindate")
	private String joindate;
	@NotEmpty
	@Column(name = "name")
	private String name;
	@NotEmpty
	@Column(name = "email")
	private String email;
	@NotEmpty
	@Column(name = "password")
	private String password;
	@NotEmpty
	@Column(name = "dob")
	private String dob;
	@NotEmpty
	@Column(name = "gender")
	private String gender;
	@NotEmpty
	@Column(name = "phone")
	private String phone;
	@NotEmpty
	@Column(name = "education")
	private String education;
	
	@Column(name = "photo")		
	private byte[] photo;
	
		
    private String photoPath;
    @Column(name = "is_deleted")
    private boolean deleted;

    @Transient
    private String coursesAsString;


	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name="stud_course",joinColumns= {
	@JoinColumn(name = "studentId")},
	inverseJoinColumns = {
	@JoinColumn(name = "courseId")})
	
	private Set<CourseBean> courses = new HashSet<>();
	
	


}

