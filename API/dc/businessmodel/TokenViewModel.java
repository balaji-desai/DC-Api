package dc.businessmodel;

import java.time.LocalDateTime;

public class TokenViewModel {
private String AccessToken;
private LocalDateTime issuedate;
private String issuername;

public String getAccessToken() {
	return AccessToken;
}
public void setAccessToken(String accessToken) {
	AccessToken = accessToken;
}
public String getIssuername() {
	return issuername;
}
public void setIssuername(String issuername) {
	this.issuername = issuername;
}
public LocalDateTime getIssuedate() {
	return issuedate;
}
public void setIssuedate(LocalDateTime issuedate) {
	this.issuedate = issuedate;
}
}
