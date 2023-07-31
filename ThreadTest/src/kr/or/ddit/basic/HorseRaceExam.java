package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HorseRaceExam {

	public static int rank = 1; // 현재 말의 순위

	public static List<Horse> horseList;

	public static void main(String[] args) {
		
		// 고정된 크기의 리스트 객체 생성하기
		horseList = Arrays.asList(new Horse("01번말"), new Horse("02번말"), new Horse("03번말"), new Horse("04번말"),
				new Horse("05번말"), new Horse("06번말"), new Horse("07번말"), new Horse("08번말"), new Horse("09번말"),
				new Horse("10번말"));
		//horseList.add(new Horse("99번말")); // UnsupportedOperationException
		
		Thread hpd = new HorsePositionDisplay();
		hpd.start();

		for (int i = 0; i < horseList.size(); i++) {
			horseList.get(i).start();
		}

		try {
			hpd.join();	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		System.out.println();
		System.out.println("============================================================");
		System.out.println("경마가 종료되었습니다.!!!");
		System.out.println();

		Collections.sort(horseList); // 리스트를 순위 오름차순으로 정렬하기

		System.out.println("======================");
		System.out.println("       경마 순위    ");
		System.out.println("======================");
		for (int i = 0; i < horseList.size(); i++) {
			System.out.println(horseList.get(i).getHorseRank() + "등\t===>\t" + horseList.get(i).getHorseName());
		}

	}
}

/**
 * 경주마 스레드 클래스
 */
class Horse extends Thread implements Comparable<Horse> {
	private String name; // 말이름
	private int rank; // 순위
	private int position; // 위치

	/**
	 * 생성자
	 *
	 * @param name 경주말 이름
	 */
	public Horse(String name) {
		super(name); // 스레드 이름 설정
		this.name = name;
	}

	public String getHorseName() {
		return name;
	}

	public void setHorseName(String name) {
		this.name = name;
	}

	public int getHorseRank() {
		return rank;
	}

	public void setHorseRank(int horseRank) {
		this.rank = horseRank;
	}

	public int getHorsePosi() {
		return position;
	}

	private void setHorsePosi(int horsePosi) {
		this.position = horsePosi;
	}

	@Override
	public int compareTo(Horse o) {
		return Integer.compare(rank, o.getHorseRank()); // 순위 오름차순으로 정렬하도록 함.
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep((int) (Math.random() * 500)); // 0~49 까지의 난수 발생(구간 사이의 시간 딜레이를 주기 위함)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setHorsePosi(i); // 각 구간으로 말의 위치 이동
		}
		this.rank = HorseRaceExam.rank;
		HorseRaceExam.rank++;
	}
}


/**
 * 전체 말의 위치를 출력하기 위한 스레드 클래스
 * @author macween
 *
 */
class HorsePositionDisplay extends Thread {

	/**
	 * 화면 출력 정리를 위한 메서드
	 */
	public void clear() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}

	@Override
	public void run() {

		while (true) {

			clear(); // 화면지우기(화면 정렬용)

			int finishedCnt = 0; // 도착한 말의 수
			System.out.println(" 지금 경마가 시작되었습니다.!!!");
			System.out.println("============================================================");
			System.out.println();

			for (int i = 0; i < HorseRaceExam.horseList.size(); i++) {

				String horseCourse = "--------------------------------------------------"; // 50구간
				Horse horse = HorseRaceExam.horseList.get(i);

				if (horse.getHorsePosi() != 49) { // 아직 도착하지 않은 경우...
					System.out.print(horse.getHorseName() + " : ");
					System.out.print(horseCourse.substring(0, horse.getHorsePosi()) + ">");
					System.out.println(horseCourse.substring(horse.getHorsePosi()+1, 50));

				} else { // 도착한 경우...
					System.out.print(horse.getHorseName() + " : ");
					System.out.print(horseCourse.substring(0, horse.getHorsePosi()+1) + " [도착]");
					System.out.println();

					finishedCnt++; // 도착한 말수 증가시키기
				}
			}

			if (finishedCnt == 10) { // 모든 경주말이 도착한 경우 ...
				return; // 쓰레드 종료
			}

			try {
				Thread.sleep(1000); // 1초마다 화면 출력
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
