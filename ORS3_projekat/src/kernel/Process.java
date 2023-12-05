package kernel;

import memory.MemoryManager;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import shell.Shell;

public class Process {
	private int pid;
	private String name;
	private ProcessState state;
	private int executingTime;
	private ArrayList<String> instructions = new ArrayList<String>();
	private int startAddress;
	private int size;
	private int pcValue = -1;
	private int[] valuesOfRegisters;
	private Path path;

	
	public Process(String name) {
		if (new File(Shell.tree.getCurrentFolder().getAbsolutePath() + "\\" + name).exists()) {
			this.pid = ProcessScheduler.allProcesses.size();
			this.state = ProcessState.READY;
			this.path = Paths.get(Shell.tree.getCurrentFolder().getAbsolutePath() + "\\" + name);
			this.name = name;
			valuesOfRegisters = new int[4];
			instructions = new ArrayList<>();
			readFile();
			this.size = instructions.size();
			this.executingTime=calculateExecutingTime();
			ProcessScheduler.allProcesses.add(this);
			ProcessScheduler.readyQueue.add(this);
			startAddress=MemoryManager.loadProcess(this);
			System.out.println("Program " + name +  " is loaded");
		} else {
			System.out.println("Program " + name + " doesn't exist in this directory");
		}
	}
	

	
	
	//cita asemblerske instrukcije iz sekundarne memorije i u listu instructions upisuje masinske instrukcije 
	   public void readFile() {  
		String data = Shell.memory.read(Shell.memory.getFile(name));
		String [] commands = data.split("\\n");
		for(String command : commands) {
			if( !command.equals(commands[commands.length-1]) ) {
				command = command.substring(0, command.length() -1);
			}
			else {
				if( command.length() > 3 )
					command = command.substring(0,3);
			}
		String machineIstruction = Shell.assemblerToMachineInstruction(command);
		instructions.add(machineIstruction);
		}
	
	}
	
	public ArrayList<String> getInstructions() {
		return instructions;
	}
	public int getPId() {
		return pid;
	}
	public void setValuesOfRegisters(int[] valuesOfRegisters) {
		for (int i = 0; i < valuesOfRegisters.length; i++)
			this.valuesOfRegisters[i] = valuesOfRegisters[i];
	}
	public int[] getValuesOfRegisters() {
		return valuesOfRegisters;
	}
	public void setPcValue(int pcValue) {
		this.pcValue = pcValue;
	}

	
	public int getPcValue() {
		return pcValue;
	}
	public Path getPath() {
		return path;
	}
	public String getName() {
		return name;
	}
	
	public void setStartAddress(int address) {
		this.startAddress=address;
	 
	}
	public int getStartAddress() {
		return this.startAddress;

	}
	private int calculateExecutingTime() {
		// TODO Auto-generated method stub
		Random rand=new Random();
		return instructions.size() * 5 + rand.nextInt(20);
	}
	public int getExecutingTime() {
		return executingTime;
	}
	public void block() {
		if (this.state == ProcessState.RUNNING) {
			this.state = ProcessState.BLOCKED;
			if (ProcessScheduler.readyQueue.contains(this))
				ProcessScheduler.readyQueue.remove(this);
		}
	}
	public void terminate() {
		if (this.state == ProcessState.READY || this.state == ProcessState.RUNNING) {
			this.state = ProcessState.TERMINATED;
			if (ProcessScheduler.readyQueue.contains(this))
				ProcessScheduler.readyQueue.remove(this);
		} else if (this.state == ProcessState.BLOCKED) {
			this.state = ProcessState.TERMINATED;
		}
	}public void setProcessState(ProcessState state) {
		// TODO Auto-generated method stub
		this.state=state;
	}
	public String toString() {
		return "Process : [pId = " + this.getPId() + ", name = " + name + ", path = " + path + ", state = "
				+ this.getProcessState() + "]";
	}


	public ProcessState getProcessState() {
	return state;
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	public void unblock() {
		// TODO Auto-generated method stub
		
			if (this.getProcessState() == ProcessState.BLOCKED) {
				this.setProcessState(ProcessState.READY);
				ProcessScheduler.readyQueue.add(this);
				System.out.println("Process " + this.getName() + " is unblocked!");
				
			
		}
	}
	
}
