
public class Instruction {
	String[] Instruction;
	
 public Instruction(String[] x ) {
	 Instruction = x;
}
 
 public String toString() {
	String s = "";
	 for (int i = 0; i < Instruction.length; i++) {
		s = s + " ["+ Instruction[i] + "]";
	}
	 return s;
 }
 
 public static void main(String[] args) {
	
}
}
