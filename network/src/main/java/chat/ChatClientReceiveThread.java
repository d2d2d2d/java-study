package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatClientReceiveThread extends Thread {
	private BufferedReader bufferedReader;

	ChatClientReceiveThread(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	@Override
	public void run() {
		//reader를 통해 읽은 데이터 콘솔에 출력하기
		try {
			while(true) {
				String msg = bufferedReader.readLine();
				if(msg == null) {
					ChatClient.log("메세지가 없습니다.");
					break;
				}
				else if(msg.equals("quit")) {
					ChatClient.log("종료합니다.");
					break;
				}
				System.out.println(msg);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bufferedReader != null) {
					bufferedReader.close();
				}
				System.exit(0);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

}
