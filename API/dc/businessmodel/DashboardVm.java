package dc.businessmodel;

public class DashboardVm {
private boolean IsSetupComplete;
private int DepartmentId;
private int FacultyTypeId;

public int getFacultyTypeId() {
	return FacultyTypeId;
}

public void setFacultyTypeId(int facultyTypeId) {
	FacultyTypeId = facultyTypeId;
}

public int getDepartmentId() {
	return DepartmentId;
}

public void setDepartmentId(int departmentId) {
	DepartmentId = departmentId;
}

public boolean isIsSetupComplete() {
	return IsSetupComplete;
}

public void setIsSetupComplete(boolean isSetupComplete) {
	IsSetupComplete = isSetupComplete;
}
}
