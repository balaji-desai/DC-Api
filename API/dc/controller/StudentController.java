package dc.controller;

import java.io.InputStream;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
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

import dc.businessmodel.Student;
import dc.businessmodel.StudentDashboardVM;
import dc.businessmodel.StudentProfile;
import dc.businessmodel.UserAuthModel;
import dc.service.StudentService;
@Path("student")
public class StudentController {
	@Inject
	private StudentService studentService;

	@GET
	@Path("getdashboard")
	@RolesAllowed({"student"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetDashboard(@Context ContainerRequestContext crc)
			throws Exception {
		StudentDashboardVM model = null;
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			model = studentService.GetDashboard(logedinuser.getInstituteId(), logedinuser.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).entity(model).build();
	}
	
	@GET
	@Path("getprofile")
	@RolesAllowed({"student"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetProfile(@Context ContainerRequestContext crc)
			throws Exception {
		StudentProfile model = null;
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			model = studentService.GetProfile(logedinuser.getInstituteId(), logedinuser.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).entity(model).build();
	}
	
	@POST
	@Path("recordstudentack")
	@RolesAllowed({"student"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RecordStudentASK(Student student,@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			student.setInstituteId(logedinuser.getInstituteId());
			student.setStudentId(logedinuser.getUserId());
			studentService.RecordStudentACK(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).build();
	}
	
	@GET
	@Path("copynotification")
	@RolesAllowed({"student"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Copynotification(@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			studentService.CopyNotification(logedinuser.getInstituteId(), logedinuser.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).build();
	}
	
	@GET
	@Path("getallform")
	@RolesAllowed({"student"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetAllForms(@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<Object> forms = studentService.GetAllForms(logedinuser.getInstituteId(), logedinuser.getUserId());
			return Response.status(200).entity(forms).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	@GET
	@Path("getform")
	@RolesAllowed({"student","accountant"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetForm(@QueryParam("formid") int formId,@QueryParam("studentid") int studentId, @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			if(logedinuser.getUserType().toLowerCase().equals("student"))
			{
				studentId = logedinuser.getUserId();
			}
			Object forms = studentService.GetForm(logedinuser.getInstituteId(), studentId, formId);
			return Response.status(200).entity(forms).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	@GET
	@Path("studentformresponce")
	@RolesAllowed({"student"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response StudentFormResponce(@QueryParam("formid") int formId, @Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			studentService.StudentFormResponce(logedinuser.getInstituteId(), logedinuser.getUserId(), formId);
			return Response.status(200).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	
	@GET
	@Path("getsubjects")
	@RolesAllowed({"student"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetSubjects(@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<Object> forms = studentService.GetSubjects(logedinuser.getInstituteId(), logedinuser.getUserId());
			return Response.status(200).entity(forms).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	@GET
	@Path("getfailsubjects")
	@RolesAllowed({"student"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetFaildSubjects(
			@QueryParam("formid")int formId,
			@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<Object> forms = studentService.GetFaildSubjects(logedinuser.getInstituteId(), logedinuser.getUserId(), formId);
			return Response.status(200).entity(forms).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	@GET
	@Path("getfailsubjectsbyid")
	@RolesAllowed({"student"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetFaildSubjectsById(
			@QueryParam("formid")int formId,
			@QueryParam("studentid")int studentId,
			@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			List<Object> forms = studentService.GetFaildSubjects(logedinuser.getInstituteId(), studentId, formId);
			return Response.status(200).entity(forms).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	@POST
	@Path("uploadphoto")
	@RolesAllowed({"student"})
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response UploadPhoto(
			@FormDataParam("file") InputStream fileInputString,
		    @FormDataParam("file") FormDataContentDisposition fileInputDetails,
			@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			if(fileInputDetails.getFileName() != null)
			{				
				studentService.UploadPhoto(logedinuser.getUserId(), fileInputString);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).build();
	}
}
