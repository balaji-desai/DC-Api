package dc.businessmodel;

public class NotificationView {
private int NotificationViewId;
private String Title;
private String Message;
private boolean IsView;
private int NotificationType;

public int getNotificationViewId() {
	return NotificationViewId;
}
public String getTitle() {
	return Title;
}
public String getMessage() {
	return Message;
}
public boolean isIsView() {
	return IsView;
}
public void setNotificationViewId(int notificationViewId) {
	NotificationViewId = notificationViewId;
}
public void setTitle(String title) {
	Title = title;
}
public void setMessage(String message) {
	Message = message;
}
public void setIsView(boolean isView) {
	IsView = isView;
}
public int getNotificationType() {
	return NotificationType;
}
public void setNotificationType(int notificationType) {
	NotificationType = notificationType;
}

}
