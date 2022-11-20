import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class Executer {
	static Resource userInput = new Resource("user input ", true);
	static Resource userOutput = new Resource("user output", true);
	static Resource fileAccess = new Resource("file access", true);
	
	static String basePath = "C:\\Users\\Omar\\OSnew\\OS Project\\";



	public static void execute_instruction(Instruction a, Process P) throws FileNotFoundException {
		if (a.Instruction[0].equalsIgnoreCase("print")) {
			kernel.printData(a, P);

		} else if (a.Instruction[0].equalsIgnoreCase("assign")) {
			kernel.writeToMemory(a,P);

		} else if (a.Instruction[0].equalsIgnoreCase("writefile")) {
			kernel.writeToDisk(a, P);

		} else if (a.Instruction[0].equalsIgnoreCase("printfromto")) {
			
			kernel.printFromTo(a,P);
			
		} else if (a.Instruction[0].equalsIgnoreCase("semwait")) {
			//System.out.println("SemWait");

			if (a.Instruction[1].equalsIgnoreCase("userinput")) {
				userInput.SemWait(P);
			}
			if (a.Instruction[1].equalsIgnoreCase("useroutput")) {
				userOutput.SemWait(P);
			}
			if (a.Instruction[1].equalsIgnoreCase("file")) {
				fileAccess.SemWait(P);
			}
		} else if (a.Instruction[0].equalsIgnoreCase("semsignal")) {
			//System.out.println("SemSignal");
			if (a.Instruction[1].equalsIgnoreCase("userinput")) {
				userInput.SemPost(P);
			}
			if (a.Instruction[1].equalsIgnoreCase("useroutput")) {
				userOutput.SemPost(P);
			}
			if (a.Instruction[1].equalsIgnoreCase("file")) {
				fileAccess.SemPost(P);
			}
		} else if (a.Instruction[0].equalsIgnoreCase("input")) {
			
			kernel.takeInput(a, P);
			
		} else if (a.Instruction[0].equalsIgnoreCase("readfile")) {
			String s = basePath + P.variables.get(a.Instruction[1]) + ".txt";
			kernel.readFromDisk(s, P);
		}
	}

	public static void main(String[] args) {

	}
}
