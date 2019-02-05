package dc.controller;

import java.util.Calendar;
import java.util.Hashtable;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import dc.businessmodel.UserAuthModel;
import dc.jwtutil.KeyGenerator;
import dc.service.AccountService;
import dc.utility.TokenIssuer;
import dc.utility.UtilityFunctions;

@Path("account")
public class AccountController {
	
	@Inject
	private TokenIssuer tokenissuer;
	@Inject
    private KeyGenerator keyGenerator;
	@Inject
	private AccountService accountservice;
	@Inject
    private Hashtable sessiondata;
	@Inject
	private ObjectMapper mapper;

	@PermitAll()
	//@RolesAllowed({"Admin"})
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response Login(
			@FormParam("username")
			String username,
			@FormParam("password")
			String password,
			@FormParam("val")
			int val) throws Exception{
		String token = null;
		try{	
			if(val != 0)
			{				
				val = val/11;
				password = UtilityFunctions.Decrypt(password,"#mh07kr1305#"+val,keyGenerator);
			}
		}
		catch(Exception ex)
		{
			throw new SecurityException("Problem in login.please try again!!");
		}
		UserAuthModel accv = accountservice.GetUser(username, password);
		UserAuthModel accvm = mapper.convertValue(accv, UserAuthModel.class);
		if(accvm != null){
			HttpSession session = (HttpSession) sessiondata.get("session");
			if(session.getAttribute(accvm.getAuthToken()) != null)
			{
				session.setMaxInactiveInterval(60*60);
			}
			else{		
				long keyvalue = Calendar.getInstance().getTimeInMillis();
				String key = Long.toString(keyvalue/1111).substring(4,7);
				token = tokenissuer.tokenissue(key,accv);
				session.setAttribute(token, key);
				accountservice.SetToken(token, accvm.getDCUserId());
				accvm.setAuthToken(token);
				accvm.setDCUserId(0);
				accvm.setUserName(null);
			}
		}else{
			throw new SecurityException("Invalid Login/Username");
		}
		return Response.status(200).entity(accvm).build();
	}
	@GET
	@Path("logout")
	@PermitAll()
	public Response Logout(@Context ContainerRequestContext crc)
	{
		String authorizationHeader = crc.getHeaderString(HttpHeaders.AUTHORIZATION);
        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader != null || authorizationHeader.startsWith("Bearer ")) {
        	String token = authorizationHeader.substring("Bearer".length()).trim();
        	HttpSession session = (HttpSession) sessiondata.get("session");
        	session.removeAttribute(token);
        	UserAuthModel logedinuser = (UserAuthModel) crc.getProperty("logedinobj");
        	if(logedinuser != null)
        	{        		
        		accountservice.logout(logedinuser.getDCUserId());
        	}
        }
      
        return Response.status(200).build();
	}
	
}
