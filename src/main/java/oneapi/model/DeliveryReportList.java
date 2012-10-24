package oneapi.model;

import java.util.Arrays;

import oneapi.model.common.DeliveryReport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryReportList {
	
	private DeliveryReport[] deliveryReports;

	@JsonProperty("deliveryReportList")
	public DeliveryReport[] getDeliveryReports() {
		return deliveryReports;
	}

	@JsonProperty("deliveryReportList")
	public void setDeliveryReports(DeliveryReport[] deliveryReports) {
		this.deliveryReports = deliveryReports;
	}

	@Override
	public String toString() {
		return "DeliveryReportList {deliveryReports="
				+ Arrays.toString(deliveryReports) + "}";
	}
}
