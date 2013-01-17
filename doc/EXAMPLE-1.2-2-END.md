		DeliveryInfo deliveryInfo = deliveryInfoNotification.getDeliveryInfo();
		String callbackData = deliveryInfoNotification.getCallbackData();         
		StringBuilder sb = new StringBuilder();
		sb.append("delivery notification:");
		sb.append("\nmessage for "+deliveryInfo.getAddress()+" has delivery status "+deliveryInfo.getDeliveryStatus());
		sb.append("\nmessage id is "+deliveryInfo.getMessageId()+" and client correlator is "+deliveryInfo.getClientCorrelator());    
		sb.append("\ncallback data is "+callbackData);
		String fname = getOutputFilePath("delivery-notification-"+System.currentTimeMillis());        
		IOUtils.write(sb.toString(),new FileOutputStream(fname));   
	}
