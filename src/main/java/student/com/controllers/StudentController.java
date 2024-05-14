package student.com.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import student.com.dto.CourseDto;
import student.com.dto.StudentDto;
import student.com.models.CourseBean;
import student.com.models.StudentBean;
import student.com.service.CourseService;
import student.com.service.StudentService;
import student.com.utils.ProfileImageService;
import student.com.repository.StudentRepository;
@Component
@ComponentScan("student.com.service")
@Controller
public class StudentController {
  
  @Autowired
  private StudentService studentService;
  
  @Autowired
  private CourseService courseService;
  
  @Autowired
  private ServletContext servletContext;
  
  @Autowired
  private StudentRepository studentRepository;
  
  @RequestMapping(value="/addStudent",method=RequestMethod.GET)
  public ModelAndView addStudent(ModelMap m) {
    List<CourseDto> courseList = courseService.getAllCourse();
    m.addAttribute("courseList", courseList);    
    return new ModelAndView("addStudent", "studentDto", new StudentDto());
  }
  
  @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
  public String addStudentProcess(@ModelAttribute("studentDto") @Validated StudentDto studentDto, BindingResult bs,
          @RequestParam("courseId") String[] courseId, @RequestParam("photoImageInput") MultipartFile file, ModelMap m,
          HttpSession session) {
    
    List<CourseDto> courseList = courseService.getAllCourse();
    
    if(bs.hasErrors()) {
      m.addAttribute("courseList",courseList);
      m.addAttribute("error","Invalid Student required");
      return "addStudent";
    }
    
   
   try {  
    
     
     if(file != null && !file.isEmpty()) {    
       System.out.println("photo"+ studentDto.getPhotoImageInput());
       byte[] photoBytes = studentDto.getPhotoImageInput().getBytes();  
       System.out.println(photoBytes);
       studentDto.setPhoto(photoBytes);
       studentDto.setPhotoPath(file.getOriginalFilename());
     }else {
       m.addAttribute("courseList", courseList);
       m.addAttribute("error","Photo Required");
       return "addStudent";
     }
        
     
     
       int studentCount = studentService.getCount();
      int nextSequence = studentCount+1;
       String formattedStudentId = String.format("STU%03d", nextSequence);
       studentDto.setStudentId(formattedStudentId);

       Set<CourseBean> courses  = studentService.addCourseForStudent(courseId);
       if(courses != null) {
       studentDto.setCourses(courses);
       }
        int rs = studentService.addNewStudent(studentDto);
     
          if(rs == 0) {
             m.addAttribute("courseList",courseList);
              m.addAttribute("error", "Update Failed");
              return "addStudent";
          }

          return "redirect:/viewStudent";
   }catch(IOException e) {
       System.out.print(e);
        m.addAttribute("courseList",courseList);
          m.addAttribute("error", "An error occurred while updating. Please try again later.");
          return "addStudent";
   }   
   
  }
  
  @RequestMapping(value = "/viewStudent", method = RequestMethod.GET)
  public String displayAllStudent(ModelMap m, HttpServletRequest request) {
    List<StudentDto> list = studentService.getAllStudent();
    if (list.size() == 0) {
      m.addAttribute("msg", " Student Data not Found");
    } else {
      m.addAttribute("studentList", list);

    }
    
    //for report start
    List<StudentBean> lists = studentRepository.findAllStudents();
    m.addAttribute("list", lists);  
    request.setAttribute("list", lists);
    request.getServletContext().setAttribute("list", lists);    
    //for report end
    return "viewStudent";
}
  
  
  @GetMapping("status/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
		System.out.println("Hi"+id);
        boolean success = studentService.updateStatus(id, 1); // Set delete status to 1
        if (success) {
        
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete student");
        }
    } 
  
  @RequestMapping(value = "/deleteStudent/{id}",  method = RequestMethod.GET)
  public String deleteStudent(@PathVariable int id, ModelMap model) {
	  boolean success = studentService.updateStatus(id, 1); // Set delete status to 1
    
    if (!success) {
      model.addAttribute("error", "Delete Fail!!");
    }
    return "redirect:/viewStudent";
  }
    
  
  @RequestMapping(value="/updateStudent/{id}",method=RequestMethod.GET)
  public ModelAndView updateStudent(@PathVariable int id, ModelMap m, HttpSession session) {
	  
      // Get the student details by ID
      StudentDto studentDto = studentService.getStudentById(id);

      List<CourseDto> allCourses = courseService.getAllCourse();
      
      List<CourseDto> courseList = new ArrayList<>();
      
      ModelMapper modelMapper = new ModelMapper();
      Set<CourseBean> coursesOfStudent = studentDto.getCourses();
      Set<CourseDto> coursesDTOOfStudent = new HashSet<>();

      for (CourseBean course : coursesOfStudent) {
          CourseDto courseDto = modelMapper.map(course, CourseDto.class);
          coursesDTOOfStudent.add(courseDto);
      }

      
      for (CourseDto course : allCourses) {
          boolean isAttended = false;
          for (CourseDto attendedCourse : coursesDTOOfStudent) { 
              if (course.getCourseId().equals(attendedCourse.getCourseId())) {
                  isAttended = true;
                  break;
              }
          }
          if (!isAttended) {
              courseList.add(course);
          }
      }

      m.addAttribute("courseList", courseList);

      // Return the ModelAndView with the studentDto
      return new ModelAndView("updateStudent", "studentDto", studentDto);
  }

	
  
