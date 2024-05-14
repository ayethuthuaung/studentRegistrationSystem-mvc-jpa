package student.com.controllers;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import lombok.AllArgsConstructor;
import student.com.dto.StudentDto;
import student.com.dto.UserDto;
import student.com.models.Email;
import student.com.models.NPassBean;
import student.com.models.OTP;
import student.com.models.UserBean;
import student.com.models.checkOTP;
import student.com.repository.UserRepository;
import student.com.service.UserService;
import student.com.utils.Helper;



@Controller
@AllArgsConstructor
public class UserController {
	
	@Autowired
	UserService  userService;
	
	@Autowired
	private final Helper helper;
	
	@Autowired
	UserRepository repo;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@GetMapping("/login")
	public ModelAndView loginPage(Model model) {
		
		return new ModelAndView("login","userDto",new UserDto());
	}
	
//	@GetMapping("/forgotPassword")
//	public ModelAndView forgotPasswordPage(Model model) {
//		
//		return new ModelAndView("forgotPassword","userDto",new UserDto());
//	}
//	@PostMapping("/forgotPassword")
//	public String checkEmailExistForForgotPassword(@ModelAttribute("userDto") UserDto userDto, Model model) {
//		if(userService.isEmailExists(userDto.getEmail())) {
//			model.addAttribute("correctEmail", Helper.generateOTP());
//			return "forgotPassword";
//		}
//		model.addAttribute("emailError", "Please enter the correct email.");
//		return "forgotPassword";
//	}
//	
	@PostMapping("/login")
    public String login(@ModelAttribute("userDto") UserDto userDto, HttpSession session, ModelMap model) {
		System.out.println("hi");
    	if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            
            model.addAttribute("loginError", "Email is required.");            
            return "login"; 
        }
    	if(userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
    		model.addAttribute("loginError", "Password is required.");            
            return "login"; 
    	}
    	
       
        UserDto loggedInUser = userService.getUserByEmail(userDto.getEmail());
        System.out.println("hi");
       
