package shell;

import asembler.Operations;
import fileSystem.FileSystem;

public class Shell {
      public static FileSystem tree;
      //public static Memory memory;
      //public static Disk disk;
      //Proces
      public static String IR;
      public static int PC;
      
      public static void executeMachineInstruction() {
    	  
    	  String operation=IR.substring(0,4);
    	 // boolean programCounterChanged=false;
    	  
    	  
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
    		  //String adr=IR.substring(4,8);
    		  //Operations.jmp(adr);
    		  //***
    	  }
    	  
    	  else if(operation.equals(Operations.inc)) {
    		  String reg=IR.substring(4,8);
    		  Operations.inc(reg);
    	  }
    	  
    	  else if(operation.equals(Operations.dec)) {
    		  String reg=IR.substring(4,8);
    		  Operations.dec(reg);
    	  }
    	  
    	
    	  
      }
      
      
      }
