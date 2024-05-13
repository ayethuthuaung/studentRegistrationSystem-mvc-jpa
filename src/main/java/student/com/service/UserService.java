package student.com.service;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.com.dto.UserDto;
import student.com.models.UserBean;
import student.com.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public int insertData(UserBean userBean) {
		return userRepo.insertData(userBean);
	}
	
	public UserDto getUserByEmail(String email) {
		ModelMapper modelMapper = new ModelMapper();
		UserBean authenticatedUser = userRepo.findByEmail(email);
		UserDto authenticatedUserDto = null; 
		if(authenticatedUser != null) 
			System.out.println(authenticatedUser.toString());
			authenticatedUserDto = modelMapper.map(authenticatedUser, UserDto.class); 
		return authenticatedUserDto;
	}

	public long getUserCount() {		
		System.out.println(userRepo.getUserCount());
		return userRepo.getUserCount();
	}
	
	
	public List<UserDto> getAllUser() {
	    List<UserBean> userList = userRepo.selectAll();
	    List<UserDto> userDtoList = new ArrayList<>();
	    ModelMapper modelMapper = new ModelMapper();
	    for(UserBean userBean : userList) {    
	      UserDto userDto = modelMapper.map(userBean, UserDto.class);
	      userDtoList.add(userDto);
	    }
	    return userDtoList;
	  }
	
	
	  
	//update
	  public int update(UserDto userDto) {
	    ModelMapper modelMapper = new ModelMapper();
	    UserBean userBean = modelMapper.map(userDto, UserBean.class);
	    return userRepo.updateData(userBean);
	  }
	  
	  //selectone
	  public UserBean selectOne(int id) {
	        return userRepo.selectOne(id);
	    }
	  
	  //getuserbyid
	  public UserDto getUserById(int id) {
	    ModelMapper modelMapper = new ModelMapper();
	    UserBean userBean = userRepo.selectOne(id);
	    return modelMapper.map(userBean, UserDto.class);
	  }
	  
	  //softdelete
	  public int softDeleteData(int id) {
	        return userRepo.softDeleteData(id);
	    }

	 //emailDuplicate 
	  public boolean isEmailExists(String email) {
		    UserDto existingUser = getUserByEmail(email);
		    return existingUser != null;
		}


}

