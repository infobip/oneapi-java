package oneapi.model.common;

import java.io.Serializable;
import java.util.Date;

public class DeliveryReport implements Serializable {
	private static final long serialVersionUID = 930827163463309979L;
	
	private String messageId = "";
	private Date sentDate;
	private Date doneDate;
	private String status;
	
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Date getSentDate() {
		return sentDate;
	}
	
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	public Date getDoneDate() {
		return doneDate;
	}
	
	public void setDoneDate(Date doneDate) {
		this.doneDate = doneDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DeliveryReport {messageId=" + messageId + ", sentDate="
				+ sentDate + ", doneDate=" + doneDate + ", status=" + status
				+ "}";
	}
}
