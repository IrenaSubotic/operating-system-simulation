package memory;

public class Block {

	private final static int SIZE=4;
	private byte[] content=new byte[SIZE];
	private int address;
	private boolean occupied;
	
	public Block(int address) {
		this.address=address;
		setOccupied(false);
	}

	public void setOccupied(boolean occupied) {
		// TODO Auto-generated method stub
		this.occupied=occupied;
	}
	public static int getSize() {
		return SIZE;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setContent(byte[] content) {
		this.content=content;
	}
	public int getAddress() {
		return address;
	}

	public byte[] getContent() {
		// TODO Auto-generated method stub
		return this.content;
	}
}
