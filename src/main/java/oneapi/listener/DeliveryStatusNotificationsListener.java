package oneapi.listener;

import java.util.EventListener;

import oneapi.model.DeliveryInfoNotification;

public interface DeliveryStatusNotificationsListener extends EventListener {
	public void onDeliveryStatusNotificationReceived(DeliveryInfoNotification deliveryInfoNotification);
}
