import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class kernel {
	
	static String basePath = "C:\\Users\\Omar\\OSnew\\OS Project\\";
	public static void createFile(String s) {
		try {
			String r = basePath + s + ".txt";
			File myObj = new File(r);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void WritetoFile(String s, String g) {
		try {
			String r = basePath + s + ".txt";
			FileWriter myWriter = new FileWriter(r);
			myWriter.write(g);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public static boolean doesFileExist(String s) {
		String r = basePath + s + ".txt";
		File file = new File(r);
		return file.exists();
	}
	
	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	static Scanner sc = new Scanner(System.in);
	public static void readFromDisk(String path, Process P) throws FileNotFoundException {
		System.out.println("Entering Kernel (readFromDisk):");
		System.out.println("Read From File:");
		File program1 = new File(path);
		Scanner fetch = new Scanner(program1);
		if (!fetch.hasNext()) {
			System.out.println("File is Empty");
		} else {
			P.stack.push(fetch.nextLine());
		}

	}
	
	public static void writeToDisk(Instruction a, Process P) {
		System.out.println("Entering Kernel (writeToDisk):");
		System.out.println("Write File:");
		String FileName = P.variables.get(a.Instruction[1]);
		String text = P.variables.get(a.Instruction[2]);
		if (doesFileExist(FileName)) {
			WritetoFile(FileName, text);
		} else {
			createFile(FileName);
			WritetoFile(FileName, text);
		}
	}
	
	public static void printData(Instruction a, Process P) {
		System.out.println("Entering Kernel (printData):");
		System.out.println("Print");
		System.out.println(P.variables.get(a.Instruction[1]));
		
	}
	public static void takeInput(Instruction I, Process P) {
		System.out.println("Entering Kernel (takeInput):");
		System.out.println("Please enter a value for " + I.Instruction[1]);
		P.stack.push(sc.nextLine());
	}
	
	
public static void writeToMemory(Instruction a, Process P) {
		System.out.println("Entering Kernel(writeToMemory):");
		System.out.println("Assign:");
		if (a.Instruction[2].equalsIgnoreCase("input") || a.Instruction[2].equalsIgnoreCase("readfile")) {
			P.variables.put(a.Instruction[1], P.stack.pop());
		} else {
			P.variables.put(a.Instruction[1], a.Instruction[2]);
		}
		}

public static void printFromTo(Instruction a, Process P) {
	System.out.println("Entering Kernel:(print)");
	int x;
	int y;
	if (isNumeric(a.Instruction[1])) {
		x = Integer.parseInt(a.Instruction[1]);
	} else {
		x = Integer.parseInt(P.variables.get(a.Instruction[1]));
	}

	if (isNumeric(a.Instruction[2])) {
		y = Integer.parseInt(a.Instruction[2]);
	} else {
		y = Integer.parseInt(P.variables.get(a.Instruction[2]));
	}
	while (x != y) {
		System.out.println(x);
		x++;
	}
}
}
