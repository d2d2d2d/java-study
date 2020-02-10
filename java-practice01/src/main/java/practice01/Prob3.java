package practice01;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while (true) {
			/*
			 * 코드 작성합니다.
			 */
			int result=0;
			System.out.print("숫자를 입력하세요: ");
			int num = scanner.nextInt();
			if(num%2==1)
				for(int i=1; i<=num; i+=2)
					result += i;
			else
				for(int i=0; i<=num; i+=2)
					result += i;
			System.out.println("결과 값 : "+result);
		}
	}
}
