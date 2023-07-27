package kr.or.ddit.basic;

public class T14ThreadShareDataTest {
	
/*
 	스레드에서 데이터를 공통으로 사용하는 방법
 	
 	1. 공통으로 사용할 데이터를 클래스로 정의한다
 	2. 공통으로 사용할 클래스의 인스턴스를 생성한다
 	3. 이 인스턴스를 각각의 스레드에 넘겨준다
 	4. 각각의 스레드는 이 인스턴스의 참조값을 저장한 변수를 이용하여 공통 데이터를 사용한다
 	
 	예) 원주율을 계산하는 스레드가 있고, 계산된 원주율을 출력하는 스레드가 있다.
 		원주율을 계산한 후 이 값을 출력하는 프로그램을 작성하시오.
 		(이 때 원주율 관련 정보를 저장할 객체가 필요하다.)
 */
}

//공유 객체 역할하는 거 정의
class ShareData {
	private double result; //원주율이 저장될 변수
	
	private boolean isOk;  //원주율 계산이 완료되었는지 확인하기 위한 변수

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
}

//원주율을 계산하는 스레드
class CalcPIThread extends Thread {
	private ShareData sd;
	
	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}
}

//계산된 원주율을 출력하기 위한 스레드
class PrintPIThread extends Thread {
	private ShareData sd;
	 
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
}