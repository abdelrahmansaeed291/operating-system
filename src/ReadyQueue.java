import java.util.LinkedList;
import java.util.Queue;
public class ReadyQueue {
	
	
	static Queue<Process> ReadyQueue = new LinkedList<>();
	
	
	public ReadyQueue() {
		
	}
	public static Queue<Process> getReadyQueue() {
		return ReadyQueue;
	}
	public static void setReadyQueue(Queue<Process> readyQueue) {
		ReadyQueue = readyQueue;
	}
	public static void addToReadyQueue(Process p) {
		ReadyQueue.add(p);
	}
	public static Process removeFromReadyQueue() {
		return ReadyQueue.poll();
	}
	public static boolean isEmpty() {
		return ReadyQueue.isEmpty();
	}
	public static  void display() {
        String s = "Ready Queue: * ";
        if(!ReadyQueue.isEmpty()) {
            for (int i = 0; i < ReadyQueue.size(); i++) {
                s = s +"[" + ReadyQueue.peek().toString() + "], ";
                ReadyQueue.add(ReadyQueue.remove());
            }
        }else {
            s = s + "The Queue is Empty";
        }
		System.out.println(s);

    } 


}
