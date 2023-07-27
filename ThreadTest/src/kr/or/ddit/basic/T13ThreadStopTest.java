package kr.or.ddit.basic;

//검사 중지시키는 방법
public class T13ThreadStopTest {
	public static void main(String[] args) {
		
//		ThreadStopEx1 th1 = new ThreadStopEx1();
//		th1.start();
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		/*
		 	Thread의 stop()메서드를 호출하면 스레드가 바로 멈춘다
		 	이때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서
		 	나중에 실행되는 프로그램에 영향을 줄 수 있다.
		 	그래서 현재는 stop()메서드는 비추천(Deprecated)되어 있다.
		 */
		//th1.stop();
		
		//th1.setStoped(true);
		
		//인터럽트 거는 용 실행문
		Thread th2 = new ThreadStopEx2();
		th2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.interrupt(); //인터럽트 걸기
	}
}

class ThreadStopEx1 extends Thread {
	
	private boolean isStoped;
	
	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}
	
	@Override
	public void run() {
		while(!isStoped) {
			System.out.println("스레드 처리 중...");
		}
		
		System.out.println("자원 정리 중...");
		System.out.println("실헹 종료");
	}
}

//interrupt() 메서드를 이용하여 스레드를 멈추게 하는 방법
class ThreadStopEx2 extends Thread {
	@Override
	public void run() {
		/*
		//방법1 => sleep()메서드나 join()메서드 등을 사용했을 때
		//			interrupt()메서드를 호출하면 interruptedException이 발생
		
		//예외 이용해서 무한루프 빠져나오기
		try {			
			while(true) {
				System.out.println("스레드 처리 중...");
				Thread.sleep(1);
				//이 줄 없으면 오류..이거 없으면 InterruptedException 일어날 일 없음
			}
		} catch(InterruptedException ex) {}
		*/
		
		//방법2 => interrupt()메서드가 호출되었는지 검사하기
		while(true) {
			System.out.println("스레드 처리 중...");
			
			/*
			// 검사방법1 => 스레드의 인스턴스용 메서드를 이용하는 방법
			// interrupt()메서드가 호출되면 true가 리턴됨
			if(this.isInterrupted()) { 	
				System.out.println("인스터스용 메서드 호출됨.");
				break;
			}
			*/
			
			// 검사 방법2 => 스레드의 정적 메서드를 이용하는 방법
			// interrupt()메서드가 호출되면 true가 리턴됨
			if(Thread.interrupted()) {
				System.out.println("정적 메서드 호출됨" + Thread.interrupted());
				break;
			}
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료");	
		
	}
}