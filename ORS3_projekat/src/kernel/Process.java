package kernel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import memory.MemoryManager;

public class Process {
	private int pid;
	private String name;
	private ProcessState state;
	private int executingTime;
	private ArrayList<String> instructions = new ArrayList<String>();
	private int startAddress;
	private int pcValue = -1;
	private int[] valuesOfRegisters;

	
	public Process(String path) {
		ProcessScheduler.allProcesses.add(this);
		ProcessScheduler.readyQueue.add(this);
		this.pid=ProcessScheduler.allProcesses.size();
		state=ProcessState.READY;
		name=path;
		writeToInstructionList(path);
		this.executingTime=calculateExecutingTime();
		valuesOfRegisters = new int[4];
	
		
	
	}
	
	public ArrayList<String> getInstructions() {
		return instructions;
	}
	public int getPId() {
		return pid;
	}
	
	public int getPcValue() {
		return pcValue;
	}
	public void setStartAddress(int address) {
		this.startAddress=address;
	 
	}
	private int calculateExecutingTime() {
		// TODO Auto-generated method stub
		Random rand=new Random();
		return instructions.size() * 5 + rand.nextInt(20);
	}
	public int getExecutingTime() {
		return executingTime;
	}
	private void writeToInstructionList(String path) {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String instruction = "";
			while ((instruction = br.readLine()) != null) {
				instructions.add(instruction);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void block() {
		if (this.state == ProcessState.READY) {
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
	}public void setState(ProcessState state) {
		// TODO Auto-generated method stub
		this.state=state;
	}
	

	

}