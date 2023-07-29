package prac.or.report;

public class P0729DaemonThread {
	public static void main(String[] args) {
//		TestDaemonThread t1 = new TestDaemonThread();
//		TestDaemonThread t2 = new TestDaemonThread();
//		
//		//t1을 데몬으로 설정
//		t1.setDaemon(true);
//		
//		//스레드 시작
//		t1.start();
//		t2.start();
		
		TestIllegal test = new TestIllegal();
		test.start();
		
		//예외 발생
		test.setDaemon(true);
	}
	
}

class TestDaemonThread extends Thread {
	public void run() {
		//데몬 스레드인지 확인
			if(Thread.currentThread().isDaemon()) {
				while(true) {
					try {
						// 데몬 스레드인 경우에는 100ms 지연되어 실행되도록 함
						// => 출력 안됨(JVM이 무시)
						Thread.sleep(100L);
						System.out.println("Daemon thread running");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}else {
				System.out.println("Normal thread executing");
			}
	}
}

class TestIllegal extends Thread {
	@Override
	public void run() {
		System.out.println("Thread is running");
	}
}
