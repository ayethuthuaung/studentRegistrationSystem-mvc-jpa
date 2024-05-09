package student.com.controllers;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import student.com.dto.UserDto;
import student.com.models.UserBean;
import student.com.service.UserService;
import student.com.utils.Helper;



@Controller
@AllArgsConstructor
public class UserController {
	
	@Autowired
	UserService  userService;
	@Autowired
	private final Helper helper;
	
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/addAdmin")
	public ModelAndView userCreate(Model model) {
		model.addAttribute("adminCode", helper.getAdminCode());
		return new ModelAndView("adminRegister","userDto",new UserDto());
	}
	
	@PostMapping("/addAdmin")
	public String userCreate(@ModelAttribute("userDto") @Validated UserDto userDto , BindingResult bs, ModelMap model, HttpSession session) {
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
		return "redirect:/";
	}

}
