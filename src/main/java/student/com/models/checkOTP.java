package student.com.models;

import java.io.Serializable;

import jakarta.persistence.Entity;

@Entity
public class checkOTP implements Serializable{
	private int otp;

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}
	
	

}
