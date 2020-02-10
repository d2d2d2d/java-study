package chapter03;

public class StaticMethodtest {

	public static void main(String[] args) {
		int a = Math.abs(-1);	//abs가 static method임
		int b = Math.max(10,  20);
		System.out.println(a + ":" + b);

	}

}