  @RequestMapping(value="/updateStudentProcess", method=RequestMethod.POST)
	public String updateStudentProcess(@ModelAttribute("studentDto") @Validated StudentDto studentDto,BindingResult bs, @RequestParam("courseId") String[] courseId,@RequestParam("photoImageInput") MultipartFile file,ModelMap m,HttpSession session) throws IOException {
	  
	  Set<CourseBean> courses  = studentService.addCourseForStudent(courseId);
      if(courses != null) {
      studentDto.setCourses(courses);
      }
		System.out.println("Hiupdate" + studentDto.toString());
		
		List<CourseDto> courseLists = courseService.getAllCourse();
		 List<CourseDto> courseList = new ArrayList<>();
		 StudentDto studentDtoRet = studentService.getStudentById(studentDto.getId());
	      ModelMapper modelMapper = new ModelMapper();
	      Set<CourseBean> coursesOfStudent = studentDtoRet.getCourses();
	      Set<CourseDto> coursesDTOOfStudent = new HashSet<>();

	      for (CourseBean course : coursesOfStudent) {
	          CourseDto courseDto = modelMapper.map(course, CourseDto.class);
	          coursesDTOOfStudent.add(courseDto);
	      }

	      
	      for (CourseDto course : courseLists) {
	          boolean isAttended = false;
	          for (CourseDto attendedCourse : coursesDTOOfStudent) { 
	              if (course.getCourseId().equals(attendedCourse.getCourseId())) {
	                  isAttended = true;
	                  break;
	              }
	          }
	          if (!isAttended) {
	              courseList.add(course);
	          }
	      }


		if(bs.hasErrors()) {
			m.addAttribute("courseLists",courseList);
			m.addAttribute("studentDto",studentDtoRet);
			m.addAttribute("error","Invalid Student required");
			return "updateStudent";
		}	
		
		 try {					 
			 
			 if(file != null && !file.isEmpty()) {		
				 
			
				 
				 System.out.println("photo"+ studentDto.getPhotoImageInput());
			       byte[] photoBytes = studentDto.getPhotoImageInput().getBytes();  
			       System.out.println(photoBytes);
			       studentDto.setPhoto(photoBytes);
			 }else {
				 System.out.println("Hi Image");
				 studentDto.setPhoto(ProfileImageService.convertStringToByteArray(studentDtoRet.getPhotoPath()));
			 }
		 } catch (IOException e) {
		        System.out.print(e);
		    }	 

	    
	    int rs = studentService.updateStudent(studentDto);
	    
	    if (rs == 0) {
	    	//StudentDto studentDtoRet = studentService.getStudentById(studentDto.getId());
	    	m.addAttribute("studentDto",studentDtoRet);
	    	m.addAttribute("courseList",courseList);	
	        m.addAttribute("error", "Update Failed");
	        return "updateStudent";
	    }
	    
	    return "redirect:/viewStudent";

	}

  
  //for report
  @GetMapping("/generateReport")
  @ResponseBody
  public void generateReport(HttpServletRequest request, HttpServletResponse response,
      @RequestParam("export") String export) throws IOException {
    String path = servletContext.getRealPath("/WEB-INF/jesper/stud_list.jrxml");
    JRBeanCollectionDataSource source = null;
    JasperReport jasperReport;
    JasperPrint print;
    ArrayList<StudentBean> lists = new ArrayList<StudentBean>();

    Object listAttribute = servletContext.getAttribute("list"); // Get the attribute from ServletContext
    if (listAttribute instanceof Vector) { // Check if the attribute is an instance of Vector
      Vector<StudentBean> vectorList = (Vector<StudentBean>) listAttribute; // Cast the attribute to Vector<User>
      lists = new ArrayList<>(vectorList); // Convert Vector to ArrayList
    } else if (listAttribute instanceof ArrayList) { // Check if the attribute is an ArrayList
      lists = (ArrayList<StudentBean>) listAttribute; // Cast the attribute to ArrayList<User>
    } else {
      // Handle the case where the attribute is not set or is not of the expected type
      System.out.println("Attribute 'list' is null or not an instance of Vector or ArrayList");
    }
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("ReportTitle", "Student_List");

    try {
      source = new JRBeanCollectionDataSource(lists);
      jasperReport = JasperCompileManager.compileReport(path);
      print = JasperFillManager.fillReport(jasperReport, parameters, source);

      if ("excel".equals(export)) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=student_list.xls");
        JRXlsExporter exporterXLS = new JRXlsExporter();
        exporterXLS.setExporterInput(new SimpleExporterInput(print));
        exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        exporterXLS.setConfiguration(configuration);
        exporterXLS.exportReport();
      } else {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=student_list.pdf");

        JRPdfExporter exporterPdf = new JRPdfExporter();
        exporterPdf.setExporterInput(new SimpleExporterInput(print));
        exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        exporterPdf.exportReport();
      }
    } catch (JRException e) {
      e.printStackTrace();
    }
  }
  
    

}