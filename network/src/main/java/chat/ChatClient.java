package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 5000;

	public static void main(String[] args) {

		Scanner scanner = null;
		Socket socket = null;
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		try {
			//1. 키보드 연결
			scanner = new Scanner(System.in);

			//2. Socket 생성
			socket = new Socket();

			//3. 연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));	//바인딩 에러나면 포트번호 바꿔주면 됨.
			System.out.println("connected");

			//4. reader/writer 생성
			// IOStream 생성
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"), true);

			//5. join 프로토콜
			String nickname = "";
			while(nickname.isEmpty()) {
				System.out.print("닉네임>>");
				nickname = scanner.nextLine();
			}
			printWriter.println("join:" + nickname);
			printWriter.flush();
			
			//6. ChatClientReceiveThread 시작
			new ChatClientReceiveThread(bufferedReader).start();

			//7. 키보드 입력 처리
			while(true) {
				String input = scanner.nextLine();
				//if(input.contain)
				String[] tokens = input.split(":");
				if(tokens.length==0) {
					printWriter.println(input);
				}else if("quit".equals(input)) {
					//8. 프로토콜 처리
					printWriter.println("quit");	//client->server '나 닫을거야!' s->c "ok" c(닫음)
					break;
				}else if (tokens[0].equals("whisper")) {
					printWriter.println(input);
				}else if (tokens[0].equals("kickOut")) {
					printWriter.println(input);
				}else{
					//9. 메세지 처리
					printWriter.println("message:"+input);	// 데이터 쓰기
				}
			}
		} catch (IOException e) {
			log("error:" + e);

		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
					if(bufferedReader != null) {
						bufferedReader.close();
					}
					if(printWriter != null) {
						printWriter.close();
					}
				}
				System.exit(0);
			} catch(IOException e) {
				//e.printStackTrace();
			}
		}
	}

	public static void log(String log) {
		System.out.println("[client]" + log);
	}

}
