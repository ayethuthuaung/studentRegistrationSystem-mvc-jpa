package student.com.controllers;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import student.com.dto.CourseDto;
import student.com.models.CourseBean;

import student.com.service.CourseService;




@Component
@ComponentScan("student.com.service")
@Controller
public class CourseController {

	@Autowired
	CourseService courseService;

	@RequestMapping(value = "/addCourse", method = RequestMethod.GET)
	public ModelAndView addCourse() {
		return new ModelAndView("addCourse", "courseDto", new CourseDto());
	}

	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public String courseInsertProcess(@ModelAttribute("courseDto") @Validated CourseDto courseDto, BindingResult bs, ModelMap m) {

		if (bs.hasErrors()) {
			System.out.println(bs);
			m.addAttribute("error", "Invalid Category required");
			return "addCourse";
		}
		int courseCount = courseService.getCount();
		int nextSequence = courseCount + 1;
		String formattedCourseId = String.format("COU%03d", nextSequence);
		courseDto.setCourseId(formattedCourseId);
		courseDto.setName(courseDto.getName());
		System.out.println(courseDto);
		ModelMapper modelMapper = new ModelMapper();
		int rs = courseService.insertCourse(modelMapper.map(courseDto, CourseBean.class));
		System.out.println(rs);

		if (rs == 0) {
			System.out.println(rs);
			m.addAttribute("errorMessage", "Course Insert Failed!!");
			return "addCourse";
		}

		m.addAttribute("successMessage", "Insert Course Successfully!!");
		return "addCourse";
	}

/*	@RequestMapping(value = "/courselist", method = RequestMethod.GET)
	public String displayAllCourse(ModelMap m) {
		List<Course> list = repository.selectAllCourse();
		if (list.size() == 0) {
			m.addAttribute("msg", " User Data not Found");
		} else {
			m.addAttribute("courseLists", list);

		}
		return "display_Course";

	}

	@RequestMapping(value = "/updateCourse/{id}", method = RequestMethod.GET)
	public ModelAndView updateCourse(@PathVariable int id) {

		Course dto = repository.selectOneCourse(id);
		ModelAndView m = new ModelAndView("update_Course");
		m.addObject("bean", dto);
		return m;
	}

	@RequestMapping(value = "/updateCourseProcess", method = RequestMethod.POST)
	public String updateCourseProcess(@ModelAttribute("bean") @Validated Course course, BindingResult bs, ModelMap m) {

		if (bs.hasErrors()) {
			m.addAttribute("error", "Invalid Category required");
			return "update_Course";
		}

		int i = repository.updateCourse(course);
		if (i == 0) {
			m.addAttribute("errorMessage", "Course Update Failed!!");
			return "update_Course";
		}

		m.addAttribute("successMessage", "Update Course Successfully!!");
		return "redirect:/courselist";

	}
*/


}
