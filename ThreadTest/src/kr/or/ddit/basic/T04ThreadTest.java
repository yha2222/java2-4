package kr.or.ddit.basic;

public class T04ThreadTest {
	public static void main(String[] args) {
		/*
		  1~20억까지의 합계를 구하는 데 걸린 시간 체크해보기
		  전체 합계를 구하는 작업을 단독으로 했을 떄(1개의 스레드를 이용할 때)와
		  여러 스레드로 분할해서 작업할 떄의 시간을 확인해보기
		*/
		
		//단독으로 처리할 때
		SumThread sumTh = new SumThread(1, 2000000000);
		
		long startTime = System.currentTimeMillis();
		
		sumTh.start();
		
		try {
			sumTh.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리했을 떄의 처리 시간: " + (endTime - startTime) + "(ms)");
		
		//여러 스레드로 나누어 계산해 보기
		SumThread[] sumThs = new SumThread[] {
			new SumThread(1L, 500000000),
			new SumThread(500000001L, 1000000000),
			new SumThread(1000000001L, 1500000000),
			new SumThread(1500000001L, 2000000000),
		};
		
		startTime = System.currentTimeMillis();
		
		for(Thread th : sumThs) {
			th.start();
		}
		
		for(Thread th : sumThs) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("여러 개의 스레드로 나누어 처리했을 때 걸린 시간: "
				+ (endTime - startTime) + "(ms)");
		
		}
}

//별도의 Thread 클래스
class SumThread extends Thread {
	private long min, max;
	
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	//start하면 실행하는 부분
	@Override
	public void run() {
		long sum = 0;
		for(long i = min; i <= max; i++) {
			sum += i;
		}
		System.out.println(min + " ~ " + max + "까지의 합계: " + sum);
	}
}
