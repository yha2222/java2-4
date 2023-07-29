package kr.or.ddit.basic;

public class T19WaitNotifyTest {
/*
 	wait() => 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별로 존재)으로 이동 시킴
 	notify() 또는 notifyAll() => Wait-Set영역에 있는 스레드를 깨워서 실행할 수 있는 상태로 만듦
 	(notify()는 하나, notifyAll()은 전부를 깨움)
 	
 	=> wait()과 notify(), notifyAll()은 동기화 영역에서만 의미가 있고
 		Object 클래스에서 제공하는 메서드이다
 */
	
	public static void main(String[] args) {
		
		WorkObject workObj = new WorkObject();
		
		Thread thA = new ThreadA(workObj);
		Thread thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

//공통으로 사용할 객체
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA()에서 작업 중...");
		
		System.out.println(Thread.currentThread().getName()
				+ " : notify() 호출");
		notify();  //자는 애(B) 깨우고 대기실ㄱ
		
		try {
			System.out.println(Thread.currentThread().getName()
					+ " : wait() 호출");
			wait(5000);
		}catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB()에서 작업 중...");
		
		System.out.println(Thread.currentThread().getName()
				+ " : notify() 호출");
		notify();
		
		try {
			System.out.println(Thread.currentThread().getName()
					+ " : wait() 호출");
			wait(5000);
		}catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}

// workObject의 methodA()만 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		super("ThreadA");
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 5; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료");
	}
}

//workObject의 methodB()만 호출하는 스레드
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		super("ThreadB");
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 5; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료");
	}
}
