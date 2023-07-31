package kr.or.ddit.basic;

public class T20WaitNotifyTest {
	public static void main(String[] args) {
		
		DataBox dataBox = new DataBox();
		
		ProducerThread pTh = new ProducerThread(dataBox);
		ConsumerThread cTh = new ConsumerThread(dataBox);
		
		pTh.start();
		cTh.start();
	}
}

//데이터를 공통으로 사용하는 클래스
class DataBox {
	private String data;
	
	// data가 null이 아닐 때 data값을 반환하는 메서드
	public synchronized String getData() {
		System.out.println(Thread.currentThread().getName()
				+ " : getData() 메서드 진입...");
		if(this.data == null) {
			try {
				System.out.println(
						Thread.currentThread().getName()
						+ " : getData()메서드 안에서 wait()호출");
				wait();		// 일시정지
			}catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		String returnData = this.data;
		System.out.println("읽어온 데이터 : " + returnData);
		this.data = null;		// 데이터 제거
		System.out.println(Thread.currentThread().getName()
				+ " : getData() 메서드 안에서 notify() 호출...");
		notify();
		
		System.out.println(Thread.currentThread().getName()
				+ " getData() 메서드 끝...");
		return returnData;
	}
	
	// data가 null일 경우에만 데이터를 세팅하는 메서드 : 있으면(null 아니면) 할 필요가 없음
	public synchronized void setData(String data) {
		System.out.println(Thread.currentThread().getName()
				+ " : setData() 매서드 진입...");
		if(this.data != null) {
			try {
				System.out.println(Thread.currentThread().getName()
						+ " : setData() 매서드 안에서 wait() 호출...");
				wait();  //대기실 가면 그 사이에 세팅하는 애가 세팅하고 감
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		this.data = data;		// 데이터 세팅
		System.out.println("세팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName()
				+ " : setData() 매서드 안에서 notify() 호출...");
		notify(); 		// 데이터 가져갔으니까 세팅하라고 깨움
		
		System.out.println(Thread.currentThread().getName()
				+ " : setData() 매서드 끝...");
	}
}

// 데이터를 세팅만 하는 스레드
class ProducerThread extends Thread {
	private DataBox dataBox;
	
	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=5; i++) {
			String data = "Data-" + i;
			System.out.println(this.getName()
					+ "가 dataBox.setData(" + data +") 호출하려고 함");
			dataBox.setData(data);
		}
	}
}

// 데이터를 읽어만 오는 스레드
class ConsumerThread extends Thread {
	private DataBox dataBox;
	
	public ConsumerThread(DataBox dataBox) {
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=5; i++) {
			String data = dataBox.getData();
			System.out.println(this.getName()
					+ "가 dataBox.getData() 호출 후 결과 받음 : " + data);
		}
	}
}