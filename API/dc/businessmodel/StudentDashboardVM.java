package dc.businessmodel;

import java.util.List;

public class StudentDashboardVM {
private boolean ACK;
private boolean Correction;
private String Note;
private List<NotificationView> Notifications;

public boolean isACK() {
	return ACK;
}

public void setACK(boolean aCK) {
	ACK = aCK;
}

public String getNote() {
	return Note;
}

public void setNote(String note) {
	Note = note;
}

public boolean isCorrection() {
	return Correction;
}

public void setCorrection(boolean correction) {
	Correction = correction;
}

public List<NotificationView> getNotifications() {
	return Notifications;
}

public void setNotifications(List<NotificationView> notifications) {
	Notifications = notifications;
}
}
