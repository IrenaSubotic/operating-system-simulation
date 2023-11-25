package kernel;

import java.util.ArrayList;
import java.util.List;

//import memory.Memory;
import shell.Shell;



public class ProcessScheduler  {
	public static List<Process> readyQueue = new ArrayList<Process>();;
	public static ArrayList<Process> allProcesses = new ArrayList<>();
	
	public static void sortProcesses() {
		
		for(int i=0;i<readyQueue.size();i++) {
			for(int j=i+1;j<readyQueue.size();j++) {
				Process temp=null;
				if(readyQueue.get(j).getExecutingTime()<readyQueue.get(i).getExecutingTime()) {
					temp=readyQueue.get(i);
					readyQueue.set(i,readyQueue.get(j));
					readyQueue.set(j, temp);
				}
			}
		
		}
		
		
	}
	public ProcessScheduler() {
		
	}
	
	public static void execute() {
		while(!readyQueue.isEmpty()) {
			sortProcesses();
	

				Process currentProcess =readyQueue.get(0);
				currentProcess.setState(ProcessState.RUNNING);
				int currentProcessWaitingTime = currentProcess.getExecutingTime();
			//	executeProcess(currentProcess);

				do {
					
						System.out.println("[ Remaining: " + currentProcessWaitingTime + " slices for process: "
								+ currentProcess.getPId() + " ]");
				
					currentProcessWaitingTime--;
				} while (currentProcessWaitingTime != 0);
				
				currentProcess.terminate();
				
			
			
		}
	
		}
	private static void executeProcess(Process currentProcess) {
		// TODO Auto-generated method stub
		Shell.currentlyExecuting = currentProcess;
		if (currentProcess.getPcValue() == -1) { // we need to start process
			System.out.println("Process " + currentProcess.getPId() + " started to execute");
			int startAddress = Shell.manager.loadProcess(currentProcess);
			currentProcess.setStartAddress(startAddress);
			Shell.base = startAddress;
			Shell.limit = currentProcess.getInstructions().size();
			Shell.PC = 0;
			currentProcess.setState(ProcessState.RUNNING);
	
		} else { // we need to continue process
			System.out.println("Process " + currentProcess.getPId() + " is executing again");
			int startAddress = Shell.manager.loadProcess(currentProcess);
			currentProcess.setStartAddress(startAddress);
			Shell.base = startAddress;
			Shell.limit = currentProcess.getInstructions().size();
			Shell.loadValues();
			currentProcess.setState(ProcessState.RUNNING);
			
		}
	}
	public static void printProcesses() {
		System.out.println("PID\t\t\tProgram\t\t\t\tSize\t\t\tState\t\t\t\tCurrent occupation of memory");
		for (Process process : allProcesses) {
			String print = "";
			print += process.getPId() + "\t\t\t";
			if (process.getName().length() < 8)
				print += process.getName() + "\t\t\t\t";
			else if (process.getName().length() < 12)
				print += process.getName() + "\t\t\t";
			else
				print += process.getName() + "\t\t";
			print += process.getSize() + "\t\t\t";
			if (process.getProcessState().toString().length() < 7)
				print += process.getProcessState() + "\t\t\t\t";
			else if (process.getProcessState().toString().length() < 9)
				print += process.getProcessState() + "\t\t\t";
			else
				print += process.getProcessState() + "\t\t";
			//print += Memory.memoryOccupiedByProcessSize(process);
			System.out.println(print);
		}
	}
	
		
		
		
		
	
	
	
	 
	       
	 
	      


}
