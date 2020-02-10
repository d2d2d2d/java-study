package chat;

import java.io.Writer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import echo.EchoServer;

public class ChatServerThread extends Thread{

	private String nickname;
	private Socket socket;

	HashMap<String, Writer> writerHash;	//데이터 통신 스레드들에서 이 list를 공유해야하기 떄문에 스레드에 list객체를 참조하는 변수를 추가.
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;

	private String[] swearWord = {"ㅅㅂ", "ㅂㅅ"};
	private String[] chatCommand = {"join", "message", "quit", "whisper", "kickOut"};



	public ChatServerThread(Socket socket, HashMap<String, Writer> writerHash) {
		this.socket = socket;
		this.writerHash = writerHash;
	}

	@Override
	public void run() {
		//reader를 통해 읽은 데이터 콘솔에 출력하기(message 처리)
		//1. Remote Host Information
		InetSocketAddress remoteInetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
		int remotePort = remoteInetSocketAddress.getPort();
		String remoteHostAddress = remoteInetSocketAddress.getAddress().getHostAddress();
		EchoServer.log("connected by client[" + remoteHostAddress + ":" + remotePort+ "]");


		try {
			//2. 스트림 얻기
			bufferedReader = new BufferedReader( new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

			//3. 요청 처리
			while(true) {
				String request = bufferedReader.readLine(); //blocking
				if(request == null) {
					ChatServer.log("클라이언트로 부터 연결 끊김");
					doQuit(printWriter);
					break;
				}
				//4. 프로토콜 분석
				// 각 요청을 구분하는 경계가 되는 것은 \r\n
				//요청은 ":"기준으로 요청 명령과 파라미터로 분리.
				String[] tokens = request.split(":");
				if(tokens.length==0) {
					doMessage(request);
				} else if(tokens[0].equals("join")) {
					doJoin( tokens[1], printWriter);
				} else if(tokens[0].equals("message")) {
					if(tokens.length==1) {
						continue;
					} else {
						// 비속어 처리
//						for(int i = 0 ; i < swearWord.length; i++) {
//							if(tokens[1].contains(swearWord[i])) {
//								tokens[1] = tokens[1].replace(swearWord[i], "@@");
//							}
//						}
						doMessage(tokens[1]);
					}
				} else if(tokens[0].equals("quit")) {
					doQuit(printWriter);
				} else if(tokens[0].equals("whisper")) {
					if(tokens.length != 3) {
						printWriter.println("\"whisper:귓속말할상대:메세지\" 를 보내주세요");
						continue;
					} else {
						if(HasWriter(tokens[1])) {
							doWhisper(tokens[1], tokens[2]);
						}
					}
				} else if(tokens[0].equals("kickOut")) {
					if(tokens.length != 2) {
						printWriter.println("\"KickOut:강퇴할상대\" 를 보내주세요");
						continue;
					} else {
						if(HasWriter(tokens[1])) {
							doKickOut(tokens[1]);
						}
					}
//				} else if(!tokens[0].equals("quit")) {//@@@
//					
				} else {
					ChatServer.log("에러: 알수 없는 요청(" + tokens[0] + ")");
				}
			}
		} catch(SocketException e) {
			EchoServer.log("suddenly closed by client");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean HasWriter(String name) {
		if(!writerHash.containsKey(name)) {
		printWriter.println("에러: " + name+"님이 존재하지 않습니다.");
		return false;
		}
		else {
			return true;
		}
	}

	private void doKickOut(String toName) {
		System.out.println("강퇴");
		String fromName = nickname;
		String data = fromName + "님이" + toName + "님을  강퇴시켰습니다.";
		broadcast(data);

		PrintWriter pw = (PrintWriter) writerHash.get(toName);
		removeWriter(pw);	//writerHash만 제거됨.
		pw.println("quit");
	}

	private void doWhisper(String toName, String message) {
		String fromName = nickname;
		//버퍼에 개행이 안들어가서 write->println으로 바꿈.
		((PrintWriter) writerHash.get(toName)).println("[" + fromName + "님이 보낸 귓속말]" + message);
		((PrintWriter) writerHash.get(fromName)).println("[" + toName + "에게 보낸 귓속말]" + message);

	}

	private void doMessage(String message) {
		// 구현하기
		//프로토콜 "message:하이 ^^;\r\n"
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(new Date());
		broadcast("["+time+"]" + nickname + "님: " + swearWordFiltering(message));
	}

	private String swearWordFiltering(String message) {
		for(int i = 0 ; i < swearWord.length; i++) {
			if(message.contains(swearWord[i])) {
				message = message.replace(swearWord[i], "@@");
			}
		}
		return message;
		
	}

	private void doJoin(String nickName, Writer writer) {
		this.nickname = nickName;

		String data = nickName + "님이 참여하였습니다.";
		broadcast(data);

		//writerPool에 저장
		addWriter(writer);	

		//ack
		printWriter.println("환영합니다.");
		printWriter.println("귓속말을 원하시면  whisper:귓속말상대:메세지");
		printWriter.println("강퇴를 원하시면 kickOut:강퇴할상대");
		printWriter.println("채팅을 종료하려면 quit을 입력해주세요.");
		printWriter.println("ㅅㅂ, ㅂㅅ등의 욕설은 필터링 됩니다.");
		printWriter.flush();
	}

	private void addWriter(Writer writer) {
		synchronized(writerHash) {
			writerHash.put(nickname, writer);
		}
	}

	private void broadcast(String data) {
		synchronized (writerHash) {
			for(String key : writerHash.keySet()) {
				PrintWriter printWriter = (PrintWriter)writerHash.get(key);
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

	private void doQuit(Writer writer) {
		((PrintWriter) writer).println("quit");
		removeWriter(writer);

		String data = nickname + "님이 퇴장 하였습니다.";
		broadcast(data);
	}

	private void removeWriter(Writer writer) {
		// 구현하기
		// 현재 스레드의 writer를 WriterPool에서 제거
		synchronized(writerHash) {
			writerHash.remove(writer);
		}
	}
}
