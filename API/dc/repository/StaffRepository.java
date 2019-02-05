package dc.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;

import dc.businessmodel.Faculty;
import dc.businessmodel.FormModel;
import dc.utility.JDBCHelper;

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
}

