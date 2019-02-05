package dc.controller;

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
import dc.businessmodel.FormModel;
import dc.businessmodel.UserAuthModel;
import dc.service.StaffService;

@Path("staff")
public class StaffController {
	@Inject
	private StaffService staffService;
	
	@POST
	@Path("createform")
	@RolesAllowed({"exam section"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CreateForm(FormModel model,@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			model.setInstituteId(logedinuser.getInstituteId());
			staffService.CreateForm(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).build();
	}
	
	@GET
	@Path("verifyform")
	@RolesAllowed({"accountant"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response VerifyForm(@QueryParam("formid")int formId,
			@QueryParam("studentid")int studentId
			,@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			staffService.VerifyForm(logedinuser.getInstituteId(), formId, studentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).build();
	}
	
	@GET
	@Path("ackverification")
	@RolesAllowed({"accountant"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AckVerificaton(@QueryParam("formid")int formId,
			@QueryParam("studentid")int studentId
			,@Context ContainerRequestContext crc)
			throws Exception {
		try {
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			staffService.AckVerificaton(logedinuser.getInstituteId(), formId, studentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return Response.status(200).build();
	}

}
