package kr.or.ddit.basic;

public class T03ThreadTest {

	public static void main(String[] args) {
		//스레드의 수행시간 체크해보기
		Thread th = new Thread(new MyRunner());
		
		//UTC(Universal Time Coodinated : 협정 세계 표준시) 사용
		//1970년 1월 1일 0시 0분 0초 기준으로 경과한 시간을 밀리세컨드 단위로 나타냄.
		long startTime = System.currentTimeMillis();
		
		th.start();  //스레드 구동 시작
		
		try {
			th.join(); //멈춰있기 - th가 run() 끝날 때까지 기다림
			//현재 실행 중인 스레드에서 작업 중인 스레드(여기선 th 스레드)가 종료될 때까지 기다림
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println(startTime);
		System.out.println("경과시간: " + (endTime - startTime) + "(ms)");
	}
}

//1~10억까지의 합계를 구하기
class MyRunner implements Runnable {

	@Override
	public void run() {
		long sum = 0;
		for(int i=0; i<=1000000000; i++) {
			sum += i;
		}
		System.out.println("합계: " + sum);
	}
}
