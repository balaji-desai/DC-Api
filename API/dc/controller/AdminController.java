package dc.controller;

import java.io.InputStream;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
import dc.businessmodel.UserAuthModel;
import dc.service.AdminService;

@Path("admin")
public class AdminController {
	@Inject
	private AdminService adminService;

	@GET
	@Path("getadmindetail")
	@RolesAllowed({"admin"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetAdminDetails(@Context ContainerRequestContext crc)
			throws Exception {
		AdminModel model = null;
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			model = adminService.GetAdminDetail(logedinuser.getInstituteId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).entity(model).build();
	}
	
	@POST
	@Path("updateprofile")
	@RolesAllowed({"admin"})
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response UpdateProfile( 
			@FormDataParam("file") InputStream fileInputString,
		    @FormDataParam("file") FormDataContentDisposition fileInputDetails,
			@FormDataParam("adminmodel")
			AdminModel model,@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			model.getInstitute().setInstituteId(logedinuser.getInstituteId());
			if(fileInputDetails.getFileName() != null)
			{
				model.getInstitute().setLogo(fileInputString);				
			}
			adminService.UpdateProfile(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
		return Response.status(200).build();
	}
	
	@GET
	@Path("getdept")
	@RolesAllowed({"admin","exam section"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetDepartment( @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<Department> dept= adminService.GetDepartment(logedinuser.getInstituteId());
			return Response.status(200).entity(dept).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@GET
	@Path("getdeptwithoutproxy")
	@RolesAllowed({"admin","exam section"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetDepartmentWithProxy( @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<Department> dept= adminService.GetDepartmentWithoutProxy(logedinuser.getInstituteId());
			return Response.status(200).entity(dept).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@GET
	@Path("getyear")
	@RolesAllowed({"admin","exam section"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetYear( @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<AcademicYear> year= adminService.GetYear(logedinuser.getInstituteId());
			return Response.status(200).entity(year).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@GET
	@Path("getdeptyear")
	@RolesAllowed({"admin","hod"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetDeptYear( @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<AcademicYear> year= adminService.GetDeptYear(logedinuser.getInstituteId());
			return Response.status(200).entity(year).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@GET
	@Path("getsemister")
	@RolesAllowed({"admin","hod","exam section"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetSemister( @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<Semister> year= adminService.GetSemister(logedinuser.getInstituteId());
			return Response.status(200).entity(year).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@GET
	@Path("getformtype")
	@RolesAllowed({"admin","exam section"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetFormType( @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<FormType> year= adminService.getFormType();
			return Response.status(200).entity(year).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@GET
	@Path("getstudents")
	@RolesAllowed({"admin"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetStudents( @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<Student> student= adminService.GetStudent(logedinuser.getInstituteId());
			return Response.status(200).entity(student).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@GET
	@Path("getstudentbyid")
	@RolesAllowed({"admin"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetStudentById(@QueryParam("studentid")int studentId, @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			Object student= adminService.GetStudentById(logedinuser.getInstituteId(),studentId);
			return Response.status(200).entity(student).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@GET
	@Path("getfacultys")
	@RolesAllowed({"admin"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetFaculty( @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<Faculty> student= adminService.GetFaculty(logedinuser.getInstituteId());
			return Response.status(200).entity(student).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@GET
	@Path("getstaffs")
	@RolesAllowed({"admin"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetStaff( @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<Staff> student= adminService.GetStaff(logedinuser.getInstituteId());
			return Response.status(200).entity(student).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@POST
	@Path("createstudent")
	@RolesAllowed({"admin"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateStudent(Student student, @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			student.setInstituteId(logedinuser.getInstituteId());
			adminService.CreateStudent(student);
			return Response.status(200).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@POST
	@Path("editstudent")
	@RolesAllowed({"admin"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response EditStudent(Student student, @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			student.setInstituteId(logedinuser.getInstituteId());
			adminService.EditStudent(student);
			return Response.status(200).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@GET
	@Path("getfacultytype")
	@RolesAllowed({"admin"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetFacultyType( @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<FacultyType> year= adminService.GetFacultyType(logedinuser.getInstituteId());
			return Response.status(200).entity(year).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	@GET
	@Path("getsatfftype")
	@RolesAllowed({"admin"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetStaffType( @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<StaffType> year= adminService.GetStaffType(logedinuser.getInstituteId());
			return Response.status(200).entity(year).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	@POST
	@Path("createfaculty")
	@RolesAllowed({"admin"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateFaculty(Faculty faculty, @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			faculty.setInstituteId(logedinuser.getInstituteId());
			adminService.CreateFaculty(faculty);
			return Response.status(200).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@POST
	@Path("createstaff")
	@RolesAllowed({"admin"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateStaff(Staff staff, @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			staff.setInstituteId(logedinuser.getInstituteId());
			adminService.CreateStaff(staff);
			return Response.status(200).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}
	
	@GET
	@Path("viewnotification")
	@RolesAllowed({"admin","student"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ViewNotification(@QueryParam("notificationviewid") int notificationviewid, @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			NotificationView notif = adminService.ViewNotification(logedinuser.getInstituteId(), notificationviewid);
			return Response.status(200).entity(notif).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e; 
		}
	}

}
