package dc.businessmodel;

public class Semister {
	private int SemisterId;
	private String Name;
	private int YearId;
	private int InstituteId;
	
	public int getSemisterId() {
		return SemisterId;
	}
	public int getYearId() {
		return YearId;
	}
	public int getInstituteId() {
		return InstituteId;
	}
	public void setSemisterId(int semisterId) {
		SemisterId = semisterId;
	}
	public void setYearId(int yearId) {
		YearId = yearId;
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
