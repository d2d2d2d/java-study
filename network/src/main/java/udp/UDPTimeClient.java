package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UDPTimeClient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 7000;
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		Scanner scanner = null;
		DatagramSocket socket = null;
		byte[] sendData;
		
		try {
			//1. Scanner 생성(표준입력, 키보드 연결)
			scanner = new Scanner(System.in);
			
			//2. Socket 생성
			socket = new DatagramSocket();	//datagram은 특정한 목적지 주소로 생성되지 않음.(TCP와 UDP의 중요한 차이점)
			
			// SO_TIMEOUT
			//socket.setSoTimeout(1000);
			
			while(true) {
				//3. 키보드 입력 받기
				System.out.print(">>");
				String line = scanner.nextLine();
				if("quit".equals(line)) {
					break;
				}
				
				//4. 데이터 쓰기
				sendData = line.getBytes("UTF-8");
				// 데이터그램패킷 new byte[0], 0, new InetSocketAddress(SERVER_IP, SERVER_PORT));
				DatagramPacket sendPacket = new DatagramPacket(
					sendData, 
					sendData.length,
					new InetSocketAddress(SERVER_IP, SERVER_PORT));
				socket.send(sendPacket);
				
				//5. 데이터 읽기
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket); // blocking
				
				byte[] data = receivePacket.getData();
				int length = receivePacket.getLength();
				String message = new String(data, 0, length, "UTF-8");
				
				System.out.println("<<" + message);
			}
		} catch(SocketTimeoutException e) {
			System.out.println("[client] time out");
		} catch(SocketException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) {
				scanner.close();
			}
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
		}
	}
}
