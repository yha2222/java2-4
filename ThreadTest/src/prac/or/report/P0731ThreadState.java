package prac.or.report;

public class P0731ThreadState {
	
	public static void main(String[] args) {
		Target target = new Target();
		
		StatePrintThread printThread = new StatePrintThread(target);
		
		printThread.start();
	}
}

// 상태 모니터링 대상 스레드
class Target extends Thread {
	@Override
	public void run() {
		// runnable
		for(long i = 1; i <= 1000000000L; i++) {}
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(long i=1; i <= 1000000000L; i++) {}  // 시간 지연?????
	}
}

// 스레드 상태를 모니터링 하는 스레드
class StatePrintThread extends Thread {
	private Thread target;
	
	public StatePrintThread(Thread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true) {
			//스레드 상태값
			Thread.State state = target.getState();
			System.out.println("타겟스레드의 상태값: " + state);
			
			//new 상태인지 검사-처음만 하고 다시 돌아가는 경우 없음
			if(state == Thread.State.NEW) {
				target.start(); //스레드 시작
			}
			
			//스레드 종료됐는지 검사
			 if(state == Thread.State.TERMINATED) {
				 break;
			 }
			 
			 //monitoring
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}