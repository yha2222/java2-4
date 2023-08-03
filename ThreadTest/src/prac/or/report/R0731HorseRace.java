package prac.or.report;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class R0731HorseRace {

	public static int rank = 1;  // 현재 말 순위
	public static List<Horse> horseList;
	
	public static void main(String[] args) {
		horseList = Arrays.asList(new Horse("01번말"), new Horse("02번말"),
						new Horse("03번말"), new Horse("04번말"),new Horse("05번말"),
						new Horse("06번말"), new Horse("07번말"), new Horse("08번말"),
						new Horse("09번말"), new Horse("10번말"));
		
		Thread hpd = new HorsePositionDisplay();
		hpd.start();
		
		for(int i=0; i<horseList.size(); i++) {
			horseList.get(i).start();
		}
		
		try {
			hpd.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("==========================");
		System.out.println("경마 종료");
		System.out.println();
		
		Collections.sort(horseList);
		
		System.out.println("==========================");
		System.out.println("          경마 순위");
		System.out.println("==========================");  
		for(int i =0; i < horseList.size(); i++) {
			System.out.println(horseList.get(i).getHRank() + "등\t===>\t" + horseList.get(i).getHName());
		}
		
	}
}

class Horse extends Thread implements Comparable<Horse> {
	private String name;
	private int rank;
	private int position;
	
	//생성자 name - 경주말 이름
	public Horse(String name) {
		super(name); //스레드 이름 설정
		this.name = name;
	}

	public String getHName() {
		return name;
	}

	public void setHName(String name) {
		this.name = name;
	}

	public int getHRank() {
		return rank;
	}

	public void setHRank(int rank) {
		this.rank = rank;
	}

	public int getHPos() {
		return position;
	}

	public void setHPos(int position) {
		this.position = position;
	}

	@Override
	public int compareTo(Horse o) {
		return Integer.compare(rank, o.getHRank());
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 50; i++) {
			try {
				//구간 사이 딜레이
				Thread.sleep((int)(Math.random()*500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setHPos(i); //각 구간으로 말 위치 이동
		}
		this.rank = R0731HorseRace.rank;
		R0731HorseRace.rank++;
	}
}


class HorsePositionDisplay extends Thread {
	//화면 출력 정리를 위한 메서드
	public void clear() {
		for(int i = 0; i < 30; i++) {
			System.out.println();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			
			clear(); // 화면 지우기
			
			int finishedCnt = 0;
			System.out.println("경마 시작");
			System.out.println("===================");
			System.out.println();
			
			for(int i = 0; i < R0731HorseRace.horseList.size(); i++ ) {
				String horseCourse = "-------------------";
				Horse horse = R0731HorseRace.horseList.get(i);
				
				if(horse.getHPos() != 49) {
					System.out.println(horse.getHName() + " : ");
					System.out.println(horseCourse.substring(0, horse.getHPos()) + ">");
					System.out.println(horseCourse.substring(horse.getHPos() + 1, 50));
				}else {
					System.out.println(horse.getHName() + " : ");
					System.out.println(horseCourse.substring(horse.getHPos() + 1) + "[도착]");
					System.out.println();
					
					finishedCnt++;
				}
			}
			
			if(finishedCnt == 10) {
				return;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
