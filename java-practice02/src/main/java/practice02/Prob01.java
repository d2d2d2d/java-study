package practice02;

import java.util.Scanner;

public class Prob01 {

	public static void main(String[] args) {
		final int[] MONEYS = { 50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1 };

		Scanner scanner = new Scanner(System.in);

		System.out.print("금액:");

		/*
		 * 코드 작성합니다.
		 */
		//int[] MONEYS_COUNT = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] MONEYS_COUNT = new int[MONEYS.length];

		int input = scanner.nextInt();
		for(int i=0; i<MONEYS.length; i++)
			while(input>=MONEYS[i]) {
				MONEYS_COUNT[i]++;
				input-=MONEYS[i];
			}
		for(int i=0; i<MONEYS.length; i++)
			System.out.println(MONEYS[i]+"원 : "+MONEYS_COUNT[i]+"개");

		scanner.close();
	}

}
