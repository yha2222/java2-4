package prac.or.report;

public class P0729Thread {
	public static void main(String[] args) {
		AutoSaveThread autoSave = new AutoSaveThread();
		
		autoSave.setDaemon(true);
		autoSave.start();
		
		try {
			for(int i = 1; i <= 20; i ++) {
				System.out.println("작업 - " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main 스레드 종료...");
	}
}

class AutoSaveThread extends Thread {
	public void save() {
		System.out.println("저장...");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save();
		}
	}
}
