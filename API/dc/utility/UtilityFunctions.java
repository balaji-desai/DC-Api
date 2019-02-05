package dc.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

import javax.validation.ConstraintViolationException;

import dc.businessmodel.DateModel;
import dc.jwtutil.KeyGenerator;

public class UtilityFunctions {
	 
	 public static String Decrypt(String pass,String secretkey,KeyGenerator keyGenerator) throws Exception
	 {
		 Key key = keyGenerator.generateKey(secretkey);
         //validate Token And Get Claims.
         Claims clm = Jwts.parser().setSigningKey(key).parseClaimsJws(pass).getBody();
         String passwd = (String) clm.get("password");
         return passwd;
	 }
	 
		public static Date convertToDate(DateModel model)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			if(model.getYear() == 0)
			{
				throw new ConstraintViolationException("invalid date",null);
			}
			if(model.getDay() == 0)
			{
				throw new ConstraintViolationException("invalid date",null);
			}
			if(model.getMonth() == 0)
			{
				throw new ConstraintViolationException("invalid date",null);
			}
			LocalDateTime datetime =  LocalDateTime.now(ZoneOffset.UTC);
			datetime = datetime.withDayOfMonth(model.getDay());
			datetime = datetime.withMonth(model.getMonth());
			datetime = datetime.withYear(model.getYear());
			datetime = datetime.withHour(0);
			datetime = datetime.withMinute(0);
			return toDateUTC(datetime);
		}
		
		public static Date toDateUTC(LocalDateTime localDateTime) {
	        return Date.from(localDateTime.atZone(ZoneOffset.ofHoursMinutes(5, 30)).toInstant());
	    }


	 
}
