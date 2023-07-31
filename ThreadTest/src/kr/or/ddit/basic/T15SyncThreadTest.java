package kr.or.ddit.basic;

public class T15SyncThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		Thread th1 = new WorkerThread("1번스레드", sObj);
		Thread th2 = new WorkerThread("2번스레드", sObj);
		
		th1.start();
		th2.start();
		//예기치 못한 데이터 발생 가능성 높음, 무작위임 
		//			=> 줄 세우기 => 단일 thread처럼 동작(한 개씩 작업) => !동기화! : 임계 영역에 한 놈씩 들어가게 => 느려ㅠ
	}
}

//공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	//동기화 할 수록 느려짐 => 범위 최소화, 가급적 안하는 게 나음 안하면 데이터 망가지는 경우 말고
	// 동기화 하는 방법1 : 메서드 자체에 동기화 설정하기
	public synchronized void add() {
		//동기화 하는 방법2 : 동기화 블럭으로 설정하기(원하는 부분만)
		//mutex : Mutal exclusion Object(상호배제 : 동시접근차단)
		//				=> 동시 접근 차단해야되는 객체  => 현재는 this => 한 놈씩 하려고
		//synchronized (this) {
			for(int i = 0; i < 1000000000; i++) {}  //동기화 전까지 시간벌기용
			
				int n = sum;
				n += 10;
				sum = n;
				System.out.println(Thread.currentThread().getName() + " 합계: " + sum);
	//	}
		
	}
}

//작업을 수행하는 스레드
class WorkerThread extends Thread {
	private ShareObject sObj;	
	
	public WorkerThread(String name, ShareObject sObj) {
		super(name); //원하는 이름으로 Thread 지정
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			
			synchronized (sObj) {  //한 놈 진입하는 순간 다른 놈은 못들어옴
				sObj.add();   //공유 객체에 있는 add()호출
			}
		}
	}
}