package memory;

import java.util.ArrayList;

import kernel.Process;

public class MemoryManager {
public ArrayList<Partition> partitionsInRAM;
public MemoryManager() {
	RAM.initialize();
	Partition.initialize();
	partitionsInRAM=new ArrayList<>();
}
public static void printMemory() {
	RAM.printRAM();
	Operations.printRegisters();
	SecondaryMemory.print();
}
	public int loadProcess(Process process) {
		// TODO Auto-generated method stub
		Tree.insertNode(process);
		Tree.fillLists(Tree.root);
		Tree.loadInRAM(process);
		Tree.list.clear();
		Tree.nodesList.clear();
		Tree.occupationList.clear();
		return 0;
	}
	public static void main(String[] args) {
		String weirdWord="10000";
		int res=Integer.parseInt(weirdWord, 2);
		System.out.println(res);
	}
}


