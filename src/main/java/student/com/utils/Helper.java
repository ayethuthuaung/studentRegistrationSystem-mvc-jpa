package student.com.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import student.com.models.UserBean;
import student.com.repository.UserRepository;

@Service
@AllArgsConstructor
public class Helper {
	@Autowired
	private final  UserRepository userRepo;
	
	
	public  String getAdminCode() {
		String studentCode = "";
		System.out.println("Hi");
		UserBean userBean = userRepo.getLastAdminCode();
		String lastCode = null;
		if (userBean != null) {
			 lastCode = userBean.getCode();
		}
		
		if ( lastCode == null) {
		    lastCode = "AD000";

		}
		System.out.println("lastCode: " + lastCode);
		int numericPart = Integer.parseInt(lastCode.substring(2))+1;
		studentCode = String.format("AD%03d", numericPart);
		return studentCode;
	}
	
//	public static String getCourseCode() {
//		String courseCode = "";
//		String lastCode = courseDao.getLastCourseCode();
//		if (lastCode == "" || lastCode.equals("")) {
//		    lastCode = "AI000";
//
//		}
//		int numericPart = Integer.parseInt(lastCode.substring(2))+1;
//		courseCode = String.format("AI%03d", numericPart);
//		return courseCode;
//	}
//
//	
//	
//	public static String getStudentCode() {
//		String studentCode = "";
//		System.out.println("Hi");
//		String lastCode = studentDao.getLastStudentCode();
//		if (lastCode == "" || lastCode.equals("") || lastCode == null) {
//		    lastCode = "STU000";
//
//		}
//		System.out.println("lastCode: " + lastCode);
//		int numericPart = Integer.parseInt(lastCode.substring(3))+1;
//		studentCode = String.format("STU%03d", numericPart);
//		return studentCode;
//	}
}
