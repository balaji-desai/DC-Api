package dc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolationException;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;

import dc.businessmodel.FormModel;
import dc.businessmodel.HallTicketInfo;
import dc.businessmodel.ResponceModel;
import dc.businessmodel.ResultModel;
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
	public Object SetHallTicket(HallTicketInfo param) throws Exception{
		try {
			return staffRepository.SetHallTicket(param, helper);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public Object GetSubject(HallTicketInfo param) throws Exception{
		try {
			return staffRepository.GetSubject(param, helper);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(ExceptionHelper.ErrorCheck(e))
			{
				throw  new ConstraintViolationException(ExceptionHelper.getErrorMessage(),null);
			}
			throw e;
		}
	}
	
	public ResponceModel VerifyStudentSubject(ResultModel param) throws Exception{
		try {
			ResponceModel model = new ResponceModel();
			param.setLaunchDateModel(UtilityFunctions.convertToDate(param.getLaunchDate()));
			SQLServerDataTable list = new SQLServerDataTable();
			list.addColumnMetadata("Id",java.sql.Types.NUMERIC);
			list.addColumnMetadata("Value",java.sql.Types.VARCHAR);
			list.setTvpName("IdValue");
		for (ResultModel pur : param.getSubjectList()) {
			if(pur != null && pur.getExamSeatNoStr() != null && !pur.getExamSeatNoStr().trim().equals(""))
			{				
				String[] strarr = pur.getExamSeatNoStr().split(",");
				for (String str : strarr) {	
					list.addRow(pur.getSubjectId(),str);
				}
			}
		}
			List<Object> output = staffRepository.VerifyStudentSubject(param,list, helper);
			String value = (String)output.get(1);
			if(value.equals("true"))
			{
				model.setCode(1);
				if(((List<Object>)output.get(0)).size() > 0)
				{					
					model.setResponce(((List<Object>)output.get(0)).get(0));
				}
			}
			else{
				model.setCode(0);
				model.setResponce(output.get(0));
			}
			return model;
			
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
