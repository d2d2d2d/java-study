package practice01;

public class Prob5 {

	public static void main(String[] args) {
		for( int i = 1; i <=100; i++ ) {

			
			/*
			 * 코드 작성합니다.
			 */
			if(((i%10)%3==0 && i%10!=0) || (i/10)%3==0 && i>10)
				System.out.println(i+" 짝");
			
			
		}
	}
}
