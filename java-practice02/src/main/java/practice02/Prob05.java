package practice02;

import java.util.Random;
import java.util.Scanner;

public class Prob05 {


	public static void main(String[] args) {

		/*
		 * 코드 작성합니다.
		 */

		Random r = new Random();

		game:
		while(true) {
			Scanner sc = new Scanner(System.in);
			int i=1, j=100, count=1, input;
			String answer;
			int k = r.nextInt(100) + 1;
			System.out.println(k);
			System.out.println("수를 결정하였습니다. 맞추어 보세요");
			while(true){
				System.out.println(i+"-"+j);
				System.out.print(count+">>");
				input = sc.nextInt();
				if(input<k) {
					System.out.println("더 높게");
					i=input;
				}
				else if(input>k) {
					System.out.println("더 낮게");
					j=input;
				}
				else if(input==k) {
					System.out.println("맞았습니다.");
					System.out.print("다시 하시겠습니까(y/n)");
					answer = sc.next();
					if(answer.equals("y"))
						break;
					else if(answer.equals("n"))
						break game;
				}
			}
		}
	}
}
