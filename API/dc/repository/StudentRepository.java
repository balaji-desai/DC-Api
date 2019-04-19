package dc.repository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dc.businessmodel.AdminModel;
import dc.businessmodel.DashboardVm;
import dc.businessmodel.NotificationView;
import dc.businessmodel.Student;
import dc.businessmodel.StudentDashboardVM;
import dc.businessmodel.StudentProfile;
import dc.businessmodel.Subject;
import dc.utility.JDBCHelper;
import dc.utility.ResultSet;
@Singleton
public class StudentRepository {
	@Inject
	ObjectMapper mapper;
	public StudentDashboardVM GetDashboard(int instituteId, int dcuserId, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		TypeReference<List<NotificationView>> ref=new TypeReference<List<NotificationView>>(){};
		StudentDashboardVM dsh = null;
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_dcuserid", dcuserId);
		List<ResultSet> resultset = helper.ExecuteMultipleResultset("STD_GetDashboard",userparam);
		if (resultset.size() >= 1 ){
			dsh = mapper.convertValue(resultset.get(0).results.get(0), StudentDashboardVM.class);
		}
		dsh.setNotifications(new ArrayList<NotificationView>());
		if(resultset.size() >= 2)
		{
			dsh.setNotifications((List<NotificationView>) mapper.convertValue(resultset.get(1).results, ref));
		}
		if(resultset.size() >= 3)
		{
			dsh.setStudentAcademicDetails(mapper.convertValue(resultset.get(2).results.get(0), Object.class));
		}
		return dsh;
	}
	
	public StudentProfile GetProfile(int instituteId, int userId, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		StudentProfile dsh = null;
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_userid", userId);
		ResultSet resultset = helper.ExecuteResult("STD_GetProfile",userparam);
		if (resultset.results.size() != 0 ){
			dsh = mapper.convertValue(resultset.results.get(0), StudentProfile.class);
		}
		return dsh;
	}
	
	public void RecordStudentACK(Student student, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", student.getInstituteId());
		userparam.put("@p_userid", student.getStudentId());
		userparam.put("@p_ack", student.isACK());
		userparam.put("@p_note", student.getNote());
		helper.Execute("STD_RecordStudentACK",userparam);
		
	}
	public void CopyNotification(int instituteId,int studentId, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_studentid", studentId);
		helper.Execute("STD_CopyNotification",userparam);
	}
	
	public List<Object> GetAllForm(int instituteId,int studentId, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		List<Object> forms = null;
		TypeReference<List<Object>> ref=new TypeReference<List<Object>>(){};
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_studentid", studentId);
		ResultSet result = helper.ExecuteResult("STD_GetAllForm",userparam);
		if(result.results.size() != 0)
		{
			forms = mapper.convertValue(result.results, ref);
		}
		return forms;
	}
	
	public Object GetForm(int instituteId,int studentId,int formId, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		Object forms = null;
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_studentid", studentId);
		userparam.put("@p_formid", formId);
		ResultSet result = helper.ExecuteResult("STD_GetForm",userparam);
		if(result.results.size() != 0)
		{
			forms = mapper.convertValue(result.results.get(0), Object.class);
		}
		return forms;
	}
	
	public List<Object> GetSubjects(int instituteId,int studentId, JDBCHelper helper) throws SQLException{
		List<Object> subjects = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		TypeReference<List<Object>> ref=new TypeReference<List<Object>>(){};
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_studentid", studentId);
		ResultSet result = helper.ExecuteResult("STD_GetSubjects",userparam);
		if(result.results.size() != 0)
		{
			subjects = mapper.convertValue(result.results, ref);
		}
		return subjects;
	}
	
	public List<Object> GetFaildSubjects(int instituteId,int studentId, int formId, JDBCHelper helper) throws SQLException{
		List<Object> subjects = null;
		Map<String,Object> userparam = new HashMap<String, Object>();
		TypeReference<List<Object>> ref=new TypeReference<List<Object>>(){};
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_studentid", studentId);
		userparam.put("@p_formid", formId);
		ResultSet result = helper.ExecuteResult("STD_GetFaildSubjects",userparam);
		if(result.results.size() != 0)
		{
			subjects = mapper.convertValue(result.results, ref);
		}
		return subjects;
	}
	
	public void StudentFormResponce(int instituteId,int studentId,int formId, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		Object forms = null;
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_studentid", studentId);
		userparam.put("@p_formid", formId);
		helper.Execute("STD_StudentFormResponce",userparam);
	}
	
	
	public void UploadPhoto(int studentId,InputStream photo, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		
		userparam.put("@p_studentid", studentId);
		userparam.put("@p_photo", photo);
		helper.Execute("STD_UploadPhoto",userparam);
	}
	
	public InputStream GetPhoto(int studentId, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		
		userparam.put("@p_studentid", studentId);
		ResultSet result = helper.ExecuteResult("STD_GetPhoto", userparam);
		if(result.results.size() != 0)
		{
			return new ByteArrayInputStream((byte[])result.results.get(0).get("Photo"));
		}
		return null;
	}
}
