package dc.utility;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHelper {
	
	private static String errorMessage;
	private static Map<String,String> error = new HashMap<String, String>();
	
	static
	{
		//Add Error Key And Message Here.
		error.put("Invalid_Login", "Invalid Username/Password.");
		error.put("User_Logout", "User Logout");
		error.put("User_Exists", "User Already Exists with same CONTACT NO.");
		error.put("HOD_Exists", "HOD Already Assign.");
		error.put("Subject_Exists", "Subject Already Added");
		error.put("date_validation", "End Date must be greate than or equal to start date.");
		error.put("user_incompleteprofile", "Form not submited due to incomplete profile.");
		error.put("user_profilenotverify", "Form not submited because profile is not verified.");
		error.put("form_notfound", "Form not found.");
		error.put("form_alreadyverified", "Form already verified.");
		error.put("form_notsubmit", "Form not submited.");
		error.put("Form_Subjectnotavailable", "Subject not available please contact with respective department HOD");
	}
	
	public static boolean ErrorCheck(Exception ex)
	{
		if(ex.getCause() != null && error.containsKey(ex.getCause().getMessage()))
		{
			errorMessage = error.get(ex.getCause().getMessage());
			return true;
		}
		else{			
			return false;
		}
	}
	
	public static String getErrorMessage() {
		return errorMessage;
	}

}
