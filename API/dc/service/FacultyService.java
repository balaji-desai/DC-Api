package dc.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolationException;

import dc.businessmodel.DashboardVm;
import dc.businessmodel.Department;
import dc.businessmodel.Subject;
import dc.repository.FacultyRepository;
import dc.utility.ExceptionHelper;
import dc.utility.JDBCHelper;
@Singleton
public class FacultyService {
	@Inject
	FacultyRepository facultyRepository;
	@Inject
	public JDBCHelper helper;
	
	public DashboardVm GetDashboard(int instituteId, int facultyId) throws Exception{
		DashboardVm dashboardVm = null;
		try {
			dashboardVm = facultyRepository.GetDashboard(instituteId, facultyId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return dashboardVm;
	}
	

	public void AddSubject(Subject sub) throws Exception{
		try {
			 facultyRepository.AddSubject(sub, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public List<Subject> GetSubjects(int instituteId,int departmentId, int yearid) throws Exception{
		try {
			 return facultyRepository.GetSubjects(instituteId, departmentId, yearid, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
}
