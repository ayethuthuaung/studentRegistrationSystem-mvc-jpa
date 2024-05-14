package student.com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import student.com.dto.CourseDto;
import student.com.service.CourseService;
import student.com.service.StudentService;
import student.com.service.UserService;
import student.com.utils.Helper;

@Controller
@AllArgsConstructor
public class PageController {
	
	@Autowired
	UserService  userService;
	@Autowired
	CourseService  courseService;
	@Autowired
	StudentService  studentService;
	
	@GetMapping({"/","/home"})
	public String home(Model m) {
		 List<CourseDto> list = courseService.getAllCourse();
		    if (list.size() == 0) {
		      m.addAttribute("msg", " Course Data not Found");
		    } else {
		      m.addAttribute("courses", list);

		    }
		return "home";
	}
	
	
	@GetMapping("/dashboard")
	public ModelAndView dashboard() {
		System.out.println("Hi DB");
	    Map<String, Long> counts = new HashMap<>();

	    // Retrieve counts and store in the map
	    long userCount = userService.getUserCount();
	    long courseCount = courseService.getCourseCount();
	    long studentCount = studentService.getStudentCount();
	    counts.put("userCount", userCount);
	    counts.put("courseCount", courseCount);
	    counts.put("studentCount", studentCount);
	   // counts.put("courseCount", courseCount);

	    // Pass the map to the dashboard view
	    return new ModelAndView("dashboard", "counts", counts);
	}
	
}
