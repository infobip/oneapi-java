package oneapi.listener;

import java.util.EventListener;
import oneapi.model.DeliveryReportList;

public interface DeliveryReportListener extends EventListener {
	public void onDeliveryReportReceived(DeliveryReportList deliveryReportList, Throwable error);
}
