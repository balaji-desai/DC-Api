package dc.providers;

import dc.businessmodel.ConstraintViolationEntity;
import dc.businessmodel.ExceptionModel;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	public Response toResponse(Throwable ex) {

		if(ex instanceof ConstraintViolationException)
		{
			System.out.println("BeanValConstrainViolationExceptionMapper in action");
			if(((ConstraintViolationException)ex).getConstraintViolations() != null)
			{
				String Message = null;
				ConstraintViolation cv = (ConstraintViolation) ((ConstraintViolationException) ex).getConstraintViolations().toArray()[0];
		        //oh yeah... you need to shell out some $$ !
		        return Response.status(Response.Status.BAD_REQUEST)
		                .entity(new ConstraintViolationEntity(cv.getMessage()))
		                .build();
			}else
			{
				return Response.status(Response.Status.BAD_REQUEST)
		                .entity(new ConstraintViolationEntity(ex.getMessage()))
		                .build();
			}
		}
		else{		
			ExceptionModel model = new ExceptionModel();
			model.setMessage(ex.getMessage());
			model.setCause(ex.toString());
			return Response.status(500)
					.entity(model)
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
		
		
	}

}
