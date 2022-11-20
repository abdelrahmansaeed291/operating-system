import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Process {
	ArrayList<Instruction> instructions;
	int processID;
	int PC = 0;
	State state = State.READY;
	HashMap<String, String> variables;
	Stack<String> stack;

	public Process(ArrayList<Instruction> instructions, int processId) {
		this.instructions = instructions;
		this.processID = processId;
		variables = new HashMap();
		stack = new Stack<String>();
	}

	public void display() {
		for (int i = 0; i < instructions.size(); i++) {
			System.out.println(instructions.get(i).toString());
		}
	}

	public String toString() {
		return "Process No. " + processID;
	}

	public void execute() throws FileNotFoundException {

		System.out.println("Currently executing Instruction no. " + (this.PC + 1) + " in Process " + this.processID);
		Executer.execute_instruction(instructions.get(PC), this);
		PC++;
		if (PC == instructions.size()) {
			setState(State.FINISHED);
			;
			System.out.println("Process " + this.processID + " is finished");

		}

	}

	public void setState(State state) {
		this.state = state;
		if (state == State.RUNNING) {
			Scheduler.runnProcess = this;
		}
	}

}
