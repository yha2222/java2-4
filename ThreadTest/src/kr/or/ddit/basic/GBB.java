package kr.or.ddit.basic;

import javax.swing.JOptionPane;
/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.

	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
	사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.

	입력시간은 5초로 제한하고 카운트 다운을 진행한다.
	5초안에 입력이 없으면 게임을 진것으로 처리한다.

	5초안에 입력이 완료되면 승패를 출력한다.

	결과예시)
		=== 결 과 ===
		컴퓨터 : 가위
		당  신 : 바위
		결  과 : 당신이 이겼습니다.

*/

public class GBB {
	public static boolean inputCheck = false;
	public static String users = "";
	
	public static void main(String[] args) {
		
		//컴퓨터 가위바위보
		String[] data = {"가위", "바위", "보"};
		int ran = (int)(Math.random() * 3);
		String com = data[ran];
		
//		String com = "";
//		int Comps = (int)(Math.random() * 3);
//		if(Comps == 1) {
//			com = "가위";
//		}else if(Comps == 2) {
//			com = "바위";
//		}else {
//			com = "보";
//		}
		
		//카운트다운 실행
		Thread tm = new GameTimerr();
		tm.start();
		
		//사용자로부터 가위, 바위, 보 입력받기
		Thread th1 = new ShowInputDialog();
		th1.start();
		
		try {
			th1.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		//결과 판정하기
		String result = "";
		if(users.equals(com)) {
			result = "비겼습니다.";
		}else if((users.equals("가위") && com.equals("보")
				|| (users.equals("바위") && com.equals("가위"))
				|| (users.equals("보") && com.equals("바위"))
				)) {
			result = "이겼습니다.";
		}else {
			result = "졌습니다.";
		}
		
		System.out.println("=== 결 과 ===");
		System.out.println("컴퓨터 : " + com);
		System.out.println("당  신 : " + users);
		System.out.println("결  과 : " + result);
	}
}

class ShowInputDialog extends Thread {
	@Override
	public void run() {
		
		String inputData = "";
		
		do {
			inputData = JOptionPane.showInputDialog("가위, 바위, 보를 입력하세요");
		}while(!inputData.equals("가위") && !inputData.equals("바위") && !inputData.equals("보"));
		
		GBB.inputCheck = true;
		GBB.users = inputData;
		
//		String userRes = JOptionPane.showInputDialog("가위 / 바위 / 보 중 하나를 입력하세요.");		
//		if(userRes.equals("가위") || userRes.equals("바위") || userRes.equals("보")) {
//			GBB.inputCheck = true;
//			GBB.users = userRes;
//		}else {
//			System.out.println("잘못된 입력!");
//		}
	}
}

class GameTimer extends Thread {
	@Override
	public void run() {
		
		for(int i = 5; i >= 1; i--) {
			if(GBB.inputCheck) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("5초가 초과되어 졌습니다.");
		System.exit(0);

	}
}