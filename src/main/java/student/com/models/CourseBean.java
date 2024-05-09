package student.com.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
@Entity
@Table(name="course")
public class CourseBean implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
		
	private String courseId;
	
	@NotEmpty
	private String name;

	
	@ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
	private Set<StudentBean> studentBean = new HashSet<>();
	
	
	
}
