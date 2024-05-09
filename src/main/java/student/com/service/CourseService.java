package student.com.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import student.com.models.CourseBean;

import student.com.repository.CourseRepository;


@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepo;
	public CourseService() {
		this.courseRepo = new CourseRepository();
	}
	public int insertCourse(CourseBean courseBean) {
		return courseRepo.insertCourse(courseBean);
	}
	public int getCount() {
		return courseRepo.getCount();
	}


}

