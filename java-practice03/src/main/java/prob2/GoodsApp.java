package prob2;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		String input = "";
		String[] array = new String[3];
		Goods[] goods = new Goods[COUNT_GOODS];
		
		for(int i=0; i<3; i++) {
			goods[i] = new Goods();
			input = scanner.nextLine();
			array = input.split(" ");
			goods[i].setName(array[0]);
			goods[i].setPrice(Integer.parseInt(array[1]));
			goods[i].setCount(Integer.parseInt(array[2]));
		}
		for(int i=0; i<3; i++)
		System.out.println(goods[i].getName()+"(가격:"+goods[i].getPrice()+"원)이 "+goods[i].getCount()+"개 입고 되었습니다.");


		scanner.close();
	}
}
