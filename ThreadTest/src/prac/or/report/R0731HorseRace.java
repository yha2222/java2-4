package prac.or.report;

import java.util.List;

public class R0731HorseRace {

	// 현재 말 순위
	public static int rank = 1;
	public static List<Horse> horseList;
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
	}
}