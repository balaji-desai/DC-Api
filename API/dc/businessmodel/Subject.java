package dc.businessmodel;

public class Subject {
	private int SubjectId;
	private String Name;
	private int YearId;
	private String YearName;
	private int SemisterId;
	private String SemisterName;
	private boolean Evaluation;
	private int DepartmentId;
	private int InstituteId;
	
	public int getInstituteId() {
		return InstituteId;
	}
	public void setInstituteId(int instituteId) {
		InstituteId = instituteId;
	}
	public int getSubjectId() {
		return SubjectId;
	}
	public String getName() {
		return Name;
	}
	public int getYearId() {
		return YearId;
	}
	public int getSemisterId() {
		return SemisterId;
	}
	public boolean isEvaluation() {
		return Evaluation;
	}
	public int getDepartmentId() {
		return DepartmentId;
	}
	public void setSubjectId(int subjectId) {
		SubjectId = subjectId;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setYearId(int yearId) {
		YearId = yearId;
	}
	public void setSemisterId(int semisterId) {
		SemisterId = semisterId;
	}
	public void setEvaluation(boolean evaluation) {
		Evaluation = evaluation;
	}
	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
	}
	public String getYearName() {
		return YearName;
	}
	public String getSemisterName() {
		return SemisterName;
	}
	public void setYearName(String yearName) {
		YearName = yearName;
	}
	public void setSemisterName(String semisterName) {
		SemisterName = semisterName;
	}
}
