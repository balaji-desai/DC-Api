package dc.businessmodel;

public class AcademicYear {
	private int YearId;
	private int InstituteId;
	private String Name;
	public int getYearId() {
		return YearId;
	}
	public void setYearId(int yearId) {
		YearId = yearId;
	}
	public int getInstituteId() {
		return InstituteId;
	}
	public void setInstituteId(int instituteId) {
		InstituteId = instituteId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
}
