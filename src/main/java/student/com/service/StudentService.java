package student.com.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.com.dto.CourseDto;
import student.com.dto.StudentDto;
import student.com.models.CourseBean;
import student.com.models.StudentBean;
import student.com.repository.CourseRepository;
import student.com.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	public StudentService() {
		this.studentRepo = new StudentRepository();
	}
	
	public int getCount() {
		return studentRepo.getCount();
	}
	
	public Set<CourseBean> addCourseForStudent(String[] id) {
		 Set<CourseBean> courses = new HashSet<>();
		 
		 
		 for (String c : id) {
		        System.out.println(c);
		        CourseBean course = courseRepo.selectByCourseId(c);
		        if (course != null) {
		            courses.add(course);
		        } else {
		            return null; 
		        }
		    }
		    return courses;
		 
	}

	public int addNewStudent(StudentDto studentDto) {
		ModelMapper modelMapper = new ModelMapper();
		
		int isAdded = studentRepo.insertStudent(modelMapper.map( studentDto, StudentBean.class));
		return isAdded;
	}
	
	public List<StudentDto> getAllStudent() {
		List<StudentBean> studentList = studentRepo.selectAllStudent();
		List<StudentDto> studentDtoList = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		for(StudentBean studentBean : studentList) {		
			StudentDto studentDto = modelMapper.map(studentBean, StudentDto.class);
			String photoString = Base64.getEncoder().encodeToString(studentBean.getPhoto());
            studentDto.setPhotoPath(photoString);
			studentDtoList.add(studentDto);
		}
		return studentDtoList;
	}
	
	public boolean updateStatus(int id, int deleteStatus) {
        return studentRepo.updateStatus(id, deleteStatus);
    }

	public long getStudentCount() {
		return studentRepo.getStudentCount();
	}

	public StudentDto getStudentById(int id) {
		StudentBean studentBean = studentRepo.selectOneStudent(id);
		ModelMapper modelMapper = new ModelMapper();
		StudentDto studentDto = modelMapper.map(studentBean, StudentDto.class);
		String photoString = Base64.getEncoder().encodeToString(studentBean.getPhoto());
        studentDto.setPhotoPath(photoString);
		return studentDto;
	}

	public int updateStudent(StudentDto studentDto) {
		ModelMapper modelMapper = new ModelMapper();
		return studentRepo.updateStudent(modelMapper.map(studentDto, StudentBean.class));
	}
	
}
