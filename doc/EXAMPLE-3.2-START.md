	 @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestData = readData(request.getInputStream());
        SMSClient smsClient = createSmsClient();
