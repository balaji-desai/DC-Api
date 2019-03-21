package dc.businessmodel;

import java.util.Date;
import java.util.List;

public class ResultModel {
	private int InstituteId;
	private int YearId;
	private int SemisterId;
	private int DepartmentId;
	private int SubjectId;
	private Date LaunchDateModel;
	private DateModel LaunchDate;
	private String ExamSeatNoStr;
	private List<ResultModel> SubjectList;

	public Date getLaunchDateModel() {
		return LaunchDateModel;
	}
	public DateModel getLaunchDate() {
		return LaunchDate;
	}
	public void setLaunchDateModel(Date launchDateModel) {
		LaunchDateModel = launchDateModel;
	}
	public void setLaunchDate(DateModel launchDate) {
		LaunchDate = launchDate;
	}
	
	public int getInstituteId() {
		return InstituteId;
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
	public int getSubjectId() {
		return SubjectId;
	}
	public String getExamSeatNoStr() {
		return ExamSeatNoStr;
	}
	public List<ResultModel> getSubjectList() {
		return SubjectList;
	}
	public void setInstituteId(int instituteId) {
		InstituteId = instituteId;
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
	public void setSubjectId(int subjectId) {
		SubjectId = subjectId;
	}
	public void setExamSeatNoStr(String examSeatNoStr) {
		ExamSeatNoStr = examSeatNoStr;
	}
	public void setSubjectList(List<ResultModel> subjectList) {
		SubjectList = subjectList;
	}
}
