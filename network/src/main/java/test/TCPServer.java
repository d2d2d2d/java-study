package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			//1. 서버소켓 생성
			serverSocket = new ServerSocket();
			
			//1-1. Time-Wait 시간에 소켓에 포트 번호 할당을 가능하게 하기 위해서
			serverSocket.setReuseAddress(true);

			//2. 바인딩: Socket Address(IP Address + Port) Binding 접속 허용	Port는 대충 문이라 선언.
			serverSocket.bind(new InetSocketAddress("127.0.0.1", 8000));	//서버가 사용할 IP주소와 포트번호를 생성한 소켓에 결합(bind)

			//3. accept
			Socket socket = serverSocket.accept();	// blocking. 기다리는거
			InetSocketAddress remoteInetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();

			//잘라서 저장해줌
			InetAddress remoteInetAddress = remoteInetSocketAddress.getAddress();	//"127.0.0.1", 5000를 사용하기 위해서 
			String remoteHostAddress = remoteInetAddress.getHostAddress();	//127.0.0.1ㅇ
			int remotePort = remoteInetSocketAddress.getPort();	//2095

			System.out.println("connected by client[" + remoteHostAddress + ":" + remotePort + "]");	//로그 남기면 누가 내꺼에 들어왔는지 알 수 있음
			try {
				//4. IOStream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while(true) {
					//5. 데이터 읽기
					byte[] buffer = new byte[256];

					int readByteCount = is.read(buffer);	// blocking
					if(readByteCount == -1) {	//0보다 크면 데이터가 들어왔다.
						// client에서 정상 종료 = close()를 호출했다
						System.out.println("[server]close by client");
						break;
					}

					String data = new String(buffer, 0, readByteCount, "UTF-8");
					System.out.println("[server]received:" + data);
					
					try {
						Thread.sleep(2000);	//TimeOut 실험
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					//6. 데이터 쓰기
					os.write(data.getBytes("UTF-8"));	//버퍼 바로 보내도 되는데 데이터받아서 써봄.
				}
			} catch(SocketException e) {
				System.out.println("[server] sudden closed by client");
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(socket != null && !socket.isClosed()) {
						socket.close();
					}
					socket.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
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

}
