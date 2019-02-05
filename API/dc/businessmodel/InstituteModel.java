package dc.businessmodel;

import java.io.InputStream;

import javax.validation.constraints.NotNull;

public class InstituteModel {
	private int InstituteId;
	@NotNull(message="Institute Name Requierd")
	private String InstituteName;
	@NotNull(message="Institute Code Requierd")
	private String InstituteCode;
	@NotNull(message="Address Requierd")
	private String Address;
	private InputStream Logo;
	
	public int getInstituteId() {
		return InstituteId;
	}
	public void setInstituteId(int instituteId) {
		InstituteId = instituteId;
	}
	public String getInstituteName() {
		return InstituteName;
	}
	public void setInstituteName(String instituteName) {
		InstituteName = instituteName;
	}
	public String getInstituteCode() {
		return InstituteCode;
	}
	public void setInstituteCode(String instituteCode) {
		InstituteCode = instituteCode;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public InputStream getLogo() {
		return Logo;
	}
	public void setLogo(InputStream logo) {
		Logo = logo;
	}

}
