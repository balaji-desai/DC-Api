package dc.businessmodel;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class FormModel {
	private int FormId;
	@NotNull(message = "Title Requierd")
	private String Title;
	private int YearId;
	private String YearName;
	private int DepartmentId;
	private String DepartmentName;
	private DateModel FillStartDate;
	private DateModel FillEndDate;
	private Date FillStartDateModel;
	private Date FillEndDateModel;
	private int FormTypeId;
	private String FormTypeName;
	private int SemisterId;
	private String SemisterName;
	private int InstituteId;
	
	public int getFormId() {
		return FormId;
	}
	public String getTitle() {
		return Title;
	}
	public int getYearId() {
		return YearId;
	}
	public String getYearName() {
		return YearName;
	}
	public int getDepartmentId() {
		return DepartmentId;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public DateModel getFillStartDate() {
		return FillStartDate;
	}
	public DateModel getFillEndDate() {
		return FillEndDate;
	}
	public Date getFillStartDateModel() {
		return FillStartDateModel;
	}
	public Date getFillEndDateModel() {
		return FillEndDateModel;
	}
	public int getFormTypeId() {
		return FormTypeId;
	}
	public String getFormTypeName() {
		return FormTypeName;
	}
	public int getSemisterId() {
		return SemisterId;
	}
	public String getSemisterName() {
		return SemisterName;
	}
	public int getInstituteId() {
		return InstituteId;
	}
	public void setFormId(int formId) {
		FormId = formId;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public void setYearId(int yearId) {
		YearId = yearId;
	}
	public void setYearName(String yearName) {
		YearName = yearName;
	}
	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	public void setFillStartDate(DateModel fillStartDate) {
		FillStartDate = fillStartDate;
	}
	public void setFillEndDate(DateModel fillEndDate) {
		FillEndDate = fillEndDate;
	}
	public void setFillStartDateModel(Date fillStartDateModel) {
		FillStartDateModel = fillStartDateModel;
	}
	public void setFillEndDateModel(Date fillEndDateModel) {
		FillEndDateModel = fillEndDateModel;
	}
	public void setFormTypeId(int formTypeId) {
		FormTypeId = formTypeId;
	}
	public void setFormTypeName(String formTypeName) {
		FormTypeName = formTypeName;
	}
	public void setSemisterId(int semisterId) {
		SemisterId = semisterId;
	}
	public void setSemisterName(String semisterName) {
		SemisterName = semisterName;
	}
	public void setInstituteId(int instituteId) {
		InstituteId = instituteId;
	}
}
