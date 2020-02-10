package prob6;

import java.util.Scanner;

public class Prob06 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while( true ) {

			/*  코드를 완성 합니다 */
			System.out.print(">>");
			String expr = scanner.nextLine();


			if("quit".equals(expr)) {
				break;
			}

			String[] tokens = expr.split(" ");

			if(tokens.length != 3) {
				System.out.println(">> 계산할 수 없는 연산식입니다.");
				continue;
			}

			int lValue = Integer.parseInt(tokens[0]);
			int rValue = Integer.parseInt(tokens[2]);
			int result = 0;
			if( "+".equals(tokens[1]) ) {
				Add add = new Add();
				add.setValue(lValue, rValue);
				result = add.calculate();
			}
			else if( "-".equals(tokens[1]) ) {
				Sub sub = new Sub();
				sub.setValue(lValue, rValue);
				result = sub.calculate();
			}
			else if( "/".equals(tokens[1]) ) {
				Div div = new Div();
				div.setValue(lValue, rValue);
				result = div.calculate();
			}

			else if( "*".equals(tokens[1]) ) {
				Product pro = new Product();
				pro.setValue(lValue, rValue);
				result = pro.calculate();
			}

			System.out.println(">>" + result);
		}

		scanner.close();
	}

}
