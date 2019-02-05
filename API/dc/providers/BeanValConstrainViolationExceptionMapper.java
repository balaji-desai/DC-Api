package dc.providers;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import dc.businessmodel.ConstraintViolationEntity;
@Provider
public class BeanValConstrainViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	public Response toResponse(ConstraintViolationException e) {
		// TODO Auto-generated method stub
		System.out.println("BeanValConstrainViolationExceptionMapper in action");
		if(e.getConstraintViolations() != null)
		{
			String Message = null;
			ConstraintViolation cv = (ConstraintViolation) e.getConstraintViolations().toArray()[0];
	        //oh yeah... you need to shell out some $$ !
	        return Response.status(Response.Status.BAD_REQUEST)
	                .entity(new ConstraintViolationEntity(cv.getMessage()))
	                .build();
		}else
		{
			return Response.status(Response.Status.BAD_REQUEST)
	                .entity(new ConstraintViolationEntity(e.getMessage()))
	                .build();
		}
        
	}

}