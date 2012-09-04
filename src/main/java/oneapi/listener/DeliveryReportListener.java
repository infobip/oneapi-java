package oneapi.listener;

import java.util.EventListener;

import oneapi.model.common.DeliveryReport;

public interface DeliveryReportListener extends EventListener {
	public void onDeliveryReportReceived(DeliveryReport[] deliveryReports, Throwable error);
}
