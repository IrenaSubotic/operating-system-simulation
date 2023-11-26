package memory;

public class RAM {
 private static final int SIZE=1024;
 private static int[] ram=new int[SIZE];
 
public static void initialize() {
	for(int i=0;i<SIZE;i++)
		ram[i]=-1;
}
public static void set(int start,int[] data) {
	for(int i=start;i<data.length + start;i++) {
		ram[i]=data[i-start];
	}
}
public static void remove(int start, int[] data) {
	for(int i=start;i<data.length + start;i++) {
		ram[i]=-1;
	}
}
public static void printRAM() {
	System.out.println("*********************RAM*********************");
	for(int i=0;i<SIZE;i++) {
		if(i%16 == 0) {
			System.out.println();
			}
		System.out.print(ram[i]+" ");
	}
}
}
