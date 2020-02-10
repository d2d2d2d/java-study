package myPackage;

public class Goods {	//필드만 정의
	public static int countOfGoods;
	
	public Goods() {
		Goods.countOfGoods++;
	}
	
	public String name; 	// 모든 접근 가능(접근 제한이 없음
	protected int price;	// 같은 패키지 + 자시 접근 가능
	int countSold;			// 같은 패키지에서만 접근 가능
	private int countStock;	// 하나의 클래스에서만 접근 가능
}
