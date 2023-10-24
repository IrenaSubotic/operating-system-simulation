package memory;

public class RAM {
 private static final int SIZE=1024;
 private static int[] ram=new int[SIZE];
 
public static void initialize() {
	for(int i=0;i<SIZE;i++)
		ram[i]=-1;
}
public static void print() {
	System.out.println("*********************RAM*********************");
	for(int i=0;i<SIZE;i++) {
		if(i%16 == 0) {
			System.out.println();
			}
		System.out.print(ram[i]+" ");
	}
}
public static void main(String[] args) {
	RAM ram=new RAM();
	ram.initialize();
	ram.print();
}
}
