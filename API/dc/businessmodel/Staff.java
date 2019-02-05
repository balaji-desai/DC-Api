package dc.businessmodel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class Staff {
	private int StaffId;
	@NotNull(message="FullName Requierd")
	private String FullName;
	@NotNull(message="Contact No. Requierd")
	private String ContactNo;
	@NotNull(message="EmailId Requierd")
	@Email(message="Invalid Email.")
	private String EmailId;
	@NotNull(message="Address Requierd")
	private String Address;
	private int StaffTypeId;
	private String StaffTypeName;
	public String getStaffTypeName() {
		return StaffTypeName;
	}
	public void setStaffTypeName(String staffTypeName) {
		StaffTypeName = staffTypeName;
	}
	private int InstituteId;
	private int DCUserId;
	public int getDCUserId() {
		return DCUserId;
	}
	public void setDCUserId(int dCUserId) {
		DCUserId = dCUserId;
	}
	public int getStaffId() {
		return StaffId;
	}
	public String getFullName() {
		return FullName;
	}
	public String getEmailId() {
		return EmailId;
	}
	public String getAddress() {
		return Address;
	}
	public int getStaffTypeId() {
		return StaffTypeId;
	}
	public int getInstituteId() {
		return InstituteId;
	}
	public void setStaffId(int staffId) {
		StaffId = staffId;
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
	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public void setStaffTypeId(int staffTypeId) {
		StaffTypeId = staffTypeId;
	}
	public void setInstituteId(int instituteId) {
		InstituteId = instituteId;
	}
}
