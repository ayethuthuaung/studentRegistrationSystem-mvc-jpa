package student.com.service;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import student.com.dto.CourseDto;
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
	
	public List<CourseDto> getAllCourse() {
		List<CourseBean> courseList = courseRepo.selectAllCourse();
		List<CourseDto> courseDtoList = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		for(CourseBean courseBean : courseList) {		
			CourseDto courseDto = modelMapper.map(courseBean, CourseDto.class);
			courseDtoList.add(courseDto);
		}
		return courseDtoList;
	}

	public CourseDto getCourseById(int id) {
		ModelMapper modelMapper = new ModelMapper();
		CourseBean courseBean = courseRepo.selectOneCourse(id);
		return modelMapper.map(courseBean, CourseDto.class);
	}

	public int update(CourseDto courseDto) {
		ModelMapper modelMapper = new ModelMapper();
		CourseBean courseBean = modelMapper.map(courseDto, CourseBean.class);
		return courseRepo.updateCourse(courseBean);
	}
	
	public int deleteCourse(int id) {
	    return courseRepo.deleteCourse(id);
	  }

	public long getCourseCount() {
		return courseRepo.getCourseCount();
	}


}

