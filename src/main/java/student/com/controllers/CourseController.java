package student.com.controllers;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import student.com.dto.CourseDto;
import student.com.models.CourseBean;

import student.com.service.CourseService;
import student.com.service.UserService;




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
		return "redirect:/viewCourse";
	}

	@RequestMapping(value = "/viewCourse", method = RequestMethod.GET)
	public String displayAllCourse(ModelMap m) {
		List<CourseDto> list = courseService.getAllCourse();
		if (list.size() == 0) {
			m.addAttribute("msg", " Course Data not Found");
		} else {
			m.addAttribute("courseList", list);

		}
		return "viewCourse";

	}

	@RequestMapping(value = "/updateCourse/{id}", method = RequestMethod.GET)
	public ModelAndView updateCourse(@PathVariable int id) {
		System.out.println();
		CourseDto courseDto = courseService.getCourseById(id);
		ModelAndView m = new ModelAndView("updateCourse");
		m.addObject("courseDto", courseDto);
		return m;
	}

	@RequestMapping(value = "/updateCourse/{id}", method = RequestMethod.POST)
	public String updateCourseProcess(@ModelAttribute("courseDto") @Validated CourseDto courseDto, BindingResult bs, ModelMap m) {
		
		if (bs.hasErrors()) {
			m.addAttribute("error", "Invalid Category required");
			return "updateCourse";
		}

		int i = courseService.update(courseDto);
		if (i == 0) {
			m.addAttribute("errorMessage", "Course Update Failed!!");
			return "updateCourse";
		}

		m.addAttribute("successMessage", "Update Course Successfully!!");
		return "redirect:/viewCourse";

	}
	
	@RequestMapping(value = "/courseDetail", method = RequestMethod.GET)
	public ModelAndView courseDetail() {

		
		ModelAndView m = new ModelAndView("courseDetail");
		
		return m;
	}

	@RequestMapping(value = "/deleteCourse/{id}",  method = RequestMethod.GET)
    public String deleteCourse(@PathVariable int id, ModelMap model) {
		System.out.println("Hi C D");
      int i = courseService.deleteCourse(id);
      if (i == 0) {
        model.addAttribute("error", "Delete Fail!!");
      }
      return "redirect:/viewCourse";
    }

	@RequestMapping(value = "/courseDetail/{id}", method = RequestMethod.GET)
	  public String courseDetail(@PathVariable int id ,Model m) {
	    
	    CourseDto courseDto = courseService.getCourseById(id);
	    m.addAttribute("course", courseDto);
	    
	    return "courseDetail";
	  }     



}	
	
  
