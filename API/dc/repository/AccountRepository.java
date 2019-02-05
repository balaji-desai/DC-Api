package dc.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;

import dc.businessmodel.InstituteModel;
import dc.businessmodel.UserAuthModel;
import dc.utility.JDBCHelper;
import dc.utility.ResultSet;

@Singleton
public class AccountRepository {
	@Inject
	ObjectMapper mapper;
	
public UserAuthModel GetUser(String UserName, String Password, JDBCHelper helper) throws Exception{
		
		Map<String,Object> userparam = new HashMap<String, Object>();
		UserAuthModel accvm = null;
		
		userparam.put("@p_username", UserName);
		userparam.put("@p_password", Password);
		
		ResultSet resultset = helper.ExecuteResult("FW_GETUSER",userparam);
		if (resultset.results.size() != 0 ){
			accvm = mapper.convertValue(resultset.results.get(0), UserAuthModel.class);
		}
		return accvm;
	}

public void SetToken(String Token, int UserId, JDBCHelper helper) throws SQLException{
	Map<String,Object> userparam = new HashMap<String, Object>();
	userparam.put("@p_AuthToken", Token);
	userparam.put("@p_DCUserId", UserId);
	helper.Execute("FW_SetToken",userparam);
}

	
	public boolean Logout(int userid, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_DCUserId", userid);
		helper.Execute("FW_LOGOUT",userparam);
		return true;
	}
	
	public InstituteModel GetInstitute(int instituteId, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		InstituteModel inst = null;
		userparam.put("@p_InstituteId", instituteId);
		ResultSet resultset = helper.ExecuteResult("INST_GetInstitute",userparam);
		if (resultset.results.size() != 0 ){
			inst = mapper.convertValue(resultset.results.get(0), InstituteModel.class);
		}
		return inst;
	}
	
	public void UpdateInstitute(InstituteModel model, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", model.getInstituteId());
		userparam.put("@p_institutename", model.getInstituteName());
		userparam.put("@p_institutecode", model.getInstituteCode());
		userparam.put("@p_address", model.getAddress());
		userparam.put("@p_logo", model.getLogo());
		helper.Execute("INST_UpdateInstitute",userparam);
	}
}
