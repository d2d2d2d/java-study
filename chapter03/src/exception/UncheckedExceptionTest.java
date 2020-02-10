package exception;

public class UncheckedExceptionTest {

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		for(int i = 0; i <= a.length; i++) {	//이 exception은 try-catch하면 안되고 오류를 고쳐야함.
			System.out.println(a[i]);
		}

	}

}
