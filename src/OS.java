import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class OS {
	int timeSlice;
	int [] timeToArrive;
    Scheduler scheduler;
	public OS(String [] programsPaths,int timeSlice,int [] timeToArrive) throws FileNotFoundException {
		this.timeSlice = timeSlice;
		this.timeToArrive = timeToArrive;
		for(int i = 0;i<programsPaths.length;i++) {
			File program = new File(programsPaths[i]);
			Scanner sc = new Scanner(program);
			ArrayList <Instruction> instructions = new ArrayList <>();
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String [] tokens = line.split(" ");
				if(tokens.length>=3 && tokens[2] .equalsIgnoreCase("input")){
					String temp [] = new String [2];
					temp[0] = tokens[2];
					temp[1] = tokens[1]; 
					instructions.add(new Instruction(temp));
				}
				else if (tokens.length>=3 && tokens[2].equalsIgnoreCase("readfile")){
					String [] temp = new String [2];
					temp[0] = tokens[2];
					temp[1] = tokens[3];
					instructions.add(new Instruction(temp));
				}
				instructions.add(new Instruction(tokens));

			}
			NewQueue.addToNewQueue(new Process(instructions,i+1));
		}
		scheduler = new Scheduler(timeSlice,timeToArrive);
		
	}
	public void start() throws FileNotFoundException {
		scheduler.runProcess();
	}
	
public static void main(String[] args) throws FileNotFoundException {
	
String [] programStrings = {"C:\\Users\\Omar\\OSnew\\OS Project\\Program_1.txt","C:\\Users\\Omar\\OSnew\\OS Project\\Program_2.txt","C:\\Users\\Omar\\OSnew\\OS Project\\Program_3.txt"};
OS os = new OS(programStrings,2,new int [] {0,1,4});
os.start();
	
	
	

}
}
