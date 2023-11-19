package memory;

import java.util.ArrayList;
import kernel.Process;

public class Partition {
 private int[] data;
 private int size;
 private int used;
 private Process process;
 private int positionInMemory=-1;
 private static ArrayList<Partition> partitions=new ArrayList<>();

 
 public Partition(Tree tree,Process process) {
	this.process=process;
	this.used=process.getInstructions().size();
	data=new int[used];
	tree.insertNode(process);
	Node n=tree.findGivenNodeByName(tree.root,process);
	size=n.getSize();
	partitions.add(this);
 }

public static Partition getPartitionByAddress(int address) {
	 for(Partition partition: partitions) {
		 if(partition.getPositionInMemory()==address)
			 return partition;
	 }
	 return null;
 }

 public static Partition getPartitionByProcess(Process process) {
	 for(Partition partition: partitions) {
		 if(partition.getProcess().equals(process))
			 return partition;
	 }
	 return null;
 }
 public int getPositionInMemory() {
	 return positionInMemory;
 }
 public int[] getData() {
	 return data;
 }
 public int getSize() {
	 return size;
 }
 public Process getProcess() {
	 return process;
 }
}
