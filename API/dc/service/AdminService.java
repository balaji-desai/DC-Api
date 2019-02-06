package dc.service;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolationException;

import dc.businessmodel.AcademicYear;
import dc.businessmodel.AdminModel;
import dc.businessmodel.Department;
import dc.businessmodel.Faculty;
import dc.businessmodel.FacultyType;
import dc.businessmodel.FormType;
import dc.businessmodel.InstituteModel;
import dc.businessmodel.NotificationView;
import dc.businessmodel.Semister;
import dc.businessmodel.Staff;
import dc.businessmodel.StaffType;
import dc.businessmodel.Student;
import dc.repository.AccountRepository;
import dc.repository.AdminRepository;
import dc.utility.ExceptionHelper;
import dc.utility.JDBCHelper;
@Singleton
public class AdminService {
	@Inject
	AccountRepository accountrepository;
	@Inject
	AdminRepository	adminRepository;

	@Inject
	public JDBCHelper helper;

	public AdminModel GetAdminDetail(int instituteId) throws Exception{
		AdminModel accvm = null;
		try {
			InstituteModel inst = accountrepository.GetInstitute(instituteId, helper);
			accvm = adminRepository.GetAdminDetail(instituteId, helper);
			accvm.setInstitute(inst);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return accvm;
	}
	
	public void UpdateProfile(AdminModel model) throws Exception{
		try {
			helper.BeginTransaction();
			accountrepository.UpdateInstitute(model.getInstitute(), helper);
			adminRepository.UpdateProfile(model.getInstitute().getInstituteId(), model, helper);
			helper.Commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			helper.Rollback();
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		finally{
			helper.Commit();
		}
	}
	public List<Department> GetDepartmentWithoutProxy(int instituteId) throws Exception{
		List<Department> department = null;
		try {
			department = adminRepository.GetDepartmentsWithoutProxy(instituteId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return department;
	}
	
	public List<Department> GetDepartment(int instituteId) throws Exception{
		List<Department> department = null;
		try {
			department = adminRepository.GetDepartments(instituteId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return department;
	}
	
	public List<AcademicYear> GetYear(int instituteId) throws Exception{
		List<AcademicYear> year = null;
		try {
			year = adminRepository.GetYears(instituteId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return year;
	}
	
	public List<AcademicYear> GetDeptYear(int instituteId) throws Exception{
		List<AcademicYear> year = null;
		try {
			year = adminRepository.GetDeptYears(instituteId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return year;
	}
	
	public List<Semister> GetSemister(int instituteId) throws Exception{
		List<Semister> year = null;
		try {
			year = adminRepository.getSemister(instituteId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return year;
	}
	
	public List<Student> GetStudent(int instituteId) throws Exception{
		List<Student> student = null;
		try {
			student = adminRepository.GetStudents(instituteId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return student;
	}
	
	public Object GetStudentById(int instituteId,int studentId) throws Exception{
		Object student = null;
		try {
			student = adminRepository.GetStudentById(instituteId, studentId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return student;
	}
	public List<Faculty> GetFaculty(int instituteId) throws Exception{
		List<Faculty> student = null;
		try {
			student = adminRepository.GetFaculty(instituteId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return student;
	}
	public List<Staff> GetStaff(int instituteId) throws Exception{
		List<Staff> student = null;
		try {
			student = adminRepository.GetStaff(instituteId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return student;
	}
	
	public void CreateStudent(Student student) throws Exception{
		try {
			adminRepository.CreateStudent(student, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public void EditStudent(Student student) throws Exception{
		try {
			adminRepository.EditStudent(student, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	public List<FacultyType> GetFacultyType(int instituteId) throws Exception{
		List<FacultyType> year = null;
		try {
			year = adminRepository.getFacultyType(instituteId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return year;
	}
	
	public List<FormType> getFormType() throws Exception{
		List<FormType> year = null;
		try {
			year = adminRepository.getFormType(helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return year;
	}
	public List<StaffType> GetStaffType(int instituteId) throws Exception{
		List<StaffType> year = null;
		try {
			year = adminRepository.getStaffType(instituteId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
		return year;
	}
	public void CreateFaculty(Faculty faculty) throws Exception{
		try {
			adminRepository.CreateFaculty(faculty, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public void CreateStaff(Staff staff) throws Exception{
		try {
			adminRepository.CreateStaff(staff, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public NotificationView ViewNotification(int instituteId, int notificationviewId) throws Exception{
		try {
			return adminRepository.ViewNotification(instituteId, notificationviewId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public InputStream getLogo(int instituteId) throws Exception{
		try {
			return adminRepository.getLogo(instituteId, helper);
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
