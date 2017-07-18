package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import dto.AssignmentInfo;

public class ClientConnectorService {

	
	public static final String ASSIGNMENT = "/assignment";
	public static final String INGEST="/ingest";
	private static final int SERVE_RPORT = 4020;

	public ObjectInputStream getServerConnection(String endPoint,String userText) throws UnknownHostException, IOException {

		InetAddress host = InetAddress.getByName("localhost");
		System.out.println("CONNECTING TO SERVER ON PORT " + SERVE_RPORT);

		Socket socket = new Socket(host, SERVE_RPORT);
		System.out.println("JUST CONNECTED TO " + socket.getRemoteSocketAddress());
		PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);

		if (ASSIGNMENT.equalsIgnoreCase(endPoint))
			toServer.println(endPoint);
		else if (INGEST.equalsIgnoreCase(endPoint)) {
			toServer.println(INGEST);
			toServer.println(userText);
		}
		ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
		return fromServer;
	}
	
	public List<AssignmentInfo> getAssignment(ObjectInputStream serverOutput) throws ClassNotFoundException, IOException{
		return (List<AssignmentInfo>) serverOutput.readObject();
	}
	
	public String getIngest(ObjectInputStream fromServer) throws IOException {
		String line = "";
		try {
			while ((line = (String) fromServer.readObject()) != null) {
				return line;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return line;
	}
}
