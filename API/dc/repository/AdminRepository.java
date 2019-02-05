package dc.repository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dc.businessmodel.AcademicYear;
import dc.businessmodel.AdminModel;
import dc.businessmodel.Department;
import dc.businessmodel.Faculty;
import dc.businessmodel.FacultyType;
import dc.businessmodel.FormType;
import dc.businessmodel.NotificationView;
import dc.businessmodel.Semister;
import dc.businessmodel.Staff;
import dc.businessmodel.StaffType;
import dc.businessmodel.Student;
import dc.utility.JDBCHelper;
import dc.utility.ResultSet;
@Singleton
public class AdminRepository {

	@Inject
	ObjectMapper mapper;
	
	public AdminModel GetAdminDetail(int instituteId, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		AdminModel inst = null;
		userparam.put("@p_InstituteId", instituteId);
		ResultSet resultset = helper.ExecuteResult("ADM_GetAdminDetail",userparam);
		if (resultset.results.size() != 0 ){
			inst = mapper.convertValue(resultset.results.get(0), AdminModel.class);
		}
		return inst;
	}
	
	public void UpdateProfile(int instituteId,AdminModel model, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_fullname", model.getFullName());
		userparam.put("@p_email", model.getEmail());
		userparam.put("@p_contactno", model.getContactNo());
		helper.Execute("ADM_UpdateProfile",userparam);
	}
	
	public List<Department> GetDepartmentsWithoutProxy(int instituteId, JDBCHelper helper) throws SQLException{
		List<Department> department = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		TypeReference<List<Department>> ref=new TypeReference<List<Department>>(){};
		ResultSet resultset = helper.ExecuteResult("INST_GetDepartmentsWithoutProxy",userparam);
		if(resultset.results.size() != 0)
		{
			department =  mapper.convertValue(resultset.results,ref);
		}
		return department;
	}
	
	public List<Department> GetDepartments(int instituteId, JDBCHelper helper) throws SQLException{
		List<Department> department = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		TypeReference<List<Department>> ref=new TypeReference<List<Department>>(){};
		ResultSet resultset = helper.ExecuteResult("INST_GetDepartments",userparam);
		if(resultset.results.size() != 0)
		{
			department =  mapper.convertValue(resultset.results,ref);
		}
		return department;
	}
	
	public List<AcademicYear> GetYears(int instituteId, JDBCHelper helper) throws SQLException{
		List<AcademicYear> year = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		TypeReference<List<AcademicYear>> ref=new TypeReference<List<AcademicYear>>(){};
		ResultSet resultset = helper.ExecuteResult("INST_GetYears",userparam);
		if(resultset.results.size() != 0)
		{
			year =  mapper.convertValue(resultset.results,ref);
		}
		return year;
	}
	
	public List<AcademicYear> GetDeptYears(int instituteId, JDBCHelper helper) throws SQLException{
		List<AcademicYear> year = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		TypeReference<List<AcademicYear>> ref=new TypeReference<List<AcademicYear>>(){};
		ResultSet resultset = helper.ExecuteResult("INST_GetYears",userparam);
		if(resultset.results.size() != 0)
		{
			year =  mapper.convertValue(resultset.results,ref);
		}
		return year;
	}
	
	public List<Faculty> GetFaculty(int instituteId, JDBCHelper helper) throws SQLException{
		List<Faculty> student = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		TypeReference<List<Faculty>> ref=new TypeReference<List<Faculty>>(){};
		ResultSet resultset = helper.ExecuteResult("INST_GetFaculty",userparam);
		if(resultset.results.size() != 0)
		{
			student =  mapper.convertValue(resultset.results,ref);
		}
		return student;
	}
	public List<Staff> GetStaff(int instituteId, JDBCHelper helper) throws SQLException{
		List<Staff> student = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		TypeReference<List<Staff>> ref=new TypeReference<List<Staff>>(){};
		ResultSet resultset = helper.ExecuteResult("INST_GetStaff",userparam);
		if(resultset.results.size() != 0)
		{
			student =  mapper.convertValue(resultset.results,ref);
		}
		return student;
	}
	public List<Student> GetStudents(int instituteId, JDBCHelper helper) throws SQLException{
		List<Student> student = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		TypeReference<List<Student>> ref=new TypeReference<List<Student>>(){};
		ResultSet resultset = helper.ExecuteResult("INST_GetStudents",userparam);
		if(resultset.results.size() != 0)
		{
			student =  mapper.convertValue(resultset.results,ref);
		}
		return student;
	}
	
