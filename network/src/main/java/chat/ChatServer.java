package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ChatServer {
	private static int PORT = 5000;
	private static String hostAddress = "127.0.0.1";
	private static HashMap<String, Writer> WriterHash = new HashMap<String, Writer>();
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			//1. 서버소켓 생성
			serverSocket = new ServerSocket();

			//2. 바인딩
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			log("연결 기다림" + hostAddress + ":" + PORT);

			//3. 요청대기
			while(true) {
				Socket socket = serverSocket.accept(); //blocking
				//스레드 실행
				new ChatServerThread(socket, WriterHash).start();
			}
		}catch (IOException e) {
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
