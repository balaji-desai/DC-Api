package dc.businessmodel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class AdminModel {
	@NotNull(message="Full Name Requierd")
	private String FullName;
	@NotNull(message="Contact No Requierd")
	private String ContactNo;
	@NotNull(message="Contact No Requierd")
	@Email(message="Invalid Email")
	private String Email;
	private InstituteModel Institute;
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getContactNo() {
		return ContactNo;
	}
	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public InstituteModel getInstitute() {
		return Institute;
	}
	public void setInstitute(InstituteModel institute) {
		Institute = institute;
	}

}
