package kr.or.ddit.basic;

public class T08ThreadPriorityTest {
	
//적용된다는 보장 없음 하면 뭐 좋지만 너무 믿지 마라..
	public static void main(String[] args) {
		//제공하는 상수 찍어보기
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
		
		Thread[] ths = new Thread[] {
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest2()
		};
		
		//start() 시키면 버스 떠나는 거임..
		//우선 순위는 start()메서드를 호출하기 전에 설정해야 함
		for(int i = 0; i < ths.length; i++) {
			if(i == 5) {
				ths[i].setPriority(10);
			}else {
				ths[i].setPriority(1);
			}
		}

		//현재 설정된 우선 순위 출력
		for(Thread th : ths) {
			System.out.println(th.getName() + "의 우선순위 : "
					+ th.getPriority());
		}
		
		//스레드 시작
		for(Thread th : ths) {
			th.start();
		}
	}

}

//대문자를 출력하는 스레드
class ThreadTest1 extends Thread {
	@Override
	public void run() {
		for(char ch='A'; ch <= 'Z'; ch++) {
			System.out.println(ch);
			
			//아무것도 하지 않는 반복문(시간 때우기용)
			for(long i = 1; i <= 1000000000L; i++) {}
		}
	}
}

class ThreadTest2 extends Thread {
	@Override
	public void run() {
		for(char ch='a'; ch <= 'z'; ch++) {
			System.out.println(ch);
			
			for(long i = 1; i <= 1000000000L; i++) {}
		}
	}
}