		InboundSMSMessage[] inboundSMSMessages = inboundSMSMessageList.getInboundSMSMessage();
		StringBuilder sb = new StringBuilder();
		sb.append("inbound messages recevied");
		for (InboundSMSMessage inboundSMSMessage : inboundSMSMessages) {
		    sb.append("\ndate and time: "+inboundSMSMessage.getDateTime());
		    sb.append("\nDestinationAddress: "+inboundSMSMessage.getDestinationAddress());
		    sb.append("\nMessageId: "+inboundSMSMessage.getMessageId());
		    sb.append("\nMessage: "+inboundSMSMessage.getMessage());
		    sb.append("\nResourceURL: "+inboundSMSMessage.getResourceURL());
		    sb.append("\nSenderAddress: "+inboundSMSMessage.getSenderAddress());
		}
		String fname = getOutputFilePath("inbound-message-"+System.currentTimeMillis());        
		IOUtils.write(sb.toString(),new FileOutputStream(fname));
	}
