package oneapi.test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class OneAPIServerSimulator implements Runnable {
	private int port = 8080;
	private boolean running = true;
	private ServerSocket server;
	private List<String> requests = new ArrayList<String>();
	private String postRequest;
	private String response = "dummy response";

	public OneAPIServerSimulator( int port ) {
		this.port = port;
	}

	@Override
	public void run() {
		try {
			if ( server == null)
				server = new ServerSocket( this.port );

			while ( running ) {
				Socket connection = null;
				try {
					connection = server.accept();
					// get request from client
					String request = getRequest( connection );
					requests.add( request );
					
					// get content (POST request)
					int postlen = parseContentLength(request);
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					char[] posted = new char[postlen];
					in.read(posted, 0, postlen);
					postRequest = new String(posted);
					System.out.println(postRequest);
					
					// return response to client
					OutputStream out = new BufferedOutputStream( connection.getOutputStream() );
									
					if (request.startsWith("POST")) {
						//out.write("HTTP/1.1 201 OK\r\nConnection: close\r\n\r\n".getBytes());
						out.write("HTTP/1.1 201 OK\r\nContent-Type: application/json;charset=UTF-8\r\nConnection: close\r\n\r\n".getBytes());
					} else if (request.startsWith("GET")) {
						out.write("HTTP/1.1 200 OK\r\nContent-Type: application/json;charset=UTF-8\r\nConnection: close\r\n\r\n".getBytes());
					} else if (request.startsWith("DELETE")) {
						out.write("HTTP/1.1 204 OK\r\nConnection: close\r\n\r\n".getBytes());
					}
							
					out.write(response.getBytes());
					out.flush(  );   	
					in.close();
				}
				catch ( IOException ex ) {
					if ( server.isClosed() == false ) {
						ex.printStackTrace();
					}
				}
				finally {
					if ( connection != null ) {
						connection.close();
					}
				}
			}
		}
		catch ( IOException e ) {
			System.err.println( "Could not start server: " + e.getMessage() );
		}
	}

	private int parseContentLength(String request) {
		int position = request.indexOf("Content-Length:");
		if ( position == -1) {
			return 0;
		}
		String contentLength = request.substring(position);
		contentLength = contentLength.split(" ")[1];
		contentLength = contentLength.split("\r\n")[0];
		return Integer.valueOf(contentLength);
	}

	 private String getRequest( Socket connection ) throws IOException {
		InputStream in = connection.getInputStream();
		StringBuilder request = new StringBuilder();
		int i;
		while( ( i = in.read() ) != -1 ) {
			request.append( (char) i );
			if( request.toString().endsWith( "\r\n\r\n" ) || request.toString().endsWith( "\n\n" ) ) {
				return request.toString();
			}
		}
		return request.toString();
	}

	public void release() {
		try {
			running = false;
			try {
				Thread.sleep( 100 );
			} catch (Exception ignore) {}

			if ( server != null && server.isClosed() == false )
				server.close();
		}
		catch ( IOException e ) {
			System.err.println( "Could not release server:" + e.getMessage() );
		}
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public List<String> getRequests() {
		return requests;
	}

	public String getPostRequest() {
		return postRequest;
	}
}
