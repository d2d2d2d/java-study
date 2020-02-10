package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPTimeServer {
	private static final int PORT = 7000;
	private static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		
		try {
			//1. socket 생성
			socket = new DatagramSocket(PORT);
			
			while(true) {
				//2. 데이터 수신
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket); // blocking
				
				byte[] data = receivePacket.getData();
				int length = receivePacket.getLength();
				System.out.println(length);
				if(length==0) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time = format.format(new Date());
					data = time.getBytes();
				}
				String message = new String(data, 0, data.length, "UTF-8");
				
				System.out.println("[server] received:" + message);
				
				try {
					Thread.sleep(2000);	//TimeOut 실험
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//3. 데이터 송신
				byte[] sendData = message.getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
				socket.send(sendPacket);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
		}
	}
}