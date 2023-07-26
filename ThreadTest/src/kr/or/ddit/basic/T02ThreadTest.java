package kr.or.ddit.basic;

public class T02ThreadTest {
	public static void main(String[] args) {
		
		//멀티스레드 프로그램 방식
		
		//방법1 : Thread 클래스를 상속한 클래스의 인스턴스를 생성한 후
		//		이때 생성된 Thread 객체의 start() 메서드를 호출하여 실행시킨다.
		Thread th1 = new MyThread1();
		th1.start();
		
		//방법2 : Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 후
		//		이 인스턴스를 Thread 클래스의 생성자를 호출하면서 매개변수로 넘겨준다.
		//		이때 생성된 Thread 객체의 start() 메서드를 호출하여 실행시킨다.
		Runnable r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.start();
		
		//방법3 : 익명클래스를 이용하는 방법
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 1; i <= 200; i++) {
					System.out.print("@");
					
					try {				
						//Thread.sleep(시간) => 주어진 시간 동안 작업을 잠시 멈춘다.
						//시간은 밀리세컨드 단위를 사용. 즉, 1000은 1초 의미
						Thread.sleep(100);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
				}	
			}
		});
		th3.run();
	}
}

class MyThread1 extends Thread {
	
	@Override
	public void run() {
		
		for(int i = 1; i <= 200; i++) {
			System.out.print("*");
			
			try {				
				//Thread.sleep(시간) => 주어진 시간 동안 작업을 잠시 멈춘다.
				//시간은 밀리세컨드 단위를 사용. 즉, 1000은 1초 의미
				Thread.sleep(100);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}

class MyThread2 implements Runnable { //Runnable 객체 - void run() 무조건

	@Override
	public void run() {
		for(int i = 1; i <= 200; i++) {
			System.out.print("$");
			
			try {				
				//Thread.sleep(시간) => 주어진 시간 동안 작업을 잠시 멈춘다.
				//시간은 밀리세컨드 단위를 사용. 즉, 1000은 1초 의미
				Thread.sleep(100);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}