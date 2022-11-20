import java.util.ArrayList;

public class NewQueue {
    static ArrayList<Process> newQueue = new ArrayList<>();
    

    public static void fetchToReadyQueue (int processId){
            Process p = getProcess(processId);
            p.setState(State.READY);
            ReadyQueue.addToReadyQueue(p);

    }

    public static void addToNewQueue(Process p) {
        newQueue.add(p);
       // System.out.println("Process " + p.processID + " is added to the new queue");
    }

    public static Process getProcess (int processId){
        for(Process p: newQueue){
            if(p.processID == processId){
                return p;
            }
        }
        return null;
    }
}
