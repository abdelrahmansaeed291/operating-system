import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
public class BlockedQueue {
	
	
	static Queue<Process> generalBlockedQueue = new LinkedList<>();
	Queue<Process> blockedQueue;
	
	
	public BlockedQueue() {
		blockedQueue = new LinkedList<>();
	}

	public static Queue<Process> getGeneralBlockedQueue() {
		return generalBlockedQueue;
	}

	public static void setGeneralBlockedQueue(Queue<Process> generalBlockedQueue) {
		BlockedQueue.generalBlockedQueue = generalBlockedQueue;
	}

	public void block(Process p) {
		blockedQueue.add(p);
	}

	public Process unblock() {
		return blockedQueue.poll();
	}
	public static void addToGeneralBlockedQueue(Process p) {
		generalBlockedQueue.add(p);
	}
	public static void removeFromGeneralBlockedQueue(Process p) {
		generalBlockedQueue.remove(p);
	}
	public boolean isEmpty() {
		return blockedQueue.isEmpty();
	}
	public static boolean isGeneralEmpty() {
		return generalBlockedQueue.isEmpty();
	}
	public static  void displayGeneralBlockedQueue() {
        String s = "General Blocked Queue: *";
        if(!generalBlockedQueue.isEmpty()) {
            for (int i = 0; i < generalBlockedQueue.size(); i++) {
                s = s +"[" + generalBlockedQueue.peek().toString() + "], ";
                generalBlockedQueue.add(generalBlockedQueue.remove());
            }
        }else {
            s = s + "The Queue is Empty";
        }
		System.out.println(s);
    } 
		

	
}
