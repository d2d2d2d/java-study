package thread;

public class DigitalThread extends Thread {

	@Override
	public void run() {
		for(int i = 1; i <= 10; i++ ) {
			System.out.print(i);
			try {
				Thread.sleep(1000);	//thread 싱크 맞추려면 각각 재웠다 꺠우면 됨.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

}
