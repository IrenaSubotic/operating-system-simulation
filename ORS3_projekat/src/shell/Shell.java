ackage shell;

import java.io.File;

import asembler.Operations;
import fileSystem.FileSystem;
import kernel.ProcessScheduler;
import memory.MemoryManager;
import memory.SecondaryMemory;

public class Shell {
      public static FileSystem tree;
      public static MemoryManager manager;
      public static SecondaryMemory memory;
      public static Process currentlyExecuting=null;
      public static int PC;
      public static String IR;
      public static int limit;
      public static int base;

      
      public static void boot() {
    	  new ProcessScheduler();
    	  memory=new SecondaryMemory();
    	  Shell.manager=new MemoryManager();
    	  tree=new FileSystem(new File("PROGRAMS"));
      }
      
      
      
      
      public static void executeMachineInstruction() {
    	  
    	  String operation=IR.substring(0,4);
    	 boolean programCounterChanged=false;
    	  
    	  
    	  if(operation.equals(Operations.hlt)) {
    		  Operations.hlt();
    	  }
    	  
    	  
    	  else if(operation.equals(Operations.mov)) {
    		  String r1=IR.substring(4,8);
    		  if(IR.length()==12) {
    			  String r2=IR.substring(8,12);
    			  Operations.mov(r1,r2);
    		  }
    		  else if(IR.length()==16) {
    			  String val2=IR.substring(8,16);
    			  Operations.mov(r1,val2);
    		  }
    		  }
    	  
    	  
    	  else if(operation.equals(Operations.add)) {
    		  String r1=IR.substring(4,8);
    		  if(IR.length()==12) {
    			  String r2=IR.substring(8,12);
    			  Operations.add(r1,r2);
    		  }
    		  else if(IR.length()==16) {
    			  String val2=IR.substring(8,16);
    			  Operations.add(r1,val2);
    		  }
    	  }
    	  
    	  else if(operation.equals(Operations.sub)) {
    		  String r1=IR.substring(4,8);
    		  if(IR.length()==12) {
    			  String r2=IR.substring(8,12);
    			  Operations.sub(r1,r2);
    		  }
    		  else if(IR.length()==16) {
    			  String val2=IR.substring(8,16);
    			  Operations.sub(r1,val2);
    		  }
    	  }
    	  
    	  else if (operation.equals(Operations.mul)) {
    		  String r1=IR.substring(4,8);
    		  if(IR.length()==12) {
    			  String r2=IR.substring(8,12);
    			  Operations.mul(r1,r2);
    		  }
    		  else if(IR.length()==16) {
    			  String val2=IR.substring(8,16);
    			  Operations.mul(r1,val2);
    		  }
    	  }
    	  
    	  else if (operation.equals(Operations.div)) {
    		  String r1=IR.substring(4,8);
    		  if(IR.length()==12) {
    			  String r2=IR.substring(8,12);
    			  Operations.div(r1,r2);
    		  }
    		  else if(IR.length()==16) {
    			  String val2=IR.substring(8,16);
    			  Operations.div(r1,val2);
    		  }
    	  }
    	  
    	  else if(operation.equals(Operations.jmp)) {
    		  String adr=IR.substring(4,8);
    		  Operations.jmp(adr);
    		  programCounterChanged=true;
    	  }
    	  
    	  else if(operation.equals(Operations.inc)) {
    		  String reg=IR.substring(4,8);
    		  Operations.inc(reg);
    	  }
    	  
    	  else if(operation.equals(Operations.dec)) {
    		  String reg=IR.substring(4,8);
    		  Operations.dec(reg);
    	  }
    	  
    	  else if(operation.equals(Operations.jmpd)) {
    		  String reg=IR.substring(4, 8);
    		  String val=null;
    		  String adr=null;
    		  if(IR.length()==20) {   //oba registra
    			  val=IR.substring(8,12);
    			  adr=IR.substring(12,20);
    		  }
    		  else if(IR.length()==24) { //registar i vrijednost
    			  val=IR.substring(8,16);
    			  adr=IR.substring(16,24);
    		  }
    		  programCounterChanged=Operations.jmpd(reg, val, adr);
    	  }
    	  
    	  else if(operation.equals(Operations.jmpe)) {
    		  String reg=IR.substring(4,8);
    		  String val=null;
    		  String adr=null;
    		  if(IR.length()==20) { //oba registra
    			  val=IR.substring(8,12);
    			  adr=IR.substring(12,20);
    		  
    		  } else if(IR.length()==24) {  //registar i vrijednost
    			  val=IR.substring(8,16);
    			  adr=IR.substring(16,24);
    		  }
    		  programCounterChanged=Operations.jmpe(reg, val, adr);
    	  }
    	  
    	  
    	  if(!programCounterChanged)
    		  PC++;
 
      }
      
      public static String toBinary(String s) {
    	  int number=Integer.parseInt(s);
    	  int [] binary=new int [8];
    	  int index=0;
    	  int counter=0;
    	  while(number>0) {
    		  binary[index]=number % 2;
    		  index++;
    		  number=number / 2;
    		  counter++;
    	  }
    	  String bin="";
    	  counter=8-counter;
    	  for(int i=0; i<counter; i++)
    		  bin+="0";
    	  for(int i=index-1; i>=0; i--)
    		  bin+=binary[i];
    	  return bin;
    	  
      }
      
      public static void saveValues() {
    	  int [] registers= {Operations.R1.value, Operations.R2.value, Operations.R3.value, Operations.R4.value};
    	  currentlyExecuting.setValuesOfRegisters(registers);
    	  currentlyExecuting.setPcValue(PC);
    	  
      }
      public static void loadValues() {
    	  int [] registers=currentlyExecuting.getValuesOfRegisters();
    	  Operations.R1.value=registers[0];
    	  Operations.R2.value=registers[1];
    	  Operations.R3.value=registers[2];
    	  Operations.R4.value=registers[3];
    	  PC=currentlyExecuting.getPcValue();
    	  
      }
      
      
      
      
      
      
      
      }
