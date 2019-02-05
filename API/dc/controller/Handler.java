package dc.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.annotation.security.PermitAll;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.imgscalr.Scalr;

import dc.businessmodel.UserAuthModel;
import dc.service.AdminService;
import dc.service.StudentService;

@Path("handler")
public class Handler {
	@Inject
	private AdminService adminService;
	@Inject
	private StudentService studentService; 
	
	@GET
	@Path("logo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@PermitAll()
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public void getLogo(@Suspended final AsyncResponse asyncResponce,
			@Context ContainerRequestContext crc)
	{
		try{
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			final InputStream in = adminService.getLogo(logedinuser.getInstituteId());
			if(in != null) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			//thumbnill
			BufferedImage img = ImageIO.read(in); // load image
			ImageIO.write(img, "png", out);
			img.flush();
			byte[] imageData = out.toByteArray();
			asyncResponce.resume(new ByteArrayInputStream(imageData));
			}
			else
			{
//				ByteArrayOutputStream out = new ByteArrayOutputStream();
//				BufferedImage img = ImageIO.read(new File(root.getPath()+"/default_logo.png")); // load image
//				byte[] imageData = out.toByteArray();
//				asyncResponce.resume(new ByteArrayInputStream(imageData));
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			if(!(e instanceof ConstraintViolationException))
			{
				asyncResponce.resume(e.getMessage());
			}
			else{
				throw (ConstraintViolationException)e;
			}
		}
	}
	
	@GET
	@Path("profile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@PermitAll()
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public void getProfilePhoto(@QueryParam("userid")int userId, @Suspended final AsyncResponse asyncResponce,
			@Context ContainerRequestContext crc)
	{
		try{
			UserAuthModel logedinuser = (UserAuthModel) crc
					.getProperty("logedinobj");
			if(userId == 0)
			{
				userId = logedinuser.getUserId();
			}
			final InputStream in = studentService.GetPhoto(userId);
			if(in != null) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			//thumbnill
			BufferedImage img = ImageIO.read(in); // load image
			ImageIO.write(img, "png", out);
			img.flush();
			byte[] imageData = out.toByteArray();
			asyncResponce.resume(new ByteArrayInputStream(imageData));
			}
			else
			{
//				ByteArrayOutputStream out = new ByteArrayOutputStream();
//				BufferedImage img = ImageIO.read(new File(root.getPath()+"/default_logo.png")); // load image
//				byte[] imageData = out.toByteArray();
//				asyncResponce.resume(new ByteArrayInputStream(imageData));
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			if(!(e instanceof ConstraintViolationException))
			{
				asyncResponce.resume(e.getMessage());
			}
			else{
				throw (ConstraintViolationException)e;
			}
		}
	}

}
