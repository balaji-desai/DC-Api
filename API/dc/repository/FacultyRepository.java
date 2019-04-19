package dc.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dc.businessmodel.DashboardVm;
import dc.businessmodel.Department;
import dc.businessmodel.Subject;
import dc.utility.JDBCHelper;
import dc.utility.ResultSet;
@Singleton
public class FacultyRepository {
	@Inject
	ObjectMapper mapper;
	
	public DashboardVm GetDashboard(int instituteId, int facultyId, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		DashboardVm dsh = null;
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_facultyid", facultyId);
		ResultSet resultset = helper.ExecuteResult("FACT_GetDashboard",userparam);
		if (resultset.results.size() != 0 ){
			dsh = mapper.convertValue(resultset.results.get(0), DashboardVm.class);
		}
		return dsh;
	}
	
	public void AddSubject(Subject sub, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		userparam.put("@p_InstituteId", sub.getInstituteId());
		userparam.put("@p_departmentid", sub.getDepartmentId());
		userparam.put("@p_yearid", sub.getYearId());
		userparam.put("@p_semisterid", sub.getSemisterId());
		userparam.put("@p_evaluation", sub.isEvaluation());
		userparam.put("@p_name", sub.getName());
		helper.Execute("FACT_AddSubject",userparam);
		
	}
	
	public List<Subject> GetSubjects(int instituteId,int departmentId, int yearid, JDBCHelper helper) throws SQLException{
		Map<String,Object> userparam = new HashMap<String, Object>();
		List<Subject> sub = null;
		userparam.put("@p_InstituteId", instituteId);
		userparam.put("@p_departmentid", departmentId);
		userparam.put("@p_yearid", yearid);
		TypeReference<List<Subject>> ref=new TypeReference<List<Subject>>(){};
		ResultSet resultset = helper.ExecuteResult("FACT_GetSubjects",userparam);
		if (resultset.results.size() != 0 ){
			sub = mapper.convertValue(resultset.results, ref);
		}
		return sub;
	}
	
	public List<Object> GetResultAnalysis(int InstituteId,int facultyId,JDBCHelper helper) throws SQLException{
		
		Map<String,Object> userparam = new HashMap<String, Object>();
		TypeReference<List<Object>> ref=new TypeReference<List<Object>>(){};
		
		userparam.put("@p_InstituteId", InstituteId);
		userparam.put("@p_facultyId", facultyId);
		ResultSet results = helper.ExecuteResult("FACT_ResultAnalysis", userparam);
		
		return mapper.convertValue(results.results, ref);
		
	}
}
