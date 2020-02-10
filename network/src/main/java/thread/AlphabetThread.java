package thread;

public class AlphabetThread extends Thread {

	@Override
	public void run() {
		for(char c = 'a'; c <= 'z'; c++) {
			System.out.print(c);
			try {
				Thread.sleep(1000);	//thread 싱크 맞추려면 각각 재웠다 꺠우면 됨.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

}
