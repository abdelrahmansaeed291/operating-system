import java.io.FileNotFoundException;

public class Scheduler {
	int timeSlice;
	int clock = 0;
	int[] timeToArrive;
	static Process runnProcess;

	public Scheduler(int x, int[] timeToArrive) {
		timeSlice = x;
		this.timeToArrive = timeToArrive;

	}

	public void checkArrival() {
		for (int i = 0; i < timeToArrive.length; i++) {
			if (timeToArrive[i] == clock) {
				NewQueue.fetchToReadyQueue(i + 1);
			}
		}
	}

	public void displayAllQueues() {
		System.out.println("..............................");
		System.out.println("Running Process: " + runnProcess.toString());
		System.out.println();
		ReadyQueue.display();
		System.out.println();
		BlockedQueue.displayGeneralBlockedQueue();
		System.out.println();
		Executer.userInput.display();
		System.out.println();
		Executer.userOutput.display();
		System.out.println();
		Executer.fileAccess.display();
		System.out.println("..............................");

	}

	public void runProcess() throws FileNotFoundException {
		checkArrival();
		while (!ReadyQueue.isEmpty()) {

			Process p = ReadyQueue.removeFromReadyQueue();
			p.setState(State.RUNNING);
			int counter = timeSlice;
			while (p.state != State.FINISHED && p.state != State.BLOCKED && counter > 0) {
				System.out.println();
				System.out.println();
				System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
				System.out.println("Current Clock: " + clock);
				displayAllQueues();
				p.execute();
				clock++;
				counter--;
				checkArrival();
			}
			if (p.state == State.RUNNING) {
				p.setState(State.READY);
				ReadyQueue.addToReadyQueue(p);
			}

		}
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		System.out.println("All processes are finished");

	}

}
