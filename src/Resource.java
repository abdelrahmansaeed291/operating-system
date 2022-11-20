import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Resource {
	String resourceName;
	boolean mutex;
	int ownerId;
	BlockedQueue blockedQueue;

	public Resource(String resourceName, boolean flag) {
		this.resourceName = resourceName;
		mutex = flag;
		blockedQueue = new BlockedQueue();

	}
	public void SemWait(Process p) {
		System.out.println("Semwait on " + resourceName);
		if(this.mutex==true) {
			this.mutex = false;
			this.ownerId = p.processID;
		}else {
			System.out.println("Process " + p.processID + " has been blocked by resource " + this.resourceName +" because it is currently being used by process " + this.ownerId);
			blockedQueue.block(p);
			BlockedQueue.addToGeneralBlockedQueue(p);
			p.setState(State.BLOCKED);
		}
	}
	
	
	void SemPost(Process p) {
		
		if(ownerId == p.processID) {
			System.out.println("Semwait on " + resourceName);
			if(blockedQueue.isEmpty()) {
				mutex = true;
			}else {
				Process newP = blockedQueue.unblock();
				BlockedQueue.removeFromGeneralBlockedQueue(newP);
				ReadyQueue.addToReadyQueue(newP);
				System.out.println("Process " + newP.processID + " has been added to the Ready Queue");
				this.ownerId = newP.processID;
				newP.setState(State.READY);
			}
			
		}else {
			System.out.println("This process can't SemPost");
		}
	}
	public void display() {
        String s = resourceName+"'s Blocked Queue: *";
        if(!blockedQueue.isEmpty()) {
            for (int i = 0; i < blockedQueue.blockedQueue.size(); i++) {
                s = s +"[" + blockedQueue.blockedQueue.peek().toString() + "], ";
                blockedQueue.blockedQueue.add(blockedQueue.blockedQueue.remove());
            }
        }else {
            s = s + "The Queue is Empty";
        }
		System.out.println(s);

    } 
}
