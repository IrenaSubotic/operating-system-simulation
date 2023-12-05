package memory;

import java.util.ArrayList;

import asembler.Operations;
import kernel.Process;

public class MemoryManager {
public static ArrayList<Partition> partitionsInRAM;
public MemoryManager() {
	RAM.initialize();
	Partition.initialize();
	partitionsInRAM=new ArrayList<>();
}
public static void printMemory() {
	RAM.printRAM();
	System.out.println();
	Operations.printRegisters();
	System.out.println();
	SecondaryMemory.print();
}
public static void removeProcess(Process process) {
	Tree.deleteNode(process);
	removePartition(Partition.getPartitionByProcess(process));
}
public static void removePartition(Partition partition) {
	if(partitionsInRAM.contains(partition)) {
		RAM.remove(partition.getPositionInMemory(), partition.getSize());
		
		partition.setPositionInMemory(-1);
		partitionsInRAM.remove(partition);
	}
}
public static int loadInRAM(Process process) {
	   int sum=0;
		int pos=Tree.position(process);
		//System.out.println(pos);
		int size=Tree.list.get(pos).getSize();
		int usage=Tree.occupationList.get(pos);
		for(int i=0;i<pos;i++){
		   sum+=Tree.list.get(i).getSize();
		}
		//System.out.println(sum);
		Partition partition=new Partition(process);
		partition.setPositionInMemory(sum);
	    partitionsInRAM.add(partition);
		//Dodavanje particije u listu particija, dodavanje u listu particija u RAM-u
		RAM.set(sum,partition.getData());
		return sum;
	}
	public static int load(Process process) {
		// TODO Auto-generated method stub
		Tree.insertNode(process);
		Tree.fillLists(Tree.root);
		int position=loadInRAM(process);
		Tree.list.clear();
		Tree.nodesList.clear();
		Tree.occupationList.clear();
		//System.out.println("Position"+position);
		return position;
	}
	
	public static int loadProcess(Process process) {
	Partition partition=Partition.getPartitionByProcess(process);
	if(!partitionsInRAM.contains(partition)) {
		return load(process);
	}
	else {
		return process.getStartAddress();
	}
	
	}
	public static int memoryOccupiedByProcess(Process process) {
		for (Partition partition : partitionsInRAM)
			if (partition.getProcess().getPId() == process.getPId())
				return partition.getSize();
		return 0;
	}
}
