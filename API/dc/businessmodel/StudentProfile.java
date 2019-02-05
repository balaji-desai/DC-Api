package dc.businessmodel;

public class StudentProfile {
	private int StudentId;
	private String FullName;
	private String YearName;
	private String SemisterName;
	private String DepartmentName;
	private String Status;
	private String ContactNo;
	private String Email;
	private String Address;
	private InstituteModel Institute;
	public String getFullName() {
		return FullName;
	}
	public String getYearName() {
		return YearName;
	}
	public String getSemisterName() {
		return SemisterName;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public String getStatus() {
		return Status;
	}
	public String getContactNo() {
		return ContactNo;
	}
	public String getEmail() {
		return Email;
	}
	public String getAddress() {
		return Address;
	}
	public InstituteModel getInstitute() {
		return Institute;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public void setYearName(String yearName) {
		YearName = yearName;
	}
	public void setSemisterName(String semisterName) {
		SemisterName = semisterName;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public void setInstitute(InstituteModel institute) {
		Institute = institute;
	}
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
}
