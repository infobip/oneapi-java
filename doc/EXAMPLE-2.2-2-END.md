		StringBuilder sb = new StringBuilder();        
		sb.append("HLR notification");        
		sb.append("\nservingMccMnc: " + roaming.getServingMccMnc());
		sb.append("\naddress: " + roaming.getAddress());
		sb.append("\ncurrentRoaming: " + roaming.getCurrentRoaming());
		sb.append("\nresourceURL: " + roaming.getResourceURL());
		sb.append("\nretrievalStatus: " + roaming.getRetrievalStatus());
		sb.append("\ncallbackData: " + roaming.getCallbackData());
		sb.append("\nextendedData: " + roaming.getExtendedData());
		sb.append("\nIMSI: " + roaming.getExtendedData().getImsi());
		sb.append("\ndestinationAddres: " + roaming.getExtendedData().getDestinationAddress());
		sb.append("\noriginalNetworkPrefix: " + roaming.getExtendedData().getOriginalNetworkPrefix());
		sb.append("\nportedNetworkPrefix: " + roaming.getExtendedData().getPortedNetworkPrefix());
		String fname = getOutputFilePath("hlr-notification-"+System.currentTimeMillis());        
		IOUtils.write(sb.toString(),new FileOutputStream(fname));
	}
