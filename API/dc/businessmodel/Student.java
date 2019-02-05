package dc.businessmodel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class Student {
	private int StudentId;
	@NotNull(message="FullName Requierd")
	private String FullName;
	private int YearId;
	private String YearName;
	private int SemisterId;
	private String SemisterName;
	private int DepartmentId;
	private String DepartmentName;
	private int ProxyDepartmentId;
	private int FutureYearId;
	private int StatusId;
	private String Status;
	private int FutureStatusId;
	private String Note;
	private boolean ACK;
	private int InstituteId;
	@NotNull(message="ContactNo Requierd")
	private String ContactNo;
	@NotNull(message="Email Requierd")
	@Email(message = "Invalid Email Format.")
	private String Email;
	@NotNull(message="Address Requierd")
	private String Address;
	public int getStudentId() {
		return StudentId;
	}
	public String getFullName() {
		return FullName;
	}
	public int getYearId() {
		return YearId;
	}
	public String getYearName() {
		return YearName;
	}
	public int getSemisterId() {
		return SemisterId;
	}
	public String getSemisterName() {
		return SemisterName;
	}
	public int getDepartmentId() {
		return DepartmentId;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public int getProxyDepartmentId() {
		return ProxyDepartmentId;
	}
	public int getFutureYearId() {
		return FutureYearId;
	}
	public int getStatusId() {
		return StatusId;
	}
	public String getStatus() {
		return Status;
	}
	public int getFutureStatusId() {
		return FutureStatusId;
	}
	public String getNote() {
		return Note;
	}
	public boolean isACK() {
		return ACK;
	}
	public int getInstituteId() {
		return InstituteId;
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
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public void setYearId(int yearId) {
		YearId = yearId;
	}
	public void setYearName(String yearName) {
		YearName = yearName;
	}
	public void setSemisterId(int semisterId) {
		SemisterId = semisterId;
	}
	public void setSemisterName(String semisterName) {
		SemisterName = semisterName;
	}
	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	public void setProxyDepartmentId(int proxyDepartmentId) {
		ProxyDepartmentId = proxyDepartmentId;
	}
	public void setFutureYearId(int futureYearId) {
		FutureYearId = futureYearId;
	}
	public void setStatusId(int statusId) {
		StatusId = statusId;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public void setFutureStatusId(int futureStatusId) {
		FutureStatusId = futureStatusId;
	}
	public void setNote(String note) {
		Note = note;
	}
	public void setACK(boolean aCK) {
		ACK = aCK;
	}
	public void setInstituteId(int instituteId) {
		InstituteId = instituteId;
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
}
