package dc.controller;

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

import dc.businessmodel.AdminModel;
import dc.businessmodel.DashboardVm;
import dc.businessmodel.Subject;
import dc.businessmodel.UserAuthModel;
import dc.service.FacultyService;

@Path("faculty")
public class FacultyController {
	@Inject
	private FacultyService facultyService;
	
	@GET
	@Path("getdashboard")
	@RolesAllowed({"teacher","hod"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetDashboard(@Context ContainerRequestContext crc)
			throws Exception {
		DashboardVm model = null;
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			model = facultyService.GetDashboard(logedinuser.getInstituteId(), logedinuser.getDCUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).entity(model).build();
	}
	
	@POST
	@Path("addsubject")
	@RolesAllowed({"hod"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddSubject(Subject sub,@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			sub.setInstituteId(logedinuser.getInstituteId());
			facultyService.AddSubject(sub);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).build();
	}
	
	@GET
	@Path("getsubjects")
	@RolesAllowed({"teacher","hod"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetSubjects(@QueryParam("departmentid") int departmentId,@QueryParam("yearid")int yearid, @Context ContainerRequestContext crc)
			throws Exception {
		List<Subject> model = null;
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			model = facultyService.GetSubjects(logedinuser.getInstituteId(), departmentId, yearid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).entity(model).build();
	}
	
	@GET
	@Path("getresultanalysis")
	@RolesAllowed({"teacher","hod"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetResultAnalysis(@Context ContainerRequestContext crc)
			throws Exception {
		List<Object> model = null;
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			model = facultyService.GetResultAnalysis(logedinuser.getInstituteId(),logedinuser.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).entity(model).build();
	}

}
