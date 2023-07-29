package kr.or.ddit.basic;

public class T01ThreadTest {
	public static void main(String[] args) {
		//싱글스레드 프로그램 : Thread가 하나뿐인 프로그램
		for(int i = 1; i < 200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for(int i = 1; i < 200; i++) {
			System.out.print("$");
		}
	}

}
