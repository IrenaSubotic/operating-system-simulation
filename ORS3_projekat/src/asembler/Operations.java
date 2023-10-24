package asembler;

public class Operations {
	
	public static final String hlt="0000";
	public static final String mov="0001";
	public static final String add="0010";
	public static final String sub="0011";
	public static final String mul="0100";
	public static final String jmp="1101";
	public static final String dec="1110";
	public static final String inc="1111";
	public static final String div="1000";
	public static final String cmpe="1001";
	public static final String cmpd="1010";
	
	public static Register R1=new Register("R1", Constants.R1,0);
	public static Register R2=new Register("R2", Constants.R2, 0);
	public static Register R3=new Register("R3" , Constants.R3,0);
	public static Register R4=new Register("R4", Constants.R4,0);
	
	public static void mov(String reg, String val) {
		Register r=getRegister(reg);
		if(val.length()==8) {   //val je vrijednost
			if (r!=null)
				r.value=Integer.parseInt(val,2);
		}
		else if (val.length()==4) {  // val je registar
			Register r2=getRegister(val);
			if(r!=null && r2!=null)
				r.value=r2.value;
			
		}
	}
	
	
	public static void add(String reg, String val) {
		Register r=getRegister(reg);
		if(val.length()==8) {
			if(r!=null)
				r.value=r.value + Integer.parseInt(val,2);
		}
		else if (val.length()==4) {
			Register r2=getRegister(val);
			if(r!=null && r2!=null)
				r.value=r.value + r2.value;
		}
		  
	}
	
	
	public static void sub(String reg, String val) {
		Register r=getRegister(reg);
		if(val.length()==8) {
			if(r!=null) {
				r.value=r.value-Integer.parseInt(val,2);
			}
		}
		else if (val.length()==4) {
			Register r2=getRegister(val);
			if(r!=null && r2!=null)
				r.value=r.value-r2.value;
		}
	}
	
	
	public static void mul(String reg, String val) {
		Register r=getRegister(reg);
		if(val.length()==8) {
			if(r!=null)
				r.value=r.value*Integer.parseInt(val,2);
		}
		else if (val.length()==4) {
			Register r2=getRegister(val);
			if(r!=null && r2!=null)
				r.value=r.value*r2.value;
		}
		
	}
	
	
	public static void div(String reg, String val) {
		Register r=getRegister(reg);
		if(val.length()==8) {
			if(r!=null && !val.equals("00000000"))
				r.value=r.value / Integer.parseInt(val,2);
		}
		else if(val.length()==4) {
			Register r2=getRegister(val);
			if(r!=null && r2!=null && r2.value!=0)
				r.value=r.value / r2.value;
		}
		
	}
	
	
	public static void inc(String reg) {
		Register r=getRegister(reg);
		r.value=r.value+1;
	}
	 
	
    public static void dec(String reg){
    	Register r=getRegister(reg);
    	r.value=r.value-1;
    }
	
	
	
	private static Register getRegister(String address) {
		switch(address){
		case Constants.R1:
			return R1;
		case Constants.R2:
			return R2;
		case Constants.R3:
			return R3;
		case Constants.R4:
			return R4;
		default:
			return null;
		}
		
	}
	

}