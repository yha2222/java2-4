package prac.or.report;

import java.util.Random;

public class P0729ThreadNRunnable {
	public static void main(String[] args) {
		// create thread objects
		Thread th1 = new MyThread();
		th1.setName("Thread #1");
		Thread th2 = new MyThread();
		th2.setName("Thread #2");
		
		// create runnable objects
		Runnable run1 = new MyRunnable();
		Runnable run2 = new MyRunnable();
		
		Thread th3 = new Thread(run1);
		th3.setName("Thread #3");
		Thread th4 = new Thread(run2);
		th4.setName("Thread #4");
		
		th1.start();
		th2.start();
		th3.start();
		th4.start();
	}
	/*
	  Thread 확장한 MyThread클래스 경우 해당 객체에 start()메서드 직접 호출 가능
	     반면 Runnable 구현한 MyRunnable클래스 경우,
	   Runnable형 인자 받는 생성자 통해 별도의 Thread 객체 생성 후 start()메서드 호출해야 함 
	 */
}

class MyThread extends Thread {
	private static final Random ran = new Random();
	
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println("- " + threadName + " has been started");
		int delay = 1000 + ran.nextInt(4000);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("- " + threadName + " has been ended (" + delay + "ms)");
	}
}

class MyRunnable implements Runnable {
	private static final Random ran = new Random();
	
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println("- " + threadName + " has been started");
		int delay = 1000 + ran.nextInt(4000);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("- " + threadName + " has been ended (" + delay + "ms)");
	}
}
