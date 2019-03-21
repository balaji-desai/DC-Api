package dc.businessmodel;

public class HallTicketInfo {
private int InstituteId;
private int StudentId;
private int YearId;
private int SemisterId;
private int DepartmentId;
private String HallticketNo;

public int getInstituteId() {
	return InstituteId;
}
public int getStudentId() {
	return StudentId;
}
public int getYearId() {
	return YearId;
}
public int getSemisterId() {
	return SemisterId;
}
public int getDepartmentId() {
	return DepartmentId;
}
public String getHallticketNo() {
	return HallticketNo;
}
public void setInstituteId(int instituteId) {
	InstituteId = instituteId;
}
public void setStudentId(int studentId) {
	StudentId = studentId;
}
public void setYearId(int yearId) {
	YearId = yearId;
}
public void setSemisterId(int semisterId) {
	SemisterId = semisterId;
}
public void setDepartmentId(int departmentId) {
	DepartmentId = departmentId;
}
public void setHallticketNo(String hallticketNo) {
	HallticketNo = hallticketNo;
}
}
