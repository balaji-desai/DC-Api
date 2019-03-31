package dc.service;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolationException;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import dc.businessmodel.DashboardVm;
import dc.businessmodel.Student;
import dc.businessmodel.StudentDashboardVM;
import dc.businessmodel.StudentProfile;
import dc.repository.AdminRepository;
import dc.repository.FacultyRepository;
import dc.repository.StudentRepository;
import dc.utility.ExceptionHelper;
import dc.utility.JDBCHelper;
@Singleton
public class StudentService {
	@Inject
	StudentRepository studentRepository;
	@Inject
	AccountService	accountService;
	@Inject
	public JDBCHelper helper;
	
	public StudentDashboardVM GetDashboard(int instituteId, int dcuserId) throws Exception{
		StudentDashboardVM dashboardVm = null;
		try {
			dashboardVm = studentRepository.GetDashboard(instituteId, dcuserId, helper);
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
	
	public StudentProfile GetProfile(int instituteId, int userId) throws Exception{
		StudentProfile dashboardVm = null;
		try {
			dashboardVm = studentRepository.GetProfile(instituteId, userId, helper);
			if(dashboardVm != null)
			{	
				dashboardVm.setStudentId(userId);			
				dashboardVm.setInstitute(accountService.GetInstitue(instituteId));
			}
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
	
	public void RecordStudentACK(Student student) throws Exception{
		try {
			if(student.isACK() == false && student.getNote().isEmpty())
			{
				throw  new ConstraintViolationException("Reason Requierd.",null);
			}
			studentRepository.RecordStudentACK(student, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public void CopyNotification(int instituteId, int studentId) throws Exception{
		try {
			studentRepository.CopyNotification(instituteId, studentId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public List<Object> GetAllForms(int instituteId, int studentId) throws Exception{
		try {
			return studentRepository.GetAllForm(instituteId, studentId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	public Object GetForm(int instituteId, int studentId, int formId) throws Exception{
		try {
			return studentRepository.GetForm(instituteId, studentId, formId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public void StudentFormResponce(int instituteId, int studentId, int formId) throws Exception{
		try {
			studentRepository.StudentFormResponce(instituteId, studentId, formId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public List<Object> GetSubjects(int instituteId, int studentId) throws Exception{
		try {
			return studentRepository.GetSubjects(instituteId, studentId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public List<Object> GetFaildSubjects(int instituteId, int studentId, int formId) throws Exception{
		try {
			return studentRepository.GetFaildSubjects(instituteId, studentId, formId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public void UploadPhoto(int studentId,InputStream photo) throws Exception{
		try {
			studentRepository.UploadPhoto(studentId, photo, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public InputStream GetPhoto(int studentId) throws Exception{
		try {
			return studentRepository.GetPhoto(studentId, helper);
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
