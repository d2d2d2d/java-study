package chapter03;

public class Student extends Person {
	public Student() {
		//자식 생성자에서 부모 생성자를 명시적으로 호출하지않으면 , 자동으로 부모의 기본 생성자를 호출 하게 된다.
		//super();
		//=컴파일러가 상속받았네? 없네? 하고 super(); 넣어줌. 부모생성자 중 기본 생성자를 가져옴.
		System.out.println("Student() called");
	}
}
