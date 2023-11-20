package memory;

import java.util.ArrayList;

import kernel.Process;

public class MemoryManager {
public ArrayList<Partition> partitionsInRAM;
public void loadPartiton() {	
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
}

