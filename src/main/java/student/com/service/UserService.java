package student.com.service;
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
		return modelMapper.map(userRepo.findByEmail(email), UserDto.class);
	}
	

}

