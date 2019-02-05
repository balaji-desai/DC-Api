package dc.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolationException;

import dc.businessmodel.FormModel;
import dc.repository.StaffRepository;
import dc.utility.ExceptionHelper;
import dc.utility.JDBCHelper;
import dc.utility.UtilityFunctions;
@Singleton
public class StaffService {
	@Inject
	private StaffRepository staffRepository;
	@Inject
	public JDBCHelper helper;
	
	public void CreateForm(FormModel model) throws Exception{
		try {
			model.setFillStartDateModel(UtilityFunctions.convertToDate(model.getFillStartDate()));
			model.setFillEndDateModel(UtilityFunctions.convertToDate(model.getFillEndDate()));
			staffRepository.CreateForm(model, helper);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public void VerifyForm(int instituteId, int formId, int studentId) throws Exception{
		try {
			staffRepository.VerifyForm(instituteId, formId, studentId, helper);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public void AckVerificaton(int instituteId, int formId, int studentId) throws Exception{
		try {
			staffRepository.AckVerificaton(instituteId, formId, studentId, helper);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
}
