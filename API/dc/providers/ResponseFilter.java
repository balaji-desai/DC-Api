package dc.providers;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
@Provider
public class ResponseFilter implements javax.ws.rs.container.ContainerResponseFilter  {

	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		// TODO Auto-generated method stub
		MultivaluedMap<String, Object> responseHeaders = responseContext.getHeaders();
       
            responseContext.getHeaders().add(
                    "Access-Control-Allow-Origin", "*");
                  responseContext.getHeaders().add(
                    "Access-Control-Allow-Credentials", "true");
                  responseContext.getHeaders().add(
                   "Access-Control-Allow-Headers",
                   "origin, content-type, accept, authorization");
                  responseContext.getHeaders().add(
                    "Access-Control-Allow-Methods", 
                    "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	}

}
