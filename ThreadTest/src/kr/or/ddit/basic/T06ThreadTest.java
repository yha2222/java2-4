package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class T06ThreadTest {
	//입력 여부를 확인하기 위한 변수 선언
	//모든 스레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false; //true면 사용자가 입력함

	public static void main(String[] args) {
		
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
	}
}

//데이터 입력받는 스레드
class DataInput extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		
		//입력이 완료되면 inputCheck 변수를 true로 변경
		T06ThreadTest.inputCheck = true;
		
		System.out.println("입력한 값은 " + str + "입니다.");
		
	}
}

//카운트다운 처리를 위한 스레드
class CountDown extends Thread {
	@Override
	public void run() {
		
		for(int i = 10; i >= 1; i--) {
			//입력이 완료되었는지 여부를 검사하고 입력이 완료되면 run()메서드를 종료.
			//즉, 현재 스레드를 종료시킨다.
			if(T06ThreadTest.inputCheck) {
				return; 		//false => run()메서드 종료되면 스레드도 끝.
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//10초가 경과되었는데도 입력이 안되면 프로그램을 종료
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); 		//프로그램을 종료시키는 명령
	}
}