package dc.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;

import dc.businessmodel.Faculty;
import dc.businessmodel.FacultyType;
import dc.businessmodel.FormModel;
import dc.businessmodel.HallTicketInfo;
import dc.businessmodel.ResultModel;
import dc.utility.JDBCHelper;
import dc.utility.ResultSet;

@Singleton
public class StaffRepository {
	@Inject
	ObjectMapper mapper;
public void CreateForm(FormModel model, JDBCHelper helper) throws SQLException{
		
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", model.getInstituteId());
		userparam.put("@p_departmentid", model.getDepartmentId());
		userparam.put("@p_fillenddate", model.getFillEndDateModel());
		userparam.put("@p_fillstartdate", model.getFillStartDateModel());
		userparam.put("@p_formtypeid", model.getFormTypeId());
		userparam.put("@p_semisterid", model.getSemisterId() == 0?null:model.getSemisterId());
		userparam.put("@p_title", model.getTitle());
		userparam.put("@p_yearid", model.getYearId());
		helper.Execute("STF_CreateForm", userparam);
		
	}

public void VerifyForm(int instituteId, int formId, int studentId, JDBCHelper helper) throws SQLException{
	
	Map<String,Object> userparam = new HashMap<String, Object>();
	userparam.put("@p_InstituteId", instituteId);
	userparam.put("@p_formid", formId);
	userparam.put("@p_studentid", studentId);
	helper.Execute("STF_VerifyForm", userparam);
	
}

public void AckVerificaton(int instituteId, int formId, int studentId, JDBCHelper helper) throws SQLException{
	
	Map<String,Object> userparam = new HashMap<String, Object>();
	userparam.put("@p_InstituteId", instituteId);
	userparam.put("@p_formid", formId);
	userparam.put("@p_studentid", studentId);
	helper.Execute("STF_AckVerificaton", userparam);
	
}
public Object SetHallTicket(HallTicketInfo param, JDBCHelper helper) throws SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
	TypeReference<List<Object>> ref=new TypeReference<List<Object>>(){};
	Object obj = null;
	Map<String,Object> userparam = new HashMap<String, Object>();
	userparam.put("@p_InstituteId",  param.getInstituteId());
	userparam.put("@p_yearid",  param.getYearId());
	userparam.put("@p_studentid", param.getStudentId());
	userparam.put("@p_semisterid", param.getSemisterId());
	userparam.put("@p_departmentid", param.getDepartmentId());
	userparam.put("@p_hallticketno", param.getHallticketNo());
	ResultSet result = helper.ExecuteResult("STF_SetHallTicket", userparam);
	if(result.results.size() != 0)
	{
		obj = mapper.convertValue(result.results, ref);
	}
	return obj;
	
}
public Object GetSubject(HallTicketInfo param, JDBCHelper helper) throws SQLException{
	Object obj = null;
	TypeReference<List<Object>> ref=new TypeReference<List<Object>>(){};
	Map<String,Object> userparam = new HashMap<String, Object>();
	userparam.put("@p_InstituteId", param.getInstituteId());
	userparam.put("@p_yearid", param.getYearId());
	userparam.put("@p_departmentid", param.getDepartmentId());
	userparam.put("@p_semisterid", param.getSemisterId());
	ResultSet result = helper.ExecuteResult("STF_GetSubject", userparam);
	if(result.results.size() != 0)
	{
		obj = mapper.convertValue(result.results, ref);
	}
	return obj;
}

public List<Object> VerifyStudentSubject(ResultModel param,SQLServerDataTable list, JDBCHelper helper) throws SQLException{
	
	Map<String,Object> userparam = new HashMap<String, Object>();
	TypeReference<List<Object>> ref=new TypeReference<List<Object>>(){};
	List<Object> output = new ArrayList<Object>();
	userparam.put("@p_InstituteId", param.getInstituteId());
	userparam.put("@p_yearid", param.getYearId());
	userparam.put("@p_departmentid", param.getDepartmentId());
	userparam.put("@p_semisterid", param.getSemisterId());
	userparam.put("@p_subjectlist", list);
	userparam.put("@p_launchdate", param.getLaunchDateModel());
	List<ResultSet> results = helper.ExecuteMultipleResultset("STF_VerifyStudentSubject", userparam);
	if(results.size() >= 1)
	{
		output.add(mapper.convertValue(results.get(0).results, ref));
	}
	if(results.size() >= 2)
	{
		output.add(results.get(1).results.get(0).get("IsSuccess"));
	}
	return output;
	
}

public List<Object> GetFormStatus(int InstituteId, JDBCHelper helper) throws SQLException{
	
	Map<String,Object> userparam = new HashMap<String, Object>();
	TypeReference<List<Object>> ref=new TypeReference<List<Object>>(){};
	
	userparam.put("@p_InstituteId", InstituteId);
	ResultSet results = helper.ExecuteResult("STF_GetFormStatus", userparam);
	
	return mapper.convertValue(results.results, ref);
	
}

public List<Object> GetDashDet(int InstituteId,String  DetailType,JDBCHelper helper) throws SQLException{
	
	Map<String,Object> userparam = new HashMap<String, Object>();
	TypeReference<List<Object>> ref=new TypeReference<List<Object>>(){};
	
	userparam.put("@p_InstituteId", InstituteId);
	userparam.put("@p_detailtype", DetailType);
	ResultSet results = helper.ExecuteResult("STF_GetDashDet", userparam);
	
	return mapper.convertValue(results.results, ref);
	
}

}

