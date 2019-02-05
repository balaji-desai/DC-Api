package dc.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import dc.businessmodel.UserAuthModel;
import dc.jwtutil.KeyGenerator;

@Singleton
public class TokenIssuer {
		
	@Context
    private UriInfo uriInfo;
	
	@Inject
	private KeyGenerator keyGenerator;
	
	//Issue New Token.
	public String tokenissue(String skey, UserAuthModel logedinobj){
		
		Key key = keyGenerator.generateKey(skey);
        //Add Claims Here In Key Value Pair.
		Map<String,Object> map = new HashMap<String,Object>();
		logedinobj.setAuthToken(null);
        map.put("logedinuser",logedinobj );
        
        String jwtToken = Jwts.builder()
        		.setClaims(map)
                .setSubject("login")
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(60L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        //logger.info("#### generating token for a key : " + jwtToken + " - " + key);
        return jwtToken;

	}
	
	//Get Date From Default System ZoneId
	private static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
