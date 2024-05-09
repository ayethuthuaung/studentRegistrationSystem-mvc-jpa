package student.com.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.com.models.UserBean;
import student.com.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public int insertData(UserBean userBean) {
		return userRepo.insertData(userBean);
	}

}

