package dc.businessmodel;

public class ConstraintViolationEntity {

	private String message;
	public ConstraintViolationEntity(String _message){
		this.setMessage(_message);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
