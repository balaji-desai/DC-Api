package dc.setup;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import dc.providers.AuthenticationFilter;
import dc.providers.BeanValConstrainViolationExceptionMapper;
import dc.providers.GenericExceptionMapper;
import dc.providers.GsonMessageBodyHandler;
import dc.providers.ResponseFilter;
import dc.utility.TokenIssuer;

@ApplicationPath("/")
public class DCStartup extends ResourceConfig{
	
	public DCStartup(){
		packages(true,"dc");
		register(new ApplicationBinder());
		register(GsonMessageBodyHandler.class);
		register(AuthenticationFilter.class);
		register(ResponseFilter.class);
		register(TokenIssuer.class);
		register(GenericExceptionMapper.class);
		register(BeanValConstrainViolationExceptionMapper.class);
		register(MultiPartFeature.class);
	}
}
