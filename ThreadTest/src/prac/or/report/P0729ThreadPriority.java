package prac.or.report;

public class P0729ThreadPriority {

		public static void main(String[] args) {
			for(int i = 0; i <= 5; i++) {
				Thread thread = new Muti("[Thread " + i + " ]");
				if(i == 5) thread.setPriority(Thread.MAX_PRIORITY);
				else thread.setPriority(Thread.MIN_PRIORITY);
				thread.start();
			}
		}
	}

	class Muti extends Thread {
		public Muti(String threadName) {
			this.setName(threadName);
		}
		
		@Override
		public void run() {
			for(int i = 0; i < 1000; i++) {
				int x = 100;
				x += i;
				for(int j = 0; j < 1000; j++) {
					x += j;
				}
			}
			System.out.println(this.getName() + " Thread_Start");
		}
	}
