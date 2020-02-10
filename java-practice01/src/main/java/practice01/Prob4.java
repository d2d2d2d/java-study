package practice01;

import java.util.Scanner;

public class Prob4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("문자열을 입력하세요 : ");

		/*
		 * 코드 작성합니다.
		 */
		String s = scanner.nextLine();
		for(int i=1; i<=s.length(); i++) {
			System.out.println(s.substring(0,i));
		}
		
		scanner.close();
	}

}
