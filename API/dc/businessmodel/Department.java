package dc.businessmodel;

public class Department {
	public int getDepartmentId() {
		return DepartmentId;
	}
	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getYearId() {
		return YearId;
	}
	public void setYearId(String yearId) {
		YearId = yearId;
	}
	private int DepartmentId;
	private String Name;
	private String YearId;
}
