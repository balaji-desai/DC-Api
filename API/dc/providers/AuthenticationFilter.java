package dc.providers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.lang.reflect.Method;
import java.security.Key;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import dc.businessmodel.UserAuthModel;
import dc.jwtutil.KeyGenerator;
import dc.service.AccountService;
	
	/**
	 * This filter verify the access permissions for a user
	 * based on username and passowrd provided in request
	 * */
	@Provider
	public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter 
	{
		
		// ======================================
	    // =          Injection Points          =
	    // ======================================


	    @Inject
	    private KeyGenerator keyGenerator;
	    
	    @Inject
	    private AccountService accountservice;
	    @Inject
	    private Hashtable sessiondata;
	    
	    @Context 
	    HttpServletRequest request;

	    // ======================================
	    // =          Business methods          =
	    // ======================================

		
		@Context
	    private ResourceInfo resourceInfo;
		
	    private static final String AUTHORIZATION_PROPERTY = "AUTHORIZATION";
	    private static final String AUTHENTICATION_SCHEME = "Basic";
	    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
	    													.entity("You cannot access this resource").build();
	    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
	    													.entity("Access blocked for all users !!").build();
	     
	    public void filter(ContainerRequestContext requestContext)
	    {
	        Method method = resourceInfo.getResourceMethod();
	        HttpSession session = null;
	        try{
	        	session = (HttpSession) sessiondata.get("session");
	        	session.getAttribute("active");
	        }
	        catch(Exception ex)
	        {
	        	session = request.getSession(true);
        		session.setAttribute("active", "true");
        		session.setMaxInactiveInterval(60*60);
        		sessiondata.clear();
        		sessiondata.put("session", session);
	        }
	        if(method.isAnnotationPresent(PermitAll.class))
	        {
	        	//Access denied for all
	            if(method.isAnnotationPresent(DenyAll.class))
	            {
	                requestContext.abortWith(ACCESS_FORBIDDEN);
	                return;
	            }
	            
	            
	             
	         // Get the HTTP Authorization header from the request
	            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
	            //logger.info("#### authorizationHeader : " + authorizationHeader);

	            // Check if the HTTP Authorization header is present and formatted correctly
	            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	                //logger.severe("#### invalid authorizationHeader : " + authorizationHeader);
	            	 // Extract the token from the HTTP Authorization header
		            String token = authorizationHeader.substring("Bearer".length()).trim();
		            UserAuthModel logedinobj = null;
		            logedinobj = SetUser(token, session);
		            if(logedinobj != null)
		            {
						requestContext.setProperty("logedinobj", logedinobj);
		            }
	            }
	        }
	        if( !(method.isAnnotationPresent(PermitAll.class)|| requestContext.getMethod().equals("OPTIONS")))
	        {
	            //Access denied for all
	            if(method.isAnnotationPresent(DenyAll.class))
	            {
	                requestContext.abortWith(ACCESS_FORBIDDEN);
	                return;
	            }
	            
	            
	             
	         // Get the HTTP Authorization header from the request
	            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
	            //logger.info("#### authorizationHeader : " + authorizationHeader);

	            // Check if the HTTP Authorization header is present and formatted correctly
	            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
	                //logger.severe("#### invalid authorizationHeader : " + authorizationHeader);
	                throw new NotAuthorizedException("Authorization header must be provided");
	            }

	            // Extract the token from the HTTP Authorization header
	            String token = authorizationHeader.substring("Bearer".length()).trim();
	            String username = null;
	            UserAuthModel logedinobj = null;
	            logedinobj = SetUser(token, session);
	            if(logedinobj == null)
	            {
	            	requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
					return;
	            }
	            requestContext.setProperty("logedinobj", logedinobj);
	             
	            //Verifying Username and password
	            System.out.println(username);
	            //System.out.println(password);
	             
	            //Verify user access
	            //Authorization
	            if(method.isAnnotationPresent(RolesAllowed.class))
	            {
	                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
	                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
	                 
	                //Is user valid?
					if( ! isUserAllowed(logedinobj,rolesSet))
					{
						requestContext.abortWith(ACCESS_DENIED);
						return;
					}
	            }
	        }
	    }
	    private boolean isUserAllowed(final UserAuthModel accvm, final Set<String> rolesSet)
	    {
	        boolean isAllowed = false;
	        
	        String userRole = accvm.getUserType();
	        String[] users = userRole.split(",");
            //Step 2. Verify user role
	        for (String user : users) {				
	        	if(rolesSet.contains(user.toLowerCase()))
	        	{
	        		isAllowed = true;
	        		break;
	        	}
			}
	        return isAllowed;
	    }
	    
	    private UserAuthModel SetUser(String token,HttpSession session)
	    {
	    	try {

            	//accountservice.IsLogedIn(logedinobj.getIHUserId(), token);
                // Validate the token
            	String secretkey = "";
            	if(session.getAttribute(token) == null)
            	{
					return null;
            	}
            	else{
            		secretkey = (String) session.getAttribute(token);
            	}
                Key key = keyGenerator.generateKey(secretkey);
                //validate Token And Get Claims.
                Claims clm = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
                String username = (String) clm.get("username");
                ObjectMapper mapper = new ObjectMapper();
                UserAuthModel logedinobj = mapper.convertValue(clm.get("logedinuser"),UserAuthModel.class);
                return logedinobj;

            } catch (Exception e) {
                //logger.severe("#### invalid token : " + token);
                return null;
            }
	    }
	}