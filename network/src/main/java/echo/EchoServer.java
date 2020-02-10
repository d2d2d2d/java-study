package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	private static int PORT = 8000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			//1. 서버소켓 생성
			serverSocket = new ServerSocket();

			//2. 바인딩: Socket Address(IP Address + Port) Binding 접속 허용	Port는 대충 문이라 선언.
			serverSocket.bind(new InetSocketAddress("127.0.0.1", PORT));
			log("Server Starts...[Port:" + PORT + "]");

			//3. accept
			while(true) {
				Socket socket = serverSocket.accept();	// blocking. 기다리는거
				InetSocketAddress remoteInetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void log(String log) {
		System.out.println("[server#]" +Thread.currentThread().getId() + log);
	}
}

