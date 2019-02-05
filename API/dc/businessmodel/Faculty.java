package dc.businessmodel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class Faculty {
	private int FacultyId;
	@NotNull(message="FullName Requierd")
	private String FullName;
	@NotNull(message="Contact No Requierd")
	private String ContactNo;
	@NotNull(message="EmailId Requierd")
	@Email
	private String EmailId;
	@NotNull(message="Address Requierd")
	private String Address;
	@NotNull(message="Qualification Requierd")
	private String Qualification;
	private int FacultyTypeId;
	private String FacultyTypeName;
	public String getFacultyTypeName() {
		return FacultyTypeName;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public void setFacultyTypeName(String facultyTypeName) {
		FacultyTypeName = facultyTypeName;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	private String StaffTypeName;
	private int DepartmentId;
	private String DepartmentName;
	private int InstituteId;
	private int DCUserId;
	public int getFacultyId() {
		return FacultyId;
	}
	public String getFullName() {
		return FullName;
	}
	public String getContactNo() {
		return ContactNo;
	}
	public String getEmailId() {
		return EmailId;
	}
	public String getAddress() {
		return Address;
	}
	public String getQualification() {
		return Qualification;
	}
	public int getFacultyTypeId() {
		return FacultyTypeId;
	}
	public int getDepartmentId() {
		return DepartmentId;
	}
	public int getInstituteId() {
		return InstituteId;
	}
	public int getDCUserId() {
		return DCUserId;
	}
	public void setFacultyId(int facultyId) {
		FacultyId = facultyId;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
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
	public void setQualification(String qualification) {
		Qualification = qualification;
	}
	public void setFacultyTypeId(int facultyTypeId) {
		FacultyTypeId = facultyTypeId;
	}
	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
	}
	public void setInstituteId(int instituteId) {
		InstituteId = instituteId;
	}
	public void setDCUserId(int dCUserId) {
		DCUserId = dCUserId;
	}
	public String getStaffTypeName() {
		return StaffTypeName;
	}
	public void setStaffTypeName(String staffTypeName) {
		StaffTypeName = staffTypeName;
	}
}