	public void CreateStudent(Student student, JDBCHelper helper) throws SQLException{
		
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", student.getInstituteId());
		userparam.put("@p_fullname", student.getFullName());
		userparam.put("@p_departmentid", student.getDepartmentId());
		userparam.put("@p_yearid", student.getYearId());
		userparam.put("@p_contactno", student.getContactNo());
		userparam.put("@p_email", student.getEmail());
		userparam.put("@p_address", student.getAddress());
		helper.Execute("STD_CreateStudent", userparam);
		
	}
	
	public List<FacultyType> getFacultyType(int instituteId, JDBCHelper helper) throws SQLException
	{
		List<FacultyType> facultytype = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		TypeReference<List<FacultyType>> ref=new TypeReference<List<FacultyType>>(){};
		ResultSet resultset = helper.ExecuteResult("INST_getFacultyType",userparam);
		if(resultset.results.size() != 0)
		{
			facultytype =  mapper.convertValue(resultset.results,ref);
		}
		return facultytype;
	}
	
	public List<FormType> getFormType(JDBCHelper helper) throws SQLException
	{
		List<FormType> FormType = null;
		TypeReference<List<FormType>> ref=new TypeReference<List<FormType>>(){};
		ResultSet resultset = helper.ExecuteResult("INST_getFormType");
		if(resultset.results.size() != 0)
		{
			FormType =  mapper.convertValue(resultset.results,ref);
		}
		return FormType;
	}

	public List<StaffType> getStaffType(int instituteId, JDBCHelper helper) throws SQLException
	{
		List<StaffType> stafftype = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		TypeReference<List<StaffType>> ref=new TypeReference<List<StaffType>>(){};
		ResultSet resultset = helper.ExecuteResult("INST_getStaffType",userparam);
		if(resultset.results.size() != 0)
		{
			stafftype =  mapper.convertValue(resultset.results,ref);
		}
		return stafftype;
	}
	
	public List<Semister> getSemister(int instituteId, JDBCHelper helper) throws SQLException
	{
		List<Semister> stafftype = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		TypeReference<List<Semister>> ref=new TypeReference<List<Semister>>(){};
		ResultSet resultset = helper.ExecuteResult("INST_getSemister",userparam);
		if(resultset.results.size() != 0)
		{
			stafftype =  mapper.convertValue(resultset.results,ref);
		}
		return stafftype;
	}
	
	public void CreateFaculty(Faculty faculty, JDBCHelper helper) throws SQLException{
		
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", faculty.getInstituteId());
		userparam.put("@p_fullname", faculty.getFullName());
		userparam.put("@p_departmentid", faculty.getDepartmentId());
		userparam.put("@p_qualification", faculty.getQualification());
		userparam.put("@p_contactno", faculty.getContactNo());
		userparam.put("@p_emailid", faculty.getEmailId());
		userparam.put("@p_facultytypeid", faculty.getFacultyTypeId());
		userparam.put("@p_address", faculty.getAddress());
		helper.Execute("FCT_CreateFaculty", userparam);
		
	}
	
	public void CreateStaff(Staff staff, JDBCHelper helper) throws SQLException{
		
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", staff.getInstituteId());
		userparam.put("@p_fullname", staff.getFullName());
		userparam.put("@p_departmentid", staff.getStaffTypeId());
		userparam.put("@p_contactno", staff.getContactNo());
		userparam.put("@p_emailid", staff.getEmailId());
		userparam.put("@p_address", staff.getAddress());
		userparam.put("@p_stafftypeid", staff.getStaffTypeId());
		helper.Execute("FCT_CreateStaff", userparam);
		
	}
	
	public NotificationView ViewNotification(int instituteId,int notificationviewId, JDBCHelper helper) throws SQLException{
		NotificationView notification = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_notificationviewid", notificationviewId);
		ResultSet result = helper.ExecuteResult("NOTIF_ViewNotification", userparam);
		if(result.results.size() != 0)
		{
			notification = mapper.convertValue(result.results.get(0), NotificationView.class);
		}
		return notification;
		
	}
	
	public InputStream  getLogo(int instituteId, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		ResultSet result = helper.ExecuteResult("INST_getLogo", userparam);
		if(result.results.size() != 0)
		{
			return new ByteArrayInputStream((byte[])result.results.get(0).get("Logo"));
		}
		return null;
		
	}
}