        if (loggedInUser != null && loggedInUser.getPassword().equals(userDto.getPassword())) {
        	 if (loggedInUser.isDeleted()) {
                 
                 model.addAttribute("loginError", "This account is disabled.");
                 return "login";
                 
             } 
        	session.setAttribute("loggedInUser", loggedInUser);
            if (!loggedInUser.getRole().equalsIgnoreCase("user")) {
            	System.out.println("hi");
                return "redirect:/dashboard";
            } else if (loggedInUser.getRole().equalsIgnoreCase("user")) {               
                return "redirect:/home";             
            }
               
              
            
        } else {
            // Authentication failed
            if (loggedInUser == null) {
            	System.out.println("");
                model.addAttribute("loginError", "Invalid email or password.");
            } 
            System.out.println("wrong");
            model.addAttribute("loginError", "Invalid email or password.");
            return "login"; 
        }       
        System.out.println("wrong");
        model.addAttribute("loginError", "Invalid email or password.");
        return "login";
    }	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}
	
	@GetMapping("/addAdmin")
	public ModelAndView userCreate(Model model) {
		System.out.println("Hi Admin");
		model.addAttribute("adminCode", helper.getAdminCode());
		return new ModelAndView("adminRegister","userDto",new UserDto());
	}
	
	@PostMapping("/addAdmin")
	public String userCreate(@ModelAttribute("userDto") @Validated UserDto userDto , BindingResult bs, ModelMap model, HttpSession session) {
		System.out.println(userDto.getPassword());
		if(bs.hasErrors()) {
			return "adminRegister";
		}
		else {
			if (!userDto.getEmail().toLowerCase().endsWith("@gmail.com")) {
                bs.rejectValue("email", "email.invalid", "Only Gmail addresses are allowed");
                return "adminRegister";
            }
			ModelMapper modelMapper = new ModelMapper();
			userDto.setRole("admin");
			int i = userService.insertData(modelMapper.map(userDto, UserBean.class));
			if(i == 0) {
				model.addAttribute("error","Add Fail!!");
				return "adminRegister";
			}
		}
		return "redirect:/viewAdmin";
	}
	//displayUser
		@GetMapping("/viewAdmin")
		  public String displayAllUser(ModelMap model) {
			System.out.println("ViewAdmin");
		    List<UserDto> list = userService.getAllUser();
		    if (list.size() == 0) {
		      model.addAttribute("msg", "  Data not Found");
		    } else {
		      model.addAttribute("userList", list);

		    }
		    return "viewUser";

		  }

		//updateUser
		  @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
		  public ModelAndView updateUser(@PathVariable int id) {

		    UserDto userDto = userService.getUserById(id);
		    ModelAndView model = new ModelAndView("updateUser");
		    model.addObject("userDto", userDto);
		    return model;
		  }

		  //updateUser
		  @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
		  public String updateUser(@ModelAttribute("userDto") @Validated UserDto userDto, BindingResult bs, ModelMap model, HttpServletRequest request) {
		      if (bs.hasErrors()) {
		          return "updateUser";
		      }
		      try {
		          // Retrieve user ID from the request
		          int id = Integer.parseInt(request.getParameter("id"));
		          userDto.setId(id); // Set the ID in the UserBean object
		          int i = userService.update(userDto);
		          if (i == 0) { 
		              model.addAttribute("error", "Update failed. User not found or an error occurred.");
		              return "updateUser";
		          }
		      } catch (Exception e) {
		          model.addAttribute("error", "An unexpected error occurred. Please try again later.");
		          return "updateUser";
		      }
		      return "redirect:/viewAdmin";
		  }
		  
		  //deleteUser
		  @GetMapping("/deleteUser/{id}")
		    public String deleteUser(@PathVariable int id, ModelMap model) {
		      int i = userService.softDeleteData(id);
		      if (i == 0) {
		            model.addAttribute("error", "Delete Fail!!");
		        }
		        return "redirect:/viewAdmin";
		    }
		  
		  private String generateOTP() {
		        // Generate a random 6-digit OTP
		        int otp = (int) (Math.random() * 900000) + 100000;
		        return String.valueOf(otp);
		    }

		    // Method to send OTP via email
		    private void sendOTPEmail(String userEmail, String otp) {
		    	
		        SimpleMailMessage message = new SimpleMailMessage();
		        message.setTo(userEmail);
		        message.setSubject("Password Reset OTP");
		        message.setText("Your OTP for password reset is: " + otp);
		        mailSender.send(message);
		    }
		  
		  @RequestMapping(value="/email",method=RequestMethod.GET)
				public ModelAndView email() {
					return new ModelAndView("email","bean",new Email());
				}
				
				@RequestMapping(value="/emailProcess",method=RequestMethod.POST)
				public String emailProcess(@ModelAttribute("bean") @Validated Email email,BindingResult bs,ModelMap m,HttpSession session) {
					UserBean userdto = repo.getEmail(email);	
					session.setAttribute("otpemail", userdto.getEmail());
					
					if(!email.getEmail().equals(userdto.getEmail())) {
						m.addAttribute("error", "Your email is wrong!");
				        return "email";
				    }
					
					else {
						
						String otp = generateOTP(); 
				        sendOTPEmail(email.getEmail(), otp);
				        OTP otpEntity = new OTP();
			            otpEntity.setOtp(Integer.parseInt(otp));
			            otpEntity.setOtpCreate(new Timestamp(System.currentTimeMillis()));
			            otpEntity.setOtpExpired(new Timestamp(System.currentTimeMillis() + (5 * 60 * 1000)));
			            otpEntity.setEmail(email.getEmail());
				        int result = repo.insertOTP(otpEntity); 
				        if(result == 1) {
				            session.setAttribute("otp", otpEntity);
				            return "redirect:/otp"; 
				        } 
				        
				        else {
				           
				            m.addAttribute("error", "Failed to insert OTP. Please try again.");
				            return "email";
				        }
					
					
			    }
					
				}
				
				
			
			    @RequestMapping(value="/otp",method=RequestMethod.GET)
				public ModelAndView otp() {
					return new ModelAndView("otp","bean",new checkOTP());
				}
			    
			    @RequestMapping(value="/otpProcess",method=RequestMethod.POST)
				public String otpProcess(@ModelAttribute("bean") @Validated checkOTP otp,BindingResult bs,ModelMap m,HttpSession session) {
					OTP otpdto = repo.getOTP(otp);
					System.out.print(otpdto.getOtp());
					System.out.print(otp.getOtp());
					
					if(otp.getOtp() != otpdto.getOtp()) {
						m.addAttribute("error", "Your otp is wrong!");
				        return "otp";
				    }
					
					else {
						return "redirect:/newpass";
			
					
					
			    }
					
				}
			    
			    @RequestMapping(value = "/newpass", method = RequestMethod.GET)
			 	public ModelAndView newpass() {

			 		return new ModelAndView("forgotpass", "nbean", new NPassBean());
			 	}
			    @RequestMapping(value = "/newpassprogress", method = RequestMethod.POST)
			    public String newPassProgress(@ModelAttribute("nbean") @Validated NPassBean nbean, BindingResult bs, ModelMap model, HttpSession session) {
			        if (bs.hasErrors()) {
			            System.out.print("Error Code: 1111");
			            return "forgotpass";
			        }

			        UserRepository userRepository = new UserRepository(); 

			        boolean isPasswordUpdated = userRepository.updatePassword((String) session.getAttribute("otpemail"), nbean.getPassword());

			        if (isPasswordUpdated) {
			           
			            return "redirect:/login"; 
			        } else {
			            
			            model.addAttribute("error", "Password update failed. Please try again.");
			            return "newpass";
			        }
			    }

}
