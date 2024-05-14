package student.com.models;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="otp")
public class OTP implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	private int otp;
	private Timestamp otpCreate;
	private Timestamp otpExpired;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public Timestamp getOtpCreate() {
		return otpCreate;
	}
	public void setOtpCreate(Timestamp otpCreate) {
		this.otpCreate = otpCreate;
	}
	public Timestamp getOtpExpired() {
		return otpExpired;
	}
	public void setOtpExpired(Timestamp otpExpired) {
		this.otpExpired = otpExpired;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
