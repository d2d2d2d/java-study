package chat.client.win;
import java.util.Scanner;

public class ChatClientApp {

	public static void main(String[] args) {
		String name = null;
		Scanner scanner = new Scanner(System.in);

		while( true ) {
			
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();
			
			if (name.isEmpty() == false ) {
				break;
			}
			
			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		
		scanner.close();
		
		//1. socket 생성
		//2. connetct to server
		//3. ioStream 생성
		//4. join 프로코톨 요청 및 처리
		//5. join 프로토콜이 성공 응답을 받으면
		//new ChatWindow(name, socket).show();
		new ChatWindow(name).show();
	}

}
