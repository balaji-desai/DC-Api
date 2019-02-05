package dc.service;


import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolationException;

import dc.businessmodel.InstituteModel;
import dc.businessmodel.UserAuthModel;
import dc.repository.AccountRepository;
import dc.utility.ExceptionHelper;
import dc.utility.JDBCHelper;

@Singleton
public class AccountService {

	@Inject
	AccountRepository accountrepository;

	@Inject
	public JDBCHelper helper;
	
	public UserAuthModel GetUser(String UserName, String Password){
		UserAuthModel accvm = null;
		try {
			accvm = accountrepository.GetUser(UserName, Password, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			e.printStackTrace();
		}
		return accvm;
	}
	
	public void SetToken(String Token, int UserId){
		try {
			accountrepository.SetToken(Token, UserId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			e.printStackTrace();
		}
	}
	
	public void logout(int userid)
	{
		try {
			accountrepository.Logout(userid, helper);
			
		} catch (Exception e) {
			// TODO: handle exception
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
		}
	}
	
	public InstituteModel GetInstitue(int instituteId){
		InstituteModel accvm = null;
		try {
			accvm = accountrepository.GetInstitute(instituteId, helper);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			e.printStackTrace();
		}
		return accvm;
	}
}
