package oneapi.listener;

import java.util.EventListener;

import oneapi.model.RoamingNotification;

public interface HLRNotificationsListener extends EventListener {
	public void OnHLRReceived(RoamingNotification roamingNotification);
}
