package kr.or.ddit.basic;

// 스레드의 상태 변화 관찰하기 예제 - 비가역적(역류 없음)
public class T10ThreadStateTest {
	/*
	 * <스레드 상태>
	 * - NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태 
	 * - RUNNABLE : 실행중 또는 실행 가능한 상태
	 * - BLOCKED : 동기화 블럭에 의해서 일시정지된 상태(락이 풀릴 때까지 기다리는 상태)
	 * - WAITING, TIMED_WAITING : 스레드의 작업이 종료되지는 않았지만 실행 가능하지 않은 일시정지 상태.
	 *  							TIMED_WAITING은 일시정지 시간이 지정된 경우
	 * - TERMINATED : 스레드의 작업이 종료된 상태
	 */
	public static void main(String[] args) {
		
		TargetThread targetThread = new TargetThread();
		
		StatePrintThread printThread = new StatePrintThread(targetThread);
		
		printThread.start();  //main 스레드 제일 먼저 죽음 => target => print
	}
}

// 상태 모니터링 대상 스레드
class TargetThread extends Thread {
	@Override
	public void run() {
		//runnable
		for (long i = 1; i <= 1000000000L; i++) {
		} // 시간 지연용

		try {
			Thread.sleep(1500);  //timed_waiting => 1.5초
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//runnable
		for (long i = 1; i <= 1000000000L; i++) {
		} // 시간 지연용
	}
}

// 스레드의 상태를 모니터링 하는 스레드
class StatePrintThread extends Thread {
	
	private Thread targetThread;

	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}

	@Override
	public void run() {
		while (true) {
			// 스레드의 상태값 구하기
			//State => enum >> Thread에서만 쓰는 상수니까
			Thread.State state = targetThread.getState();
			System.out.println("타겟스레드의 상태값 : " + state);

			// NEW 상태인지 검사
			// 처음만! 다시 NEW로 돌아가는 경우 없음
			if (state == Thread.State.NEW) {
				targetThread.start(); // 스레드 시작
			}

			// 스레드가 종료되었는지 검사
			if (state == Thread.State.TERMINATED) {
				break;  
				//targetThread.start();
				//   => 예외 발생(terminated=>start 안됨) => start() 다시 하고 싶으면 새로 만들어야 됨
			}

			//모니터링
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}