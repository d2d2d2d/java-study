package chapter03;

public class Goods2 {
	private String name; 	
	private int price;	
	private int countSold;			
	private int countStock;
	public String getCountSold;
	
	public Goods2() {
		//생성자 하나라도 추가하면 기본 생성자 넣어주지 않음.
	}
	
	public Goods2(String name, int price, int countSold, int countStock) {
		this.name = name;
		this.price = price;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCountSold() {
		return countSold;
	}
	public void setCountSold(int countSold) {
		this.countSold = countSold;
	}
	public int getCountStock() {
		return countStock;
	}
	public void setCountStock(int countStock) {
		this.countStock = countStock;
	}	
	public void showInfo() {
		System.out.println();
	}
	public int calcDiscountPrice(double discountRate) {
		return (int) (price - price*discountRate);	//double*double = double 큰데서 작은데로 갈때 테스팅 필요
	}
}
